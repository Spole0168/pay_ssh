/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT_DTL_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustAcntDtlHis extends BaseEntity {
	
	private String id; // ID
	private String acntDtlCode; // ACNT_DTL_CODE
	private String cnlAcntCode; // CNL_ACNT_CODE
	private String cnlCustCode; // CNL_CUST_CODE
	private String custCode; // CUST_CODE
	private String transNum; // TRANS_NUM
	private String acntType; // ACNT_TYPE
	private String currency; // CURRENCY
	private BigDecimal amount; // AMOUNT
	private String direction; // DIRECTION
	private String transType; // TRANS_TYPE
	private BigDecimal balance; // BALANCE
	private BigDecimal preBalance; // PRE_BALANCE
	private Date transDate; // TRANS_DATE
	private Date transTime; // TRANS_TIME
	private String isPrinted; // IS_PRINTED
	private String voucherNum; // VOUCHER_NUM
	private String comments; // COMMENTS
	private String isValid; // IS_VALID
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getAcntDtlCode(){
		return acntDtlCode;
	}
	public void setAcntDtlCode(String acntDtlCode){
		this.acntDtlCode = acntDtlCode;
	}

	public String getCnlAcntCode(){
		return cnlAcntCode;
	}
	public void setCnlAcntCode(String cnlAcntCode){
		this.cnlAcntCode = cnlAcntCode;
	}

	public String getCnlCustCode(){
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode){
		this.cnlCustCode = cnlCustCode;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getTransNum(){
		return transNum;
	}
	public void setTransNum(String transNum){
		this.transNum = transNum;
	}

	public String getAcntType(){
		return acntType;
	}
	public void setAcntType(String acntType){
		this.acntType = acntType;
	}

	public String getCurrency(){
		return currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}

	public BigDecimal getAmount(){
		return amount;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}

	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction){
		this.direction = direction;
	}

	public String getTransType(){
		return transType;
	}
	public void setTransType(String transType){
		this.transType = transType;
	}

	public BigDecimal getBalance(){
		return balance;
	}
	public void setBalance(BigDecimal balance){
		this.balance = balance;
	}

	public BigDecimal getPreBalance(){
		return preBalance;
	}
	public void setPreBalance(BigDecimal preBalance){
		this.preBalance = preBalance;
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

	public String getIsPrinted(){
		return isPrinted;
	}
	public void setIsPrinted(String isPrinted){
		this.isPrinted = isPrinted;
	}

	public String getVoucherNum(){
		return voucherNum;
	}
	public void setVoucherNum(String voucherNum){
		this.voucherNum = voucherNum;
	}

	public String getComments(){
		return comments;
	}
	public void setComments(String comments){
		this.comments = comments;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
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

	
}
