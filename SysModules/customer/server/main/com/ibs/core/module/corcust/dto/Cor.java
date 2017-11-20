package com.ibs.core.module.corcust.dto;

import java.util.Date;

public class Cor {
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
	
	
	private String tcid; // T_C_ID
	private String companyCode; // COMPANY_CODE
	private String unitProperty; // UNIT_PROPERTY
	private String corporateName; // CORPORATE_NAME
	private String corporateCountryCode; // CORPORATE_COUNTRY_CODE
	private String corporateCertType; // CORPORATE_CERT_TYPE
	private String corporateCertNum; // CORPORATE_CERT_NUM
	private String corporateCertCopy; // CORPORATE_CERT_COPY
	private Date corporateCertExpireDate; // CORPORATE_CERT_EXPIRE_DATE
	private String corporatePhonenum; // CORPORATE_PHONENUM
	private String businessScope; // BUSINESS_SCOPE
	private String industry; // INDUSTRY
	private String companyRegAddr; // COMPANY_REG_ADDR
	private String companyFax; // COMPANY_FAX
	private String companyWebsite; // COMPANY_WEBSITE
	private String email; // EMAIL
		
	
	private String phoneType; // PHONE_TYPE
	private String phoneNum; // PHONE_NUM
	private String isDefault; // IS_DEFAULT
	
	
	private String addrType; // ADDR_TYPE
	private String addr; // ADDR
	private String provience; // PROVIENCE
	private String city; // CITY
	private String district; // DISTRICT
	private String postcode; // POSTCODE
	
	
	
	
	@Override
	public String toString() {
		return "Cor [id=" + id + ", custCode=" + custCode + ", custType=" + custType + ", country=" + country
				+ ", localName=" + localName + ", realNameLeve=" + realNameLeve + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", custLevel=" + custLevel + ", certType=" + certType + ", certNum="
				+ certNum + ", certExpireDate=" + certExpireDate + ", status=" + status + ", regTime=" + regTime
				+ ", dataSource=" + dataSource + ", isValid=" + isValid + ", creator=" + creator + ", updator="
				+ updator + ", createTime=" + createTime + ", updateTime=" + updateTime + ", certCopy=" + certCopy
				+ ", tcid=" + tcid + ", companyCode=" + companyCode + ", unitProperty=" + unitProperty
				+ ", corporateName=" + corporateName + ", corporateCountryCode=" + corporateCountryCode
				+ ", corporateCertType=" + corporateCertType + ", corporateCertNum=" + corporateCertNum
				+ ", corporateCertCopy=" + corporateCertCopy + ", corporateCertExpireDate=" + corporateCertExpireDate
				+ ", corporatePhonenum=" + corporatePhonenum + ", businessScope=" + businessScope + ", industry="
				+ industry + ", companyRegAddr=" + companyRegAddr + ", companyFax=" + companyFax + ", companyWebsite="
				+ companyWebsite + ", email=" + email + ", phoneType=" + phoneType + ", phoneNum=" + phoneNum
				+ ", isDefault=" + isDefault + ", addrType=" + addrType + ", addr=" + addr + ", provience=" + provience
				+ ", city=" + city + ", district=" + district + ", postcode=" + postcode + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getRealNameLeve() {
		return realNameLeve;
	}
	public void setRealNameLeve(String realNameLeve) {
		this.realNameLeve = realNameLeve;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public Date getCertExpireDate() {
		return certExpireDate;
	}
	public void setCertExpireDate(Date certExpireDate) {
		this.certExpireDate = certExpireDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCertCopy() {
		return certCopy;
	}
	public void setCertCopy(String certCopy) {
		this.certCopy = certCopy;
	}
	public String getTcid() {
		return tcid;
	}
	public void setTcid(String tcid) {
		this.tcid = tcid;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getUnitProperty() {
		return unitProperty;
	}
	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getCorporateCountryCode() {
		return corporateCountryCode;
	}
	public void setCorporateCountryCode(String corporateCountryCode) {
		this.corporateCountryCode = corporateCountryCode;
	}
	public String getCorporateCertType() {
		return corporateCertType;
	}
	public void setCorporateCertType(String corporateCertType) {
		this.corporateCertType = corporateCertType;
	}
	public String getCorporateCertNum() {
		return corporateCertNum;
	}
	public void setCorporateCertNum(String corporateCertNum) {
		this.corporateCertNum = corporateCertNum;
	}
	public String getCorporateCertCopy() {
		return corporateCertCopy;
	}
	public void setCorporateCertCopy(String corporateCertCopy) {
		this.corporateCertCopy = corporateCertCopy;
	}
	public Date getCorporateCertExpireDate() {
		return corporateCertExpireDate;
	}
	public void setCorporateCertExpireDate(Date corporateCertExpireDate) {
		this.corporateCertExpireDate = corporateCertExpireDate;
	}
	public String getCorporatePhonenum() {
		return corporatePhonenum;
	}
	public void setCorporatePhonenum(String corporatePhonenum) {
		this.corporatePhonenum = corporatePhonenum;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCompanyRegAddr() {
		return companyRegAddr;
	}
	public void setCompanyRegAddr(String companyRegAddr) {
		this.companyRegAddr = companyRegAddr;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getAddrType() {
		return addrType;
	}
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getProvience() {
		return provience;
	}
	public void setProvience(String provience) {
		this.provience = provience;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
	
	
	
}
