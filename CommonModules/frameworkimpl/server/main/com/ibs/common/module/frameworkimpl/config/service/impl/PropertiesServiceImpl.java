package com.ibs.common.module.frameworkimpl.config.service.impl;

import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import com.ibs.common.module.frameworkimpl.common.Constants;
import com.ibs.common.module.frameworkimpl.config.service.IPropertiesService;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.service.BaseService;

public class PropertiesServiceImpl extends BaseService implements IPropertiesService {

	public String getPropertyValue(String key) {
		logger.trace("entering service...");
		
		if (StringUtils.isEmpty(key))
			return null;
		
		ICache cache = this.getPropertyCache();
		@SuppressWarnings("unchecked")
		Map<String, String> maps = (Map<String, String>) cache.getData(Constants.PROPERTY_CACHE_KEY);
		return maps.get(key);
		
	}
	
	private ICache getPropertyCache() {
		return CacheManager.getInstance().getCache(Constants.PROPERTY_CACHE);
	}

}
