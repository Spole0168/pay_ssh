/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.corcust.biz.ICorCustPhonesBiz;
import com.ibs.core.module.corcust.dao.ICorCustPhonesDao;
import com.ibs.core.module.corcust.domain.CorCustPhones;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PHONES
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustPhonesBizImpl extends BaseBiz implements ICorCustPhonesBiz {

	private ICorCustPhonesDao corCustPhonesDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CorCustPhones> findCorCustPhonesByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustPhonesBizImpl.findCorCustPhonesByPage()...");
		return corCustPhonesDao.findCorCustPhonesByPage(queryPage);
	}

	@Override
	public CorCustPhones getCorCustPhonesById(String id) {
		logger.info("entering action::CorCustPhonesBizImpl.getCorCustPhonesById()...");
		return corCustPhonesDao.load(id);
	}

	@Override
	public CorCustPhones saveCorCustPhones(CorCustPhones corCustPhones) {
		logger.info("entering action::CorCustPhonesBizImpl.saveCorCustPhones()...");
		corCustPhones.setId(null);
		corCustPhonesDao.saveOrUpdate(corCustPhones);
		return corCustPhones;
	}

	@Override
	public CorCustPhones updateCorCustPhones(CorCustPhones corCustPhones) {
		logger.info("entering action::CorCustPhonesBizImpl.updateCorCustPhones()...");
		corCustPhonesDao.saveOrUpdate(corCustPhones);
		return corCustPhones;
	}

	@Override
	public void exportCorCustPhones(ExportSetting exportSetting) {
		logger.info("entering action::CorCustPhonesBizImpl.exportCorCustPhones()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorCustPhones> corCustPhonesPage = (Page<CorCustPhones>) corCustPhonesDao
				.findCorCustPhonesByPage(exportSetting);

		excelService.exportToFile(corCustPhonesPage.getRows(), exportSetting);
	}

	public ICorCustPhonesDao getCorCustPhonesDao() {
		logger.info("entering action::CorCustPhonesBizImpl.getCorCustPhonesDao()...");
		return corCustPhonesDao;
	}

	public void setCorCustPhonesDao(ICorCustPhonesDao corCustPhonesDao) {
		logger.info("entering action::CorCustPhonesBizImpl.setCorCustPhonesDao()...");
		this.corCustPhonesDao = corCustPhonesDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorCustPhonesBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorCustPhonesBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	@Override
	public CorCustPhones load1(String custCode) {
		return corCustPhonesDao.load1(custCode);
	}

}
