package com.ibs.core.module.violationRecord.domain;

import java.util.Date;

/**
 * 
* @ClassName: SysViolationRecordIPExceptionDTO
* @Description: TODO(IP异常查询列表DTO)
* @author Frank
* @date 2016年7月18日 
*
 */
public class SysViolationRecordIPExceptionDTO
{
	private String id; // ID
	private String reqNum; // 商户号  T_SYS_VIOLATION_RECORD.REQ_NUM
	private String cnlIntfCode; // 网关接入号 T_SYS_VIOLATION_RECORD.CNL_INTF_CODE
	private String cnlCnlCode; // 渠道申请单号 T_SYS_VIOLATION_RECORD.CNL_CNL_CODE
	private String cnlCustCode;
	private String violationId; //异常IP  T_SYS_VIOLATION_RECORD.VIOLATION_ID
	private String transType; // 异常申请操作 T_CNL_TRANS.TRANS_TYPE
	private Date createTime; // 发生时间 T_SYS_VIOLATION_RECORD.CREATE_TIME
	 
	  
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReqNum() {
		return reqNum;
	}
	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
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
	public String getViolationId() {
		return violationId;
	}
	public void setViolationId(String violationId) {
		this.violationId = violationId;
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
	@Override
	public String toString() {
		return "SysViolationRecordIPExceptionDTO [id=" + id + ", reqNum=" + reqNum + ", cnlIntfCode=" + cnlIntfCode
				+ ", cnlCnlCode=" + cnlCnlCode + ", violationId=" + violationId + ", transType=" + transType
				+ ", createTime=" + createTime + "]";
	}


}
