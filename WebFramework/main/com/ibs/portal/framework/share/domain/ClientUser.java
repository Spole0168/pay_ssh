package com.ibs.portal.framework.share.domain;

import java.util.Date;
import java.util.List;

import com.ibs.portal.framework.server.domain.BaseEntity;
import com.ibs.portal.framework.server.metadata.SystemMenu;

public class ClientUser extends BaseEntity implements IClientUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8127198202570924989L;

	/*
	 * 登录IP
	 */
	private String loginIp;

	/**
	 * 用户名
	 */
	private String userName;

	private String userCode;

	private Date loginTime;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	private String sessionId;

	public String getEmployeeSN() {
		return employeeSN;
	}

	public void setEmployeeSN(String employeeSN) {
		this.employeeSN = employeeSN;
	}

	/**
	 * 密码
	 */
	private String userPwd;

	private String orgId;

	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	private String employeeName;

	private String employeeSN;

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getUserId() {
		return this.getId();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	private List<String> orgFunctions;

	public List<String> getOrgFunctions() {
		return orgFunctions;
	}

	private List<SystemMenu> guiMenus;

	public List<SystemMenu> getGuiMenus() {
		return guiMenus;
	}

	public void setOrgFunctions(List<String> orgFunctions) {
		this.orgFunctions = orgFunctions;
	}

	public void setGuiMenus(List<SystemMenu> guiMenus) {
		this.guiMenus = guiMenus;
	}

	private String belongToOrgCode;

	public String getBelongToOrgCode() {
		return belongToOrgCode;
	}

	public void setBelongToOrgCode(String belongToOrgCode) {
		this.belongToOrgCode = belongToOrgCode;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
