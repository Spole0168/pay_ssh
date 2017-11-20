package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class JqGridObj extends BaseEntity {
	private static final long serialVersionUID = 975911686835279913L;
	
	private String id;
	private String jqgrid;
	private String userid;
	private String field;
	private Date operTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJqgrid() {
		return jqgrid;
	}

	public void setJqgrid(String jqgrid) {
		this.jqgrid = jqgrid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

}
