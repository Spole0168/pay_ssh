package com.ibs.portal.framework.server.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.portal.framework.server.context.ApplicationContext;
import com.ibs.portal.framework.server.metadata.Application;
import com.ibs.portal.framework.server.metadata.Module;

public class MessageSourceContainer {

	public static final String GLOBAL_MESSAGE_BASE_NAME = "/WEB-INF/i18n";

	static MessageSourceContainer messageSourceCache = new MessageSourceContainer();

	protected final Log logger = LogFactory.getLog(MessageSourceContainer.class);

	public static MessageSourceContainer getInstance() {
		return messageSourceCache;
	}

	/**
	 * @return the globalMsPath
	 */
	public String getGlobalMsPath() {
		return globalMsPath;
	}

	/**
	 * @param globalMsPath
	 *            the globalMsPath to set
	 */
	public void setGlobalMsPath(String globalMsPath) {
		
		this.globalMsPath = ApplicationContext.getContext().getServletContext().getRealPath("/") + globalMsPath;
		
		try {
			File f = new File(this.globalMsPath);
			if (!f.exists())
				return;

			this.globalMsPath = this.globalMsPath + "/messages";
			defaultRs = new PropertyResourceBundle(new FileInputStream(
					this.globalMsPath + ".properties"));
		} catch (FileNotFoundException e) {
			if (logger.isDebugEnabled())
				logger
						.debug("Global resource file: Can't load default global i18n resource file:"
								+ this.globalMsPath);
		} catch (IOException e) {
			if (logger.isDebugEnabled())
				logger
						.debug("Global resource file: Can't load default global i18n resource file"
								+ this.globalMsPath);
		}
	}

	String globalMsPath = "";

	ResourceBundle defaultRs = null;

	//第一个String Module_KeyName
	//第二个String 值
	Map<Locale,MessageSource> messageSourceMap = new HashMap<Locale,MessageSource>();
	
	public IMessageSource getMessageSource(Locale locale) {

		MessageSource messageSource = null;
		if(messageSourceMap.containsKey(locale) == false) {
			messageSource = initialMessageSource(locale);
			messageSourceMap.put(locale, messageSource);
		}
		else {
			messageSource = messageSourceMap.get(locale);
		}
		
		return messageSourceMap.get(locale);
	}
	
	private synchronized MessageSource initialMessageSource(Locale locale) {
		
		//if(StringUtils.isEmpty(arg)locale.getCountry().equals(""))
		/**
		 * 获取总的国际化资源
		 */
		if (null == globalMsPath || "".equals(globalMsPath)) {
			this.setGlobalMsPath(MessageSourceContainer.GLOBAL_MESSAGE_BASE_NAME);
		}
		
		String globalBaseName = globalMsPath + "_" + locale.getLanguage();
		if (null != locale.getCountry()
				&& !"".equals(locale.getCountry())) {
			globalBaseName = globalBaseName + "_" + locale.getCountry();
		}
		globalBaseName = globalBaseName + ".properties";

		ResourceBundle rs = null;
		try {
			rs = new WebResourceBundle(globalBaseName,
				defaultRs);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("Can't find bundle for base name:"
						+ globalBaseName);
			}
			return null;
		}
		
		//构建messageMap
		Map<String,String> messageMap = new HashMap<String,String>();
		
		//从resourceBundle中获取数据并设置进messageMap
		for (Enumeration<String> e = rs.getKeys(); e.hasMoreElements() ;) {
			String key = e.nextElement();
			String value = rs.getString(key);
			if(messageMap.containsKey(key)) {
				if(logger.isErrorEnabled()){
					logger.error("国际化资源(" + key + ")已经加载，不能再次加载");
				}
				//logger.error("国际化资源(" + key + ")已经加载，不能再次加载");
			}
			else {
				messageMap.put(key, value);
			}
	     }
		

		/**
		 * 获取各模块的国际化资源
		 */
		// 获取当前模块的资源文件base name
		Application app = ApplicationContext.getContext()
			.getApplication();
		if (null == app) {
			logger
				.debug("Can not find the applicatin ");
			return null;
		}

		for(Iterator<Module> i = app.getModules().iterator();i.hasNext();) {
			Module module = i.next();
			String baseName = module.getI18nBaseName();
			
			logger.info(baseName);
			logger.info(locale);
			
			ResourceBundle resourceBundle = null;
			try {
				resourceBundle = ResourceBundle
						.getBundle(baseName, locale, Thread
								.currentThread()
								.getContextClassLoader());
				
				
			} catch (MissingResourceException e) {
				if(logger.isErrorEnabled()){
					logger.error("Can't find bundle for base name:"
							+ baseName);
				}
				//logger.error("Can't find bundle for base name:"
				//			+ baseName);
			}
			
			if(resourceBundle != null) {
				//从resourceBundle中获取数据并设置进messageMap
				for (Enumeration<String> e = resourceBundle.getKeys(); e.hasMoreElements() ;) {
					String key = e.nextElement();
					String value = resourceBundle.getString(key);
					if(messageMap.containsKey(key)) {
						if(logger.isErrorEnabled()){
							logger.error("国际化资源(" + key + ")已经加载，不能再次加载");
						}
						//logger.error("国际化资源(" + key + ")已经加载，不能再次加载");
					}
					else {
						logger.debug(key + ": " + value);
						messageMap.put(key, value);
					}
				}
			}
			
		}
		
		return new MessageSource(messageMap,locale);
	}

	private boolean existsMessageBundleFile(String path) {
		File tmpFile = new File(path);
		boolean exists = tmpFile.exists();
		tmpFile = null;
		return exists;
	}
}
