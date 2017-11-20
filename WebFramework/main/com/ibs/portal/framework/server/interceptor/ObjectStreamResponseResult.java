package com.ibs.portal.framework.server.interceptor;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.action.ObjectStreamBaseAction;
import com.opensymphony.xwork2.ActionInvocation;

public class ObjectStreamResponseResult extends BaseResult {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4070689819501367970L;

	@Override
	protected void doExecute(String location, ActionInvocation invocation)
			throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		Object action = invocation.getAction();
		response.setContentType("application/x-serialization;charset=UTF-8");

		if (action instanceof ObjectStreamBaseAction) {
			ObjectStreamBaseAction osAction = (ObjectStreamBaseAction) action;

			Map<String, String> responseParameters = osAction
					.getResponseParameters();

			if (null != responseParameters && !responseParameters.isEmpty()) {
				Set<Entry<String, String>> se = responseParameters.entrySet();
				for (Entry<String, String> e : se) {
					response.addHeader(e.getKey(), e.getValue());
				}
			}

			if (osAction.hasResponseData()) {
				osAction.sendResponseData(response.getOutputStream());
			}
		}

	}

}
