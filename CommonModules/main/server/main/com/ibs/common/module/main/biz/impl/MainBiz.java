package com.ibs.common.module.main.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.dto.UserComplex;
import com.ibs.common.module.frameworkimpl.security.exception.AuthorizeException;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.common.module.main.biz.IMainBiz;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.SystemMenu;

public class MainBiz extends BaseBiz implements IMainBiz {

	protected final Log log = LogFactory.getLog(getClass());

	private IPermissionService permissionService;

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}
	@Deprecated
	/*
	 * 这里没有任何实现？
	 */
	public void changePassword(String loginIp, String userId, String oldPassword, String newPassword)
			throws AuthorizeException {

	}
	/**
	 * 用户登录后，修改用户密码
	 */
	public void changePassword(String oldPassword, String newPassword) throws AuthorizeException {
		permissionService.resetUserPwd(oldPassword, newPassword, false);
	}
	public void logout(IUser user) throws AuthorizeException {
		UserContext.getUserContext().removeUserId();
	}

	/**
	 * 登录后获取完整的用户，初始化用户相关信息
	 * 
	 * @param suser
	 * @return
	 */
	public List<SystemMenu> loadUserMenu(UserComplex suser) {
		return permissionService.loadUserMenu(suser.getUserId());
	}
	
	/**
	 * 根据用户选择的角色加载用户菜单
	 * 
	 * @param suser
	 */
	public List<SystemMenu> loadCurrentRoleMenu(UserComplex suser,String nextRoleId){
		List<SystemMenu> smenus = new ArrayList<SystemMenu>(0);
		Map<String, SystemMenu> tmpMenus = new HashMap<String, SystemMenu>(0);

		if (log.isDebugEnabled())
			log.debug("构造菜单,当前的用户id[" + suser.getUserId() + "]");

		// 获取用户所有的角色构造菜单
		List<Menu> menus = permissionService.getAllMenusByUserIdAndRole(suser.getUserId(),nextRoleId);

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

	public List<OptionObjectPair> getAllRolesByUser(UserComplex suser) {
		List<OptionObjectPair> roles = new ArrayList<OptionObjectPair>();
		
		List<Role> roleList = permissionService.getAllRolesByUserName(suser.getUserName());
		
		for(Role role : roleList) {
			OptionObjectPair op = new OptionObjectPair();
			op.setKey(role.getId().toString());
			op.setValue(role.getDescription());

			roles.add(op);
		}

		return roles;
	}

	public void switchRole(UserComplex suser, String nextRoleId) {
		permissionService.switchRole((UserComplex)suser, nextRoleId);
	}

	private void buildSubMenu(List<Menu> menus, Map<String, SystemMenu> tmpMenus) {
		for (Menu menu : menus) {
			if (null != menu.getParentMenu()) {
				SystemMenu parent = tmpMenus.get(menu.getParentMenu().getId());
				SystemMenu current = tmpMenus.get(menu.getId());
				if(null!=parent){
					parent.addSubMenu(current);
				}
			}
		}
	}
	
	public Integer checkUserPwd(String userId, String oriPwd){
		return permissionService.checkUserPwd(userId, oriPwd);
	}

}
