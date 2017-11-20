package com.ibs.common.module.frameworkimpl.cache;

import java.util.Map;

import com.ibs.common.module.frameworkimpl.config.dao.IPropertiesEntityDao;
import com.ibs.portal.framework.server.cache.ttl.TTLBaseCacheDataProvider;

/**
 * menu name cache provider
 * @author 
 * $Id: MacCacheProvider.java 39733 2011-06-03 11:38:08Z  $
 */
public class PropertiesCacheProvider extends TTLBaseCacheDataProvider {

	private IPropertiesEntityDao propertiesEntityDao;

	public Object getData(Object key) {
		if (key == null)
			return null;

		logger.trace("loading " + key + " from db begin...");
		
		Map<String, String> properties = propertiesEntityDao.findAllValid();
		
		return properties;
	}

	public void setPropertiesEntityDao(IPropertiesEntityDao propertiesEntityDao) {
		this.propertiesEntityDao = propertiesEntityDao;
	}


}
