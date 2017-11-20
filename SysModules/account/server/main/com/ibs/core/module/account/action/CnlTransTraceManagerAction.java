/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.account.biz.ICnlTransBiz;
import com.ibs.core.module.account.biz.ICnlTransTraceBiz;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceDto;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlTransTraceManagerAction extends CrudBaseAction {

	private ICnlTransTraceBiz cnlTransTraceBiz;
	private String id;
	private CnlTransTrace cnlTransTrace;
	private String msg; // 报文处理状态
	private String transStatus; // 交易状态
	private List<OptionObjectPair> transTypeList; // 交易类型
	private List<OptionObjectPair> handleStatusList; // 处理状态
	private List<OptionObjectPair> errTypeList; // 错误类型
	private List<OptionObjectPair> msgHandleStatusList; // 报文处理状态
	private IDataDictBiz dataDictBiz;
	private ICnlTransBiz cnlTransBiz;
	//渠道来源标识
	private List<CnlCnlCfg> cnlCnlCfgList;
	// 渠道来源标识
	private List<OptionObjectPair> cnlCnlCodeList;
	private IDataDictService dataDictService;
	private StringBuffer condition = new StringBuffer();
	
	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public List<OptionObjectPair> getMsgHandleStatusList() {
		return msgHandleStatusList;
	}

	public void setMsgHandleStatusList(List<OptionObjectPair> msgHandleStatusList) {
		this.msgHandleStatusList = msgHandleStatusList;
	}

	public List<OptionObjectPair> getHandleStatusList() {
		return handleStatusList;
	}

	public void setHandleStatusList(List<OptionObjectPair> handleStatusList) {
		this.handleStatusList = handleStatusList;
	}

	public List<OptionObjectPair> getErrTypeList() {
		return errTypeList;
	}

	public void setErrTypeList(List<OptionObjectPair> errTypeList) {
		this.errTypeList = errTypeList;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}
	
	public List<OptionObjectPair> getCnlCnlCodeList() {
		return cnlCnlCodeList;
	}

	public void setCnlCnlCodeList(List<OptionObjectPair> cnlCnlCodeList) {
		this.cnlCnlCodeList = cnlCnlCodeList;
	}

	public List<CnlCnlCfg> getCnlCnlCfgList() {
		return cnlCnlCfgList;
	}

	public void setCnlCnlCfgList(List<CnlCnlCfg> cnlCnlCfgList) {
		this.cnlCnlCfgList = cnlCnlCfgList;
	}

	public ICnlTransBiz getCnlTransBiz() {
		return cnlTransBiz;
	}

	public void setCnlTransBiz(ICnlTransBiz cnlTransBiz) {
		this.cnlTransBiz = cnlTransBiz;
	}

	public List<OptionObjectPair> getTransTypeList() {
		return transTypeList;
	}

	public void setTransTypeList(List<OptionObjectPair> transTypeList) {
		this.transTypeList = transTypeList;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	// 交易失败数据管理查询页面的 将数据列未处理状态转成待处理状态
	public String saveHandleStatusTransFailed(){
		logger.info("entering action::CnlTransTraceManagerAction.saveHandleStatus()..."+id);
		cnlTransTrace = cnlTransTraceBiz.getCnlTransTraceById(id);
		
		logger.info("transStatus::"+transStatus);
		String handleStatus = Constants.CHECKING_HANDLE_STATUS_PROCESSED; // 改成已处理状态"02"
		cnlTransTrace.setTransStatus(transStatus);
		cnlTransTrace.setHandleStatus(handleStatus);
		// 设置处理时间
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		cnlTransTrace.setUpdateTime(nowTime);
		cnlTransTraceBiz.updateCnlTransTrace(cnlTransTrace);
		
		return SUCCESS;
	}
	
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlTransTraceManagerAction.list()...");
		load();
		return SUCCESS;
	}
	
	@SuppressWarnings("all")
	public void load(){
		logger.info("entering action::CnlTransTraceManagerAction.load()...");
		//交易类型
		transTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		//渠道来源标识
		CnlCnlCfg c = new CnlCnlCfg();
		c.setIsValid(Constants.IS_VALID_VALID);// "01"表示有效的情况下拿到渠道来源标识
		setCnlCnlCfgList(cnlTransBiz.getCnlCnlCodeVal(c));
		cnlCnlCodeList = toOptionPairList(cnlCnlCfgList);
		
		// 处理状态
		handleStatusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CHECKING_HANDLE_STATUS);
		// 错误类型
		errTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__TRADE_FAIL_REASON);
		// 报文处理状态
		msgHandleStatusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__MSG_HANDLE_STATUS);
		
	}
	private List<OptionObjectPair> toOptionPairList(List<CnlCnlCfg> list) {
		logger.info("entering action::CnlTransTraceManagerAction.toOptionPairList()...");
		List<OptionObjectPair> optionPairList = new ArrayList<OptionObjectPair>();
		if(null != list) {
			for(CnlCnlCfg o : list) {
				optionPairList.add(new OptionObjectPair(o.getCnlCnlCode(),o.getCnlSysName()));
			}
		}
		return optionPairList;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlTransTraceManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlTransTraceManagerAction.modify()...");
		cnlTransTrace = cnlTransTraceBiz.getCnlTransTraceById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlTransTraceManagerAction.search()...");

		// 获取交易失败数据管理查询页面的 交易状态（04：表示失败）
		String transStatus = request.getParameter("transStatus");
		// 交易失败数据管理的条件  添加:交易状态是失败
		logger.info("entering method search()...transFailed.jsp came in or not::"+transStatus);
		if(StringUtils.isNotEmpty(transStatus) && StringUtils.equals(transStatus, Constants.TRANS_STATUS_FAIL)){
			logger.info("entering action::进入交易失败页面,设置查询条件：transStatus='04'，表示失败的交易状态:");
			if (null != queryPage) {
				queryPage.addQueryCondition("transStatus", "%" + transStatus + "%");
				queryPage.addLikeSearch("transStatus", transStatus);
			}
		}
		
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		/*Page<CnlTransTrace> result = (Page<CnlTransTrace>) cnlTransTraceBiz
				.findCnlTransTraceByPage(queryPage);
		setResult(result);*/

		//添加条件
		String searchCondition = getSearchCondition();
		queryPage.setHqlString(searchCondition);
		
		if(StringUtils.isNotEmpty(transStatus) && StringUtils.equals(transStatus, Constants.TRANS_STATUS_FAIL)){ // 不为空，表示被银行交易失败页面访问，单表查询数据
			logger.info("entering action::进入交易失败页面,进行查询数据");
			Page<CnlTransTrace> result = (Page<CnlTransTrace>) cnlTransTraceBiz
					.findCnlTransTraceByPageTransFailed(queryPage);
			
			Collection<CnlTransTrace> cnlTransTraceColl = result.getRows();
			handleForResult(cnlTransTraceColl);
			setResult(result);
			/**
			 * 第三步，返回
			 */
			return AJAX_RETURN_TYPE;
		}
		
		//多表查询
		Page<CnlTransTraceDto> result = (Page<CnlTransTraceDto>) cnlTransTraceBiz
				.findCnlTransTraceByPage(queryPage,msg);
		
		Collection<CnlTransTraceDto> cnlTransTraceColl = result.getRows();
		handleForResult(cnlTransTraceColl);
		List<DataDict> msgHandleStatus = dataDictService.list(IDataDictService.DATA_DICT_TYPE__MSG_HANDLE_STATUS); // 报文处理状态
		try {
			CollectionUtils.transformCollection(cnlTransTraceColl, "msgHandleStatus","msgHandleStatus", msgHandleStatus, "code", "name");
		} catch (Exception e) {								
			throw new BizException(e.getMessage());							
		}
		setResult(result);
		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	/** 数字转中文
	 * @param cnlTransTraceColl
	 */
	public void handleForResult(Collection<?> cnlTransTraceColl){
		logger.info("从数据字典加载相应的name值");
		List<DataDict> transType = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);// 交易状态
		List<DataDict> handleStatus = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CHECKING_HANDLE_STATUS); // 处理状态
		List<DataDict> errType = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRADE_FAIL_REASON); // 错误类型--交易失败原因
		List<DataDict> transCurrency = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE); // 交易币种=币种
		List<DataDict> transDc = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_DC); // 交易方向
		List<DataDict> transStatus = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS); // 交易状态
		List<DataDict> bankDebitName = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME); // 收款银行=银行名称
		List<DataDict> bankCreditName = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME); // 付款银行=银行名称
		List<DataDict> bankHandlePriority = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY); // 银行处理优先级
		List<DataDict> isPrinted = dataDictService.list(IDataDictService.DATA_DICT_TYPE__IS_PRINTED); // 打印标志
		List<DataDict> isinGl = dataDictService.list(IDataDictService.DATA_DICT_TYPE__ISIN_GL); // 入账标志
		List<DataDict> paymentCnl = dataDictService.list(IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL); // 支付通道
		
		try {								
			CollectionUtils.transformCollection(cnlTransTraceColl, "transType","transType", transType, "code", "name");		
			CollectionUtils.transformCollection(cnlTransTraceColl, "handleStatus","handleStatus", handleStatus, "code", "name");		
			CollectionUtils.transformCollection(cnlTransTraceColl, "errType","errType", errType, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "transCurrency","transCurrency", transCurrency, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "transDc","transDc", transDc, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "transStatus","transStatus", transStatus, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "bankDebitName","bankDebitName", bankDebitName, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "bankCreditName","bankCreditName", bankCreditName, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "bnakHandlePriority","bnakHandlePriority", bankHandlePriority, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "isPrinted","isPrinted", isPrinted, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "isinGl","isinGl", isinGl, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "bankPmtCnlCode","bankPmtCnlCode", paymentCnl, "code", "name");
			
		} catch (Exception e) {	
			logger.info("从数据字典加载相应的name值的时候失败："+e);
			throw new BizException(e.getMessage());							
		}
	}
	
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CnlTransTraceManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlTransTraceBiz.saveCnlTransTrace(cnlTransTrace);
		}
		// 如果是修改
		else {
			cnlTransTraceBiz.updateCnlTransTrace(cnlTransTrace);
		}

		return SUCCESS;
	}
	
	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CnlTransTraceManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlTransTraceManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlTransTraceBiz.exportCnlTransTrace(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlTransTraceManagerAction.setQueryCondition()...");

		queryPage.addQueryCondition("isValid", Constants.IS_VALID_VALID);
		queryPage.addEqualSearch("isValid", Constants.IS_VALID_VALID);
		// ID
		String id = getSearchFields().get("id");
		if (StringUtils.isNotEmpty(id)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("id", "%" + id + "%");
				queryPage.addLikeSearch("id", id);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("id", "%" + id + "%");
				exportSetting.addLikeSearch("id", id);
			}
		}


		// REQ_INNER_NUM
		String reqInnerNum = getSearchFields().get("reqInnerNum");
		if (StringUtils.isNotEmpty(reqInnerNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqInnerNum", "%" + reqInnerNum + "%");
				queryPage.addLikeSearch("reqInnerNum", reqInnerNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqInnerNum", "%" + reqInnerNum + "%");
				exportSetting.addLikeSearch("reqInnerNum", reqInnerNum);
			}
		}


		// CUST_CODE
		String custCode = getSearchFields().get("custCode");
		if (StringUtils.isNotEmpty(custCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("custCode", "%" + custCode + "%");
				queryPage.addLikeSearch("custCode", custCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("custCode", "%" + custCode + "%");
				exportSetting.addLikeSearch("custCode", custCode);
			}
		}


		// CNL_CUST_CODE
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlCustCode", "%" + cnlCustCode + "%");
				queryPage.addLikeSearch("cnlCustCode", cnlCustCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlCustCode", "%" + cnlCustCode + "%");
				exportSetting.addLikeSearch("cnlCustCode", cnlCustCode);
			}
		}


		// TRANS_ORDER_NUM
		String reqNum = getSearchFields().get("reqNum");
		if (StringUtils.isNotEmpty(reqNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqNum", "%" + reqNum + "%");
				queryPage.addLikeSearch("reqNum", reqNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqNum", "%" + reqNum + "%");
				exportSetting.addLikeSearch("reqNum", reqNum);
			}
		}


		// REQ_BATCH
		Long reqBatch = Long.getLong(getSearchFields().get("reqBatch"));
		if (null != reqBatch) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqBatch", reqBatch);
				queryPage.addEqualSearch("reqBatch", reqBatch);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("reqBatch", reqBatch);
				queryPage.addEqualSearch("reqBatch", reqBatch);
			}
		}


		// TRANS_TYPE
		String transType = getSearchFields().get("transType");
		if (StringUtils.isNotEmpty(transType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transType", "%" + transType + "%");
				queryPage.addLikeSearch("transType", transType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transType", "%" + transType + "%");
				exportSetting.addLikeSearch("transType", transType);
			}
		}


		// TRANS_DC
		String transDc = getSearchFields().get("transDc");
		if (StringUtils.isNotEmpty(transDc)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transDc", "%" + transDc + "%");
				queryPage.addLikeSearch("transDc", transDc);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transDc", "%" + transDc + "%");
				exportSetting.addLikeSearch("transDc", transDc);
			}
		}


		// TRADE_SUB_TYPE
		String tradeSubType = getSearchFields().get("tradeSubType");
		if (StringUtils.isNotEmpty(tradeSubType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("tradeSubType", "%" + tradeSubType + "%");
				queryPage.addLikeSearch("tradeSubType", tradeSubType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("tradeSubType", "%" + tradeSubType + "%");
				exportSetting.addLikeSearch("tradeSubType", tradeSubType);
			}
		}


		// TRANS_CURRENCY
		String transCurrency = getSearchFields().get("transCurrency");
		if (StringUtils.isNotEmpty(transCurrency)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transCurrency", "%" + transCurrency + "%");
				queryPage.addLikeSearch("transCurrency", transCurrency);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transCurrency", "%" + transCurrency + "%");
				exportSetting.addLikeSearch("transCurrency", transCurrency);
			}
		}


		// TRANS_AMOUNT
		Long transAmount = Long.getLong(getSearchFields().get("transAmount"));
		if (null != transAmount) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transAmount", transAmount);
				queryPage.addEqualSearch("transAmount", transAmount);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("transAmount", transAmount);
				queryPage.addEqualSearch("transAmount", transAmount);
			}
		}


		// TRNAS_STATUS
		String trnasStatus = getSearchFields().get("trnasStatus");
		if (StringUtils.isNotEmpty(trnasStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("trnasStatus", "%" + trnasStatus + "%");
				queryPage.addLikeSearch("trnasStatus", trnasStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("trnasStatus", "%" + trnasStatus + "%");
				exportSetting.addLikeSearch("trnasStatus", trnasStatus);
			}
		}


		// TRANS_DATE
		String transDate = getSearchFields().get("transDate");
		if (StringUtils.isNotEmpty(transDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transDate",
						DateUtils.convert(transDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transDate",
						DateUtils.convert(transDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// TRNAS_TIME
		String trnasTime = getSearchFields().get("trnasTime");
		if (StringUtils.isNotEmpty(trnasTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("trnasTime",
						DateUtils.convert(trnasTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("trnasTime",
						DateUtils.convert(trnasTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// TRANS_RATE
		Long transRate = Long.getLong(getSearchFields().get("transRate"));
		if (null != transRate) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transRate", transRate);
				queryPage.addEqualSearch("transRate", transRate);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("transRate", transRate);
				queryPage.addEqualSearch("transRate", transRate);
			}
		}


		// TRANS_COMMENTS
		String transComments = getSearchFields().get("transComments");
		if (StringUtils.isNotEmpty(transComments)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transComments", "%" + transComments + "%");
				queryPage.addLikeSearch("transComments", transComments);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transComments", "%" + transComments + "%");
				exportSetting.addLikeSearch("transComments", transComments);
			}
		}


		// BANK_ACCEPTE_TIME
		String bankAccepteTime = getSearchFields().get("bankAccepteTime");
		if (StringUtils.isNotEmpty(bankAccepteTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankAccepteTime",
						DateUtils.convert(bankAccepteTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankAccepteTime",
						DateUtils.convert(bankAccepteTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// BANK_TRANS_NUM
		String bankTransNum = getSearchFields().get("bankTransNum");
		if (StringUtils.isNotEmpty(bankTransNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankTransNum", "%" + bankTransNum + "%");
				queryPage.addLikeSearch("bankTransNum", bankTransNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankTransNum", "%" + bankTransNum + "%");
				exportSetting.addLikeSearch("bankTransNum", bankTransNum);
			}
		}


		// BANK_HANDLE_NUM
		String bankHandleNum = getSearchFields().get("bankHandleNum");
		if (StringUtils.isNotEmpty(bankHandleNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankHandleNum", "%" + bankHandleNum + "%");
				queryPage.addLikeSearch("bankHandleNum", bankHandleNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankHandleNum", "%" + bankHandleNum + "%");
				exportSetting.addLikeSearch("bankHandleNum", bankHandleNum);
			}
		}


		// BANK_RETURN_TIME
		String bankReturnTime = getSearchFields().get("bankReturnTime");
		if (StringUtils.isNotEmpty(bankReturnTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReturnTime",
						DateUtils.convert(bankReturnTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReturnTime",
						DateUtils.convert(bankReturnTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// BANK_RETURN_RESULT
		String bankReturnResult = getSearchFields().get("bankReturnResult");
		if (StringUtils.isNotEmpty(bankReturnResult)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReturnResult", "%" + bankReturnResult + "%");
				queryPage.addLikeSearch("bankReturnResult", bankReturnResult);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReturnResult", "%" + bankReturnResult + "%");
				exportSetting.addLikeSearch("bankReturnResult", bankReturnResult);
			}
		}


		// BANK_PMT_CNL_TYPE
		String bankPmtCnlType = getSearchFields().get("bankPmtCnlType");
		if (StringUtils.isNotEmpty(bankPmtCnlType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankPmtCnlType", "%" + bankPmtCnlType + "%");
				queryPage.addLikeSearch("bankPmtCnlType", bankPmtCnlType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankPmtCnlType", "%" + bankPmtCnlType + "%");
				exportSetting.addLikeSearch("bankPmtCnlType", bankPmtCnlType);
			}
		}


		// BANK_PMT_CNL_CODE
		String bankPmtCnlCode = getSearchFields().get("bankPmtCnlCode");
		if (StringUtils.isNotEmpty(bankPmtCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankPmtCnlCode", "%" + bankPmtCnlCode + "%");
				queryPage.addLikeSearch("bankPmtCnlCode", bankPmtCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankPmtCnlCode", "%" + bankPmtCnlCode + "%");
				exportSetting.addLikeSearch("bankPmtCnlCode", bankPmtCnlCode);
			}
		}


		// CNL_CNL_CODE
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlCnlCode", "%" + cnlCnlCode + "%");
				queryPage.addLikeSearch("cnlCnlCode", cnlCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlCnlCode", "%" + cnlCnlCode + "%");
				exportSetting.addLikeSearch("cnlCnlCode", cnlCnlCode);
			}
		}


		// ISIN_GL
		String isinGl = getSearchFields().get("isinGl");
		if (StringUtils.isNotEmpty(isinGl)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isinGl", "%" + isinGl + "%");
				queryPage.addLikeSearch("isinGl", isinGl);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isinGl", "%" + isinGl + "%");
				exportSetting.addLikeSearch("isinGl", isinGl);
			}
		}


		// IS_PRINTED
		String isPrinted = getSearchFields().get("isPrinted");
		if (StringUtils.isNotEmpty(isPrinted)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isPrinted", "%" + isPrinted + "%");
				queryPage.addLikeSearch("isPrinted", isPrinted);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isPrinted", "%" + isPrinted + "%");
				exportSetting.addLikeSearch("isPrinted", isPrinted);
			}
		}


		// BANK_CREDIT_NAME
		String bankCreditName = getSearchFields().get("bankCreditName");
		if (StringUtils.isNotEmpty(bankCreditName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditName", "%" + bankCreditName + "%");
				queryPage.addLikeSearch("bankCreditName", bankCreditName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditName", "%" + bankCreditName + "%");
				exportSetting.addLikeSearch("bankCreditName", bankCreditName);
			}
		}


		// BANK_CREDIT_CUST_NAME
		String bankCreditCustName = getSearchFields().get("bankCreditCustName");
		if (StringUtils.isNotEmpty(bankCreditCustName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditCustName", "%" + bankCreditCustName + "%");
				queryPage.addLikeSearch("bankCreditCustName", bankCreditCustName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditCustName", "%" + bankCreditCustName + "%");
				exportSetting.addLikeSearch("bankCreditCustName", bankCreditCustName);
			}
		}


		// BANK_CREDIT_ACNT_CODE
		String bankCreditAcntCode = getSearchFields().get("bankCreditAcntCode");
		if (StringUtils.isNotEmpty(bankCreditAcntCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditAcntCode", "%" + bankCreditAcntCode + "%");
				queryPage.addLikeSearch("bankCreditAcntCode", bankCreditAcntCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditAcntCode", "%" + bankCreditAcntCode + "%");
				exportSetting.addLikeSearch("bankCreditAcntCode", bankCreditAcntCode);
			}
		}


		// BANK_DEBIT_NAME
		String bankDebitName = getSearchFields().get("bankDebitName");
		if (StringUtils.isNotEmpty(bankDebitName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitName", "%" + bankDebitName + "%");
				queryPage.addLikeSearch("bankDebitName", bankDebitName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitName", "%" + bankDebitName + "%");
				exportSetting.addLikeSearch("bankDebitName", bankDebitName);
			}
		}


		// BANK_DEBIT_CUST_NAME
		String bankDebitCustName = getSearchFields().get("bankDebitCustName");
		if (StringUtils.isNotEmpty(bankDebitCustName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitCustName", "%" + bankDebitCustName + "%");
				queryPage.addLikeSearch("bankDebitCustName", bankDebitCustName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitCustName", "%" + bankDebitCustName + "%");
				exportSetting.addLikeSearch("bankDebitCustName", bankDebitCustName);
			}
		}


		// BANK_DEBIT_ACNT_CODE
		String bankDebitAcntCode = getSearchFields().get("bankDebitAcntCode");
		if (StringUtils.isNotEmpty(bankDebitAcntCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitAcntCode", "%" + bankDebitAcntCode + "%");
				queryPage.addLikeSearch("bankDebitAcntCode", bankDebitAcntCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitAcntCode", "%" + bankDebitAcntCode + "%");
				exportSetting.addLikeSearch("bankDebitAcntCode", bankDebitAcntCode);
			}
		}


		// BANK_REQ_TRNAS_DATE
		String bankReqTrnasDate = getSearchFields().get("bankReqTrnasDate");
		if (StringUtils.isNotEmpty(bankReqTrnasDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReqTrnasDate",
						DateUtils.convert(bankReqTrnasDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReqTrnasDate",
						DateUtils.convert(bankReqTrnasDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// BNAK_SERVICE_FEE_ACT
		String bnakServiceFeeAct = getSearchFields().get("bnakServiceFeeAct");
		if (StringUtils.isNotEmpty(bnakServiceFeeAct)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bnakServiceFeeAct", "%" + bnakServiceFeeAct + "%");
				queryPage.addLikeSearch("bnakServiceFeeAct", bnakServiceFeeAct);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bnakServiceFeeAct", "%" + bnakServiceFeeAct + "%");
				exportSetting.addLikeSearch("bnakServiceFeeAct", bnakServiceFeeAct);
			}
		}


		// BANK_REQ_TRANS_TIME
		String bankReqTransTime = getSearchFields().get("bankReqTransTime");
		if (StringUtils.isNotEmpty(bankReqTransTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReqTransTime",
						DateUtils.convert(bankReqTransTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReqTransTime",
						DateUtils.convert(bankReqTransTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// BNAK_HANDLE_PRIORITY
		String bnakHandlePriority = getSearchFields().get("bnakHandlePriority");
		if (StringUtils.isNotEmpty(bnakHandlePriority)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bnakHandlePriority", "%" + bnakHandlePriority + "%");
				queryPage.addLikeSearch("bnakHandlePriority", bnakHandlePriority);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bnakHandlePriority", "%" + bnakHandlePriority + "%");
				exportSetting.addLikeSearch("bnakHandlePriority", bnakHandlePriority);
			}
		}


		// ERR_TYPE
		String errType = getSearchFields().get("errType");
		if (StringUtils.isNotEmpty(errType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errType", "%" + errType + "%");
				queryPage.addLikeSearch("errType", errType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errType", "%" + errType + "%");
				exportSetting.addLikeSearch("errType", errType);
			}
		}


		// ERR_CODE
		String errCode = getSearchFields().get("errCode");
		if (StringUtils.isNotEmpty(errCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errCode", "%" + errCode + "%");
				queryPage.addLikeSearch("errCode", errCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errCode", "%" + errCode + "%");
				exportSetting.addLikeSearch("errCode", errCode);
			}
		}


		// ERR_MSG
		String errMsg = getSearchFields().get("errMsg");
		if (StringUtils.isNotEmpty(errMsg)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errMsg", "%" + errMsg + "%");
				queryPage.addLikeSearch("errMsg", errMsg);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errMsg", "%" + errMsg + "%");
				exportSetting.addLikeSearch("errMsg", errMsg);
			}
		}


		// HANDLE_STATUS
		String handleStatus = getSearchFields().get("handleStatus");
		if (StringUtils.isNotEmpty(handleStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("handleStatus", "%" + handleStatus + "%");
				queryPage.addLikeSearch("handleStatus", handleStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("handleStatus", "%" + handleStatus + "%");
				exportSetting.addLikeSearch("handleStatus", handleStatus);
			}
		}


		// HANDLE_MSG
		String handleMsg = getSearchFields().get("handleMsg");
		if (StringUtils.isNotEmpty(handleMsg)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("handleMsg", "%" + handleMsg + "%");
				queryPage.addLikeSearch("handleMsg", handleMsg);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("handleMsg", "%" + handleMsg + "%");
				exportSetting.addLikeSearch("handleMsg", handleMsg);
			}
		}


		// HANDLE_TIME
		String handleTime = getSearchFields().get("handleTime");
		if (StringUtils.isNotEmpty(handleTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("handleTime",
						DateUtils.convert(handleTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("handleTime",
						DateUtils.convert(handleTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// OPERATOR
		String operator = getSearchFields().get("operator");
		if (StringUtils.isNotEmpty(operator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("operator", "%" + operator + "%");
				queryPage.addLikeSearch("operator", operator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("operator", "%" + operator + "%");
				exportSetting.addLikeSearch("operator", operator);
			}
		}


		// IS_VALID
		String isValid = getSearchFields().get("isValid");
		if (StringUtils.isNotEmpty(isValid)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isValid", "%" + isValid + "%");
				queryPage.addLikeSearch("isValid", isValid);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isValid", "%" + isValid + "%");
				exportSetting.addLikeSearch("isValid", isValid);
			}
		}


		// CREATE_TIME
		String createTime = getSearchFields().get("createTime");
		if (StringUtils.isNotEmpty(createTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// UPDATE_TIME
		String updateTime = getSearchFields().get("updateTime");
		if (StringUtils.isNotEmpty(updateTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// CREATOR
		String creator = getSearchFields().get("creator");
		if (StringUtils.isNotEmpty(creator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("creator", "%" + creator + "%");
				queryPage.addLikeSearch("creator", creator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("creator", "%" + creator + "%");
				exportSetting.addLikeSearch("creator", creator);
			}
		}


		// UPDATOR
		String updator = getSearchFields().get("updator");
		if (StringUtils.isNotEmpty(updator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updator", "%" + updator + "%");
				queryPage.addLikeSearch("updator", updator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updator", "%" + updator + "%");
				exportSetting.addLikeSearch("updator", updator);
			}
		}

		// timeType 时间类型
		String timeType = getSearchFields().get("timeType");
		String startTime = getSearchFields().get("startTime");
		String endTime = getSearchFields().get("endTime");
		logger.info("entering setQueryCondition()...时间类型timeType::"+timeType);
		if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			/* 01-交易时间
			 * 02-接入银行网关时间
			 * 03-处理时间
			 * 04-银行到账时间
			 * 05-银行请求时间
			 */
			String searchTime = "";
			if(timeType.equals(Constants.TIME_TYPE_TRANSACTION_TIME)){// 01-交易时间
				searchTime = "transTime";
			}else if(timeType.equals(Constants.TIME_TYPE_INTERFACING_TIME)){// 02-接入银行网关时间
				searchTime = "bankAccepteTime";
			}else if(timeType.equals(Constants.TIME_TYPE_HANDLING_TIME)){// 03-处理时间
				searchTime = "handleTime";
			}
			if(null != queryPage){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					Date stime = sdf.parse(startTime);
					Date etime = sdf.parse(endTime);
					
					queryPage.addBetweenSearch(searchTime, stime, etime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public ICnlTransTraceBiz getCnlTransTraceBiz() {
		logger.info("entering action::CnlTransTraceManagerAction.getCnlTransTraceBiz()...");
		return cnlTransTraceBiz;
	}

	public void setCnlTransTraceBiz(ICnlTransTraceBiz cnlTransTraceBiz) {
		logger.info("entering action::CnlTransTraceManagerAction.setCnlTransTraceBiz()...");
		this.cnlTransTraceBiz = cnlTransTraceBiz;
	}

	public CnlTransTrace getCnlTransTrace() {
		logger.info("entering action::CnlTransTraceManagerAction.getCnlTransTrace()...");
		return cnlTransTrace;
	}

	public void setCnlTransTrace(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceManagerAction.setCnlTransTrace()...");
		this.cnlTransTrace = cnlTransTrace;
	}

	public String getId() {
		logger.info("entering action::CnlTransTraceManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlTransTraceManagerAction.setId()...");
		this.id = id;
	}

	
	/**
	 * 为hql语句生成查询条件
	 */
	public String getSearchCondition(){
		logger.info("entering action::CnlTransTraceManagerAction.getSearchCondition()...银行接口数据查询页面：为hql语句生成查询条件");
		//清除condition
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		
		
		condition.append(" and t.isValid = ? ");
		queryPage.addQueryCondition("isValid", Constants.IS_VALID_VALID);
		
		//reqNum 渠道订单号
		String reqNum = getSearchFields().get("reqNum");
		if (StringUtils.isNotEmpty(reqNum)) {
			condition.append(" and t.reqNum like ? ");
			queryPage.addQueryCondition("reqNum", "%"+reqNum+"%");
		}
		
		//reqInnerNum 系统申请流水单号
		String reqInnerNum = getSearchFields().get("reqInnerNum");
		if (StringUtils.isNotEmpty(reqInnerNum)) {
			condition.append(" and t.reqInnerNum like ? ");
			queryPage.addQueryCondition("reqInnerNum", "%"+reqInnerNum+"%");
		}
		
		//bankTransNum 银行交易流水号
		String bankTransNum = getSearchFields().get("bankTransNum");
		if (StringUtils.isNotEmpty(bankTransNum)) {
			condition.append(" and t.bankTransNum like ? ");
			queryPage.addQueryCondition("bankTransNum", "%"+bankTransNum+"%");
		}
		
		//transNum 系统交易流水号
		String transNum = getSearchFields().get("transNum");
		if (StringUtils.isNotEmpty(transNum)) {
			condition.append(" and d.transNum like ? ");
			queryPage.addQueryCondition("transNum", "%"+transNum+"%");
		}
		
		// timeType 时间类型
		String timeType = getSearchFields().get("timeType");
		String startTime = getSearchFields().get("startTime");
		String endTime = getSearchFields().get("endTime");
		
		//msgHandleStatus 报文处理状态
		String msgHandleStatus = getSearchFields().get("msgHandleStatus");
		if (StringUtils.isNotEmpty(msgHandleStatus)) {
			
			/* 		
			 * 	报文处理状态的判断：
				01-已发送：存在请求时间且不存在接入银行网关时间
				02-未发送：不存在请求时间
				03-已处理：存在接入银行网关时间
			*/
			
			if(msgHandleStatus.equals(Constants.MSG_HANDLE_STATUS_SEND)){ // 01-已发送
				msg=Constants.MSG_HANDLE_STATUS_SEND;
				condition.append(" and t.bankAccepteTime is null and t.bankReqTime is not null ");
			}else if(msgHandleStatus.equals(Constants.MSG_HANDLE_STATUS_UNSEND)){ // 02-未发送
				msg=Constants.MSG_HANDLE_STATUS_UNSEND;
				timeType=null;
				startTime=null;
				endTime=null;
				condition.append(" and t.bankAccepteTime is null and t.bankReqTime is null ");
			}else if(msgHandleStatus.equals(Constants.MSG_HANDLE_STATUS_HANDLED)){ // 03-已处理
				msg=Constants.MSG_HANDLE_STATUS_HANDLED;
				condition.append(" and t.bankAccepteTime is not null and t.bankReqTime is not null ");
			}
		}
		
		// 开始时间和结束时间都输入
		if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			/* 01-交易时间
			 * 02-接入银行网关时间
			 * 03-处理时间
			 * 04-银行到账时间
			 * 05-银行请求时间
			 */
			String searchTime = "";
			if(timeType.equals(Constants.TIME_TYPE_BANK_REQ_TIME)){ // 05-银行请求时间
				searchTime = "bankReqTime";
			}else if(timeType.equals(Constants.TIME_TYPE_INTERFACING_TIME)){ // 02-接入银行网关时间
				searchTime = "bankAccepteTime";
			}else if(timeType.equals(Constants.TIME_TYPE_TRANSACTION_TIME)){ // 01-交易时间
				searchTime = "transTime";
			}else if(timeType.equals(Constants.TIME_TYPE_RECORD_TIME)){ // 04-银行到账时间
				searchTime = "bankReturnTime";
			}else if(timeType.equals(Constants.TIME_TYPE_HANDLING_TIME)){ // 03-处理时间 
				searchTime = "handleTime";
			}
			if(null != queryPage){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					Date stime = sdf.parse(startTime);
					Date etime = sdf.parse(endTime);
					
					condition.append(" and t."+searchTime+" >= ? ");
					queryPage.addQueryCondition(searchTime, stime);
					
					condition.append(" and t."+searchTime+" <= ? ");
					queryPage.addQueryCondition(searchTime, etime);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			/*String errType = getSearchFields().get("errType");
			if (!StringUtils.isNotEmpty(errType)) {
				condition.append(" and t.errType is not null ");
			}*/
		}
		
		
		//transType 接口类型
		String transType = getSearchFields().get("transType");
		if (StringUtils.isNotEmpty(transType)) {
			condition.append(" and t.transType= '"+transType+"'");
//			queryPage.addQueryCondition("transType", transType);
		}
		
		
		// cnlCnlCode 渠道来源标识
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			condition.append(" and t.cnlCnlCode= '"+cnlCnlCode+"'");
//			queryPage.addQueryCondition("bankReturnResult", bankReturnResult);
		}
		
		// handleStatus 处理状态
		String handleStatus = getSearchFields().get("handleStatus");
		if (StringUtils.isNotEmpty(handleStatus)) {
			condition.append(" and t.handleStatus= '"+handleStatus+"'");
//			queryPage.addQueryCondition("bankReturnResult", bankReturnResult);
		}
		
		// errType 错误类型
		String errType = getSearchFields().get("errType");
		if (StringUtils.isNotEmpty(errType)) {
			condition.append(" and t.errType like ? ");
			queryPage.addQueryCondition("errType", "%"+errType+"%");
		}
		
		return condition.toString();
	}
	
}
