package com.ibs.portal.framework.server.context;

import java.net.URLDecoder;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.common.Constants;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BaseRuntimeException;
import com.ibs.portal.framework.server.exception.NotLoginException;
import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.Token;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户身份上下文. 表示当前用户环境信息 <br/> 
 * 
 * @author : 
 * 
 */
public class UserContext {

	public static final String USER_UID_KEY = "SESSION_UID";
	public static final String LOCALE_KEY = "SESSION_LOCALE";
	/**
	 * 密码未修改时，session中的标记位
	 */
	public static final String SESSION_PWD_NEED_CHANGE_FLAG = "SESSION_PWD_NEED_CHANGE_FLAG";

	private static final Logger logger = LoggerFactory
			.getLogger(UserContext.class);

	// 禁止在外部实例化
	private UserContext() {
		Locale.setDefault(Locale.PRC);
	}
	
	public ICache getUserCache() {
		return CacheManager.getInstance().getCache(Constants.USER_CACHE);
	}

	static class UserContextHolder {
		static UserContext context = new UserContext();
	}

	public static UserContext getUserContext() {
		return UserContextHolder.context;
	}

	/**
	 * 获取用户级缓存数据
	 *
	 * @param key
	 *            缓存注册ID
	 * @return 缓存数据
	 */
	public Object getUserCached(String key) {
		ICache userCache = getUserCache();
		
		return userCache.getData(key);
	}
	
	/**
	 * 获取当前登录用户的ID
	 *
	 * @return 登录用户的ID, 未登录返回null
	 */
	public String getUserId() {

		return (String) ServletActionContext.getRequest().getSession()
				.getAttribute(USER_UID_KEY);
	}
	
	public String getUserInCookie() {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String strToken="";
		
		if(cookies!=null){
//			logger.trace("cookie size: " + cookies.length);
			for(int i=0;i<cookies.length;i++){
//				logger.trace("cookie name: " + cookies[i].getName() + "; " + cookies[i].getValue());
				if(cookies[i].getName().equals(ConfigFactory.TOKEN_HEADER_KEY)){
					strToken = cookies[i].getValue();
					break;
				} 
			}
		}
		logger.trace("Token: " + strToken);
		if ( StringUtils.isBlank(strToken) )
			return null;
		
		Token token = Token.valueOf(strToken);
		
		if ( token != null ) {
			return token.getUserId();
		}
		
		return null;
	}
	
	/**
	 * 从session中取得当前用户，用于支持GUI的登录；
	 * @return
	 */
	public IUser getCurrentUserFromSession() {
		String userId = UserContext.getUserContext().getUserId();
		if (null == userId) {
			if(logger.isDebugEnabled()){
				logger.debug(" Current Session Not Found UserID");
			}
			//logger.debug(" Current Session Not Found UserID");
			throw new BaseRuntimeException();
		}
		Object obj = getUserCached(userId);
		
		if (null == obj) {
			if(logger.isDebugEnabled()){
				logger.debug(" can not found User from Cache by  UserID [ " + userId
						+ "]");
			}
		//	logger.debug(" can not found User from Cache by  UserID [ " + userId
		//			+ "]");
			throw new BaseRuntimeException();
		}
		IUser user = (IUser) obj;
		return user;
	}

	/**
	 * 注册登录用户的ID (在登录Action中调用)
	 *
	 * @param id
	 *            登录用户的ID
	 */
	@SuppressWarnings("unchecked")
	public void setUserId(String id) {
		ServletActionContext.getRequest().getSession().setAttribute(
				USER_UID_KEY, id);
	}
	
	/**
	 * 注册登录用户的ID (在SSO中调用)
	 *
	 * @param id
	 *            登录用户的ID
	 */
	@SuppressWarnings("unchecked")
	public void setUserId(HttpSession session,String id) {
		session.setAttribute(
				USER_UID_KEY, id);
	}

	/**
	 * 注销登录用户的ID (在退出登录Action中调用)
	 */
	public void removeUserId() {
		String userId = getUserId();
		try {
			if (userId != null) {
				ServletActionContext.getRequest().getSession().setAttribute(
						USER_UID_KEY, null);
			}
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("Error happen when removeUserId[" + userId.toString()
						+ "]", e);
			}
			//logger.error("Error happen when removeUserId[" + userId.toString()
			//		+ "]", e);
		}
	}

	
	/**
	 * 获取当前用户
	 * 
	 * 2010/01/18  
	 * 从cookie中获取当前用户，不再从session中取得；
	 * 避免出现同一台机器下，使用多个用户登录系统发生菜单互串的问题；
	 * 参考SecurityInterceptor.java中的注释；
	 * 
	 * @return
	 */
	public IUser getCurrentUser() {
		String userId = getUserInCookie();
		if (null == userId) {
			logger.warn(" Could not find User from cookie");
			throw new NotLoginException();
		}
		
		Object obj = getUserCached(userId);
		if (null == obj) {
			if(logger.isDebugEnabled()){
				logger.debug(" can not found User from Cache by  UserID [ " + userId
						+ "]");
			}
		//	logger.debug(" can not found User from Cache by  UserID [ " + userId
		//			+ "]");
			throw new BaseRuntimeException();
		}
		IUser user = (IUser) obj;
		return user;
	}

	/**
	 * 获取当前登录用户的用户名
	 * 
	 * @return 登录用户的用户名, 未登录返回null
	 */
	public String getUserName() {
		IUser user = getCurrentUser();
		if (user != null)
			return user.getUserName();
		else
			return null;
	}

	/**
	 * 注册登录用户 (在登录Action中调用)
	 * 
	 * @param user
	 *            登录用户
	 */
	/*
	public void setUser(IUser user) {	
		ServletActionContext.getRequest().getSession().setAttribute(USER_KEY,
				user);
	}
	*/

	/**
	 * 注销登录用户 (在退出登录Action中调用)
	 */
	/*
	public void removeUser() {
		IUser user = getCurrentUser();
		try {
			if (user != null) {
				ServletActionContext.getRequest().getSession().setAttribute(
						USER_KEY, null);
			}
		} catch (Exception e) {
		if(logger.isErrorEnabled()){
				logger.error("error,remove user UserName=[" + user.getUserName()
					+ "]", e);
			}
			
		}
	}
	*/

	public Locale getUserLocale() {
		Locale locale = (Locale) ActionContext.getContext().getSession().get(LOCALE_KEY);
		if (locale == null) {
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie != null
							&& "locale".equalsIgnoreCase(cookie.getName())
							&& cookie.getValue() != null
							&& cookie.getValue().length() > 0) {
						String[] tokens = cookie.getValue().split("_");
						if (tokens != null) {
							if (tokens.length >= 3) {
								locale = new Locale(tokens[0], tokens[1], tokens[2]);
							} else if (tokens.length >= 2) {
								locale = new Locale(tokens[0], tokens[1]);
							} else if (tokens.length >= 1) {
								locale = new Locale(tokens[0]);
							}
						}
						ActionContext.getContext().getSession().put(LOCALE_KEY, locale);
						break;
					}
				}
			}
		}
		return locale;
	}

	public void setUserLocale(Locale locale) {
		if (isChange(getUserLocale(), locale)) {
			ActionContext.getContext().getSession().put(LOCALE_KEY, locale);
			Cookie cookie = new Cookie("locale", locale.toString());
			//cookie.setMaxAge(60000); // seconds
			cookie.setPath("/");
			ServletActionContext.getResponse().addCookie(cookie);
		}
	}

	// 判断用户ID是否改变
	private boolean isChange(Object oldObj, Object newObj) {
		if (oldObj == null && newObj == null)
			return false;
		if (oldObj == null || newObj == null)
			return true;
		return !oldObj.equals(newObj);
	}

	/**
	 * 只能返回 zh_CN/zh_TW/en_US，其他的都作为 zh_CN返回
	 * @return
	 */
	public Locale getLocale() {
		return Locale.PRC;
		/*
		Locale defaultLocale = Locale.PRC;
		
		Locale locale = getUserLocale();
		if (locale == null) {

			locale = ServletActionContext.getRequest().getLocale();
			
			if (locale == null)
				locale = defaultLocale;
			else if (!Locale.PRC.equals(locale)
					&& !Locale.TAIWAN.equals(locale)
					&& !Locale.US.equals(locale))
				locale = defaultLocale;
		}
		
		return locale;
		*/
	}
	
	public String getCookieValue(String cookieName) {
		
		try {
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(cookieName)) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return "";
	}
}
