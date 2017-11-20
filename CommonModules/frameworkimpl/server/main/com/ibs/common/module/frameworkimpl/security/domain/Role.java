package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.HashSet;
import java.util.Set;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 系统角色
 * 
 * @author luoyue
 *
 */
public class Role extends BaseEntity {

	public static final String ROLE_IS_ADMIN = "Y";
	public static final String ROLE_NOT_ADMIN = "N";
	public static final String WHOLE_ROLE_SEPARATOR = "|";
	public static final String WHOLE_SCOPE_SEPARATOR = "#";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5551232109471587067L;

	private String id;
	private String roleName;
	private String roleCode; // 考虑从遗留系统导入数据
	private String description;
//	private String createRoleId;
//	private String wholeCreateRoleId;
	private String isAdmin;
	private String deletable;
	
	private Set<Menu> grantedMenus = new HashSet<Menu>(0);
	private Set<Menu> grantedGuiMenus = new HashSet<Menu>(0);
	private Set<Menu> grantedOpers = new HashSet<Menu>(0);
	private Set<User> includedUsers = new HashSet<User>(0);
	
	private Application application;
	private String appId;
	
	private String scope;
	private String userScope;
	//管理范围
	private String manageScope;
	
	private String createOrgId; // 创建组织ID
	
	public String getCreateOrgId() {
		return createOrgId;
	}

	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
	}

	public Role() {
	}

	public Set<User> getIncludedUsers() {
		return includedUsers;
	}

	public void setIncludedUsers(Set<User> includedUsers) {
		this.includedUsers = includedUsers;
	}

//	public Application getApplication() {
//		return application;
//	}
//
//	public void setApplication(Application application) {
//		this.application = application;
//	}

	public Role(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Set<Menu> getGrantedMenus() {
		return grantedMenus;
	}

	public void setGrantedMenus(Set<Menu> grantedMenus) {
		this.grantedMenus = grantedMenus;
	}

	public Set<Menu> getGrantedOpers() {
		return grantedOpers;
	}

	public void setGrantedOpers(Set<Menu> grantedOpers) {
		this.grantedOpers = grantedOpers;
	}

	public Set<Menu> getGrantedGuiMenus() {
		return grantedGuiMenus;
	}

	public void setGrantedGuiMenus(Set<Menu> grantedGuiMenus) {
		this.grantedGuiMenus = grantedGuiMenus;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

//	public String getCreateRoleId() {
//		return createRoleId;
//	}
//
//	public void setCreateRoleId(String createRoleId) {
//		this.createRoleId = createRoleId;
//	}
//
//	public String getWholeCreateRoleId() {
//		return wholeCreateRoleId;
//	}
//
//	public void setWholeCreateRoleId(String wholeCreateRoleId) {
//		this.wholeCreateRoleId = wholeCreateRoleId;
//	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getDeletable() {
		return deletable;
	}

	public void setDeletable(String deletable) {
		this.deletable = deletable;
	}

	public String getUserScope() {
		return userScope;
	}

	public void setUserScope(String userScope) {
		this.userScope = userScope;
	}

	public String getManageScope() {
		return manageScope;
	}

	public void setManageScope(String manageScope) {
		this.manageScope = manageScope;
	}
	
	
	
}
