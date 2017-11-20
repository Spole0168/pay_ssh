package com.ibs.core.module.account.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CnlCustAcntDtlDto {
	private String id; // ID
	private String acntDtlCode;
	private String cnlAcntCode; // CNL_ACNT_CODE
	private String cnlCustCode; // CUST_CODE
	private String transNum; // TRANS_NUM
	private String acntType; // ACNT_TYPE
	private String currency; // CURRENCY
	private BigDecimal amount; // AMOUNT
	private String direction; // DIRECTION
	private BigDecimal balance; // BALANCE
	private Date transTime; // TRANS_TIME
	private String isPrinted; // IS_PRINTED
	private String voucherNum; // VOUCHER_NUM
	private String comments; // COMMENTS
	private Date trnasTime; //交易流水中的交易时间
	private Date createTime;
	private String localName;
	private String cnlCnlCode;
	
	
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAcntDtlCode() {
		return acntDtlCode;
	}
	public void setAcntDtlCode(String acntDtlCode) {
		this.acntDtlCode = acntDtlCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCnlAcntCode() {
		return cnlAcntCode;
	}
	public void setCnlAcntCode(String cnlAcntCode) {
		this.cnlAcntCode = cnlAcntCode;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getTransNum() {
		return transNum;
	}
	public void setTransNum(String transNum) {
		this.transNum = transNum;
	}
	public String getAcntType() {
		return acntType;
	}
	public void setAcntType(String acntType) {
		this.acntType = acntType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public String getIsPrinted() {
		return isPrinted;
	}
	public void setIsPrinted(String isPrinted) {
		this.isPrinted = isPrinted;
	}
	public String getVoucherNum() {
		return voucherNum;
	}
	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getTrnasTime() {
		return trnasTime;
	}
	public void setTrnasTime(Date trnasTime) {
		this.trnasTime = trnasTime;
	}

}
