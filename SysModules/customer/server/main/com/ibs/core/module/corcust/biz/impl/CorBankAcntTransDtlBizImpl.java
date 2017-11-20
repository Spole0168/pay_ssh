/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.corcust.biz.ICorBankAcntTransDtlBiz;
import com.ibs.core.module.corcust.dao.ICorBankAcntTransDtlDao;
import com.ibs.core.module.corcust.domain.CorBankAcntTransDtl;
import com.ibs.core.module.corcust.domain.CorDto;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankAcntTransDtlBizImpl extends BaseBiz implements ICorBankAcntTransDtlBiz {

	private ICorBankAcntTransDtlDao corBankAcntTransDtlDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CorDto> findCorBankAcntTransDtlByPage(QueryPage queryPage,String st,String direction) {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.findCorBankAcntTransDtlByPage()...");
		return corBankAcntTransDtlDao.findCorBankAcntTransDtlByPage(queryPage, st, direction);
	}

	@Override
	public CorBankAcntTransDtl getCorBankAcntTransDtlById(String id) {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.getCorBankAcntTransDtlById()...");
		return corBankAcntTransDtlDao.load(id);
	}

	@Override
	public CorBankAcntTransDtl saveCorBankAcntTransDtl(CorBankAcntTransDtl corBankAcntTransDtl) {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.saveCorBankAcntTransDtl()...");
		corBankAcntTransDtl.setId(null);
		corBankAcntTransDtlDao.saveOrUpdate(corBankAcntTransDtl);
		return corBankAcntTransDtl;
	}

	@Override
	public CorBankAcntTransDtl updateCorBankAcntTransDtl(CorBankAcntTransDtl corBankAcntTransDtl) {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.updateCorBankAcntTransDtl()...");
		corBankAcntTransDtlDao.saveOrUpdate(corBankAcntTransDtl);
		return corBankAcntTransDtl;
	}

	@Override
	public void exportCorBankAcntTransDtl(ExportSetting exportSetting,String st,String direction) {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.exportCorBankAcntTransDtl()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorDto> corBankAcntTransDtlPage = (Page<CorDto>) corBankAcntTransDtlDao
				.findCorBankAcntTransDtlByPage(exportSetting,st,direction);

		excelService.exportToFile(corBankAcntTransDtlPage.getRows(), exportSetting);
	}

	public ICorBankAcntTransDtlDao getCorBankAcntTransDtlDao() {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.getCorBankAcntTransDtlDao()...");
		return corBankAcntTransDtlDao;
	}

	public void setCorBankAcntTransDtlDao(ICorBankAcntTransDtlDao corBankAcntTransDtlDao) {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.setCorBankAcntTransDtlDao()...");
		this.corBankAcntTransDtlDao = corBankAcntTransDtlDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorBankAcntTransDtlBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
