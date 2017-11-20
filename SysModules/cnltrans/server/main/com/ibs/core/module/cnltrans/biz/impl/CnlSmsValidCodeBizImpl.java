/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.cnltrans.biz.ICnlSmsValidCodeBiz;
import com.ibs.core.module.cnltrans.dao.ICnlSmsValidCodeDao;
import com.ibs.core.module.cnltrans.domain.CnlSmsValidCode;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SMS_VALID_CODE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSmsValidCodeBizImpl extends BaseBiz implements ICnlSmsValidCodeBiz {

	private ICnlSmsValidCodeDao cnlSmsValidCodeDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlSmsValidCode> findCnlSmsValidCodeByPage(QueryPage queryPage) {
		logger.info("entering action::CnlSmsValidCodeBizImpl.findCnlSmsValidCodeByPage()...");
		return cnlSmsValidCodeDao.findCnlSmsValidCodeByPage(queryPage);
	}

	@Override
	public CnlSmsValidCode getCnlSmsValidCodeById(String id) {
		logger.info("entering action::CnlSmsValidCodeBizImpl.getCnlSmsValidCodeById()...");
		return cnlSmsValidCodeDao.load(id);
	}

	@Override
	public CnlSmsValidCode saveCnlSmsValidCode(CnlSmsValidCode cnlSmsValidCode) {
		logger.info("entering action::CnlSmsValidCodeBizImpl.saveCnlSmsValidCode()...");
		cnlSmsValidCode.setId(null);
		cnlSmsValidCodeDao.saveOrUpdate(cnlSmsValidCode);
		return cnlSmsValidCode;
	}

	@Override
	public CnlSmsValidCode updateCnlSmsValidCode(CnlSmsValidCode cnlSmsValidCode) {
		logger.info("entering action::CnlSmsValidCodeBizImpl.updateCnlSmsValidCode()...");
		cnlSmsValidCodeDao.saveOrUpdate(cnlSmsValidCode);
		return cnlSmsValidCode;
	}

	@Override
	public void exportCnlSmsValidCode(ExportSetting exportSetting) {
		logger.info("entering action::CnlSmsValidCodeBizImpl.exportCnlSmsValidCode()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlSmsValidCode> cnlSmsValidCodePage = (Page<CnlSmsValidCode>) cnlSmsValidCodeDao
				.findCnlSmsValidCodeByPage(exportSetting);

		excelService.exportToFile(cnlSmsValidCodePage.getRows(), exportSetting);
	}

	public ICnlSmsValidCodeDao getCnlSmsValidCodeDao() {
		logger.info("entering action::CnlSmsValidCodeBizImpl.getCnlSmsValidCodeDao()...");
		return cnlSmsValidCodeDao;
	}

	public void setCnlSmsValidCodeDao(ICnlSmsValidCodeDao cnlSmsValidCodeDao) {
		logger.info("entering action::CnlSmsValidCodeBizImpl.setCnlSmsValidCodeDao()...");
		this.cnlSmsValidCodeDao = cnlSmsValidCodeDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlSmsValidCodeBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlSmsValidCodeBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
