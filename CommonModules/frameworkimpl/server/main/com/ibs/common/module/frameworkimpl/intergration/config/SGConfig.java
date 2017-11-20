package com.ibs.common.module.frameworkimpl.intergration.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <pre>
 * *********************************************
 * Description: 属性文件配置类，给框架提供基本属性值
 * *********************************************
 * </pre>
 */
public class SGConfig {

	private static SGConfig instance = new SGConfig();
	// 数据报文路径定义
	private String rootPath;
	// 数据报文根
	private String rootPackage;
	// 数据报文目录日期格式
	private String dateFormat;

	private SGConfig() {
	}

	/**
	 * 获取SGConfig实例
	 * 
	 * @return
	 */
	public static SGConfig getInstance() {
		return instance;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateFormat getDateFormater() {
		return new SimpleDateFormat(dateFormat);
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
	}
}
