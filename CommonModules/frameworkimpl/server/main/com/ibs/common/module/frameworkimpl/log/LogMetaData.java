package com.ibs.common.module.frameworkimpl.log;

import java.util.HashMap;
import java.util.List;

import com.ibs.common.module.frameworkimpl.log.dao.IBizInfoDao;
import com.ibs.common.module.frameworkimpl.log.dao.IEntityInfoDao;
import com.ibs.common.module.frameworkimpl.log.domain.BizInfo;
import com.ibs.common.module.frameworkimpl.log.domain.EntityInfo;


/**
 * 获取日志所需的基础数据
 * @author 
 * $Id: LogMetaData.java 31169 2011-03-11 01:35:33Z  $
 */
public class LogMetaData {

	private IEntityInfoDao entityInfoDao;
	private IBizInfoDao bizInfoDao;
	
	private HashMap<String, EntityInfo> entityInfoMap;
	private HashMap<String, BizInfo> bizInfoMap;
	
	/**
	 * Hibernate post event 不能做任何数据库查询操作
	 * @return
	 */
	public HashMap<String, EntityInfo> getEntityInfoMap() {
		return entityInfoMap;
	}
	
	/**
	 * 初始化操作
	 * @return
	 */
	public HashMap<String, BizInfo> getBizInfoMap() {
		if (bizInfoMap == null || entityInfoMap == null) {
			init();
		}
		
		return bizInfoMap;
	}
	
	private void init() {
		if (entityInfoMap == null) {
			List<EntityInfo> list = entityInfoDao.getLogEntityInfos();
			entityInfoMap = new HashMap<String, EntityInfo>(list == null ? 0 : list.size());
			for (EntityInfo entityInfo : list) {
				entityInfoMap.put(entityInfo.getEntityClass(), entityInfo);
			}
		}
		
		if (bizInfoMap == null) {
			List<BizInfo> list = bizInfoDao.getAllBizInfos();
			bizInfoMap = new HashMap<String, BizInfo>(list == null ? 0 : list.size());
			for (BizInfo bizInfo : list) {
				bizInfoMap.put(bizInfo.getBizClass().concat(".").concat(bizInfo.getBizMethod()), bizInfo);
			}
		}
	}

	public IEntityInfoDao getEntityInfoDao() {
		return entityInfoDao;
	}

	public void setEntityInfoDao(IEntityInfoDao entityInfoDao) {
		this.entityInfoDao = entityInfoDao;
	}

	public IBizInfoDao getBizInfoDao() {
		return bizInfoDao;
	}

	public void setBizInfoDao(IBizInfoDao bizInfoDao) {
		this.bizInfoDao = bizInfoDao;
	}

}
