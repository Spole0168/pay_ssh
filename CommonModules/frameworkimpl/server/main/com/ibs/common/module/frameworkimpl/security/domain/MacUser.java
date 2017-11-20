package com.ibs.common.module.frameworkimpl.security.domain;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class MacUser extends BaseEntity{
	
	private static final long serialVersionUID = 3234036614166954063L;

	private String macId;
	
	private String userCode;

	private String userName;
	
	private String orgCode;
	
	private String orgName;
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

}
