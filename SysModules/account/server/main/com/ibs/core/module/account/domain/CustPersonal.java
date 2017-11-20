package com.ibs.core.module.account.domain;

import java.util.Date;

import com.ibs.portal.framework.util.DateUtils;

public class CustPersonal {

	private String id;
	private String cnlCnlCode;
	private String cnlCustCode;
	private String cnlCustType;
	private String custStatus;
	private String localName;
	private String firstName;
	private String lastName;
	private String country;
	private String certType;
	private String certNum;
	private Date certExpireDate;
	private Date createTime;
	private String creator;
	private String realNamelevel;
	private String custLevel;
	private String phonenum;
	private String cnlSysName;
	private String certExpireDateString;
	
	public String getCertExpireDateString() {
		if(certExpireDate != null)
			return DateUtils.convert(certExpireDate, DateUtils.DATE_FORMAT);
		else
			return certExpireDateString;
	}
	public void setCertExpireDateString(String certExpireDateString) {
		this.certExpireDateString = certExpireDateString;
	}
	public String getCnlSysName() {
		return cnlSysName;
	}
	public void setCnlSysName(String cnlSysName) {
		this.cnlSysName = cnlSysName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getCnlCustType() {
		return cnlCustType;
	}
	public void setCnlCustType(String cnlCustType) {
		this.cnlCustType = cnlCustType;
	}
	public String getCustStatus() {
		return custStatus;
	}
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	
}
