package com.ibs.portal.framework.server.i18n;

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
public interface IMessageSource {

	/**
	 * 获取默认的本地信息资源
	 *
	 * @param key
	 *            索引
	 * @return 本地信息资源
	 */
	public String getMessage(String key);

	/**
	 * 获取默认的本地信息资源
	 *
	 * @param key
	 *            索引
	 * @param args
	 *            动态参数
	 * @return 本地信息资源
	 */
	public String getMessage(String key, Object[] args);

	/**
	 * 获取默认的本地信息资源
	 *
	 * @param key
	 *            索引
	 * @param defaultValue
	 *            默认值，当本地信息不存在时返回
	 * @return 本地信息资源
	 */
	public String getMessage(String key, String defaultValue);

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
	public String getMessage(String key, Object[] args, String defaultValue);

	/**
	 * 通过布尔值获取同个属性的值
	 * @param key
	 * @param b
	 * @return
	 */
	public String getMessage(String key,Boolean b);
}
