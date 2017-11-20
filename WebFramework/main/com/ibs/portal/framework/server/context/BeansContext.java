package com.ibs.portal.framework.server.context;

import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.XmlWebApplicationContext;

public class BeansContext extends XmlWebApplicationContext{
	// ClassPath路径前缀
	private static final String CLASS_PATH_PREFIX = "classpath:";

	private static final String DEFAULT_CONFIG = CLASS_PATH_PREFIX
			+ "com/ibs/portal/framework/server/META-INF/config/beans.xml";
	
	public static final String PROJECT_PACKAGE_NAME = "PROJECT_PACKAGE_NAME";
	
	public static final String PROJECT_PACKAGE_MODULE_NAME = "PROJECT_PACKAGE_MODULE_NAME";
	
	/**
	 * struts file configuration name
	 */
	public static final String ACTION_CONFIG = "/META-INF/config/action.xml";

	/**
	 * spring file configuration name
	 */
	public static final String BEANS_CONFIG = "/META-INF/config/beans.xml";

	/**
	 * persistent file configuration name
	 */
	public static final String MAPPING_CONFIG = "/META-INF/config/mapping.xml";
	
	/**
	 * tiles file configuration name
	 */
	public static final String TILES_CONFIG = "/META-INF/config/tiles.xml";

	private static final String DEFAULT_SPRING_BEANS_CONFIG = "classpath*:applicationContext.xml";

	public String[] getConfigLocations() {
		// 获取原配置
		String[] configs = super.getConfigLocations();
		int configsSize = (configs == null ? 0 : configs.length);
		// 获取模块配置
		List<String> moduleConfigs = ApplicationContext.getContext()
				.getApplication().getBeansConfigs();
		int modulesSize = (moduleConfigs == null ? 0 : moduleConfigs.size());

		// 合并配置 
		//配置数+1  1是默认config
		String[] locations = new String[configsSize + modulesSize + 1];
		if (configsSize > 0) // 合并原配置
			System.arraycopy(configs, 0, locations, 0, configsSize);
		if (modulesSize > 0) { // 合并模块配置
			int i = configsSize;
			for (String mc : moduleConfigs)
				locations[i++] = CLASS_PATH_PREFIX + mc;
		}
		locations[locations.length - 1] = getDefaultConfigLocation();
		
		return locations;
	}

	private String getDefaultConfigLocation() {
		ServletContext servletContext = ApplicationContext.getContext()
				.getServletContext();
		try {
			URL url = servletContext.getResource(DEFAULT_SPRING_BEANS_CONFIG);
			if (url != null)
				return DEFAULT_SPRING_BEANS_CONFIG;
		} catch (Exception e) {

		}
		return DEFAULT_CONFIG;
	}

	protected String[] getDefaultConfigLocations() {
		return new String[0];
	}
}
