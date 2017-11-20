package com.ibs.common.module.frameworkimpl.log.domain;

import java.util.Date;

import com.ibs.portal.framework.util.StringUtils;

/**
 * ActionLog entity. @author MyEclipse Persistence Tools
 */

public class ActionLog extends com.ibs.portal.framework.server.domain.BaseEntity {

	private static final long serialVersionUID = -6530073105072586275L;
	
	private String userCode;
	private String userName;
	private String orgCode;
	private String orgName;
	private String serverName;
	private String serverAddr;
	private String clientName;
	private String clientAddr;
	private String clientMac;
	private String clientUserAgent;
	private String sessionId;
	private String locale;
	private Date requestTime;
	private Date responseTime;
	private String appId;
	private String menuId;
	private String url;
	private String method;
	private String params;
	private String actionClass;
	private String actionMethod;
	private String actionResult;
	private Boolean isException;

	// Constructors

	/** default constructor */
	public ActionLog() {
	}

	// Property accessors

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

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerAddr() {
		return this.serverAddr;
	}

	public void setServerAddr(String serverAddr) {
		this.serverAddr = serverAddr;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddr() {
		return this.clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public String getClientMac() {
		return this.clientMac;
	}

	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}

	public String getClientUserAgent() {
		return this.clientUserAgent;
	}

	public void setClientUserAgent(String clientUserAgent) {
		this.clientUserAgent = clientUserAgent;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getActionClass() {
		return this.actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getActionMethod() {
		return this.actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getActionResult() {
		return this.actionResult;
	}

	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}

	public Boolean getIsException() {
		return this.isException;
	}

	public void setIsException(Boolean isException) {
		this.isException = isException;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClientBrowser() {
		
		if (this.clientUserAgent == null)
			return null;
		
		if (this.clientUserAgent.indexOf("Maxthon") >= 0) {
			String agent = this.clientUserAgent.substring(this.clientUserAgent.indexOf("Maxthon"));
			if (agent.indexOf(" ") >= 0)
				return agent.substring(0, agent.indexOf(" "));
			else
				return agent;
		}
		else if (this.clientUserAgent.indexOf("Trident") >= 0) {
			String agent = this.clientUserAgent.substring(this.clientUserAgent.indexOf("Trident"));
			if (agent.indexOf(";") >= 0)
				return agent.substring(0, agent.indexOf(";"));
			else
				return agent;
		}
		else if (this.clientUserAgent.indexOf("Firefox") >= 0) {
			String agent = this.clientUserAgent.substring(this.clientUserAgent.indexOf("Firefox"));
			if (agent.indexOf(" ") >= 0)
				return agent.substring(0, agent.indexOf(" "));
			else
				return agent;
		}
		else if (this.clientUserAgent.indexOf("MSIE") >= 0)
			return this.clientUserAgent.substring(this.clientUserAgent.indexOf("MSIE") + 2, this.clientUserAgent.indexOf("MSIE") + 8);
		else if (this.clientUserAgent.indexOf("Chrome") >= 0) {
			String agent = this.clientUserAgent.substring(this.clientUserAgent.indexOf("Chrome"));
			return agent.substring(0, agent.indexOf(" "));
		} else if (this.clientUserAgent.indexOf("Opera") >= 0) {
			String agent = this.clientUserAgent.substring(this.clientUserAgent.indexOf("Opera"));
			return agent.substring(0, agent.indexOf(" "));
		} else if (this.clientUserAgent.indexOf("Safari") >= 0) {
			return this.clientUserAgent.substring(this.clientUserAgent.indexOf("Safari"));
		}
		
		return null;
	}
}