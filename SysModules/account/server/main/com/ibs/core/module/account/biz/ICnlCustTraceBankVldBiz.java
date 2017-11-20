/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;


import com.ibs.core.module.account.domain.CnlCustTraceBankVld;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE_BANK_VLD
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustTraceBankVldBiz {

	public IPage<CnlCustTraceBankVld> findCnlCustTraceBankVldByPage(QueryPage queryPage);

	public CnlCustTraceBankVld getCnlCustTraceBankVldById(String id);
	
	public CnlCustTraceBankVld saveCnlCustTraceBankVld(CnlCustTraceBankVld object);
	
	public CnlCustTraceBankVld updateCnlCustTraceBankVld(CnlCustTraceBankVld object);
	
	public void exportCnlCustTraceBankVld(ExportSetting exportSetting);

}
