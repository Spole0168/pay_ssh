package com.ibs.portal.framework.browser.tags;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.i18n.IMessageSource;
import com.ibs.portal.framework.server.i18n.MessageSourceContainer;

/**
 * 国际化EL表达式
 * 使用如:
 * &lt;%@ taglib prefix="app" uri="/app-tags"%&gt;
 * ${app:i18n(‘key’)}
 * ${app:i18n_arg1(‘key’, arg1)}
 * ${app:i18n_arg2(‘key’ , arg1, arg2)}
 * ${app:i18n_arg3(‘key’ , arg1, arg2, arg3)}
 * ${app:i18n_arg4(‘key’ , arg1, arg2, arg3, arg4)}
 * ${app:i18n_args(‘key’ , new Object[]{arg1, arg2 })}
 * ${app:i18n_def(‘key’,’defaultValue’)}
 * ${app:i18n_def_arg1 (‘key’,’defaultValue’,arg1)}
 * ${app:i18n_def_arg2(‘key’ ,’defaultValue’, arg1, arg2)}
 * ${app:i18n_def_arg3(‘key’ ,’defaultValue’, arg1, arg2, arg3)}
 * ${app:i18n_def_arg4(‘key’ ,’defaultValue’ , arg1, arg2, arg3, arg4)}
 * ${app:i18n_def_args(‘key’ ,’defaultValue’ , new Object[]{arg1, arg2})}
 *
 * @author 
 *
 */

/**
 * modify memo:
 * added i18n_func(String key, String value,String defaultValue)
 */
public class I18nEl {

	protected static Log logger = LogFactory.getLog(I18nEl.class);
	
	static MessageSourceContainer messageSourceContainer = MessageSourceContainer.getInstance ();

	public static final String concat(String str1, String str2) {
		return str1 + str2;
	}

	public static final String i18n(String key) {

		Locale locale = UserContext.getUserContext().getLocale();
		
		IMessageSource messageSource = messageSourceContainer.getMessageSource(locale);
		
		String retVal = null;
		if(messageSource!=null){
			retVal = messageSource.getMessage ( key );
		}

		if(null==retVal ) {
			logger.info("找不到" + key);
			logger.info(locale);
			//logger.debug("找不到" + key);
			return "";
		}

		return retVal;
	}

	public static final String i18n_arg1(String key, Object arg0) {
		return i18n_args(key, new Object[]{arg0});
	}

	public static final String i18n_arg2(String key, Object arg0, Object arg1) {
		return i18n_args(key, new Object[]{arg0, arg1});
	}

	public static final String i18n_arg3(String key, Object arg0, Object arg1, Object arg2) {
		return i18n_args(key, new Object[]{arg0, arg1, arg2});
	}

	public static final String i18n_arg4(String key, Object arg0, Object arg1, Object arg2, Object arg3) {
		return i18n_args(key, new Object[]{arg0, arg1, arg2, arg3});
	}

	public static final String i18n_func_def(String key, String propertyValue, String defaultValue) {
		Locale locale = UserContext.getUserContext().getLocale();

		IMessageSource msgRes = messageSourceContainer.getMessageSource(locale);
		String retVal = null;
		if(msgRes!=null){
			retVal = msgRes.getMessage(key, new String[]{propertyValue}, defaultValue);
		}

		if(null==retVal ) {
			if(logger.isDebugEnabled()){
				logger.debug("找不到" + key);
			}
			//logger.debug("找不到" + key);
			return "";
		}

		return retVal;
	}

	public static final String i18n_func(String key, String propertyValue) {
		Locale locale = UserContext.getUserContext().getLocale();
		IMessageSource msgRes = messageSourceContainer.getMessageSource(locale);
		String retVal = null;
		if(msgRes!=null){
			retVal = msgRes.getMessage(key,propertyValue);
		}

		if(null==retVal ) {
			if(logger.isDebugEnabled()){
				logger.debug("找不到" + key);
			}
			//logger.debug("找不到" + key);
			return "";
		}

		return retVal;
	}

	public static final String i18n_func_bln(String key, Boolean b) {
		Locale locale = UserContext.getUserContext().getLocale();

		IMessageSource msgRes = messageSourceContainer.getMessageSource(locale);
		String retVal = null;
		if(msgRes!=null){
			retVal = msgRes.getMessage(key, b);
		}

		if(null==retVal ) {
			if(logger.isDebugEnabled()){
				logger.debug("找不到" + key);
			}
			//logger.debug("找不到" + key);
			return "";
		}

		return retVal;
	}

	public static final String i18n_args(String key, Object[] args) {
		Locale locale = UserContext.getUserContext().getLocale();

		IMessageSource msgRes = messageSourceContainer.getMessageSource(locale);
		String retVal = null;
		if(msgRes!=null){
			retVal = msgRes.getMessage(key, args);
		}

		if(null==retVal ) {
			if(logger.isDebugEnabled()){
				logger.debug("找不到" + key);
			}
		//	logger.debug("找不到" + key);
			return "";
		}

		return retVal;
	}

	public static final String i18n_def(String key, String defaultValue) {
		Locale locale = UserContext.getUserContext().getLocale();

		IMessageSource msgRes = messageSourceContainer.getMessageSource(locale);
		String retVal = null;
		if(msgRes!=null){
			retVal = msgRes.getMessage(key);
		}

		if(null==retVal ) {
			if(logger.isDebugEnabled()){
				logger.debug("找不到" + key);
			}
			//logger.debug("找不到" + key);
			return "";
		}

		return retVal;
		//return ApplicationContext.getContext().getMessageSource().getMessage(key, defaultValue);
	}

	public static final String i18n_def_arg1(String key, String defaultValue, Object arg0) {
		return i18n_def_args(key, defaultValue, new Object[]{arg0});
	}

	public static final String i18n_def_arg2(String key, String defaultValue, Object arg0, Object arg1) {
		return i18n_def_args(key, defaultValue, new Object[]{arg0, arg1});
	}

	public static final String i18n_def_arg3(String key, String defaultValue, Object arg0, Object arg1, Object arg2) {
		return i18n_def_args(key, defaultValue, new Object[]{arg0, arg1, arg2});
	}

	public static final String i18n_def_arg4(String key, String defaultValue, Object arg0, Object arg1, Object arg2, Object arg3) {
		return i18n_def_args(key, defaultValue, new Object[]{arg0, arg1, arg2, arg3});
	}

	public static final String i18n_def_args(String key, String defaultValue, Object[] args) {
		Locale locale = UserContext.getUserContext().getLocale();

		IMessageSource msgRes = messageSourceContainer.getMessageSource(locale);
		String retVal = null;
		if(msgRes!=null){
			retVal = msgRes.getMessage(key, args, defaultValue);
		}

		if(null==retVal ) {
			//logger.debug("找不到" + key);
			if(logger.isDebugEnabled()){
				logger.debug("找不到" + key);
			}
			return "";
		}

		return retVal;
	}

	//TODO 菜单的国际化需要补充进来
	//public static final String i18n_menu(String key) {
	//	return ApplicationContext.getContext().getMenuMessageSource().getMessage(key);
	//}

	//public static final String i18n_menu_def(String key, String defaultValue) {
	//	return ApplicationContext.getContext().getMenuMessageSource().getMessage(key, defaultValue);
	//}

}
