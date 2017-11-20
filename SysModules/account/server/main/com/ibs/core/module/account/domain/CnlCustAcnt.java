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
 * @comments	: code generated based on database T_CNL_CUST_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustAcnt extends BaseEntity {
	
	private String id; // ID
	private String cnlCustCode; // CNL_CUST_CODE
	private String custCode; // CUST_CODE
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlAcntCode; // CNL_ACNT_CODE
	private String pcnlAcntCode; // P_CNL_ACNT_CODE
	private String bankAcntCode; // BANK_ACNT_CODE
	private BigDecimal balance; // BALANCE
	private BigDecimal balanceAvalible; // BALANCE_AVALIBLE
	private BigDecimal balanceFrozen; // BALANCE_FROZEN
	private BigDecimal balanceReserved; // BALANCE_RESERVED
	private String isSubsidiary; // IS_SUBSIDIARY
	private String currency; // CURRENCY
	private Date regTime; // REG_TIME
	private String acntStatus; // ACNT_STATUS
	private String acntType; // ACNT_TYPE
	private String dac; // DAC
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

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getCnlAcntCode(){
		return cnlAcntCode;
	}
	public void setCnlAcntCode(String cnlAcntCode){
		this.cnlAcntCode = cnlAcntCode;
	}

	public String getPcnlAcntCode(){
		return pcnlAcntCode;
	}
	public void setPcnlAcntCode(String pcnlAcntCode){
		this.pcnlAcntCode = pcnlAcntCode;
	}

	public String getBankAcntCode(){
		return bankAcntCode;
	}
	public void setBankAcntCode(String bankAcntCode){
		this.bankAcntCode = bankAcntCode;
	}

	

	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getBalanceAvalible() {
		return balanceAvalible;
	}
	public void setBalanceAvalible(BigDecimal balanceAvalible) {
		this.balanceAvalible = balanceAvalible;
	}
	public BigDecimal getBalanceFrozen() {
		return balanceFrozen;
	}
	public void setBalanceFrozen(BigDecimal balanceFrozen) {
		this.balanceFrozen = balanceFrozen;
	}
	public BigDecimal getBalanceReserved() {
		return balanceReserved;
	}
	public void setBalanceReserved(BigDecimal balanceReserved) {
		this.balanceReserved = balanceReserved;
	}
	public String getIsSubsidiary(){
		return isSubsidiary;
	}
	public void setIsSubsidiary(String isSubsidiary){
		this.isSubsidiary = isSubsidiary;
	}

	public String getCurrency(){
		return currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}

	public Date getRegTime(){
		return regTime;
	}
	public void setRegTime(Date regTime){
		this.regTime = regTime;
	}

	public String getAcntStatus(){
		return acntStatus;
	}
	public void setAcntStatus(String acntStatus){
		this.acntStatus = acntStatus;
	}

	public String getAcntType(){
		return acntType;
	}
	public void setAcntType(String acntType){
		this.acntType = acntType;
	}

	public String getDac(){
		return dac;
	}
	public void setDac(String dac){
		this.dac = dac;
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
