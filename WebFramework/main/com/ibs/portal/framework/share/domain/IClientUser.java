package com.ibs.portal.framework.share.domain;

import java.util.Date;
import java.util.List;

import com.ibs.portal.framework.server.domain.IEntity;
import com.ibs.portal.framework.server.metadata.SystemMenu;


public interface IClientUser extends IEntity {
	
	public String getUserId();

	public String getUserName();
	
	public String getUserCode();

	public String getEmployeeName();

	public String getOrgId();
	
	public String getOrgCode();

	public void setUserName(String userName);
	
	public List<String> getOrgFunctions();
	
	public List<SystemMenu> getGuiMenus();
	
	public String getSessionId();
	
	// 登录认证服务器时间
	public Date getLoginTime();

}
