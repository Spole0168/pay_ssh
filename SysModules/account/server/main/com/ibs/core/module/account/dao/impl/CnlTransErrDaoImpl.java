/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import com.ibs.core.module.account.dao.ICnlTransErrDao;
import com.ibs.core.module.account.domain.CnlTransErr;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_ERR
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransErrDaoImpl extends BaseEntityDao<CnlTransErr> implements
		ICnlTransErrDao {

	public IPage<CnlTransErr> findCnlTransErrByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransErrDaoImpl.findCnlTransErrByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlTransErr load(String id) {
		logger.info("entering action::CnlTransErrDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlTransErr cnlTransErr) {
		logger.info("entering action::CnlTransErrDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlTransErr);
	}

}
