package com.ibs.core.module.corcust.action;


import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.corcust.biz.ICorBankAcntTransDtlBiz;
import com.ibs.core.module.corcust.domain.CorBankAcntTransDtl;
import com.ibs.core.module.corcust.domain.CorDto;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CorBankAcntTransDtlManagerAction extends CrudBaseAction {

	private ICorBankAcntTransDtlBiz corBankAcntTransDtlBiz;
	
	private String id;
	private CorBankAcntTransDtl corBankAcntTransDtl;
	private List<OptionObjectPair> bankNameList;
	private List<OptionObjectPair> transCurrencyList;
	private List<OptionObjectPair> directionList;
	private List<OptionObjectPair> transTypeList;
	private List<OptionObjectPair> bankNumList;
	private List<OptionObjectPair> certTypeList;
	private IDataDictBiz dataDictBiz;
	private IDataDictService dataDictService;
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.list()...");
		 bankNameList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		 transCurrencyList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		 directionList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		 return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.modify()...");
		corBankAcntTransDtl = corBankAcntTransDtlBiz.getCorBankAcntTransDtlById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		StringBuffer stb = new StringBuffer();
		
		String bankName = getSearchFields().get("bankName");
		if(StringUtils.isNotEmpty(bankName)){
			stb.append(" and c.bankName like ? ");
			queryPage.addQueryCondition("bankName", "%"+bankName+"%");
		}
		String bankCardNum = getSearchFields().get("bankCardNum");
		if (StringUtils.isNotEmpty(bankCardNum)) {
			stb.append(" and c.bankCardNum like ? ");
			queryPage.addQueryCondition("bankCarNum", "%"+bankCardNum+"%");
		}
		String custName = getSearchFields().get("custName");
		if (StringUtils.isNotEmpty(custName)) {
			stb.append(" and c.custName like ? ");
			queryPage.addQueryCondition("custName", "%" + custName + "%");
		}
		String transCurrency = getSearchFields().get("transCurrency");
		if(StringUtils.isNotEmpty(transCurrency)){
			stb.append(" and cb.transCurrency like ? ");
			queryPage.addQueryCondition("transCurrency", "%"+transCurrency+"%");
		}
	String startTime = getSearchFields().get("startTime");
	if (StringUtils.isNotEmpty(startTime)) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date sTime = sdf.parse(startTime);
			stb.append(" and cb.transTime >= ? ");
			queryPage.addQueryCondition("startTime", sTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	String endTime = getSearchFields().get("endTime");
	if (StringUtils.isNotEmpty(endTime)) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date eTime = sdf.parse(endTime);
			stb.append(" and cb.transTime <= ? ");
			queryPage.addQueryCondition("endTime", eTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
		String maxAmount = getSearchFields().get("maxAmount");
		if (StringUtils.isNotEmpty(maxAmount)) {
			stb.append(" and cb.amount <= ? ");
			queryPage.addQueryCondition("maxAmount", new BigDecimal(maxAmount));
		}
		String minAmount = getSearchFields().get("minAmount");
		if (StringUtils.isNotEmpty(minAmount)) {
			stb.append(" and cb.amount >= ? ");
			queryPage.addQueryCondition("minAmount", new BigDecimal(minAmount));
		}
		
		String direction = getSearchFields().get("direction");
		if(StringUtils.isNotEmpty(direction)){
			stb.append(" and cb.direction like ? ");
			queryPage.addQueryCondition("direction", "%"+direction+"%");
		}
		
		String st = stb.toString();
		logger.debug(stb.toString());
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CorDto> result = (Page<CorDto>) corBankAcntTransDtlBiz.findCorBankAcntTransDtlByPage(queryPage,st,direction);
		Collection<CorDto> cnlTransTraceColl = result.getRows();
		List<DataDict> bankNameList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		List<DataDict> transCurrencyList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		List<DataDict> directionList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		List<DataDict> transTypeList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		List<DataDict> certTypeList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__WARRANT_TYPE);
		List<DataDict> bankNumList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NUM);
		try {								
			CollectionUtils.transformCollection(cnlTransTraceColl, "bankName","bankName", bankNameList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "transCurrency","transCurrency", transCurrencyList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "direction","direction", directionList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "transType","transType", transTypeList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "otherBankNum","otherBankNum", bankNumList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "certType","certType", certTypeList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "bankNum","bankNum", bankNumList, "code", "name");
		} catch (Exception e) {								
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
		logger.info("entering action::CorBankAcntTransDtlManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			corBankAcntTransDtlBiz.saveCorBankAcntTransDtl(corBankAcntTransDtl);
		}
		// 如果是修改
		else {
			corBankAcntTransDtlBiz.updateCorBankAcntTransDtl(corBankAcntTransDtl);
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
		logger.info("entering action::CorBankAcntTransDtlManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		StringBuffer sb = new StringBuffer();
		String bankName = getSearchFields().get("bankName");
		if (null != bankName) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankName", bankName);
				queryPage.addEqualSearch("bankName", bankName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankName", bankName);
				exportSetting.addEqualSearch("bankName", bankName);
			}
		}

		String bankCarNum = getSearchFields().get("bankCarNum");
		if (StringUtils.isNotEmpty(bankCarNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCarNum", "%" + bankCarNum + "%");
				queryPage.addLikeSearch("bankCarNum", bankCarNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCarNum", "%" + bankCarNum + "%");
				exportSetting.addLikeSearch("bankCarNum", bankCarNum);
			}
		}

		String cusName = getSearchFields().get("cusName");
		if (StringUtils.isNotEmpty(cusName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cusName", "%" + cusName + "%");
				queryPage.addLikeSearch("cusName", cusName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cusName", "%" + cusName + "%");
				exportSetting.addLikeSearch("cusName", cusName);
			}
		}

		String transCurrency = getSearchFields().get("transCurrency");
		if (null != transCurrency) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transCurrency", transCurrency);
				queryPage.addEqualSearch("transCurrency", transCurrency);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transCurrency", transCurrency);
				exportSetting.addEqualSearch("transCurrency", transCurrency);
			}
		}

		String direction = getSearchFields().get("direction");
		if (null != direction) {
			if (null != queryPage) {
				queryPage.addQueryCondition("direction", direction);
				queryPage.addEqualSearch("direction", direction);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("direction", direction);
				exportSetting.addEqualSearch("direction", direction);
			}
		}

		String startTime = getSearchFields().get("startTime");
		if (StringUtils.isNotEmpty(startTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date sTime = sdf.parse(startTime);
				sb.append(" and cb.transTime >= ?");
				queryPage.addQueryCondition("transTime", sTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		String endTime = getSearchFields().get("endTime");
		if (StringUtils.isNotEmpty(endTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date eTime = sdf.parse(endTime);
				sb.append(" and cb.transTime >= ?");
				queryPage.addQueryCondition("transTime", eTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		String maxAmount = getSearchFields().get("maxAmount");
		if (StringUtils.isNotEmpty(maxAmount)) {
			sb.append(" and cb.amount <= ?");
			queryPage.addQueryCondition("amount", maxAmount);
		}

		String minAmount = getSearchFields().get("minAmount");
		if (StringUtils.isNotEmpty(minAmount)) {
			sb.append(" and cb.amount >= ?");
			queryPage.addQueryCondition("amount", minAmount);
		}
		String st = sb.toString();
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		corBankAcntTransDtlBiz.exportCorBankAcntTransDtl(exportSetting,st,direction);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.setQueryCondition()...");


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


		// DIRECTION
		/*String direction = getSearchFields().get("direction");
		if (StringUtils.isNotEmpty(direction)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("direction", "%" + direction + "%");
				queryPage.addLikeSearch("direction", direction);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("direction", "%" + direction + "%");
				exportSetting.addLikeSearch("direction", direction);
			}
		}*/


		// AMOUNT
		String amount = getSearchFields().get("amount");
		if (null != amount) {
			if (null != queryPage) {
				queryPage.addQueryCondition("amount", amount);
				queryPage.addEqualSearch("amount", amount);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("amount", amount);
				queryPage.addEqualSearch("amount", amount);
			}
		}


		// TRANS_CURRENCY
		/*String transCurrency = getSearchFields().get("transCurrency");
		if (StringUtils.isNotEmpty(transCurrency)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transCurrency", "%" + transCurrency + "%");
				queryPage.addLikeSearch("transCurrency", transCurrency);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transCurrency", "%" + transCurrency + "%");
				exportSetting.addLikeSearch("transCurrency", transCurrency);
			}
		}*/


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


		// TRANS_STATUS
		String transStatus = getSearchFields().get("transStatus");
		if (StringUtils.isNotEmpty(transStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transStatus", "%" + transStatus + "%");
				queryPage.addLikeSearch("transStatus", transStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transStatus", "%" + transStatus + "%");
				exportSetting.addLikeSearch("transStatus", transStatus);
			}
		}


		// BANK_MSG_CODE
		String bankMsgCode = getSearchFields().get("bankMsgCode");
		if (StringUtils.isNotEmpty(bankMsgCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankMsgCode", "%" + bankMsgCode + "%");
				queryPage.addLikeSearch("bankMsgCode", bankMsgCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankMsgCode", "%" + bankMsgCode + "%");
				exportSetting.addLikeSearch("bankMsgCode", bankMsgCode);
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


		// ERR_DESC
		String errDesc = getSearchFields().get("errDesc");
		if (StringUtils.isNotEmpty(errDesc)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errDesc", "%" + errDesc + "%");
				queryPage.addLikeSearch("errDesc", errDesc);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errDesc", "%" + errDesc + "%");
				exportSetting.addLikeSearch("errDesc", errDesc);
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


		// HANDLE_DESC
		String handleDesc = getSearchFields().get("handleDesc");
		if (StringUtils.isNotEmpty(handleDesc)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("handleDesc", "%" + handleDesc + "%");
				queryPage.addLikeSearch("handleDesc", handleDesc);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("handleDesc", "%" + handleDesc + "%");
				exportSetting.addLikeSearch("handleDesc", handleDesc);
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


		// ORG_REQ_NUM
		String orgReqNum = getSearchFields().get("orgReqNum");
		if (StringUtils.isNotEmpty(orgReqNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("orgReqNum", "%" + orgReqNum + "%");
				queryPage.addLikeSearch("orgReqNum", orgReqNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("orgReqNum", "%" + orgReqNum + "%");
				exportSetting.addLikeSearch("orgReqNum", orgReqNum);
			}
		}


		// BANK_BALANCE_AFTER_TRANS
		String bankBalanceAfterTrans = getSearchFields().get("bankBalanceAfterTrans");
		if (null != bankBalanceAfterTrans) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankBalanceAfterTrans", bankBalanceAfterTrans);
				queryPage.addEqualSearch("bankBalanceAfterTrans", bankBalanceAfterTrans);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("bankBalanceAfterTrans", bankBalanceAfterTrans);
				queryPage.addEqualSearch("bankBalanceAfterTrans", bankBalanceAfterTrans);
			}
		}


		// BANK_FROZEN_AMOUMT
		String bankFrozenAmoumt = getSearchFields().get("bankFrozenAmoumt");
		if (null != bankFrozenAmoumt) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankFrozenAmoumt", bankFrozenAmoumt);
				queryPage.addEqualSearch("bankFrozenAmoumt", bankFrozenAmoumt);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("bankFrozenAmoumt", bankFrozenAmoumt);
				queryPage.addEqualSearch("bankFrozenAmoumt", bankFrozenAmoumt);
			}
		}


		// BANK_AVALIABLE_AMOUNT
		String bankAvaliableAmount = getSearchFields().get("bankAvaliableAmount");
		if (null != bankAvaliableAmount) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankAvaliableAmount", bankAvaliableAmount);
				queryPage.addEqualSearch("bankAvaliableAmount", bankAvaliableAmount);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("bankAvaliableAmount", bankAvaliableAmount);
				queryPage.addEqualSearch("bankAvaliableAmount", bankAvaliableAmount);
			}
		}


		// TRANS_RATE
		String transRate = getSearchFields().get("transRate");
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


		// BANK_NUM
		String bankNum = getSearchFields().get("bankNum");
		if (StringUtils.isNotEmpty(bankNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankNum", "%" + bankNum + "%");
				queryPage.addLikeSearch("bankNum", bankNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankNum", "%" + bankNum + "%");
				exportSetting.addLikeSearch("bankNum", bankNum);
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

	}

	public ICorBankAcntTransDtlBiz getCorBankAcntTransDtlBiz() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.getCorBankAcntTransDtlBiz()...");
		return corBankAcntTransDtlBiz;
	}

	public void setCorBankAcntTransDtlBiz(ICorBankAcntTransDtlBiz corBankAcntTransDtlBiz) {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.setCorBankAcntTransDtlBiz()...");
		this.corBankAcntTransDtlBiz = corBankAcntTransDtlBiz;
	}

	public CorBankAcntTransDtl getCorBankAcntTransDtl() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.getCorBankAcntTransDtl()...");
		return corBankAcntTransDtl;
	}

	public void setCorBankAcntTransDtl(CorBankAcntTransDtl corBankAcntTransDtl) {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.setCorBankAcntTransDtl()...");
		this.corBankAcntTransDtl = corBankAcntTransDtl;
	}

	public String getId() {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CorBankAcntTransDtlManagerAction.setId()...");
		this.id = id;
	}

	public List<OptionObjectPair> getBankNameList() {
		return bankNameList;
	}

	public void setBankNameList(List<OptionObjectPair> bankNameList) {
		this.bankNameList = bankNameList;
	}

	public List<OptionObjectPair> getTransCurrencyList() {
		return transCurrencyList;
	}

	public void setTransCurrencyList(List<OptionObjectPair> transCurrencyList) {
		this.transCurrencyList = transCurrencyList;
	}

	public List<OptionObjectPair> getDirectionList() {
		return directionList;
	}

	public void setDirectionList(List<OptionObjectPair> directionList) {
		this.directionList = directionList;
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

	public List<OptionObjectPair> getTransTypeList() {
		return transTypeList;
	}

	public void setTransTypeList(List<OptionObjectPair> transTypeList) {
		this.transTypeList = transTypeList;
	}

	public List<OptionObjectPair> getBankNumList() {
		return bankNumList;
	}

	public void setBankNumList(List<OptionObjectPair> bankNumList) {
		this.bankNumList = bankNumList;
	}

	public List<OptionObjectPair> getCertTypeList() {
		return certTypeList;
	}

	public void setCertTypeList(List<OptionObjectPair> certTypeList) {
		this.certTypeList = certTypeList;
	}
    
}
