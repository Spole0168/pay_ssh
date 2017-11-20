/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.bank.biz.ICorBankSettingBiz;
import com.ibs.core.module.bank.dao.ICorBankSettingDao;
import com.ibs.core.module.bank.domain.CorBankSetting;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankSettingBizImpl extends BaseBiz implements ICorBankSettingBiz {

	private ICorBankSettingDao corBankSettingDao;
	private IExcelService excelService;
	////
	@Override
	public IPage<CorBankSetting> findCorBankSettingByPage(QueryPage queryPage) {
		logger.info("entering action::CorBankSettingBizImpl.findCorBankSettingByPage()...");
		return corBankSettingDao.findCorBankSettingByPage(queryPage);
	}

	@Override
	public CorBankSetting getCorBankSettingById(String id) {
		logger.info("entering action::CorBankSettingBizImpl.getCorBankSettingById()...");
		return corBankSettingDao.load(id);
	}

	@Override
	public CorBankSetting saveCorBankSetting(CorBankSetting corBankSetting) {
		logger.info("entering action::CorBankSettingBizImpl.saveCorBankSetting()...");
		corBankSetting.setId(null);
		corBankSettingDao.saveOrUpdate(corBankSetting);
		return corBankSetting;
	}

	@Override
	public CorBankSetting updateCorBankSetting(CorBankSetting corBankSetting) {
		logger.info("entering action::CorBankSettingBizImpl.updateCorBankSetting()...");
		corBankSettingDao.saveOrUpdate(corBankSetting);
		return corBankSetting;
	}

	@Override
	public void exportCorBankSetting(ExportSetting exportSetting) {
		logger.info("entering action::CorBankSettingBizImpl.exportCorBankSetting()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorBankSetting> corBankSettingPage = (Page<CorBankSetting>) corBankSettingDao
				.findCorBankSettingByPage(exportSetting);

		excelService.exportToFile(corBankSettingPage.getRows(), exportSetting);
	}

	public ICorBankSettingDao getCorBankSettingDao() {
		logger.info("entering action::CorBankSettingBizImpl.getCorBankSettingDao()...");
		return corBankSettingDao;
	}

	public void setCorBankSettingDao(ICorBankSettingDao corBankSettingDao) {
		logger.info("entering action::CorBankSettingBizImpl.setCorBankSettingDao()...");
		this.corBankSettingDao = corBankSettingDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorBankSettingBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorBankSettingBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	
	public CorBankSetting findByValue(String bankAcntCode){
		return corBankSettingDao.findByValue(bankAcntCode);
	}

}
