package com.ibs.portal.framework.server.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibs.portal.framework.server.metadata.PageCache;
import com.ibs.portal.framework.server.metadata.SystemMenu;

public interface IUser extends Serializable {
	/**
	 * get user id
	 * @return
	 */
	public String getUserId();

	/**
	 * get login user name
	 * @return
	 */
	public String getUserName();
	
	/**
	 * get user code which mapping to Employee/Customer/Agent
	 * @return
	 */
	public String getUserCode();

	/**
	 * get name that can be displayed
	 * @return
	 */
	public String getDisplayName();

	/**
	 * get which type of the user (1.Employee/2.Customer/3.Agent)
	 * @return
	 */
	public String getUserType();
	
	/**
	 * 获取当前用户所有的角色的Code
	 * @return
	 */
	public List<String> getRoleCodes();
	
	public Set<String> getOperCodes();
	
	public boolean hasPermission(String operCode);
	
	public Map<String, String> getUrlMaps();

	public void setUserName(String userName); //???

	public void putPageCache(String pageName, PageCache pageCache);

	public PageCache getPageCache(String pageName);
	
	public String getMenuId();
	
	public void setMenuId(String menuId);
	
	public List<SystemMenu> getMenus();
	
	public List<SystemMenu> getGuiMenus();
	public List<SystemMenu> getPdaMenus();

	public void setMenus(List<SystemMenu> menus);
	
}
