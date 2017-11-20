/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.corcust.biz.ICorCustCompanyBiz;
import com.ibs.core.module.corcust.dao.ICorCustCompanyDao;
import com.ibs.core.module.corcust.domain.CorCustCompany;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustCompanyBizImpl extends BaseBiz implements ICorCustCompanyBiz {

	private ICorCustCompanyDao corCustCompanyDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CorCustCompany> findCorCustCompanyByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustCompanyBizImpl.findCorCustCompanyByPage()...");
		return corCustCompanyDao.findCorCustCompanyByPage(queryPage);
	}

	@Override
	public CorCustCompany getCorCustCompanyById(String id) {
		logger.info("entering action::CorCustCompanyBizImpl.getCorCustCompanyById()...");
		return corCustCompanyDao.load(id);
	}

	@Override
	public CorCustCompany saveCorCustCompany(CorCustCompany corCustCompany) {
		logger.info("entering action::CorCustCompanyBizImpl.saveCorCustCompany()...");
		corCustCompany.setId(null);
		corCustCompanyDao.saveOrUpdate(corCustCompany);
		return corCustCompany;
	}

	@Override
	public CorCustCompany updateCorCustCompany(CorCustCompany corCustCompany) {
		logger.info("entering action::CorCustCompanyBizImpl.updateCorCustCompany()...");
		corCustCompanyDao.saveOrUpdate(corCustCompany);
		return corCustCompany;
	}

	@Override
	public void exportCorCustCompany(ExportSetting exportSetting) {
		logger.info("entering action::CorCustCompanyBizImpl.exportCorCustCompany()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorCustCompany> corCustCompanyPage = (Page<CorCustCompany>) corCustCompanyDao
				.findCorCustCompanyByPage(exportSetting);

		excelService.exportToFile(corCustCompanyPage.getRows(), exportSetting);
	}

	public ICorCustCompanyDao getCorCustCompanyDao() {
		logger.info("entering action::CorCustCompanyBizImpl.getCorCustCompanyDao()...");
		return corCustCompanyDao;
	}

	public void setCorCustCompanyDao(ICorCustCompanyDao corCustCompanyDao) {
		logger.info("entering action::CorCustCompanyBizImpl.setCorCustCompanyDao()...");
		this.corCustCompanyDao = corCustCompanyDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorCustCompanyBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorCustCompanyBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	@Override
	public CorCustCompany load1(String custCode) {
		
		return corCustCompanyDao.load1(custCode);
	}

}
