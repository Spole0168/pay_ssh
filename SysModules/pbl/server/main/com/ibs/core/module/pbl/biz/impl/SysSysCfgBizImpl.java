/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.pbl.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.pbl.biz.ISysSysCfgBiz;
import com.ibs.core.module.pbl.dao.ISysSysCfgDao;
import com.ibs.core.module.pbl.domain.SysSysCfg;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @create by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SYS_CFG
 */
public class SysSysCfgBizImpl extends BaseBiz implements ISysSysCfgBiz {

	private ISysSysCfgDao sysSysCfgDao;
	private IExcelService excelService;
	
	@Override
	public IPage<SysSysCfg> findSysSysCfgByPage(QueryPage queryPage) {
		logger.info("entering action::SysSysCfgBizImpl.findSysSysCfgByPage()...");
		return sysSysCfgDao.findSysSysCfgByPage(queryPage);
	}

	@Override
	public SysSysCfg getSysSysCfgById(String id) {
		logger.info("entering action::SysSysCfgBizImpl.getSysSysCfgById()...");
		return sysSysCfgDao.load(id);
	}

	@Override
	public SysSysCfg saveSysSysCfg(SysSysCfg sysSysCfg) {
		logger.info("entering action::SysSysCfgBizImpl.saveSysSysCfg()...");
		sysSysCfg.setId(null);
		sysSysCfgDao.saveOrUpdate(sysSysCfg);
		return sysSysCfg;
	}

	@Override
	public SysSysCfg updateSysSysCfg(SysSysCfg sysSysCfg) {
		logger.info("entering action::SysSysCfgBizImpl.updateSysSysCfg()...");
		sysSysCfgDao.saveOrUpdate(sysSysCfg);
		return sysSysCfg;
	}

	@Override
	public void exportSysSysCfg(ExportSetting exportSetting) {
		logger.info("entering action::SysSysCfgBizImpl.exportSysSysCfg()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<SysSysCfg> sysSysCfgPage = (Page<SysSysCfg>) sysSysCfgDao
				.findSysSysCfgByPage(exportSetting);

		excelService.exportToFile(sysSysCfgPage.getRows(), exportSetting);
	}

	public ISysSysCfgDao getSysSysCfgDao() {
		logger.info("entering action::SysSysCfgBizImpl.getSysSysCfgDao()...");
		return sysSysCfgDao;
	}

	public void setSysSysCfgDao(ISysSysCfgDao sysSysCfgDao) {
		logger.info("entering action::SysSysCfgBizImpl.setSysSysCfgDao()...");
		this.sysSysCfgDao = sysSysCfgDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::SysSysCfgBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::SysSysCfgBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
