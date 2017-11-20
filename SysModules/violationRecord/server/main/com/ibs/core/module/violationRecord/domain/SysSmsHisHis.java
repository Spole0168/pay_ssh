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
 * @comments	: code generated based on database T_SYS_SMS_HIS_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysSmsHisHis extends BaseEntity {
	
	private String id; // ID
	private String tsid; // T_S_ID
	private String custCode; // CUST_CODE
	private String phonenum; // PHONENUM
	private String tplCode; // TPL_CODE
	private String smsContent; // SMS_CONTENT
	private String cnlCnlCode; // CNL_CNL_CODE
	private String smsCnlCode; // SMS_CNL_CODE
	private String msgType; // MSG_TYPE
	private String msgStatus; // MSG_STATUS
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

	public String getTsid(){
		return tsid;
	}
	public void setTsid(String tsid){
		this.tsid = tsid;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getPhonenum(){
		return phonenum;
	}
	public void setPhonenum(String phonenum){
		this.phonenum = phonenum;
	}

	public String getTplCode(){
		return tplCode;
	}
	public void setTplCode(String tplCode){
		this.tplCode = tplCode;
	}

	public String getSmsContent(){
		return smsContent;
	}
	public void setSmsContent(String smsContent){
		this.smsContent = smsContent;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getSmsCnlCode(){
		return smsCnlCode;
	}
	public void setSmsCnlCode(String smsCnlCode){
		this.smsCnlCode = smsCnlCode;
	}

	public String getMsgType(){
		return msgType;
	}
	public void setMsgType(String msgType){
		this.msgType = msgType;
	}

	public String getMsgStatus(){
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus){
		this.msgStatus = msgStatus;
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
