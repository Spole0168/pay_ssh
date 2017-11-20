/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.interfaces.dao.impl;

import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.interfaces.dao.ICnlReqTransInfDataInqueryDao;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlReqTransInfDataInqueryDaoImpl extends BaseEntityDao<CnlReqTrans> implements
		ICnlReqTransInfDataInqueryDao {

	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage) {
		logger.info("entering action::CnlReqTransDaoImpl.findCnlReqTransByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlReqTrans load(String id) {
		logger.info("entering action::CnlReqTransDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlReqTrans cnlReqTrans) {
		logger.info("entering action::CnlReqTransDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlReqTrans);
	}

}
