/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlCustTraceBiz;
import com.ibs.core.module.account.dao.ICnlCustTraceDao;
import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustTraceBizImpl extends BaseBiz implements ICnlCustTraceBiz {

	private ICnlCustTraceDao cnlCustTraceDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlCustTrace> findCnlCustTraceByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustTraceBizImpl.findCnlCustTraceByPage()...");
		return cnlCustTraceDao.findCnlCustTraceByPage(queryPage);
	}

	@Override
	public CnlCustTrace getCnlCustTraceById(String id) {
		logger.info("entering action::CnlCustTraceBizImpl.getCnlCustTraceById()...");
		return cnlCustTraceDao.load(id);
	}

	@Override
	public CnlCustTrace saveCnlCustTrace(CnlCustTrace cnlCustTrace) {
		logger.info("entering action::CnlCustTraceBizImpl.saveCnlCustTrace()...");
		cnlCustTrace.setId(null);
		cnlCustTraceDao.saveOrUpdate(cnlCustTrace);
		return cnlCustTrace;
	}

	@Override
	public CnlCustTrace updateCnlCustTrace(CnlCustTrace cnlCustTrace) {
		logger.info("entering action::CnlCustTraceBizImpl.updateCnlCustTrace()...");
		cnlCustTraceDao.saveOrUpdate(cnlCustTrace);
		return cnlCustTrace;
	}

	@Override
	public void exportCnlCustTrace(ExportSetting exportSetting) {
		logger.info("entering action::CnlCustTraceBizImpl.exportCnlCustTrace()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlCustTrace> cnlCustTracePage = (Page<CnlCustTrace>) cnlCustTraceDao
				.findCnlCustTraceByPage(exportSetting);

		excelService.exportToFile(cnlCustTracePage.getRows(), exportSetting);
	}

	public ICnlCustTraceDao getCnlCustTraceDao() {
		logger.info("entering action::CnlCustTraceBizImpl.getCnlCustTraceDao()...");
		return cnlCustTraceDao;
	}

	public void setCnlCustTraceDao(ICnlCustTraceDao cnlCustTraceDao) {
		logger.info("entering action::CnlCustTraceBizImpl.setCnlCustTraceDao()...");
		this.cnlCustTraceDao = cnlCustTraceDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCustTraceBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCustTraceBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
