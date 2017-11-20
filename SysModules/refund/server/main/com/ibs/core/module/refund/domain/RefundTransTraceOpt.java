package com.ibs.core.module.refund.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class RefundTransTraceOpt extends BaseEntity {
	private String id; // ID
	private String refNum; // REF_NUM
	private String custCode; // CUST_CODE
	private String cnlCustCode; // CNL_CUST_CODE
	private String reqNum; // REQ_NUM
	private Long reqBatch; // REQ_BATCH
	private String operator; // OPERATOR
	private Date optTime; // OPT_TIME
	private String optDesc; // OPT_DESC
	private String optResult; // OPT_RESULT
	private String optCode; // OPT_CODE
	private String recordType; // RECORD_TYPE
	private String isValid; // IS_VALID
	private String creator; // CREATOR
	private Date createTime; // CREATE_TIME
	private String updator; // UPDATOR
	private Date updateTime; // UPDATE_TIME
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getRefNum(){
		return refNum;
	}
	public void setRefNum(String refNum){
		this.refNum = refNum;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getCnlCustCode(){
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode){
		this.cnlCustCode = cnlCustCode;
	}

	public String getReqNum(){
		return reqNum;
	}
	public void setReqNum(String reqNum){
		this.reqNum = reqNum;
	}

	public Long getReqBatch(){
		return reqBatch;
	}
	public void setReqBatch(Long reqBatch){
		this.reqBatch = reqBatch;
	}

	public String getOperator(){
		return operator;
	}
	public void setOperator(String operator){
		this.operator = operator;
	}

	public Date getOptTime(){
		return optTime;
	}
	public void setOptTime(Date optTime){
		this.optTime = optTime;
	}

	public String getOptDesc(){
		return optDesc;
	}
	public void setOptDesc(String optDesc){
		this.optDesc = optDesc;
	}

	public String getOptResult(){
		return optResult;
	}
	public void setOptResult(String optResult){
		this.optResult = optResult;
	}

	public String getOptCode(){
		return optCode;
	}
	public void setOptCode(String optCode){
		this.optCode = optCode;
	}

	public String getRecordType(){
		return recordType;
	}
	public void setRecordType(String recordType){
		this.recordType = recordType;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
	}

	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	
}

