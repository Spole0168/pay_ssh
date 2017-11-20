package com.ibs.portal.framework.server.deploy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class MappingAutowiring implements BeanPostProcessor {
	private static Logger log = LoggerFactory
			.getLogger(MappingAutowiring.class);

	private String[] mappingResources;

	public String[] getMappingResources() {
		return mappingResources;
	}

	public void setMappingResources(String[] mappingResources) {
		this.mappingResources = mappingResources;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof LocalSessionFactoryBean) {
			log.debug("============ MappingAutowiring: " + mappingResources);
			((LocalSessionFactoryBean) bean)
					.setMappingResources(mappingResources);
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
}
