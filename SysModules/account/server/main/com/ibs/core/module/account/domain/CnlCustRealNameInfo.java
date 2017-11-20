/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_REAL_NAME_INFO
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustRealNameInfo extends BaseEntity {
	
	private String id; // ID
	private String cnlCustCode; // CNL_CUST_CODE
	private String custCode; // CUST_CODE
	private String cnlCnlCode; // CNL_CNL_CODE
	private String realNameCnl; // REAL_NAME_CNL
	private Date realNameTime; // REAL_NAME_TIME
	private String realNameType; // REAL_NAME_TYPE
	private String realNameCode; // REAL_NAME_CODE
	private String realNameStatus; // REAL_NAME_STATUS
	private Date realNameExpireDate; // REAL_NAME_EXPIRE_DATE
	private String isValid; // IS_VALID
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getCnlCustCode(){
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode){
		this.cnlCustCode = cnlCustCode;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getRealNameCnl(){
		return realNameCnl;
	}
	public void setRealNameCnl(String realNameCnl){
		this.realNameCnl = realNameCnl;
	}

	public Date getRealNameTime(){
		return realNameTime;
	}
	public void setRealNameTime(Date realNameTime){
		this.realNameTime = realNameTime;
	}

	public String getRealNameType(){
		return realNameType;
	}
	public void setRealNameType(String realNameType){
		this.realNameType = realNameType;
	}

	public String getRealNameCode(){
		return realNameCode;
	}
	public void setRealNameCode(String realNameCode){
		this.realNameCode = realNameCode;
	}

	public String getRealNameStatus(){
		return realNameStatus;
	}
	public void setRealNameStatus(String realNameStatus){
		this.realNameStatus = realNameStatus;
	}

	public Date getRealNameExpireDate(){
		return realNameExpireDate;
	}
	public void setRealNameExpireDate(Date realNameExpireDate){
		this.realNameExpireDate = realNameExpireDate;
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

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
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

	
}
