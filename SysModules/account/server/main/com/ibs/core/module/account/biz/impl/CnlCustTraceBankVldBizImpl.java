/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlCustTraceBankVldBiz;
import com.ibs.core.module.account.dao.ICnlCustTraceBankVldDao;
import com.ibs.core.module.account.domain.CnlCustTraceBankVld;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE_BANK_VLD
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustTraceBankVldBizImpl extends BaseBiz implements ICnlCustTraceBankVldBiz {

	private ICnlCustTraceBankVldDao cnlCustTraceBankVldDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlCustTraceBankVld> findCnlCustTraceBankVldByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.findCnlCustTraceBankVldByPage()...");
		return cnlCustTraceBankVldDao.findCnlCustTraceBankVldByPage(queryPage);
	}

	@Override
	public CnlCustTraceBankVld getCnlCustTraceBankVldById(String id) {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.getCnlCustTraceBankVldById()...");
		return cnlCustTraceBankVldDao.load(id);
	}

	@Override
	public CnlCustTraceBankVld saveCnlCustTraceBankVld(CnlCustTraceBankVld cnlCustTraceBankVld) {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.saveCnlCustTraceBankVld()...");
		cnlCustTraceBankVld.setId(null);
		cnlCustTraceBankVldDao.saveOrUpdate(cnlCustTraceBankVld);
		return cnlCustTraceBankVld;
	}

	@Override
	public CnlCustTraceBankVld updateCnlCustTraceBankVld(CnlCustTraceBankVld cnlCustTraceBankVld) {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.updateCnlCustTraceBankVld()...");
		cnlCustTraceBankVldDao.saveOrUpdate(cnlCustTraceBankVld);
		return cnlCustTraceBankVld;
	}

	@Override
	public void exportCnlCustTraceBankVld(ExportSetting exportSetting) {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.exportCnlCustTraceBankVld()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlCustTraceBankVld> cnlCustTraceBankVldPage = (Page<CnlCustTraceBankVld>) cnlCustTraceBankVldDao
				.findCnlCustTraceBankVldByPage(exportSetting);

		excelService.exportToFile(cnlCustTraceBankVldPage.getRows(), exportSetting);
	}

	public ICnlCustTraceBankVldDao getCnlCustTraceBankVldDao() {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.getCnlCustTraceBankVldDao()...");
		return cnlCustTraceBankVldDao;
	}

	public void setCnlCustTraceBankVldDao(ICnlCustTraceBankVldDao cnlCustTraceBankVldDao) {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.setCnlCustTraceBankVldDao()...");
		this.cnlCustTraceBankVldDao = cnlCustTraceBankVldDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCustTraceBankVldBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
