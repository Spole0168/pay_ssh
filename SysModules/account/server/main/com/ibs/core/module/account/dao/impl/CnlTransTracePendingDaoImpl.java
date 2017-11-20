/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;


import com.ibs.core.module.account.dao.ICnlTransTracePendingDao;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransTracePendingDaoImpl extends BaseEntityDao<CnlTransTrace> implements
		ICnlTransTracePendingDao {

	public IPage<CnlTransTrace> findCnlTransTraceByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransTraceDaoImpl.findCnlTransTraceByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlTransTrace load(String id) {
		logger.info("entering action::CnlTransTraceDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlTransTrace);
	}
	
	public void updateCnlTransTrace(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceDaoImpl.updateCnlTransTrace()...");
       super.update(cnlTransTrace);
	

		
	}

}
