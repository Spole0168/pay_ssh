/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.violationRecord.biz.ISysEmailHisBiz;
import com.ibs.core.module.violationRecord.dao.ISysEmailHisDao;
import com.ibs.core.module.violationRecord.domain.SysEmailHis;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_EMAIL_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysEmailHisBizImpl extends BaseBiz implements ISysEmailHisBiz {

	private ISysEmailHisDao sysEmailHisDao;
	private IExcelService excelService;
	
	@Override
	public IPage<SysEmailHis> findSysEmailHisByPage(QueryPage queryPage) {
		logger.info("entering action::SysEmailHisBizImpl.findSysEmailHisByPage()...");
		return sysEmailHisDao.findSysEmailHisByPage(queryPage);
	}

	@Override
	public SysEmailHis getSysEmailHisById(String id) {
		logger.info("entering action::SysEmailHisBizImpl.getSysEmailHisById()...");
		return sysEmailHisDao.load(id);
	}

	@Override
	public SysEmailHis saveSysEmailHis(SysEmailHis sysEmailHis) {
		logger.info("entering action::SysEmailHisBizImpl.saveSysEmailHis()...");
		sysEmailHis.setId(null);
		sysEmailHisDao.saveOrUpdate(sysEmailHis);
		return sysEmailHis;
	}

	@Override
	public SysEmailHis updateSysEmailHis(SysEmailHis sysEmailHis) {
		logger.info("entering action::SysEmailHisBizImpl.updateSysEmailHis()...");
		sysEmailHisDao.saveOrUpdate(sysEmailHis);
		return sysEmailHis;
	}

	@Override
	public void exportSysEmailHis(ExportSetting exportSetting) {
		logger.info("entering action::SysEmailHisBizImpl.exportSysEmailHis()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<SysEmailHis> sysEmailHisPage = (Page<SysEmailHis>) sysEmailHisDao
				.findSysEmailHisByPage(exportSetting);

		excelService.exportToFile(sysEmailHisPage.getRows(), exportSetting);
	}

	public ISysEmailHisDao getSysEmailHisDao() {
		logger.info("entering action::SysEmailHisBizImpl.getSysEmailHisDao()...");
		return sysEmailHisDao;
	}

	public void setSysEmailHisDao(ISysEmailHisDao sysEmailHisDao) {
		logger.info("entering action::SysEmailHisBizImpl.setSysEmailHisDao()...");
		this.sysEmailHisDao = sysEmailHisDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::SysEmailHisBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::SysEmailHisBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
