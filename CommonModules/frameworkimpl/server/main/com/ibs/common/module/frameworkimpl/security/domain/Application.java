package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.HashSet;
import java.util.Set;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 应用系统
 * 
 * @author MaChong
 *
 */
public class Application extends BaseEntity {

	private static final long serialVersionUID = 21603136222929422L;
	
	private String id;
	
	private String appName;
	
	private String description;
	
	private Set<Menu> includedMenus = new HashSet<Menu>(0);
	private Set<Menu> includedOpers = new HashSet<Menu>(0);
	private Set<Menu> includedGuiMenus = new HashSet<Menu>(0);

	public Application() {
	}

	public Application(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Set<Menu> getIncludedMenus() {
		return includedMenus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIncludedMenus(Set<Menu> includedMenus) {
		this.includedMenus = includedMenus;
	}

	public Set<Menu> getIncludedOpers() {
		return includedOpers;
	}

	public void setIncludedOpers(Set<Menu> includedOpers) {
		this.includedOpers = includedOpers;
	}

	public Set<Menu> getIncludedGuiMenus() {
		return includedGuiMenus;
	}

	public void setIncludedGuiMenus(Set<Menu> includedGuiMenus) {
		this.includedGuiMenus = includedGuiMenus;
	}
}
