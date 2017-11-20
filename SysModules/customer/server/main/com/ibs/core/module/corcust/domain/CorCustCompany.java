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
 * @comments	: code generated based on database T_COR_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustCompany extends BaseEntity {
	
	private String id; // ID
	private String tcid; // T_C_ID
	private String custCode; // CUST_CODE
	private String companyCode; // COMPANY_CODE
	private String unitProperty; // UNIT_PROPERTY
	private String corporateName; // CORPORATE_NAME
	private String corporateCountryCode; // CORPORATE_COUNTRY_CODE
	private String corporateCertType; // CORPORATE_CERT_TYPE
	private String corporateCertNum; // CORPORATE_CERT_NUM
	private String corporateCertCopy; // CORPORATE_CERT_COPY
	private Date corporateCertExpireDate; // CORPORATE_CERT_EXPIRE_DATE
	private String corporatePhonenum; // CORPORATE_PHONENUM
	private String country; // COUNTRY
	private Date regTime; // REG_TIME
	private String businessScope; // BUSINESS_SCOPE
	private String industry; // INDUSTRY
	private String companyRegAddr; // COMPANY_REG_ADDR
	private String companyFax; // COMPANY_FAX
	private String companyWebsite; // COMPANY_WEBSITE
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String email; // EMAIL
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getTcid(){
		return tcid;
	}
	public void setTcid(String tcid){
		this.tcid = tcid;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getCompanyCode(){
		return companyCode;
	}
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}

	public String getUnitProperty(){
		return unitProperty;
	}
	public void setUnitProperty(String unitProperty){
		this.unitProperty = unitProperty;
	}

	public String getCorporateName(){
		return corporateName;
	}
	public void setCorporateName(String corporateName){
		this.corporateName = corporateName;
	}

	public String getCorporateCountryCode(){
		return corporateCountryCode;
	}
	public void setCorporateCountryCode(String corporateCountryCode){
		this.corporateCountryCode = corporateCountryCode;
	}

	public String getCorporateCertType(){
		return corporateCertType;
	}
	public void setCorporateCertType(String corporateCertType){
		this.corporateCertType = corporateCertType;
	}

	public String getCorporateCertNum(){
		return corporateCertNum;
	}
	public void setCorporateCertNum(String corporateCertNum){
		this.corporateCertNum = corporateCertNum;
	}

	public String getCorporateCertCopy(){
		return corporateCertCopy;
	}
	public void setCorporateCertCopy(String corporateCertCopy){
		this.corporateCertCopy = corporateCertCopy;
	}

	public Date getCorporateCertExpireDate(){
		return corporateCertExpireDate;
	}
	public void setCorporateCertExpireDate(Date corporateCertExpireDate){
		this.corporateCertExpireDate = corporateCertExpireDate;
	}

	public String getCorporatePhonenum(){
		return corporatePhonenum;
	}
	public void setCorporatePhonenum(String corporatePhonenum){
		this.corporatePhonenum = corporatePhonenum;
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

	public String getBusinessScope(){
		return businessScope;
	}
	public void setBusinessScope(String businessScope){
		this.businessScope = businessScope;
	}

	public String getIndustry(){
		return industry;
	}
	public void setIndustry(String industry){
		this.industry = industry;
	}

	public String getCompanyRegAddr(){
		return companyRegAddr;
	}
	public void setCompanyRegAddr(String companyRegAddr){
		this.companyRegAddr = companyRegAddr;
	}

	public String getCompanyFax(){
		return companyFax;
	}
	public void setCompanyFax(String companyFax){
		this.companyFax = companyFax;
	}

	public String getCompanyWebsite(){
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite){
		this.companyWebsite = companyWebsite;
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

	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	
}
