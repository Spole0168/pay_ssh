/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS_DTL_CH
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankAcntTransDtlCh extends BaseEntity {

	private String id; // ID
	private String bankTransNum; // BANK_TRANS_NUM
	private String bankCreditAcntCode; // BANK_CREDIT_ACNT_CODE
	private String bankCreditName; // BANK_CREDIT_NAME
	private String bankCreditCardNum; // BANK_CREDIT_CARD_NUM
	private String bankDebitAcntCode; // BANK_DEBIT_ACNT_CODE
	private String bankDebitName; // BANK_DEBIT_NAME
	private String bankDebitCardNum; // BANK_DEBIT_CARD_NUM
	private String transType; // TRANS_TYPE
	private String direction; // DIRECTION
	private BigDecimal amount; // AMOUNT
	private String transCurrency; // TRANS_CURRENCY
	private Date transTime; // TRANS_TIME
	private Date transDate; // TRANS_DATE
	private String transComments; // TRANS_COMMENTS
	private String transStatus; // TRANS_STATUS
	private String bankMsgCode; // BANK_MSG_CODE
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String errCode; // ERR_CODE
	private String errDesc; // ERR_DESC
	private Date handleTime; // HANDLE_TIME
	private String operator; // OPERATOR
	private String handleDesc; // HANDLE_DESC
	private String handleStatus; // HANDLE_STATUS
	private String orgReqNum; // ORG_REQ_NUM
	private BigDecimal bankBalanceAfterTrans; // BANK_BALANCE_AFTER_TRANS
	private BigDecimal bankFrozenAmoumt; // BANK_FROZEN_AMOUMT
	private BigDecimal bankAvaliableAmount; // BANK_AVALIABLE_AMOUNT
	private Long transRate; // TRANS_RATE
	private String bankNum; // BANK_NUM
	private String isValid; // IS_VALID
	private String documentStaff;// DOCUMENT_STAFF 制单员
	private String documentReviewer; // DOCUMENT_REVIEWER 审单员
	private String certType; // CERT_TYPE 凭证类型
	private String certNum; // CERT_NUM 凭证号码
	private Date interestsStartDate; // INTERESTS_START_DATE 起息日期
	private String bankCreditCode; // BANK_CREDIT_CODE
	private String bankCreditBranchName;// BANK_CREDIT_BRANCH_NAME
	private String bankCreditBranchCode;// BANK_CREDIE_BRANCH_CODE
	private String bankCreditCustName;// BANK_CREDIT_CUST_NAME
	private String bankDebitCode; // BANK_DEBIT_CODE
	private String bankDebitBranchName;// BANK_DEBIT_BRANCH_NAME
	private String bankDebitBranchCode;// BANK_DEBIT_BRANCH_CODE
	private String bankDebitCustName;// BANK_DEBIT_CUST_NAME

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankTransNum() {
		return bankTransNum;
	}

	public void setBankTransNum(String bankTransNum) {
		this.bankTransNum = bankTransNum;
	}

	public String getBankCreditAcntCode() {
		return bankCreditAcntCode;
	}

	public void setBankCreditAcntCode(String bankCreditAcntCode) {
		this.bankCreditAcntCode = bankCreditAcntCode;
	}

	public String getBankCreditName() {
		return bankCreditName;
	}

	public void setBankCreditName(String bankCreditName) {
		this.bankCreditName = bankCreditName;
	}

	public String getBankCreditCardNum() {
		return bankCreditCardNum;
	}

	public void setBankCreditCardNum(String bankCreditCardNum) {
		this.bankCreditCardNum = bankCreditCardNum;
	}

	public String getBankDebitAcntCode() {
		return bankDebitAcntCode;
	}

	public void setBankDebitAcntCode(String bankDebitAcntCode) {
		this.bankDebitAcntCode = bankDebitAcntCode;
	}

	public String getBankDebitName() {
		return bankDebitName;
	}

	public void setBankDebitName(String bankDebitName) {
		this.bankDebitName = bankDebitName;
	}

	public String getBankDebitCardNum() {
		return bankDebitCardNum;
	}

	public void setBankDebitCardNum(String bankDebitCardNum) {
		this.bankDebitCardNum = bankDebitCardNum;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransCurrency() {
		return transCurrency;
	}

	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getTransComments() {
		return transComments;
	}

	public void setTransComments(String transComments) {
		this.transComments = transComments;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getBankMsgCode() {
		return bankMsgCode;
	}

	public void setBankMsgCode(String bankMsgCode) {
		this.bankMsgCode = bankMsgCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getHandleDesc() {
		return handleDesc;
	}

	public void setHandleDesc(String handleDesc) {
		this.handleDesc = handleDesc;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getOrgReqNum() {
		return orgReqNum;
	}

	public void setOrgReqNum(String orgReqNum) {
		this.orgReqNum = orgReqNum;
	}

	public BigDecimal getBankBalanceAfterTrans() {
		return bankBalanceAfterTrans;
	}

	public void setBankBalanceAfterTrans(BigDecimal bankBalanceAfterTrans) {
		this.bankBalanceAfterTrans = bankBalanceAfterTrans;
	}

	public BigDecimal getBankFrozenAmoumt() {
		return bankFrozenAmoumt;
	}

	public void setBankFrozenAmoumt(BigDecimal bankFrozenAmoumt) {
		this.bankFrozenAmoumt = bankFrozenAmoumt;
	}

	public BigDecimal getBankAvaliableAmount() {
		return bankAvaliableAmount;
	}

	public void setBankAvaliableAmount(BigDecimal bankAvaliableAmount) {
		this.bankAvaliableAmount = bankAvaliableAmount;
	}

	public Long getTransRate() {
		return transRate;
	}

	public void setTransRate(Long transRate) {
		this.transRate = transRate;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getDocumentStaff() {
		return documentStaff;
	}

	public void setDocumentStaff(String documentStaff) {
		this.documentStaff = documentStaff;
	}

	public String getDocumentReviewer() {
		return documentReviewer;
	}

	public void setDocumentReviewer(String documentReviewer) {
		this.documentReviewer = documentReviewer;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public Date getInterestsStartDate() {
		return interestsStartDate;
	}

	public void setInterestsStartDate(Date interestsStartDate) {
		this.interestsStartDate = interestsStartDate;
	}

	public String getBankCreditCode() {
		return bankCreditCode;
	}

	public void setBankCreditCode(String bankCreditCode) {
		this.bankCreditCode = bankCreditCode;
	}

	public String getBankCreditBranchName() {
		return bankCreditBranchName;
	}

	public void setBankCreditBranchName(String bankCreditBranchName) {
		this.bankCreditBranchName = bankCreditBranchName;
	}

	public String getBankCreditBranchCode() {
		return bankCreditBranchCode;
	}

	public void setBankCreditBranchCode(String bankCreditBranchCode) {
		this.bankCreditBranchCode = bankCreditBranchCode;
	}

	public String getBankCreditCustName() {
		return bankCreditCustName;
	}

	public void setBankCreditCustName(String bankCreditCustName) {
		this.bankCreditCustName = bankCreditCustName;
	}

	public String getBankDebitCode() {
		return bankDebitCode;
	}

	public void setBankDebitCode(String bankDebitCode) {
		this.bankDebitCode = bankDebitCode;
	}

	public String getBankDebitBranchName() {
		return bankDebitBranchName;
	}

	public void setBankDebitBranchName(String bankDebitBranchName) {
		this.bankDebitBranchName = bankDebitBranchName;
	}

	public String getBankDebitBranchCode() {
		return bankDebitBranchCode;
	}

	public void setBankDebitBranchCode(String bankDebitBranchCode) {
		this.bankDebitBranchCode = bankDebitBranchCode;
	}

	public String getBankDebitCustName() {
		return bankDebitCustName;
	}

	public void setBankDebitCustName(String bankDebitCustName) {
		this.bankDebitCustName = bankDebitCustName;
	}

}
