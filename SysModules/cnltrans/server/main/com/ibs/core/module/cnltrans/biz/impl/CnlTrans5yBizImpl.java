/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.cnltrans.biz.ICnlTrans5yBiz;
import com.ibs.core.module.cnltrans.dao.ICnlTrans5yDao;
import com.ibs.core.module.cnltrans.domain.CnlTrans5y;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_5Y
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTrans5yBizImpl extends BaseBiz implements ICnlTrans5yBiz {

	private ICnlTrans5yDao cnlTrans5yDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlTrans5y> findCnlTrans5yByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTrans5yBizImpl.findCnlTrans5yByPage()...");
		return cnlTrans5yDao.findCnlTrans5yByPage(queryPage);
	}

	@Override
	public CnlTrans5y getCnlTrans5yById(String id) {
		logger.info("entering action::CnlTrans5yBizImpl.getCnlTrans5yById()...");
		return cnlTrans5yDao.load(id);
	}

	@Override
	public CnlTrans5y saveCnlTrans5y(CnlTrans5y cnlTrans5y) {
		logger.info("entering action::CnlTrans5yBizImpl.saveCnlTrans5y()...");
		cnlTrans5y.setId(null);
		cnlTrans5yDao.saveOrUpdate(cnlTrans5y);
		return cnlTrans5y;
	}

	@Override
	public CnlTrans5y updateCnlTrans5y(CnlTrans5y cnlTrans5y) {
		logger.info("entering action::CnlTrans5yBizImpl.updateCnlTrans5y()...");
		cnlTrans5yDao.saveOrUpdate(cnlTrans5y);
		return cnlTrans5y;
	}

	@Override
	public void exportCnlTrans5y(ExportSetting exportSetting) {
		logger.info("entering action::CnlTrans5yBizImpl.exportCnlTrans5y()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlTrans5y> cnlTrans5yPage = (Page<CnlTrans5y>) cnlTrans5yDao
				.findCnlTrans5yByPage(exportSetting);

		excelService.exportToFile(cnlTrans5yPage.getRows(), exportSetting);
	}

	public ICnlTrans5yDao getCnlTrans5yDao() {
		logger.info("entering action::CnlTrans5yBizImpl.getCnlTrans5yDao()...");
		return cnlTrans5yDao;
	}

	public void setCnlTrans5yDao(ICnlTrans5yDao cnlTrans5yDao) {
		logger.info("entering action::CnlTrans5yBizImpl.setCnlTrans5yDao()...");
		this.cnlTrans5yDao = cnlTrans5yDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlTrans5yBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlTrans5yBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
