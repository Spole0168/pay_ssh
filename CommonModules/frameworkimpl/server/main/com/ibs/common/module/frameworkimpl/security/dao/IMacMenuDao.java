package com.ibs.common.module.frameworkimpl.security.dao;

import java.util.List;

import com.ibs.common.module.frameworkimpl.security.domain.MacMenu;

public interface IMacMenuDao {
	public void deleteAllBindingMacByMenuId(String menuId);

	public String save(MacMenu macMenu);
	
	public List<String> getMenuIdsByMacIds(List<String> macIds);
}
