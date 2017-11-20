package com.ibs.common.module.frameworkimpl.file.dao.impl;

import java.io.Serializable;

import com.ibs.common.module.frameworkimpl.file.dao.IFilePersistenceDao;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class FilePersistenceDaoImpl extends BaseEntityDao<FilePersistence> implements IFilePersistenceDao {

	public Serializable save(FilePersistence fileUD) {
		return super.save(fileUD);
	}
	
	/**
	 * 获取文件下载根目录
	 * @return
	 
	private static String FILE_PATH ;
	
	public String findFilePath(){
		if("".equals(FILE_PATH) || null == FILE_PATH){
			FILE_PATH = (String)this.getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					SQLQuery sqlQuery = session.createSQLQuery("SELECT T.PATH FROM T_FILE_PATH T");
					return  sqlQuery.uniqueResult();
				}
			});
		}
		return FILE_PATH;
	}*/
	
	/**
	 * @author swh1984
	 * @return 返回文件对象
	 */
	public FilePersistence getFilePersistence(String fileId) {
		return super.load(fileId);
	}
	
	/**
	 * @param 分页查询
	 * @return 返回FilePersistence分页对象
	 */
	public IPage<FilePersistence> findFilePersistenceByPage(QueryPage queryPage) {
		return super.findPageBy(queryPage);
	}
	
	public void removeFilePersistence(String id) {
		super.remove(id);
	}
}
