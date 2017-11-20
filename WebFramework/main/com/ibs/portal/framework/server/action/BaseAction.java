package com.ibs.portal.framework.server.action;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.common.Constants;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.i18n.IMessageSource;
import com.ibs.portal.framework.server.i18n.MessageSourceContainer;
import com.ibs.portal.framework.server.metadata.SystemMenu;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action基础实现
 * <p>
 * (注:不应在此类中放入getRequest(),getSession()等函数, 防止有人越过框架直接操作Servlet API)
 * 
 * @author : 
 * 
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, CookiesAware,
		ParameterAware, SessionAware, ApplicationAware {
	
	public static final String AJAX_RETURN_TYPE = "ajax";
	public static final String FAILURE = "failure";
	public static final String ERROR = "error";
	public static final String DONE = "done";
	public static final String EXCEPTION = "exception";
	public static final String SYNC_EXPORT = "syncExport";
	public static final String ASYNC_EXPORT = "asyncExport";
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String,Object> session;
	protected Map<String,Object> application;
	protected Map<String,String[]> parameters;
	protected Map<String,String> cookiesMap;
	protected String message;
	protected SystemMenu systemMenu;
	protected String userOrg;//用户组织机构
	protected String currentSit;
	protected String commonVersion;	// 静态资源版本
	protected Boolean notifyEnabled;
	
	public String getCurrentSit() {
		
		List<SystemMenu> currentSits  = (List<SystemMenu>) ServletActionContext.getRequest().getAttribute("currentMenuList");
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("<a href='" + ServletActionContext.getRequest().getContextPath() + "/main.action" + "'>首页</a>");
		
		if (currentSits == null)
			return sBuffer.toString();
		
		Iterator<SystemMenu> iter = currentSits.iterator();
		while(iter.hasNext()) {
			SystemMenu sMenu = (SystemMenu) iter.next();
			sBuffer.append("<span>").append(sMenu.getTitle()).append("</span>");
		}
		
		currentSit = sBuffer.toString();
		
		return currentSit;
	}

	public String getCommonVersion() {
		
		ICache propCache = CacheManager.getInstance().getCache(Constants.PROPERTY_CACHE);
		Map<String, String> maps = (Map<String, String>) propCache.getData(Constants.PROPERTY_CACHE_KEY);
		commonVersion = maps.get("pbl.web.commonVersion");
		
		return commonVersion;
	}

	public Boolean getNotifyEnabled() {
		
		ICache propCache = CacheManager.getInstance().getCache(Constants.PROPERTY_CACHE);
		Map<String, String> maps = (Map<String, String>) propCache.getData(Constants.PROPERTY_CACHE_KEY);
		String str = maps.get("pbl.notify.enable");
		if (StringUtils.isNotEmpty(str) && "true".equalsIgnoreCase(str))
			return true;
		
		return false;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	public SystemMenu getSystemMenu() {
		String menuIdString = menuId;
		SystemMenu sysmenu = null;
		if(null != menuIdString){
			for (Iterator iterator = getMenus().iterator(); iterator.hasNext();) {
				SystemMenu menu = (SystemMenu) iterator.next();
				sysmenu = menu.getSubMenu(menu.getSubMenus(), menuIdString);
				if(sysmenu != null){
					break;
				}
			}
		}
		if(sysmenu == null){
			sysmenu = new SystemMenu();
			sysmenu.setTitle("欢迎进入IBS支付管理平台");
		}
		request.setAttribute("menu", sysmenu);
		return sysmenu;
	}

	public void setSystemMenu(SystemMenu systemMenu) {
		this.systemMenu = systemMenu;
	}

	//当前用户菜单号
	protected String menuId;
	
	//当前用户菜单
	protected List<SystemMenu> menus;
	
	protected String displayName;

	public IUser getCurrentUser() {
		if(request == null) {
			return null;
		}
		
		if(request.getSession() == null) {
			return null;
		}
		
		return (IUser) UserContext.getUserContext().getCurrentUser();
	}
	/**
	 * 通过传入的userId，加载用户Cache
	 * @author huolang 2011-
	 * @param userId
	 * @return
	 */
	public IUser getUserCache(String userId){
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		Object obj = UserContext.getUserContext().getUserCached(userId);
		if (null == obj) {
			if(logger.isDebugEnabled()){
				logger.debug(" can not found User from Cache by  UserID [ " + userId
						+ "]");
			}
		//	logger.debug(" can not found User from Cache by  UserID [ " + userId
		//			+ "]");
			return null;
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
	 * 获取当前模块国际化信息源 (便捷工具函数)
	 *
	 * @return 当前模块国际化信息源
	 */
	protected IMessageSource getMessageSource() {
		Locale locale = UserContext.getUserContext().getLocale();
		return MessageSourceContainer.getInstance().getMessageSource(locale);
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public void setCookiesMap(Map<String, String> cookiesMap) {
		this.cookiesMap = cookiesMap;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void beforeInvoke(String method) {
	}
	
	public void afterInvoke(String method) {
		
	}

	public String getMenuId() {
		if(StringUtils.isEmpty(menuId)){
			IUser suser = getCurrentUser();
			this.menuId = suser.getMenuId();
		}
		return menuId;
	}

	public void setMenuId(String menuId) {
		IUser suser = getCurrentUser();
		this.menuId = menuId;
		suser.setMenuId(menuId);
	}

	public List<SystemMenu> getMenus() {
		IUser suser = getCurrentUser();
		this.menus = suser.getMenus();
		return menus;
	}

	public void setMenus(List<SystemMenu> menus) {
		IUser suser = getCurrentUser();
		this.menus = suser.getMenus();
	}
	
	public String getDisplayName() {
		if (StringUtils.isEmpty(displayName)) {
			IUser suser = getCurrentUser();
			this.displayName = suser.getDisplayName();
		}
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
