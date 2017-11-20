package com.ibs.core.module.account.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CustPersonalInfo extends BaseEntity{

	private String cnlCustCode;
	private String localName;
	private Date birthday;
	private String gender;
	private String isMarried;
	private String country;
	private String provience;
	private String city;
	private String addr;
	private String postcode;
	private String highestEdu;
	private String industry;
	private String jobTitle;
	private String employer;
	private String phonenum;
	private String workTel;
	private String email;
	private String cnlCnlCode;
	private String spouseLocalName;
	private String spouseCertNum;
	private String realNamelevel;
	private String custLevel;
	private String custStatus;
	private String bankCardNum;
	private String cnlSysName;
	private String certType;
	private String certNum;
	private Date certExpireDate;
	private Date createTime;
	private String creator;
	private String name;
	private String firstName;
	private String lastName;
	
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCnlSysName() {
		return cnlSysName;
	}
	public void setCnlSysName(String cnlSysName) {
		this.cnlSysName = cnlSysName;
	}
	public String getBankCardNum() {
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(String isMarried) {
		this.isMarried = isMarried;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getHighestEdu() {
		return highestEdu;
	}
	public void setHighestEdu(String highestEdu) {
		this.highestEdu = highestEdu;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getWorkTel() {
		return workTel;
	}
	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getSpouseLocalName() {
		return spouseLocalName;
	}
	public void setSpouseLocalName(String spouseLocalName) {
		this.spouseLocalName = spouseLocalName;
	}
	public String getSpouseCertNum() {
		return spouseCertNum;
	}
	public void setSpouseCertNum(String spouseCertNum) {
		this.spouseCertNum = spouseCertNum;
	}
	public String getRealNamelevel() {
		return realNamelevel;
	}
	public void setRealNamelevel(String realNamelevel) {
		this.realNamelevel = realNamelevel;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustStatus() {
		return custStatus;
	}
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	
	public CustPersonalInfo(String cnlCustCode, String localName, Date birthday, String gender, String isMarried,
			String country, String provience, String city, String addr, String postcode, String highestEdu,
			String industry, String jobTitle, String employer, String phonenum, String workTel, String email,
			String cnlCnlCode, String spouseLocalName, String spouseCertNum, String realNamelevel, String custLevel,
			String custStatus, String bankCardNum, String cnlSysName, String certType, String certNum,
			Date certExpireDate, Date createTime, String creator, String name, String firstName, String lastName) {
		super();
		this.cnlCustCode = cnlCustCode;
		this.localName = localName;
		this.birthday = birthday;
		this.gender = gender;
		this.isMarried = isMarried;
		this.country = country;
		this.provience = provience;
		this.city = city;
		this.addr = addr;
		this.postcode = postcode;
		this.highestEdu = highestEdu;
		this.industry = industry;
		this.jobTitle = jobTitle;
		this.employer = employer;
		this.phonenum = phonenum;
		this.workTel = workTel;
		this.email = email;
		this.cnlCnlCode = cnlCnlCode;
		this.spouseLocalName = spouseLocalName;
		this.spouseCertNum = spouseCertNum;
		this.realNamelevel = realNamelevel;
		this.custLevel = custLevel;
		this.custStatus = custStatus;
		this.bankCardNum = bankCardNum;
		this.cnlSysName = cnlSysName;
		this.certType = certType;
		this.certNum = certNum;
		this.certExpireDate = certExpireDate;
		this.createTime = createTime;
		this.creator = creator;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public CustPersonalInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustPersonalInfo(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
}
