/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.refund.biz;

import com.ibs.core.module.refund.domain.RefundTransRefund;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_REFUND
 * @modify		: your comments goes here (author,date,reason).
 */
public interface IRefundTransRefundBiz {

	public IPage<RefundTransRefund> findRefundTransRefundByPage(QueryPage queryPage);

	public RefundTransRefund getRefundTransRefundById(String id);
	
	public RefundTransRefund saveRefundTransRefund(RefundTransRefund object);
	
	public RefundTransRefund updateRefundTransRefund(RefundTransRefund object);
	
	public void exportRefundTransRefund(ExportSetting exportSetting);

	void verifyTransRefund(RefundTransRefund refundTransRefund);

}
