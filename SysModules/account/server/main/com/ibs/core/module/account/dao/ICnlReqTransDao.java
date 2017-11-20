/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.cnltrans.domain.CnlReqTransHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlReqTransDao {

	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage);

	public void saveOrUpdate(CnlReqTrans cnlReqTrans);

	public CnlReqTrans load(String id);

	/**
	 * 多表查询
	 * 
	 * @param queryPage
	 * @param conditon
	 */
	public IPage<CnlReqTransDto> findReqTranBizByMoreTable(QueryPage queryPage, String conditon);

	public List<CnlReqTrans> findCnlReqTransByOneCondition(String columName, String condition);

	public void updateStatus(String stlNum, String handleStatus, String reqDataErr ,String errMsg ,Date updateTime, String updator);

	public List<CnlReqTrans> findTrans(String stlNum);

	public CnlReqTrans maxBatch(String stlNum);

	public void updateBatch(List<CnlReqTrans> list);

	/**
	 * 修改申请单流水
	 * 
	 * @author jicheng
	 * @param cnlReqTrans
	 */
	public void updateReqTrans(CnlReqTrans cnlReqTrans);

	public CnlReqTrans findByInnerNum(String innerNum);

	public CnlReqTrans getReq(String stlNum);

	/**
	 * @author chenyanyan
	 * @param reqInnerNum
	 * @param reqStatus
	 * @return
	 */
	public int updateReqStatus(String reqInnerNum, String reqStatus);

	public boolean updateReqestStatus(String reqInnerNum, String reqStatus);

	public CnlReqTrans findByReqInnerNum(String reqInnerNum);

	/**
	 * 用于验证企业开户是否有重复的申请单
	 * 
	 * @author duxiaolin
	 * @return
	 */
	public boolean findByCustCodeAndCnlCode(String custCode, String cnlCode);

	/**
	 * 根据reqInnerNum更新 交易状态
	 * 
	 * @param reqInnerNum
	 * @param object
	 * @param reqStatus
	 * @param errCode
	 * @param errMsg
	 */
	public void updateReqTransByReqInnerNum(String reqInnerNum, String reqStatus, String errCode, String errMsg);

	public List<CnlReqTrans> findCnlReqTrans();

	public void saveCnlReqTransHis(Collection<CnlReqTransHis> cnlReqTransHisList);

	public boolean deleteCnlReqTrans();

	public CnlReqTransDto findTransDetail(String reqInnerNum);

	public String getReturnUrl(String msgId);

	public IPage<CnlReqTransDto> findBalanceDetail(QueryPage queryPage,String reqInnerNum);
	
	public String getClearingTotalBatch(String stlNum);
	
	public List<String> getClearingBatch(String stlNum);

	public CnlReqTransDto findPersonAboutCustTrace(String reqInnerNum);

	public CnlReqTransDto findCompanyAboutCustTrace(String reqInnerNum);
	
}
