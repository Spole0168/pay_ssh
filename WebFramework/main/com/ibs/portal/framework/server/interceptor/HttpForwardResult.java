package com.ibs.portal.framework.server.interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;

public class HttpForwardResult extends BaseResult{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2721868030117955986L;

	protected void doExecute(String location, ActionInvocation invocation)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation
		.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html; charset=utf-8");
		ServletOutputStream out = response.getOutputStream();
		if (location != null && ! location.startsWith("/")) {
			location = "/" + location;
		}
		String context = request.getContextPath();
		if (context != null && ! "/".equals(context)) {
			location = context + location;
		}
		out.print("<html><body><script type='text/javascript'>window.location.href='" + location + "';</script></body></html>");
		out.flush();
	}

}
