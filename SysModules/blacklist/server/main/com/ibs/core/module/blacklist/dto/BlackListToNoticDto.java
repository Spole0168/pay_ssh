package com.ibs.core.module.blacklist.dto;

import java.util.Date;

public class BlackListToNoticDto {
	
	
//	private List<String> email;
//	private List<String> phone;
//	private List<String> country;
//	private List<String> card; 
	
	private String email;
	private String phone;
	private String country;
	private String card;
	private String violationSubType; //黑名单类型   T_SYS_VIOLATION_RECORD.VIOLATION_TYPE
	private String violationId;//黑名单值  T_SYS_VIOLATION_RECORD.VIOLATION_ID
	private String num;//商户号 T_SYS_VIOLATION_RECORD.REQ_NUM
	private String cnlIntfCode;//网关接入号 T_SYS_VIOLATION_RECORD.CNL_INTF_CODE
	private String cnlCnlCode;//渠道申请单号 T_SYS_VIOLATION_RECORD.CNL_CNL_CODE
	private String transType;//异常申请操作 T_CNL_TRANS.TRANS_TYPE
	private Date  createTime;//发生时间 T_SYS_VIOLATION_RECORD.CREATE_TIME
	
	 
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
 
	 
	public String getViolationSubType() {
		return violationSubType;
	}
	public void setViolationSubType(String violationSubType) {
		this.violationSubType = violationSubType;
	}
	public String getViolationId() {
		return violationId;
	}
	public void setViolationId(String violationId) {
		this.violationId = violationId;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCnlIntfCode() {
		return cnlIntfCode;
	}
	public void setCnlIntfCode(String cnlIntfCode) {
		this.cnlIntfCode = cnlIntfCode;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	 
	
	
	
}
