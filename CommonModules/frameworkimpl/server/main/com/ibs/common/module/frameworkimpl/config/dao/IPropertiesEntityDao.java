package com.ibs.common.module.frameworkimpl.config.dao;

import java.util.HashMap;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.config.domain.Properties;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IPropertiesEntityDao {

	public HashMap<String, String> findAllValid();
	
	public Page<Properties> findConfigByPage(QueryPage queryPage,
			Map<String, String> searchFields);
	
	public String saveProperties(Properties properties);

	public void updateProperties(Properties properties);
	
	public Properties getPropertiesById(String id);
	
	public Properties getPropertiesByKey(String key);
}
