package com.ibs.portal.framework.server.metadata;

import org.hibernate.criterion.Criterion;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CriteriaAlias extends BaseEntity {
	//对象名
	private String objectName;
	
	//别名
	private String aliasName;
	
	
	//操作
	private Criterion criterion;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	
	public Criterion getCriterion() {
		return criterion;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

}
