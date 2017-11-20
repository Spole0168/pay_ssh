package com.ibs.core.module.account.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CnlReqTransDto {
	private String reqInnerNum;//CnlReqTrans
	private String cnlCnlCode;//CnlReqTrans
	private String localName;
	private String reqType; 
	private String reqStatus;
	private String isinGl;
	private Date msgTime;
	private Date recieveTime; // RECIEVE_TIME
	private Date handleTime;
	private Date bankAccepteTime; // BANK_ACCEPTE_TIME
	private Date bankReqTransTime; 
	private String bankTransNum;
	private Date transTime;
	private Date bankReturnTime;
	private String isPrinted; 
	private Date printedTime;
	private String custCode;
	private String custStatus;
	private Date createCustTime;
	private Date updateCustTime;
	private Date logoutTime;
	private Date birthday;
	private String gender;
	private String isMarried;
	private String country;
	private String provience;
	private String city;
	private String district;
	private String addr;
	private String postcode;
	private String highestEdu;
	private String industry;
	private String jobTitle;
	private String employer;
	private String phonenum;
	private String email;
	private String cnlCustCode;
	private String spouseLocalName;
	private String spousePhonenum;
	private String isRealName;
	private String realNameLevel;
	private String workTel;
	private String isValid;
	private String transType;
	private String transDc;
	private String transCurrency;
	private BigDecimal transAmount;
	private String transStatus;
	private Date transDate;
	private String transComments;
	private String bankReturnResult;
	private String bankPmtCnlType;
	private String bankPmtCnlCode;
	private String bankCreditName;
	private String bankCreditCustName;
	private String bankCreditAcntCode;
	private Date bankReqTransDate;
	private String bankServiceFeeAct;
	private String bankHandLePriority;
	private String bankDebitName;
	private String bankDebitAcntCode;
	private String bankDebitCustName;
	private String companyName;
	private String unitProperty;
	private String certType;
	private String certNum;
	private Date certExpireDate;
	private String corporateNum;
	private Date companyRegTime;
	private String corporateName;
	private String corporateCertType;
	private String companyRegAddr;
	private String businessScope;
	private BigDecimal regCapital;
	private String regCapitalCurrency;
	private String financeContact;
	private String financeTel;
	private String finnaceEmail;
	private String corporateCertNum;
	private Date regTime;
	private Date updateTime;
	private String bankCardNum;
	private String companyTel;
	private String companyFax;
	private String website;
	private String stlNum;
	private String reqNum;
	private Date createTime;
	
	
	public String getFinnaceEmail() {
		return finnaceEmail;
	}
	public void setFinnaceEmail(String finnaceEmail) {
		this.finnaceEmail = finnaceEmail;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStlNum() {
		return stlNum;
	}
	public void setStlNum(String stlNum) {
		this.stlNum = stlNum;
	}
	public String getReqNum() {
		return reqNum;
	}
	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getBankCardNum() {
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getCorporateCertNum() {
		return corporateCertNum;
	}
	public void setCorporateCertNum(String corporateCertNum) {
		this.corporateCertNum = corporateCertNum;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUnitProperty() {
		return unitProperty;
	}
	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
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
	public String getCorporateNum() {
		return corporateNum;
	}
	public void setCorporateNum(String corporateNum) {
		this.corporateNum = corporateNum;
	}
	public Date getCompanyRegTime() {
		return companyRegTime;
	}
	public void setCompanyRegTime(Date companyRegTime) {
		this.companyRegTime = companyRegTime;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getCorporateCertType() {
		return corporateCertType;
	}
	public void setCorporateCertType(String corporateCertType) {
		this.corporateCertType = corporateCertType;
	}
	public String getCompanyRegAddr() {
		return companyRegAddr;
	}
	public void setCompanyRegAddr(String companyRegAddr) {
		this.companyRegAddr = companyRegAddr;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	
	public BigDecimal getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}
	public String getRegCapitalCurrency() {
		return regCapitalCurrency;
	}
	public void setRegCapitalCurrency(String regCapitalCurrency) {
		this.regCapitalCurrency = regCapitalCurrency;
	}
	public String getFinanceContact() {
		return financeContact;
	}
	public void setFinanceContact(String financeContact) {
		this.financeContact = financeContact;
	}
	public String getFinanceTel() {
		return financeTel;
	}
	public void setFinanceTel(String financeTel) {
		this.financeTel = financeTel;
	}
	public String getBankDebitName() {
		return bankDebitName;
	}
	public void setBankDebitName(String bankDebitName) {
		this.bankDebitName = bankDebitName;
	}
	public String getBankDebitAcntCode() {
		return bankDebitAcntCode;
	}
	public void setBankDebitAcntCode(String bankDebitAcntCode) {
		this.bankDebitAcntCode = bankDebitAcntCode;
	}
	public String getBankDebitCustName() {
		return bankDebitCustName;
	}
	public void setBankDebitCustName(String bankDebitCustName) {
		this.bankDebitCustName = bankDebitCustName;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransDc() {
		return transDc;
	}
	public void setTransDc(String transDc) {
		this.transDc = transDc;
	}
	public String getTransCurrency() {
		return transCurrency;
	}
	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}
	
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getTransComments() {
		return transComments;
	}
	public void setTransComments(String transComments) {
		this.transComments = transComments;
	}
	public String getBankReturnResult() {
		return bankReturnResult;
	}
	public void setBankReturnResult(String bankReturnResult) {
		this.bankReturnResult = bankReturnResult;
	}
	public String getBankPmtCnlType() {
		return bankPmtCnlType;
	}
	public void setBankPmtCnlType(String bankPmtCnlType) {
		this.bankPmtCnlType = bankPmtCnlType;
	}
	public String getBankPmtCnlCode() {
		return bankPmtCnlCode;
	}
	public void setBankPmtCnlCode(String bankPmtCnlCode) {
		this.bankPmtCnlCode = bankPmtCnlCode;
	}
	public String getBankCreditName() {
		return bankCreditName;
	}
	public void setBankCreditName(String bankCreditName) {
		this.bankCreditName = bankCreditName;
	}
	public String getBankCreditCustName() {
		return bankCreditCustName;
	}
	public void setBankCreditCustName(String bankCreditCustName) {
		this.bankCreditCustName = bankCreditCustName;
	}
	public String getBankCreditAcntCode() {
		return bankCreditAcntCode;
	}
	public void setBankCreditAcntCode(String bankCreditAcntCode) {
		this.bankCreditAcntCode = bankCreditAcntCode;
	}
	public Date getBankReqTransDate() {
		return bankReqTransDate;
	}
	public void setBankReqTransDate(Date bankReqTransDate) {
		this.bankReqTransDate = bankReqTransDate;
	}
	public String getBankServiceFeeAct() {
		return bankServiceFeeAct;
	}
	public void setBankServiceFeeAct(String bankServiceFeeAct) {
		this.bankServiceFeeAct = bankServiceFeeAct;
	}
	public String getBankHandLePriority() {
		return bankHandLePriority;
	}
	public void setBankHandLePriority(String bankHandLePriority) {
		this.bankHandLePriority = bankHandLePriority;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getWorkTel() {
		return workTel;
	}
	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}
	public String getCustStatus() {
		return custStatus;
	}
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	public Date getCreateCustTime() {
		return createCustTime;
	}
	public void setCreateCustTime(Date createCustTime) {
		this.createCustTime = createCustTime;
	}
	public Date getUpdateCustTime() {
		return updateCustTime;
	}
	public void setUpdateCustTime(Date updateCustTime) {
		this.updateCustTime = updateCustTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
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
	public String getIsRealName() {
		return isRealName;
	}
	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}
	public String getRealNameLevel() {
		return realNameLevel;
	}
	public void setRealNameLevel(String realNameLevel) {
		this.realNameLevel = realNameLevel;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getIsinGl() {
		return isinGl;
	}
	public void setIsinGl(String isinGl) {
		this.isinGl = isinGl;
	}
	public String getReqInnerNum() {
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public Date getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}
	public Date getRecieveTime() {
		return recieveTime;
	}
	public void setRecieveTime(Date recieveTime) {
		this.recieveTime = recieveTime;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public Date getBankAccepteTime() {
		return bankAccepteTime;
	}
	public void setBankAccepteTime(Date bankAccepteTime) {
		this.bankAccepteTime = bankAccepteTime;
	}
	public Date getBankReqTransTime() {
		return bankReqTransTime;
	}
	public void setBankReqTransTime(Date bankReqTransTime) {
		this.bankReqTransTime = bankReqTransTime;
	}
	public String getBankTransNum() {
		return bankTransNum;
	}
	public void setBankTransNum(String bankTransNum) {
		this.bankTransNum = bankTransNum;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public Date getBankReturnTime() {
		return bankReturnTime;
	}
	public void setBankReturnTime(Date bankReturnTime) {
		this.bankReturnTime = bankReturnTime;
	}
	public String getIsPrinted() {
		return isPrinted;
	}
	public void setIsPrinted(String isPrinted) {
		this.isPrinted = isPrinted;
	}
	public Date getPrintedTime() {
		return printedTime;
	}
	public void setPrintedTime(Date printedTime) {
		this.printedTime = printedTime;
	}
}
