package com.ibs.core.module.demo.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.demo.biz.IDemoBiz;
import com.ibs.core.module.demo.dao.IDemoDao;
import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class DemoBizImpl extends BaseBiz implements IDemoBiz {

	private IDemoDao demoDao;
	private IExcelService excelService;
	
	@Override
	public IPage<Demo> findDemoByPage(QueryPage queryPage) {
		return demoDao.findDemoByPage(queryPage);
	}

	@Override
	public Demo getDemoById(String id) {
		return demoDao.load(id);
	}

	@Override
	public Demo saveDemo(Demo demo) {
		demo.setId(null);
		demoDao.saveOrUpdate(demo);
		return demo;
	}

	@Override
	public Demo updateDemo(Demo demo) {
		demoDao.saveOrUpdate(demo);
		return demo;
	}

	@Override
	public void exportDemo(ExportSetting exportSetting) {
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<Demo> demoPage = (Page<Demo>) demoDao
				.findDemoByPage(exportSetting);

		excelService.exportToFile(demoPage.getRows(), exportSetting);
	}

	public IDemoDao getDemoDao() {
		return demoDao;
	}

	public void setDemoDao(IDemoDao demoDao) {
		this.demoDao = demoDao;
	}

	public IExcelService getExcelService() {
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		this.excelService = excelService;
	}

}
