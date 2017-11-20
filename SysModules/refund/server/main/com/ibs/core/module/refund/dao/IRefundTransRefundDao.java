/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.refund.dao;

import com.ibs.core.module.refund.domain.RefundTransRefund;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_REFUND
 * @modify		: your comments goes here (author,date,reason).
 */
public interface IRefundTransRefundDao {

	public IPage<RefundTransRefund> findRefundTransRefundByPage(QueryPage queryPage);
	
	public void saveOrUpdate(RefundTransRefund cnlTransRefund);

	public RefundTransRefund load(String id);
	
}
