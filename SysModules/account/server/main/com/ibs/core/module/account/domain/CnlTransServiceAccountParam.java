package com.ibs.core.module.account.domain;

import java.util.Date;

public class CnlTransServiceAccountParam {
	private String cnlCnlCode;
	private String reqInnerNum;
	private String cnl_customer_code;
	private String customer_name;
	private String cert_num;
	private Date cert_expire_date ;
	private String bank_code;
	private String bank_account_name;
	private String bank_card_num;
	private String phonenum;
	private String address;
	private Date msgDate;
	private String msgId;
	private String bank_provice;
	private String bank_city;
	private String bank_branch;
	
	
	public String getBank_provice() {
		return bank_provice;
	}
	public void setBank_provice(String bank_provice) {
		this.bank_provice = bank_provice;
	}
	public String getBank_city() {
		return bank_city;
	}
	public void setBank_city(String bank_city) {
		this.bank_city = bank_city;
	}
	public String getBank_branch() {
		return bank_branch;
	}
	public void setBank_branch(String bank_branch) {
		this.bank_branch = bank_branch;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public Date getCert_expire_date() {
		return cert_expire_date;
	}
	public Date getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}
	public void setCert_expire_date(Date cert_expire_date) {
		this.cert_expire_date = cert_expire_date;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getReqInnerNum() {
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
	}
	public String getCnl_customer_code() {
		return cnl_customer_code;
	}
	public void setCnl_customer_code(String cnl_customer_code) {
		this.cnl_customer_code = cnl_customer_code;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCert_num() {
		return cert_num;
	}
	public void setCert_num(String cert_num) {
		this.cert_num = cert_num;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_account_name() {
		return bank_account_name;
	}
	public void setBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
	}
	public String getBank_card_num() {
		return bank_card_num;
	}
	public void setBank_card_num(String bank_card_num) {
		this.bank_card_num = bank_card_num;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
