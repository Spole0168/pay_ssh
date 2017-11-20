package com.ibs.core.module.account.service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlTransServiceAccountParam;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceService;
import com.ibs.core.module.cnlcust.dto.CnlCustPersonalDto;



/**
 * 交易记录相关接口
 * @author duxiaolin
 * @version 2016年7月12日19:25:44
 */
public interface ICnlTransService {
	/**
	 * 充值申请单
	 * @param cnlCode 渠道编码
	 * @param intfCode 接口编码
	 * @param msgCode 报文编码
	 * @param msgTime 报文时间
	 * @return reqInnerNum 申请单流水号
	 * @throws Exception
	 */
	public String pay(String cnlCode,String intfCode,String msgId,Timestamp msgTime,String returnUrl) throws Exception;

	/**
	 * 提现申请单
	 * @param cnlCode 渠道编码
	 * @param intfCode 接口编码
	 * @param msgCode 报文编码
	 * @param msgTime 报文时间
	 * @return reqInnerNum 申请单流水号
	 * @throws Exception
	 */
	public String getMoney(String cnlCode,String intfCode,String msgId,Timestamp msgTime,String returnUrl) throws Exception;

	/**
	 * 清结算申请单
	 * @param cnlCode 渠道编码
	 * @param intfCode 接口编码
	 * @param reqBatch 申请单批次号
	 * @param reqTotalBatch 申请单总批次
	 * @param reqCnt 本批次记录数量
	 * @param reqTotalCnt 所有批次记录总数
	 * @param msgCode 报文编码
	 * @param msgTime 报文时间
	 * @param stlNum 清算号
	 * @return  reqInnerNum 申请单流水号
	 * @throws Exception
	 */
	public String  clearing(String cnlCode,String intfCode,String reqBatch,String reqTotalBatch,String reqCnt,String reqTotalCnt,String msgId,Timestamp msgTime,String stlNum,String returnUrl) throws Exception;
	/**
	 * 个人开户
	 * @param cnlCode 渠道编码
	 * @param intfCode 接口编码
	 * @param msgCode 报文编码
	 * @param msgTime 报文时间
	 * @return reqInnerNum 申请单流水号
	 */
	public String account(String cnlCode,String intfCode,String msgId,Timestamp msgTime,String returnUrl)throws Exception;
	/**
	 * 账户修改
	 * @param cnlCode 渠道编码
	 * @param intfCode 接口编码
	 * @param msgCode 报文编码
	 * @param msgTime 报文时间
	 * @return reqInnerNum 申请单流水号
	 */
	public String updateAccount(String cnlCode,String intfCode,String msgId,Timestamp msgTime,String returnUrl)throws Exception;



	/**
	 * 注销
	 * @param cnlCode 渠道编码
	 * @param intfCode 接口编码
	 * @param msgCode 报文编码
	 * @param msgTime 报文时间
	 * @return reqInnerNum 申请单流水号
	 */
	public String cancellation(String cnlCode,String intfCode,String msgId,Timestamp msgTime ,String returnUrl)throws Exception;

	/**
	 * 创建企业客户申请单
	 * @return
	 */
	public String addEnterpriseAccount(String cnlCode,String intfCode,String msgId,Timestamp msgTime ,String returnUrl)throws Exception;

	/**
	 * 修改企业客户申请单
	 * @return
	 */
	public String modifyEnterpriseAccount(String cnlCode,String intfCode,String msgId,Timestamp msgTime ,String returnUrl)throws Exception;


	/**
	 * 增加个人开户跟踪记录
	 */
	public void createAccount(CnlTransServiceAccountParam param) throws Exception;

	/**
	 * 增加个人更新跟踪记录
	 */
	public void updateAccount(CnlTransServiceAccountParam param) throws Exception;

	/**
	 * 创建企业客户待处理跟踪
	 */
	public BigDecimal addEnterpriseAccountTrace(CnlTransServiceAccountParam param)throws Exception;

	public void addModifyCustTrace(String reqInnerNum,String cnlCnlCode,String cnlCustCode,String phonenum,String address,Date msgDate,String msgId)throws Exception;

	/**
	 * 修改企业客户待处理跟踪
	 */
	public void addModifyEnterpriseAccountTrace(String reqInnerNum,String cnlCnlCode,String cnlCustCode,String phonenum,String address,Date msgDate)throws Exception;

	/**
	 * 充值申请单跟踪
	 * @param reqInnerNum 申请单流水号
	 * @param custCode 客户唯一编码
	 * @param orderNum 订单号
	 * @param cnlCustCode 渠道客户编码
	 * @param transAmount 交易金额
	 * @param transDate 交易日期
	 * @param transComments 交易说明
	 * @param termialType 终端类型
	 * @throws Exception 
	 */
	public void payTrace(String reqInnerNum,String msgId,String orderNum,String cnlCustCode,String transAmount,Timestamp transDate,String transComments,String termialType,String cnlCnlCode) throws Exception;
	/**
	 * @param reqInnerNum 申请单流水号
	 * @param custCode 客户唯一编码
	 * @param orderNum 订单号
	 * @param cnlCustCode 渠道客户编码
	 * @param transAmount 交易金额
	 * @param transDate 交易日期
	 * @param transComments 交易说明
	 * @param termialType 终端类型
	 * @throws Exception
	 */	
	public void getMoneyTrace(String reqInnerNum,String msgId,String cnlCustCode,String transAmount,Timestamp transDate,String transComments,String termialType,String cnlCnlCode) throws Exception;
	/**
	 * 清结算申请单跟踪
	 * @param transTraceServices 数据集
	 * @param reqInnerNum 流水号
	 * @param StlNum 清算号
	 * @param reqNum 申请单号
	 * @param reqBatch 申请单批次号
	 * @throws Exception 
	 */
	public void clearingTrace(List<CnlTransTraceService> transTraceServices,String reqInnerNum,String StlNum,String reqNum, String reqBatch,String cnlCnlCode) throws Exception;

	//待处理进流水
	public void moveReqTransToCnlTrans(String  cnlTransTraceId)throws Exception;

	//流水进明细
	public void moveCnlTransToCnlCustAcntDtl(String cnlTransId)throws Exception;

	//校验重复充值
	public boolean checkCnlOrderNumExists(String cnlOrderNum); 

	//查询目前批次
	public Long reqTrans(String stlNum);

	//更新银行返回信息
	public void updateTransTrace(String reqInnerNum,String BankNumCode);
	/**
	 * 对账查询（充值提现）接口
	 * @param cnlCustomerCode 渠道客户编码
	 * @param TransType 交易类型 传报文过来的信息 我做判断
	 * @return 返回查询结果集
	 */
	public Map records(String cnlCustomerCode,int TransType,String pageSize,String pageIndex);

	/**
	 * 余额查询
	 * @param cnlCnlCode 渠道编码
	 * @param cnlCustCode 客户编码 没有传空
	 * @return
	 */
	public Map findBalance(String cnlCnlCode,String cnlCustCode,String pageSize,String pageIndex);

	/**
	 * 查询是否接收到报文
	 * @param msgId 申请单号
	 * @return true接收到false没有收到
	 */
	public boolean responseStatus(String msgId);

	/**
	 * 清算结果查询
	 * @param stlNum
	 * @return
	 */
	public Map clearingResult(String stlNum,String pageSize,String pageIndex) throws Exception;

	/**
	 * 验证企业银行
	 * @return
	 */
	public BigDecimal bankVld(CnlTransServiceAccountParam param);

	/**
	 * 创建企业开户信息
	 */
	public void createData(String cnlCustCode,String cnlCnlCode,String infoCode,String reqNum)throws Exception;

	/**
	 * 
	 * @param orderNum
	 * @param BankNumCode
	 */
	public void updateTransTraceByOrderNum(String orderNum, String BankNumCode);

	/**
	 * 
	 * @param reqNum
	 * @param BankNumCode
	 * @param status
	 */
	public void updateTransTraceByReqNum(String reqNum, String BankNumCode, String status);


	/**
	 * 企业开户申请重复验证
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return false是已经存在申请单
	 */
	public boolean cheackEnterpriseAccountReq(String cnlCustCode,String cnlCnlCode);


	void updateReqAndTraceStatus(String innerNum);


	/**
	 * 是否有重复记录
	 * @param name 企业名称
	 * @param num 营业执照编号
	 * @return true为有记录存在
	 */
	public boolean cheackCustTrace(String name,String num);

	/**
	 * 验证渠道会员是否注销
	 * @param cnlCnlCode 渠道编号
	 * @param cnlCustCode 渠道会员编号
	 * @return true证明有账户信息
	 */
	public boolean cheackAccount(String cnlCnlCode,String cnlCustCode);

	/**
	 * 根据订单号获取返回地址
	 * @param msgId 申请单号
	 * @return 回调地址
	 */
	public String getReturnUrl(String msgId);

	/**
	 * 获取清算总批次
	 * @param stlNum 清算号
	 * @return 清算总批次
	 */
	public String getClearingTotalBatch(String stlNum);

	/**
	 * 获取清算批次
	 * @param stlNum 清算号
	 * @return 所有清算批次
	 */
	public List<String> getClearingBatch(String stlNum);


}
