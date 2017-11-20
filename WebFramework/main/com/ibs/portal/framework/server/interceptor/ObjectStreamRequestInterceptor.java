package com.ibs.portal.framework.server.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.action.CustomHttpConstants;
import com.ibs.portal.framework.server.action.ObjectStreamBaseAction;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * XML InputStream Request
 * 
 * @author luoyue
 * 
 */
public class ObjectStreamRequestInterceptor extends BaseInterceptor implements
		CustomHttpConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1112988164639522375L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		Object action = invocation.getAction();
		if (action instanceof ObjectStreamBaseAction) {
			ObjectStreamBaseAction osAction = (ObjectStreamBaseAction) action;
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put(X_REQUEST_TYPE, request.getHeader(X_REQUEST_TYPE));
			parameters.put(X_REQUEST_METHOD, request
					.getHeader(X_REQUEST_METHOD));
			parameters.put(X_REQUEST_PARAM, request.getHeader(X_REQUEST_PARAM));
			parameters.put(X_REQUEST_USER, request.getHeader(X_REQUEST_USER));
			parameters.put(X_REQUEST_AUTH, request.getHeader(X_REQUEST_AUTH));

			osAction.setRequestData(request.getInputStream(), parameters);
		}
		return invocation.invoke();
	}

}
