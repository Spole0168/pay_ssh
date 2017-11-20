/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.topup.biz;

import com.ibs.core.module.topup.domain.TopupTransTraceOpt;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE_OPT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ITopupTransTraceOptBiz {

	public IPage<TopupTransTraceOpt> findTopupTransTraceOptByPage(QueryPage queryPage);

	public TopupTransTraceOpt getTopupTransTraceOptById(String id);
	
	public TopupTransTraceOpt saveTopupTransTraceOpt(TopupTransTraceOpt object);
	
	public TopupTransTraceOpt updateTopupTransTraceOpt(TopupTransTraceOpt object);
	
	public void exportTopupTransTraceOpt(ExportSetting exportSetting);

}
