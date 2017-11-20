/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SMS_VALID_CODE_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSmsValidCodeHis extends BaseEntity {
	
	private String id; // ID
	private String cnlCnlCode; // CNL_CNL_CODE
	private String validNum; // VALID_NUM
	private String validCode; // VALID_CODE
	private Date expireDate; // EXPIRE_DATE
	private String type; // TYPE
	private String result; // RESULT
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

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getValidNum(){
		return validNum;
	}
	public void setValidNum(String validNum){
		this.validNum = validNum;
	}

	public String getValidCode(){
		return validCode;
	}
	public void setValidCode(String validCode){
		this.validCode = validCode;
	}

	public Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(Date expireDate){
		this.expireDate = expireDate;
	}

	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}

	public String getResult(){
		return result;
	}
	public void setResult(String result){
		this.result = result;
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
