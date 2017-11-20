/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.topup.biz;

import java.util.ArrayList;

import com.ibs.core.module.topup.dao.ITopupTransTraceDao;
import com.ibs.core.module.topup.domain.TopupTransTrace;
import com.ibs.core.module.topup.dto.TopupTransTraceDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ITopupTransTraceBiz {

//	public IPage<TopupTransTrace> findTopupTransTraceByPage(QueryPage queryPage);
	public IPage<TopupTransTraceDto> findTopupTransTraceByPage(QueryPage queryPage);

	public IPage<TopupTransTraceDto> findCnlTransBySql(QueryPage queryPage, TopupTransTraceDto conditionDto);

	public TopupTransTrace getTopupTransTraceById(String id);
	
	public TopupTransTrace saveTopupTransTrace(TopupTransTrace object);
	
	public TopupTransTrace updateTopupTransTrace(TopupTransTrace object);
	
	public void exportTopupTransTrace(ExportSetting exportSetting, TopupTransTraceDto conditionDto);
	
	public void checks(ArrayList<TopupTransTrace> topupTransTrace);
	
	public void sendMessage(ArrayList<TopupTransTrace> topupTransTraceList);
	
	public void handleOrReview(TopupTransTrace topupTransTrace);
	
	public String getCnlSysNameByCnlCnlCode(String cnlCnlCode);
	
	public void updateAllTopupTransTraceBankInfo();
	
}
