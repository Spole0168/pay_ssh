package com.ibs.portal.framework.server.metadata;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
 
public class Application extends Assembly{
	@SuppressWarnings("unchecked")
	public Application(String name, String version, String releaseDate,
			Set<Module> modules, List<String> actionConfigs,
			List<String> beansConfigs, List<String> mappingConfigs,List<String> tilesConfigs) {
		super(name, version, releaseDate);
		this.modules = (modules == null ? Collections.EMPTY_SET : Collections
				.unmodifiableSet(modules));
		this.actionConfigs = (actionConfigs == null ? Collections.EMPTY_LIST
				: Collections.unmodifiableList(actionConfigs));
		this.beansConfigs = (beansConfigs == null ? Collections.EMPTY_LIST
				: Collections.unmodifiableList(beansConfigs));
		this.mappingConfigs = (mappingConfigs == null ? Collections.EMPTY_LIST
				: Collections.unmodifiableList(mappingConfigs));
		this.tilesConfigs = (tilesConfigs == null ? Collections.EMPTY_LIST
				: Collections.unmodifiableList(tilesConfigs));
	}

	private boolean securityCheck;
	private String implPackage;
	private Locale defaultLocale = Locale.SIMPLIFIED_CHINESE;

	public String getImplPackage() {
		return implPackage;
	}

	public void setImplPackage(String implPackage) {
		this.implPackage = implPackage;
	}

	public boolean isSecurityCheck() {
		return securityCheck;
	}

	public void setSecurityCheck(boolean securityCheck) {
		this.securityCheck = securityCheck;
	}

	private Set<Module> modules;

	/**
	 * 通过模块名获取模块信息
	 * 
	 * @moduleName 模块名
	 * @return 模块, 如果不存在则返回null
	 */
	public Module getModule(String moduleName) {
		if (moduleName != null)
			for (Module module : modules)
				if (moduleName.equals(module.getName()))
					return module;
		return null;
	}

	private final List<String> actionConfigs;
	private final List<String> beansConfigs;
	private final List<String> mappingConfigs;
	private final List<String> tilesConfigs;

	public List<String> getActionConfigs() {
		return actionConfigs;
	}

	public List<String> getBeansConfigs() {
		return beansConfigs;
	}

	public List<String> getMappingConfigs() {
		return mappingConfigs;
	}
	
	public List<String> getTilesConfigs() {
		return tilesConfigs;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
}
