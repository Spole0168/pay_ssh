/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.topup.dao.impl;

import com.ibs.core.module.topup.dao.ITopupTransTraceOptDao;
import com.ibs.core.module.topup.domain.TopupTransTraceOpt;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE_OPT
 * @modify		: your comments goes here (author,date,reason).
 */
public class TopupTransTraceOptDaoImpl extends BaseEntityDao<TopupTransTraceOpt> implements
		ITopupTransTraceOptDao {

	public IPage<TopupTransTraceOpt> findTopupTransTraceOptByPage(QueryPage queryPage) {
		logger.info("entering action::TopupTransTraceOptDaoImpl.findTopupTransTraceOptByPage()...");
		return super.findPageBy(queryPage);
	}

	public TopupTransTraceOpt load(String id) {
		logger.info("entering action::TopupTransTraceOptDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(TopupTransTraceOpt topupTransTraceOpt) {
		logger.info("entering action::TopupTransTraceOptDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(topupTransTraceOpt);
	}

}
