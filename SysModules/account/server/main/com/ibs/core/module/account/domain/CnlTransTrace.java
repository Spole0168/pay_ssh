/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransTrace extends BaseEntity {
	private BigDecimal sumAmount;
	
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	private String id; // ID
	private String reqInnerNum; // REQ_INNER_NUM
	private String custCode; // CUST_CODE
	private String cnlCustCode; // CNL_CUST_CODE
	private String reqNum; // REQ_NUM
	private Long reqBatch; // REQ_BATCH
	private String stlNum; // STL_NUM
	private String transType; // TRANS_TYPE
	private String transDc; // TRANS_DC
	private String transSubType; // TRANS_SUB_TYPE
	private String transOrderNum; // TRANS_ORDER_NUM
	private String transCurrency; // TRANS_CURRENCY
	private BigDecimal transAmount; // TRANS_AMOUNT
	private BigDecimal transLatestAmount; // TRANS_LATEST_AMOUNT
	private String transStatus; // TRANS_STATUS
	private Date transDate; // TRANS_DATE
	private Date transTime; // TRANS_TIME
	private BigDecimal transRate; // TRANS_RATE
	private String transComments; // TRANS_COMMENTS
	private String termialType; // TERMIAL_TYPE
	private Date bankReqTime; // BANK_REQ_TIME
	private Date bankAccepteTime; // BANK_ACCEPTE_TIME
	private String bankTransNum; // BANK_TRANS_NUM
	private String bankHandleNum; // BANK_HANDLE_NUM
	private Date bankReturnTime; // BANK_RETURN_TIME
	private String bankReturnResult; // BANK_RETURN_RESULT
	private String bankPmtCnlType; // BANK_PMT_CNL_TYPE
	private String bankPmtCnlCode; // BANK_PMT_CNL_CODE
	private String cnlCnlCode; // CNL_CNL_CODE
	private String isinGl; // ISIN_GL
	private Date inglTime; // INGL_TIME
	private Date printedTime; // PRINTED_TIME
	private String isPrinted; // IS_PRINTED
	private String bankCreditAcntCode; // BANK_CREDIT_ACNT_CODE
	private String bankCreditName; // BANK_CREDIT_NAME
	private String bankCreditCode; // BANK_CREDIT_CODE
	private String bankCreditBranchName; // BANK_CREDIT_BRANCH_NAME
	private String bankCreditBranchCode; // BANK_CREDIT_BRANCH_CODE
	private String bankCreditCustName; // BANK_CREDIT_CUST_NAME
	private String bankCreditCardNum; // BANK_CREDIT_CARD_NUM
	private String bankDebitAcntCode; // BANK_DEBIT_ACNT_CODE
	private String bankDebitName; // BANK_DEBIT_NAME
	private String bankDebitCode; // BANK_DEBIT_CODE
	private String bankDebitBranchName; // BANK_DEBIT_BRANCH_NAME
	private String bankDebitBranchCode; // BANK_DEBIT_BRANCH_CODE
	private String bankDebitCustName; // BANK_DEBIT_CUST_NAME
	private String bankDebitCardNum; // BANK_DEBIT_CARD_NUM
	private Date bankReqTrnasDate; // BANK_REQ_TRNAS_DATE
	private String bnakServiceFeeAct; // BNAK_SERVICE_FEE_ACT
	private Date bankReqTransTime; // BANK_REQ_TRANS_TIME
	private String bnakHandlePriority; // BNAK_HANDLE_PRIORITY
	private String returnUrl; // RETURN_URL
	private String errType; // ERR_TYPE
	private String errCode; // ERR_CODE
	private String errMsg; // ERR_MSG
	private String handleStatus; // HANDLE_STATUS
	private String handleResult; // HANDLE_RESULT
	private String handleMsg; // HANDLE_MSG
	private Date handleTime; // HANDLE_TIME
	private String operator; // OPERATOR
	private String reviewer; // REVIEWER
	private String reviewMsg; // REVIEW_MSG
	private String reviewStatus; // REVIEW_STATUS
	private String reviewResult; // REVIEW_RESULT
	private Date reviewTime; // REVIEW_TIME
	private String voucherNum; // VOUCHER_NUM
	private String voucherLocation; // VOUCHER_LOCATION
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String transBankSummary; // TRANS_BANK_SUMMARY
	 
	public String getTransBankSummary() {
		return transBankSummary;
	}
	public void setTransBankSummary(String transBankSummary) {
		this.transBankSummary = transBankSummary;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getReqInnerNum(){
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum){
		this.reqInnerNum = reqInnerNum;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getCnlCustCode(){
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode){
		this.cnlCustCode = cnlCustCode;
	}

	public String getReqNum(){
		return reqNum;
	}
	public void setReqNum(String reqNum){
		this.reqNum = reqNum;
	}

	public Long getReqBatch(){
		return reqBatch;
	}
	public void setReqBatch(Long reqBatch){
		this.reqBatch = reqBatch;
	}

	public String getStlNum(){
		return stlNum;
	}
	public void setStlNum(String stlNum){
		this.stlNum = stlNum;
	}

	public String getTransType(){
		return transType;
	}
	public void setTransType(String transType){
		this.transType = transType;
	}

	public String getTransDc(){
		return transDc;
	}
	public void setTransDc(String transDc){
		this.transDc = transDc;
	}

	public String getTransSubType(){
		return transSubType;
	}
	public void setTransSubType(String transSubType){
		this.transSubType = transSubType;
	}

	public String getTransOrderNum(){
		return transOrderNum;
	}
	public void setTransOrderNum(String transOrderNum){
		this.transOrderNum = transOrderNum;
	}

	public String getTransCurrency(){
		return transCurrency;
	}
	public void setTransCurrency(String transCurrency){
		this.transCurrency = transCurrency;
	}

	

	public String getTransStatus(){
		return transStatus;
	}
	public void setTransStatus(String transStatus){
		this.transStatus = transStatus;
	}

	public Date getTransDate(){
		return transDate;
	}
	public void setTransDate(Date transDate){
		this.transDate = transDate;
	}

	public Date getTransTime(){
		return transTime;
	}
	public void setTransTime(Date transTime){
		this.transTime = transTime;
	}

	

	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public BigDecimal getTransLatestAmount() {
		return transLatestAmount;
	}
	public void setTransLatestAmount(BigDecimal transLatestAmount) {
		this.transLatestAmount = transLatestAmount;
	}
	public BigDecimal getTransRate() {
		return transRate;
	}
	public void setTransRate(BigDecimal transRate) {
		this.transRate = transRate;
	}
	public String getTransComments(){
		return transComments;
	}
	public void setTransComments(String transComments){
		this.transComments = transComments;
	}

	public String getTermialType(){
		return termialType;
	}
	public void setTermialType(String termialType){
		this.termialType = termialType;
	}

	public Date getBankReqTime(){
		return bankReqTime;
	}
	public void setBankReqTime(Date bankReqTime){
		this.bankReqTime = bankReqTime;
	}

	public Date getBankAccepteTime(){
		return bankAccepteTime;
	}
	public void setBankAccepteTime(Date bankAccepteTime){
		this.bankAccepteTime = bankAccepteTime;
	}

	public String getBankTransNum(){
		return bankTransNum;
	}
	public void setBankTransNum(String bankTransNum){
		this.bankTransNum = bankTransNum;
	}

	public String getBankHandleNum(){
		return bankHandleNum;
	}
	public void setBankHandleNum(String bankHandleNum){
		this.bankHandleNum = bankHandleNum;
	}

	public Date getBankReturnTime(){
		return bankReturnTime;
	}
	public void setBankReturnTime(Date bankReturnTime){
		this.bankReturnTime = bankReturnTime;
	}

	public String getBankReturnResult(){
		return bankReturnResult;
	}
	public void setBankReturnResult(String bankReturnResult){
		this.bankReturnResult = bankReturnResult;
	}

	public String getBankPmtCnlType(){
		return bankPmtCnlType;
	}
	public void setBankPmtCnlType(String bankPmtCnlType){
		this.bankPmtCnlType = bankPmtCnlType;
	}

	public String getBankPmtCnlCode(){
		return bankPmtCnlCode;
	}
	public void setBankPmtCnlCode(String bankPmtCnlCode){
		this.bankPmtCnlCode = bankPmtCnlCode;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getIsinGl(){
		return isinGl;
	}
	public void setIsinGl(String isinGl){
		this.isinGl = isinGl;
	}

	public Date getInglTime(){
		return inglTime;
	}
	public void setInglTime(Date inglTime){
		this.inglTime = inglTime;
	}

	public Date getPrintedTime(){
		return printedTime;
	}
	public void setPrintedTime(Date printedTime){
		this.printedTime = printedTime;
	}

	public String getIsPrinted(){
		return isPrinted;
	}
	public void setIsPrinted(String isPrinted){
		this.isPrinted = isPrinted;
	}

	public String getBankCreditAcntCode(){
		return bankCreditAcntCode;
	}
	public void setBankCreditAcntCode(String bankCreditAcntCode){
		this.bankCreditAcntCode = bankCreditAcntCode;
	}

	public String getBankCreditName(){
		return bankCreditName;
	}
	public void setBankCreditName(String bankCreditName){
		this.bankCreditName = bankCreditName;
	}

	public String getBankCreditCode(){
		return bankCreditCode;
	}
	public void setBankCreditCode(String bankCreditCode){
		this.bankCreditCode = bankCreditCode;
	}

	public String getBankCreditBranchName(){
		return bankCreditBranchName;
	}
	public void setBankCreditBranchName(String bankCreditBranchName){
		this.bankCreditBranchName = bankCreditBranchName;
	}

	public String getBankCreditBranchCode(){
		return bankCreditBranchCode;
	}
	public void setBankCreditBranchCode(String bankCreditBranchCode){
		this.bankCreditBranchCode = bankCreditBranchCode;
	}

	public String getBankCreditCustName(){
		return bankCreditCustName;
	}
	public void setBankCreditCustName(String bankCreditCustName){
		this.bankCreditCustName = bankCreditCustName;
	}

	public String getBankCreditCardNum(){
		return bankCreditCardNum;
	}
	public void setBankCreditCardNum(String bankCreditCardNum){
		this.bankCreditCardNum = bankCreditCardNum;
	}

	public String getBankDebitAcntCode(){
		return bankDebitAcntCode;
	}
	public void setBankDebitAcntCode(String bankDebitAcntCode){
		this.bankDebitAcntCode = bankDebitAcntCode;
	}

	public String getBankDebitName(){
		return bankDebitName;
	}
	public void setBankDebitName(String bankDebitName){
		this.bankDebitName = bankDebitName;
	}

	public String getBankDebitCode(){
		return bankDebitCode;
	}
	public void setBankDebitCode(String bankDebitCode){
		this.bankDebitCode = bankDebitCode;
	}

	public String getBankDebitBranchName(){
		return bankDebitBranchName;
	}
	public void setBankDebitBranchName(String bankDebitBranchName){
		this.bankDebitBranchName = bankDebitBranchName;
	}

	public String getBankDebitBranchCode(){
		return bankDebitBranchCode;
	}
	public void setBankDebitBranchCode(String bankDebitBranchCode){
		this.bankDebitBranchCode = bankDebitBranchCode;
	}

	public String getBankDebitCustName(){
		return bankDebitCustName;
	}
	public void setBankDebitCustName(String bankDebitCustName){
		this.bankDebitCustName = bankDebitCustName;
	}

	public String getBankDebitCardNum(){
		return bankDebitCardNum;
	}
	public void setBankDebitCardNum(String bankDebitCardNum){
		this.bankDebitCardNum = bankDebitCardNum;
	}

	public Date getBankReqTrnasDate(){
		return bankReqTrnasDate;
	}
	public void setBankReqTrnasDate(Date bankReqTrnasDate){
		this.bankReqTrnasDate = bankReqTrnasDate;
	}

	public String getBnakServiceFeeAct(){
		return bnakServiceFeeAct;
	}
	public void setBnakServiceFeeAct(String bnakServiceFeeAct){
		this.bnakServiceFeeAct = bnakServiceFeeAct;
	}

	public Date getBankReqTransTime(){
		return bankReqTransTime;
	}
	public void setBankReqTransTime(Date bankReqTransTime){
		this.bankReqTransTime = bankReqTransTime;
	}

	public String getBnakHandlePriority(){
		return bnakHandlePriority;
	}
	public void setBnakHandlePriority(String bnakHandlePriority){
		this.bnakHandlePriority = bnakHandlePriority;
	}

	public String getReturnUrl(){
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl){
		this.returnUrl = returnUrl;
	}

	public String getErrType(){
		return errType;
	}
	public void setErrType(String errType){
		this.errType = errType;
	}

	public String getErrCode(){
		return errCode;
	}
	public void setErrCode(String errCode){
		this.errCode = errCode;
	}

	public String getErrMsg(){
		return errMsg;
	}
	public void setErrMsg(String errMsg){
		this.errMsg = errMsg;
	}

	public String getHandleStatus(){
		return handleStatus;
	}
	public void setHandleStatus(String handleStatus){
		this.handleStatus = handleStatus;
	}

	public String getHandleResult(){
		return handleResult;
	}
	public void setHandleResult(String handleResult){
		this.handleResult = handleResult;
	}

	public String getHandleMsg(){
		return handleMsg;
	}
	public void setHandleMsg(String handleMsg){
		this.handleMsg = handleMsg;
	}

	public Date getHandleTime(){
		return handleTime;
	}
	public void setHandleTime(Date handleTime){
		this.handleTime = handleTime;
	}

	public String getOperator(){
		return operator;
	}
	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getReviewer(){
		return reviewer;
	}
	public void setReviewer(String reviewer){
		this.reviewer = reviewer;
	}

	public String getReviewMsg(){
		return reviewMsg;
	}
	public void setReviewMsg(String reviewMsg){
		this.reviewMsg = reviewMsg;
	}

	public String getReviewStatus(){
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus){
		this.reviewStatus = reviewStatus;
	}

	public String getReviewResult(){
		return reviewResult;
	}
	public void setReviewResult(String reviewResult){
		this.reviewResult = reviewResult;
	}

	public Date getReviewTime(){
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime){
		this.reviewTime = reviewTime;
	}

	public String getVoucherNum(){
		return voucherNum;
	}
	public void setVoucherNum(String voucherNum){
		this.voucherNum = voucherNum;
	}

	public String getVoucherLocation(){
		return voucherLocation;
	}
	public void setVoucherLocation(String voucherLocation){
		this.voucherLocation = voucherLocation;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}
}
