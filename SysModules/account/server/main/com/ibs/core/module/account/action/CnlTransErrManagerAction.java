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
import com.ibs.core.module.account.biz.ICnlTransErrBiz;
import com.ibs.core.module.account.domain.CnlTransErr;
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
 * @comments	: code generated based on database T_CNL_TRANS_ERR
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlTransErrManagerAction extends CrudBaseAction {

	private ICnlTransErrBiz cnlTransErrBiz;
	
	private String id;
	private CnlTransErr cnlTransErr;

	// 渠道来源标识
	private List<CnlCnlCfg> cnlCnlCfgList;
	// 渠道来源标识
	private List<OptionObjectPair> cnlCnlCodeList;
	// 处理状态
	private List<OptionObjectPair> handleStatusList; 
	// 银行对账异常错误类型
	private List<OptionObjectPair> errTypeList;
	
	
	private ICnlTransBiz cnlTransBiz;
	private IDataDictBiz dataDictBiz;
	private IDataDictService dataDictService;
	
	private String handleStatus;
	
	public List<OptionObjectPair> getErrTypeList() {
		return errTypeList;
	}

	public void setErrTypeList(List<OptionObjectPair> errTypeList) {
		this.errTypeList = errTypeList;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public ICnlTransBiz getCnlTransBiz() {
		return cnlTransBiz;
	}

	public void setCnlTransBiz(ICnlTransBiz cnlTransBiz) {
		this.cnlTransBiz = cnlTransBiz;
	}

	public List<CnlCnlCfg> getCnlCnlCfgList() {
		return cnlCnlCfgList;
	}

	public void setCnlCnlCfgList(List<CnlCnlCfg> cnlCnlCfgList) {
		this.cnlCnlCfgList = cnlCnlCfgList;
	}

	public List<OptionObjectPair> getCnlCnlCodeList() {
		return cnlCnlCodeList;
	}

	public void setCnlCnlCodeList(List<OptionObjectPair> cnlCnlCodeList) {
		this.cnlCnlCodeList = cnlCnlCodeList;
	}

	public List<OptionObjectPair> getHandleStatusList() {
		return handleStatusList;
	}

	public void setHandleStatusList(List<OptionObjectPair> handleStatusList) {
		this.handleStatusList = handleStatusList;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	
	public String saveHandleStatusErr(){
		logger.info("entering action::CnlTransTraceManagerAction.saveHandleStatus()...将一条数据列处理状态改为已处理状态...id::"+id);
		cnlTransErr = cnlTransErrBiz.getCnlTransErrById(id);
		
		logger.info("entering action::CnlTransTraceManagerAction.saveHandleStatus()...将一条数据列处理状态改为已处理状态...handleStatus::"+handleStatus);
		cnlTransErr.setHandleStatus(handleStatus);
		// 设置处理时间
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		cnlTransErr.setUpdateTime(nowTime);
		
		cnlTransErrBiz.updateCnlTransErr(cnlTransErr);
		
		return SUCCESS;
	}
	
	
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlTransErrManagerAction.list()...");
		load();
		return SUCCESS;
	}
	@SuppressWarnings("all")
	public void load(){
		logger.info("entering action::CnlTransTraceManagerAction.load()...从数据字典加载下拉框的值");
		//渠道来源标识
		CnlCnlCfg c = new CnlCnlCfg();
		c.setIsValid(Constants.IS_VALID_VALID);// "01"表示有效的情况下拿到渠道来源标识
		setCnlCnlCfgList(cnlTransBiz.getCnlCnlCodeVal(c));
		cnlCnlCodeList = toOptionPairList(cnlCnlCfgList);
		
		// 处理状态
		handleStatusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CHECKING_HANDLE_STATUS);
		// 错误类型
		errTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CHECKING_EXCEPTION_TYPE);
		
	}
	private List<OptionObjectPair> toOptionPairList(List<CnlCnlCfg> list) {
		logger.info("entering action::CnlTransTraceManagerAction.toOptionPairList()...从数据字典加载下拉框的值");
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
		logger.info("entering action::CnlTransErrManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlTransErrManagerAction.modify()...");
		cnlTransErr = cnlTransErrBiz.getCnlTransErrById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlTransErrManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlTransErr> result = (Page<CnlTransErr>) cnlTransErrBiz
				.findCnlTransErrByPage(queryPage);
		
		logger.info("entering action::CnlTransTraceManagerAction.search()...将查询出来的code数据从数据字典加载转成相应的name数据");
		Collection<CnlTransErr> cnlTransErrColl = result.getRows();
		List<DataDict> transTypeList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);// 交易状态
		List<DataDict> handleStatusList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CHECKING_HANDLE_STATUS); // 处理状态
		List<DataDict> errTypeList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CHECKING_EXCEPTION_TYPE); // 错误类型
		List<DataDict> transCurrencyList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE); // 交易币种=币种
		List<DataDict> transDcList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_DC); // 交易方向
		List<DataDict> transStatusList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS); // 交易状态
		List<DataDict> bankDebitNameList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME); // 收款银行=银行名称
		List<DataDict> bankCreditNameList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME); // 付款银行=银行名称
		List<DataDict> bankHandlePriorityList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY); // 银行处理优先级
		List<DataDict> isPrintedList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__IS_PRINTED); // 打印标志
		List<DataDict> isinGl = dataDictService.list(IDataDictService.DATA_DICT_TYPE__ISIN_GL); // 入账标志
		List<DataDict> paymentCnl = dataDictService.list(IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL); // 支付通道
		
		try {								
			CollectionUtils.transformCollection(cnlTransErrColl, "transType","transType", transTypeList, "code", "name");		
			CollectionUtils.transformCollection(cnlTransErrColl, "handleStatus","handleStatus", handleStatusList, "code", "name");		
			CollectionUtils.transformCollection(cnlTransErrColl, "errType","errType", errTypeList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "transCurrency","transCurrency", transCurrencyList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "transDc","transDc", transDcList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "transStatus","transStatus", transStatusList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "bankDebitName","bankDebitName", bankDebitNameList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "bankCreditName","bankCreditName", bankCreditNameList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "bnakHandlePriority","bnakHandlePriority", bankHandlePriorityList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "isPrinted","isPrinted", isPrintedList, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "isinGl","isinGl", isinGl, "code", "name");
			CollectionUtils.transformCollection(cnlTransErrColl, "bankPmtCnlCode","bankPmtCnlCode", paymentCnl, "code", "name");
			
		} catch (Exception e) {		
			logger.info("entering action::CnlTransTraceManagerAction.search()...将查询出来的code数据从数据字典加载转成相应的name数据失败");
			throw new BizException(e.getMessage());							
		}	
		
		
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CnlTransErrManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlTransErrBiz.saveCnlTransErr(cnlTransErr);
		}
		// 如果是修改
		else {
			cnlTransErrBiz.updateCnlTransErr(cnlTransErr);
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
		logger.info("entering action::CnlTransErrManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlTransErrManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlTransErrBiz.exportCnlTransErr(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlTransErrManagerAction.setQueryCondition()...");

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


		// TRANS_NUM
		String transNum = getSearchFields().get("transNum");
		if (StringUtils.isNotEmpty(transNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transNum", "%" + transNum + "%");
				queryPage.addLikeSearch("transNum", transNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transNum", "%" + transNum + "%");
				exportSetting.addLikeSearch("transNum", transNum);
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


		// FIX_REQ_NUM
		String fixReqNum = getSearchFields().get("fixReqNum");
		if (StringUtils.isNotEmpty(fixReqNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("fixReqNum", "%" + fixReqNum + "%");
				queryPage.addLikeSearch("fixReqNum", fixReqNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("fixReqNum", "%" + fixReqNum + "%");
				exportSetting.addLikeSearch("fixReqNum", fixReqNum);
			}
		}


		// REQ_NUM
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


		// STL_NUM
		String stlNum = getSearchFields().get("stlNum");
		if (StringUtils.isNotEmpty(stlNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("stlNum", "%" + stlNum + "%");
				queryPage.addLikeSearch("stlNum", stlNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("stlNum", "%" + stlNum + "%");
				exportSetting.addLikeSearch("stlNum", stlNum);
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


		// TRANS_SUB_TYPE
		String transSubType = getSearchFields().get("transSubType");
		if (StringUtils.isNotEmpty(transSubType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transSubType", "%" + transSubType + "%");
				queryPage.addLikeSearch("transSubType", transSubType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transSubType", "%" + transSubType + "%");
				exportSetting.addLikeSearch("transSubType", transSubType);
			}
		}


		// TRANS_ORDER_NUM
		String transOrderNum = getSearchFields().get("transOrderNum");
		if (StringUtils.isNotEmpty(transOrderNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transOrderNum", "%" + transOrderNum + "%");
				queryPage.addLikeSearch("transOrderNum", transOrderNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transOrderNum", "%" + transOrderNum + "%");
				exportSetting.addLikeSearch("transOrderNum", transOrderNum);
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


		// TRANS_STATUS
//		String transStatus = getSearchFields().get("transStatus");
		String transStatus = Constants.TRANS_STATUS_FAIL;// 04-只查询交易状态是失败的数据
		if (true) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transStatus", "%" + transStatus + "%");
				queryPage.addLikeSearch("transStatus", transStatus);
			}
		}


		// TRANS_LATEST_AMOUNT
		Long transLatestAmount = Long.getLong(getSearchFields().get("transLatestAmount"));
		if (null != transLatestAmount) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transLatestAmount", transLatestAmount);
				queryPage.addEqualSearch("transLatestAmount", transLatestAmount);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("transLatestAmount", transLatestAmount);
				queryPage.addEqualSearch("transLatestAmount", transLatestAmount);
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


		// TRANS_TIME
		String transTime = getSearchFields().get("transTime");
		if (StringUtils.isNotEmpty(transTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transTime",
						DateUtils.convert(transTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transTime",
						DateUtils.convert(transTime,
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


		// BANK_REQ_TIME
		String bankReqTime = getSearchFields().get("bankReqTime");
		if (StringUtils.isNotEmpty(bankReqTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReqTime",
						DateUtils.convert(bankReqTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReqTime",
						DateUtils.convert(bankReqTime,
								DateUtils.DATE_FORMAT));
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


		// PRINTED_TIME
		String printedTime = getSearchFields().get("printedTime");
		if (StringUtils.isNotEmpty(printedTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("printedTime",
						DateUtils.convert(printedTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("printedTime",
						DateUtils.convert(printedTime,
								DateUtils.DATE_FORMAT));
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


		// BANK_CREDIT_CODE
		String bankCreditCode = getSearchFields().get("bankCreditCode");
		if (StringUtils.isNotEmpty(bankCreditCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditCode", "%" + bankCreditCode + "%");
				queryPage.addLikeSearch("bankCreditCode", bankCreditCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditCode", "%" + bankCreditCode + "%");
				exportSetting.addLikeSearch("bankCreditCode", bankCreditCode);
			}
		}


		// BANK_CREDIT_BRANCH_NAME
		String bankCreditBranchName = getSearchFields().get("bankCreditBranchName");
		if (StringUtils.isNotEmpty(bankCreditBranchName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditBranchName", "%" + bankCreditBranchName + "%");
				queryPage.addLikeSearch("bankCreditBranchName", bankCreditBranchName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditBranchName", "%" + bankCreditBranchName + "%");
				exportSetting.addLikeSearch("bankCreditBranchName", bankCreditBranchName);
			}
		}


		// BANK_CREDIT_BRANCH_CODE
		String bankCreditBranchCode = getSearchFields().get("bankCreditBranchCode");
		if (StringUtils.isNotEmpty(bankCreditBranchCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditBranchCode", "%" + bankCreditBranchCode + "%");
				queryPage.addLikeSearch("bankCreditBranchCode", bankCreditBranchCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditBranchCode", "%" + bankCreditBranchCode + "%");
				exportSetting.addLikeSearch("bankCreditBranchCode", bankCreditBranchCode);
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


		// BANK_CREDIT_CARD_NUM
		String bankCreditCardNum = getSearchFields().get("bankCreditCardNum");
		if (StringUtils.isNotEmpty(bankCreditCardNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditCardNum", "%" + bankCreditCardNum + "%");
				queryPage.addLikeSearch("bankCreditCardNum", bankCreditCardNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditCardNum", "%" + bankCreditCardNum + "%");
				exportSetting.addLikeSearch("bankCreditCardNum", bankCreditCardNum);
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


		// BANK_DEBIT_CODE
		String bankDebitCode = getSearchFields().get("bankDebitCode");
		if (StringUtils.isNotEmpty(bankDebitCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitCode", "%" + bankDebitCode + "%");
				queryPage.addLikeSearch("bankDebitCode", bankDebitCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitCode", "%" + bankDebitCode + "%");
				exportSetting.addLikeSearch("bankDebitCode", bankDebitCode);
			}
		}


		// BANK_DEBIT_BRANCH_NAME
		String bankDebitBranchName = getSearchFields().get("bankDebitBranchName");
		if (StringUtils.isNotEmpty(bankDebitBranchName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitBranchName", "%" + bankDebitBranchName + "%");
				queryPage.addLikeSearch("bankDebitBranchName", bankDebitBranchName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitBranchName", "%" + bankDebitBranchName + "%");
				exportSetting.addLikeSearch("bankDebitBranchName", bankDebitBranchName);
			}
		}


		// BANK_DEBIT_BRANCH_CODE
		String bankDebitBranchCode = getSearchFields().get("bankDebitBranchCode");
		if (StringUtils.isNotEmpty(bankDebitBranchCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitBranchCode", "%" + bankDebitBranchCode + "%");
				queryPage.addLikeSearch("bankDebitBranchCode", bankDebitBranchCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitBranchCode", "%" + bankDebitBranchCode + "%");
				exportSetting.addLikeSearch("bankDebitBranchCode", bankDebitBranchCode);
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


		// BANK_DEBIT_CARD_NUM
		String bankDebitCardNum = getSearchFields().get("bankDebitCardNum");
		if (StringUtils.isNotEmpty(bankDebitCardNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitCardNum", "%" + bankDebitCardNum + "%");
				queryPage.addLikeSearch("bankDebitCardNum", bankDebitCardNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitCardNum", "%" + bankDebitCardNum + "%");
				exportSetting.addLikeSearch("bankDebitCardNum", bankDebitCardNum);
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


		// RETURN_URL
		String returnUrl = getSearchFields().get("returnUrl");
		if (StringUtils.isNotEmpty(returnUrl)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("returnUrl", "%" + returnUrl + "%");
				queryPage.addLikeSearch("returnUrl", returnUrl);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("returnUrl", "%" + returnUrl + "%");
				exportSetting.addLikeSearch("returnUrl", returnUrl);
			}
		}


		// TERMIAL_TYPE
		String termialType = getSearchFields().get("termialType");
		if (StringUtils.isNotEmpty(termialType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("termialType", "%" + termialType + "%");
				queryPage.addLikeSearch("termialType", termialType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("termialType", "%" + termialType + "%");
				exportSetting.addLikeSearch("termialType", termialType);
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


		// RECON_STATUS
		String reconStatus = getSearchFields().get("reconStatus");
		if (StringUtils.isNotEmpty(reconStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reconStatus", "%" + reconStatus + "%");
				queryPage.addLikeSearch("reconStatus", reconStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reconStatus", "%" + reconStatus + "%");
				exportSetting.addLikeSearch("reconStatus", reconStatus);
			}
		}


		// RECON_TIME
		String reconTime = getSearchFields().get("reconTime");
		if (StringUtils.isNotEmpty(reconTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reconTime",
						DateUtils.convert(reconTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reconTime",
						DateUtils.convert(reconTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// REVIEWER
		String reviewer = getSearchFields().get("reviewer");
		if (StringUtils.isNotEmpty(reviewer)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewer", "%" + reviewer + "%");
				queryPage.addLikeSearch("reviewer", reviewer);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewer", "%" + reviewer + "%");
				exportSetting.addLikeSearch("reviewer", reviewer);
			}
		}


		// REVIEW_MSG
		String reviewMsg = getSearchFields().get("reviewMsg");
		if (StringUtils.isNotEmpty(reviewMsg)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewMsg", "%" + reviewMsg + "%");
				queryPage.addLikeSearch("reviewMsg", reviewMsg);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewMsg", "%" + reviewMsg + "%");
				exportSetting.addLikeSearch("reviewMsg", reviewMsg);
			}
		}


		// REVIEW_RESULT
		String reviewResult = getSearchFields().get("reviewResult");
		if (StringUtils.isNotEmpty(reviewResult)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewResult", "%" + reviewResult + "%");
				queryPage.addLikeSearch("reviewResult", reviewResult);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewResult", "%" + reviewResult + "%");
				exportSetting.addLikeSearch("reviewResult", reviewResult);
			}
		}


		// REVIEW_TIME
		String reviewTime = getSearchFields().get("reviewTime");
		if (StringUtils.isNotEmpty(reviewTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewTime",
						DateUtils.convert(reviewTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewTime",
						DateUtils.convert(reviewTime,
								DateUtils.DATE_FORMAT));
			}
		}
		
		// timeType 时间类型
		String timeType = getSearchFields().get("timeType");
		String startTime = getSearchFields().get("startTime");
		String endTime = getSearchFields().get("endTime");
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
			}else if(timeType.equals(Constants.TIME_TYPE_RECORD_TIME)){// 04-银行到账时间
				searchTime = "bankReturnTime";
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
		
		// errType 错误类型
		String errType = getSearchFields().get("errType");
		if (StringUtils.isNotEmpty(errType)) {
			/* 
			 	01	金额不一致
				02	银行交易流水缺失
				03	平台交易流水缺失
			 */
			/*if(errType.equals("99")){ 
				// 查询全部的错误,不添加任何条件
				queryPage.addIsNotNull("errType");
			}else{*/
			
			if (null != queryPage) {
				queryPage.addQueryCondition("errType", "%" + errType + "%");
				queryPage.addLikeSearch("errType", errType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errType", "%" + errType + "%");
				exportSetting.addLikeSearch("errType", errType);
			}
		}
		
	}

	public ICnlTransErrBiz getCnlTransErrBiz() {
		logger.info("entering action::CnlTransErrManagerAction.getCnlTransErrBiz()...");
		return cnlTransErrBiz;
	}

	public void setCnlTransErrBiz(ICnlTransErrBiz cnlTransErrBiz) {
		logger.info("entering action::CnlTransErrManagerAction.setCnlTransErrBiz()...");
		this.cnlTransErrBiz = cnlTransErrBiz;
	}

	public CnlTransErr getCnlTransErr() {
		logger.info("entering action::CnlTransErrManagerAction.getCnlTransErr()...");
		return cnlTransErr;
	}

	public void setCnlTransErr(CnlTransErr cnlTransErr) {
		logger.info("entering action::CnlTransErrManagerAction.setCnlTransErr()...");
		this.cnlTransErr = cnlTransErr;
	}

	public String getId() {
		logger.info("entering action::CnlTransErrManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlTransErrManagerAction.setId()...");
		this.id = id;
	}

}
