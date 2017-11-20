package com.ibs.core.module.cnlmgr.dto;

public class CnlSysIntfIpLimitConditionDto {
	private String cnlCustCode;
	private String cnlCnlCode;
	private String cnlIntfCode;
	private String ipRangeFrom;
	private String ipRangeTo;
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getCnlIntfCode() {
		return cnlIntfCode;
	}
	public void setCnlIntfCode(String cnlIntfCode) {
		this.cnlIntfCode = cnlIntfCode;
	}
	public String getIpRangeFrom() {
		return ipRangeFrom;
	}
	public void setIpRangeFrom(String ipRangeFrom) {
		this.ipRangeFrom = ipRangeFrom;
	}
	public String getIpRangeTo() {
		return ipRangeTo;
	}
	public void setIpRangeTo(String ipRangeTo) {
		this.ipRangeTo = ipRangeTo;
	}
	@Override
	public String toString() {
		return "CnlSysIntfIpLimitConditionDto [cnlCustCode=" + cnlCustCode + ", cnlCnlCode=" + cnlCnlCode
				+ ", cnlIntfCode=" + cnlIntfCode + ", IPRangeFrom=" + ipRangeFrom + ", IPRangeTo=" + ipRangeTo + "]";
	}

	
	
}
