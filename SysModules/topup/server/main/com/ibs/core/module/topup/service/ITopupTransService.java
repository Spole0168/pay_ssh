package com.ibs.core.module.topup.service;

import java.util.Map;

public interface ITopupTransService {

	/**
	 * 检索T_CNL_TRANS_TRACE、T_CNL_CNL_CFG、T_COR_CUST的数据
	 * 
	 * @param reqNum			渠道申请单号
	 * @param reqInnerNum		系统申请单流水号
	 * @param cnlCnlCode		渠道编码
	 * @param cnlSysName		渠道名称
	 * @param cnlCustCode		客户编号
	 * @param localName			客户名称
	 * @param transStartTime	交易开始时间
	 * @param transEndTime		交易结束时间
	 * @param handleStatus		入账处理状态
	 * @param pageSize			每页条数
	 * @param pageIndex			页数索引
	 * @return 					结果集
	 * @throws Exception		抛出验证错误
	 */
	public Map selectTransTraceAndCnlCfgAndCorCust(
			  String	reqNum
			, String	reqInnerNum
			, String	cnlCnlCode
			, String	cnlSysName
			, String	cnlCustCode
			, String	localName
			, long		transStartTime
			, long		transEndTime
			, String	handleStatus
			, String	pageSize
			, String	pageIndex
	) throws Exception; 
}
