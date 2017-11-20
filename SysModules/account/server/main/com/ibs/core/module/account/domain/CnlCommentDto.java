package com.ibs.core.module.account.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.portal.framework.server.domain.BaseEntity;

@SuppressWarnings("serial")
public class CnlCommentDto extends BaseEntity{
	
 
	
	private String id;
	private String csiid;
	private String cccid;
	private String cnlCnlCode;//渠道编码
	private String cnlCustCode;//渠道客户编码
	private String cnlSysName;//渠道名称
	private Date startTime;//创建开始时间
	private Date endTime;//创建结束时间
	private Date createTime;//创建时间
	private String cnlDesc;//渠道系统说明
	private String addr;//地址
	private String country;//国家
	private Date contractEffectDate;//合同起始时间
	private Date contractExpireDate;//合同结束时间
	private String effectDate;
	private String expireDate;
	
	private String accessKey;//接入key
	private String accessType;//接入类型
	
	
 
	
	private BigDecimal perTransLimit;
	private BigDecimal dayLimit;
	private BigDecimal weekLimit;
	private BigDecimal monthLimit;
	private BigDecimal yearLimit;
	
	
	private String sendSms;//是否发送短信
	private String sendEmail;//是否发送邮件
	private String techSupportName;//技术负责人
	private String techSupportPhonenum;//技术负责人电话
	private String techSupportEmail;//技术负责人邮箱
	private String businessSupportName;//业务负责人
	private String businessSupportPhonenum;//业务负责人电话
	private String businessSupportEmail;//业务负责人邮箱
	private String creator;//创建人
	private String comments;//其他说明
	private String custCode;//客户唯一编码
	private String countryValue;//国家显示
	private String accessTypeValue;//渠道接入类型显示
	private String smsValue;//短信显示
	private String emailVale;//邮箱显示
	
	
	
	private CnlCnlCfg cnlCnlCfg;
	private CnlSysIntf cnlSysIntf;
	private CnlCustCompany cnlCustCompany;
	
	
	
	
	
	public String getCnlCustCode() {
		return cnlCustCode;
	}


	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}


	public String getEffectDate() {
		return effectDate;
	}


	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}


	public String getExpireDate() {
		return expireDate;
	}


	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}


	public CnlCommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getCountryValue() {
		return countryValue;
	}


	public void setCountryValue(String countryValue) {
		this.countryValue = countryValue;
	}


	public String getAccessTypeValue() {
		return accessTypeValue;
	}


	public void setAccessTypeValue(String accessTypeValue) {
		this.accessTypeValue = accessTypeValue;
	}


	public String getSmsValue() {
		return smsValue;
	}


	public void setSmsValue(String smsValue) {
		this.smsValue = smsValue;
	}


	public String getEmailVale() {
		return emailVale;
	}


	public void setEmailVale(String emailVale) {
		this.emailVale = emailVale;
	}


 

	public BigDecimal getPerTransLimit() {
		return perTransLimit;
	}


	public void setPerTransLimit(BigDecimal perTransLimit) {
		this.perTransLimit = perTransLimit;
	}


	public BigDecimal getDayLimit() {
		return dayLimit;
	}


	public void setDayLimit(BigDecimal dayLimit) {
		this.dayLimit = dayLimit;
	}


	public BigDecimal getWeekLimit() {
		return weekLimit;
	}


	public void setWeekLimit(BigDecimal weekLimit) {
		this.weekLimit = weekLimit;
	}


	public BigDecimal getMonthLimit() {
		return monthLimit;
	}


	public void setMonthLimit(BigDecimal monthLimit) {
		this.monthLimit = monthLimit;
	}


	public BigDecimal getYearLimit() {
		return yearLimit;
	}


	public void setYearLimit(BigDecimal yearLimit) {
		this.yearLimit = yearLimit;
	}


	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getCnlSysName() {
		return cnlSysName;
	}
	public void setCnlSysName(String cnlSysName) {
		this.cnlSysName = cnlSysName;
	}
	 
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCnlDesc() {
		return cnlDesc;
	}
	public void setCnlDesc(String cnlDesc) {
		this.cnlDesc = cnlDesc;
	}
 
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getContractEffectDate() {
		return contractEffectDate;
	}
	public void setContractEffectDate(Date contractEffectDate) {
		this.contractEffectDate = contractEffectDate;
	}
	public Date getContractExpireDate() {
		return contractExpireDate;
	}
	public void setContractExpireDate(Date contractExpireDate) {
		this.contractExpireDate = contractExpireDate;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
 
	public String getSendSms() {
		return sendSms;
	}
	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}
	 
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getTechSupportName() {
		return techSupportName;
	}
	public void setTechSupportName(String techSupportName) {
		this.techSupportName = techSupportName;
	}
	public String getTechSupportEmail() {
		return techSupportEmail;
	}
	public void setTechSupportEmail(String techSupportEmail) {
		this.techSupportEmail = techSupportEmail;
	}
	public String getBusinessSupportName() {
		return businessSupportName;
	}
	public void setBusinessSupportName(String businessSupportName) {
		this.businessSupportName = businessSupportName;
	}
 
	public String getBusinessSupportPhonenum() {
		return businessSupportPhonenum;
	}
	public void setBusinessSupportPhonenum(String businessSupportPhonenum) {
		this.businessSupportPhonenum = businessSupportPhonenum;
	}
	public String getBusinessSupportEmail() {
		return businessSupportEmail;
	}
	public void setBusinessSupportEmail(String businessSupportEmail) {
		this.businessSupportEmail = businessSupportEmail;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTechSupportPhonenum() {
		return techSupportPhonenum;
	}
	public void setTechSupportPhonenum(String techSupportPhonenum) {
		this.techSupportPhonenum = techSupportPhonenum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CnlCnlCfg getCnlCnlCfg() {
		return cnlCnlCfg;
	}
	public void setCnlCnlCfg(CnlCnlCfg cnlCnlCfg) {
		this.cnlCnlCfg = cnlCnlCfg;
	}
	public CnlSysIntf getCnlSysIntf() {
		return cnlSysIntf;
	}
	public void setCnlSysIntf(CnlSysIntf cnlSysIntf) {
		this.cnlSysIntf = cnlSysIntf;
	}
	public CnlCustCompany getCnlCustCompany() {
		return cnlCustCompany;
	}
	public void setCnlCustCompany(CnlCustCompany cnlCustCompany) {
		this.cnlCustCompany = cnlCustCompany;
	}
	 
	public String getCsiid() {
		return csiid;
	}
	public void setCsiid(String csiid) {
		this.csiid = csiid;
	}
	public String getCccid() {
		return cccid;
	}
	public void setCccid(String cccid) {
		this.cccid = cccid;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
 
	
	
	
	
}
