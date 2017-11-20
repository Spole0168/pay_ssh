package com.ibs.portal.framework.server.metadata;

public class Module extends Assembly{
	public Module(String name, String version, String releaseDate,
			String implPackage) {
		super(name, version, releaseDate);
		this.implPackage = implPackage;
		this.implDirectory = implPackage.replace(".", "/");
	}

	private String implPackage;

	private String implDirectory;

	/**
	 * 获取子模块的包名称 ex: "com.ibs.app.yourproject.modulename."
	 * 
	 * @return 模块的包名称
	 */
	public String getPackage() {
		return implPackage + getName();
	}

	/**
	 * 获取子模块的目录名称 ex: "com/ibs/app/yourproject/modulename/"
	 * 
	 * @return 模块的目录名称
	 */
	public String getDirectory() {
		return implDirectory + getName();
	}

	/**
	 * 获取子模块的元数据目录
	 * 
	 * @return 模块的元数据目录
	 */
	public String getMetadataDirectory() {
		return getDirectory() + "/META-INF";
	}

	/**
	 * 获取子模块的配置目录
	 * 
	 * @return 模块的配置目录
	 */
	public String getConfigDirectory() {
		return getMetadataDirectory() + "/config";
	}

	/**
	 * 获取模块的国际化信息目录
	 * 
	 * @return 模块的国际化信息目录
	 */
	public String getI18nDirectory() {
		return getMetadataDirectory() + "/i18n";
	}

	/**
	 * 获取JSP页面源目录
	 * 
	 * @return 模块的JSP页面源目录
	 */
	public String getPagesDirectory() {
		return getMetadataDirectory() + "/pages";
	}

	public String getImagesDirectory() {
		return getMetadataDirectory() + "/images";
	}

	public String getScriptsDirectory() {
		return getMetadataDirectory() + "/js";
	}

	public String getStylesDirectory() {
		return getMetadataDirectory() + "/css";
	}

	/**
	 * 获取JSP页面所在目录
	 * 
	 * @return 模块的JSP页面所在目录
	 */
	public String getPagesExportDirectory() {
		return "/WEB-INF/pages/" + getName();
	}

	public String getImagesExportDirectory() {
		return "/images/" + getName();
	}

	public String getScriptsExportDirectory() {
		return "/js/" + getName();
	}

	public String getStylesExportDirectory() {
		return "/css/" + getName();
	}

	/**
	 * 获取子模块的自述信息
	 * 
	 * @return 模块的目录名称
	 */
	public String getMappingConfig() {
		return getConfigDirectory() + "/mapping.xml";
	}

	/**
	 * 获取子模块的自述信息
	 * 
	 * @return 模块的目录名称
	 */
	public String getBeansConfig() {
		return getConfigDirectory() + "/beans.xml";
	}

	/**
	 * 获取子模块的自述信息
	 * 
	 * @return 模块的目录名称
	 */
	public String getActionConfig() {
		return getConfigDirectory() + "/action.xml";
	}

	/**
	 * 获取模块的国际化信息目录
	 * 
	 * @return 模块的国际化信息目录
	 */
	public String getI18nBaseName() {
		return getI18nDirectory() + "/messages";
	}

	/**
	 * 获取子模块的自述信息
	 * 
	 * @return 模块的自述信息
	 */
	public String getReadme() {
		return getMetadataDirectory() + "/readme.txt";
	}

	public String getImplPackage() {
		return implPackage;
	}

	public void setImplPackage(String implPackage) {
		this.implPackage = implPackage;
	}
}
