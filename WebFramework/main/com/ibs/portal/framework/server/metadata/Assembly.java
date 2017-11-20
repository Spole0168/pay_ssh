package com.ibs.portal.framework.server.metadata;

public abstract class Assembly {
	public Assembly(String name, String version, String releaseDate) {
		this.name = name;
		this.version = version;
		this.releaseDate = releaseDate;
	}

	private final String name;

	/**
	 * 获取程序集(子系统或模块)的名称
	 * 
	 * @return 程序集(子系统或模块)的名称
	 */
	public String getName() {
		return name;
	}

	private final String version;

	/**
	 * 获取程序集(子系统或模块)的版本号
	 * 
	 * @return 程序集(子系统或模块)的版本号
	 */
	public String getVersion() {
		return version;
	}

	private String releaseDate;

	public String getReleaseDate() {
		return releaseDate;
	}
}
