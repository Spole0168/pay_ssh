/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.cnlcust.biz.ICnlCustPersonalBiz;
import com.ibs.core.module.cnlcust.dao.ICnlCustPersonalDao;
import com.ibs.core.module.cnlcust.domain.CnlCustPersonal;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustPersonalBizImpl extends BaseBiz implements ICnlCustPersonalBiz {

	private ICnlCustPersonalDao cnlCustPersonalDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlCustPersonal> findCnlCustPersonalByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustPersonalBizImpl.findCnlCustPersonalByPage()...");
		return cnlCustPersonalDao.findCnlCustPersonalByPage(queryPage);
	}

	@Override
	public CnlCustPersonal getCnlCustPersonalById(String id) {
		logger.info("entering action::CnlCustPersonalBizImpl.getCnlCustPersonalById()...");
		return cnlCustPersonalDao.load(id);
	}

	@Override
	public CnlCustPersonal saveCnlCustPersonal(CnlCustPersonal cnlCustPersonal) {
		logger.info("entering action::CnlCustPersonalBizImpl.saveCnlCustPersonal()...");
		cnlCustPersonal.setId(null);
		cnlCustPersonalDao.saveOrUpdate(cnlCustPersonal);
		return cnlCustPersonal;
	}

	@Override
	public CnlCustPersonal updateCnlCustPersonal(CnlCustPersonal cnlCustPersonal) {
		logger.info("entering action::CnlCustPersonalBizImpl.updateCnlCustPersonal()...");
		cnlCustPersonalDao.saveOrUpdate(cnlCustPersonal);
		return cnlCustPersonal;
	}

	@Override
	public void exportCnlCustPersonal(ExportSetting exportSetting) {
		logger.info("entering action::CnlCustPersonalBizImpl.exportCnlCustPersonal()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlCustPersonal> cnlCustPersonalPage = (Page<CnlCustPersonal>) cnlCustPersonalDao
				.findCnlCustPersonalByPage(exportSetting);

		excelService.exportToFile(cnlCustPersonalPage.getRows(), exportSetting);
	}

	public ICnlCustPersonalDao getCnlCustPersonalDao() {
		logger.info("entering action::CnlCustPersonalBizImpl.getCnlCustPersonalDao()...");
		return cnlCustPersonalDao;
	}

	public void setCnlCustPersonalDao(ICnlCustPersonalDao cnlCustPersonalDao) {
		logger.info("entering action::CnlCustPersonalBizImpl.setCnlCustPersonalDao()...");
		this.cnlCustPersonalDao = cnlCustPersonalDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCustPersonalBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCustPersonalBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
