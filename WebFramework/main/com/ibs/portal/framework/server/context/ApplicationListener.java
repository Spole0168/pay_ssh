package com.ibs.portal.framework.server.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ApplicationListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent sce) {
		ApplicationContext.destroy(sce.getServletContext());
	}

	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext.init(sce.getServletContext());
	}
}
