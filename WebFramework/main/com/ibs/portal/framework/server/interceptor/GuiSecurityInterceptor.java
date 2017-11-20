package com.ibs.portal.framework.server.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
import com.ibs.portal.integration.sso.generate.TokenUtils;
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
 * @author:
 * 
 */
public class GuiSecurityInterceptor extends BaseInterceptor {

	private static final Log logger = LogFactory
			.getLog(GuiSecurityInterceptor.class);

	private static final boolean enable = true; // 设置该拦截器是否生效，便于开发阶段调试

	private static final String FORBIDDEN = "forbidden";

	private static final String LOGIN = "login";

	private static final String AJAX = "ajax";

	private static final String JSON_LOGIN = "jsonlogin";
	
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
			String actionUrl = namespace + "/" + actionName + ".action";


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
				logger.debug("[from sending]strToken : " + strToken);
				
				//boolean validated = validateToken(strToken, session, response);
				boolean validated = validateToken(strToken, session);
				
				//用户没有登录，返回SC_UNAUTHORIZED
				if(!validated){
					if(logger.isWarnEnabled()){
						logger.warn(" [LOGIN] User not login or session is timeout");
					}
	
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return FORBIDDEN;
				}
			}

			// 检查当前用户是否有权限访问当前模块功能，无权限返回SC_FORBIDDEN
			if (!isAccessable(actionUrl)) {
				if(logger.isWarnEnabled()){
					logger.warn(" [FORBIDDEN] User{"
							+ UserContext.getUserContext().getUserName()
							+ "} cann't access this URL{" + actionUrl + "}");
				}

				response.sendError(HttpServletResponse.SC_FORBIDDEN);
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
			
			Application trustedApp = ConfigFactory.getApplication(applicationId);
			
			if ( trustedApp != null ) {
				logger.debug("Found trusted application: " + applicationId);
				validated = true;
				
				//将用户ID保存在session中
				ConfigFactory.setUserId(session, token);
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
				|| actionUrl.endsWith("modifyPassword.action")
				|| actionUrl.endsWith("toModifyPassword.action")
				|| actionUrl.endsWith("testConnection.action"))
			return false;
		return true;
	}

	// 用户是否登录
	private boolean isLogin(ActionInvocation invocation) {
		/*
		String userId = (String)invocation.getInvocationContext().getSession().get(UserContext.USER_UID_KEY);
		if(userId != null) {
			UserContext.getUserContext().setUserId(userId);
			return true;
		}
		else {
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


}
