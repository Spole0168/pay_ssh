package com.ibs.common.module.frameworkimpl.security.service.impl;

import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.service.IMenuService;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.service.BaseService;


public class MenuServiceImpl extends BaseService implements
		IMenuService {
	
	private String menuNameCacheRegion;

	public void setMenuNameCacheRegion(String menuNameCacheRegion) {
		this.menuNameCacheRegion = menuNameCacheRegion;
	}

	public Menu getMenu(String menuName) {
		
		ICache menuNameCache = CacheManager.getInstance().getCache(menuNameCacheRegion);
		
		Menu menu = (Menu)menuNameCache.getData(menuName);
		
		return menu;
	}

	public String getMenuLocation(String menuName) {
		
		Menu menu = getMenu(menuName);
		
		StringBuffer menuUrl = new StringBuffer();
		if (menu != null) {
			menuUrl.append("/").append(menu.getApplication().getAppName()).append(menu.getLocation());
		
			if (menuUrl.toString().indexOf("?") > 0)
				menuUrl.append("&menuId=").append(menu.getId());
			else
				menuUrl.append("?menuId=").append(menu.getId());
		}
		
		return menuUrl.toString();
	}

}
