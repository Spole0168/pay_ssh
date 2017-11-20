package com.ibs.common.module.frameworkimpl.security.domain;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class MacMenu extends BaseEntity{
	
	private static final long serialVersionUID = 8586516894018092179L;

	private String macId;
	
	private String menuId;

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
