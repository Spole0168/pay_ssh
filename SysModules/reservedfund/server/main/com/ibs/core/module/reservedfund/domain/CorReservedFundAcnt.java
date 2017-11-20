/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.reservedfund.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_RESERVED_FUND_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorReservedFundAcnt extends BaseEntity {
	
	private String id; // ID
	private String bankAcntCode; // BANK_ACNT_CODE
	private String cnlCustCode; // CNL_CUST_CODE
	private String country; // COUNTRY
	private String custName; // CUST_NAME
	private String certType; // CERT_TYPE
	private String certNum; // CERT_NUM
	private String bankName; // BANK_NAME
	private String bankCode; // BANK_CODE
	private String bankBranchCode; // BANK_BRANCH_CODE
	private String bankBranchName; // BANK_BRANCH_NAME
	private String bankCardNum; // BANK_CARD_NUM
	private String bankCardType; // BANK_CARD_TYPE
	private String status; // STATUS
	private String currency; // CURRENCY
	private String acntCategory; // ACNT_CATEGORY
	private String acntType; // ACNT_TYPE
	private Date lastTransTime; // LAST_TRANS_TIME
	private BigDecimal frozenAmoumt; // FROZEN_AMOUMT
	private BigDecimal avaliableAmount; // AVALIABLE_AMOUNT
	private BigDecimal balance; // BALANCE
	private String bankNum; // BANK_NUM
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String isValid; // IS_VALID
	private String creator; // CREATOR
	private String updator; // UPDATOR
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getBankAcntCode(){
		return bankAcntCode;
	}
	public void setBankAcntCode(String bankAcntCode){
		this.bankAcntCode = bankAcntCode;
	}

	public String getCnlCustCode(){
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode){
		this.cnlCustCode = cnlCustCode;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	public String getCertType(){
		return certType;
	}
	public void setCertType(String certType){
		this.certType = certType;
	}

	public String getCertNum(){
		return certNum;
	}
	public void setCertNum(String certNum){
		this.certNum = certNum;
	}

	public String getBankName(){
		return bankName;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankCode(){
		return bankCode;
	}
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}

	public String getBankBranchCode(){
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode){
		this.bankBranchCode = bankBranchCode;
	}

	public String getBankBranchName(){
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName){
		this.bankBranchName = bankBranchName;
	}

	public String getBankCardNum(){
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum){
		this.bankCardNum = bankCardNum;
	}

	public String getBankCardType(){
		return bankCardType;
	}
	public void setBankCardType(String bankCardType){
		this.bankCardType = bankCardType;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getCurrency(){
		return currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getAcntCategory(){
		return acntCategory;
	}
	public void setAcntCategory(String acntCategory){
		this.acntCategory = acntCategory;
	}

	public String getAcntType(){
		return acntType;
	}
	public void setAcntType(String acntType){
		this.acntType = acntType;
	}

	public Date getLastTransTime(){
		return lastTransTime;
	}
	public void setLastTransTime(Date lastTransTime){
		this.lastTransTime = lastTransTime;
	}

	public BigDecimal getFrozenAmoumt(){
		return frozenAmoumt;
	}
	public void setFrozenAmoumt(BigDecimal frozenAmoumt){
		this.frozenAmoumt = frozenAmoumt;
	}

	public BigDecimal getAvaliableAmount(){
		return avaliableAmount;
	}
	public void setAvaliableAmount(BigDecimal avaliableAmount){
		this.avaliableAmount = avaliableAmount;
	}

	public BigDecimal getBalance(){
		return balance;
	}
	public void setBalance(BigDecimal balance){
		this.balance = balance;
	}

	public String getBankNum(){
		return bankNum;
	}
	public void setBankNum(String bankNum){
		this.bankNum = bankNum;
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

	
}
