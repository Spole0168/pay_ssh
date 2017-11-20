package com.ibs.core.module.account.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.core.module.account.domain.CnlCustTraceBankVld;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.CnlTransServiceAccountParam;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceService;
import com.ibs.core.module.cnlcust.dto.CnlCustPersonalDto;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.core.module.account.common.ConstantsCnlTrans;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/**
 * 交易处理服务
 * @author duxiaolin
 * @version 2016年7月14日16:10:30
 */
public class TransServiceUtil {

	protected static final Log logger = LogFactory.getLog(TransServiceUtil.class);

	/**
	 * 验证申请单参数是否为空
	 * @param i 验证类型 1充值，2提现，3清结算，4个人开户，5个人更新，6企业开户，7企业更新，8注销
	 * @param cnlCode
	 * @param intfCode
	 * @param reqBatch
	 * @param reqTotalBatch
	 * @param reqCnt
	 * @param reqTotalCnt
	 * @param msgCode
	 * @param msgTime
	 * @param stlNum
	 * @throws Exception 
	 */
	public static CnlReqTrans req(int type,String cnlCode, String intfCode, String reqBatch, String reqTotalBatch, String reqCnt,
			String reqTotalCnt, String msgId, Date msgTime, String stlNum,String returnUrl) throws Exception{
		CnlReqTrans cnlReqTrans = new CnlReqTrans();
		//校验渠道编码
		if(StringUtils.isEmpty(cnlCode) ){
			if(logger.isErrorEnabled())
				logger.error("渠道编码为空");
			throw new BizException("渠道编码为空");
		}
		cnlReqTrans.setCnlCnlCode(cnlCode);
		//校验接口编码
		if(StringUtils.isEmpty(intfCode)){
			if(logger.isErrorEnabled())
				logger.error("接口编码为空");
			throw new BizException("接口编码为空");
		}
		cnlReqTrans.setCnlIntfCode(intfCode);
		//校验报文编码
		if(StringUtils.isEmpty(msgId)){
			if(logger.isErrorEnabled())
				logger.error("报文编码为空");
			throw new BizException("报文编码为空");
		}
		cnlReqTrans.setReqNum(msgId);//申请单号
		cnlReqTrans.setMsgCode(msgId);//报文编号
		//校验报文时间
		if(msgTime==null){
			if(logger.isErrorEnabled())
				logger.error("报文时间为空");
			throw new BizException("报文时间为空");
		}
		cnlReqTrans.setMsgTime(msgTime);

		if(type == 3){//清结算
			//校验申请单批次号
			if(StringUtils.isEmpty(reqBatch)){
				if(logger.isErrorEnabled())
					logger.error("申请单批次号为空");
				throw new BizException("申请单批次号为空");
			}
			cnlReqTrans.setReqBatch(Long.parseLong(reqBatch));
			//校验申请单总批次
			if(StringUtils.isEmpty(reqTotalBatch)){
				if(logger.isErrorEnabled())
					logger.error("申请单总批次为空");
				throw new BizException("申请单总批次为空");
			}
			cnlReqTrans.setReqTotalBatch(Long.parseLong(reqTotalBatch));
			//校验本批次记录总数
			if(StringUtils.isEmpty(reqCnt)){
				if(logger.isErrorEnabled())
					logger.error("本批次记录数量为空");
				throw new BizException("本批次记录数量为空");
			}
			cnlReqTrans.setReqCnt(Long.parseLong(reqCnt));
			//校验所有批次记录总数
			if(StringUtils.isEmpty(reqTotalCnt)){
				if(logger.isErrorEnabled())
					logger.error("所有批次记录总数为空");
				throw new BizException("所有批次记录总数为空");
			}
			cnlReqTrans.setReqTotalCnt(Long.parseLong(reqTotalCnt));
			//校验清算号
			if(StringUtils.isEmpty(stlNum)){
				if(logger.isErrorEnabled())
					logger.error("清算号为空");
				throw new BizException("清算号为空");
			}
			cnlReqTrans.setStlNum(stlNum);
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_CLEARING_AND_SETTLEMENT);
		}


		//
		cnlReqTrans.setRecieveTime(new Date());
		//补全必填字段
		cnlReqTrans = createNewCnlReqTrans(cnlReqTrans);

		switch (type) {
		case 1://充值
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_TOP_UP);
			break;
		case 2://提现
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_WITHDRAW);
			break;
		case 4://个人开户
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_INDIVIDUAL_SIGN_UP);
			cnlReqTrans.setReqStatus(Constants.REQ_STATUS_SUCCESS);
			cnlReqTrans.setHandleTime(new Date());
			break;
		case 5://个人更新
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_UPDATE_INDIVIDUAL_CUSTOMER);
			cnlReqTrans.setReqStatus(Constants.REQ_STATUS_SUCCESS);
			cnlReqTrans.setHandleTime(new Date());
			break;
		case 6://企业开户
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_ENTERPRISE_SIGN_UP);
			break;
		case 7://企业更新
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_UPDATE_ENTERPRISE_CUSTOMER);
			cnlReqTrans.setReqStatus(Constants.REQ_STATUS_SUCCESS);
			cnlReqTrans.setHandleTime(new Date());
			break;
		case 8://注销
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_SIGN_OFF);
			cnlReqTrans.setReqStatus(Constants.REQ_STATUS_SUCCESS);
			cnlReqTrans.setHandleTime(new Date());
			break;
		case 9:
			cnlReqTrans.setReqType(Constants.REQ_TRACE_TYPE_OPEN_ACCOUNT);
			break;
		}
		if(type==6){
			cnlReqTrans.setReqStatus(Constants.REQ_STATUS_WAITING);
		}
		cnlReqTrans.setReturnUrl(returnUrl);
		return cnlReqTrans;
	}
	/**
	 * 验证跟踪单参数是否为空
	 */
	public static CnlTransTrace trace(int type,String reqInnerNum,String msgId ,String orderNum,String cnlCustCode,String transAmount,Date transDate,String transComments,String termialType,String cnlCnlCode){
		CnlTransTrace cnlTransTrace = new CnlTransTrace();
		//校验申请单流水号
		if(StringUtils.isEmpty(reqInnerNum)){
			if(logger.isErrorEnabled())
				logger.error("申请单流水号为空");
			throw new BizException("申请单流水号为空");
		}
		cnlTransTrace.setReqInnerNum(reqInnerNum);

		if(type==1){//充值
			//校验入金订单号
			if(StringUtils.isEmpty(orderNum)){
				if(logger.isErrorEnabled())
					logger.error("入金订单号为空");
				throw new BizException("入金订单号为空");
			}
			cnlTransTrace.setTransOrderNum(orderNum);
			//交易方向
			cnlTransTrace.setTransDc(Constants.TRANS_DC_INCOMING);
			//交易类型
			cnlTransTrace.setTransType(Constants.TRANS_TYPE_TOPUP);
		}else if(type==2){//提现
			//交易方向
			cnlTransTrace.setTransDc(Constants.TRANS_DC_OUTGOING);
			//交易方向
			cnlTransTrace.setTransType(Constants.TRANS_TYPE_WITHDRAW);
		}
		//校验渠道申请单号
		if(StringUtils.isEmpty(msgId)){
			if(logger.isErrorEnabled())
				logger.error("渠道订单号为空");
			throw new BizException("渠道订单号为空");
		}
		cnlTransTrace.setReqNum(msgId);
		//校验渠道客户编码
		if(StringUtils.isEmpty(cnlCustCode)){
			if(logger.isErrorEnabled())
				logger.error("渠道客户编码为空");
			throw new BizException("渠道客户编码为空");
		}
		cnlTransTrace.setCnlCustCode(cnlCustCode);
		//校验交易金额
		if(StringUtils.isEmpty(transAmount)){
			if(logger.isErrorEnabled())
				logger.error("交易金额为空");
			throw new BizException("交易金额为空");
		}	
		cnlTransTrace.setTransAmount(formatToBigDecimal(transAmount));
		//校验交易日期
		if(transDate==null){
			if(logger.isErrorEnabled())
				logger.error("交易日期为空");
			throw new BizException("交易日期为空");
		}
		cnlTransTrace.setTransTime(transDate);
		cnlTransTrace.setTransDate(DateUtils.convert(transDate.toString(),DateUtils.DATE_FORMAT));
		//校验终端类型
		if(StringUtils.isEmpty(termialType)){
			if(logger.isErrorEnabled())
				logger.error("终端类型为空");
			throw new BizException("终端类型为空");
		}
		cnlTransTrace.setTermialType(termialType);
		//校验渠道编码
		if(cnlCnlCode==null){
			if(logger.isErrorEnabled())
				logger.error("渠道编码为空");
			throw new BizException("渠道编码为空");
		}
		cnlTransTrace.setCnlCnlCode(cnlCnlCode);
		//币种默认人民币
		cnlTransTrace.setTransCurrency(Constants.CURRENCY_TYPE_50);
		//补齐必填字段
		cnlTransTrace = createNewCnlTransTrace(cnlTransTrace);

		if(null==cnlTransTrace.getBnakHandlePriority()||"".equals(cnlTransTrace.getBnakHandlePriority())){
			//默认普通
			cnlTransTrace.setBnakHandlePriority(Constants.BNAK_HANDLE_PRIORITY_REGULAR);
		}
		//默认未提交申请单
		cnlTransTrace.setHandleStatus(Constants.PENDING_HANDLE_STATUS_NOT_SUBMIT);
		return cnlTransTrace;
	}

	/**
	 * 清结算验证和必填赋值
	 * @param services
	 * @return
	 */
	public static List<CnlTransTrace> traceList(List<CnlTransTraceService> services,String reqInnerNum,String stlNum,String reqNum, String reqBatch,String cnlCnlCode){
		List<CnlTransTrace> traces = new ArrayList<CnlTransTrace>();
		for (CnlTransTraceService s : services) {
			//调用方法验证并补全字段
			CnlTransTrace trace = trace(3,reqInnerNum,reqNum,null,s.getCnlCustCode(),s.getTransAmount(),s.getTransDate(),null,"pc",cnlCnlCode);
			//校验最后金额
			if(StringUtils.isEmpty(s.getTransLatestAmount())){
				if(logger.isErrorEnabled())
					logger.error("最后金额为空");
				throw new BizException("最后金额为空");
			}
			trace.setTransLatestAmount(formatToBigDecimal(s.getTransLatestAmount()));
			//校验交易方向
			if(StringUtils.isEmpty(s.getTraceDc())){
				if(logger.isErrorEnabled())
					logger.error("交易方向为空");
				throw new BizException("交易方向为空");
			}
			trace.setTransDc(s.getTraceDc());
			//插入清结算类型
			trace.setTransType(Constants.TRANS_TYPE_CLEARING_SETTLEMENT);
			trace.setReqBatch(Long.parseLong(reqBatch));
			trace.setStlNum(stlNum);
			traces.add(trace);
		}		
		return traces;
	}


	/**
	 * CnlReqTrans必填数据填充
	 * @param c
	 * @return
	 */
	public static CnlReqTrans createNewCnlReqTrans(CnlReqTrans cnlReqTrans){
		//创建时间
		cnlReqTrans.setCreateTime(new Date());
		//状态
		cnlReqTrans.setReqStatus(Constants.REQ_STATUS_WAITING);
		//创建人
		cnlReqTrans.setCreator(Constants.SYSADMIN);
		//时区
		cnlReqTrans.setTimeZone(Constants.ZONE_GTM8);
		//是否有效
		cnlReqTrans.setIsValid(Constants.IS_VALID_VALID);
		return cnlReqTrans;
	}

	/**
	 * CnlTransTrace必填数据填充 
	 * @param c
	 * @return
	 */
	public static CnlTransTrace createNewCnlTransTrace(CnlTransTrace cnlTransTrace){
		//创建人
		cnlTransTrace.setCreator(Constants.SYSADMIN);
		//创建时间
		cnlTransTrace.setCreateTime(new Date());
		//交易状态
		cnlTransTrace.setTransStatus(Constants.TRANS_STATUS_UNPROCESSED);
		//是否有效
		cnlTransTrace.setIsValid(Constants.IS_VALID_VALID);

		return cnlTransTrace;
	}

	/**
	 * CnlReqTrans必填数据校验,有空值抛出异常
	 * @param c CnlReqTrans
	 */
	public static void cnlReqTransVerifcation(CnlReqTrans cnlReqTrans) throws Exception{
		//判断渠道编码是否为空
		if(StringUtils.isEmpty(cnlReqTrans.getCnlCnlCode())){
			if(logger.isErrorEnabled())
				logger.error("渠道编码为空");
			throw new BizException("渠道编码为空");
		}
		//接口编码
		if(StringUtils.isEmpty(cnlReqTrans.getCnlIntfCode())){
			if(logger.isErrorEnabled())
				logger.error("接口编码为空");
			throw new BizException("接口编码为空");
		}
		//判断申请单号是否为空
		if(StringUtils.isEmpty(cnlReqTrans.getReqNum())){
			if(logger.isErrorEnabled())
				logger.error("申请单号为空");
			throw new BizException("申请单号为空");
		}
		//申请单类别
		if(StringUtils.isEmpty(cnlReqTrans.getReqType())){
			if(logger.isErrorEnabled())
				logger.error("申请单类别为空");
			throw new BizException("申请单类别为空");
		}
		//清算号
		if(StringUtils.isEmpty(cnlReqTrans.getStlNum())){
			if(logger.isErrorEnabled())
				logger.error("清算号为空");
			throw new BizException("清算号为空");
		}

		//报文时间
		if(cnlReqTrans.getMsgTime()==null){
			if(logger.isErrorEnabled())
				logger.error("报文时间为空");
			throw new BizException("报文时间为空");
		}
		//接收时间
		if(cnlReqTrans.getRecieveTime()==null){
			if(logger.isErrorEnabled())
				logger.error("接收时间为空");
			throw new BizException("接收时间为空");
		}
		//报文编码
		if(StringUtils.isEmpty(cnlReqTrans.getMsgCode())){
			if(logger.isErrorEnabled())
				logger.error("报文编码为空");
			throw new BizException("报文编码为空");
		}
	}

	public static CnlCustAcntDtl createCnlCustAcntDtl(CnlCustAcntDtl dtl,CnlTrans trans){
		dtl.setAmount(trans.getTransAmount());
		dtl.setCurrency(trans.getTransCurrency());
		dtl.setBalance(trans.getTransLatestAmount());

		if(trans.getTransDc().equals(Constants.TRANS_DC_INCOMING)){
			dtl.setPreBalance(trans.getTransLatestAmount().add(trans.getTransAmount()));
		}else{
			dtl.setPreBalance(trans.getTransLatestAmount().subtract(trans.getTransAmount()));
		}
		dtl.setDirection(trans.getTransDc());
		dtl.setAcntDtlCode(StringUtils.getUUID());

		return dtl;
	}

	/**
	 * CnlTransTrace必填数据校验,有空值抛出异常
	 * @param c  CnlTransTrace对象
	 */
	public static void cnlTransTraceVerification(CnlTransTrace cnlTransTrace) throws Exception{
		//申请单流水号
		if(StringUtils.isEmpty(cnlTransTrace.getReqInnerNum())){
			if(logger.isErrorEnabled())
				logger.error("申请单流水号为空");
			throw new BizException("申请单流水号为空");
		}
		//判断客户唯一编码
		if(StringUtils.isEmpty(cnlTransTrace.getCustCode())){
			if(logger.isErrorEnabled())
				logger.error("客户唯一编码为空");
			throw new BizException("客户唯一编码为空");
		}
		//判断客户渠道编码
		if(StringUtils.isEmpty(cnlTransTrace.getCnlCnlCode())){
			if(logger.isErrorEnabled())
				logger.error("渠道客户编码为空");
			throw new BizException("渠道客户编码为空");
		}
		//判断申请单号
		if(StringUtils.isEmpty(cnlTransTrace.getReqNum())){
			if(logger.isErrorEnabled())
				logger.error("申请单号为空");
			throw new BizException("申请单号为空");
		}
		//判断交易类型
		if(StringUtils.isEmpty(cnlTransTrace.getTransType())){
			if(logger.isErrorEnabled())
				logger.error("交易类型为空");
			throw new BizException("交易类型为空");
		}
		//交易方向
		if(StringUtils.isEmpty(cnlTransTrace.getTransDc())){
			if(logger.isErrorEnabled())
				logger.error("交易方向为空");
			throw new BizException("交易方向为空");
		}
		//交易币种
		if(StringUtils.isEmpty(cnlTransTrace.getTransCurrency())){
			if(logger.isErrorEnabled())
				logger.error("交易币种为空");
			throw new BizException("交易币种为空");
		}
		//判断是否有金额
		/*if(c.getTransAmount()<0){
			throw new BizException("交易金额小于0");
		}
		//最新余额
		if(c.getTransLatestAmount()<0){
			throw new BizException("最新余额金额小于0");
		}*/
		//判断交易日期
		if(cnlTransTrace.getTransDate()==null){
			if(logger.isErrorEnabled())
				logger.error("交易日期为空");
			throw new BizException("交易日期为空");
		}
		//判断交易时间
		if(cnlTransTrace.getTransTime()==null){
			if(logger.isErrorEnabled())
				logger.error("交易时间为空");
			throw new BizException("交易时间为空");
		}
		//判断清算号
		if(StringUtils.isEmpty(cnlTransTrace.getStlNum())){
			if(logger.isErrorEnabled())
				logger.error("清算号为空");
			throw new BizException("清算号为空");
		}
		//终端类型
		if(StringUtils.isEmpty(cnlTransTrace.getTermialType())){
			if(logger.isErrorEnabled())
				logger.error("终端类型为空");
			throw new BizException("终端类型为空");
		}
	}


	/**
	 * 数据库映射的跟踪对象
	 * @param i 1企业开户，2企业更新 3个人开户 4个人更新
	 * @param param 接口传的参数类
	 * @return 和数据库映射的对象
	 * @throws Exception
	 */
	public static CnlCustTrace createCnlCustTrace(int type,CnlTransServiceAccountParam param)throws Exception{
		CnlCustTrace custTrace = new CnlCustTrace();
		//校验申请单号
		if(null == param.getMsgId()||"".equals(param.getMsgId())){
			if(logger.isErrorEnabled())
				logger.error("申请单号为空");
			throw new BizException("申请单号为空");
		}
		custTrace.setReqNum(param.getMsgId());
		/*
		if(null == param.getAddress()||"".equals(param.getAddress())){
			throw new BizException("地址为空");
		}
		 */
		//地址
		custTrace.setAddr(param.getAddress());	
		//校验报文时间
		if(null == param.getMsgDate()){
			if(logger.isErrorEnabled())
				logger.error("报文时间为空");
			throw new BizException("报文时间为空");
		}
		custTrace.setMsgTime(param.getMsgDate());
		//校验渠道编号
		if(null == param.getCnlCnlCode()||"".equals(param.getCnlCnlCode())){
			if(logger.isErrorEnabled())
				logger.error("渠道编号为空");
			throw new BizException("渠道编号为空");
		}
		custTrace.setCnlCustCode(param.getCnlCnlCode());	
		//校验渠道会员编码
		if(null == param.getCnl_customer_code()||"".equals(param.getCnl_customer_code())){
			if(logger.isErrorEnabled())
				logger.error("渠道会员编号为空");
			throw new BizException("渠道会员编号为空");
		}
		custTrace.setCnlCustCode(param.getCnl_customer_code());
		//校验交易流水
		if(null == param.getReqInnerNum()||"".equals(param.getReqInnerNum())){
			if(logger.isErrorEnabled())
				logger.error("交易流水为空");
			throw new BizException("交易流水为空");
		}
		custTrace.setReqInnerNum(param.getReqInnerNum());
		/*
		if(null == param.getPhonenum()||"".equals(param.getPhonenum())){
			throw new BizException("电话号为空");
		}
		 */
		custTrace.setPhonenum(param.getPhonenum());
		if(type==1||type==3){
			//企业开户
			if(type==1){
				//校验开卡户名
				if(null == param.getBank_account_name()||"".equals(param.getBank_account_name())){
					if(logger.isErrorEnabled())
						logger.error("开卡户名为空");
					throw new BizException("开卡户名为空");
				}
				//企业开户待处理
				custTrace.setReqStatus(Constants.CUST_TRACE_STATUS_PENDING);
				//银行分行CODE
				custTrace.setBankBranchCode(param.getBank_branch());
				//城市
				custTrace.setCity(param.getBank_city());
				//省份
				custTrace.setProvience(param.getBank_provice());	

			}else{
				//校验银行编号
				if(null == param.getBank_code()||"".equals(param.getBank_code())){
					if(logger.isErrorEnabled())
						logger.error("银行编号为空");
					throw new BizException("银行编号为空");
				}
				custTrace.setBankCode(param.getBank_code());
			}
			//开户人
			custTrace.setBankCustName(param.getBank_account_name());
			//校验银行卡号
			if(null == param.getBank_card_num()||"".equals(param.getBank_card_num())){
				if(logger.isErrorEnabled())
					logger.error("银行卡号为空");
				throw new BizException("银行卡号为空");
			}
			custTrace.setBankCardNum(param.getBank_card_num());
			//校验姓名
			if(null == param.getCustomer_name()||"".equals(param.getCustomer_name())){
				if(logger.isErrorEnabled())
					logger.error("姓名为空");
				throw new BizException("姓名为空");
			}
			custTrace.setName(param.getCustomer_name());		
			//校验证件号码
			if(null == param.getCert_num()||"".equals(param.getCert_num())){
				if(logger.isErrorEnabled())
					logger.error("证件号码为空");
				throw new BizException("证件号码为空");
			}
			custTrace.setCertNum(param.getCert_num());
			//校验证件有效期
			if(null == param.getCert_expire_date()||"".equals(param.getCert_expire_date().toString())){
				if(logger.isErrorEnabled())
					logger.error("证件有效期为空");
				throw new BizException("证件有效期为空");
			}
			//证件有效期
			custTrace.setCertExpire(param.getCert_expire_date());
			//跟踪单类型
			custTrace.setReqType(Constants.REQ_TYPE_ENTERPRISE_SIGN_UP);
		}
		//城市
		custTrace.setCountry(Constants.COUNTRY_37);
		//创建时间
		custTrace.setCreateTime(new Date());
		//创建人
		custTrace.setCreator(Constants.SYSADMIN);
		//是否有效
		custTrace.setIsValid(Constants.IS_VALID_VALID);
		//证件类型
		custTrace.setCertType(Constants.CERT_TYPE_MERCHANT_LICENSE);
		//用户类型
		custTrace.setCustType(Constants.CUST_TYPE_ENTERPRISE);
		if(type==2){
			//企业更新
			custTrace.setReqType(Constants.REQ_TYPE_UPDATE_ENTERPRISE_CUSTOMER);
			custTrace.setReqStatus(Constants.CUST_TRACE_STATUS_SUCCESS);
		}
		if(type==3){
			custTrace.setReqType(Constants.REQ_TYPE_INDIVIDUAL_SIGN_UP);
			//个人开户直接成功
			custTrace.setReqStatus(Constants.CUST_TRACE_STATUS_SUCCESS);
			//用户类型 个人
			custTrace.setCustType(Constants.CUST_TYPE_INDIVIDUAL);
		}
		if(type==4){
			custTrace.setReqType(Constants.REQ_TYPE_UPDATE_INDIVIDUAL_CUSTOMER);
			//个人更新直接成功
			custTrace.setReqStatus(Constants.CUST_TRACE_STATUS_SUCCESS);
			//用户类型 个人
			custTrace.setCustType(Constants.CUST_TYPE_INDIVIDUAL);
		}
		return custTrace;
	}


	/**
	 * 数据库映射的对象
	 * @param param 接口传的参数类
	 * @return 企业开户转账记录映射类
	 */
	public static CnlCustTraceBankVld createC(CnlTransServiceAccountParam param){

		CnlCustTraceBankVld c = new CnlCustTraceBankVld();
		//内部流水号
		c.setReqInnerNum(param.getReqInnerNum());
		//渠道客户编号
		c.setCnlCustCode(param.getCnl_customer_code());
		//渠道申请单号
		c.setReqNum(param.getMsgId());
		//交易类型
		c.setTransType(Constants.TRANS_TYPE_WITHDRAW);
		//交易方向
		c.setTransDc(Constants.TRANS_DC_OUTGOING);
		//币种
		c.setTransCurrency(Constants.CURRENCY_TYPE_50);
		//随机金额
		BigDecimal b =new BigDecimal(randomDoubleToString());
		c.setTransAmount(b);
		//交易状态
		c.setTransStatus(Constants.TRANS_STATUS_UNPROCESSED);
		//交易日期
		c.setTransDate(DateUtils.convert(new Date().toString(),DateUtils.DATE_FORMAT));
		//交易时间
		c.setTransTime(new Date());
		//交易汇率
		c.setTransRate(new BigDecimal("1"));
		//渠道编号
		c.setCnlCnlCode(param.getCnlCnlCode());
		//是否有效
		c.setIsValid(Constants.IS_VALID_VALID);
		//创建时间
		c.setCreateTime(new Date());
		//创建人
		c.setCreator(Constants.SYSADMIN);
		//城市
		c.setCity(param.getBank_city());
		//省份
		c.setProvience(param.getBank_provice());
		return c;
	}

	/**
	 * 获取一个随机金额
	 * @return
	 */
	private static String randomDoubleToString(){
		DecimalFormat    df   = new DecimalFormat("######0.00");  
		do{
			double a = Math.random();
			a =Double.valueOf(df.format(a));	
			System.out.println(a);		
			if(a>0&&a<1){
				return String.valueOf(a);
			}else{
				continue;
			}	
		}while(true);
	}

	/**
	 * String 转带2位小数的BigDecimal
	 * @param num
	 * @return
	 */
	private static BigDecimal formatToBigDecimal(String num){
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
		BigDecimal bigDecimal = new BigDecimal(df.format(new BigDecimal(num)));
		return bigDecimal;
	}
}
