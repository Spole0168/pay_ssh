package com.ibs.common.module.frameworkimpl.security.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.PageCache;
import com.ibs.portal.framework.server.metadata.SystemMenu;

/**
 * 合成用户对象,非持久化类,提供与用户相关的属性
 * 
 * @author
 * 
 */
public class UserComplex implements IUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -449028895361474600L;

	private String userId;
	private String userName;
	private String userCode; // mapping to code from Employee/Customer/Agent tables
	private String displayName; // mapping to name from Employee/Customer/Agent tables
	private String userType;
	
	private String currentRoleId;
	private String currentRoleName;
	
	private String[] roleIds; // roleId
	
	private List<String> roleCodes;
	
	private Map<String, String> urlMaps;
	
	private List<OptionObjectPair> rolePairs;

	//当前页面缓存
	private Map<String, PageCache> pageCache;
	
	//当前用户菜单
	private List<SystemMenu> menus;
	
	//当前用户点击的菜单
	private String menuId;
	
	private List<SystemMenu> guiMenus;
	
	private List<SystemMenu> pdaMenus;
	
	private Set<String> operCodes;
	
//	private Integer passwordActiveDay;
//	
//	public Integer getPasswordActiveDay() {
//		return passwordActiveDay;
//	}
//
//	public void setPasswordActiveDay(Integer passwordActiveDay) {
//		this.passwordActiveDay = passwordActiveDay;
//	}

	public List<SystemMenu> getGuiMenus() {
		return guiMenus;
	}

	public void setGuiMenus(List<SystemMenu> guiMenus) {
		this.guiMenus = guiMenus;
	}

	public String getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(String currentRoleId) {
		this.currentRoleId = currentRoleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentRoleName() {
		return currentRoleName;
	}

	public List<OptionObjectPair> getRolePairs() {
		return rolePairs;
	}

	public void setRolePairs(List<OptionObjectPair> rolePairs) {
		this.rolePairs = rolePairs;
	}

	public void setCurrentRoleName(String currentRoleName) {
		this.currentRoleName = currentRoleName;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public void putPageCache(String pageName, PageCache pageValue) {
		synchronized (pageName) {
			if (null == pageCache) {
				pageCache = new LinkedHashMap<String, PageCache>(10, 0.75f, true) {
					// 最大缓存10个页面,加载因子0.75,排序规则LRU(Least Recently Used)
					private static final long serialVersionUID = -3593256292511960647L;

					protected boolean removeEldestEntry(
							Map.Entry<String, PageCache> eldest) {
						// 保持容量,将较旧的entry剔除
						return size() > 10;
					}
				};
			}
		}

		pageCache.put(pageName, pageValue);

	}

	public PageCache getPageCache(String pageName) {
		if (null != pageCache) {
			return pageCache.get(pageName);
		} else
			return null;
	}

	public List<SystemMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SystemMenu> menus) {
		this.menus = menus;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUserCode() {
		//zdp 统一处理，user表中，userCode不再使用，统一返回userName
		return userName;
		//return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

	public Set<String> getOperCodes() {
		return operCodes;
	}

	public void setOperCodes(Set<String> operCodes) {
		this.operCodes = operCodes;
	}

	public Map<String, String> getUrlMaps() {
		return urlMaps;
	}

	public void setUrlMaps(Map<String, String> urlMaps) {
		this.urlMaps = urlMaps;
	}

	public boolean hasPermission(String operCode) {
		return this.operCodes.contains(operCode);
	}

	public List<SystemMenu> getPdaMenus() {
		return pdaMenus;
	}

	public void setPdaMenus(List<SystemMenu> pdaMenus) {
		this.pdaMenus = pdaMenus;
	}
	
}
