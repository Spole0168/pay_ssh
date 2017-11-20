package com.ibs.common.module.frameworkimpl.file.dao;

import java.io.Serializable;

import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IFilePersistenceDao {
	/**
	 * 保存文件到数据库
	 * @param fileUD
	 * @return 返回保存id
	 */
	public Serializable save(FilePersistence fileUD);
	/**
	 * 从数据库获取文件对象
	 * @author swh1984
	 * @return 返回文件对象
	 */
	public FilePersistence getFilePersistence(String fileId);
	/**
	 * 页面查询
	 * @param 分页查询
	 * @return 返回FilePersistence分页对象
	 */
	public IPage<FilePersistence> findFilePersistenceByPage(QueryPage queryPage);
	/**
	 * 根据id删除文件
	 * @param id
	 */
	public void removeFilePersistence(String id);
}
