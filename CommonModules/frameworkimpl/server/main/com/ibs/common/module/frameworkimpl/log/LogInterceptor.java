package com.ibs.common.module.frameworkimpl.log;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ibs.common.module.frameworkimpl.log.common.Constants;
import com.ibs.common.module.frameworkimpl.log.domain.ActionLog;
import com.ibs.common.module.frameworkimpl.log.log4j.AsyncLoggerFactory;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.util.StringUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Action Log - Struts2 Interceptor
 * @author 
 * $Id: LogInterceptor.java 41168 2011-06-24 09:04:31Z  $
 */
public class LogInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 7347538460989516795L;
	private static final Log log = LogFactory.getLog(LogInterceptor.class);
	private String serverHostName;
	private String serverAddress;
	
	public LogInterceptor() {
		try {
			this.serverHostName = InetAddress.getLocalHost().getHostName();
			this.serverAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		log.trace("entering doIntercept ... ");

		ActionProxy proxy = invocation.getProxy();
		BaseAction action = (BaseAction) invocation.getAction();

		String result = ActionSupport.NONE;

		HttpServletRequest request = ServletActionContext.getRequest();
		ActionLog actionLog = null;

		String actionId = StringUtils.getUUID();

		request.setAttribute(Constants.LOG_ACTION_ATTRIBUTE_NAME, actionId);

		actionLog = new ActionLog();
		actionLog.setRequestTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		actionLog.setId(actionId);
		actionLog.setServerName(this.serverHostName);
		actionLog.setServerAddr(this.serverAddress);
		actionLog.setSessionId(request.getSession().getId());
//		actionLog.setAppId(AppSystem.PORTAL);
		actionLog.setLocale(request.getLocale().toString());
		
		actionLog.setClientAddr(request.getRemoteAddr());
		actionLog.setClientUserAgent(request.getHeader("user-agent"));
		actionLog.setClientMac(UserContext.getUserContext().getCookieValue("mac"));
		
		try {
			String url = request.getRequestURL().toString();
			if (request.getQueryString() != null)  
				url += "?" + request.getQueryString();
			
			actionLog.setUrl(url);
			
			actionLog.setMethod(request.getMethod());
			
			JSONObject json = JSONObject.fromObject(request.getParameterMap());
			String params = json.toString();

			params = URLDecoder.decode(params, "UTF-8");
			if (params.length() > 2000)
				actionLog.setParams(params.substring(0, 2000));
			else
				actionLog.setParams(params);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		actionLog.setActionClass(action.getClass().getName());
		actionLog.setActionMethod(proxy.getMethod());

		try {
			result = invocation.invoke();

			actionLog.setMenuId(action.getMenuId());
			actionLog.setActionResult(result);
			actionLog.setIsException(Boolean.FALSE);
		} catch (Exception e) {
//			log.error(e.getMessage(), e);
			actionLog.setMenuId(action.getMenuId());
			actionLog.setIsException(Boolean.TRUE);

			throw e;
		} finally {
			IUser user = UserContext.getUserContext().getCurrentUser();
			if (user != null) {
				actionLog.setUserCode(user.getUserCode());
				actionLog.setUserName(user.getDisplayName());
//				actionLog.setOrgCode(user.getOrgCode());
//				actionLog.setOrgName(user.getOrgName());
			}
			
			actionLog.setResponseTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			AsyncLoggerFactory.instance().saveActionLog(actionLog);
		}

		log.trace("exiting doIntercept ... ");
		return result;
	}

}
