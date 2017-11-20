package com.ibs.common.module.frameworkimpl.log.dao.impl;

import com.ibs.common.module.frameworkimpl.log.dao.IBizLogDao;
import com.ibs.common.module.frameworkimpl.log.domain.BizLog;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

/**
 * @author 
 * $Id: BizLogDaoImpl.java 7767 2010-10-06 12:54:11Z  $
 */
public class BizLogDaoImpl extends BaseEntityDao<BizLog> implements IBizLogDao {

	public void saveBizLog(BizLog bizLog) {
		super.save(bizLog);
	}

}
