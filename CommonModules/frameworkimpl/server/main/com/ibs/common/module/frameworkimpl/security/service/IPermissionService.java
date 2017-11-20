package com.ibs.common.module.frameworkimpl.security.service;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.common.module.frameworkimpl.security.dto.UserComplex;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.server.metadata.SystemMenu;
import com.ibs.portal.framework.server.metadata.Tree;


public interface IPermissionService {

	/**
	 * 获取用户对象
	 */
	public User getUserByUserName(String userName);

	public String login(String loginIp, String userName, String password);
	
	public String pdaLogin(String userName, String password);

	public String loginSSO(String loginIp, String userName);
	
	public List<Role> getAllRolesByUserName(String userName);
	
	public List<SystemMenu> loadUserMenu(String userId);
 
	public List<Menu> getAllMenusByUserId(String userId);

	public List<Menu> getAllMenusByUserIdAndRole(String userId,String roleId);
	
	public void switchRole(UserComplex suser, String nextRoleId);
	
	public List<Menu> getAllMenu();
	
	public List<Menu> getAllMenuByLevel(int level);
	
	public List<Tree> buildOneLevelMenuTree(List<Menu> menus);
	
	public List<Tree> buildMenuTree(List<Menu>menus);
	
	public Menu getMenuById(String menuId);

	public Menu getMenuCascadeById(String menuId);
	
	public List<Menu> getMenuByParentId(String parentMenuId);
	
	public String saveOrUpdateMenu(final String menuId, final String menuName,
			final String menuTitle, final String menuTitleEn,
			final String location, final String description,
			final Integer displayOrder, final String target,
			final String parentMenuId, final String appId, final String menuType) ;

	public void deleteMenu(String menuId);	
	
	// ---- application ----
	public IPage<Application> getAppListByPage(QueryPage page);

	public Application getAppCascadeById(String appId);

	public Application getAppById(String appId);

	public void saveOrUpdateApp(Application app);

	public void deleteApp(String id);
	
	public Application findApplicationByName(String appName);

	// ---- operation ----
	public IPage<Menu> getOperListByPage(QueryPage page);
	
	public void saveOrUpdateOperation(Menu oper);
	
	public void deleteOperation(String operId);
	
	public Menu getOperationById(String operId);
	
	public void grantOpersToRole(String roleId, String currentPageAllOperIds,
			String currentPageSelectedOperIds);
	
//	public IPage<Resource> findResourceByPage(String resourceName,
//			String resourceType, String resourceValue, String moduleCode,
//			int pageSize, int pageIndex);
//
//	public Resource getResourceById(String id);
//
//	public Resource getResourceCascadeById(String id);
//
//	public void saveOrUpdateResource(Resource res);
//
//	public void deleteResource(String id);
//	
//	public void grantResource2Role(final Role role, final String resourceId);
	
	// ---- Role ----
	public IPage<Role> findRoleByPage(String roleName, String roleCode,
			String description, int pageSize, int pageIndex);
	
	public IPage<Role> getRoleListByPage(QueryPage page);
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, int pageSize, int pageIndex);
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, int pageSize, int pageIndex, Map<String,String> sortMap);

	public Role getRoleById(String id);

	public Role getRoleCascadeById(String id);

	public void saveOrUpdateRole(Role role);
	
	public void deleteRole(String id);
	public void deleteRoles(String[] ids);
	
	public void grantRolesToUser(String userId,
			String currentPageAllRoleIds, String currentPageSelectedRoleIds);
	
//	public void grantMenu2Role(final Role role, final String menuId);
	
	public void grantMenusToRole(String roleId, String grantedMenuIdString);
	
	public IPage<User> getUsersByRoleByPage(String roleId, QueryPage page);
	
	public Role getRoleByCode(String code);
	
	public Role getRoleByName(String name);
	
	// ---- User ----
//	public IPage<User> findUserByPage(String userName, String userCode,
//			String employeeName, String employeeSN, String mobile, String type,
//			String status, String orgId, int pageSize, int pageIndex);
	
//	public IPage<User> getUserListByPage(String name, String code,
//			String status, String type, String displayName, String description,
//			String orgId, String orgCode, int pageSize, int pageIndex);
	
	public IPage<UserDto> getUserListByPage(String name, String code,
			String status, String type, String displayName, String description,
			String roleId, int pageSize, int pageIndex,
			Map<String, String> sortMap);
	
//	public IPage<User> getUserListByPage(QueryPage page);
	
	public User getUserById(String id);
	
	public User getUserByCode(String code);

	public User getUserCascadeById(String id);

	public void saveOrUpdateUser(User user);
	
	public void saveOrUpdateUser(String userId, String userName,
			String userPwd, String status, String description);

	public void deleteUser(String id);
	
	/**
	 * 根据传进来的旧密码，重置当前用户的密码为新密码
	 * @param oriPwd 原先密码
	 * @param newPwd 新密码
	 * @param isEncrypted 传进来的密码是否是密文; true-密文；false-明文
	 * @return
	 */
	public boolean resetUserPwd(String oriPwd, String newPwd, boolean isEncrypted);
	
	/**
	 * 根据传进来的用户ID和旧密码，重置当前用户的密码为新密码
	 * @param userId 用户ID
	 * @param oriPwd 原先密码
	 * @param newPwd 新密码
	 * @param isEncrypted 传进来的密码是否是密文; true-密文；false-明文
	 * @return
	 */
	public boolean resetUserPwd(String userId, String oriPwd, String newPwd, boolean isEncrypted);
	
	public Menu loadMenuByTypeAndName(final String menuType,
			final String menuName);
	
	/**
	 * 根据用户名和密码，验证用户是否可以登录，并返回用户基本信息
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * user：id,name,displayName, code,type
	 */
	public IUser webServiceLogin(String userName, String password);
	
	/**
	 * 根据角色的Code， 查询拥有次Code的员工
	 * @param roleCodes
	 * @return
	 * 返回的对象只包括以下内容
	 * id,code,displayname
	 */
	public List<User> getEmployeeUserByRole(List<String> roleCodes);
	
	/**
	 * 根据角色的Code， 查询拥有次Code的员工
	 * @param roleCodes
	 * @param orgCodes
	 * @param parm	可空，传入用户的名字或Code，支持模糊搜索
	 * @return
	 * 返回的对象只包括以下内容
	 * id,code,displayname
	 */
	public List<User> getEmployeeUserByRole(List<String> roleCodes, String parm);
	
	/**
	 * 根据权限的名称，返回符合查询条件的员工
	 * @param permissionNames 权限名称列表（仅限于控制，页面按钮，url）
	 * @param orgCodes 可空，传入指定组织机构
	 * @param parm 可空，传入用户的名称或code支持模糊搜索
	 * @return
	 */
	public List<User> getEmployeeUserByPermission(List<String> permissionNames, String parm);
	
	public List<Menu> searchMenuByTitleWithParent(String str);
	
	public List<User> checkUniqueUserCode(String code);
	
	public List<User> checkUniqueUserName(String name);
	
	public IPage<RoleDto> getRolePageBySql(String roleName, String roleCode,
			String description, List<String> scopes,List<String> manageScopes, List<String> appIds,
			List<String> groupIds, List<String> rejectRoleIds, String userId,
			Boolean userHasRole, int pageSize, int pageIndex,
			Map<String, String> sortMap);
	
	public List<Application> findAllApplication();
	
	/**
	 * 获取菜单
	 * @param roleCodes
	 * @param level
	 * @param parentMenuId
	 * @param menuTypes
	 * @return
	 */
	public List<Menu> getMenuByRoleCodes(List<String> roleCodes, Integer level,
			String parentMenuId,List<String> menuTypes);
	/**
	 * 给某一角色赋予管理菜单权限
	 * @param roleId
	 * @param menuIdStr
	 * @return
	 */
	public void grantManageMenuToRole(String roleId, String menuIdStr);
	
	/**
	 * 初始化一批用户密码，使用方法
	 * 1. 数据迁移时，初始化明文，要求长度不大于20
	 * 2. 运行此代码，指定起始和总更新数
	 * 
	 * 后台将原先明文密码更改为密文，供初次上线时使用
	 * @param pageSize
	 * @param pageIndex
	 */
	public void initUserPwd(int pageSize, int pageIndex);
	
	/**
	 * 检查用户输入的密码是否正确，以及距离过期还有多少天
	 * @param oriPwd 用户输入的密码，如果为空，则查询用户当前密码所剩天数
	 * @param userId 用户ID，如果为空，则取当前用户的ID
	 * 
	 * @return <0 输入密码不匹配用户的密码
	 * @return >0 输入密码匹配用户密码，且未过期；返回为密码剩余天数（如果输入密码为空，则返回当前密码剩余天数）
	 * @return =0 输入的密码匹配用户密码，但是已经过期（包括初始化密码）
	 */
	public Integer checkUserPwd(String userId, String oriPwd);
	
	/**
	 * 检查当前用户密码距离过期时常
	 * @param user
	 * @return 剩余天数；如果过期，则返回0
	 */
	public Integer checkUserPwdActiveDays(User user);
	
	/**
	 * 取消以一个用户所拥有的全部角色
	 */
	public void unassigedAllRoleFromUser(String userId);
	
	/**
	 * mac地址是否有权限
	 * @param menuId
	 * @return
	 */
	public Boolean hasMacPermission(String menuId, IUser user);

	/**
	 * 给用户赋角色
	 * @param userName 用户名
	 * @param roleCodes 角色代码list
	 */
	public void assignUserRoles(String userName,List<String> roleCodes);
	
	
	/**
	 * 取消用户角色
	 * @param userName 用户名
	 * @param roleCodes 角色代码list
	 */
	public void unAssignUserRoles(String userName,List<String> roleCodes);
	
	/**
	 * @param userName
	 * @param orgCode
	 * @param userType 参考 User 类中定义， 员工：employee；客户：customer；航空货代：fltagent；国际件货代：intagent
	 * @param defaultRoleCodes  参考 com.ibs.common.module.frameworkimpl.security.constant.RoleConst 定义
	 */
	public void createUser(String userId,String userName,String userType,List<String> defaultRoleCodes);
	
	/**
	 * 注销用户
	 * @param userName
	 */
	public void writeOffUser(String userName);

	public void deleteUserAll(String id);
}
