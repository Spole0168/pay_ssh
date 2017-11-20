package com.ibs.core.module.account.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibs.portal.framework.server.domain.BaseEntity;

public class CnlTransTraceService extends BaseEntity{
	private String reqInnerNum; //申请单流水号
	private String msgId;
	
	@JsonProperty("cnl_customer_code")
	private String cnlCustCode; //客户渠道编码
	private String reqNum;//申请单号
	private String reqBatch;//申请单批次号
	
	@JsonProperty("amount")
	private String transAmount; //交易金额
	
	@JsonProperty("cnl_customer_balance")
	private String transLatestAmount;//最后一次交易金额
	
	@JsonProperty("trans_date")
	private Timestamp transDate;//交易日期
	private String stlNum;//清算号
	private String transComments;//交易说明
	private String termialType;//终端类型
	
	@JsonProperty("trace_dc")
	private String traceDc;
	private String traceType;
	private String traceSubType;
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getTraceDc() {
		return traceDc;
	}
	public void setTraceDc(String traceDc) {
		this.traceDc = traceDc;
	}
	public String getTraceType() {
		return traceType;
	}
	public void setTraceType(String traceType) {
		this.traceType = traceType;
	}
	public String getTraceSubType() {
		return traceSubType;
	}
	public void setTraceSubType(String traceSubType) {
		this.traceSubType = traceSubType;
	}
	public String getTermialType() {
		return termialType;
	}
	public void setTermialType(String termialType) {
		this.termialType = termialType;
	}
	public String getReqInnerNum() {
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getReqNum() {
		return reqNum;
	}
	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}
	
	
	public String getReqBatch() {
		return reqBatch;
	}
	public void setReqBatch(String reqBatch) {
		this.reqBatch = reqBatch;
	}
	public String getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransLatestAmount() {
		return transLatestAmount;
	}
	public void setTransLatestAmount(String transLatestAmount) {
		this.transLatestAmount = transLatestAmount;
	}


	public Timestamp getTransDate() {
		return transDate;
	}
	public void setTransDate(Timestamp transDate) {
		this.transDate = transDate;
	}
	public String getStlNum() {
		return stlNum;
	}
	public void setStlNum(String stlNum) {
		this.stlNum = stlNum;
	}
	public String getTransComments() {
		return transComments;
	}
	public void setTransComments(String transComments) {
		this.transComments = transComments;
	}
}
