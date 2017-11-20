package com.ibs.core.module.corcust.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CorDto{
	
	
	private String bankName; // BANK_NAME  //开户行名称
	private String bankCardNum; // BANK_CARD_NUM  // 开户帐号
	private String custName; // CUST_NAME  //账户名
	private String bankNum;//BANK_NUM //联行号
	private Date transDate; // TRANS_DATE   //交易日期
	private Date transTime; // TRANS_TIME   //交易时间
	private String direction; // DIRECTION  //交易方向
	private String transType; // TRANS_TYPE //交易类别
	private String bankTransNum; // BANK_TRANS_NUM  //银行流水帐号
	private String transCurrency; // TRANS_CURRENCY  //交易币种
	private BigDecimal amount; // AMOUNT  // 交易金额
	private BigDecimal bankBalanceAfterTrans; // BANK_BALANCE_AFTER_TRANS // 交易后余额
	private BigDecimal bankAvaliableAmount; // BANK_AVALIABLE_AMOUNT // 可用余额
	private BigDecimal bankFrozenAmoumt; // BANK_FROZEN_AMOUMT//冻结余额
	private String transComments; // TRANS_COMMENTS  // 摘要
	private Long transRate; // TRANS_RATE  //汇率
	private String otherBankName; // BANK_NAME  //对方开户行名称  
	private String otherBankNum; // BANK_ACNT_CODE//  对方行号 
	private String otherBankCarNum; // BANK_CARD_NUM  // 对方帐号
	private String otherCustName; // CUST_NAME  //对方账户名
	private Date startTime; //开始时间
	private Date endTime; //结束时间
	private BigDecimal maxAmount; //最大金额
	private BigDecimal minAmount;//最小金额
	private String documentStaff;//DOCUMENT_STAFF  制单员
	private String documentReviewer;  //   DOCUMENT_REVIEWER   审单员
    private String certType; //CERT_TYPE  凭证类型
    private String certNum; //CERT_NUM   凭证号码
    private Date interestsStartDate; //INTERESTS_START_DATE   起息日期
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardNum() {
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getBankTransNum() {
		return bankTransNum;
	}
	public void setBankTransNum(String bankTransNum) {
		this.bankTransNum = bankTransNum;
	}
	public String getTransCurrency() {
		return transCurrency;
	}
	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getBankBalanceAfterTrans() {
		return bankBalanceAfterTrans;
	}
	public void setBankBalanceAfterTrans(BigDecimal bankBalanceAfterTrans) {
		this.bankBalanceAfterTrans = bankBalanceAfterTrans;
	}
	public BigDecimal getBankAvaliableAmount() {
		return bankAvaliableAmount;
	}
	public void setBankAvaliableAmount(BigDecimal bankAvaliableAmount) {
		this.bankAvaliableAmount = bankAvaliableAmount;
	}
	public BigDecimal getBankFrozenAmoumt() {
		return bankFrozenAmoumt;
	}
	public void setBankFrozenAmoumt(BigDecimal bankFrozenAmoumt) {
		this.bankFrozenAmoumt = bankFrozenAmoumt;
	}
	public String getTransComments() {
		return transComments;
	}
	public void setTransComments(String transComments) {
		this.transComments = transComments;
	}
	public Long getTransRate() {
		return transRate;
	}
	public void setTransRate(Long transRate) {
		this.transRate = transRate;
	}
	public String getOtherBankName() {
		return otherBankName;
	}
	public void setOtherBankName(String otherBankName) {
		this.otherBankName = otherBankName;
	}
	public String getOtherBankNum() {
		return otherBankNum;
	}
	public void setOtherBankNum(String otherBankNum) {
		this.otherBankNum = otherBankNum;
	}
	public String getOtherBankCarNum() {
		return otherBankCarNum;
	}
	public void setOtherBankCarNum(String otherBankCarNum) {
		this.otherBankCarNum = otherBankCarNum;
	}
	public String getOtherCustName() {
		return otherCustName;
	}
	public void setOtherCustName(String otherCustName) {
		this.otherCustName = otherCustName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}
	public BigDecimal getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
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
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
    
	
}
