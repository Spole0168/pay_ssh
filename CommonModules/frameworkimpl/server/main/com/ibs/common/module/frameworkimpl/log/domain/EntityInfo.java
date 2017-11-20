package com.ibs.common.module.frameworkimpl.log.domain;

/**
 * EntityInfo entity. @author MyEclipse Persistence Tools
 */

public class EntityInfo extends com.ibs.portal.framework.server.domain.BaseEntity {

	private static final long serialVersionUID = 5794811927748478645L;
	
	private String entityClass;
	private String hisEntityClass;
	private String appId;
	private String entityName;
	private String entityDesc;
	private String codeProperty;
	private String nameProperty;
	private String viewUrl;

	// Constructors

	/** default constructor */
	public EntityInfo() {
	}

	// Property accessors

	public String getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getCodeProperty() {
		return this.codeProperty;
	}

	public void setCodeProperty(String codeProperty) {
		this.codeProperty = codeProperty;
	}

	public String getNameProperty() {
		return this.nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}

	public String getViewUrl() {
		return this.viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public String getHisEntityClass() {
		return hisEntityClass;
	}

	public void setHisEntityClass(String hisEntityClass) {
		this.hisEntityClass = hisEntityClass;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}