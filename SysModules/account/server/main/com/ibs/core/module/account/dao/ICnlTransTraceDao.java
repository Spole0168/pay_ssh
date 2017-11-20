/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.account.domain.CnlTransServiceClearing;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransTraceDao {

	public IPage<CnlTransTrace> findCnlTransTraceByPageTransFailed(QueryPage queryPage);
	
	public IPage<CnlTransTraceDto> findCnlTransTraceByPage(QueryPage queryPage,String msg);
	
	public void saveOrUpdate(CnlTransTrace cnlTransTrace);

	public CnlTransTrace load(String id);

	public CnlTransTraceDto getCnlTransTraceDtoById(String id);
	
	public void saveBatch(List<CnlTransTrace> cnlTransTraces);
	
	public BigDecimal findSumTransAmount(String cnlCnlCode, String stlNum, String TransDc);

	public List<CnlTransTrace> findDcBalAnce(String stlNum);

	public void updateStatus(String cnlCustCode,String stlNum,String handleStatus,String reqDataErr,String errMsg,Date updateTime,String updator);

	public void deleTransTrace(String stlNum);
	
	public List<CnlTransTrace> findHandler(String transType);
		
	public CnlTransTrace findByReqInnerNum(String reqInnerNum);
	
	public CnlTransTrace findByCnlOrderNum(String OrderNum);

	public CnlTransTrace findByReqNum(String reqNum);

	public void deleteBatch(List<CnlTransTrace> list);

	public int countStlNum(String stlNum);

	public List<CnlTransServiceClearing> getClearing(String stlNum,String pageSize,String pageIndex);
	
	public int countList(String stlNum);
	
	public boolean updateBatchTransTrace(List<CnlTransTrace> list);

	public void updateTransTrace(String stlNum, String handleStatus, String reqDataErr , String errMsg , Date updateTime,
			String updator);
	
	public void update(CnlTransTrace cnlTransTrace);
	
	public void deleteByEntity(CnlTransTrace cnlTransTrace);

	/**   根据reqNum更新 交易状态
	 * @param reqNum
	 * @param object
	 * @param transStatusFail
	 * @param errCode 错误code
	 * @param errMsg 错误消息
	 */
	public void updateTransTraceByReqNum(String reqNum, String traNo, String transStatusFail, String errCode, String errMsg);

	/** 根据渠道订单号 更新银行接入信息
	 * @param orderNum
	 * @param bankNumCode
	 */
	public void updateTransTraceByOrderNum(String orderNum, String bankNumCode);

	/**  根据渠道客户编号和 渠道编号 查询一条记录
	 * @param cnlCustCode
	 * @param cnlCode
	 * @return
	 */
	public CnlTransTrace findByCustCodeAndCnlCode(String cnlCustCode, String cnlCode);
}
