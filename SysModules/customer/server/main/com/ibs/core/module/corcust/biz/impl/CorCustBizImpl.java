/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.corcust.biz.ICorCustBiz;
import com.ibs.core.module.corcust.dao.ICorCustDao;
import com.ibs.core.module.corcust.domain.CorCust;
import com.ibs.core.module.corcust.dto.Cor;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustBizImpl extends BaseBiz implements ICorCustBiz {

	private ICorCustDao corCustDao;
	private IExcelService excelService;

	@Override
	public IPage<CorCust> findCorCustByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustBizImpl.findCorCustByPage()...");
		return corCustDao.findCorCustByPage(queryPage);
	}

	@Override
	public CorCust getCorCustById(String id) {
		logger.info("entering action::CorCustBizImpl.getCorCustById()...");
		return corCustDao.load(id);
	}

	@Override
	public CorCust saveCorCust(CorCust corCust) {
		logger.info("entering action::CorCustBizImpl.saveCorCust()...");
		corCust.setId(null);
		corCustDao.saveOrUpdate(corCust);
		return corCust;
	}

	@Override
	public CorCust updateCorCust(CorCust corCust) {
		logger.info("entering action::CorCustBizImpl.updateCorCust()...");
		corCustDao.saveOrUpdate(corCust);
		return corCust;
	}

	@Override
	public void exportCorCust(ExportSetting exportSetting) {
		logger.info("entering action::CorCustBizImpl.exportCorCust()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorCust> corCustPage = (Page<CorCust>) corCustDao.findCorCustByPage(exportSetting);

		excelService.exportToFile(corCustPage.getRows(), exportSetting);
	}

	public ICorCustDao getCorCustDao() {
		logger.info("entering action::CorCustBizImpl.getCorCustDao()...");
		return corCustDao;
	}

	public void setCorCustDao(ICorCustDao corCustDao) {
		logger.info("entering action::CorCustBizImpl.setCorCustDao()...");
		this.corCustDao = corCustDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorCustBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorCustBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	@Override
	public IPage<Cor> findCorCustByPageAndMoreTable(QueryPage queryPage) {
		logger.info("entering action::CorCustBizImpl.findCorCustByPageAndMoreTable()...");
		return corCustDao.findCorCustByPageAndMoreTable(queryPage);
	}

	@Override
	public Cor load1(String id) {
		
		return corCustDao.load1(id);
	}	

	@Override
	public CorCust load2(String custCode) {
		
		return corCustDao.liad2(custCode);
	}



}
