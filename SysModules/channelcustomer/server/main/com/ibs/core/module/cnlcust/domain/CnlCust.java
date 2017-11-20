/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCust extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // ID
	private String cnlCustCode; // CNL_CUST_CODE
	private String custCode; // CUST_CODE
	private String localName; // LOCAL_NAME
	private String firstName; // FIRST_NAME
	private String lastName; // LAST_NAME
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlCustType; // CNL_CUST_TYPE
	private String custStatus; // CUST_STATUS
	private String country; // COUNTRY
	private Date regTime; // REG_TIME
	private String certType; // CERT_TYPE
	private String certNum; // CERT_NUM
	private String certCopy; // CERT_COPY
	private Date certExpireDate; // CERT_EXPIRE_DATE
	private String custLevel; // CUST_LEVEL
	private String realNameLevel; // REAL_NAME_LEVEL
	private String isMerchent; // IS_MERCHENT
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

	public String getLocalName(){
		return localName;
	}
	public void setLocalName(String localName){
		this.localName = localName;
	}

	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getCnlCustType(){
		return cnlCustType;
	}
	public void setCnlCustType(String cnlCustType){
		this.cnlCustType = cnlCustType;
	}

	public String getCustStatus(){
		return custStatus;
	}
	public void setCustStatus(String custStatus){
		this.custStatus = custStatus;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	public Date getRegTime(){
		return regTime;
	}
	public void setRegTime(Date regTime){
		this.regTime = regTime;
	}

	public String getCertType(){
		return certType;
	}
	public void setCertType(String certType){
		this.certType = certType;
	}

	public String getCertNum(){
		return certNum;
	}
	public void setCertNum(String certNum){
		this.certNum = certNum;
	}

	public String getCertCopy(){
		return certCopy;
	}
	public void setCertCopy(String certCopy){
		this.certCopy = certCopy;
	}

	public Date getCertExpireDate(){
		return certExpireDate;
	}
	public void setCertExpireDate(Date certExpireDate){
		this.certExpireDate = certExpireDate;
	}

	public String getCustLevel(){
		return custLevel;
	}
	public void setCustLevel(String custLevel){
		this.custLevel = custLevel;
	}

	public String getRealNameLevel(){
		return realNameLevel;
	}
	public void setRealNameLevel(String realNameLevel){
		this.realNameLevel = realNameLevel;
	}

	public String getIsMerchent(){
		return isMerchent;
	}
	public void setIsMerchent(String isMerchent){
		this.isMerchent = isMerchent;
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
