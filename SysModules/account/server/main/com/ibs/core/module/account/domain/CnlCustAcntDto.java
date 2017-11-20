package com.ibs.core.module.account.domain;

import java.math.BigDecimal;

public class CnlCustAcntDto {

	private String id;//客户编号
	
	private String cnlCustCode;//客户编号
	
	private String localName;//客户名称
	
	private String currency;//币种
	
	private BigDecimal balanceAvalible;//可用余额
	
	private BigDecimal balanceFrozen;//冻结余额
	
	private BigDecimal balance;//余额
	
	private String acntStatus;//账户状态


	public String getAcntStatus() {
		return acntStatus;
	}

	public void setAcntStatus(String acntStatus) {
		this.acntStatus = acntStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCnlCustCode() {
		return cnlCustCode;
	}

	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}





	
	
}
