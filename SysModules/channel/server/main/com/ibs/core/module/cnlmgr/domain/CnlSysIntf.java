/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSysIntf extends BaseEntity {
	private String id; // ID
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlIntfCode; // CNL_INTF_CODE
	private String accessCode; // ACCESS_CODE
	private String accessKey; // ACCESS_KEY
	private String verificationType; // VERIFICATION_TYPE
	private String accessType; // ACCESS_TYPE
	private String sendEmail; // SEND_EMAIL
	private String sendSms; // SEND_SMS
	private String retrunType; // RETRUN_TYPE
	private String supportSdk; // SUPPORT_SDK
	private String showLogo; // SHOW_LOGO
	private String comments; // COMMENTS
	private String direction; // DIRECTION
	private String serviceUrl; // SERVICE_URL
	private String serviceUrlFormat; // SERVICE_URL_FORMAT
	private String techSupportName; // TECH_SUPPORT_NAME
	private String techSupportPhonenum; // TECH_SUPPORT_PHONENUM
	private String techSupportEmail; // TECH_SUPPORT_EMAIL
	private String businessSupportName; // BUSINESS_SUPPORT_NAME
	private String businessSupportPhonenum; // BUSINESS_SUPPORT_PHONENUM
	private String businessSupportEmail; // BUSINESS_SUPPORT_EMAIL
	private String ipRangeFrom; // IP_RANGE_FROM
	private String ipRangeTo; // IP_RANGE_TO
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private BigDecimal perTransLimit;//perTransLimit
	private BigDecimal dayLimit; //DAY_LIMIT
	private BigDecimal weekLimit; //WEEK_LIMIT
	private BigDecimal monthLimit; //MONTH_LIMIT
	private BigDecimal yearLimit; //YEAR_LIMIT
	private String currency; //CURRENCY

	private String ipOpt; //IP_OPT
	private Date ipOptTime; //IP_OPT_TIME
	private String lmtOpt; //LMT_OPT
	private Date lmtOptTime; //LMT_OPT_TIME
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
	public String getCnlIntfCode() {
		return cnlIntfCode;
	}
	public void setCnlIntfCode(String cnlIntfCode) {
		this.cnlIntfCode = cnlIntfCode;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getVerificationType() {
		return verificationType;
	}
	public void setVerificationType(String verificationType) {
		this.verificationType = verificationType;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getSendSms() {
		return sendSms;
	}
	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}
	public String getRetrunType() {
		return retrunType;
	}
	public void setRetrunType(String retrunType) {
		this.retrunType = retrunType;
	}
	public String getSupportSdk() {
		return supportSdk;
	}
	public void setSupportSdk(String supportSdk) {
		this.supportSdk = supportSdk;
	}
	public String getShowLogo() {
		return showLogo;
	}
	public void setShowLogo(String showLogo) {
		this.showLogo = showLogo;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String getServiceUrlFormat() {
		return serviceUrlFormat;
	}
	public void setServiceUrlFormat(String serviceUrlFormat) {
		this.serviceUrlFormat = serviceUrlFormat;
	}
	public String getTechSupportName() {
		return techSupportName;
	}
	public void setTechSupportName(String techSupportName) {
		this.techSupportName = techSupportName;
	}
	public String getTechSupportPhonenum() {
		return techSupportPhonenum;
	}
	public void setTechSupportPhonenum(String techSupportPhonenum) {
		this.techSupportPhonenum = techSupportPhonenum;
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
	public String getIpRangeFrom() {
		return ipRangeFrom;
	}
	public void setIpRangeFrom(String ipRangeFrom) {
		this.ipRangeFrom = ipRangeFrom;
	}
	public String getIpRangeTo() {
		return ipRangeTo;
	}
	public void setIpRangeTo(String ipRangeTo) {
		this.ipRangeTo = ipRangeTo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIpOpt() {
		return ipOpt;
	}
	public void setIpOpt(String ipOpt) {
		this.ipOpt = ipOpt;
	}
	public Date getIpOptTime() {
		return ipOptTime;
	}
	public void setIpOptTime(Date ipOptTime) {
		this.ipOptTime = ipOptTime;
	}
	public String getLmtOpt() {
		return lmtOpt;
	}
	public void setLmtOpt(String lmtOpt) {
		this.lmtOpt = lmtOpt;
	}
	public Date getLmtOptTime() {
		return lmtOptTime;
	}
	public void setLmtOptTime(Date lmtOptTime) {
		this.lmtOptTime = lmtOptTime;
	}

	
}
