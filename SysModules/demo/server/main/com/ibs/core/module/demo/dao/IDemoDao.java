package com.ibs.core.module.demo.dao;

import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IDemoDao {

	public IPage<Demo> findDemoByPage(QueryPage queryPage);
	
	public void saveOrUpdate(Demo demo);

	public Demo load(String id);
	
}
