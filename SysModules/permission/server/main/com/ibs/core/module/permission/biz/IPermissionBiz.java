package com.ibs.core.module.permission.biz;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.common.module.frameworkimpl.security.domain.Mac;
import com.ibs.common.module.frameworkimpl.security.domain.MacUser;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.dto.MacDto;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.server.metadata.Tree;

/**
 * For those methods that not provided in IPermissionService, <br>
 * mainly about application and resource related methods <br>
 * The interface only used in permission related maintaince Actions
 * 
 * @author huolang
 * 
 */
public interface IPermissionBiz {
	// ---- application ----
	public IPage<Application> getAppListByPage(QueryPage page);

	public Application getAppCascadeById(String appId);

	public Application getAppById(String appId);

	public void saveOrUpdateApp(Application app);

	public void deleteApp(String id);
	
	public Application findApplicationByName(String appName);

	// ---- operation ----
	public IPage<Menu> getOperListByPage(QueryPage page);

	public Menu getOperById(String operId);
	
	public void saveOrUpdateOperation(Menu oper);
	
	public Menu getOperByName(String operType, String operName);
	
	public void deleteOper(String operId);
	
	// ---- user ----
//	public IPage<User> getUserListByPage(QueryPage page);
	
	public IPage<UserDto> getUserListByPage(String name, String code,
			String status, String type, String displayName, String description,
			String roleId, String districtId,
			int pageSize, int pageIndex, Map<String, String> sortMap);

	public User getUserById(String userId);
	
	public User getUserCascadeById(String id);

	public void saveOrUpdateUser(User user);
	
	public void saveOrUpdateUser(String userId, String userName,
			String userPwd, String status, String description);
	
	public void grantRolesToUser(String userId, String currentPageAllRoleIds,
			String currentPageSelectedRoleIds);
	
	public void deleteUser(String userId);
	
	// qiyongping add 20160720
	public IPage<User> getUsersByPage(QueryPage queryPage);

	// ---- role ----

	public IPage<Role> getRoleListByPage(QueryPage page);

	public Role getRoleById(String roleId, boolean withCascade);

	public void saveOrUpdateRole(Role role);

	public void saveOrUpdateRole(Role role, boolean editMenu,
			String grantedMenuIdString);
	
	public void deleteRole(String id);
	public void deleteRoles(String[] ids);
	
	public IPage<User> getUsersByRoleByPage(String roleId, QueryPage page);
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, int pageSize, int pageIndex, Map<String, String> sortMap);
	
	public void grantOpersToRole(String roleId, String currentPageAllOperIds,
			String currentPageSelectedOperIds);
	
	public void grantMenusToRole(String roleId, String grantedMenuIdString);
	
	public Role getRoleByCode(String code);
	
	public Role getRoleByName(String name);
	
	// ---- menu ----
	
//	public List<Menu> getAllMenu();
	
	public List<Tree> expendMenuTree(String nodeId);
	
	public List<Tree> buildMenuTree();
	public List<Tree> buildMenuTree(List<Menu>menus);

	public Menu getMenuById(String menuId);

	public Menu getMenuCascadeById(String menuId);

	public String saveOrUpdateMenu(final String menuId, final String menuName,
			final String menuTitle, final String menuTitleEn,
			final String location, final String description,
			final Integer displayOrder, final String target,
			final String parentMenuId, final String appId, final String menuType) ;

	public void deleteMenu(String id);

	public List<Menu> searchMenuByTitleWithParent(String str);

	public List<User> checkUniqueUserCode(String code);
	
	public List<User> checkUniqueUserName(String name);
	
	public IPage<RoleDto> getRolePageBySql(String roleName, String roleCode,
			String description, List<String> scopes,List<String> manageScopes, List<String> appIds,
			List<String> groupIds, List<String> rejectRoleIds, String userId,
			Boolean userHasRole, int pageSize, int pageIndex,
			Map<String, String> sortMap);
	
//	public List<OptionObjectPair> getRoleGroupPairs(boolean needDefault, String name, int pageSize);
	
	public List<OptionObjectPair> getAppPairs(boolean needDefault);
	
	public List<Tree> expendManagedMenuTree(String nodeId);
	
	public List<Menu> getManageMenus(String roleId);
	
	public void grantManageMenusToRole(String roleId, String grantedMenuIdString);
	
	public void initUserPwd(int pageSize, int pageIndex);
	
	/**
	 * 通过菜单ID 反查相关的用户ID， 提供给缓存刷新用
	 * @param menuId
	 * @return
	 */
	public List<String> getUserIdListByMenuId(String menuId);
	
	/**
	 * 从角色中删除一批用户
	 * @param userIds
	 * @param roleId
	 * @return
	 */
	public int removeUserFromRole(List<String> userIds, String roleId);

	public Page<MacDto> findMacByPage(QueryPage queryPage,	Map<String, String> searchFields);

	public List<Mac> getMacIdsByMenuId(String menuId);

	public void saveMenuBindingMac(String menuId, Set<String> macSet);

	public Page<MacUser> findMacUsersByPage(QueryPage queryPage, Map<String, String> searchFields);
	
	public IPage<RoleDto> getRoleByMenu(String menuId,
			int pageSize, int pageIndex, Map<String, String> sortMap);

	/**
	 * 根据id，菜单名，菜单类型验证
	 * @param id
	 * @param menuName
	 * @param menuType
	 * @return
	 */
	public int checkValid(String id, String menuName, String menuType);
	
	public Menu getMenuByMenuName(String menuName);
	
	public Menu getMenuByMenuTitle(String menuTitle);
	
	public List<User> getUsersByRole(String roleId);
}
