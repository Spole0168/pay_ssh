package com.ibs.common.module.frameworkimpl.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.common.module.frameworkimpl.security.dao.IApplicationDao;
import com.ibs.common.module.frameworkimpl.security.dao.IMenuDao;
import com.ibs.common.module.frameworkimpl.security.dao.IRoleDao;
import com.ibs.common.module.frameworkimpl.security.dao.IUserDao;
import com.ibs.common.module.frameworkimpl.security.dao.impl.ApplicationDao;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.dto.UserComplex;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.portal.framework.server.cache.lru.LRUBaseCacheDataProvider;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BaseRuntimeException;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.SystemMenu;
import com.ibs.portal.framework.util.StringUtils;

/**
 * 用户缓存供给�? 当用户尝试从缓存中获取用户时，如果需要获取的用户不存在于缓存中，则使用供给器从数据库总加�?
 *
 * @author Qin Zhengming
 *
 */
public class UserCacheProvider extends LRUBaseCacheDataProvider {

	//log
	protected final Log logger = LogFactory.getLog(UserCacheProvider.class);	

	private IUserDao userDao;
	private IRoleDao roleDao;
	private IMenuDao menuDao;
	private IPermissionService permissionService;
	private IApplicationDao applicationDao;
	

	private Map<String, String> appMap = null;
	
	public IApplicationDao getApplicationDao() {
		return applicationDao;
	}

	public void setApplicationDao(IApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	//当前系统名称
	public String applicationName;



	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}


	/**
	 * @param userCacheDao
	 *            the userCacheDao to set
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public Object getData(Object key) {
		if (key == null) {
			logger
					.debug("Can not get User from UserCache without assign a userId");
			return null;
		}

		logger.info("从数据库加载获取用户(" + key + ")缓存数据-begin");
		try {
			String userId = (String) key;
			User user = userDao.loadCascade(userId);
			if (null == user) {
				throw new BaseRuntimeException(
						"Can not find user["
								+ key.toString()
								+ "], if this record exists in database, please check if there an employee exists with its emp_id.");
			}
			
			IUser suer = toUserComplex(user);
//			List<SystemMenu> smenus = permissionService.loadUserMenu(userId);
//			suer.setMenus(smenus);
			
			return suer;
		} catch (Exception e) {
			logger.error("Can not get User from UserCache. user_id["
					+ (String) key + "]", e);
			throw new BizException(e.getMessage());
//			return null;
		}
		finally {
			logger.info("从数据库加载获取用户缓存数据-end");
		}
	}
	

	/**
	 * 将user对象变为UserComplex对象
	 * @param user
	 * @return
	 */
	public IUser toUserComplex(User user) {
		UserComplex suser = new UserComplex();
//		// 计算用户密码过期天数
//		Integer res = permissionService.checkUserPwdActiveDays(user);
//		suser.setPasswordActiveDay(res);
		
//		String orgCode = user.getOrgCode();
//		if(orgCode == null) {
//			logger.warn("数据不完�?,未设定[用户-组织]的对应关�?");
//			throw new InvalidUserException();
//		}
//		//重新加载org
//		Org org = orgDao.getOrgByCode(orgCode);
//		if(null == org){
//			org = orgDao.load(orgCode);
//		}
		
//		Set<Org> orgs = user.getOrgs();
//		if (null == orgs || !(orgs.size() > 0)) {
//			logger.warn("数据不完�?,未设定[用户-组织]的对应关�?");
//			throw new InvalidUserException();
//		}
//		Iterator<Org> iter0 = orgs.iterator();
//		List<String> userOrgCodes = new ArrayList<String>();
//		while (iter0.hasNext()) {
//			Org temp = iter0.next();
//			if (null != temp) {
//				userOrgCodes.add(temp.getOrgCode());
//			}
//		}
//		suser.setOrgCodes(userOrgCodes);
		
//		List<UserOrg> orgList = userOrgDao.findOrgsByUserId(user.getId());
//		
//		if(null == orgList || !(orgList.size()>0)){
//			logger.warn("数据不完�?,未设定[用户-组织]的对应关�?");
//			throw new InvalidUserException();
//		}
		
//		Org org = null;
//		for (UserOrg userOrg : orgList) {
//			if ("y".compareToIgnoreCase(userOrg.getIsMaster()) == 0) {
//				org = orgDao.loadCascade(userOrg.getOrgId());
//				break;
//			}
//		}
//		if (org == null) {
//			org = orgDao.loadCascade(orgList.get(0).getOrgId());
//		}

		//设置用户信息
		suser.setUserId(user.getId());
		suser.setUserName(user.getUserName());
		suser.setUserCode(user.getUserCode());
		suser.setDisplayName(user.getDisplayName());
		suser.setUserType(user.getUserType());

		//设置系统角色
//		List<Role> roleList = roleDao.getAllRoleByApplicationNameAndUserId(applicationName,user.getId());
//		if (null != roleList && !roleList.isEmpty()) {
//			int n = roleList.size();
//			String[] roleIds = new String[n];
//			int i = 0;
//			for (Role role : roleList) {
//				roleIds[i] = role.getId();
//				i++;
//			}
		
//		suser.setRoleCodes(roleCodes);
//		suser.setOperCodes(operCodes);
//		suser.setUrlMaps(urlMaps);

		// 设置角色信息
		Set<Role> roleSet = user.getAssignedRoles();
		List<String> roleIds = new ArrayList<String>();
		List<String> roleCodes = new ArrayList<String>();
		for(Role role : roleSet) {
			roleIds.add(role.getId());
			roleCodes.add(role.getRoleCode());
		}
		//设置当前角色，这个结果不稳定的，并且�?个人可以有多个角色，�?以尽量不要使�?
		if (roleSet != null && roleSet.size() != 0) {
			Role role = roleSet.iterator().next();
			suser.setCurrentRoleId(role.getId());
			suser.setCurrentRoleName(role.getRoleName());
		}
		
		if (roleIds.isEmpty()) {
			logger.warn("数据不完�?,未设定[用户-角色]的对应关�?");
			suser.setMenus(null);
			suser.setGuiMenus(null);
			
			suser.setRoleIds(null);
			suser.setRoleCodes(null);
			suser.setOperCodes(null);
			suser.setUrlMaps(null);
			
			return suser;
//			throw new NoRoleAssignedException();
		}
		
		// 设置菜单和操�?
		// 1) 获取用户�?有菜单和操作
		List<SystemMenu> allwebmenus = menuDao.findMenuByRoleIds(roleIds);
		List<SystemMenu> allclientmenus = menuDao.findGuiMenuByRoleIds(roleIds);
//		List<SystemMenu> allguimenus = menuDao.findGuiMenuByRoleIds(roleIds);
		List<SystemMenu> allguimenus = new ArrayList<SystemMenu>();
		List<SystemMenu> allpdamenus = new ArrayList<SystemMenu>();
		List<SystemMenu> alloperations = menuDao.findOperationByRoleIds(roleIds);
		
		// 2) web菜单拼接context path前缀
		if(null == appMap || appMap.size()<=0){
			if(null == ApplicationDao.APP_MAP || ApplicationDao.APP_MAP.size()<=0){
				applicationDao.getAppMap();
			}
			appMap = ApplicationDao.APP_MAP;
		}
		for (SystemMenu menu : allwebmenus) {
			if (StringUtils.isNotEmpty(menu.getAppId())) {
				String prefix = appMap.get(menu.getAppId());
				String location = menu.getLocation();
				if (StringUtils.isNotEmpty(prefix) && StringUtils.isNotEmpty(location)) {
					menu.setLocation("/".concat(prefix).concat(menu.getLocation()));
				}
			}
		}
		
		// 2) client菜单进行区分
		for(SystemMenu menu:allclientmenus){
			if(null != menu && StringUtils.isNotEmpty(menu.getType())){
				if(Menu.MENU_TYPE_GUI_MENU.equals(menu.getType())){
					allguimenus.add(menu);
				}else if(Menu.MENU_TYPE_PDA_MENU.equals(menu.getType())){
					allpdamenus.add(menu);
				}
			}
		}
		
		// 3) 构�?�菜单树
//		List<SystemMenu> treewebmenus = generateMenus(allwebmenus, Menu.MENU_TYPE_MENU);
//		List<SystemMenu> treeguimenus = generateMenus(allguimenus, Menu.MENU_TYPE_GUI_MENU);
		
		List<SystemMenu> treewebmenus = buildMenusTree(allwebmenus, Menu.MENU_TYPE_MENU);
		List<SystemMenu> treeguimenus = buildMenusTree(allguimenus, Menu.MENU_TYPE_GUI_MENU);
		List<SystemMenu> treepdamenus = buildMenusTree(allpdamenus, Menu.MENU_TYPE_PDA_MENU);
		
//		suser.setMenus(buildMenusTree(menus, "menu"));
//		suser.setGuiMenus(buildMenusTree(guiMenus, "guimenu"));
		
		// 4) 操作处理
		Set<String> operCodes = new HashSet<String>();
		HashMap<String, String> urlMaps = new HashMap<String, String>();
		for (SystemMenu oper : alloperations) {
			operCodes.add(oper.getName());
			if (Menu.MENU_TYPE_URL.equals(oper.getType()))
				urlMaps.put(oper.getName(), oper.getLocation());
		}
		
		// 5) 设置user
		suser.setMenus(treewebmenus);
		suser.setGuiMenus(treeguimenus);
		suser.setPdaMenus(treepdamenus);
		
		suser.setRoleIds((String[]) roleIds.toArray(new String[0]));
		suser.setRoleCodes(roleCodes);
		suser.setOperCodes(operCodes);
		suser.setUrlMaps(urlMaps);
		
		/*
		if(null != roleSet && !roleSet.isEmpty()){
			Iterator<Role> iter = roleSet.iterator();
			String [] roleIds = new String[roleSet.size()];
			List<String> roleCodes = new ArrayList<String>();
			Set<String> operCodes = new HashSet<String>();
			HashMap<String, String> urlMaps = new HashMap<String, String>();
			List<OptionObjectPair> rolePairs = new ArrayList<OptionObjectPair>();
			int i = 0;
			while(iter.hasNext()){ // 遍历当前用户的Role
				Role role = iter.next();
				
				if(null != role){
					roleIds[i++] = role.getId();
					roleCodes.add(role.getRoleCode());
					OptionObjectPair pair = new OptionObjectPair();
					pair.setKey(role.getId());
					pair.setValue(role.getDescription());
					rolePairs.add(pair);
					
					Role tmpRole = roleDao.loadCascade(role.getId()); // 重新取得带级联的Role
					Set<Menu> gms = tmpRole.getGrantedGuiMenus();
					for (Menu tm : gms) { // 合并gui的菜�?
						if (!guiMenus.contains(tm)) {
							guiMenus.add(tm);
						}
					}
					Set<Menu> ms = tmpRole.getGrantedMenus();
					for(Menu tm:ms){ // 合并web的menu菜单
						if(!menus.contains(tm)){
							menus.add(tm);
						}
					}
					Set<Menu> opers = tmpRole.getGrantedOpers();
					for (Menu oper : opers) {
						if (!operCodes.contains(oper.getMenuName())) {
							operCodes.add(oper.getMenuName());
						}
						if (Menu.MENU_TYPE_URL.equals(oper.getMenuType())) {
							urlMaps.put(oper.getMenuName(), oper.getLocation());
						}
					}
					
				}else{
					roleIds[i++] = null;
				}
			}
			
			suser.setRoleIds(roleIds);
			suser.setRoleCodes(roleCodes);
			suser.setOperCodes(operCodes);
			suser.setUrlMaps(urlMaps);
			
			if(i > 1){
				OptionObjectPair first = rolePairs.get(0);
				suser.setCurrentRoleId(first.getKey());
				suser.setCurrentRoleName(first.getValue());
			}
		} else {
			logger.warn("数据不完�?,未设定[用户-角色]的对应关�?");
		}*/
		
//		suser.setMenus(buildMenusTree(menus, "menu"));
//		suser.setGuiMenus(buildMenusTree(guiMenus, "guimenu"));
		//设置默认角色
//		if(roleList != null && roleList.size() != 0) {
//			Role role = roleList.get(0);
//			suser.setCurrentRoleId(role.getId());
//			suser.setCurrentRoleName(role.getRoleName());
//		}
		
		return suser;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	/**
	 * 创建菜单�?
	 * @param menus
	 * @param type
	 * @return
	 */
	private List<SystemMenu> buildMenusTree(List<SystemMenu> menus, String type){
		List<SystemMenu> smenus = new ArrayList<SystemMenu>(0);
		Map<String, SystemMenu> tmpMenus = new HashMap<String, SystemMenu>(0);
//		if(null == appMap || appMap.size()<=0){
//			if(null == ApplicationDao.APP_MAP || ApplicationDao.APP_MAP.size()<=0){
//				applicationDao.getAppMap();
//			}
//			appMap = ApplicationDao.APP_MAP;
//		}
		// 1. 将所有菜单挂到Map中，并找出一级菜�?
		for(SystemMenu menu : menus) {
//			SystemMenu smenu = new SystemMenu();
//			smenu.setId(menu.getId());
//			smenu.setName(menu.getMenuName());
//			smenu.setTitle(menu.getMenuTitle());
//			String location = "";
//			String parentLoc = menu.getLocation();
//			if ("guimenu".equals(type)) {
//				smenu.setLocation(menu.getLocation());
//			} else if (null == parentLoc || "".equals(parentLoc)) {
//				smenu.setLocation(null);
//			} else {
//				if (null != appMap && appMap.size() > 0) {
//					String appId = menu.getApplication().getId();
//					if (appMap.containsKey(appId)) {
//						String tempstr = appMap.get(appId);
//						if (null != tempstr && !"".equals(tempstr.trim())) {
//							location = "/" + tempstr;
//						}
//					}
//				}
//				location += menu.getLocation();
//				smenu.setLocation(location);
//			}
//			smenu.setDisplayOrder(menu.getDisplayOrder());
//			smenu.setLevel(menu.getMenuLevel());
//			smenu.setTarget(menu.getTarget());
//
//			if (null == menu.getParentMenu()) {
//				// �?级菜�?
//				smenus.add(smenu);
//			} else
//				smenu.setParentId(menu.getParentMenu().getId());

//			tmpMenus.put(smenu.getId(), smenu);
			if(null == menu.getParentId()){
				smenus.add(menu);
			}
			tmpMenus.put(menu.getId(), menu);
		}
		// 2. 建立菜单�?
		buildSubMenu(menus, tmpMenus);

		// 3. 排序�?级菜�?
		Collections.sort(smenus);
		
		// 4. 排序其他级别菜单
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
	/**
	 * 建立子菜�?
	 * @param menus
	 * @param tmpMenus
	 */
	private void buildSubMenu(List<SystemMenu> menus, Map<String, SystemMenu> tmpMenus) {
		for (SystemMenu menu : menus) {
			if (null != menu.getParentId()) {
				SystemMenu parent = tmpMenus.get(menu.getParentId());
				SystemMenu current = tmpMenus.get(menu.getId());
				if(null != parent){
					parent.addSubMenu(current);
				}
			}
		}
	}
//	private void buildSubMenu(List<Menu> menus, Map<String, SystemMenu> tmpMenus) {
//		for (Menu menu : menus) {
//			if (null != menu.getParentMenu()) {
//				SystemMenu parent = tmpMenus.get(menu.getParentMenu().getId());
//				SystemMenu current = tmpMenus.get(menu.getId());
//				if(null != parent){
//					parent.addSubMenu(current);
//				}
//			}
//		}
//	}
	
//	private List<SystemMenu> generateMenus(List<SystemMenu> menus, String menuType) {
//		if (menus == null || menus.size() == 0)
//			return new ArrayList<SystemMenu>(0);
//
//		// 1)构�?�菜单到不同级别对同父叶子分�?
//		// Map<Level, Map<parentId, SystemMenu>> 
//		LinkedHashMap<Integer, LinkedHashMap<String, List<SystemMenu>>> mappings = new LinkedHashMap<Integer, LinkedHashMap<String, List<SystemMenu>>>();
//		
//		for (int i = 0; i < menus.size(); ++i) {
//			SystemMenu menu = menus.get(i);
//			
//			LinkedHashMap<String, List<SystemMenu>> map = mappings.get(menu.getLevel());
//			if (map == null) {
//				map = new LinkedHashMap<String, List<SystemMenu>>();
//				List<SystemMenu> ms = new ArrayList<SystemMenu>();
//				ms.add(menu);
//				map.put(menu.getParentId(), ms);
//				mappings.put(menu.getLevel(), map);
//			} else {
//				List<SystemMenu> ms = map.get(menu.getParentId());
//				if (ms == null)
//					ms = new ArrayList<SystemMenu>();
//				ms.add(menu);
//				map.put(menu.getParentId(), ms);
//			}
//		}
//
//		// 2) 构�?�tree
//		int first = 1;
//		Map<String, List<SystemMenu>> map1 = mappings.get(Integer.valueOf(first));
//
//		// �?级菜�?
//		List<SystemMenu> treemenus = new ArrayList<SystemMenu>();
//		
//		for(List<SystemMenu> m1 : map1.values()) {
//			treemenus.addAll(m1);
//		}
//
//		List<SystemMenu> levelMenu = treemenus;
//		
//		for (int i = first + 1; i <= mappings.size(); ++ i) {
//
//			tranverseMenu(levelMenu, mappings.get(Integer.valueOf(i)));
//			
//			List<SystemMenu> tmp = new ArrayList<SystemMenu>();
//			for (SystemMenu menu : levelMenu) {
//				if (menu.getSubMenus() != null)
//					tmp.addAll(menu.getSubMenus());
//			}
//			levelMenu = tmp;
//		}
//		
//		return treemenus;
//	}
	
	/**
	 * @param menus 上级菜单
	 * @param maps  下级菜单
	 */
//	private void tranverseMenu(List<SystemMenu> menus, Map<String, List<SystemMenu>> maps) {
//		// 菜单脏数据处�?
//		if (menus == null || maps == null)
//			return;
//		
//		for (SystemMenu menu : menus) {
//			List<SystemMenu> ms = maps.get(menu.getId());
//			if (ms != null) {
//				menu.setSubMenus(ms);
//			}
//		}
//	}
	
}
