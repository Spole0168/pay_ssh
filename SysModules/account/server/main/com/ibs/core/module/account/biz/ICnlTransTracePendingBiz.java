/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;



import javax.jms.Message;

import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransTracePendingBiz {

	public IPage<CnlTransTrace> findCnlTransTraceByPage(QueryPage queryPage);

	public CnlTransTrace getCnlTransTraceById(String id);
	
	public CnlTransTrace saveCnlTransTrace(CnlTransTrace object);
	
	public void exportCnlTransTrace(ExportSetting exportSetting);
	
	public String findStatus(String bankInnerCode);

	void verifyCnlTransTrace(CnlTransTrace cnlTransTrace);
	
	public void updateCnlTransTrace(CnlTransTrace cnlTransTrace);
	
	public CnlTransTrace findDicCnlTransTraceById(String id);
	
	public void updateStatus(CnlTransTrace cnlTransTrace);
	
	public CorBankIntf findByCode(String code);

}
