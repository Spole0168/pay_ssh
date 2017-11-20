package com.ibs.common.module.frameworkimpl.log.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.log.dao.IEntityInfoDao;
import com.ibs.common.module.frameworkimpl.log.domain.EntityInfo;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

/**
 * @author 
 * $Id: EntityInfoDaoImpl.java 28985 2011-02-24 08:10:35Z  $
 */
public class EntityInfoDaoImpl extends BaseEntityDao<EntityInfo> implements IEntityInfoDao {

	public List<EntityInfo> getLogEntityInfos() {
		logger.trace("entering dao...");
		
		StringBuffer hql = new StringBuffer("from EntityInfo t where t.hisEntityClass is not null");
		
		List<EntityInfo> list = super.findByHql(hql.toString(), null, null);
		
		return list;
	}

	public List<EntityInfo> getAppEntityInfos(String appId) {
		logger.trace("entering dao...");
		
		StringBuffer hql = new StringBuffer("from EntityInfo t where t.appId = ?");
		
		List<Object> args = new ArrayList<Object>();
		args.add(appId);
		
		return super.findByHql(hql.toString(), args, null);
	}
}
