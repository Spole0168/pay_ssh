package com.ibs.core.test.module.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextHelper {
	public static ApplicationContext context = null;

	public static ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"classpath:resource/testContext.xml");
		}
		return context;
	}

	public static Object getBean(String name) {
		if (name == null || "".equals(name.trim())) {
			return null;
		}

		Object obj = getContext().getBean(name);

		return obj;
	}

}
