/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.dto;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSysIntfIpLimitDto extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // ID
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlCustCode; // CNL_CUST_CODE
	private String cnlIntfCode; // CNL_INTF_CODE
	private String ipRangeFrom; // IP_RANGE_FROM
	private String ipRangeTo; // IP_RANGE_TO
	private String comments;
	private String ipOpt;//IP_OPT
	private Date ipOptTime;//IP_OPT_TIME
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getIpOpt() {
		return ipOpt;
	}
	public void setIpOpt(String ipOpt) {
		this.ipOpt = ipOpt;
	}
	public Date getIpOptTime() {
		return ipOptTime;
	}
	public void setIpOptTime(Date ipOptTime) {
		this.ipOptTime = ipOptTime;
	}
	@Override
	public String toString() {
		return "CnlSysIntfIpLimitDto [id=" + id + ", cnlCnlCode=" + cnlCnlCode + ", cnlCustCode=" + cnlCustCode
				+ ", cnlIntfCode=" + cnlIntfCode + ", ipRangeFrom=" + ipRangeFrom + ", ipRangeTo=" + ipRangeTo
				+ ", comments=" + comments + ", ipOpt=" + ipOpt + ", ipOptTime=" + ipOptTime + "]";
	}
	
}
