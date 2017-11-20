/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlTransTracePendingBiz;
import com.ibs.core.module.account.dao.ICnlReqTransDao;
import com.ibs.core.module.account.dao.ICnlTransTracePendingDao;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.mq.producer.IProducer;
import com.ibs.core.module.bank.dao.ICorBankIntfDao;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.context.BeanHolder;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.StringUtils;

import net.sf.json.JSONObject;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransTracePendingBizImpl extends BaseBiz implements ICnlTransTracePendingBiz{
	private IDataDictService dataDictService;
	private ICorBankIntfDao bankIntfDao;
	private IProducer pendingTransTraceProducer;
	private IProducer pendingDebitProducer;
	private ICnlCnlCfgDao cnlCnlCfgDao;


	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}

	public IProducer getPendingDebitProducer() {
		return pendingDebitProducer;
	}

	public void setPendingDebitProducer(IProducer pendingDebitProducer) {
		this.pendingDebitProducer = pendingDebitProducer;
	}

	private ICnlReqTransDao cnlReqTransDao;

	public ICnlReqTransDao getCnlReqTransDao() {
		return cnlReqTransDao;
	}

	public void setCnlReqTransDao(ICnlReqTransDao cnlReqTransDao) {
		this.cnlReqTransDao = cnlReqTransDao;
	}

	public IProducer getPendingTransTraceProducer() {
		return pendingTransTraceProducer;
	}

	public void setPendingTransTraceProducer(IProducer pendingTransTraceProducer) {
		this.pendingTransTraceProducer = pendingTransTraceProducer;
	}

	public ICorBankIntfDao getBankIntfDao() {
		return bankIntfDao;
	}

	public void setBankIntfDao(ICorBankIntfDao bankIntfDao) {
		this.bankIntfDao = bankIntfDao;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	private ICnlTransTracePendingDao cnlTransTracePendingDao;
	public ICnlTransTracePendingDao getCnlTransTracePendingDao() {
		return cnlTransTracePendingDao;
	}

	public void setCnlTransTracePendingDao(ICnlTransTracePendingDao cnlTransTracePendingDao) {
		this.cnlTransTracePendingDao = cnlTransTracePendingDao;
	}

	private IExcelService excelService;

	@Override
	public IPage<CnlTransTrace> findCnlTransTraceByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransTraceBizImpl.findCnlTransTraceByPage()...");
		IPage<CnlTransTrace> cnlTransTraces = cnlTransTracePendingDao.findCnlTransTraceByPage(queryPage);
		Collection<CnlTransTrace> list = null;
		if(cnlTransTraces!=null){
			list = cnlTransTraces.getRows();
		}

		
		//交易类型
		List<DataDict> dataDictTransTypes =dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		//交易方向
		List<DataDict> dataDictDcTypes =dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		//交易币种
		List<DataDict> dataDictCurrencyTypes =dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		//终端类型
		List<DataDict> dataDicttermialTypeTypes =dataDictService.list(IDataDictService.DATA_DICT_TYPE__TERMIAL_TYPE);
		//银行处理优先级
		List<DataDict> dataDictBankDictTypes =dataDictService.list(IDataDictService.DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY);
		//处理状态
		List<DataDict> dataDictHandleStatus =dataDictService.list(IDataDictService.DATA_DICT_TYPE__PENDING_HANDLE_STATUS);
		//交易状态
		List<DataDict> dataDictTransStatus =dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		//支付通道
		List<DataDict> dataTypes =dataDictService.list(IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL);
		
		//渠道
		List<DataDict> dataCnlCnlCfgCode = new ArrayList<DataDict>();
		List<CnlCnlCfg> lists = cnlCnlCfgDao.getList();
		for (CnlCnlCfg c : lists) {
			DataDict dataDict = new DataDict();
			dataDict.setCode(c.getCnlCnlCode());
			dataDict.setName(c.getCnlSysName());
			dataCnlCnlCfgCode.add(dataDict);
		}
		try {
				//支付通道
			CollectionUtils.transformCollection(list, "bankPmtCnlType",
					"bankPmtCnlType", dataTypes, "code", "name");

			
				//交易类型
				CollectionUtils.transformCollection(list, "transType",
						"transType", dataDictTransTypes, "code", "name");

			
			
				//交易方向
				CollectionUtils.transformCollection(list, "transDc",
						"transDc", dataDictDcTypes, "code", "name");
			


			
				//交易币种transCurrency
				CollectionUtils.transformCollection(list, "transCurrency",
					"transCurrency", dataDictCurrencyTypes, "code", "name");
			


		
				//终端类型termialType
				CollectionUtils.transformCollection(list, "termialType",
						"termialType", dataDicttermialTypeTypes, "code", "name");
			


			
				//银行处理优先级
				CollectionUtils.transformCollection(list, "bnakHandlePriority",
						"bnakHandlePriority", dataDictBankDictTypes, "code", "name");
			
			

		
				//处理状态
				CollectionUtils.transformCollection(list, "handleStatus",
						"handleStatus", dataDictHandleStatus, "code", "name");
			
			

			
				//交易状态
				CollectionUtils.transformCollection(list, "transStatus",
						"transStatus", dataDictTransStatus, "code", "name");
			
			
			if(dataCnlCnlCfgCode.size()>0){
				//渠道名称
				CollectionUtils.transformCollection(list, "cnlCnlCode",
						"cnlCnlCode", dataCnlCnlCfgCode, "code", "name");
			}
			 

		} catch (Exception e) {
			throw new BizException(e.getMessage());
		}

		return cnlTransTraces;
	}


	public CnlTransTrace getCnlTransTraceById(String id){
		logger.info("entering action::CnlTransTraceBizImpl.getCnlTransTraceById()...");
		CnlTransTrace cnlTransTrace = cnlTransTracePendingDao.load(id);
		return cnlTransTrace;
	}




	/**
	 * @author jicheng
	 * 查询cnlTransTrace回显数据字典中的数据
	 */
	@Override
	public CnlTransTrace findDicCnlTransTraceById(String id) {
		logger.info("entering action::CnlTransTraceBizImpl.findDicCnlTransTraceById...");
		CnlTransTrace cnlTransTrace = cnlTransTracePendingDao.load(id);

		//数据字典显示交易终端类型
		List<DataDict>  termialTypeList= dataDictService.list(IDataDictService.DATA_DICT_TYPE__TERMIAL_TYPE);
		if(StringUtils.isNotEmpty(cnlTransTrace.getTermialType())){
			for (DataDict termialType : termialTypeList) {
				if(cnlTransTrace.getTermialType().equals(termialType.getCode())){
					cnlTransTrace.setTermialType(termialType.getName());
				}
			}

		}

		//数据字典显示币种
		List<DataDict>  transCurrencyList= dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		if(StringUtils.isNotEmpty(cnlTransTrace.getTransCurrency())){
			for (DataDict currency : transCurrencyList) {
				if(cnlTransTrace.getTransCurrency().equals(currency.getCode())){
					cnlTransTrace.setTransCurrency(currency.getName());
				}
			}

		}

		//数据字典显示交易方向
		List<DataDict>  transDcTypes= dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		if(StringUtils.isNotEmpty(cnlTransTrace.getTransDc())){
			for(DataDict transDc : transDcTypes) {
				if(cnlTransTrace.getTransDc().equals(transDc.getCode())) {
					cnlTransTrace.setTransDc(transDc.getName());
				}
			}
		}
		//数字字典显示交易类型
		List<DataDict>  dataDictTransType= dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		if(StringUtils.isNotEmpty(cnlTransTrace.getTransType())){
			for(DataDict transType : dataDictTransType) {
				if(cnlTransTrace.getTransType().equals(transType.getCode())) {
					cnlTransTrace.setTransType(transType.getName());
				}
			}
		}

		//数据字典显示银行有限处理级
		List<DataDict>  dataDictPriority= dataDictService.list(IDataDictService.DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY);
		if(StringUtils.isNotEmpty(cnlTransTrace.getBankHandleNum())){
			for(DataDict priority : dataDictPriority) {
				if(cnlTransTrace.getBankHandleNum().equals(priority.getCode())) {
					cnlTransTrace.setBankHandleNum(priority.getName());
				}
			}
		}
		//数据字典显示交易终端类型
		List<DataDict>  dataDictTermialType= dataDictService.list(IDataDictService.DATA_DICT_TYPE__TERMIAL_TYPE);
		if(StringUtils.isNotEmpty(cnlTransTrace.getTermialType())){
			for(DataDict termialType : dataDictTermialType) {
				if(cnlTransTrace.getTermialType().equals(termialType.getCode())) {
					cnlTransTrace.setTermialType(termialType.getName());
				}

			}
		}
		//数据字典显示交易状态
		List<DataDict>  dataDictTransStatus= dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		if(StringUtils.isNotEmpty(cnlTransTrace.getTransStatus())){
			for(DataDict transStatus : dataDictTransStatus) {
				if(cnlTransTrace.getTransStatus().equals(transStatus.getCode())) {
					String code = transStatus.getName();
					cnlTransTrace.setTransStatus(transStatus.getName());
				}
			}
		}
		//数据字典显示处理状态
		List<DataDict> dataDictHandleStatus = dataDictService.list(IDataDictService.DATA_DICT_TYPE__PENDING_HANDLE_STATUS);
		if(StringUtils.isNotEmpty(cnlTransTrace.getHandleStatus())){
			for (DataDict handleStatus : dataDictHandleStatus) {
				if(cnlTransTrace.getHandleStatus().equals(handleStatus.getCode())){
					cnlTransTrace.setHandleStatus(handleStatus.getName());
				}
			}
		}
		return cnlTransTrace;
	}

	@Override
	public CnlTransTrace saveCnlTransTrace(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceBizImpl.saveCnlTransTrace()...");
		cnlTransTrace.setId(null);
		cnlTransTracePendingDao.saveOrUpdate(cnlTransTrace);
		return cnlTransTrace;
	}


	/**
	 * @author jicheng
	 * 审核修改并MQ发送消息到队列
	 */
	@Override
	public void verifyCnlTransTrace(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceBizImpl.updateCnlTransTrace()...");
		//修改补救申请单数据
		cnlTransTracePendingDao.updateCnlTransTrace(cnlTransTrace);
		//查询待处理请求中的记录
		CnlReqTrans cnlReqTrans = cnlReqTransDao.findByInnerNum(cnlTransTrace.getReqInnerNum());

		if(null!=cnlReqTrans){

			if(cnlTransTrace.getHandleStatus().equals(Constants.PENDING_HANDLE_STATUS_SUCCESS)){

				cnlReqTrans.setReqStatus(Constants.REQ_STATUS_SUCCESS);

			}else{

				cnlReqTrans.setReqStatus(Constants.REQ_STATUS_FAIL);
			}

			cnlReqTransDao.updateReqTrans(cnlReqTrans);


			// 往MQ队列中发送 文本消息
			try {
				logger.info("----- send message to MQ start -----");
				MQConnectionFactory factory = new MQConnectionFactory();

				JSONObject response = new JSONObject();

				Map<String, String> responseHeader = new HashMap<String, String>();
				Map<String, String> responseBody = new HashMap<String, String>();

				//头信息
				responseHeader.put("channel_code",cnlTransTrace.getCnlCnlCode());
				responseHeader.put("service_code",cnlReqTrans.getCnlIntfCode());
				responseHeader.put("msg_id",cnlTransTrace.getReqNum());

				//充值操作
				if(cnlTransTrace.getTransType().equals(Constants.TRANS_TYPE_TOPUP)){

					//正文信息
					responseBody.put("trans_status",cnlTransTrace.getTransStatus());
					responseBody.put("cnl_customer_code",cnlTransTrace.getCnlCustCode());
					responseBody.put("cnl_order_num",cnlTransTrace.getTransOrderNum());
					responseBody.put("bank_card_num",cnlTransTrace.getBankCreditCardNum());
					responseBody.put("amount",cnlTransTrace.getTransAmount().toString());
					responseBody.put("bank_trans_num",cnlTransTrace.getBankTransNum());
					if(cnlTransTrace.getHandleStatus().equals(Constants.PENDING_HANDLE_STATUS_SUCCESS)){
						responseBody.put("err_msg", cnlTransTrace.getReviewMsg());
					}

					response.put("header", responseHeader);
					response.put("content", responseBody);
					String data = response.toString();
					logger.info("\n最终构造的JSON数据格式：       " + data);
					pendingTransTraceProducer = (IProducer) BeanHolder.getBean("pendingTransTraceProducer");

					pendingTransTraceProducer.sendMessage(data);
					logger.info("----- send message to MQ successfully ----");
					



				}

				//提现操作
				if(cnlTransTrace.getTransType().equals(Constants.REQ_TYPE_WITHDRAW)){

					//正文信息
					responseBody.put("trans_status",cnlTransTrace.getTransStatus());
					responseBody.put("cnl_customer_code",cnlTransTrace.getCnlCustCode());
					responseBody.put("bank_card_num", cnlTransTrace.getBankDebitCardNum());
					responseBody.put("amount", cnlTransTrace.getTransAmount().toString());
					responseBody.put("bank_trans_num", cnlTransTrace.getBankTransNum());
					if(cnlTransTrace.getHandleStatus().equals(Constants.PENDING_HANDLE_STATUS_FAIL)){
						responseBody.put("err_msg", cnlTransTrace.getReviewMsg());
					}
					response.put("header", responseHeader);
					response.put("content", responseBody);
					String data = response.toString();
					logger.info("\n最终构造的JSON数据格式：       " + data);
					pendingDebitProducer = (IProducer) BeanHolder.getBean("pendingDebitProducer");

					pendingDebitProducer.sendMessage(data);
					logger.info("----- send message to MQ successfully ----");
				}



				logger.info("--------- Send end -------------");
			} catch (Exception e) {
				logger.error("------ send message to MQ fail !!! -------");
			}
		}else{
			throw new BizException("待处理交易请求中没有对应记录");
		}
	}



	@Override
	public void exportCnlTransTrace(ExportSetting exportSetting) {
		logger.info("entering action::CnlTransTraceBizImpl.exportCnlTransTrace()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlTransTrace> cnlTransTracePage = (Page<CnlTransTrace>) cnlTransTracePendingDao
				.findCnlTransTraceByPage(exportSetting);

		excelService.exportToFile(cnlTransTracePage.getRows(), exportSetting);
	}

	public ICnlTransTracePendingDao getCnlTransTraceDao() {
		logger.info("entering action::CnlTransTraceBizImpl.getCnlTransTraceDao()...");
		return cnlTransTracePendingDao;
	}

	/*public void setCnlTransTraceDao(ICnlTransTracePendingDao cnlTransTraceDao) {
		logger.info("entering action::CnlTransTraceBizImpl.setCnlTransTraceDao()...");
		this.cnlTransTracePendingDao = cnlTransTraceDao;
	}*/

	public IExcelService getExcelService() {
		logger.info("entering action::CnlTransTraceBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlTransTraceBizImpl.setExcelService()...");
		this.excelService = excelService;
	}


	/**
	 * 查询银行接口是否正常
	 */
	@Override
	public String findStatus(String bankInnerCode) {
		return bankIntfDao.findCode(bankInnerCode);
	}


	/**
	 * @author jicheng
	 * 修改方法	
	 */
	@Override
	public void updateCnlTransTrace(CnlTransTrace cnlTransTrace) {
		// TODO Auto-generated method stub
		cnlTransTracePendingDao.updateCnlTransTrace(cnlTransTrace);

	}

	@Override
	/**
	 * 增加补救单
	 */
	public void updateStatus(CnlTransTrace cnlTransTrace) {
		cnlTransTracePendingDao.saveOrUpdate(cnlTransTrace);

	}

	@Override
	public CorBankIntf findByCode(String code) {

		return bankIntfDao.findByCode(code);
	}


}
