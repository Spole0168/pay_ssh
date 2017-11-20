/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.topup.dao;

import java.util.List;

import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.core.module.topup.domain.TopupTransTrace;
import com.ibs.core.module.topup.dto.TopupTransTraceDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ITopupTransTraceDao {

	public IPage<TopupTransTraceDto> findTopupTransTraceByPage(QueryPage queryPage, TopupTransTraceDto conditionDto);
	
	public IPage<TopupTransTraceDto> findCnlTransBySql(QueryPage queryPage, TopupTransTraceDto topupTransTraceConditionDto);
	
	public void saveOrUpdate(TopupTransTrace topupTransTrace);

	public TopupTransTrace load(String id);
	
	public void checks(TopupTransTrace topupTransTraceDao);
	
	public List<TopupTransTrace> getTopupTransTraceList();
	
	public CorReservedFundAcnt getCorReservedFundAcntByBankCode(String bankCreditCode);

}
