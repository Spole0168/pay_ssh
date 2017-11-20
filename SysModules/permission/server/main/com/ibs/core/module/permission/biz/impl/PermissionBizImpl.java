package com.ibs.core.module.permission.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;
import com.ibs.common.module.frameworkimpl.security.dao.IMacDao;
import com.ibs.common.module.frameworkimpl.security.dao.IMacMenuDao;
import com.ibs.common.module.frameworkimpl.security.dao.IMacUserDao;
import com.ibs.common.module.frameworkimpl.security.dao.IMenuDao;
import com.ibs.common.module.frameworkimpl.security.dao.IUserDao;
import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.common.module.frameworkimpl.security.domain.Mac;
import com.ibs.common.module.frameworkimpl.security.domain.MacMenu;
import com.ibs.common.module.frameworkimpl.security.domain.MacUser;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.dto.MacDto;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.core.module.permission.biz.IPermissionBiz;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.server.metadata.Tree;
import com.ibs.portal.framework.util.StringUtils;

public class PermissionBizImpl extends BaseBiz implements IPermissionBiz {
	protected final Log log = LogFactory.getLog(getClass());

	private IPermissionService permissionService;
	private IMenuDao menuDao;
	private IUserDao userDao;
	private IMacDao macDao;
	private IMacMenuDao macMenuDao;
	private IMacUserDao macUserDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setMacDao(IMacDao macDao) {
		this.macDao = macDao;
	}
	
	public void setMacMenuDao(IMacMenuDao macMenuDao) {
		this.macMenuDao = macMenuDao;
	}
	
	public void deleteApp(String id) {
		permissionService.deleteApp(id);

	}

	public Application getAppById(String appId) {
		return permissionService.getAppById(appId);
	}

	public Application getAppCascadeById(String appId) {
		return permissionService.getAppCascadeById(appId);
	}

	public IPage<Application> getAppListByPage(QueryPage page) {
		return permissionService.getAppListByPage(page);
	}

	public void saveOrUpdateApp(Application app) {
		permissionService.saveOrUpdateApp(app);
	}
	
	public Application findApplicationByName(String appName) {
		return permissionService.findApplicationByName(appName);
	}

//	public IPage<User> getUserListByPage(QueryPage page) {
//		return permissionService.getUserListByPage(page);
//	}
	
	public IPage<UserDto> getUserListByPage(String name, String code,
			String status, String type, String displayName, String description,
			String roleId, String districtId,
			int pageSize, int pageIndex, Map<String, String> sortMap) {
//		String searchDistrictId = null;
//		if (null != districtId && !"".equals(districtId)) {
//			StringBuffer bf = new StringBuffer();
//			List<District> districtList = districtService
//					.getAllDistrictByParent(districtId);
//			if (districtList != null) {
//				for (Iterator<District> itr = districtList.iterator(); itr
//						.hasNext();) {
//					District object = itr.next();
//					bf.append("'");
//					bf.append(object.getId());
//					bf.append("'");
//					if (itr.hasNext()) {
//						bf.append(",");
//					}
//				}
//
//			}
//			searchDistrictId = bf.toString();
//		}
		Page<UserDto> page = (Page<UserDto>) permissionService
				.getUserListByPage(name, code, status, type, displayName,
						description, roleId, pageSize, pageIndex, sortMap);
		return page;
	}

	public User getUserById(String userId) {
		return permissionService.getUserById(userId);
	}

	public void saveOrUpdateUser(User user) {
		permissionService.saveOrUpdateUser(user);
	}
	
	public void saveOrUpdateUser(String userId, String userName,
			String userPwd, String status, String description) {
		permissionService.saveOrUpdateUser(userId, userName, userPwd, status,
				description);
	}

	public IPage<Role> getRoleListByPage(QueryPage page) {
//		List<String> currentRoleList = getCurrentUser().getRoleCodes();
		
		return permissionService.getRoleListByPage(page);
	}

	public Role getRoleById(String roleId, boolean withCascade) {
		return (withCascade == true) ? permissionService
				.getRoleCascadeById(roleId) : permissionService
				.getRoleById(roleId);
	}

	public void saveOrUpdateRole(Role role) {
		permissionService.saveOrUpdateRole(role);
	}

	public void saveOrUpdateRole(Role role, boolean editMenu,
			String grantedMenuIdString) {
		permissionService.saveOrUpdateRole(role);
		String id = role.getId();

		if (editMenu == true && null != id && null != grantedMenuIdString) {
			permissionService.grantMenusToRole(id, grantedMenuIdString);
		}
	}

	public List<Tree> buildMenuTree() {
//		List<Menu> menus = permissionService.getAllMenu();
		List<Menu> menus = permissionService.getAllMenuByLevel(1);
//		return permissionService.buildMenuTree(menus);
		return permissionService.buildOneLevelMenuTree(menus);
	}

	public List<Tree> expendMenuTree(String nodeId) {
		// List<Menu> menus = permissionService.getAllMenu();
		List<Menu> menus = null;
		if (null == nodeId || "".equals(nodeId) || "0".equals(nodeId)) {
			menus = permissionService.getAllMenuByLevel(1);
		} else {
			menus = permissionService.getMenuByParentId(nodeId);
		}
		return permissionService.buildOneLevelMenuTree(menus);
	}
	
	public List<Tree> buildMenuTree(List<Menu>menus){
		return permissionService.buildMenuTree(menus);
	}
	/**
	 * 管理员应该看到的菜单选项
	 * @param nodeId
	 * @return
	 */
	public List<Tree> expendManagedMenuTree(String nodeId) {
		// List<Menu> menus = permissionService.getAllMenu();
		List<Menu> menus = null;
		IUser user = getCurrentUser();
		if(null == user){
			return null;
		}
		List<String> roles = user.getRoleCodes();
		if (null == nodeId || "".equals(nodeId) || "0".equals(nodeId)) {
//			menus = permissionService.getMenuByRoleCodes(roles, 1, null, null);
			menus = permissionService.getAllMenuByLevel(1);
		} else {
//			menus = permissionService.getMenuByRoleCodes(roles, null, nodeId, null);
			menus = permissionService.getMenuByParentId(nodeId);
		}
		
		
		return permissionService.buildOneLevelMenuTree(menus);
	}
	/**
	 * 通过角色的code获取角色管理的ID
	 * @param roleCode
	 * @return
	 */
	public List<Menu> getManageMenus(String roleCode){
//		Role role = permissionService.getRoleById(roleId);
//		if(null == role || StringUtils.isEmpty(role.getRoleCode())){
//			return null;
//		}
		List<String> roleCodes = new ArrayList<String>();
		roleCodes.add(roleCode);
		return permissionService.getMenuByRoleCodes(roleCodes, null, null, null);
	}
	
	public Menu getMenuById(String menuId) {
		return permissionService.getMenuById(menuId);
	}

	public Menu getMenuCascadeById(String menuId) {
		return permissionService.getMenuCascadeById(menuId);
	}

	public String saveOrUpdateMenu(final String menuId, final String menuName,
			final String menuTitle, final String menuTitleEn,
			final String location, final String description,
			final Integer displayOrder, final String target,
			final String parentMenuId, final String appId, final String menuType) {
		return permissionService.saveOrUpdateMenu(menuId, menuName, menuTitle,
				menuTitleEn, location, description, displayOrder, target,
				parentMenuId, appId, menuType);
	}

	
	public void deleteMenu(String id) {
		permissionService.deleteMenu(id);
	}

	public void deleteRole(String id) {
		permissionService.deleteRole(id);
	}

	public void deleteRoles(String [] ids) {
		permissionService.deleteRoles(ids);
	}

	public void grantRolesToUser(String userId, String currentPageAllRoleIds,
			String currentPageSelectedRoleIds) {
		permissionService.grantRolesToUser(userId, currentPageAllRoleIds,
				currentPageSelectedRoleIds);
	}

	public User getUserCascadeById(String id) {
		return permissionService.getUserCascadeById(id);
	}

	public Menu getOperById(String operId) {
		return permissionService.getOperationById(operId);
	}

	public IPage<Menu> getOperListByPage(QueryPage page) {
		page.addInSearch("menuType", Menu.OPER_TYPE_ALL);
		
		return permissionService.getOperListByPage(page);
	}
	
	public void saveOrUpdateOperation(Menu oper){
		if(null == oper){
			return ;
		}
		Menu menu = null;
		String id = oper.getId();
		if(null != id && !"".equals(id)){ // update
			menu = permissionService.getMenuById(id);
		}else{ // create
			menu = new Menu();
			menu.setMenuName(oper.getMenuName());
			menu.setMenuType(oper.getMenuType());
		}
		menu.setMenuName(oper.getMenuName());
		menu.setLocation(oper.getLocation());
		menu.setDescription(oper.getDescription());
		permissionService.saveOrUpdateOperation(menu);
	}
	public IPage<User> getUsersByRoleByPage(String roleId, QueryPage page){
		Page<User> userPage = (Page<User>)permissionService.getUsersByRoleByPage(roleId, page);
		return userPage;
	}
	
	public void grantOpersToRole(String roleId, String currentPageAllOperIds,
			String currentPageSelectedOperIds) {
		permissionService.grantOpersToRole(roleId, currentPageAllOperIds,
				currentPageSelectedOperIds);
	}
	
	public void grantMenusToRole(String roleId, String grantedMenuIdString){
		permissionService.grantMenusToRole(roleId, grantedMenuIdString);
	}
	
	public void grantManageMenusToRole(String roleId, String grantedMenuIdString){
		permissionService.grantManageMenuToRole(roleId, grantedMenuIdString);
	}
	
	public void deleteUser(String userId) {
//		permissionService.deleteUser(userId);
		permissionService.deleteUserAll(userId);
	}

	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, int pageSize, int pageIndex, Map<String, String> sortMap) {
		return permissionService.getRoleListByPage(roleName, roleCode,
				description, isAdmin, pageSize, pageIndex, sortMap);
	}
	
	public Menu getOperByName(String operType, String operName) {
		return permissionService.loadMenuByTypeAndName(operType, operName);
	}
	
	public Role getRoleByCode(String code){
		return permissionService.getRoleByCode(code);
	}
	
	public Role getRoleByName(String name){
		return permissionService.getRoleByName(name);
	}
	
	public void deleteOper(String operId){
		permissionService.deleteOperation(operId);
	}
	
	public List<Menu> searchMenuByTitleWithParent(String str){
		return permissionService.searchMenuByTitleWithParent(str);
	}
	
	public List<User> checkUniqueUserCode(String code){
		return permissionService.checkUniqueUserCode(code);
	}
	
	public List<User> checkUniqueUserName(String name){
		return permissionService.checkUniqueUserName(name);
	}
	
	public IPage<RoleDto> getRolePageBySql(String roleName, String roleCode,
			String description, List<String> scopes,List<String> manageScopes, List<String> appIds,
			List<String> groupIds, List<String> rejectRoleIds, String userId,
			Boolean userHasRole, int pageSize, int pageIndex,
			Map<String, String> sortMap) {
//		OptionObjectPair[] priority = PermissionConstant.ORG_TYPE_PRIORITY;
//		if (null == scopes || scopes.size()<=0) {
//			scopes = new ArrayList<String>();
//			for(int i=0;i<priority.length;i++){
//				scopes.add(priority[i].getKey());
//			}
//		}
//		
//		if (null == manageScopes || manageScopes.size()<=0) {
//			manageScopes = new ArrayList<String>();
//			for(int i=0;i<priority.length;i++){
//				manageScopes.add(priority[i].getKey());
//			}
//		}
		
		return permissionService.getRolePageBySql(roleName, roleCode,
				description, scopes,manageScopes, appIds, groupIds, rejectRoleIds, userId,
				userHasRole, pageSize, pageIndex, sortMap);
	}
	
//	public List<OptionObjectPair> getRoleGroupPairs(boolean needDefault,String name,
//			int pageSize) {
//		List<RoleGroup> groups = roleGroupDao.searchRoleGroup(name, null, null,
//				pageSize);
//		List<OptionObjectPair> pairs = new ArrayList<OptionObjectPair>();
//		if (needDefault) {
//			pairs.add(OptionObjectPair.getDefaultOptionObjectPair());
//		}
//		if (null != groups && groups.size() > 0) {
//			for (RoleGroup g : groups) {
//				pairs.add(new OptionObjectPair(g.getId(), g.getName()));
//			}
//		}
//		return pairs;
//	}
	
	public List<OptionObjectPair> getAppPairs(boolean needDefault){
		List<Application> appList = permissionService.findAllApplication();
		List<OptionObjectPair> appPairs = new ArrayList<OptionObjectPair>();
		
		if(needDefault){
			appPairs.add(OptionObjectPair.getDefaultOptionObjectPair());
		}
		
		if(null != appList && appList.size()>0){
			for(Application app:appList){
				appPairs.add(new OptionObjectPair(app.getId(),app.getDescription()));
			}
		}
		
		return appPairs;
	}
	
	public void initUserPwd(int pageSize, int pageIndex){
		permissionService.initUserPwd(pageSize, pageIndex);
	}
	
	public List<String> getUserIdListByMenuId(String menuId){
		return menuDao.getUserIdListByMenuId(menuId);
	}
	
	public int removeUserFromRole(List<String> userIds, String roleId){
		// 检查输入参数是否为空
		if(StringUtils.isEmpty(roleId) || null == userIds || userIds.size()<=0){
			return 0;
		}
		for(String userId:userIds){
			userDao.unassignRoleFromUser(userId, roleId);
		}
		return 0;
	}

	public Page<MacDto> findMacByPage(QueryPage queryPage,	Map<String, String> searchFields) {
		logger.trace("entering biz...");
		
		if (searchFields.containsKey("userName") && StringUtils.isNotEmpty(searchFields.get("userName"))) {
			return macDao.findMacUserByPage(queryPage, searchFields);
		}
		
		return macDao.findMacMenuByPage(queryPage, searchFields);
		
	}

	public List<Mac> getMacIdsByMenuId(String menuId) {
		logger.trace("entering biz...");
		
		return macDao.getMacIdsByMenuId(menuId);
	}

	public void saveMenuBindingMac(String menuId, Set<String> macSet) {
		//delete all and insert
		macMenuDao.deleteAllBindingMacByMenuId(menuId);
		
		for(String macId: macSet){
			MacMenu macMenu = new MacMenu();
			macMenu.setMenuId(menuId);
			macMenu.setMacId(macId);
			macMenuDao.save(macMenu);
		}
	}

	public void setMacUserDao(IMacUserDao macUserDao) {
		this.macUserDao = macUserDao;
	}

	public Page<MacUser> findMacUsersByPage(QueryPage queryPage, Map<String, String> searchFields) {
		logger.trace("into biz...");
		
		return macUserDao.findMacUsersByPage(queryPage, searchFields);
	}
	
	public IPage<RoleDto> getRoleByMenu(String menuId,
			int pageSize, int pageIndex, Map<String, String> sortMap){
		return menuDao.getRoleByMenu(menuId, pageSize, pageIndex, sortMap);
	}

	public int checkValid(String id, String menuName, String menuType) {
			
		List<Menu> alist = menuDao.findMenuByParm(menuName, menuType); 
		if (StringUtils.isNotEmpty(id)) {
			// modify
			if (alist != null && alist.size() > 0) {
				for (int i = 0; i < alist.size(); i++) {
					Menu object = alist.get(i);
					if (object.getMenuName().equalsIgnoreCase(menuName)
							&& !id.equalsIgnoreCase(object.getId())) {
						return 1;
					}
				}
				return 0;
			} else {
				return 0;
			}
		} else {
			if (alist != null && alist.size() > 0) {
				for (int i = 0; i < alist.size(); i++) {
					Menu object = alist.get(i);
					if (object.getMenuName().equalsIgnoreCase(menuName)) {
						return 1;
					} 
				}
				return 0;
			} else {
				return 0;
			}
		}
	}
	
	// qiyongping add 20160720
	public IPage<User> getUsersByPage(QueryPage queryPage) {
		return userDao.getUsersByPage(queryPage);
	}
	
	public Menu getMenuByMenuName(String menuName) {
		return menuDao.getMenuByMenuName(menuName);
	}
	
	public Menu getMenuByMenuTitle(String menuTitle) {
		return menuDao.getMenuByMenuTitle(menuTitle);
	}
	
	public List<User> getUsersByRole(String roleId) {
		return userDao.getUsersByRole(roleId);
	}
}