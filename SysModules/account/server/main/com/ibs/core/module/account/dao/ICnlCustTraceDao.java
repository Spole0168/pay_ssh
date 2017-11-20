/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;


import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustTraceDao {

	public IPage<CnlCustTrace> findCnlCustTraceByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlCustTrace cnlCustTrace);

	public CnlCustTrace load(String id);
	
	public CnlCustTrace findByCustCode(String custCode);
	
	public int updateReqStatus(String reqInnerNum,String reqStatus, String errMsg);

	public CnlCustTrace findFirstTrace(String cnlCustCode, String reqTraceType);

	public CnlCustTrace findByInnerNum(String innerNum);
	
	public boolean findBooleanByCnlCustCodeAndCnlCnlCode(String cnlCustCode,String cnlCnlCode);
	
	public boolean findBooleanByNameAndCretNum(String name,String cretNum);

	/**
	 * 根据申请单流水号获取一条数据
	 * @param reqInnerNum 申请单流水号
	 * @return
	 */
	public CnlCustTrace findByReqInnerNum(String reqInnerNum);
	
	
	public CnlCustTrace findByCnlCustCode(String cnlCustCode,String cnlCnlcode);
}
