package com.ibs.common.module.frameworkimpl.security.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.common.module.frameworkimpl.security.dao.IApplicationDao;
import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;


public class ApplicationDao extends BaseEntityDao<Application> implements IApplicationDao {

	public void delete(String id) {
		super.remove(id);
	}

	public Application load(String id) {
		return super.load(id);
	}
	
	public Application findByName(String appName) {
		List<Application> applicationList = super.findByValue("from Application where appName = ? ", appName);
		
		if(applicationList != null && applicationList.size() != 0) {
			return applicationList.get(0);
		}
		
		return null;
	}
	
	public String save(Application application) {
		return (String)super.save(application);
	}

	public IPage<Application> findPageBy(QueryPage queryPage) throws DaoException{
		return super.findPageBy(queryPage);
	}
	
	public Application loadCascade(final String id) {
		return (Application) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Application app = (Application) session.load(
								Application.class, id);
						Hibernate.initialize(app.getIncludedMenus());
						Hibernate.initialize(app.getIncludedGuiMenus());
						Hibernate.initialize(app.getIncludedOpers());
						return app;
					}
				});
	}
	
	public void saveOrUpdate(Application app) {
		super.saveOrUpdate(app);
	}
	/**
	 * 获取全部的应用，返回应用Map<id, name>
	 * @return
	 */
	public void getAppMap() {
		if(APP_MAP.size() == 0 ){
			List<Application> apps = super.findAll();
			if (null != apps && apps.size() > 0) {
				for (Application app : apps) {
					if (null != app) {
						APP_MAP.put(app.getId(), app.getAppName());
					}
				}
			}
		}
	}
}
