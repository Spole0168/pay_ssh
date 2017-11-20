/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.topup.biz.impl;


import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.topup.mq.producer.IProducer;
import com.ibs.core.module.topup.biz.ITopupTransTraceBiz;
import com.ibs.core.module.topup.dao.ITopupTransTraceDao;
import com.ibs.core.module.topup.domain.TopupTransTrace;
import com.ibs.core.module.topup.dto.TopupTransTraceDto;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.context.BeanHolder;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;
import com.ibs.core.module.account.dao.ICnlCustAcntDao;
import com.ibs.core.module.account.dao.ICnlCustAcntDtlDao;
import com.ibs.core.module.account.dao.ICnlReqTransDao;
import com.ibs.core.module.account.dao.ICnlTransDao;
import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.bank.dao.ICorBankAcntDao;
import com.ibs.core.module.bank.domain.CorBankAcnt;
import com.ibs.core.module.cnlcust.utils.BeanUtil;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.fundtransaction.dao.ICorBankAcntTransDao;
import com.ibs.core.module.fundtransaction.domain.CorBankAcntTrans;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.reservedfund.dao.ICorReservedFundAcntDao;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;

import net.sf.json.JSONObject;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class TopupTransTraceBizImpl extends BaseBiz implements ITopupTransTraceBiz {

	private ITopupTransTraceDao topupTransTraceDao;
	private IExcelService excelService;
	private IProducer topupProducer;
	
	private ICorBankAcntTransDao corBankAcntTransDao;
	private ICnlReqTransDao cnlReqTransDao;
	private ICnlTransDao cnlTransDao;
	private ICnlCustAcntDao cnlCustAcntDao;
	private ICnlCustAcntDtlDao cnlCustAcntDtlDao;
	private ICnlCnlCfgDao cnlCnlCfgDao;
	private ICorBankAcntDao corBankAcntDao;
	private ICorReservedFundAcntDao corReservedFundAcntDao;
	
	
	
	
	public ICorReservedFundAcntDao getCorReservedFundAcntDao() {
		return corReservedFundAcntDao;
	}
	public void setCorReservedFundAcntDao(ICorReservedFundAcntDao corReservedFundAcntDao) {
		this.corReservedFundAcntDao = corReservedFundAcntDao;
	}
	public ICorBankAcntDao getCorBankAcntDao() {
		return corBankAcntDao;
	}
	public void setCorBankAcntDao(ICorBankAcntDao corBankAcntDao) {
		this.corBankAcntDao = corBankAcntDao;
	}
	public IProducer getTopupProducer() {
		return topupProducer;
	}
	public void setTopupProducer(IProducer topupProducer) {
		this.topupProducer = topupProducer;
	}
	public ICorBankAcntTransDao getCorBankAcntTransDao() {
		return corBankAcntTransDao;
	}
	public void setCorBankAcntTransDao(ICorBankAcntTransDao corBankAcntTransDao) {
		this.corBankAcntTransDao = corBankAcntTransDao;
	}
	public ICnlReqTransDao getCnlReqTransDao() {
		return cnlReqTransDao;
	}
	public void setCnlReqTransDao(ICnlReqTransDao cnlReqTransDao) {
		this.cnlReqTransDao = cnlReqTransDao;
	}
	public ICnlTransDao getCnlTransDao() {
		return cnlTransDao;
	}
	public void setCnlTransDao(ICnlTransDao cnlTransDao) {
		this.cnlTransDao = cnlTransDao;
	}
	public ICnlCustAcntDao getCnlCustAcntDao() {
		return cnlCustAcntDao;
	}
	public void setCnlCustAcntDao(ICnlCustAcntDao cnlCustAcntDao) {
		this.cnlCustAcntDao = cnlCustAcntDao;
	}
	public ICnlCustAcntDtlDao getCnlCustAcntDtlDao() {
		return cnlCustAcntDtlDao;
	}
	public void setCnlCustAcntDtlDao(ICnlCustAcntDtlDao cnlCustAcntDtlDao) {
		this.cnlCustAcntDtlDao = cnlCustAcntDtlDao;
	}
	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		return cnlCnlCfgDao;
	}
	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}
	//	@Override
//	public IPage<TopupTransTrace> findTopupTransTraceByPage(QueryPage queryPage) {
//		logger.info("entering action::TopupTransTraceBizImpl.findTopupTransTraceByPage()...");
//		return topupTransTraceDao.findTopupTransTraceByPage(queryPage);
//	}
	@Override
	public IPage<TopupTransTraceDto> findTopupTransTraceByPage(QueryPage queryPage) {
		logger.info("entering action::TopupTransTraceBizImpl.findTopupTransTraceByPage()...");
//		return topupTransTraceDao.findTopupTransTraceByPage(queryPage);
		return null;
	}
	@Override
	public IPage<TopupTransTraceDto> findCnlTransBySql(QueryPage queryPage, TopupTransTraceDto conditionDto) {
		logger.info("entering action::TopupTransTraceBizImpl.findTopupTransTraceByPage()...");
		return topupTransTraceDao.findCnlTransBySql(queryPage, conditionDto);
	}	
	@Override
	public TopupTransTrace getTopupTransTraceById(String id) {
		logger.info("entering action::TopupTransTraceBizImpl.getTopupTransTraceById()...");
		return topupTransTraceDao.load(id);
	}

	@Override
	public TopupTransTrace saveTopupTransTrace(TopupTransTrace topupTransTrace) {
		logger.info("entering action::TopupTransTraceBizImpl.saveTopupTransTrace()...");
		topupTransTrace.setId(null);
		topupTransTraceDao.saveOrUpdate(topupTransTrace);
		return topupTransTrace;
	}

	@Override
	public TopupTransTrace updateTopupTransTrace(TopupTransTrace topupTransTrace) {
		logger.info("entering action::TopupTransTraceBizImpl.updateTopupTransTrace()...");
		topupTransTraceDao.saveOrUpdate(topupTransTrace);
		return topupTransTrace;
	}

	@Override
	public void exportTopupTransTrace(ExportSetting exportSetting, TopupTransTraceDto conditionDto) {
		logger.info("entering action::TopupTransTraceBizImpl.exportTopupTransTrace()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<TopupTransTraceDto> topupTransTracePage = (Page<TopupTransTraceDto>) topupTransTraceDao
				.findTopupTransTraceByPage(exportSetting, conditionDto);

		excelService.exportToFile(topupTransTracePage.getRows(), exportSetting);
	}

	public ITopupTransTraceDao getTopupTransTraceDao() {
		logger.info("entering action::TopupTransTraceBizImpl.getTopupTransTraceDao()...");
		return topupTransTraceDao;
	}

	public void setTopupTransTraceDao(ITopupTransTraceDao topupTransTraceDao) {
		logger.info("entering action::TopupTransTraceBizImpl.setTopupTransTraceDao()...");
		this.topupTransTraceDao = topupTransTraceDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::TopupTransTraceBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::TopupTransTraceBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	
	@Override
	public void checks(ArrayList<TopupTransTrace> topupTransTraceList) {
		logger.info("entering action::TopupTransTraceBizImpl.checks()...");
		for (TopupTransTrace topupTransTrace : topupTransTraceList) {
			topupTransTraceDao.checks(topupTransTrace);
			// 交易流水状态更新
			CnlTransTrace cnlTransTrace = new CnlTransTrace();
			try {
				BeanUtil.copyBasicTypeProperties(cnlTransTrace, topupTransTrace);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			this.updateCnlReqTrans(cnlTransTrace);
			// 发送消息
			this.sendMessage(topupTransTrace);
		} 
	}
	@Override
	public void sendMessage(ArrayList<TopupTransTrace> topupTransTraceList) {
		for (TopupTransTrace topupTransTrace : topupTransTraceList) {
			// 交易状态为成功OR失败的场合下，发送消息
			if (Constants.TRANS_STATUS_SUCCESS.equals(topupTransTrace.getTransStatus()) ||
					Constants.TRANS_STATUS_FAIL.equals(topupTransTrace.getTransStatus())) {
				sendMessage(topupTransTrace);
			}
		}
	}

	/**
	 * 
	 * 发送MQ队列消息
	 * 
	 * @param topupTransTrace
	 */
	private void sendMessage(TopupTransTrace topupTransTrace) {
		// 发送报文
		try {
			System.out.println("--------- Send start -------------");
			System.out.println("sendMessage：交易状态:" + topupTransTrace.getTransStatus());
			JSONObject response = new JSONObject();

			Map<String, String> responseHeader = new HashMap<String, String>();
			responseHeader.put("channel_code", 		topupTransTrace.getCnlCnlCode());
			responseHeader.put("service_code", 		"NMG02001");// 参照平台接口说明书
			responseHeader.put("msg_id", 			topupTransTrace.getReqNum());

			Map<String, String> responseBody = new HashMap<String, String>();
			if (Constants.TRANS_STATUS_SUCCESS.equals(topupTransTrace.getTransStatus())) {
				responseBody.put("trans_status", 	Constants.TRANS_RETTONMG_SUCCESS);
				responseBody.put("err_msg", 		"");
			} else if (Constants.TRANS_STATUS_FAIL.equals(topupTransTrace.getTransStatus())) {
				responseBody.put("trans_status", 	Constants.TRANS_RETTONMG_FAIL);
				responseBody.put("err_msg", 		NulltoString(topupTransTrace.getErrMsg()));
			}
			responseBody.put("cnl_customer_code", 	NulltoString(topupTransTrace.getCnlCustCode()));
			responseBody.put("cnl_order_num", 		NulltoString(topupTransTrace.getTransOrderNum()));
			responseBody.put("bank_card_num", 		NulltoString(topupTransTrace.getBankCreditCardNum()));
			responseBody.put("amount", 				NulltoZero(topupTransTrace.getTransAmount()).toString());
			responseBody.put("bank_trans_num", 		NulltoString(topupTransTrace.getBankTransNum()));
			responseBody.put("req_trans_num", 		NulltoString(topupTransTrace.getReqInnerNum()));

			response.put("header", 	responseHeader);
			response.put("content", responseBody);

			String data = response.toString();
			System.out.println("\n最终构造的JSON数据格式：" + data);
			topupProducer = (IProducer) BeanHolder.getBean("topupProducer");
			topupProducer.sendMessage(data);

			System.out.println("--------- Send end -------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void handleOrReview(TopupTransTrace topupTransTrace){
		TopupTransTrace temp = DealHandleReivewDate(topupTransTrace);
		topupTransTraceDao.saveOrUpdate(temp);
		CnlTransTrace cnlTransTrace = new CnlTransTrace();
		try {
			BeanUtil.copyBasicTypeProperties(cnlTransTrace, temp);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 审核失败场合下的处理
		if(Constants.RECORD_HANDLE_STATUS_1ST_CHECK_FAIL.equals(temp.getHandleStatus())){
			cnlTransTrace.setTransStatus(Constants.TRANS_STATUS_FAIL);
			updateCnlReqTrans(cnlTransTrace);
			sendMessage(temp);
		}
		// 复核通过入账
		if(Constants.RECORD_HANDLE_STATUS_2ND_CHECK_PASS.equals(temp.getHandleStatus())){
//			CnlTransTrace cnlTransTrace = new CnlTransTrace();
//			try {
//				BeanUtil.copyBasicTypeProperties(cnlTransTrace, temp);
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
			inAccount(cnlTransTrace);
			// 放入消息队列通知客户
			sendMessage(temp);
		}
	}
	private TopupTransTrace DealHandleReivewDate(TopupTransTrace topupTransTrace) {
		TopupTransTrace topupTransTraceTemp = this.getTopupTransTraceById(topupTransTrace.getId());
//		//交易状态
//		if(null!=topupTransTrace&&null!=topupTransTrace.getTransStatus()&&!"".equals(topupTransTrace.getTransStatus())){
//			topupTransTraceTemp.setTransStatus(topupTransTrace.getTransStatus());
//		}
		//银行交易流水号
		if(null!=topupTransTrace&&null!=topupTransTrace.getBankTransNum()&&!"".equals(topupTransTrace.getBankTransNum())){
			topupTransTraceTemp.setBankTransNum(topupTransTrace.getBankTransNum());
		}
		//支付通道
		if(null!=topupTransTrace&&null!=topupTransTrace.getBankPmtCnlType()&&!"".equals(topupTransTrace.getBankPmtCnlType())){
			topupTransTraceTemp.setBankPmtCnlType(topupTransTrace.getBankPmtCnlType());
		}
		//银行摘要
		if(null!=topupTransTrace&&null!=topupTransTrace.getTransBankSummary()&&!"".equals(topupTransTrace.getTransBankSummary())){
			topupTransTraceTemp.setTransBankSummary(topupTransTrace.getTransBankSummary());
		}
		//凭证图片
		if(null!=topupTransTrace&&null!=topupTransTrace.getVoucherLocation()&&!"".equals(topupTransTrace.getVoucherLocation())){
			topupTransTraceTemp.setVoucherLocation(topupTransTrace.getVoucherLocation());
			topupTransTraceTemp.setVoucherNum(topupTransTrace.getVoucherNum());
		}
		//实际到账金额
		if(null!=topupTransTrace&&null!=topupTransTrace.getTransLatestAmount()&&!"".equals(topupTransTrace.getTransLatestAmount())){
			topupTransTraceTemp.setTransLatestAmount(topupTransTrace.getTransLatestAmount());
		}
		//到帐时间
		if(null!=topupTransTrace&&null!=topupTransTrace.getBankReturnTime()&&!"".equals(topupTransTrace.getBankReturnTime())){
			topupTransTraceTemp.setBankReturnTime(topupTransTrace.getBankReturnTime());
		}
		//失败原因
		if(null!=topupTransTrace&&null!=topupTransTrace.getHandleResult()&&!"".equals(topupTransTrace.getHandleResult())){
			topupTransTraceTemp.setHandleResult(topupTransTrace.getHandleResult());
		}
		//处理状态
		if(null!=topupTransTrace&&null!=topupTransTrace.getHandleStatus()&&!"".equals(topupTransTrace.getHandleStatus())){
			topupTransTraceTemp.setHandleStatus(topupTransTrace.getHandleStatus());
			// 交易状态联动变更:处理状态为“审核失败”的场合下，交易状态修改为“失败”
			if ((Constants.RECORD_HANDLE_STATUS_1ST_CHECK_FAIL).equals(topupTransTrace.getHandleStatus())) {
				topupTransTraceTemp.setTransStatus(Constants.TRANS_STATUS_FAIL);
			}
			//复核成功需要修改交易状态为成功
			if(Constants.RECORD_HANDLE_STATUS_2ND_CHECK_PASS.equals(topupTransTrace.getHandleStatus())){
				topupTransTraceTemp.setTransStatus(Constants.TRANS_STATUS_SUCCESS);
			}
		}
		IUser iUser = this.getCurrentUser();

		Date dt = new Date();
		//设置审核信息
		if(Constants.RECORD_HANDLE_STATUS_1ST_CHECK_PASS.equals(topupTransTrace.getHandleStatus())||Constants.RECORD_HANDLE_STATUS_1ST_CHECK_FAIL.equals(topupTransTrace.getHandleStatus())){
			topupTransTraceTemp.setOperator(iUser.getUserName());
			topupTransTraceTemp.setHandleTime(dt);
		}
		//设置复核信息
		if(Constants.RECORD_HANDLE_STATUS_2ND_CHECK_PASS.equals(topupTransTrace.getHandleStatus())||Constants.RECORD_HANDLE_STATUS_2ND_CHECK_FAIL.equals(topupTransTrace.getHandleStatus())){
			topupTransTraceTemp.setReviewer(iUser.getUserName());
			topupTransTraceTemp.setReviewTime(dt);
		}
		topupTransTrace.setUpdateTime(dt);
		topupTransTrace.setUpdator(iUser.getUserName());
		
		return topupTransTraceTemp;
	}
	private String NulltoString(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}
	private BigDecimal NulltoZero(BigDecimal value) {
		if (value == null) {
			return BigDecimal.ZERO;
		}
		return value;
	}
	/**
	 * 充值入账
	 * 
	 * 入账 明细账 和  总账
	 * shangzhuzi
	 * @throws Exception 
	 */
	private void inAccount(CnlTransTrace cnlTransTrace){
//		//insert 资金账户流水表
//		saveBankAcntTransToDb(cnlTransTrace);
//		//update 申请单跟踪表   cnlTransTrace  -update  updatetime  TRANS_STATUS - 03
//		topupTransTrace.setTransStatus(Constants.TRANS_STATUS_SUCCESS);
//		updateApply(topupTransTrace);
		
		//update 渠道申请单流水表 T_CNL_REQ_TRANS
		updateCnlReqTrans(cnlTransTrace);
		//insert 渠道交易流水 T_CNL_TRANS
		insertCnlTrans(cnlTransTrace);
		// 7.更新渠道账户和个人或企业账户，增加明细账
		updateCnlCustAcntandCnlCustAcntDTL(cnlTransTrace);
	}
	
	/**
	 * 插入T_COR_BANK_ACNT_TRANS表 将申请单跟踪表中筛选出的数据插入
	 * 
	 * @param corBankAcnt
	 * @return true/false
	 * @return
	 * @throws InvocationTargetException
	 */
	private boolean saveBankAcntTransToDb(CnlTransTrace cnlTransTrace) throws InvocationTargetException {
		logger.debug("enter PaymentStatusBizImpl.saveBankAcntTransToDb()");
		CorBankAcntTrans corBankAcntTrans = new CorBankAcntTrans();
		BeanUtil.copyBasicTypeProperties(corBankAcntTrans, cnlTransTrace);
		// corBankAcntTrans.setBankNum("");
		corBankAcntTrans.setId(null);
		corBankAcntTrans.setTransNum(cnlTransTrace.getBankTransNum());
		corBankAcntTrans.setReturnCode(cnlTransTrace.getBankReturnResult());
		corBankAcntTrans.setAmount(cnlTransTrace.getTransAmount());
		corBankAcntTrans.setIsValid(Constants.IS_VALID_VALID);
		corBankAcntTrans.setCreateTime(new Date());
		corBankAcntTrans.setCreator(Constants.SYSADMIN);
		// corBankAcntTrans.setTransOrderNum("001");
		corBankAcntTransDao.saveOrUpdate(corBankAcntTrans);
	
		return true;
	}
	/**
	 * update 申请单跟踪表
	 * @param topupTransTrace
	 * @return
	 */
	private boolean updateApply(TopupTransTrace topupTransTrace){
		topupTransTraceDao.saveOrUpdate(topupTransTrace);
		return true;
	}
	/**
	 * 更新渠道申请单流水表 T_CNL_REQ_TRANS
	 * 
	 * @param list
	 * @return true/false;
	 * 
	 * 
	 */
	private boolean updateCnlReqTrans(CnlTransTrace cnlTransTrace) {
		CnlReqTrans reqTrans = new CnlReqTrans();
		reqTrans = cnlReqTransDao.findByInnerNum(cnlTransTrace.getReqInnerNum());
		reqTrans.setReqStatus(cnlTransTrace.getTransStatus());
		reqTrans.setUpdator(Constants.SYSADMIN);
		reqTrans.setUpdateTime(new Date());
		cnlReqTransDao.updateReqTrans(reqTrans);
		return true;
	}

	/**
	 * 只有返回代付成功时，插入渠道交易流水 T_CNL_TRANS
	 * 
	 * @param list
	 * @return true/false
	 * @throws InvocationTargetException
	 */
	private boolean insertCnlTrans(CnlTransTrace cnlTransTrace) {
		logger.info("enter PaymentStatusBizImpl.insertCnlTrans()");
		CnlTrans cnlTrans = new CnlTrans();
		try {
			BeanUtil.copyBasicTypeProperties(cnlTrans, cnlTransTrace);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		cnlTrans.setId(null);
		cnlTrans.setTransNum(StringUtils.getUUID());// 交易流水号系统生成
		cnlTrans.setIsValid(Constants.IS_VALID_VALID);
		cnlTrans.setIsinGl(Constants.ISIN_GL_RECORDED);
		cnlTrans.setCreator(Constants.SYSADMIN);;
		cnlTrans.setCreateTime(new Date());
		cnlTransDao.saveOrUpdate(cnlTrans);
		return true;
	}
	
	private void updateCnlCustAcntandCnlCustAcntDTL(CnlTransTrace cnlTransTrace) {
		CnlCustAcnt cnlCustAcntCompany = null;
		CnlCustAcnt cnlCustAcntPersonal = null;
		String cnlCnlCode = cnlTransTrace.getCnlCnlCode();
		String cnlCustCode = cnlTransTrace.getCnlCustCode();
		logger.info("...cnlCnlCode:"+cnlCnlCode+"...cnlCustCode:"+cnlCustCode);
		
		// a.确定渠道客户
		cnlCustAcntCompany = findcnlCustAcntCompany(cnlCnlCode);
		// b.确定个人或企业客户
		cnlCustAcntPersonal = cnlCustAcntDao.findPersonalByCnlCnlCodeAndCnlCustCode(cnlCnlCode, cnlCustCode);
		if (null != cnlCustAcntPersonal && null != cnlCustAcntCompany) {
				logger.info("...Trans successed:" + cnlTransTrace.getTransStatus());
				// 成功
				// c.1对金额进行处理
				// 更新渠道账户
				cnlCustAcntDao.updatePersonalCreditSuccess(cnlCustAcntPersonal, cnlTransTrace.getTransAmount());
				// 更新个人或企业账户
				cnlCustAcntDao.updateCompanyCreditSuccess(cnlCustAcntCompany, cnlTransTrace.getTransAmount());
				// c.2加入明细账
				insertCnlCustAcntDtl(cnlCustAcntCompany, cnlTransTrace);
				insertCnlCustAcntDtl(cnlCustAcntPersonal, cnlTransTrace);
		}
		
	}
	/**
	 * 查询出渠道客户 1.通过cnlCnlCode 从CNL_CNL_CFG中找到客户编码 cnlCustCode
	 * 2.通过cnlCustCode找到渠道账户
	 */
	private CnlCustAcnt findcnlCustAcntCompany(String cnlCnlCode) {
		
		String cnlCustCode = cnlCnlCfgDao.findCnlCustCodeByCnlCnlCode(cnlCnlCode);
		if (null != cnlCustCode) {
			logger.info("...cnlCustCode:" + cnlCustCode);
			CnlCustAcnt cnlCustAcnt = cnlCustAcntDao.findByCustCode(cnlCustCode);
			if (null != cnlCustAcnt) {
				return cnlCustAcnt;
			} else {
				logger.info("can not found the CompanyACNT...");
				return null;
			}
		} else {
			logger.info("can not found the cnlCustCode from Table T.CNL_CNL_CFG");
			return null;
		}
	}
	// 插入账户明细表
	private boolean insertCnlCustAcntDtl(CnlCustAcnt cnlCustAcnt, CnlTransTrace cnlTransTrace) {
		logger.info("enter PaymentStatusBizImpl.insertCnlCustAcntDtl()");
		CnlCustAcntDtl cnlCustAcntDtl = new CnlCustAcntDtl();
		cnlCustAcntDtl.setAcntDtlCode(StringUtils.getUUID());
		cnlCustAcntDtl.setCnlAcntCode(cnlCustAcnt.getCnlAcntCode());
		cnlCustAcntDtl.setCnlCustCode(cnlCustAcnt.getCnlCustCode());
		cnlCustAcntDtl.setCustCode(cnlCustAcnt.getCustCode());
		cnlCustAcntDtl.setTransNum(cnlTransTrace.getBankTransNum());
		cnlCustAcntDtl.setAcntType(cnlCustAcnt.getAcntType());
		cnlCustAcntDtl.setCurrency(cnlCustAcnt.getCurrency());
		cnlCustAcntDtl.setAmount(cnlTransTrace.getTransAmount());
		cnlCustAcntDtl.setDirection(cnlTransTrace.getTransDc());
		cnlCustAcntDtl.setTransType(cnlTransTrace.getTransType());
		// 更新之后的金额为
		BigDecimal amount = cnlCustAcnt.getBalance().add(cnlTransTrace.getTransAmount());
		cnlCustAcntDtl.setBalance(amount);
		cnlCustAcntDtl.setPreBalance(cnlCustAcnt.getBalance());
		cnlCustAcntDtl.setTransDate(cnlTransTrace.getTransDate());
		cnlCustAcntDtl.setTransTime(cnlTransTrace.getTransTime());
		cnlCustAcntDtl.setIsPrinted(cnlTransTrace.getIsPrinted());
		cnlCustAcntDtl.setVoucherNum(cnlTransTrace.getVoucherNum());
		cnlCustAcntDtl.setComments(cnlTransTrace.getTransComments());
		cnlCustAcntDtl.setIsValid(Constants.IS_VALID_VALID);
		cnlCustAcntDtl.setCreator(Constants.SYSADMIN);
		cnlCustAcntDtl.setCreateTime(new Date());

		cnlCustAcntDtlDao.saveCnlCustAcntDtl(cnlCustAcntDtl);
		return true;
	}
	@Override
	public String getCnlSysNameByCnlCnlCode(String cnlCnlCode) {
		logger.info("entering action::TopupTransTraceBizImpl.getCnlSysNameByCnlCnlCode()...");
		if (cnlCnlCode != null && !cnlCnlCode.equals("")) {
			List<CnlCnlCfg> cnlCnlCfgList = cnlCnlCfgDao.getCfgList();
			
			for (CnlCnlCfg cnlCnlCfg : cnlCnlCfgList) {
				if (cnlCnlCode.equals(cnlCnlCfg.getCnlCnlCode()) && (Constants.IS_VALID_VALID).equals(cnlCnlCfg.getIsValid())) {
					return cnlCnlCfg.getCnlSysName();
				}
			}
		}
		return "";
	}
	@Override
	public void updateAllTopupTransTraceBankInfo() {
		logger.info("entering action::updateAllTopupTransTraceBankInfo.updateAllTopupTransTraceBankInfo()...");

		// 检索渠道跟踪表数据
		List<TopupTransTrace> topupTransTraceList = topupTransTraceDao.getTopupTransTraceList();
		// 没有数据则处理结束
		if (topupTransTraceList == null || topupTransTraceList.size() == 0) {
			return;
		}

		for (TopupTransTrace topupTransTrace : topupTransTraceList) {
			
			// 非有效数据不处理
			if (!Constants.IS_VALID_VALID.equals(topupTransTrace.getIsValid())) {
				continue;
			}
			// 交易状态非待处理的数据不处理
			if (!Constants.TRANS_STATUS_UNPROCESSED.equals(topupTransTrace.getTransStatus())) {
				continue;
			}
			// 银行信息已经设定的数据不处理
			if (!AllBankInfoIsBlank(topupTransTrace)) {
				continue;
			}
			// 设定收款行和付款行的信息
			// 渠道编码取得
			String cnlCnlCode = topupTransTrace.getCnlCnlCode();
			// 客户编号取得
			String cnlCustCode = topupTransTrace.getCnlCustCode();
			// 渠道-总账账户数据取得
			CnlCustAcnt cnlCustAcnt = cnlCustAcntDao.findPersonalByCnlCnlCodeAndCnlCustCode(cnlCnlCode, cnlCustCode);
			// 如果没有关联数据，处理跳过
			if (cnlCustAcnt == null) {
				continue;
			}

			// 银行账户编码取得
			String bankAcntCode = Null2String(cnlCustAcnt.getBankAcntCode());
			// 如果银行账户编码为空，处理跳过
			if ("".equals(bankAcntCode)) {
				continue;
			}
			
			// 检索用户资金账号数据
			CorBankAcnt corBankAcnt = corBankAcntDao.findByBankAcntCode(bankAcntCode);
			// 如果没有数据，处理跳过
			if (corBankAcnt == null) {
				continue;
			}

			// 付款银行账户编码
			String bankCreditAcntCode 		= Null2String(corBankAcnt.getBankAcntCode());
			// 付款银行名称
			String bankCreditName 			= Null2String(corBankAcnt.getBankName());
			// 付款银行代码
			String bankCreditCode 			= Null2String(corBankAcnt.getBankCode());
			// 付款银行分行名称
			String bankCreditBranchName 	= Null2String(corBankAcnt.getBankBranchName());
			// 付款银行分行代码
			String bankCreditBranchCode 	= Null2String(corBankAcnt.getBankBranchCode());
			// 付款行开户名
			String bankCreditCustName 		= Null2String(corBankAcnt.getCustName());
			// 付款银行卡号
			String bankCreditCardNum 		= Null2String(corBankAcnt.getBankCardNum());
		
			// 检索系统资金账号数据
			CorReservedFundAcnt corReservedFundAcnt = topupTransTraceDao.getCorReservedFundAcntByBankCode(bankCreditCode);
			// 如果没有数据，处理跳过
			if (corReservedFundAcnt == null) {
				continue;
			}

			// 收款银行账户编码
			String bankDebitAcntCode 		= Null2String(corReservedFundAcnt.getBankAcntCode());
			// 收款银行名称
			String bankDebitName 			= Null2String(corReservedFundAcnt.getBankName());
			// 收款银行代码
			String bankDebitCode 			= Null2String(corReservedFundAcnt.getBankCode());
			// 收款银行分行名称
			String bankDebitBranchName 		= Null2String(corReservedFundAcnt.getBankBranchName());
			// 收款银行分行代码
			String bankDebitBranchCode 		= Null2String(corReservedFundAcnt.getBankBranchCode());
			// 收款行开户名
			String bankDebitCustName 		= Null2String(corReservedFundAcnt.getCustName());
			// 收款银行卡号
			String bankDebitCardNum 		= Null2String(corReservedFundAcnt.getBankCardNum());
			
			// 付款行信息更新
			topupTransTrace.setBankCreditAcntCode(bankCreditAcntCode);
			topupTransTrace.setBankCreditName(bankCreditName);
			topupTransTrace.setBankCreditCode(bankCreditCode);
			topupTransTrace.setBankCreditBranchName(bankCreditBranchName);
			topupTransTrace.setBankCreditBranchCode(bankCreditBranchCode);
			topupTransTrace.setBankCreditCustName(bankCreditCustName);
			topupTransTrace.setBankCreditCardNum(bankCreditCardNum);

			// 收款行信息更新
			topupTransTrace.setBankDebitAcntCode(bankDebitAcntCode);
			topupTransTrace.setBankDebitName(bankDebitName);
			topupTransTrace.setBankDebitCode(bankDebitCode);
			topupTransTrace.setBankDebitBranchName(bankDebitBranchName);
			topupTransTrace.setBankDebitBranchCode(bankDebitBranchCode);
			topupTransTrace.setBankDebitCustName(bankDebitCustName);
			topupTransTrace.setBankDebitCardNum(bankDebitCardNum);
			// 更新人
			topupTransTrace.setUpdator(Constants.SYSADMIN);
			// 更新时间
			topupTransTrace.setUpdateTime(new Date());
			// 数据更新
			topupTransTraceDao.saveOrUpdate(topupTransTrace);
		}
		return;
	}
	private String Null2String(String value) {
		return (value == null) ? "" : value;
	}
	/**
	 * 付款行和收款行的信息完全检查，如果都为空，则返回true，只要有一个不为空，否则返回false
	 * @param topupTransTrace
	 * @return
	 */
	private boolean AllBankInfoIsBlank(TopupTransTrace topupTransTrace) {
		
		String bankCreditAcntCode 		= Null2String(topupTransTrace.getBankCreditAcntCode());
		String bankCreditName 			= Null2String(topupTransTrace.getBankCreditName());
		String bankCreditCode 			= Null2String(topupTransTrace.getBankCreditCode());
		String bankCreditBranchName 	= Null2String(topupTransTrace.getBankCreditBranchName());
		String bankCreditBranchCode 	= Null2String(topupTransTrace.getBankCreditBranchCode());
		String bankCreditCustName 		= Null2String(topupTransTrace.getBankCreditCustName());
		String bankCreditCardNum 		= Null2String(topupTransTrace.getBankCreditCardNum());

		String bankDebitAcntCode 		= Null2String(topupTransTrace.getBankDebitAcntCode());
		String bankDebitName 			= Null2String(topupTransTrace.getBankDebitName());
		String bankDebitCode 			= Null2String(topupTransTrace.getBankDebitCode());
		String bankDebitBranchName 		= Null2String(topupTransTrace.getBankDebitBranchName());
		String bankDebitBranchCode		= Null2String(topupTransTrace.getBankDebitBranchCode());
		String bankDebitCustName 		= Null2String(topupTransTrace.getBankDebitCustName());
		String bankDebitCardNum 		= Null2String(topupTransTrace.getBankDebitCardNum());
		
		if ("".equals(bankCreditAcntCode) &&
			"".equals(bankCreditName) &&
			"".equals(bankCreditCode) &&
			"".equals(bankCreditBranchName) && 
			"".equals(bankCreditBranchCode) &&
			"".equals(bankCreditCustName) &&
			"".equals(bankCreditCardNum) &&

			"".equals(bankDebitAcntCode) &&
			"".equals(bankDebitName) &&
			"".equals(bankDebitCode) &&
			"".equals(bankDebitBranchName) && 
			"".equals(bankDebitBranchCode) &&
			"".equals(bankDebitCustName) &&
			"".equals(bankDebitCardNum)
				) {
			return true;
		}
		return false;
	}
}
