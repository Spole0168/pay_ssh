/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.topup.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.topup.biz.ITopupTransTraceOptBiz;
import com.ibs.core.module.topup.dao.ITopupTransTraceOptDao;
import com.ibs.core.module.topup.domain.TopupTransTraceOpt;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE_OPT
 * @modify		: your comments goes here (author,date,reason).
 */
public class TopupTransTraceOptBizImpl extends BaseBiz implements ITopupTransTraceOptBiz {

	private ITopupTransTraceOptDao topupTransTraceOptDao;
	private IExcelService excelService;
	
	@Override
	public IPage<TopupTransTraceOpt> findTopupTransTraceOptByPage(QueryPage queryPage) {
		logger.info("entering action::TopupTransTraceOptBizImpl.findTopupTransTraceOptByPage()...");
		return topupTransTraceOptDao.findTopupTransTraceOptByPage(queryPage);
	}

	@Override
	public TopupTransTraceOpt getTopupTransTraceOptById(String id) {
		logger.info("entering action::TopupTransTraceOptBizImpl.getTopupTransTraceOptById()...");
		return topupTransTraceOptDao.load(id);
	}

	@Override
	public TopupTransTraceOpt saveTopupTransTraceOpt(TopupTransTraceOpt topupTransTraceOpt) {
		logger.info("entering action::TopupTransTraceOptBizImpl.saveTopupTransTraceOpt()...");
		topupTransTraceOpt.setId(null);
		topupTransTraceOptDao.saveOrUpdate(topupTransTraceOpt);
		return topupTransTraceOpt;
	}

	@Override
	public TopupTransTraceOpt updateTopupTransTraceOpt(TopupTransTraceOpt topupTransTraceOpt) {
		logger.info("entering action::TopupTransTraceOptBizImpl.updateTopupTransTraceOpt()...");
		topupTransTraceOptDao.saveOrUpdate(topupTransTraceOpt);
		return topupTransTraceOpt;
	}

	@Override
	public void exportTopupTransTraceOpt(ExportSetting exportSetting) {
		logger.info("entering action::TopupTransTraceOptBizImpl.exportTopupTransTraceOpt()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<TopupTransTraceOpt> topupTransTraceOptPage = (Page<TopupTransTraceOpt>) topupTransTraceOptDao
				.findTopupTransTraceOptByPage(exportSetting);

		excelService.exportToFile(topupTransTraceOptPage.getRows(), exportSetting);
	}

	public ITopupTransTraceOptDao getTopupTransTraceOptDao() {
		logger.info("entering action::TopupTransTraceOptBizImpl.getTopupTransTraceOptDao()...");
		return topupTransTraceOptDao;
	}

	public void setTopupTransTraceOptDao(ITopupTransTraceOptDao topupTransTraceOptDao) {
		logger.info("entering action::TopupTransTraceOptBizImpl.setTopupTransTraceOptDao()...");
		this.topupTransTraceOptDao = topupTransTraceOptDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::TopupTransTraceOptBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::TopupTransTraceOptBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
