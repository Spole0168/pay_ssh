/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankIntf extends BaseEntity {
	////s
	private String id; // ID
	private String bankInnerCode; // BANK_INNER_CODE
	private String intfCode; // INTF_CODE
	private String country; // COUNTRY
	private String bankCode; // BANK_CODE
	private String verificationType; // VERIFICATION_TYPE
	private String accessType; // ACCESS_TYPE
	private String comments; // COMMENTS
	private String direction; // DIRECTION
	private String serviceUrl; // SERVICE_URL
	private String serviceUrlFormat; // SERVICE_URL_FORMAT
	private String techSupportName; // TECH_SUPPORT_NAME
	private String techSupportPhonenum; // TECH_SUPPORT_PHONENUM
	private String techSupportEmail; // TECH_SUPPORT_EMAIL
	private String businessSupportName; // BUSINESS_SUPPORT_NAME
	private String businessSupportPhonenum; // BUSINESS_SUPPORT_PHONENUM
	private String businessSupportEmail; // BUSINESS_SUPPORT_EMAIL
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

	public String getBankInnerCode(){
		return bankInnerCode;
	}
	public void setBankInnerCode(String bankInnerCode){
		this.bankInnerCode = bankInnerCode;
	}

	public String getIntfCode(){
		return intfCode;
	}
	public void setIntfCode(String intfCode){
		this.intfCode = intfCode;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	public String getBankCode(){
		return bankCode;
	}
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}

	public String getVerificationType(){
		return verificationType;
	}
	public void setVerificationType(String verificationType){
		this.verificationType = verificationType;
	}

	public String getAccessType(){
		return accessType;
	}
	public void setAccessType(String accessType){
		this.accessType = accessType;
	}

	public String getComments(){
		return comments;
	}
	public void setComments(String comments){
		this.comments = comments;
	}

	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction){
		this.direction = direction;
	}

	public String getServiceUrl(){
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl){
		this.serviceUrl = serviceUrl;
	}

	public String getServiceUrlFormat(){
		return serviceUrlFormat;
	}
	public void setServiceUrlFormat(String serviceUrlFormat){
		this.serviceUrlFormat = serviceUrlFormat;
	}

	public String getTechSupportName(){
		return techSupportName;
	}
	public void setTechSupportName(String techSupportName){
		this.techSupportName = techSupportName;
	}

	public String getTechSupportPhonenum(){
		return techSupportPhonenum;
	}
	public void setTechSupportPhonenum(String techSupportPhonenum){
		this.techSupportPhonenum = techSupportPhonenum;
	}

	public String getTechSupportEmail(){
		return techSupportEmail;
	}
	public void setTechSupportEmail(String techSupportEmail){
		this.techSupportEmail = techSupportEmail;
	}

	public String getBusinessSupportName(){
		return businessSupportName;
	}
	public void setBusinessSupportName(String businessSupportName){
		this.businessSupportName = businessSupportName;
	}

	public String getBusinessSupportPhonenum(){
		return businessSupportPhonenum;
	}
	public void setBusinessSupportPhonenum(String businessSupportPhonenum){
		this.businessSupportPhonenum = businessSupportPhonenum;
	}

	public String getBusinessSupportEmail(){
		return businessSupportEmail;
	}
	public void setBusinessSupportEmail(String businessSupportEmail){
		this.businessSupportEmail = businessSupportEmail;
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
