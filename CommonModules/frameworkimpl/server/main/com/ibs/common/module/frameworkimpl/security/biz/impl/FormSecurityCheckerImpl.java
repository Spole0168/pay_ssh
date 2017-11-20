package com.ibs.common.module.frameworkimpl.security.biz.impl;

import com.ibs.common.module.frameworkimpl.security.dao.IMenuDao;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.portal.framework.common.Constants;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.security.IResourceSecurityChecker;

public class FormSecurityCheckerImpl implements IResourceSecurityChecker {
	
	private IMenuDao menuDao;
	private IPermissionService permissionService;
	
	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public ICache getButtonResourceCache() {
		return CacheManager.getInstance().getCache(Constants.BUTTON_RESOURCE_CACHE);
	}

	/**
	 * 获取button资源缓存数据
	 *
	 * @param key
	 *            缓存注册ID
	 * @return 缓存数据
	 */
	public Menu getButtonResourceCached(String key) {
		ICache buttonResourceCache = getButtonResourceCache();
		return (Menu)buttonResourceCache.getData(key);
	}

	public boolean hasPrivilege(IUser user, String resourceValue) {

		// 1.从缓存获得resourceValue对应的resource,若为空返回true
		Menu resource = getButtonResourceCached(resourceValue);
		// 如果未设定该资源，则不允许访问
		if (null == resource) {
			return false;
		}
		
		// 1.没有资源权限
		if (!user.getOperCodes().contains(resourceValue))
			return false;
		
		// 2.没有资源MAC地址权限
		if (Menu.MENU_TARGET_MAC.equals(resource.getTarget())) {
			return permissionService.hasMacPermission(resource.getId(), user);
		}
		
		return true;

	}
}
