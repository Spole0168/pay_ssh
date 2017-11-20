/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.cnltrans.biz.ICnlTrans5yBiz;
import com.ibs.core.module.cnltrans.domain.CnlTrans5y;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_5Y
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlTrans5yManagerAction extends CrudBaseAction {

	private ICnlTrans5yBiz cnlTrans5yBiz;
	
	private String id;
	private CnlTrans5y cnlTrans5y;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlTrans5yManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlTrans5yManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlTrans5yManagerAction.modify()...");
		cnlTrans5y = cnlTrans5yBiz.getCnlTrans5yById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlTrans5yManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlTrans5y> result = (Page<CnlTrans5y>) cnlTrans5yBiz
				.findCnlTrans5yByPage(queryPage);
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
		logger.info("entering action::CnlTrans5yManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlTrans5yBiz.saveCnlTrans5y(cnlTrans5y);
		}
		// 如果是修改
		else {
			cnlTrans5yBiz.updateCnlTrans5y(cnlTrans5y);
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
		logger.info("entering action::CnlTrans5yManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlTrans5yManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlTrans5yBiz.exportCnlTrans5y(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlTrans5yManagerAction.setQueryCondition()...");


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
		String reqBatch = getSearchFields().get("reqBatch");
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
		String transAmount = getSearchFields().get("transAmount");
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


		// TRANS_LATEST_AMOUNT
		String transLatestAmount = getSearchFields().get("transLatestAmount");
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

	}

	public ICnlTrans5yBiz getCnlTrans5yBiz() {
		logger.info("entering action::CnlTrans5yManagerAction.getCnlTrans5yBiz()...");
		return cnlTrans5yBiz;
	}

	public void setCnlTrans5yBiz(ICnlTrans5yBiz cnlTrans5yBiz) {
		logger.info("entering action::CnlTrans5yManagerAction.setCnlTrans5yBiz()...");
		this.cnlTrans5yBiz = cnlTrans5yBiz;
	}

	public CnlTrans5y getCnlTrans5y() {
		logger.info("entering action::CnlTrans5yManagerAction.getCnlTrans5y()...");
		return cnlTrans5y;
	}

	public void setCnlTrans5y(CnlTrans5y cnlTrans5y) {
		logger.info("entering action::CnlTrans5yManagerAction.setCnlTrans5y()...");
		this.cnlTrans5y = cnlTrans5y;
	}

	public String getId() {
		logger.info("entering action::CnlTrans5yManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlTrans5yManagerAction.setId()...");
		this.id = id;
	}

}
