package com.ibs.common.module.frameworkimpl.log.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.log.dao.IBizInfoDao;
import com.ibs.common.module.frameworkimpl.log.domain.BizInfo;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

/**
 * @author 
 * $Id: BizInfoDaoImpl.java 28985 2011-02-24 08:10:35Z  $
 */
public class BizInfoDaoImpl extends BaseEntityDao<BizInfo> implements IBizInfoDao {

	public List<BizInfo> getAllBizInfos() {
		logger.trace("entering dao...");
		
		List<BizInfo> list = super.findAll();
		return list;
	}

	public BizInfo getBizInfo(String bizClass, String bizMethod) {
		
		logger.trace("entering dao...");
		StringBuffer hql = new StringBuffer("from BizInfo t where t.bizClass = ? and t.bizMethod = ?");
		
		List<Object> args = new ArrayList<Object>();
		args.add(bizClass);
		args.add(bizMethod);
		List<BizInfo> list = super.findByHql(hql.toString(), args, null);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public List<BizInfo> getAppBizInfos(String appId) {
		logger.trace("entering dao...");
		
		StringBuffer hql = new StringBuffer("from BizInfo t where t.appId = ? and t.status = 1 order by t.sort");
		
		List<Object> args = new ArrayList<Object>();
		args.add(appId);
		
		return super.findByHql(hql.toString(), args, null);
	}
}
