package com.ibs.core.module.account.domain;

import java.math.BigDecimal;

public class OperatingAcntDto {
	
	private String id;//帐户表自增ID;

    private String cnlCustCode;//客户编号
    
    private String localName;//客户名称
	
	private String cnlAcntCode;//客户账号
	
	private String acntType;//帐类
	
	private String currency;//币种
	
	private BigDecimal balanceAvalible;//可用余额
	
	private BigDecimal balanceFrozen;//冻结余额
	
	private BigDecimal balance;//余额

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

	public String getCnlAcntCode() {
		return cnlAcntCode;
	}

	public void setCnlAcntCode(String cnlAcntCode) {
		this.cnlAcntCode = cnlAcntCode;
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
