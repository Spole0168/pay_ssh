package com.ibs.common.module.frameworkimpl.security.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.ibs.common.module.frameworkimpl.common.Constants;
import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;
import com.ibs.common.module.frameworkimpl.security.dao.IApplicationDao;
import com.ibs.common.module.frameworkimpl.security.dao.IMenuDao;
//import com.ibs.common.module.frameworkimpl.security.dao.IOrgDao;
import com.ibs.common.module.frameworkimpl.security.dao.IRoleDao;
import com.ibs.common.module.frameworkimpl.security.dao.IUserDao;
//import com.ibs.common.module.frameworkimpl.security.dao.IUserOrgDao;
import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.common.module.frameworkimpl.security.domain.Mac;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
//import com.ibs.common.module.frameworkimpl.security.domain.Org;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
//import com.ibs.common.module.frameworkimpl.security.domain.UserOrg;
import com.ibs.common.module.frameworkimpl.security.dto.MacDto;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.common.module.frameworkimpl.security.dto.UserComplex;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.common.module.frameworkimpl.security.exception.ErrorPasswordException;
import com.ibs.common.module.frameworkimpl.security.exception.IllPasswordException;
import com.ibs.common.module.frameworkimpl.security.exception.IllegalUserNameException;
import com.ibs.common.module.frameworkimpl.security.exception.InvalidUserException;
import com.ibs.common.module.frameworkimpl.security.exception.NotExistUserException;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.exception.ServiceException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.server.metadata.SystemMenu;
import com.ibs.portal.framework.server.metadata.Tree;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.Md5Encoder;
import com.ibs.portal.framework.util.StringUtils;

import net.sf.json.JSONObject;

/**
 * 获取org、user、role、resource、menu的服务， 注意，这里获取role、resource、menu的时候都考虑当前子系统
 * 
 * @author zjblue, huolang
 * 
 */
public class PermissionServiceImpl extends BaseService implements
		IPermissionService {

	// 当前系统名称
	public String applicationName;

	public IUserDao userDao;
	public IRoleDao roleDao;
	public IMenuDao menuDao;

	//
	private IApplicationDao applicationDao;
//	private IResourceDao resourceDao;

	protected ICache getApplicationCache() {
		return CacheManager.getInstance().getCache(Constants.APPLICATION_CACHE);
	}

	// 获取当前系统
	public Application getCurrentApplication() {
		// 按照当前系统名称获得当前系统
		Application application = (Application) getApplicationCache().getData(
				applicationName);

		// 如果当前application为空，抛异常
		if (application == null) {
			if(logger.isErrorEnabled())
				logger.error("无法获取当前子系统，请检查是否没有在application.properties中配置application.name");
			throw new ServiceException("无法获取当前子系统的信息");
		}

		return application;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public String encrypt(String password) {
		return new Md5Encoder().encode(password);
	}

	/**
	 * 获取用户对象
	 */
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	public User getUserByUserCode(String userCode) {
		return userDao.getUserByUserCode(userCode);
	}

	public String login(String loginIp, String userName, String password) {
		// 用户名不能为空并且必需大于3个字符
		if (null == userName || userName.length() < 3)
			throw new IllegalUserNameException();

		// 密码不能为空并且必需大于4个字符
		if (null == password || password.length() < PermissionConstant.MIN_PWD_LENGTH)
			throw new IllPasswordException();

		User user = this.getUserByUserName(userName);
		// 用户不存在
		if (null == user)
			throw new NotExistUserException();

		// 用户处于无效状态不能登录
		if (!User.USER_STATUS_VALID.equals(user.getStatus()))
			throw new InvalidUserException();
		// 用户是否启用
//		if (!PermissionConstant.USER_USED.equals(user.getUsed())){ 
//			throw new InvalidUserException();
//		}
		
		// 密码错误
		if(logger.isInfoEnabled())logger.info(encrypt(password));
		if (!encrypt(password).equalsIgnoreCase(user.getUserPwd()))
			throw new ErrorPasswordException();

		return user.getId();
	}
	
	public String pdaLogin(String userName, String password){
		// password已经经过MD5加密
		
		// 用户名不能为空并且必需大于3个字符
		if (null == userName || userName.length() < 3)
			throw new IllegalUserNameException();
		
		// 密码不能为空并且必需大于4个字符
		if (null == password || password.length() < PermissionConstant.MIN_PWD_LENGTH)
			throw new IllPasswordException();
		
		User user = this.getUserByUserName(userName);
		// 用户不存在
		if (null == user)
			throw new NotExistUserException();

		// 用户处于无效状态不能登录
		if (User.USER_STATUS_INVALID.equals(user.getStatus()))
			throw new InvalidUserException();

		// 用户是否启用
//		if (!PermissionConstant.USER_USED.equals(user.getUsed())){ 
//			throw new InvalidUserException();
//		}
		
		// 密码错误
		if(logger.isInfoEnabled())logger.info(password);
		if (!password.equalsIgnoreCase(user.getUserPwd()))
			throw new ErrorPasswordException();

		return user.getId();
	}

	public String loginSSO(String loginIp, String userCode) {
		// 用户名不能为空并且必需大于3个字符
		if (null == userCode || userCode.length() < 3)
			throw new IllegalUserNameException();

		User user = userDao.getUserByUserCode(userCode);

		// 用户不存在
		if (null == user)
			throw new NotExistUserException();

		// 用户处于无效状态不能登录
		if (User.USER_STATUS_INVALID.equals(user.getStatus()))
			throw new InvalidUserException();
		// 用户是否启用
//		if (!PermissionConstant.USER_USED.equals(user.getUsed())){ 
//			throw new InvalidUserException();
//		}

		return user.getId();
	}

	/*
	 * (non-Javadoc) 假定:用户登录后,数据完整性校验已完成
	 * 
	 * @see
	 * com.ibs.portal.framework.common.permission.biz.IPermissionFacade
	 * #getUserComplexById(java.lang.Integer)
	 */
	public List<UserComplex> getUserComplexByUserName(String userName) {
		User user = userDao.getUserByUserName(userName);
		List<UserComplex> ucs = new ArrayList<UserComplex>(0);

		UserComplex uc = new UserComplex();
		uc.setUserId(user.getId());
		uc.setUserName(user.getUserName());
		// uc.setEmployeeId(user.getEmployee().getId());
		// uc.setEmployeeName(user.getEmployee().getName());
		// uc.setEmployeeSN(user.getEmployee().getCode());

		// 设置roles
		String[] roleIds = new String[user.getAssignedRoles().size()];
		int roleIndex = 0;
		for (Role role : user.getAssignedRoles()) {

			if (roleIndex == 0) {
				uc.setCurrentRoleId(role.getId());
				uc.setCurrentRoleName(role.getRoleName());
			}

			roleIds[roleIndex] = role.getId();
			roleIndex++;
		}
		uc.setRoleIds(roleIds);

		ucs.add(uc);

		if (!ucs.isEmpty())
			return ucs;
		else
			return null;
	}
	
	/**
	 * 构造用户菜单
	 * 
	 * @param userName
	 * @return List<SystemMenu>
	 */
	public List<SystemMenu> loadUserMenu(String userId) {
		List<SystemMenu> smenus = new ArrayList<SystemMenu>(0);
		Map<String, SystemMenu> tmpMenus = new HashMap<String, SystemMenu>(0);

		if (logger.isDebugEnabled())
			logger.debug("构造菜单,当前的用户ID[" + userId + "]");

		// 获取用户所有的角色构造菜单
		List<Menu> menus = getAllMenusByUserId(userId);

		for(Menu menu : menus) {
			SystemMenu smenu = new SystemMenu();
			smenu.setId(menu.getId());
			smenu.setName(menu.getMenuName());
			smenu.setTitle(menu.getMenuTitle());
			smenu.setLocation(menu.getLocation());
			smenu.setDisplayOrder(menu.getDisplayOrder());
			smenu.setLevel(menu.getMenuLevel());
			smenu.setTarget(menu.getTarget());

			if (null == menu.getParentMenu()) {
				// 一级菜单
				smenus.add(smenu);
			} else
				smenu.setParentId(menu.getParentMenu().getId());

			tmpMenus.put(smenu.getId(), smenu);
		}

		// 构建子菜单
		buildSubMenu(menus, tmpMenus);

		// 排序一级菜单
		Collections.sort(smenus);

		for (SystemMenu m1 : smenus) {
			if (null != m1.getSubMenus()) {
				Collections.sort(m1.getSubMenus()); // 排序二级菜单
				for (SystemMenu m2 : m1.getSubMenus()) {
					if (null != m2.getSubMenus()) {
						Collections.sort(m2.getSubMenus()); // 排序三级菜单
					}
				}

			}
		}

		return smenus;
	}	

	public List<Menu> getAllMenusByUserId(String userId) {
		User user = userDao.loadCascade(userId);

//		Application currentApplication = this.getCurrentApplication();
//		String currentApplicationId = currentApplication.getId();

		if (null != user) {
			Set<Role> roles = user.getAssignedRoles();
			List<Menu> menus = new ArrayList<Menu>();

			for (Role role : roles) {

				// 需要检查当前role是否属于当前子系统
				// if(role.getApplication()!= null
				// &&
				// role.getApplication().getId().equals(currentApplicationId)) {
				role = roleDao.loadCascade(role.getId());
				Set<Menu> ms = role.getGrantedMenus();
				
				for (Menu menu : ms) {

					// 需要检查当前menu是否属于当前子系统
					// if(menu.getApplication() != null
					// &&
					// menu.getApplication().getId().equals(currentApplicationId))
					// {
					if (!menus.contains(menu)) {
						menus.add(menu);
					}
					// }
				}
				// }

			}

			return menus;
		}

		return null;
	}

	public List<Menu> getAllMenusByUserIdAndRole(String userName,String roleId) {
		User user = userDao.getUserByUserName(userName);

		Application currentApplication = this.getCurrentApplication();
		String currentApplicationId = currentApplication.getId();

		if (null != user) {
			Set<Role> roles = user.getAssignedRoles();
			List<Menu> menus = new ArrayList<Menu>();

			for (Role role : roles) {

				// 需要检查当前role是否属于当前子系统
				// if(role.getApplication()!= null
				// &&
				// role.getApplication().getId().equals(currentApplicationId)) {
				if (role.getId().equals(roleId)) {
					Set<Menu> ms = role.getGrantedMenus();

					for (Menu menu : ms) {
						// 需要检查当前menu是否属于当前子系统
						if (menu.getApplication() != null
								&& menu.getApplication().getId().equals(
										currentApplicationId)) {
							if (!menus.contains(menu)) {
								menus.add(menu);
							}
						}
					}
					break;
					// }
				}

			}

			return menus;
		}

		return null;
	}

	public List<Role> getAllRolesByUserName(String userName) {

		List<Role> roleList = new ArrayList<Role>();

//		Application currentApplication = this.getCurrentApplication();
//		String currentApplicationId = currentApplication.getId();

		User user = this.getUserByUserName(userName);

		if(null == user){
			return null;
		}
		for (Role role : user.getAssignedRoles()) {
			// 需要检查当前role是否属于当前子系统
			// if(role.getApplication()!= null
			// && role.getApplication().getId().equals(currentApplicationId)) {
			roleList.add(role);
			// }
		}

		return roleList;
	}

	public void switchRole(UserComplex suser, String nextRoleId) {
		if (!suser.getCurrentRoleId().equals(nextRoleId)) {

			for (String roleId : suser.getRoleIds()) {
				if (roleId.equals(nextRoleId)) {
					suser.setCurrentRoleId(nextRoleId);
					Role role = roleDao.load(nextRoleId);
					;
					suser.setCurrentRoleName(role.getRoleName());
					return;
				}
			}
		}
	}

	public List<Menu> getAllMenu() {
		return menuDao.getAllMenu();
	}

	public List<Menu> getAllMenuByLevel(int level) {
//		Application currentApplication = this.getCurrentApplication();
//		String currentApplicationId = currentApplication.getId();
//
//		return menuDao.getAllMenuByApplicationIdAndLevel(currentApplicationId,
//				1);
		return menuDao.getAllMenuByLevel(level);
	}
	
	public List<Tree> buildOneLevelMenuTree(List<Menu> menus) {
		if (null != menus && !menus.isEmpty()) {
			List<Tree> tree = new ArrayList<Tree>(1);

			for (Menu menu : menus) {
				Tree node = new Tree();
				node.setId(menu.getId());
				node.setName(menu.getMenuTitle());
				node.setExtraValue(menu.getLocation());
				node.setDisplayOrder(menu.getDisplayOrder());
				node.setSubCount(menu.getSubMenuCount());
				node.setExtendId(menu.getMenuType());
				tree.add(node);
			}
			return tree;
		} else {
			return null;
		}
	}

	public List<Tree> buildMenuTree(List<Menu> menus) {
		if (null != menus && !menus.isEmpty()) {
			List<Tree> tree = new ArrayList<Tree>(1);
			Map<String, Tree> tmpTree = new HashMap<String, Tree>(menus.size());

			for (Menu menu : menus) {
				Tree node = new Tree();
				node.setId(menu.getId());
				node.setName(menu.getMenuTitle());
				node.setExtraValue(menu.getLocation());
				node.setDisplayOrder(menu.getDisplayOrder());
				node.setSubCount(0);

				if (1 == menu.getMenuLevel()) {
					tree.add(node);
				}

				tmpTree.put(node.getId(), node);
			}

			buildSubMenuTree(menus, tmpTree);

			// 排序
			Collections.sort(tree);

			// 对树进行排序
			for (Tree t1 : tree) {
				if (t1.hasSubTree()) {
					Collections.sort(t1.getSubTree());
					for (Tree t2 : t1.getSubTree()) {
						if (t2.hasSubTree()) {
							Collections.sort(t2.getSubTree());
						}
					}

				}
			}

			return tree;
		} else {
			return null;
		}
	}

	private void buildSubMenuTree(List<Menu> menus, Map<String, Tree> tmpTree) {
		for (Menu menu : menus) {
			if (null != menu.getParentMenu()) {
				Tree parent = tmpTree.get(menu.getParentMenu().getId());
				Tree current = tmpTree.get(menu.getId());
				if (null != parent) {
					parent.addSubTree(current);
				}
			}
		}
	}

	public Menu getMenuById(String menuId) {
		return menuDao.load(menuId);
	}

	public Menu getMenuCascadeById(String menuId) {
		return menuDao.loadCascade(menuId);
	}

	public List<Menu> getMenuByParentId(String parentMenuId) {
		return menuDao.getSubMenuByParentId(parentMenuId);
	}

//	public void saveOrUpdateMenu(String menuId, String menuName,
//			String menuTitle, String location, String description,
//			Integer displayOrder, String target, String parentMenuId) {
//		if (null != menuId && !"".equals(menuId)) {
//			Menu menu = menuDao.load(menuId);
//			menu.setMenuName(menuName);
//			menu.setMenuTitle(menuTitle);
//			menu.setLocation(location);
//			menu.setDescription(description);
//			menu.setDisplayOrder(displayOrder);
//			menu.setTarget(target);
//
//			menuDao.saveOrUpdate(menu);
//		} else {
//			Menu menu = new Menu();
//
//			menu.setMenuName(menuName);
//			menu.setMenuTitle(menuTitle);
//			menu.setLocation(location);
//			menu.setDescription(description);
//			menu.setDisplayOrder(displayOrder);
//			menu.setTarget(target);
//			menu.setMenuType(Menu.MENU_TYPE_MENU);
//			if (null != parentMenuId && !"".equals(parentMenuId)) {
//				Menu parentMenu = menuDao.loadCascade(parentMenuId);
//				menu.setApplication(parentMenu.getApplication());
//				
//				menu.setParentMenu(parentMenu);
//				menu.setMenuLevel(parentMenu.getMenuLevel() + 1);
//			} else {
//				// root
//				menu.setMenuLevel(1);
//			}
//
//			menuDao.save(menu);
//
//		}
//	}

	public String saveOrUpdateMenu(final String menuId, final String menuName,
			final String menuTitle, final String menuTitleEn,
			final String location, final String description,
			final Integer displayOrder, final String target,
			final String parentMenuId, final String appId, final String menuType) {
		Menu oldmenu = null;
		if( !StringUtils.isEmpty(menuId))
		{
			oldmenu = menuDao.loadCascade(menuId);
		}
		
		boolean modifyLevel=false;
		int newLevel=0;
		int oldlevel=0;
		int leveldif=0;
		if(oldmenu!=null)
		{
		 Menu oldParentMenu = oldmenu.getParentMenu();
		 if(oldParentMenu==null)
		 {
			 oldlevel=0;
		 }else
		 {
			 oldlevel=oldParentMenu.getMenuLevel();
		 }
		
		 
		if( StringUtils.isEmpty(parentMenuId))
		{
			newLevel=0;
		}else
		{
			Menu newParentMenu = menuDao.load(parentMenuId);
			newLevel=newParentMenu.getMenuLevel();
		}
		leveldif=newLevel-oldlevel;
		 if(leveldif!=0)
		 {
			 modifyLevel=true;
		 }
		 
		 
			
		}
		
		
		String menu_id = menuDao.saveOrUpdateMenu(menuId, menuName, menuTitle, menuTitleEn,
				location, description, displayOrder, target, parentMenuId,
				appId, menuType);
		if(modifyLevel)
		{
			menuDao.updateLevels(menuId, leveldif);
		}
		return menu_id;
	}

	public void deleteMenu(String menuId) {
		Menu menu = menuDao.loadCascade(menuId);
		if ((null == menu.getIncludedRoles() || menu.getIncludedRoles().size()==0)
				&& menuDao.getSubMenuCountByParentId(menuId) <= 0) {
			menuDao.delete(menuId);
		} else {
			if(null != menu.getIncludedRoles() && menu.getIncludedRoles().size()>0){
				StringBuffer sb = new StringBuffer("该菜单还绑定以下角色：");
				for(Role role : menu.getIncludedRoles()){
					if(null != role){
						sb.append(role.getRoleCode()+",");
					}
				}
				sb.replace(sb.length()-1, sb.length(), "");
				throw new ServiceException(sb.toString());
			}else{
				throw new ServiceException("该菜单还包含子菜单！");
			}
		}
	}

	// add by huolang 2010-09-08
	public IApplicationDao getApplicationDao() {
		return applicationDao;
	}

	public void setApplicationDao(IApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	public IPage<Application> getAppListByPage(QueryPage page) {
		return applicationDao.findPageBy(page);
	}

	public Application getAppCascadeById(String appId) {
		Application app = applicationDao.loadCascade(appId);
		return app;
	}

	public Application getAppById(String appId) {
		return applicationDao.load(appId);
	}

	public void saveOrUpdateApp(Application app) {
		applicationDao.saveOrUpdate(app);
	}

	public void deleteApp(String id) {
		applicationDao.delete(id);
	}
	
	public Application findApplicationByName(String appName){
		return applicationDao.findByName(appName);
	}

//	public IPage<Resource> findResourceByPage(String resourceName,
//			String resourceType, String resourceValue, String moduleCode,
//			int pageSize, int pageIndex) {
//		return resourceDao.findResourceByPage(resourceName, resourceType,
//				resourceValue, moduleCode, pageSize, pageIndex);
//	}
//
//	public void deleteResource(String id) {
//		resourceDao.delete(id);
//
//	}
//
//	public Resource getResourceById(String id) {
//		return resourceDao.load(id);
//	}
//
//	public Resource getResourceCascadeById(String id) {
//		return resourceDao.loadCascade(id);
//	}
//
//	public void saveOrUpdateResource(Resource res) {
//		resourceDao.saveOrUpdate(res);
//	}
//
//	public void grantResource2Role(final Role role, final String resourceId) {
//		roleDao.grantResource2Role(role, resourceId);
//	}

	public IPage<Role> findRoleByPage(String roleName, String roleCode,
			String description, int pageSize, int pageIndex) {
		return roleDao.findRoleByPage(roleName, roleCode, description,
				pageSize, pageIndex);
	}

	public Role getRoleById(String id) {
		return roleDao.load(id);
	}

	public Role getRoleCascadeById(String id) {
		return roleDao.loadCascade(id);
	}

	public void saveOrUpdateRole(Role role) {
		if(null == role){
			return;
		}
		Role _role;
//		List<String> delScope = new ArrayList<String>(); // 删除的scope，用来判断取消角色范围配错的用户的角色
		if(null == role.getId()	|| "".equals(role.getId())){
			_role = new Role();
			_role.setRoleCode(role.getRoleCode().toUpperCase());
			_role.setScope(role.getScope());
			_role.setUserScope(role.getUserScope());
			_role.setManageScope(role.getManageScope());
//			_role.setIsAdmin(Role.ROLE_NOT_ADMIN);
//			String userId = getCurrentUser().getUserId();
//			List<Role> roleList = roleDao.getAdminRoleByUser(userId);
//			if (null != roleList && roleList.size() > 0) {
//				Role myrole = roleList.get(0);
//				if (null != myrole && null != myrole.getId()) {
//					_role.setCreateRoleId(myrole.getId().trim());
//					String whole = myrole.getWholeCreateRoleId();
//					// 判断是否是由sysadmin建立的
//					if (null == whole || "".equals(whole.trim())) {
//						whole = Role.WHOLE_ROLE_SEPARATOR;
//					}
//					whole = whole.trim() + myrole.getId().trim() + Role.WHOLE_ROLE_SEPARATOR;
//					_role.setWholeCreateRoleId(whole);
//				}
//			}
		}else{
			_role = roleDao.load(role.getId());
			String roleScope = role.getScope();
			String userScope = role.getUserScope();
			String manageScope = role.getManageScope();
			if (StringUtils.isEmpty(roleScope)) {
				_role.setScope(role.getScope());
			} else { // 原先角色有Scope
				int index = 0;
				roleScope = roleScope.substring(0, index);// .substring(index); 截获
				roleScope = roleScope.concat(role.getScope()); // 替换
				
				manageScope = manageScope.substring(0, index);// .substring(index); 截获
				manageScope = manageScope.concat(role.getManageScope()); // 替换
			}
			_role.setUserScope(userScope);
			_role.setScope(roleScope);
			_role.setManageScope(manageScope);
		}
		_role.setRoleName(role.getRoleName());
		_role.setAppId(role.getAppId());
		_role.setDescription(role.getDescription());
		roleDao.saveOrUpdate(_role);
		role.setId(_role.getId());
		
	}

	public void deleteUserAll(String id) {
		User user = userDao.loadCascade(id);
		if (null == user) {
			throw new ServiceException("无法找到用户");
		}
		if(null != user.getAssignedRoles() && user.getAssignedRoles().size() > 0) {
			user.getAssignedRoles().clear();
		}
		user.setStatus("02");
		userDao.saveOrUpdate(user);
	}
	
	public void deleteRole(String id) {
		Role role = roleDao.loadCascade(id);
		if (null == role) {
			throw new RuntimeException("无法找到角色信息");
		}
		if(null != role.getDeletable() && "false".equals(role.getDeletable().trim())){
			throw new RuntimeException("系统维护角色，无法删除");
		}
		if (null != role.getGrantedMenus() && role.getGrantedMenus().size() > 0) {
			role.getGrantedMenus().clear();
		}
		if (null != role.getGrantedGuiMenus() && role.getGrantedGuiMenus().size() > 0) {
			role.getGrantedGuiMenus().clear();
		}
		if (null != role.getGrantedOpers() && role.getGrantedOpers().size() > 0) {
			role.getGrantedOpers().clear();
		}
		if (null != role.getIncludedUsers()
				&& role.getIncludedUsers().size() > 0) {
//			role.getIncludedUsers().clear();
			Set<User> users = role.getIncludedUsers();
			for(User user:users){
				user.getAssignedRoles().remove(role);
				userDao.saveOrUpdate(user);
			}
		}
		roleDao.saveOrUpdate(role);
		roleDao.delete(id);
	}
	
	public void deleteRoles(String [] ids){
		if(null != ids && ids.length>0){
			for(int i=0;i<ids.length;i++){
				if(null != ids[i] && !"".equals(ids[i])){
					deleteRole(ids[i]);
				}
			}
		}
	}

//	public IPage<User> findUserByPage(String userName, String userCode,
//			String employeeName, String employeeSN, String mobile, String type,
//			String status, String orgId, int pageSize, int pageIndex) {
//		return userDao.findUserByPage(userName, userCode, employeeName,
//				employeeSN, mobile, type, status, orgId, pageSize, pageIndex);
//	}

	public User getUserById(String id) {
		return userDao.load(id);
	}

	public User getUserCascadeById(String id) {
		return userDao.loadCascade(id);
	}

	public void saveOrUpdateUser(User user) {
		userDao.saveOrUpdate(user);
//		userDao.insertUser(user,null);
	}
	
	public void saveOrUpdateUser(String userId, String userName, String userPwd, String status, String description){
		IUser self = getCurrentUser();
		User user;
		if (null != userId && !"".equals(userId.trim())) {
			user = userDao.load(userId);
			if (user == null) {
				throw new ServiceException("无法找到用户");
			}
		}else{
			user = new User();
		}
		
		if (null != self) {
			user.setLastUpdateId(self.getUserId());
			user.setLastUpdateTm(new Date());
			user.setLastUpdator(self.getDisplayName());
			user.setLastUpdateCode(self.getUserCode());
		}
//		if (null != userName && !"".equals(userName)) {
//			user.setUserName(userName);
//		}
		if (null != userPwd && !"".equals(userPwd)) {
			user.setPwdUpdateDate(new Date());
			user.setUserPwd(new Md5Encoder().encode(userPwd));
		}
//		if (null != status && !"".equals(status)) {
////			user.setStatus(status);
//			user.setUsed(status);
//		}
		if (null != description && !"".equals(description)) {
			user.setDescription(description);
		}
		userDao.saveOrUpdate(user);
	}

	public void deleteUser(String id) {
		User user = userDao.loadCascade(id);

		if (null == user) {
			throw new ServiceException("无法找到用户");
		}
		userDao.delete(id);
	}

//	public IPage<User> getUserListByPage(QueryPage page) {
//		return userDao.getUserListByPage(page);
//	}

	// ---- Role ----
	public void grantRolesToUser(String userId, String currentPageAllRoleIds,
			String currentPageSelectedRoleIds) {
		User user = userDao.loadCascade(userId);
		if (null == user) {
			return;
		}
		Set<Role> roles = user.getAssignedRoles();
		if (null == currentPageAllRoleIds || "".equals(currentPageAllRoleIds)) {
			return;
		}

		String allIds = currentPageAllRoleIds + ",";
		String selectIds = currentPageSelectedRoleIds + ",";
		String rmIds = "";

		// 先取消用户点击取消的权限
		if (null != roles && roles.size() > 0) {
			Iterator<Role> it = roles.iterator();
			while (it.hasNext()) {
				Role role = it.next();
				if (null == role) {
					continue;
				}
				String rId = role.getId();
				if (null == rId || "".equals(rId)) {
					continue;
				}

				String trId = rId.trim() + ",";

				if (allIds.indexOf(trId) < 0) { // 不在当前列表
					continue;
				} else if (selectIds.indexOf(trId) < 0) { // 在当前列表，但是被取消了
					rmIds += trId;
				} else { // 在当前列表，而且依然被选中
					selectIds = selectIds.replaceAll(trId, "");
				}
			}
		}
		if(!"".equals(rmIds)){ // 如果有需要移除的
			String [] rms = rmIds.split(",");
			for(int j=0;j<rms.length;j++){
				userDao.unassignRoleFromUser(user, rms[j]);
			}
		}

		if (!"".equals(selectIds)) { // 如果有新添加的
			String[] assignedRoles = selectIds.split(",");
			for (int i = 0; i < assignedRoles.length; i++) {
				userDao.assignRole2User(user, assignedRoles[i]);
			}
		}
		
	}

//	public void grantMenu2Role(final Role role, final String menuId) {
//		roleDao.grantMenu2Role(role, menuId);
//	}
	
	public void grantMenusToRole(String roleId, String grantedMenuIdString){
//		Role role = roleDao.loadCascade(roleId);
//		if (null != role) {
//            Set<String> changedMenuIds = new HashSet<String>();
//            for(Scanner tokenize = (new Scanner(grantedMenuIdString)).useDelimiter(","); tokenize.hasNext(); changedMenuIds.add(tokenize.next()));
//            roleDao.ungrantAllMenuFromRole(role);
//            String changedMenuId;
//            for(Iterator<String> i$ = changedMenuIds.iterator(); i$.hasNext(); roleDao.grantMenu2Role(role, changedMenuId))
//                changedMenuId = i$.next();
//
//        }
		
		if(StringUtils.isEmpty(roleId)){// || StringUtils.isEmpty(grantedMenuIdString)){
			return;
		}
		
		IUser user = getCurrentUser();
		if(null == user || null == user.getRoleCodes() || user.getRoleCodes().size()<=0){
			throw new ServiceException("无法找到当前用户权限信息");
		}
//		// 首先获得当前用户管理的菜单信息
//		List<Menu> menus = null;
//		menus = menuDao.getMenuByRoleCodes(user.getRoleCodes(), null, null, null);
//
//		String manMenuStr = "";
//		if (null != menus && menus.size() > 0) {
//			for (Menu m : menus) {
//				manMenuStr += m.getMenuType() + "-" + m.getId() + ",";
//			}
//		}
		
		// 其次获得该Role已有的管理菜单信息
		Role role = roleDao.loadCascade(roleId);
		if(null == role){
			throw new ServiceException("无法找到角色信息");
		}
//		List<String> roles = new ArrayList<String>();
//		roles.add(role.getRoleCode());
//		List<Menu> currentMenu = menuDao.getMenuByRoleCodes(roles, null, null, null);
		
		List<Menu> currentMenu = new ArrayList<Menu>();
		if(null != role.getGrantedMenus() && role.getGrantedMenus().size()>0){
			currentMenu.addAll(role.getGrantedMenus());
		}
		if(null != role.getGrantedGuiMenus() && role.getGrantedGuiMenus().size()>0){
			currentMenu.addAll(role.getGrantedGuiMenus());
		}
		if(null != role.getGrantedOpers() && role.getGrantedOpers().size()>0){
			currentMenu.addAll(role.getGrantedOpers());
		}
		
		String selectIds = grantedMenuIdString + ",";//修改后的所有menu的id拼成的字符串
		String rmIds = "";

		// 以下部分判断当前用户包含的信息中，需要移除那些信息，以及需要添加新的信息
		if (null != currentMenu && currentMenu.size() > 0) {
			Iterator<Menu> it = currentMenu.iterator();
			while (it.hasNext()) {
				Menu menu = it.next();
				if (null == menu) {
					continue;
				}
				
				String rId = menu.getMenuType()+"-"+menu.getId();//用户已经存储的menu信息
				String trId = rId.trim() + ",";

//				if (manMenuStr.indexOf(trId) < 0) { // 不在管理列表，则不予考虑
//					continue;
//				} else if (selectIds.indexOf(trId) < 0) { // 在管理列表，但是被取消了（判断依据是选择的ID中没有）
				if(selectIds.indexOf(trId) < 0) {
					rmIds += trId;
				} else { // 在管理列表，而且依然被选中
					selectIds = selectIds.replaceAll(trId, "");
				}
			}
		}
		
		if(!"".equals(rmIds)){ // 如果有需要移除的
			String [] rms = rmIds.split(",");
			for(int j=0;j<rms.length;j++){
				roleDao.unGrantMenu2Role(role, rms[j]);
			}
		}

		if(!"".equals(selectIds)){ // 如果有需要增加的
			String [] sids = selectIds.split(",");
			for(int j=0;j<sids.length;j++){
				roleDao.grantMenu2Role(role, sids[j]);
			}
		}
	}

	public IPage<Role> getRoleListByPage(QueryPage page) {
		return roleDao.getRoleListByPage(page);
	}

	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, int pageSize, int pageIndex) {
		List<String> creatorIds = new ArrayList<String>();
		String userId = getCurrentUser().getUserId();
		if (null != userId && !"".equals(userId)) {
			List<Role> roleList = roleDao.getAdminRoleByUser(userId);
			if (null != roleList && roleList.size() > 0) {
				for (Role role : roleList) {
					creatorIds.add(role.getId());
				}
			}
		}
		return roleDao.getRoleListByPage(roleName, roleCode, description,
				isAdmin, creatorIds, pageSize, pageIndex);
	}
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, int pageSize, int pageIndex, Map<String,String> sortMap) {
		List<String> creatorIds = new ArrayList<String>();
		String userId = getCurrentUser().getUserId();
		if (null != userId && !"".equals(userId)) {
			List<Role> roleList = roleDao.getAdminRoleByUser(userId);
			if (null != roleList && roleList.size() > 0) {
				for (Role role : roleList) {
					creatorIds.add(role.getId());
				}
			}
		}
		return roleDao.getRoleListByPage(roleName, roleCode, description,
				isAdmin, creatorIds, pageSize, pageIndex, sortMap);
	}

	public IPage<Menu> getOperListByPage(QueryPage page) {
		return menuDao.getOperListByPage(page);
	}

	public void deleteOperation(String operId) {
//		Menu menu = menuDao.load(operId);
		List<String> roleList = menuDao.getOperRelatedRoleIds(operId);
//		if (null == menu.getIncludedRoles()
//				|| menuDao.getSubMenuCountByParentId(operId) <= 0) {
		if(null == roleList || roleList.size()<=0){
			menuDao.delete(operId);
		} else {
			StringBuffer sb = new StringBuffer("操作ID["+operId+"]还绑定以下角色：");
			for(String roleCode : roleList){
				if(null != roleCode){
					sb.append(roleCode+",");
				}
			}
			sb.replace(sb.length()-1, sb.length(), "");
			throw new ServiceException(sb.toString());
		}
	}

	public Menu getOperationById(String operId) {
		return menuDao.load(operId);
		
	}

	public void saveOrUpdateOperation(Menu oper) {
		menuDao.saveOrUpdate(oper);
	}
	
	public IPage<User> getUsersByRoleByPage(String roleId, QueryPage page){
		return userDao.getUsersByRoleByPage(roleId, page);
	}
	
	public void grantOpersToRole(String roleId, String currentPageAllOperIds,
			String currentPageSelectedOperIds) {
		Role role = roleDao.loadCascade(roleId);
		if (null != role) {
			String tmpOperId = null;
			for (Scanner tokenize = (new Scanner(currentPageAllOperIds))
					.useDelimiter(","); tokenize.hasNext(); roleDao
					.ungrantOperFromRole(role, tmpOperId)) {
				tmpOperId = tokenize.next().trim();
			}

			if (null != currentPageSelectedOperIds
					&& currentPageSelectedOperIds.trim().length() > 0) {
				tmpOperId = null;
				for (Scanner tokenize = (new Scanner(currentPageSelectedOperIds))
						.useDelimiter(","); tokenize.hasNext(); roleDao
						.grantOper2Role(role, tmpOperId)) {
					tmpOperId = tokenize.next().trim();
				}

			}
		}
	}
	
	private void buildSubMenu(List<Menu> menus, Map<String, SystemMenu> tmpMenus) {
		for (Menu menu : menus) {
			if (null != menu.getParentMenu()) {
				SystemMenu parent = tmpMenus.get(menu.getParentMenu().getId());
				SystemMenu current = tmpMenus.get(menu.getId());

				parent.addSubMenu(current);
			}
		}
	}
	
//	public IPage<User> getUserListByPage(String name, String code,
//			String status, String type, String displayName, String description,
//			String orgId, String orgCode, int pageSize, int pageIndex) {
//		return userDao.getUserListByPage(name, code, status, type, displayName,
//				description, orgId, orgCode, pageSize, pageIndex);
//	}
	
	/**
	 * 增加对sort的支持
	 */
	public IPage<UserDto> getUserListByPage(String name, String code,
			String status, String type, String displayName, String description,
			String roleId, int pageSize, int pageIndex,
			Map<String, String> sortMap) {
		return userDao.getUserListByPage(name, code, status, type, displayName,
				description, roleId, pageSize,
				pageIndex, sortMap);
	}
	
	/**
	 * 根据传进来的旧密码，修改密码为新密码
	 * @param oriPwd
	 * @param newPwd
	 * @param isEncrypted 客户端可以直接传加密过密码的进来
	 * @return
	 */
	public boolean resetUserPwd(String oriPwd, String newPwd, boolean isEncrypted){
		// 判断新的密码是否符合要求
		if (null == newPwd
				|| "".equals(newPwd.trim())
				|| (!isEncrypted && newPwd.trim().length() < PermissionConstant.MIN_PWD_LENGTH)) {
			logger.error("传入的新密码非法");
			throw new IllPasswordException();
		}
		
		IUser currentUser = getCurrentUser();
		if(null == currentUser || null == currentUser.getUserId()){
			throw new InvalidUserException();
		}
		
		User user = userDao.load(currentUser.getUserId());
		
		if(null == user){
			throw new NotExistUserException();
		}
		
		user.setLastUpdator(currentUser.getDisplayName());
		user.setLastUpdateCode(currentUser.getUserCode());
		user.setLastUpdateTm(new Date());
		
		String oriPwdEncode = isEncrypted?oriPwd:encrypt(oriPwd);
		if(!oriPwdEncode.equals(user.getUserPwd())){
			throw new ErrorPasswordException();
		//	return false;
		}else{
			String newPwdEncode = isEncrypted?newPwd:encrypt(newPwd);
			user.setUserPwd(newPwdEncode);
			user.setPwdUpdateDate(new Date());
			userDao.saveOrUpdate(user);
			
//			
//			try {
//				// 更改缓存数据
//				String userCache = com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE;
//				CacheManager.getInstance().refresh(userCache, user.getId());
//			} catch (Exception e) {
//				logger.error("刷新缓存失败 : \n" + e.getMessage());
//			}
			return true;
		}
	}
	
	/**
	 * 根据传入的userId，修改用户的密码
	 * @param userId
	 * @param oriPwd
	 * @param newPwd
	 * @param isEncrypted
	 * @return
	 */
	public boolean resetUserPwd(String userId, String oriPwd, String newPwd, boolean isEncrypted){
		if(StringUtils.isEmpty(userId)){
			logger.error("传入的用户ID为空");
			throw new NotExistUserException();
		}
		
		// 判断新的密码是否符合要求
		if (null == newPwd
				|| "".equals(newPwd.trim())
				|| (!isEncrypted && newPwd.trim().length() < PermissionConstant.MIN_PWD_LENGTH)) {
			logger.error("传入的新密码非法");
			throw new IllPasswordException();
		}
		
		User user = userDao.load(userId);
		
		if (null == user) {
			throw new NotExistUserException();
		}
		try {
			IUser currentUser = getCurrentUser();
			if (null != currentUser) {
				user.setLastUpdator(currentUser.getDisplayName());
				user.setLastUpdateCode(currentUser.getUserCode());
				user.setLastUpdateTm(new Date());
			}
		} catch (Exception e) {
			logger.error("can not get user cache info \n" + e.getMessage());
		}
		
		String oriPwdEncode = isEncrypted?oriPwd:encrypt(oriPwd);
		if(!oriPwdEncode.equals(user.getUserPwd())){
			throw new ErrorPasswordException();
		//	return false;
		}else{
			String newPwdEncode = isEncrypted?newPwd:encrypt(newPwd);
			user.setUserPwd(newPwdEncode);
			user.setPwdUpdateDate(new Date());
			userDao.saveOrUpdate(user);
			
			return true;
		}
	}
	
	public User getUserByCode(String code){
		return userDao.getUserByUserCode(code);
	}
	
	public Menu loadMenuByTypeAndName(final String menuType,
			final String menuName) {
		return menuDao.loadByTypeAndName(menuType, menuName);
	}
	
	// add for webservice check
	public IUser webServiceLogin(String userName, String password){
		
		// 用户名不能为空并且必需大于3个字符
		if (null == userName || userName.length() < 3)
			throw new IllegalUserNameException();
		
		// 密码不能为空并且必需大于4个字符
		if (null == password || password.length() < PermissionConstant.MIN_PWD_LENGTH)
			throw new IllPasswordException();
		
		User user = this.getUserByUserName(userName);
		// 用户不存在
		if (null == user)
			throw new NotExistUserException();

		// 用户处于无效状态不能登录
		if (User.USER_STATUS_INVALID.equals(user.getStatus()))
			throw new InvalidUserException();
		// 用户是否启用
//		if (!PermissionConstant.USER_USED.equals(user.getUsed())){ 
//			throw new InvalidUserException();
//		}
		// 用户组织是否有效
//		boolean isValidOrg = userDao.checkUserOrgStatus(user.getId());
//		if (isValidOrg == false) {
//			throw new InvalidUserException();
//		}

		// 密码错误
		String pwdstr = encrypt(password);
		if(logger.isInfoEnabled())logger.info(pwdstr);
		if (!pwdstr.equalsIgnoreCase(user.getUserPwd()))
			throw new ErrorPasswordException();
		
		// 组合IUser信息
		UserComplex suser = new UserComplex();
		//设置用户信息
		suser.setUserId(user.getId());
		suser.setUserName(user.getUserName());
		suser.setUserCode(user.getUserCode());
		suser.setDisplayName(user.getDisplayName());
		suser.setUserType(user.getUserType());
		
		return suser;
	}
	
	public List<User> getEmployeeUserByRole(List<String> roleCodes){
		List<User> userList = userDao.findUserByRole(roleCodes, User.USER_TYPE_EMPLOYEE, null);
		if(null == userList || userList.size()<=0){
			return null;
		}
		List<User> list = new ArrayList<User>();
		for(User u:userList){
			User user = new User();
			user.setId(u.getId());
			user.setDisplayName(u.getDisplayName());
			user.setUserCode(u.getUserCode());
			list.add(user);
		}
		return list;
	}
	
	public List<User> getEmployeeUserByRole(List<String> roleCodes, String parm){
		List<User> userList = userDao.findUserByRole(roleCodes, User.USER_TYPE_EMPLOYEE, parm);
		if(null == userList || userList.size()<=0){
			return null;
		}
		List<User> list = new ArrayList<User>();
		for(User u:userList){
			User user = new User();
			user.setId(u.getId());
			user.setDisplayName(u.getDisplayName());
			user.setUserCode(u.getUserCode());
			list.add(user);
		}
		return list;
	}
	
	public List<User> getEmployeeUserByPermission(List<String> permissionNames, String parm) {
		List<User> userList = userDao.findUserByPermission(permissionNames,
				User.USER_TYPE_EMPLOYEE, parm);
		if (null == userList || userList.size() <= 0) {
			return null;
		}
		List<User> list = new ArrayList<User>();
		for (User u : userList) {
			User user = new User();
			user.setId(u.getId());
			user.setDisplayName(u.getDisplayName());
			user.setUserCode(u.getUserCode());
			list.add(user);
		}
		return list;
	}
	
	public Role getRoleByCode(String code){
		return roleDao.getRoleByCode(code);
	}
	
	public Role getRoleByName(String name){
		return roleDao.getRoleByName(name);
	}
	
	public List<Menu> searchMenuByTitleWithParent(String str){
		return menuDao.searchMenuByTitleWithParent(str);
	}
	
	public List<User> checkUniqueUserCode(String code){
		return userDao.checkUniqueUserCode(code);
	}
	
	public List<User> checkUniqueUserName(String name){
		return userDao.checkUniqueUserName(name);
	}
	
	public IPage<RoleDto> getRolePageBySql(String roleName, String roleCode,
			String description, List<String> scopes,List<String> manageScopes, List<String> appIds,
			List<String> groupIds, List<String> rejectRoleIds, String userId,
			Boolean userHasRole, int pageSize, int pageIndex,
			Map<String, String> sortMap) {
		    String userType=null;
		  if(StringUtils.isNotEmpty(userId))
		  {
			 User user = userDao.load(userId);
			 userType=user.getUserType();			  
		  }
	      
		return roleDao.getRolePageBySql(roleName, roleCode, description,
				scopes,manageScopes, appIds, groupIds, rejectRoleIds, userId, userHasRole,
				pageSize, pageIndex, sortMap,userType);
	}
	
	public List<Application> findAllApplication(){
		return applicationDao.findAll();
	}
	
	public List<Menu> getMenuByRoleCodes(List<String> roleCodes, Integer level,
			String parentMenuId, List<String> menuTypes) {
		return menuDao.getMenuByRoleCodes(roleCodes, level, parentMenuId,
				menuTypes);
	}
	
	public void grantManageMenuToRole(String roleId, String menuIdStr){
		if(StringUtils.isEmpty(roleId) || StringUtils.isEmpty(menuIdStr)){
			return;
		}
		
		IUser user = getCurrentUser();
		if(null == user || null == user.getRoleCodes() || user.getRoleCodes().size()<=0){
			throw new ServiceException("无法找到当前用户权限信息");
		}
		// 首先获得当前用户管理的菜单信息
		List<Menu> menus = null;
		menus = menuDao.getMenuByRoleCodes(user.getRoleCodes(), null, null, null);
		
		String manMenuStr = "";
		if(null != menus && menus.size()>0){
			for(Menu m:menus){
				manMenuStr += m.getId()+",";
			}
		}
		
		// 其次获得该Role已有的管理菜单信息
		Role role = roleDao.load(roleId);
		if(null == role){
			throw new ServiceException("无法找到角色信息");
		}
		List<String> roles = new ArrayList<String>();
		roles.add(role.getRoleCode());
		List<Menu> currentMenu = menuDao.getMenuByRoleCodes(roles, null, null, null);
		
		
		String selectIds = menuIdStr + ",";
		String rmIds = "";

		// 以下部分判断当前用户包含的信息中，需要移除那些信息，以及需要添加新的信息
		if (null != currentMenu && currentMenu.size() > 0) {
			Iterator<Menu> it = currentMenu.iterator();
			while (it.hasNext()) {
				Menu menu = it.next();
				if (null == menu) {
					continue;
				}
				
				String rId = menu.getId();
				String trId = rId.trim() + ",";

				if (manMenuStr.indexOf(trId) < 0) { // 不在管理列表，则不予考虑
					continue;
				} else if (selectIds.indexOf(trId) < 0) { // 在管理列表，但是被取消了（判断依据是选择的ID中没有）
					rmIds += trId;
				} else { // 在管理列表，而且依然被选中
					selectIds = selectIds.replaceAll(trId, "");
				}
			}
		}
		
		if(!"".equals(rmIds)){ // 如果有需要移除的
			String [] rms = rmIds.split(",");
			for(int j=0;j<rms.length;j++){
				roleDao.unGrantManageMenuToRole(roleId, rms[j]);
			}
		}

		if(!"".equals(selectIds)){ // 如果有需要移除的
			String [] sids = selectIds.split(",");
			for(int j=0;j<sids.length;j++){
				roleDao.grantManageMenuToRole(roleId, sids[j]);
			}
		}
//		String currentMenuStr = "";
//		if(null != currentMenu && currentMenu.size()>0){
//			for(Menu m:currentMenu){
//				currentMenuStr += m.getId()+",";
//			}
//		}
//		String [] res = this.calChangeList(currentIds, selects, controls)
//		String[] menuIds = menuIdStr.split(",");
//		for(int i=0;i<menuIds.length;i++){
//			String menuId = menuIds[i];
//			int index = menuId.indexOf("-");
//			if(index >0){
//				menuId = menuId.substring(index+1);
//			}
//			roleDao.grantManageMenuToRole(roleId, menuId);
//		}
	}
	
	/**
	 * 修改密码策略1：
	 * 利用明文初始化一批密码（长度不超过20)，在程序中运行以下代码，将明文转化为对应的密文
	 * 
	 */
	public void initUserPwd(int pageSize, int pageIndex) {

		List<User> userList = userDao.findAll(pageSize, pageIndex);
		String tmp = "";
		try {
			if (null != userList && userList.size() > 0) {

				for (User user : userList) {
					tmp = user.getUserCode();
					String pwd = user.getUserPwd();
					if (null == pwd || (null != pwd && pwd.length() > 20)) { // 密码为空，或者密码长度大于20，都不再进行处理
						continue;
					} else {
						user.setUserPwd(new Md5Encoder().encode(pwd));
						userDao.saveOrUpdate(user);
						logger.debug("update user:"+user.getUserCode());
					}

				}
				logger.debug("finished update pwd");
			}
		} catch (Exception e) {
			logger.warn("err when process user["+tmp+"] : "+e.getMessage());
		}
	}
	
	
	private static final long DAY_MILLISECONDS = 1000*60*60*24;
	/**
	 * 检查用户输入的密码是否正确，以及距离过期还有多少天
	 * @param oriPwd 用户输入的密码，如果为空，则查询用户当前密码所剩天数
	 * @param userId 用户ID，如果为空，则取当前用户的ID
	 * 
	 * @return <0 输入密码不匹配用户的密码
	 * @return >0 输入密码匹配用户密码，且未过期；返回为密码剩余天数（如果输入密码为空，则返回当前密码剩余天数）
	 * @return =0 输入的密码匹配用户密码，但是已经过期（包括初始化密码）
	 */
	public Integer checkUserPwd(String userId, String oriPwd) {
		
		String uid = userId;
		if (StringUtils.isEmpty(uid)) {
			IUser currentUser = getCurrentUser();
			if (null == currentUser || null == currentUser.getUserId()) {
				throw new InvalidUserException();
			}
			uid = currentUser.getUserId();
		}
		User user = userDao.load(uid);

		if (null == user) {
			throw new NotExistUserException();
		}
		
		
		String oriPwdEncode = user.getUserPwd();
		if(StringUtils.isNotEmpty(oriPwd)){
			oriPwd = oriPwd.trim();
			oriPwdEncode = encrypt(oriPwd);
		}
		
		if (!oriPwdEncode.equals(user.getUserPwd())) {
			return -1; // not match
		} else { // match
//			Date pwdDate = user.getPwdUpdateDate();
//			Date today = new Date();
//			if (null == pwdDate
//					|| PermissionConstant.DEFAULT_PASSWORD_ENCRYPTED
//							.equals(user.getUserPwd())) { // 日期为空(初次修改密码），或者与原始密码相同
//				return 0;
//			} else {
//				int dif = (int) ((pwdDate.getTime() - today.getTime()
//						+ DAY_MILLISECONDS - 1) / DAY_MILLISECONDS) + PermissionConstant.PWD_ACTIVE_DAY;
//				if(dif<0){
//					return 0;
//				}
//				return dif;
//			}
			return checkUserPwdActiveDays(user);
		}
	}
	
	public Integer checkUserPwdActiveDays(User user){
		if(null == user){
			return null;
		}
		
		Date pwdDate = user.getPwdUpdateDate();
		Date today = new Date();
		if (null == pwdDate
				|| PermissionConstant.DEFAULT_PASSWORD_ENCRYPTED
						.equals(user.getUserPwd())) { // 日期为空(初次修改密码），或者与原始密码相同
			return 0;
		} else {
			int dif = (int) ((pwdDate.getTime() - today.getTime()
					+ DAY_MILLISECONDS - 1) / DAY_MILLISECONDS) + PermissionConstant.PWD_ACTIVE_DAY;
			if(dif<0){
				return 0;
			}
			return dif;
		}
	}
	
	// 为确保用户变更组织或者特定情况下，需要注销该用户所有的权限
	public void unassigedAllRoleFromUser(String userId){
		userDao.unassigedAllRoleFromUser(userId);
	}

	@SuppressWarnings("unchecked")
	public Boolean hasMacPermission(String menuId, IUser user) {
		if (menuId == null)
			return true;
		
		try {
			String mac = UserContext.getUserContext().getCookieValue("mac");
			
			JSONObject json = JSONObject.fromObject(mac);
			MacDto macDto = (MacDto)JSONObject.toBean(json, MacDto.class);
			
			logger.info("MAC Address is: " + ToStringBuilder.reflectionToString(macDto));
			
			if (macDto == null || macDto.getMacs() == null || macDto.getMacs().size() == 0)
				return false;
			
			ICache cache = CacheManager.getInstance().getCache(Constants.MAC_CACHE);
			Map<Mac, List<String>> map = (Map<Mac, List<String>>) cache.getData(menuId);
			
			if (map == null || map.size() == 0)
				return false;
			
			for (String m : macDto.getMacs()) {
				if ("00-00-00-00-00-01".equals(m))
					continue;
				
				if (map.containsKey(new Mac(m, macDto.getCpuId())))
					return true;
				
//				List<String> userCodes = map.get(new Mac(m, macDto.getCpuId()));
//				if (userCodes != null)
//					return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return false;
	}
	
	/**
	 * 给用户赋角色
	 * 
	 * @param userName
	 *            用户名
	 * @param roleCodes
	 *            角色代码list
	 */
	public void assignUserRoles(String userName, List<String> roleCodes) {
		logger.info("enter assignUserRoles");
		User user = userDao.getUserByUserName(userName);
		Map<String, String> hasRoles = new HashMap<String, String>();
		if (null == user) {
			logger.info("user is null");
			return;
		}
		if (roleCodes == null || roleCodes.size() < 1) {
			logger.info("roleCodes is null");
			return;
		}
		Set<Role> roles = user.getAssignedRoles();

		if (null != roles && roles.size() > 0) {
			Iterator<Role> it = roles.iterator();
			while (it.hasNext()) {
				Role role = it.next();
				if (null == role) {
					continue;
				}
				String rCode = role.getRoleCode();
				if (null == rCode || "".equals(rCode)) {
					continue;
				}
				hasRoles.put(rCode, rCode);
			}
		}

		for (int j = 0; j < roleCodes.size(); j++) {
			String roleCode = roleCodes.get(j);
			if (hasRoles.get(roleCode) == null) {
				// 需要添加该角色
				Role role = roleDao.getRoleByCode(roleCode);
				if (role != null && role.getId() != null) {
					userDao.assignRole2User(user, role.getId());
				}

			}

		}
		
		try {
			// 刷新Cache
			String userCache = com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE;
			CacheManager.getInstance().refresh(userCache, user.getId());
		} catch (Exception e) {
			logger.error("刷新缓存失败 : " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 取消用户角色
	 * 
	 * @param userName
	 *            用户名
	 * @param roleCodes
	 *            角色代码list
	 */
	public void unAssignUserRoles(String userName, List<String> roleCodes) {

		logger.info("enter assignUserRoles");
		User user = userDao.getUserByUserName(userName);
		Map<String, String> hasRoles = new HashMap<String, String>();
		if (null == user) {
			logger.info("user is null");
			return;
		}
		if (roleCodes == null || roleCodes.size() < 1) {
			logger.info("roleCodes is null");
			return;
		}
		Set<Role> roles = user.getAssignedRoles();

		if (null != roles && roles.size() > 0) {
			Iterator<Role> it = roles.iterator();
			while (it.hasNext()) {
				Role role = it.next();
				if (null == role) {
					continue;
				}
				String rCode = role.getRoleCode();
				if (null == rCode || "".equals(rCode)) {
					continue;
				}
				hasRoles.put(rCode, rCode);
			}
		}

		for (int j = 0; j < roleCodes.size(); j++) {
			String roleCode = roleCodes.get(j);
			if (hasRoles.get(roleCode) != null) {
				// 需要去掉该角色
				Role role = roleDao.getRoleByCode(roleCode);
				if (role != null && role.getId() != null) {
					userDao.unassignRoleFromUser(user, role.getId());
				}

			}

		}

		try {
			// 刷新Cache
			String userCache = com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE;
			CacheManager.getInstance().refresh(userCache, user.getId());
		} catch (Exception e) {
			logger.error("刷新缓存失败 : " + e.getMessage());
			e.printStackTrace();
		}

	}
	/**
	 * @param userName
	 * @param orgCode
	 * @param userType 参考 User 类中定义， 员工：employee；客户：customer；航空货代：fltagent；国际件货代：intagent
	 * @param defaultRoleCodes
	 */
	public void createUser(String userId,String userName,String userType,List<String> defaultRoleCodes)
	{
		
		IUser self = null;//this.getCurrentUser();		
		if(StringUtils.isEmpty(userType)||StringUtils.isEmpty(userName))
		{
			throw new BizException("错误的用户类型");
		}
		if(StringUtils.isEmpty(userId) )
		{
			userId=userName;
			User userold = userDao.getUserByUserName(userName);
			if(userold!=null)
			{
				throw new BizException("该用户的登录名已经重复"); 
			}
		}
		 
		User userfind=userDao.load(userId);
		if(userfind!=null)
		{
			throw new BizException("该用户的user_id名已经重复"); 
		}
		
		User user = new User();
		if (null != self) {
			// 设置新建的记录信息
			user.setCreateId(self.getUserId()); 
			user.setCreator(self.getDisplayName());
			user.setCreateCode(self.getUserCode());
			user.setLastUpdateId(self.getUserId()); 
			user.setLastUpdator(self.getDisplayName());
			user.setLastUpdateCode(self.getUserCode());
		}
		user.setCreateTm(new Date());
		user.setLastUpdateTm(new Date());
		user.setUserName(userName);  
		user.setStatus(User.USER_STATUS_VALID); 
		user.setUserType(userType);
		String pwdEncoded = new Md5Encoder().encode(PermissionConstant.DEFAULT_PASSWORD);
		user.setUserPwd(pwdEncoded); // 设置为默认值
		user.setDefaultLocate(Locale.SIMPLIFIED_CHINESE.toString()); 
		user.setId(userId);
		user.setVersionNo(System.currentTimeMillis());
		userDao.insertUser(user,defaultRoleCodes);	 
		assignUserRoles(userName,defaultRoleCodes);
		 
	}
	public void writeOffUser(String userName)
	{
		User user = userDao.getUserByUserName(userName);
		if(user==null)
		{
			throw new BizException("无法获得用户信息");
		}
		user.setStatus(User.USER_STATUS_INVALID);
		
		userDao.save(user);
	}

}
