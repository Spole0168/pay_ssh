package com.ibs.common.module.frameworkimpl.file.service;

import java.io.File;

import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceException;
import com.ibs.portal.framework.server.exception.ServiceException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IFilePersistenceService {
	/**
	 * 保存文件
	 * @param file//文件
	 * @param fileName//文件名
	 * @param fileContentType//文件类型，用于下载时候用户可以直接打开文件
	 * @param ownerModule//指定所属模块
	 * @throws ServiceException
	 * @return fileId
	 * @throws FileServiceException 
	 */
	public String saveFile(File file, String fileName, String fileContentType) throws FileServiceException;
	
	/**
	 * 根据文件ID获取文件
	 * @param fileId
	 * @throws ServiceException
	 * @return FilePersistence
	 * @throws FileServiceException 
	 */
	public FilePersistence getFile(String fileId);
	
	/**
	 * 根据文件ID获取文件
	 * 校验文件是否存在
	 * @param fileId
	 * @throws ServiceException
	 * @return FilePersistence
	 * @throws FileServiceException 
	 */
	public FilePersistence getDownloadFile(String fileId) throws FileServiceException;
	
	/**
	 * 分页对象查询，用于页面显示文件保存列表
	 * @param queryPage 分页查询条件
	 * @return 返回分页对象
	 */
	public IPage<FilePersistence> findFilePersistenceByQueryPage(QueryPage queryPage);
	
	/**
	 * 根据id删除文件对象
	 * @param id
	 */
	public void removeFilePersistenceById(String id);
	
	/**
	 * @return
	 */
	public String saveFile(FilePersistence filePersistence);
}
