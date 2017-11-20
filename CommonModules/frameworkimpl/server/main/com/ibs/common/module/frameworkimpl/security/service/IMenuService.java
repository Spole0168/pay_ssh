package com.ibs.common.module.frameworkimpl.security.service;

import com.ibs.common.module.frameworkimpl.security.domain.Menu;

public interface IMenuService {

	/**
	 * get menu from cache
	 * @param menuName
	 * @return
	 */
	public Menu getMenu(String menuName);

	/**
	 * @param menuName
	 * @return
	 */
	public String getMenuLocation(String menuName);
}
