/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.violationRecord.biz.ISysSmsHisBiz;
import com.ibs.core.module.violationRecord.dao.ISysSmsHisDao;
import com.ibs.core.module.violationRecord.domain.SysSmsHis;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SMS_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysSmsHisBizImpl extends BaseBiz implements ISysSmsHisBiz {

	private ISysSmsHisDao sysSmsHisDao;
	private IExcelService excelService;
	
	@Override
	public IPage<SysSmsHis> findSysSmsHisByPage(QueryPage queryPage) {
		logger.info("entering action::SysSmsHisBizImpl.findSysSmsHisByPage()...");
		return sysSmsHisDao.findSysSmsHisByPage(queryPage);
	}

	@Override
	public SysSmsHis getSysSmsHisById(String id) {
		logger.info("entering action::SysSmsHisBizImpl.getSysSmsHisById()...");
		return sysSmsHisDao.load(id);
	}

	@Override
	public SysSmsHis saveSysSmsHis(SysSmsHis sysSmsHis) {
		logger.info("entering action::SysSmsHisBizImpl.saveSysSmsHis()...");
		sysSmsHis.setId(null);
		sysSmsHisDao.saveOrUpdate(sysSmsHis);
		return sysSmsHis;
	}

	@Override
	public SysSmsHis updateSysSmsHis(SysSmsHis sysSmsHis) {
		logger.info("entering action::SysSmsHisBizImpl.updateSysSmsHis()...");
		sysSmsHisDao.saveOrUpdate(sysSmsHis);
		return sysSmsHis;
	}

	@Override
	public void exportSysSmsHis(ExportSetting exportSetting) {
		logger.info("entering action::SysSmsHisBizImpl.exportSysSmsHis()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<SysSmsHis> sysSmsHisPage = (Page<SysSmsHis>) sysSmsHisDao
				.findSysSmsHisByPage(exportSetting);

		excelService.exportToFile(sysSmsHisPage.getRows(), exportSetting);
	}

	public ISysSmsHisDao getSysSmsHisDao() {
		logger.info("entering action::SysSmsHisBizImpl.getSysSmsHisDao()...");
		return sysSmsHisDao;
	}

	public void setSysSmsHisDao(ISysSmsHisDao sysSmsHisDao) {
		logger.info("entering action::SysSmsHisBizImpl.setSysSmsHisDao()...");
		this.sysSmsHisDao = sysSmsHisDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::SysSmsHisBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::SysSmsHisBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
