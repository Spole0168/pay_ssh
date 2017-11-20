package com.ibs.core.module.account.domain;

public class CnlTransTraceServiceBalance {
	/**
	 * 编号
	 */
	private String cnl_customer_code;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 余额
	 */
	private String balance;
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
}
