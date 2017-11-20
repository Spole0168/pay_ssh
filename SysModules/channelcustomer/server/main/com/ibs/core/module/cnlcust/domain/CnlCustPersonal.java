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
 * @comments	: code generated based on database T_CNL_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustPersonal extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // ID
	private String cnlCustCode; // CNL_CUST_CODE
	private String custCode; // CUST_CODE
	private String isSmsVerified; // IS_SMS_VERIFIED
	private String name; // NAME
	private Date regTime; // REG_TIME
	private String email; // EMAIL
	private String country; // COUNTRY
	private String provience; // PROVIENCE
	private String city; // CITY
	private String district; // DISTRICT
	private String addr; // ADDR
	private String employer; // EMPLOYER
	private String jobTitle; // JOB_TITLE
	private String workTel; // WORK_TEL
	private String gender; // GENDER
	private Date birthday; // BIRTHDAY
	private String industry; // INDUSTRY
	private String highestEdu; // HIGHEST_EDU
	private String phonenum; // PHONENUM
	private String postcode; // POSTCODE
	private String isMarried; // IS_MARRIED
	private String spouseCountry; // SPOUSE_COUNTRY
	private String spouseLocalName; // SPOUSE_LOCAL_NAME
	private String spouseLastName; // SPOUSE_LAST_NAME
	private String spouseFirstName; // SPOUSE_FIRST_NAME
	private String spouseCertType; // SPOUSE_CERT_TYPE
	private String spouseCertNum; // SPOUSE_CERT_NUM
	private Date spouseCertExpireDate; // SPOUSE_CERT_EXPIRE_DATE
	private String isValid; // IS_VALID
	private String spousePhonenum; // SPOUSE_PHONENUM
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

	public String getIsSmsVerified(){
		return isSmsVerified;
	}
	public void setIsSmsVerified(String isSmsVerified){
		this.isSmsVerified = isSmsVerified;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public Date getRegTime(){
		return regTime;
	}
	public void setRegTime(Date regTime){
		this.regTime = regTime;
	}

	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
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

	public String getEmployer(){
		return employer;
	}
	public void setEmployer(String employer){
		this.employer = employer;
	}

	public String getJobTitle(){
		return jobTitle;
	}
	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}

	public String getWorkTel(){
		return workTel;
	}
	public void setWorkTel(String workTel){
		this.workTel = workTel;
	}

	public String getGender(){
		return gender;
	}
	public void setGender(String gender){
		this.gender = gender;
	}

	public Date getBirthday(){
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getIndustry(){
		return industry;
	}
	public void setIndustry(String industry){
		this.industry = industry;
	}

	public String getHighestEdu(){
		return highestEdu;
	}
	public void setHighestEdu(String highestEdu){
		this.highestEdu = highestEdu;
	}

	public String getPhonenum(){
		return phonenum;
	}
	public void setPhonenum(String phonenum){
		this.phonenum = phonenum;
	}

	public String getPostcode(){
		return postcode;
	}
	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getIsMarried(){
		return isMarried;
	}
	public void setIsMarried(String isMarried){
		this.isMarried = isMarried;
	}

	public String getSpouseCountry(){
		return spouseCountry;
	}
	public void setSpouseCountry(String spouseCountry){
		this.spouseCountry = spouseCountry;
	}

	public String getSpouseLocalName(){
		return spouseLocalName;
	}
	public void setSpouseLocalName(String spouseLocalName){
		this.spouseLocalName = spouseLocalName;
	}

	public String getSpouseLastName(){
		return spouseLastName;
	}
	public void setSpouseLastName(String spouseLastName){
		this.spouseLastName = spouseLastName;
	}

	public String getSpouseFirstName(){
		return spouseFirstName;
	}
	public void setSpouseFirstName(String spouseFirstName){
		this.spouseFirstName = spouseFirstName;
	}

	public String getSpouseCertType(){
		return spouseCertType;
	}
	public void setSpouseCertType(String spouseCertType){
		this.spouseCertType = spouseCertType;
	}

	public String getSpouseCertNum(){
		return spouseCertNum;
	}
	public void setSpouseCertNum(String spouseCertNum){
		this.spouseCertNum = spouseCertNum;
	}

	public Date getSpouseCertExpireDate(){
		return spouseCertExpireDate;
	}
	public void setSpouseCertExpireDate(Date spouseCertExpireDate){
		this.spouseCertExpireDate = spouseCertExpireDate;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
	}

	public String getSpousePhonenum(){
		return spousePhonenum;
	}
	public void setSpousePhonenum(String spousePhonenum){
		this.spousePhonenum = spousePhonenum;
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
