/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCust extends BaseEntity {
	
	private String id; // ID
	private String custCode; // CUST_CODE
	private String custType; // CUST_TYPE
	private String country; // COUNTRY
	private String localName; // LOCAL_NAME
	private String realNameLeve; // REAL_NAME_LEVE
	private String firstName; // FIRST_NAME
	private String lastName; // LAST_NAME
	private String custLevel; // CUST_LEVEL
	private String certType; // CERT_TYPE
	private String certNum; // CERT_NUM
	private Date certExpireDate; // CERT_EXPIRE_DATE
	private String status; // STATUS
	private Date regTime; // REG_TIME
	private String dataSource; // DATA_SOURCE
	private String isValid; // IS_VALID
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String certCopy; // CERT_COPY
	
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

	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	public String getLocalName(){
		return localName;
	}
	public void setLocalName(String localName){
		this.localName = localName;
	}

	public String getRealNameLeve(){
		return realNameLeve;
	}
	public void setRealNameLeve(String realNameLeve){
		this.realNameLeve = realNameLeve;
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

	public String getCustLevel(){
		return custLevel;
	}
	public void setCustLevel(String custLevel){
		this.custLevel = custLevel;
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

	public Date getCertExpireDate(){
		return certExpireDate;
	}
	public void setCertExpireDate(Date certExpireDate){
		this.certExpireDate = certExpireDate;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public Date getRegTime(){
		return regTime;
	}
	public void setRegTime(Date regTime){
		this.regTime = regTime;
	}

	public String getDataSource(){
		return dataSource;
	}
	public void setDataSource(String dataSource){
		this.dataSource = dataSource;
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

	public String getCertCopy(){
		return certCopy;
	}
	public void setCertCopy(String certCopy){
		this.certCopy = certCopy;
	}

	
}
