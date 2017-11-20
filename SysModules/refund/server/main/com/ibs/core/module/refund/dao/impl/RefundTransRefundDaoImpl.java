/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.refund.dao.impl;

import com.ibs.core.module.refund.dao.IRefundTransRefundDao;
import com.ibs.core.module.refund.domain.RefundTransRefund;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_REFUND
 * @modify		: your comments goes here (author,date,reason).
 */
public class RefundTransRefundDaoImpl extends BaseEntityDao<RefundTransRefund> implements
		IRefundTransRefundDao {

	public IPage<RefundTransRefund> findRefundTransRefundByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransRefundDaoImpl.findCnlTransRefundByPage()...");
		return super.findPageBy(queryPage);
	}

	public RefundTransRefund load(String id) {
		logger.info("entering action::CnlTransRefundDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(RefundTransRefund cnlTransRefund) {
		logger.info("entering action::CnlTransRefundDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlTransRefund);
	}

}
