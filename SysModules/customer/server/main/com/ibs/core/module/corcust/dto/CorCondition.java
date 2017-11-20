package com.ibs.core.module.corcust.dto;

public class CorCondition {
	private String custType;
	private String custCode;
	private String localName;
	private String certType;
	private String certNum;
	private String status;
	private String phoneNum;
	private String realNameLeve;
	private String custLevel;
	private String createTimeStart;
	private String createTimeEnd;
	
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getRealNameLeve() {
		return realNameLeve;
	}
	public void setRealNameLeve(String realNameLeve) {
		this.realNameLeve = realNameLeve;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	@Override
	public String toString() {
		return "CorCondition [custType=" + custType + ", custCode=" + custCode + ", localName=" + localName
				+ ", certType=" + certType + ", certNum=" + certNum + ", status=" + status + ", phoneNum=" + phoneNum
				+ ", realNameLeve=" + realNameLeve + ", custLevel=" + custLevel + ", createTimeStart=" + createTimeStart
				+ ", createTimeEnd=" + createTimeEnd + "]";
	}
	

	
	
}
