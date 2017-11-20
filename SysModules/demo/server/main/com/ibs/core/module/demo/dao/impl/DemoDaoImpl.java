package com.ibs.core.module.demo.dao.impl;

import com.ibs.core.module.demo.dao.IDemoDao;
import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class DemoDaoImpl extends BaseEntityDao<Demo> implements
		IDemoDao {

	public IPage<Demo> findDemoByPage(QueryPage queryPage) {
		return super.findPageBy(queryPage);
	}

	public Demo load(String id) {
		return super.load(id);
	}

	public void saveOrUpdate(Demo demo) {
		super.saveOrUpdate(demo);
	}

}
