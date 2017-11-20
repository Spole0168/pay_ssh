/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.corcust.biz.ICorCustAddrBiz;
import com.ibs.core.module.corcust.dao.ICorCustAddrDao;
import com.ibs.core.module.corcust.domain.CorCustAddr;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_ADDR
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustAddrBizImpl extends BaseBiz implements ICorCustAddrBiz {

	private ICorCustAddrDao corCustAddrDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CorCustAddr> findCorCustAddrByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustAddrBizImpl.findCorCustAddrByPage()...");
		return corCustAddrDao.findCorCustAddrByPage(queryPage);
	}

	@Override
	public CorCustAddr getCorCustAddrById(String id) {
		logger.info("entering action::CorCustAddrBizImpl.getCorCustAddrById()...");
		return corCustAddrDao.load(id);
	}

	@Override
	public CorCustAddr saveCorCustAddr(CorCustAddr corCustAddr) {
		logger.info("entering action::CorCustAddrBizImpl.saveCorCustAddr()...");
		corCustAddr.setId(null);
		corCustAddrDao.saveOrUpdate(corCustAddr);
		return corCustAddr;
	}

	@Override
	public CorCustAddr updateCorCustAddr(CorCustAddr corCustAddr) {
		logger.info("entering action::CorCustAddrBizImpl.updateCorCustAddr()...");
		corCustAddrDao.saveOrUpdate(corCustAddr);
		return corCustAddr;
	}

	@Override
	public void exportCorCustAddr(ExportSetting exportSetting) {
		logger.info("entering action::CorCustAddrBizImpl.exportCorCustAddr()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorCustAddr> corCustAddrPage = (Page<CorCustAddr>) corCustAddrDao
				.findCorCustAddrByPage(exportSetting);

		excelService.exportToFile(corCustAddrPage.getRows(), exportSetting);
	}

	public ICorCustAddrDao getCorCustAddrDao() {
		logger.info("entering action::CorCustAddrBizImpl.getCorCustAddrDao()...");
		return corCustAddrDao;
	}

	public void setCorCustAddrDao(ICorCustAddrDao corCustAddrDao) {
		logger.info("entering action::CorCustAddrBizImpl.setCorCustAddrDao()...");
		this.corCustAddrDao = corCustAddrDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorCustAddrBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorCustAddrBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	@Override
	public CorCustAddr load1(String custCode) {
		return corCustAddrDao.load1(custCode);
	}
}
