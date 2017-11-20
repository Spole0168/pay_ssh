package com.ibs.portal.framework.server.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 国际化信息源.<br/>
 * 使用如:
 * <pre>
 * MessageSource messageSource = ModuleContext.getContext().getMessageSource(); // 获取当前模块国际化信息源
 * </pre>
 *
 * @see com.sf.framework.server.core.context.ModuleContext#getMessageSource()
 * @author 
 *
 */
public class MessageSource implements IMessageSource {

	private final Map<String,String> resourceMap;

	private final Locale locale;
	
	/**
	 * 构造国际化信息源
	 *
	 * @param resourceBundle 资源包
	 * @param locale 区域信息
	 */
	public MessageSource(Map<String,String> resourceMap, Locale locale) {
		this.resourceMap = resourceMap;
		this.locale = locale;
	}

	/**
	 * 获取默认的本地信息资源
	 *
	 * @param key
	 *            索引
	 * @return 本地信息资源
	 */
	public String getMessage(String key) {
		return getMessage(key, "NotSuch:" + key);
	}

	/**
	 * 获取默认的本地信息资源
	 *
	 * @param key
	 *            索引
	 * @param args
	 *            动态参数
	 * @return 本地信息资源
	 */
	public String getMessage(String key, Object[] args) {
		return formatMessage(getMessage(key), args);
	}

	/**
	 * 获取默认的本地信息资源
	 *
	 * @param key
	 *            索引
	 * @param defaultValue
	 *            默认值，当本地信息不存在时返回
	 * @return 本地信息资源
	 */
	public String getMessage(String key, String defaultValue) {
		if (resourceMap == null || key == null)
			return defaultValue;
		String msg;
		try {
			msg = resourceMap.get(key);
		} catch (java.util.MissingResourceException e) {
			msg = null;
		}
		if (msg == null)
			return defaultValue;
		return msg;
	}

	/**
	 * 获取基础属性值的国际化信息
	 * @param o
	 * @param propertyName
	 * @return
	 */
	public String getMessage(String key,Boolean b){
		String k = key+"."+"false";
		if(b.booleanValue()){
			k = key+"."+"true";
		}
		return getMessage(k);
	}

	/**
	 * 获取默认的本地信息资源
	 *
	 * @param key
	 *            索引
	 * @param args
	 *            动态参数
	 * @param defaultValue
	 *            默认值，当本地信息不存在时返回
	 * @return 本地信息资源
	 */
	public String getMessage(String key, Object[] args, String defaultValue) {
		return formatMessage(getMessage(key, defaultValue), args);
	}

	// 格式化占位符
	private final String formatMessage(String msg, Object[] args) {
		if (msg != null && msg.length() > 0
				&& args != null && args.length > 0) {
			return new MessageFormat(msg, locale).format(args);
		}
		return msg;
	}

}
