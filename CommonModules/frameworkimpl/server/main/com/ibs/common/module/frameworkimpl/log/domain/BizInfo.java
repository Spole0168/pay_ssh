package com.ibs.common.module.frameworkimpl.log.domain;


/**
 * BizInfo entity. @author MyEclipse Persistence Tools
 */

public class BizInfo extends com.ibs.portal.framework.server.domain.BaseEntity {

	private static final long serialVersionUID = 6319733116622259923L;

	private String appId;
	private String bizName;
	private String bizDesc;
	private String bizClass;
	private String bizMethod;
	private EntityInfo entityInfo;
	private Integer sort;
	private Short status;

	// Constructors

	/** default constructor */
	public BizInfo() {
	}

	// Property accessors

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBizName() {
		return this.bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizDesc() {
		return this.bizDesc;
	}

	public void setBizDesc(String bizDesc) {
		this.bizDesc = bizDesc;
	}

	public String getBizClass() {
		return this.bizClass;
	}

	public void setBizClass(String bizClass) {
		this.bizClass = bizClass;
	}

	public String getBizMethod() {
		return this.bizMethod;
	}

	public void setBizMethod(String bizMethod) {
		this.bizMethod = bizMethod;
	}

	public EntityInfo getEntityInfo() {
		return entityInfo;
	}

	public void setEntityInfo(EntityInfo entityInfo) {
		this.entityInfo = entityInfo;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}