package com.ibs.portal.framework.server.interceptor;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;

public class ErrorInterceptor extends BaseInterceptor {

	protected static Logger logger = LoggerFactory
			.getLogger(ErrorInterceptor.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5180669222013525626L;

	private static final String ERROR = "error";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if (action instanceof ValidationAware) {
			ValidationAware validationAwareAction = (ValidationAware) action;
			if (validationAwareAction.hasErrors()) {
				Iterator<String> iter = validationAwareAction.getActionErrors().iterator();
				while (iter.hasNext()) {
					logger.error(iter.next());
				}
				logger.error(" [FORBIDDEN] Error happened when access this URL  " + validationAwareAction.getActionErrors().toArray());
				return ERROR;
			}
		}
		try {
			return invocation.invoke();
		} catch (Exception e) {
			((ValidationAware) action).addActionError(e.getMessage());
			logger.error(e.getMessage(), e);
			return ERROR;
		}
	}
}
