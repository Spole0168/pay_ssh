package com.ibs.portal.framework.server.context;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 获取Spring对象的公共方法
 * 
 * @author luoyue
 *
 */
public class BeanHolder {
	public static Object getBean(String beanId) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ApplicationContext
						.getContext().getServletContext());
		return ctx.getBean(beanId);
	}
}
