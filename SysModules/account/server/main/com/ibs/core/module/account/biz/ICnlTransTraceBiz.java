/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;

import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransTraceBiz {

	public IPage<CnlTransTrace> findCnlTransTraceByPageTransFailed(QueryPage queryPage);
	
	public IPage<CnlTransTraceDto> findCnlTransTraceByPage(QueryPage queryPage,String msg);

	public CnlTransTrace getCnlTransTraceById(String id);
	
	public CnlTransTrace saveCnlTransTrace(CnlTransTrace object);
	
	public CnlTransTrace updateCnlTransTrace(CnlTransTrace object);
	
	public void exportCnlTransTrace(ExportSetting exportSetting);

	public CnlTransTraceDto getCnlTransTraceDtoById(String id);

}
