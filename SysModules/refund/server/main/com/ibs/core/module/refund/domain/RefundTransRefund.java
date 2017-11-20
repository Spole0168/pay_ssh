/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.refund.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_REFUND
 * @modify		: your comments goes here (author,date,reason).
 */
public class RefundTransRefund extends BaseEntity {
	
	private String id; // ID
	private String refundCode; // REFUND_CODE
	private String cnlCnlCode; // CNL_CNL_CODE
	private String reqNum; // REQ_NUM
	private String refundContact; // REFUND_CONTACT
	private String refundContactTel; // REFUND_CONTACT_TEL
	private String refundReason; // REFUND_REASON
	private Date refundTime; // REFUND_TIME
	private String refundCurrency; // REFUND_CURRENCY
	private Long refundAmount; // REFUND_AMOUNT
	private Date refundDate; // REFUND_DATE
	private String refundStatus; // REFUND_STATUS
	private String voucherNum; // VOUCHER_NUM
	private String voucherLocation; // VOUCHER_LOCATION
	private String refundFailReason; // REFUND_FAIL_REASON
	private String bankTransNum; // BANK_TRANS_NUM
	private String bankCreditCustName; // BANK_CREDIT_CUST_NAME
	private String bankCreditName; // BANK_CREDIT_NAME
	private String bankCreditCode; // BANK_CREDIT_CODE
	private String bankCreditCardNum; // BANK_CREDIT_CARD_NUM
	private String bankDebitCustName; // BANK_DEBIT_CUST_NAME
	private String bankDebitName; // BANK_DEBIT_NAME
	private String bankDebitCode; // BANK_DEBIT_CODE
	private String bankDebitCardNum; // BANK_DEBIT_CARD_NUM
	private String handler; // HANDLER
	private Date handleTime; // HANDLE_TIME
	private String reviewer; // REVIEWER
	private String reviewResult; // REVIEW_RESULT
	private String reviewErrCode; // REVIEW_ERR_CODE
	private Date reviewTime; // REVIEW_TIME
	private String reviewMsg; // REVIEW_MSG
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String refundVoucherLocation;
	private String refundVoucherNum;
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getRefundCode(){
		return refundCode;
	}
	public void setRefundCode(String refundCode){
		this.refundCode = refundCode;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getReqNum(){
		return reqNum;
	}
	public void setReqNum(String reqNum){
		this.reqNum = reqNum;
	}

	public String getRefundContact(){
		return refundContact;
	}
	public void setRefundContact(String refundContact){
		this.refundContact = refundContact;
	}

	public String getRefundContactTel(){
		return refundContactTel;
	}
	public void setRefundContactTel(String refundContactTel){
		this.refundContactTel = refundContactTel;
	}

	public String getRefundReason(){
		return refundReason;
	}
	public void setRefundReason(String refundReason){
		this.refundReason = refundReason;
	}

	public Date getRefundTime(){
		return refundTime;
	}
	public void setRefundTime(Date refundTime){
		this.refundTime = refundTime;
	}

	public String getRefundCurrency(){
		return refundCurrency;
	}
	public void setRefundCurrency(String refundCurrency){
		this.refundCurrency = refundCurrency;
	}

	public Long getRefundAmount(){
		return refundAmount;
	}
	public void setRefundAmount(Long refundAmount){
		this.refundAmount = refundAmount;
	}

	public Date getRefundDate(){
		return refundDate;
	}
	public void setRefundDate(Date refundDate){
		this.refundDate = refundDate;
	}

	public String getRefundStatus(){
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus){
		this.refundStatus = refundStatus;
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

	public String getRefundFailReason(){
		return refundFailReason;
	}
	public void setRefundFailReason(String refundFailReason){
		this.refundFailReason = refundFailReason;
	}

	public String getBankTransNum(){
		return bankTransNum;
	}
	public void setBankTransNum(String bankTransNum){
		this.bankTransNum = bankTransNum;
	}

	public String getBankCreditCustName(){
		return bankCreditCustName;
	}
	public void setBankCreditCustName(String bankCreditCustName){
		this.bankCreditCustName = bankCreditCustName;
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

	public String getBankCreditCardNum(){
		return bankCreditCardNum;
	}
	public void setBankCreditCardNum(String bankCreditCardNum){
		this.bankCreditCardNum = bankCreditCardNum;
	}

	public String getBankDebitCustName(){
		return bankDebitCustName;
	}
	public void setBankDebitCustName(String bankDebitCustName){
		this.bankDebitCustName = bankDebitCustName;
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

	public String getBankDebitCardNum(){
		return bankDebitCardNum;
	}
	public void setBankDebitCardNum(String bankDebitCardNum){
		this.bankDebitCardNum = bankDebitCardNum;
	}

	public String getHandler(){
		return handler;
	}
	public void setHandler(String handler){
		this.handler = handler;
	}

	public Date getHandleTime(){
		return handleTime;
	}
	public void setHandleTime(Date handleTime){
		this.handleTime = handleTime;
	}

	public String getReviewer(){
		return reviewer;
	}
	public void setReviewer(String reviewer){
		this.reviewer = reviewer;
	}

	public String getReviewResult(){
		return reviewResult;
	}
	public void setReviewResult(String reviewResult){
		this.reviewResult = reviewResult;
	}

	public String getReviewErrCode(){
		return reviewErrCode;
	}
	public void setReviewErrCode(String reviewErrCode){
		this.reviewErrCode = reviewErrCode;
	}

	public Date getReviewTime(){
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime){
		this.reviewTime = reviewTime;
	}

	public String getReviewMsg(){
		return reviewMsg;
	}
	public void setReviewMsg(String reviewMsg){
		this.reviewMsg = reviewMsg;
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
	public String getRefundVoucherLocation() {
		return refundVoucherLocation;
	}
	public void setRefundVoucherLocation(String refundVoucherLocation) {
		this.refundVoucherLocation = refundVoucherLocation;
	}
	public String getRefundVoucherNum() {
		return refundVoucherNum;
	}
	public void setRefundVoucherNum(String refundVoucherNum) {
		this.refundVoucherNum = refundVoucherNum;
	}
	
}
