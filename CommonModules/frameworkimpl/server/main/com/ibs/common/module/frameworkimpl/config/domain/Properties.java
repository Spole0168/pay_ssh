package com.ibs.common.module.frameworkimpl.config.domain;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class Properties extends BaseEntity {

	private static final long serialVersionUID = -6530073105072586275L;

	private String key;
	private String value;
	private String description;
	private Boolean status;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
