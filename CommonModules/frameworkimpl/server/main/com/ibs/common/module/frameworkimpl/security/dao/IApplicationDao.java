package com.ibs.common.module.frameworkimpl.security.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IApplicationDao {
	
	public static Map<String, String> APP_MAP = new HashMap<String, String>();
	
	public Application findByName(String appName);
	
	public Application load(String id);
	
	public void delete(String id);
	
	public List<Application> findAll();
	
	public IPage<Application> findPageBy(QueryPage queryPage)
			throws DaoException;

	public Application loadCascade(final String id);

	public void saveOrUpdate(Application app);
	
	public void getAppMap();
}
