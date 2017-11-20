package com.ibs.core.module.demo.biz;

import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IDemoBiz {

	public IPage<Demo> findDemoByPage(QueryPage queryPage);

	public Demo getDemoById(String id);
	
	public Demo saveDemo(Demo object);
	
	public Demo updateDemo(Demo object);
	
	public void exportDemo(ExportSetting exportSetting);

}
