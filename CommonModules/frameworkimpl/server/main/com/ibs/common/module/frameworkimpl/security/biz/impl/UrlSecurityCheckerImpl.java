package com.ibs.common.module.frameworkimpl.security.biz.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.security.dao.IMenuDao;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.portal.framework.common.Constants;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.SystemMenu;
import com.ibs.portal.framework.server.security.IResourceSecurityChecker;
import com.ibs.portal.framework.util.StringUtils;

public class UrlSecurityCheckerImpl implements IResourceSecurityChecker {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
//	private IResourceDao resourceDao;
	private IMenuDao menuDao;
	private IPermissionService permissionService;

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public ICache getUrlResourceCache() {
		return CacheManager.getInstance().getCache(Constants.URL_RESOURCE_CACHE);
	}

	/**
	 * 获取url资源缓存数据
	 *
	 * @param key
	 *            缓存注册ID
	 * @return 缓存数据
	 */
	public Menu getUrlResourceCached(String key) {
		ICache urlResourceCache = getUrlResourceCache();
		return (Menu)urlResourceCache.getData(key);
	}

	public boolean hasPrivilege(IUser user, String resourceValue) {

		// 1.从缓存获得resourceValue对应的resource,若为空返回true
		Menu menu = getUrlResourceCached(resourceValue);
		if (null == menu) {
			return true;
		}
		
		// 2.检查该用户是否具有资源权限
		if (Menu.MENU_TYPE_URL.equals(menu.getMenuType())) {
			// URL校验
			return menuDao.userHasOperation(user.getUserId(), menu.getId());
		} else if (Menu.MENU_TYPE_MENU.equals(menu.getMenuType())) {
			// 菜单校验
			List<SystemMenu> menus = user.getMenus();
			for (SystemMenu sm : menus) {
				if (recursionMenu(user, sm , menu))
					return true;
				
			}
			return false;
		}
		
		return true;
	}

	private boolean recursionMenu(IUser user, SystemMenu subMenu, Menu menu) {

		String url = subMenu.getLocation();
		if (StringUtils.isNotEmpty(url)) {
			int pos = url.indexOf("/", 1);
			if (pos > 0)
				url = url.substring(pos);
			
			if (menu.getLocation().equals(url)) {
				// 增加MAC地址校验
				if (Menu.MENU_TARGET_MAC.equals(menu.getTarget())) {
					boolean b = permissionService.hasMacPermission(menu.getId(), user);
					if (!b)
						throw new BizException("MAC地址认证失败，请确认安装金刚系统安全控件！");
				}
				
				user.setMenuId(menu.getId());
				return true;
			}
		}
		
		
		if (subMenu.getSubMenus() != null) {
			for (SystemMenu sm : subMenu.getSubMenus()) {
				if (recursionMenu(user, sm, menu))
					return true;
			}
		}
		
		return false;
	}

}
