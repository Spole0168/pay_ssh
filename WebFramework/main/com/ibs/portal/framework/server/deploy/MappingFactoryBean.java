package com.ibs.portal.framework.server.deploy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.ibs.portal.framework.server.context.ApplicationContext;

public class MappingFactoryBean implements FactoryBean {
	private static Logger log = LoggerFactory
			.getLogger(MappingFactoryBean.class);

	public Object getObject() throws Exception {
		List<String> configs = ApplicationContext.getContext().getApplication()
				.getMappingConfigs();
		log.debug("************* hibernate configs: " + configs);
		return configs.toArray(new String[configs.size()]);
	}

	public Class<?> getObjectType() {
		return String[].class;
	}

	public boolean isSingleton() {
		return true;
	}
}
