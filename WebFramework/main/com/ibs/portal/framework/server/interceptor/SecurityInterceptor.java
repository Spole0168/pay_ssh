package com.ibs.portal.framework.server.interceptor;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BaseRuntimeException;
import com.ibs.portal.framework.server.security.UserSecurity;
import com.ibs.portal.framework.util.StringUtils;
import com.ibs.portal.integration.sso.Application;
import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.Token;
import com.ibs.portal.integration.sso.TokenValidationException;
import com.ibs.portal.integration.sso.access.TokenValidateClient;
import com.ibs.portal.integration.sso.generate.TokenUrlRewriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.config.entities.ResultConfig;

/**
 * 安全拦截器
 * <p/>
 * 1. 检查是否登录, 未登录返回登录页面<br/>
 * 2. 检查功能URL是否注册, 未注册直接通过<br/>
 * 
 * 
 * 2010/01/18  
 * 同一台机器，多次用不同用户登录系统时发生菜单互串的问题。
 * 问题重现过程：
 * 1. 打开浏览器，使用admin登录系统http://[uat]/mdm
 * 2. 不关闭浏览器，再打开一个，访问另外一个系统：http://[uat]/rut，这时单点登录生效，会以刚才登录成功的用户名称登录rut；
 * 3. 在第二个窗口注销用户，使用另外一个用户90001001重新登录到http://[uat]/rut，
 * 4. 在第二个窗口的菜单中点击【路由管理】->【线路管理】->【线路管理】；
 * 5. 此时可能显示的是第一个窗口登录成功的用户，也就是admin，包括菜单，也变成admin用户可见的菜单。
 *    正确的情况下，应该还是显示第二个窗口重新登录成功的用户，也就是90001001；
 * 
 * 出现这个bug的原因是因为系统菜单是从当前用户中取得的，后一次的登录虽然是注销掉了原先的用户，
 * 但是并没有销毁服务器端的session（因为是在不同的窗口下执行注销操作，sessionid不同，所以不能销毁服务端的第一次的登录）；
 * 这样服务端仍然保留了第一次成功登录的session，所以在getCurrentUser时就可能取错当前的用户，
 * 从而发生菜单互串的bug；
 * 
 * 解决此bug的方法是：不再从session中取得当前用户，而改为从cookie中取得当前用户；
 * 
 * @author:
 * 
 */
public class SecurityInterceptor extends BaseInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(SecurityInterceptor.class);

	private static final boolean enable = true; // 设置该拦截器是否生效，便于开发阶段调试

	private static final String FORBIDDEN = "forbidden";

	private static final String LOGIN = "login";

	private static final String AJAX = "ajax";

	private static final String JSON_LOGIN = "jsonlogin";
	
	private static final String INIT_PASSWORD = "initPassword";
	
	private final TokenValidateClient tvc = new TokenValidateClient();

	/**
	 * 
	 */
	private static final long serialVersionUID = -6062142628871472627L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (enable) {
			ActionContext actionContext = invocation.getInvocationContext();  
			HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
			HttpServletResponse response= (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE); 
			HttpSession session = request.getSession();
			
			// 获取参数
			String actionName = invocation.getProxy().getActionName();
			String namespace = invocation.getProxy().getNamespace();
			String actionUrl = null;
			if ("/".equals(namespace))
				actionUrl = "/" + actionName + ".action";
			else
				actionUrl = namespace + "/" + actionName + ".action";


			// Object action = invocation.getAction();
			if (logger.isDebugEnabled())
				logger.debug(" Current entry URL is [" + actionUrl + "]");

			// 检查当前模块功能是否受控
			if (!isChecked(actionUrl))
				return invocation.invoke();

			// 检查当前用户是否登录
			if (!isLogin(invocation)) {
				if(logger.isInfoEnabled()){
					logger.info(" [LOGIN] begin enter in tokens parse and validation");
				}
				
				Cookie[] cookies = request.getCookies();
				String strToken="";
				if(cookies!=null){
					for(int i=0;i<cookies.length;i++){
						if(cookies[i].getName().equals(ConfigFactory.TOKEN_HEADER_KEY)){
							strToken = cookies[i].getValue();
							break;
						} 
					}
				}
				if (logger.isDebugEnabled())
					logger.debug("[from sending]strToken : " + strToken);
				
				//boolean validated = validateToken(strToken, session, response);
				boolean validated = validateToken(strToken, session);
				
				if(!validated){
					if(logger.isWarnEnabled()){
						logger.warn(" [LOGIN] User not login or session is timeout");
					}
					//logger.warn(" [LOGIN] User not login or session is timeout");
					if (session != null)
						session.invalidate();
	
					if (isAjaxRequest(invocation))
						return JSON_LOGIN;
					else
						return LOGIN;
				}
			}
			
			// 添加：如果用户密码未修改，则不允许访问任何受管控的资源
			// 判断用户密码过期时间是否为0
//			Object obj = session.getAttribute(UserContext.SESSION_PWD_NEED_CHANGE_FLAG);dd
			IUser currentUser = UserContext.getUserContext().getCurrentUser();
			if(null == currentUser){
				if(logger.isWarnEnabled()){
					logger.warn(" [LOGIN] User not login or session is timeout");
				}
				if (session != null)
					session.invalidate();

				if (isAjaxRequest(invocation))
					return JSON_LOGIN;
				else
					return LOGIN;
			}
			
//			Integer res = currentUser.getPasswordActiveDay();
//			if(null == res || res.intValue() == 0){
//				if(logger.isWarnEnabled()){
//					logger.warn(" [FORBIDDEN] User{"
//							+ UserContext.getUserContext().getUserName()
//							+ "} cann't access this URL{" + actionUrl + "} : password should change firstly");
//				}
//				return INIT_PASSWORD;
//			}

			// 检查当前用户是否有权限访问当前模块功能
			if (!isAccessable(actionUrl)) {
				if(logger.isWarnEnabled()){
					logger.warn(" [FORBIDDEN] User{"
							+ UserContext.getUserContext().getUserName()
							+ "} cann't access this URL{" + actionUrl + "}");
				}
				//logger.warn(" [FORBIDDEN] User{"
					//	+ UserContext.getUserContext().getUserName()
						//+ "} cann't access this URL{" + actionUrl + "}");
				return FORBIDDEN;
			}
		}

		return invocation.invoke();
	}
	
	/**
	 * 老的验证token的方法，发起一个http请求，到已登录的应用中进行验证token；
	 * 
	 * @deprecated
	 * 
	 * @param strToken
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private boolean validateToken(String strToken, HttpSession session, HttpServletResponse response) throws Exception {
		boolean validated = false;
		try {

			if(StringUtils.isNotEmpty(strToken)){
				// 验证令牌
				Token token = Token.valueOf(strToken);
				// 如果未登录, 则检查令牌

				tvc.validate(token);
				
				//SSO token验证通过
				validated=true;
				if (logger.isDebugEnabled())
					logger.debug("success login with user id : " + token.getUserId());
				
				//将用户ID保存在session中
				ConfigFactory.setUserId(session, token);
				
				//WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
				//IMainBiz mainBiz = ctx.getBean("mainBiz");
				
				//重写cookie
				token = TokenUrlRewriter.generate(session);
				
				//创建Cookie
				Cookie cookie = new Cookie(ConfigFactory.TOKEN_HEADER_KEY, token.toString()); 
				cookie.setPath("/");
				//cookie.setMaxAge(30*60);
				
				response.addCookie(cookie);
			}

		} catch (TokenValidationException e) {
			if (logger.isDebugEnabled()){
				logger.debug("[validation] error!!!", e);
				logger.debug("*************** send to client with response code: " + HttpServletResponse.SC_FORBIDDEN);
			}
		} catch (IOException e) {
			if (logger.isDebugEnabled())
				if (logger.isDebugEnabled()){
					logger.debug("[validation] error!!!", e);
					logger.debug("*************** send to client with response code: " + HttpServletResponse.SC_NO_CONTENT);
				}	
		} catch (Exception e) {
			if (logger.isDebugEnabled()){
				logger.debug("[validation] error!!!", e);
				logger.debug("*************** send to client with response code: " + HttpServletResponse.SC_NO_CONTENT);
			}	
		}
		return validated;
	}
	
	/**
	 * 新的验证token的方法，不再通过发起一个http请求到登陆的应用里进行校验，
	 * 取得token中的applicationId，并把本应用中的sso.xml中的application列表作为受信任的应用；
	 * 如果登陆的应用受信任，则单点登陆成功；
	 * 
	 * 【注意】如果存在多个环境，则最好不同环境下的sso.xml中的同一个application名称不一样；
	 * 避免出现不可预料的情况；
	 * 
	 * @param strToken
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private boolean validateToken(String strToken, HttpSession session) throws Exception {
		boolean validated = false;
		
		if ( StringUtils.isNotEmpty(strToken) ) {
			Token token = Token.valueOf(strToken);
			
			if ( token == null ) {
				logger.error("Could not deserialized token: " + strToken);
				return validated;
			}
			
			String applicationId = token.getApplicationId();
			logger.debug("Application Id found in token: " + applicationId);
			Application trustedApp = ConfigFactory.getApplication(applicationId);
			
			if ( logger.isTraceEnabled() ) {
				logger.trace("Trusted applications: ");
				Map trustedApplications = ConfigFactory.getAllApplications();
				
				Iterator iterator = trustedApplications.keySet().iterator();
				while ( iterator.hasNext() ) {
					String applicationid = (String)iterator.next();
					logger.trace("\tApplication Id: " + applicationId);
				}
			}
			if ( trustedApp != null ) {
				logger.debug("Found trusted application: " + applicationId);
				validated = true;
				
				//将用户ID保存在session中
				ConfigFactory.setUserId(session, token);
			} else {
				logger.debug("Is not a trusted application: " + applicationId);
				logger.debug("Please add as trusted application in sso.xml");
			}
		}
		
		return validated;
	}

	// URL是否受控(过滤不需要登录的页面)
	private boolean isChecked(String actionUrl) {
		// TODO 不受控页面应可配置
		if (actionUrl.endsWith("index.action")
				|| actionUrl.endsWith("logout.action")
				|| actionUrl.endsWith("login.action")
				|| actionUrl.endsWith("password.action")
				|| actionUrl.endsWith("initPassword.action")
				|| actionUrl.endsWith("initPasswordPost.action")
				|| actionUrl.endsWith("checkUserPwd.action")
				|| actionUrl.endsWith("modifyPassword.action")
				|| actionUrl.endsWith("modifyPasswordPost.action")
				|| actionUrl.endsWith("toModifyPassword.action"))
			return false;
		return true;
	}

	// 用户是否登录
	/**
	 * 原先判断是否登录的是从Session中取得当前用户的id，
	 * 也就是通过方法invocation.getInvocationContext().getSession().get(UserContext.USER_UID_KEY)来取得用户id；
	 * 这可能引起使用多个用户在同一台机器上登录系统时发生菜单互串的问题；
	 * 
	 * 把取得当前用户的方式改为从cookie中读取；
	 * 
	 * 2010/01/18  
	 */
	private boolean isLogin(ActionInvocation invocation) {
		// return true;
		/*
		IUser user = (IUser) invocation.getInvocationContext().getSession()
				.get(UserContext.USER_KEY);
		if (null != user) {
			UserContext.getUserContext().setUser(user);
			return true;
		} else
			return false;
		*/
		/*
		logger.debug("Wether user has logined or not..");
		String userId = (String)invocation.getInvocationContext().getSession().get(UserContext.USER_UID_KEY);
		if(userId != null) {
			logger.debug("Already login: " + userId);
			UserContext.getUserContext().setUserId(userId);
			return true;
		}
		else {
			logger.debug("Not logined.");
			return false;
		}
		*/
		try {
			IUser currentUser = UserContext.getUserContext().getCurrentUser();
			if ( currentUser != null ) {
				logger.debug("CurrentUser : " + currentUser);
				logger.debug(currentUser.getUserId());
			}
			return (currentUser != null);
		} catch ( Exception e ) {
			return false;
		}
	}

	// URL是否可访问
	private boolean isAccessable(String actionUrl) {
		return UserSecurity.hasPrivilege(UserContext.getUserContext()
				.getCurrentUser(), actionUrl, UserSecurity.URL_SECURITY_TYPE);
	}

	// 是否ajax请求
	private boolean isAjaxRequest(ActionInvocation invocation) {
		Map<String, ResultConfig> resultConfigs = invocation.getProxy()
				.getConfig().getResults();

		if (resultConfigs.containsKey(AJAX)) {
			if (logger.isDebugEnabled())
				logger.debug(" Current entry is ajax request ");

			Object action = invocation.getAction();
			if (action instanceof ValidationAware) {
				if (logger.isDebugEnabled())
					logger.debug(" return error message ");

				ValidationAware validationAwareAction = (ValidationAware) action;
				validationAwareAction.addActionError("未登录系统或已过有效期, 请登录系统...");
			}

			return true;
		}

		return false;

	}
}
