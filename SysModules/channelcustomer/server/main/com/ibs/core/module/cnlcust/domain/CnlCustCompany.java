/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustCompany extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // ID
	private String cnlCustCode; // CNL_CUST_CODE
	private String custCode; // CUST_CODE
	private String isSmsVerified; // IS_SMS_VERIFIED
	private String companyType; // COMPANY_TYPE
	private String companyName; // COMPANY_NAME
	private String companyCode; // COMPANY_CODE
	private Date contractEffectDate; // CONTRACT_EFFECT_DATE
	private Date contractExpireDate; // CONTRACT_EXPIRE_DATE
	private Date regTime; // REG_TIME
	private String website; // WEBSITE
	private String email; // EMAIL
	private String companyTel; // COMPANY_TEL
	private String companyRegAddr; // COMPANY_REG_ADDR
	private String financeContact; // FINANCE_CONTACT
	private String financeTel; // FINANCE_TEL
	private String finnaceEmail; // FINNACE_EMAIL
	private BigDecimal regCapital; // REG_CAPITAL
	private String regCapitalCurrency; // REG_CAPITAL_CURRENCY
	private String companyFax; // COMPANY_FAX
	private String corporateName; // CORPORATE_NAME
	private String corporateCertType; // CORPORATE_CERT_TYPE
	private String corporateCertNum; // CORPORATE_CERT_NUM
	private String corporateCertCopy; // CORPORATE_CERT_COPY
	private String corporateCountry; // CORPORATE_COUNTRY
	private Date corporateCertExpireDate; // CORPORATE_CERT_EXPIRE_DATE
	private String corporateTel; // CORPORATE_TEL
	private String country; // COUNTRY
	private String provience; // PROVIENCE
	private String city; // CITY
	private String district; // DISTRICT
	private String addr; // ADDR
	private String postcode; // POSTCODE
	private String isValid; // IS_VALID
	private String industry; // INDUSTRY
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String businessScope; //BUSINESS_SCOPE
	private String unitProperty; //UNIT_PROPERTY
	
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

	public String getIsSmsVerified(){
		return isSmsVerified;
	}
	public void setIsSmsVerified(String isSmsVerified){
		this.isSmsVerified = isSmsVerified;
	}

	public String getCompanyType(){
		return companyType;
	}
	public void setCompanyType(String companyType){
		this.companyType = companyType;
	}

	public String getCompanyName(){
		return companyName;
	}
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyCode(){
		return companyCode;
	}
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}

	public Date getContractEffectDate(){
		return contractEffectDate;
	}
	public void setContractEffectDate(Date contractEffectDate){
		this.contractEffectDate = contractEffectDate;
	}

	public Date getContractExpireDate(){
		return contractExpireDate;
	}
	public void setContractExpireDate(Date contractExpireDate){
		this.contractExpireDate = contractExpireDate;
	}

	public Date getRegTime(){
		return regTime;
	}
	public void setRegTime(Date regTime){
		this.regTime = regTime;
	}

	public String getWebsite(){
		return website;
	}
	public void setWebsite(String website){
		this.website = website;
	}

	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	public String getCompanyTel(){
		return companyTel;
	}
	public void setCompanyTel(String companyTel){
		this.companyTel = companyTel;
	}

	public String getCompanyRegAddr(){
		return companyRegAddr;
	}
	public void setCompanyRegAddr(String companyRegAddr){
		this.companyRegAddr = companyRegAddr;
	}

	public String getFinanceContact(){
		return financeContact;
	}
	public void setFinanceContact(String financeContact){
		this.financeContact = financeContact;
	}

	public String getFinanceTel(){
		return financeTel;
	}
	public void setFinanceTel(String financeTel){
		this.financeTel = financeTel;
	}

	public String getFinnaceEmail(){
		return finnaceEmail;
	}
	public void setFinnaceEmail(String finnaceEmail){
		this.finnaceEmail = finnaceEmail;
	}

	public BigDecimal getRegCapital(){
		return regCapital;
	}
	public void setRegCapital(BigDecimal regCapital){
		this.regCapital = regCapital;
	}

	public String getRegCapitalCurrency(){
		return regCapitalCurrency;
	}
	public void setRegCapitalCurrency(String regCapitalCurrency){
		this.regCapitalCurrency = regCapitalCurrency;
	}

	public String getCompanyFax(){
		return companyFax;
	}
	public void setCompanyFax(String companyFax){
		this.companyFax = companyFax;
	}

	public String getCorporateName(){
		return corporateName;
	}
	public void setCorporateName(String corporateName){
		this.corporateName = corporateName;
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

	public String getCorporateCountry(){
		return corporateCountry;
	}
	public void setCorporateCountry(String corporateCountry){
		this.corporateCountry = corporateCountry;
	}

	public Date getCorporateCertExpireDate(){
		return corporateCertExpireDate;
	}
	public void setCorporateCertExpireDate(Date corporateCertExpireDate){
		this.corporateCertExpireDate = corporateCertExpireDate;
	}

	public String getCorporateTel(){
		return corporateTel;
	}
	public void setCorporateTel(String corporateTel){
		this.corporateTel = corporateTel;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	public String getProvience(){
		return provience;
	}
	public void setProvience(String provience){
		this.provience = provience;
	}

	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getDistrict(){
		return district;
	}
	public void setDistrict(String district){
		this.district = district;
	}

	public String getAddr(){
		return addr;
	}
	public void setAddr(String addr){
		this.addr = addr;
	}

	public String getPostcode(){
		return postcode;
	}
	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
	}

	public String getIndustry(){
		return industry;
	}
	public void setIndustry(String industry){
		this.industry = industry;
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
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getUnitProperty() {
		return unitProperty;
	}
	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}

	
}
