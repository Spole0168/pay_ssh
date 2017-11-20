package com.ibs.portal.framework.server.context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.ibs.portal.framework.server.metadata.Application;
import com.ibs.portal.framework.server.metadata.Module;
import com.ibs.portal.framework.util.IOUtils;

public class ApplicationContext {
	private static Logger logger = LoggerFactory
			.getLogger(ApplicationContext.class);

	private static ApplicationContext context;

	private ServletContext servletContext;

	private Application application;
	
	private static String PROJECT_PACKAGE_NAME = "com/corpname/projectname/"; 
	
	private static String PROJECT_COMMON_PACKAGE_NAME = "com/corpname/projectname/"; 

	private static String PROJECT_MODULE_NAME = "module/"; 
	
	private static String PROJECT_PACKAGE_MODULE_FULL_NAME = PROJECT_PACKAGE_NAME + PROJECT_MODULE_NAME;
	
	private static String PROJECT_COMMON_PACKAGE_MODULE_FULL_NAME = PROJECT_COMMON_PACKAGE_NAME + PROJECT_MODULE_NAME;
	/**
	 * 获取子系统上下文
	 * 
	 * @return 子系统上下文
	 */
	public static ApplicationContext getContext() {
		if (context == null)
			throw new ConfigurationException(
					"please config web.xml, add: \n<listener>\n\t<listener-class>"
							+ ApplicationListener.class.getName()
							+ "</listener-class>\n</listener>\n");
		return context;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public Application getApplication() {
		return application;
	}

	/**
	 * 初始化上下文, 由框架调用.
	 * 
	 * @param servletContext
	 *            Servelet上下文
	 */
	public static void init(ServletContext servletContext) {
		context = new ApplicationContext(servletContext);
	}

	/**
	 * 销毁上下文, 由框架调用.
	 */
	public static void destroy(ServletContext servletContext) {
		context = null;
	}

	// 私有构造函数, 禁止实例化, 通过getContext()工厂方法获取单例
	private ApplicationContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initApplication();
	}

	// 初始化子系统信息
	private void initApplication() {
		logger.debug("[Framework] init application: "
				+ servletContext.getServletContextName());
		logger.debug("[Framework] application location: "
				+ servletContext.getRealPath("/"));
		

		PROJECT_PACKAGE_NAME = servletContext.getInitParameter("PROJECT_PACKAGE_NAME");
		PROJECT_COMMON_PACKAGE_NAME = servletContext.getInitParameter("PROJECT_COMMON_PACKAGE_NAME");
		PROJECT_MODULE_NAME = servletContext.getInitParameter("PROJECT_MODULE_NAME");
		PROJECT_PACKAGE_MODULE_FULL_NAME = PROJECT_PACKAGE_NAME + PROJECT_MODULE_NAME;
		PROJECT_COMMON_PACKAGE_MODULE_FULL_NAME = PROJECT_COMMON_PACKAGE_NAME + PROJECT_MODULE_NAME;

		/*
		 * 构建模块集合
		 */
		List<String> moduleNames = new ArrayList<String>();
		Set<Module> modules = new HashSet<Module>();
		
		//加载Struts配置文件
		List<String> actionConfigs = initConfigs(
				BeansContext.ACTION_CONFIG, moduleNames, modules);
		
		//加载Spring配置文件
		List<String> beansConfigs = initConfigs(BeansContext.BEANS_CONFIG,
				moduleNames, modules);
		
		//加载Hibernate映射文件
		List<String> mappingConfigs = initConfigs(
				BeansContext.MAPPING_CONFIG, moduleNames, modules);
	
		
		//加载Tiles配置文件
		List<String> tilesConfigs = initConfigs(BeansContext.TILES_CONFIG, moduleNames, modules);

		// move init modules action into initConfigs
//		Set<Module> modules = initModules(moduleNames);

		exportResources(modules);

		// TODO
		this.application = new Application(null, null, null, modules,
				actionConfigs, beansConfigs, mappingConfigs,tilesConfigs);
	}

	private PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	// 初始化子系统包含的模块信息
	private List<String> initConfigs(String configName, List<String> moduleNames, Set<Module> modules) {
		List<String> configs = new ArrayList<String>(); // 构建模块集合
		try {
			Resource[] resources = resolver.getResources("classpath*:"
					+ PROJECT_PACKAGE_NAME + "**" + configName);
			logger.debug("[Framework] search " + configName + " count: "
					+ resources.length);
			if (logger.isInfoEnabled())
				logger.info("[Framework] search " + configName + " count: "
						+ resources.length);

			for (Resource resource : resources) {
				try {
					String file = resource.getURL().getPath();
					String config = file.substring(file
							.lastIndexOf(PROJECT_PACKAGE_NAME));
					if (!configs.contains(config)) {
						logger.debug("[Framework] add config: " + config);

						if (logger.isInfoEnabled())
							logger.info("[Framework] add config: " + config);
						configs.add(config);
					}
					if (config.startsWith(PROJECT_PACKAGE_MODULE_FULL_NAME)) {
						String moduleName = config.substring(
								PROJECT_PACKAGE_MODULE_FULL_NAME.length(),
								config.length() - configName.length());
						logger.debug("[Framework] parse module name: "
								+ moduleName);

						if (logger.isInfoEnabled())
							logger.info("[Framework] parse module name: "
									+ moduleName);
						if (!moduleNames.contains(moduleName)) {
							moduleNames.add(moduleName);
							modules.add(new Module(moduleName, null, null,
									PROJECT_PACKAGE_MODULE_FULL_NAME));

						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			// load common module config
			Resource[] commonResources = resolver.getResources("classpath*:"
					+ PROJECT_COMMON_PACKAGE_NAME + "**" + configName);
			logger.debug("[Framework] search " + configName + " count: "
					+ resources.length);
			if (logger.isInfoEnabled())
				logger.info("[Framework] search " + configName + " count: "
						+ resources.length);

			for (Resource resource : commonResources) {
				try {
					String file = resource.getURL().getPath();
					String config = file.substring(file
							.lastIndexOf(PROJECT_COMMON_PACKAGE_NAME));
					if (!configs.contains(config)) {
						logger.debug("[Framework] add config: " + config);

						if (logger.isInfoEnabled())
							logger.info("[Framework] add config: " + config);
						configs.add(config);
					}
					if (config.startsWith(PROJECT_COMMON_PACKAGE_MODULE_FULL_NAME)) {
						String moduleName = config.substring(
								PROJECT_COMMON_PACKAGE_MODULE_FULL_NAME.length(),
								config.length() - configName.length());
						logger.debug("[Framework] parse module name: "
								+ moduleName);

						if (logger.isInfoEnabled())
							logger.info("[Framework] parse module name: "
									+ moduleName);
						if (!moduleNames.contains(moduleName)) {
							moduleNames.add(moduleName);
							modules.add(new Module(moduleName, null, null,
									PROJECT_COMMON_PACKAGE_MODULE_FULL_NAME));

						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return configs;
	}

	// 初始化子系统包含的模块信息
	private Set<Module> initModules(List<String> moduleNames) {
		Set<Module> modules = new HashSet<Module>(); // 构建模块集合
		try {
			logger.debug("[Framework] search module count: "
					+ moduleNames.size());

			if (logger.isInfoEnabled())
				logger.info("[Framework] search module count: "
						+ moduleNames.size());

			for (String moduleName : moduleNames) {
				logger.debug("[Framework] add module: " + moduleName);

				if (logger.isInfoEnabled())
					logger.info("[Framework] add module: " + moduleName);
				
				// TODO
				modules.add(new Module(moduleName, null, null,
						PROJECT_PACKAGE_MODULE_FULL_NAME));

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return modules;
	}

	// 导出资源
	private void exportResources(Set<Module> modules) {
		for (Module module : modules) {
			export(module.getPagesDirectory(), module.getPagesExportDirectory());
			export(module.getImagesDirectory(), module
					.getImagesExportDirectory());
			export(module.getScriptsDirectory(), module
					.getScriptsExportDirectory());
			export(module.getStylesDirectory(), module
					.getStylesExportDirectory());
		}
	}

	private void export(String src, String dist) {
		try {
			Resource[] resources = resolver.getResources("classpath*:" + src
					+ "/**/*.*");

			if (resources != null && resources.length > 0) {

				File pagesTargetDir = new File(servletContext.getRealPath(dist));
				if (!pagesTargetDir.exists())
					pagesTargetDir.mkdirs();

				for (Resource resource : resources) {
					try {
						String resouceName = resource.getURL().getPath();
						int index = resouceName.indexOf(src);
						String fileName = resouceName.substring(index
								+ src.length());
						File target = new File(pagesTargetDir, fileName);
						File dir = target.getParentFile();
						
						//读出resource文件，解决读取jar文件中内容文件失败问题
						File srcFile = new File(resource.getURL().getFile());
						
						
						if (!dir.exists())
							dir.mkdirs();
						
						// 不存在 或 修改时间晚
						if (!target.exists() ||srcFile.lastModified() > target.lastModified()) {
						// 直接覆盖?
							logger.debug("[Framework] release resource: "
									+ dist + fileName);

							if (logger.isInfoEnabled())
								logger.info("[Framework] release resource: "
										+ dist + fileName);

							OutputStream out = new FileOutputStream(target);
							InputStream in = resource.getInputStream();
							IOUtils.copy(in, out, true);
						}
					} catch (Exception e) {
						servletContext.log(e.getMessage(), e);
						if(logger.isErrorEnabled()){
							logger.error(e.getMessage(), e);
						}
						//logger.error(e.getMessage(), e);
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			if(logger.isErrorEnabled()){
				logger.error(e.getMessage(), e);
			}
			//logger.error(e.getMessage(), e);
		}
	}
}
