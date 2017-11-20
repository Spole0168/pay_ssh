package com.ibs.core.module.topup.dto;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class TopupTransTraceConditionDto extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String reqNum; // 渠道申请单号
	private String reqInnerNum; // 系统申请单流水号
	private String cnlCnlCode; // 渠道编码
	private String cnlCustCode; // 客户编号
	private String transStartTime; // 交易开始时间
	private String transEndTime; // 交易结束时间
	private String handleStatus; // 处理状态
	private String cnlSysName; // 渠道名称
	private String localName; // 客户名称

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getReqInnerNum() {
		return reqInnerNum;
	}

	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
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

	public String getTransStartTime() {
		return transStartTime;
	}

	public void setTransStartTime(String transStartTime) {
		this.transStartTime = transStartTime;
	}

	public String getTransEndTime() {
		return transEndTime;
	}

	public void setTransEndTime(String transEndTime) {
		this.transEndTime = transEndTime;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getCnlSysName() {
		return cnlSysName;
	}

	public void setCnlSysName(String cnlSysName) {
		this.cnlSysName = cnlSysName;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TopupTransTraceDto [id=" + id);
		sb.append(",reqNum=" + reqNum);
		sb.append(",reqInnerNum=" + reqInnerNum);
		sb.append(",cnlCnlCode=" + cnlCnlCode);
		sb.append(",cnlCustCode=" + cnlCustCode);
		sb.append(",transStartTime=" + transStartTime);
		sb.append(",transEndTime=" + transEndTime);
		sb.append(",handleStatus=" + handleStatus);
		sb.append(",cnlSysName=" + cnlSysName);
		sb.append(",localName=" + localName);
		sb.append("]");
		return sb.toString();
	}
}
