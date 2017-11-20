/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.dto;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCompound extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // ID
	private String custCode; // CUST_CODE
	private String custType; // CUST_TYPE
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
	private String gender; // GENDER
	private Date birthday; // BIRTHDAY
	private String country; // COUNTRY
	private String provience; // PROVIENCE
	private String city; // CITY
	private String district; // DISTRICT
	private String addr; // ADDR
	private String highestEdu; // HIGHEST_EDU
	private String occupation; // OCCUPATION
	private String jobTitle; // JOB_TITLE
	private String employer; // EMPLOYER
	private String email; // EMAIL
	private String postcode; // POSTCODE
	private String isMarried; // IS_MARRIED
	private String spouseLocalName; // SPOUSE_LOCAL_NAME
	private String spousePhonenum; // SPOUSE_PHONENUM
	private String isValid; // IS_VALID
	private String phoneNum;
	private String workTel;
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getHighestEdu() {
		return highestEdu;
	}
	public void setHighestEdu(String highestEdu) {
		this.highestEdu = highestEdu;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(String isMarried) {
		this.isMarried = isMarried;
	}
	public String getSpouseLocalName() {
		return spouseLocalName;
	}
	public void setSpouseLocalName(String spouseLocalName) {
		this.spouseLocalName = spouseLocalName;
	}
	public String getSpousePhonenum() {
		return spousePhonenum;
	}
	public void setSpousePhonenum(String spousePhonenum) {
		this.spousePhonenum = spousePhonenum;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getWorkTel() {
		return workTel;
	}
	public void setWorkTel(String workTel) {
		this.workTel = workTel;
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
	@Override
	public String toString() {
		return "CorCompound [id=" + id + ", custCode=" + custCode + ", custType=" + custType + ", localName="
				+ localName + ", realNameLeve=" + realNameLeve + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", custLevel=" + custLevel + ", certType=" + certType + ", certNum=" + certNum + ", certExpireDate="
				+ certExpireDate + ", status=" + status + ", regTime=" + regTime + ", dataSource=" + dataSource
				+ ", gender=" + gender + ", birthday=" + birthday + ", country=" + country + ", provience=" + provience
				+ ", city=" + city + ", district=" + district + ", addr=" + addr + ", highestEdu=" + highestEdu
				+ ", occupation=" + occupation + ", jobTitle=" + jobTitle + ", employer=" + employer + ", email="
				+ email + ", postcode=" + postcode + ", isMarried=" + isMarried + ", spouseLocalName=" + spouseLocalName
				+ ", spousePhonenum=" + spousePhonenum + ", isValid=" + isValid + ", phoneNum=" + phoneNum
				+ ", workTel=" + workTel + ", creator=" + creator + ", updator=" + updator + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
	
	
}
