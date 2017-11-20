package com.ibs.portal.framework.server.metadata;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class PageCache {

	
	private Map<String, Object> pageProperties = new HashMap<String, Object>(0);

	
	public PageCache() {

	}

	public Map<String, Object> getPageProperties() {
		return pageProperties;
	}


	public void setPageProperties(Map<String, Object> pageProperties) {
		this.pageProperties = pageProperties;
	}

	public void putPageProperties(String name, Object value){
		pageProperties.put(name, value);
	}
	
	public Object getPageProperties(String name){
		return pageProperties.get(name);
	}
	


}
