package com.ibs.core.module.account.domain;

public class Reconciliation {
	/**
	 * 渠道会员编号
	 */
	String cnl_customer_code;
	/**
	 * 会员姓名
	 */
	String name;
	/**
	 * 交易流水号
	 */
	String trans_num;
	/**
	 * 申请单号
	 */
	String request_num;
	/**
	 * 银行交易流水
	 */
	String bank_trans_num;
	/**
	 * 交易日期
	 */
	String trans_date;
	/**
	 * 交易类型
	 */
	String trans_type;
	/**
	 * 金额
	 */
	String amount;
	
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
	
	public String getTrans_num() {
		return trans_num;
	}
	public void setTrans_num(String trans_num) {
		this.trans_num = trans_num;
	}
	public String getRequest_num() {
		return request_num;
	}
	public void setRequest_num(String request_num) {
		this.request_num = request_num;
	}
	public String getBank_trans_num() {
		return bank_trans_num;
	}
	public void setBank_trans_num(String bank_trans_num) {
		this.bank_trans_num = bank_trans_num;
	}
	public String getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
