package com.ibs.common.module.frameworkimpl.log.domain;

import java.util.Date;

/**
 * BizLog entity. @author MyEclipse Persistence Tools
 */

public class BizLog extends com.ibs.portal.framework.server.domain.BaseEntity {

	private static final long serialVersionUID = -8064257378899019042L;

	private String bizType;
	private String actionId;
	private String appId;
	private String userCode;
	private String userName;
	private String orgCode;
	private String orgName;
	private Date bizTime;
	private String entityId;
	private String entityCode;
	private String entityName;
	private Boolean isException;

	// Constructors

	/** default constructor */
	public BizLog() {
	}

	// Property accessors

	public String getActionId() {
		return this.actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getBizTime() {
		return this.bizTime;
	}

	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
	}

	public String getEntityId() {
		return this.entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Boolean getIsException() {
		return isException;
	}

	public void setIsException(Boolean isException) {
		this.isException = isException;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}