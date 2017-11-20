package com.ibs.core.module.account.domain;

public class CnlTransServiceClearing {
	
	private String clearing_date;
	private String cnl_customer_code;
	private String name;
	private String amount;
	private String fail_reason;
	private String cnl_balance;
	private String balance;
	private String gap_balance;
	public String getClearing_date() {
		return clearing_date;
	}
	public void setClearing_date(String clearing_date) {
		this.clearing_date = clearing_date;
	}
	public String getCnl_customer_code() {
		return cnl_customer_code;
	}
	public void setCnl_customer_code(String cnl_customer_code) {
		this.cnl_customer_code = cnl_customer_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFail_reason() {
		return fail_reason;
	}
	public void setFail_reason(String fail_reason) {
		this.fail_reason = fail_reason;
	}
	public String getCnl_balance() {
		return cnl_balance;
	}
	public void setCnl_balance(String cnl_balance) {
		this.cnl_balance = cnl_balance;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getGap_balance() {
		return gap_balance;
	}
	public void setGap_balance(String gap_balance) {
		this.gap_balance = gap_balance;
	}
	
	
	
}
