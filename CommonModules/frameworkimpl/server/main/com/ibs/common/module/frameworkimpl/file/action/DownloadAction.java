package com.ibs.common.module.frameworkimpl.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.apache.struts2.ServletActionContext;

import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.dto.DownloadDto;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceException;
import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.util.StringUtils;
import com.opensymphony.xwork2.ModelDriven;


public class DownloadAction extends BaseAction implements ModelDriven<DownloadDto>{
	
	private static final long serialVersionUID = 8474887933678699654L;
	
	private static final Integer DEFAULT_BUFFER_SIZE = new Integer(2048);
	private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream,charset=ISO8859-1";
	
	private IFilePersistenceService filePersistenceService;
	
	private DownloadDto downloadDto = new DownloadDto();
	
	/**
	 * 用于excel导出后的下载功能
	 * @return
	 */
	public String download() {
		
		logger.trace("entering action...");
		
		try {
			String phisicalFileName = null;
			
			if (StringUtils.isNotEmpty(downloadDto.getRealName())) {
				phisicalFileName = downloadDto.getRealName();
			} else if (StringUtils.isNotEmpty(downloadDto.getWebFileName())) {
				phisicalFileName = ServletActionContext.getServletContext().getRealPath(downloadDto.getWebFileName());
			}

			String fileName = downloadDto.getFileName();
			
			// tomcat对于GET请求并不会考虑使用request.setCharacterEncoding方法设置的编码，而会永远使用iso-8859-1编码。
			// 所以此判断是用于tomcat GET请求的处理，在WAS中不需要
			if (!Charset.defaultCharset().displayName().equalsIgnoreCase("UTF-8")) {
				fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
				phisicalFileName = new String(phisicalFileName.getBytes("ISO8859-1"), "UTF-8");
			}
			
			downloadDto.setContentLength(new File(phisicalFileName).length());
			downloadDto.setInputStream(new FileInputStream(phisicalFileName));
			
			//TODO 需要先判断字符串是否是中文，如果国际化则需要使用其他编码方式
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			phisicalFileName = new String(phisicalFileName.getBytes("GBK"), "ISO8859-1");
			
			downloadDto.setFileName(fileName);
			
		} catch (Exception e) {
			this.addActionError("文件导出或下载失败！");
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 用于文件上传后的下载功能
	 * @return
	 */
	public String downloadFile(){
		
		logger.trace("entering action...");
		
		logger.debug("fileId: " + downloadDto.getFileId());
		
		try {
			if (downloadDto.getBufferSize() == null)
				downloadDto.setBufferSize(DEFAULT_BUFFER_SIZE);
			if (downloadDto.getContentType() == null)
				downloadDto.setContentType(DEFAULT_CONTENT_TYPE);
			
			FilePersistence fp = this.filePersistenceService.getDownloadFile(downloadDto.getFileId());
			
			downloadDto.setFileName(new String(fp.getFileName().getBytes("GBK"), "ISO8859-1"));
			downloadDto.setContentType(fp.getContentType());
			downloadDto.setInputStream(new FileInputStream(fp.getPhsicalName()));
		} catch (FileServiceException fse) {
			this.addActionError(fse.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return ERROR;
		}
		
		return SUCCESS;
		
	}

	public IFilePersistenceService getFilePersistenceService() {
		return filePersistenceService;
	}

	public void setFilePersistenceService(
			IFilePersistenceService filePersistenceService) {
		this.filePersistenceService = filePersistenceService;
	}

	public DownloadDto getModel() {
		return this.downloadDto;
	}

}
