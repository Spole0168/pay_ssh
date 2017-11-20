/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import java.util.List;

import com.ibs.core.module.account.domain.CnlCustTraceBankVld;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE_BANK_VLD
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustTraceBankVldDao {

	public IPage<CnlCustTraceBankVld> findCnlCustTraceBankVldByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlCustTraceBankVld cnlCustTraceBankVld);

	public CnlCustTraceBankVld load(String id);
	
	public List<CnlCustTraceBankVld> findCnlCustTranceBankVldOnHandle(String transStatus);

	/**
	 * 根据申请单流水号获取一条数据
	 * @param reqInnerNum 申请单流水号
	 * @return
	 */
	public CnlCustTraceBankVld findByReqInnerNum(String reqInnerNum);
}
