package com.ibs.portal.framework.server.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.action.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;

/**
 *
 * 用于新增、修改动作，对EditBaseAction进行处理,设置需要的变量
 * @author zjblue
 *
 */
public class CommonInterceptor extends BaseInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2257249682049481100L;
	
	protected static Logger logger = LoggerFactory
			.getLogger(CommonInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		
		String method = invocation.getProxy().getMethod();
		//提前执行
		if (action instanceof BaseAction) {
			BaseAction baseAction = (BaseAction)action;
			baseAction.beforeInvoke(method);
		}
	
		String result = invocation.invoke();

		//后面执行
		if (action instanceof BaseAction) {
			BaseAction baseAction = (BaseAction)action;
			baseAction.afterInvoke(method);
		}
		
		return result;
	}
	
 
}
