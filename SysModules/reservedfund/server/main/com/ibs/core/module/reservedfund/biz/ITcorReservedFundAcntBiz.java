/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.reservedfund.biz;

import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_RESERVED_FUND_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ITcorReservedFundAcntBiz {

	public IPage<CorReservedFundAcnt> findCorReservedFundAcntByPage(QueryPage queryPage);

	public CorReservedFundAcnt getCorReservedFundAcntById(String id);
	
	public CorReservedFundAcnt saveCorReservedFundAcnt(CorReservedFundAcnt object);
	
	public CorReservedFundAcnt updateCorReservedFundAcnt(CorReservedFundAcnt object);
	
	public void exportCorReservedFundAcnt(ExportSetting exportSetting);

	//银行备付金管理 徐伟龙
	public IPage<CorReservedFundAcnt> findByBankNum(QueryPage queryPage,String bankInnerCode);
	
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum,String id);
	
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum);
	
	public void deleteById(String id);
	
}
