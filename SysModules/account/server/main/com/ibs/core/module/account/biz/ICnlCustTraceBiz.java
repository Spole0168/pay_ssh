/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;


import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustTraceBiz {

	public IPage<CnlCustTrace> findCnlCustTraceByPage(QueryPage queryPage);

	public CnlCustTrace getCnlCustTraceById(String id);
	
	public CnlCustTrace saveCnlCustTrace(CnlCustTrace object);
	
	public CnlCustTrace updateCnlCustTrace(CnlCustTrace object);
	
	public void exportCnlCustTrace(ExportSetting exportSetting);

}
