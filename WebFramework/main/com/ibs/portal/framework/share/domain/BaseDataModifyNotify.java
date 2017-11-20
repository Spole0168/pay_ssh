package com.ibs.portal.framework.share.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class BaseDataModifyNotify extends BaseEntity {
	private String entityTypeCode;
	
	private Date LastModifiedTm;

	public Date getLastModifiedTm() {
		return LastModifiedTm;
	}

	public void setLastModifiedTm(Date lastModifiedTm) {
		LastModifiedTm = lastModifiedTm;
	}

	public String getEntityTypeCode() {
		return entityTypeCode;
	}

	public void setEntityTypeCode(String entityTypeCode) {
		this.entityTypeCode = entityTypeCode;
	}
}
