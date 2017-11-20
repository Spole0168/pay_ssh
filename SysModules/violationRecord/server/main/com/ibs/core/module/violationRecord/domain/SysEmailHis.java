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
 * @comments	: code generated based on database T_SYS_EMAIL_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysEmailHis extends BaseEntity {
	
	private String id; // ID
	private String custCode; // CUST_CODE
	private String cnlCustCode; // CNL_CUST_CODE
	private String emailAddr; // EMAIL_ADDR
	private String tplCode; // TPL_CODE
	private String emailCnlCode; // EMAIL_CNL_CODE
	private String smsType; // SMS_TYPE
	private String smsStatus; // SMS_STATUS
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
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

	public String getEmailAddr(){
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr){
		this.emailAddr = emailAddr;
	}

	public String getTplCode(){
		return tplCode;
	}
	public void setTplCode(String tplCode){
		this.tplCode = tplCode;
	}

	public String getEmailCnlCode(){
		return emailCnlCode;
	}
	public void setEmailCnlCode(String emailCnlCode){
		this.emailCnlCode = emailCnlCode;
	}

	public String getSmsType(){
		return smsType;
	}
	public void setSmsType(String smsType){
		this.smsType = smsType;
	}

	public String getSmsStatus(){
		return smsStatus;
	}
	public void setSmsStatus(String smsStatus){
		this.smsStatus = smsStatus;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
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
