/**
 *@author swh1984
 * 对外实现upload download方法实现，内部实现文件上传逻辑，下载逻辑。
 */
package com.ibs.common.module.frameworkimpl.file.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.ibs.common.module.frameworkimpl.file.dao.IFilePersistenceDao;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceException;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceMessage;
import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.portal.framework.server.exception.BaseException;
import com.ibs.portal.framework.server.exception.ServiceException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.RandomUtils;

public class FilePersistenceServiceImpl extends BaseService implements IFilePersistenceService {

	private IFilePersistenceDao filePersistenceDao;
	
	private static final String FILES_IHS_PREFIX = "/files/";
	
	//从appliaction.properties配置文件中获取文件保存路径
	private String root ;
	
	private String module;
	
	/**
	 * 保存文件
	 * @param file//文件
	 * @param fileName//文件名
	 * @param fileContentType//上传文件类型
	 * @param ownerModule//指定所属模块
	 * @throws FileServiceException 
	 * @throws ServiceException
	 */
	public String saveFile(File file, String fileName, String fileContentType) throws FileServiceException {
		
		logger.trace("entering service...");
		
		//创建文件对象
		FilePersistence fp = new FilePersistence();
		fp.setFileName(fileName);
		fp.setOwnerModule(module);
		fp.setTempFile(file);
		fp.setContentType(fileContentType);
		fp.setOperatorID(this.getCurrentUser().getUserId());
		
		//获取时间戳
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		//设置上传时间
		fp.setLogDate(ts);
		//创建时间作为第2层文件夹
		String phsicalNameFolder = new SimpleDateFormat("yyyyMMdd").format(ts);
		//创建文件上传全路径
		String filePath = newFilePath(fp.getOwnerModule(), phsicalNameFolder);
		
		File destFile = this.newPhysicalFile(filePath, fp.getFileName(), phsicalNameFolder);
		
		//将物理文件名保存到实体类中
		fp.setPhsicalName(destFile.getAbsolutePath());
		//设置下载路径
		String downLoadPath = fp.getPhsicalName();
		
		String ihsFile = FILES_IHS_PREFIX + downLoadPath.substring(downLoadPath.indexOf(this.root) + this.root.length());
		
		fp.setDownLoadPath(ihsFile.replace(File.separator, "/"));

		try {
			this.saveToNFS(fp, destFile);
			fp.setContentLength(destFile.length());
			this.saveToDB(fp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			FileUtils.deleteQuietly(destFile);
			throw new FileServiceException(FileServiceMessage.FILE_SAVE_ERROR);
		}
		
		return fp.getId();
	}
	
	/**
	 * 保存文件到文件系统
	 * @param filePath
	 * @param fp
	 * @throws FileServiceException 
	 * @throws IOException 
	 * @throws BaseException
	 */
	private void saveToNFS(FilePersistence fp, File destFile) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(fp.getTempFile());
			os = new FileOutputStream(destFile);
			
			IOUtils.copy(is, os);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}

	public void removeFilePersistenceById(String id) {
		logger.trace("entering service...");
		
		FilePersistence fp = filePersistenceDao.getFilePersistence(id);
		File delFile = new File(fp.getPhsicalName());
		FileUtils.deleteQuietly(delFile);
		filePersistenceDao.removeFilePersistence(id);
	}
	
	/**
	 * 拼接上传路径字符串
	 * @param ownerModule	第一层目录
	 * @param timestamp		第二层目录
	 * @return
	 * @throws FileServiceException 
	 */
	private String newFilePath(String ownerModule, String date) throws FileServiceException {
		
		StringBuffer filePath = new StringBuffer();
		filePath.append(this.root);
		filePath.append(ownerModule);
		filePath.append(File.separator);
		filePath.append(date);
		
		try {
			File d = new File(filePath.toString());
			if (!d.exists())
				d.mkdirs();
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);
			throw new FileServiceException(FileServiceMessage.FILE_CANNOT_WRITE);
		}
		
		return filePath.toString();
	}
	
	private File newPhysicalFile(String filePath, String fileName, String suffix) {
		
		File newFile = null;
		
		while(true) {
			//获取随机数
			Integer rdm = Math.abs(RandomUtils.getRandom().nextInt());
			//拼接成新的文件名，作为物理路径
			String fileNewNameString = FilenameUtils.getBaseName(fileName) + "_" + suffix + "_" + rdm + FilenameUtils.EXTENSION_SEPARATOR
					+ FilenameUtils.getExtension(fileName);
			
			newFile = new File(filePath + File.separator + fileNewNameString);
			
			if (!newFile.exists())
				break;
		}
		
		return newFile;
	}
	
	/**
	 * 保存文件信息到数据库
	 * @param fp
	 * @return 
	 */
	private void saveToDB(FilePersistence fp) {
		filePersistenceDao.save(fp);
	}
	
	/**
	 * 根据文件ID获取文件
	 * @param fileId
	 * @throws FileServiceException 
	 */
	public FilePersistence getFile(String fileId) {
		
		logger.trace("entering service...");
		
		return filePersistenceDao.getFilePersistence(fileId);
	}

	public FilePersistence getDownloadFile(String fileId)
			throws FileServiceException {
		
		logger.trace("entering service...");
		
		FilePersistence fp = null;
		try {
			fp = filePersistenceDao.getFilePersistence(fileId);
			if (fp == null) {
				throw new FileServiceException(FileServiceMessage.FILE_INFO_NOT_EXIST);
			}

			File file = new File(fp.getPhsicalName());
			if (!file.exists()) {
				throw new FileServiceException(FileServiceMessage.FILE_NOT_EXIST);
			}
			if (!file.canRead()) {
				throw new FileServiceException(FileServiceMessage.FILE_CANNOT_READ);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (!(e instanceof FileServiceException))
				throw new FileServiceException(FileServiceMessage.FILE_GET_ERROR);
			else
				throw (FileServiceException)e;
		}
		
		return fp;
	}

	public String saveFile(FilePersistence filePersistence) {
		logger.trace("entering service...");
		
		return (String) filePersistenceDao.save(filePersistence);
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public IFilePersistenceDao getFilePersistenceDao() {
		return filePersistenceDao;
	}

	public void setFilePersistenceDao(IFilePersistenceDao filePersistenceDao) {
		this.filePersistenceDao = filePersistenceDao;
	}

	public IPage<FilePersistence> findFilePersistenceByQueryPage(
			QueryPage queryPage) {
		return filePersistenceDao.findFilePersistenceByPage(queryPage);
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
