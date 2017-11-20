/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_VIOLATION_RECORD
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysViolationRecord extends BaseEntity {
	
	
	private static final long serialVersionUID = 3016044488231933533L;
  	
	private String id; // ID
	private String reqNum; // REQ_NUM
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlIntfCode; // CNL_INTF_CODE
	private String violationType; // VIOLATION_TYPE
	private String violationId; // VIOLATION_ID
	private String violationDesc; // VIOLATION_DESC
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String violationSubType;
	
	
	public String getViolationSubType() {
		return violationSubType;
	}
	public void setViolationSubType(String violationSubType) {
		this.violationSubType = violationSubType;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getReqNum(){
		return reqNum;
	}
	public void setReqNum(String reqNum){
		this.reqNum = reqNum;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getCnlIntfCode(){
		return cnlIntfCode;
	}
	public void setCnlIntfCode(String cnlIntfCode){
		this.cnlIntfCode = cnlIntfCode;
	}

	public String getViolationType(){
		return violationType;
	}
	public void setViolationType(String violationType){
		this.violationType = violationType;
	}

	public String getViolationId(){
		return violationId;
	}
	public void setViolationId(String violationId){
		this.violationId = violationId;
	}

	public String getViolationDesc(){
		return violationDesc;
	}
	public void setViolationDesc(String violationDesc){
		this.violationDesc = violationDesc;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}

	
}
