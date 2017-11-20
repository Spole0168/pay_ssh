/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.util.List;

import com.ibs.core.module.account.dao.ICnlTransTraceHisDao;
import com.ibs.core.module.account.domain.CnlTransTraceHis;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransTraceHisDaoImpl extends BaseEntityDao<CnlTransTraceHis> implements
		ICnlTransTraceHisDao {

	public IPage<CnlTransTraceHis> findCnlTransTraceHisByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransTraceHisDaoImpl.findCnlTransTraceHisByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlTransTraceHis load(String id) {
		logger.info("entering action::CnlTransTraceHisDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlTransTraceHis cnlTransTraceHis) {
		logger.info("entering action::CnlTransTraceHisDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlTransTraceHis);
	}

	@Override
	public void save(List<CnlTransTraceHis> his) {
		logger.debug("entering action::CnlTransTraceHisDaoImpl.save()...");
		super.saveBatch(his);
	}


}
