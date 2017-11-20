/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlCustAcntDtlBiz;
import com.ibs.core.module.account.dao.ICnlCustAcntDtlDao;
import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlCustAcntDtlDto;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustAcntDtlBizImpl extends BaseBiz implements ICnlCustAcntDtlBiz {

	private ICnlCustAcntDtlDao cnlCustAcntDtlDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlCustAcntDtl> findCnlCustAcntDtlByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.findCnlCustAcntDtlByPage()...");
		return cnlCustAcntDtlDao.findCnlCustAcntDtlByPage(queryPage);
	}

	@Override
	public CnlCustAcntDtl getCnlCustAcntDtlById(String id) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.getCnlCustAcntDtlById()...");
		return cnlCustAcntDtlDao.load(id);
	}

	@Override
	public CnlCustAcntDtl saveCnlCustAcntDtl(CnlCustAcntDtl cnlCustAcntDtl) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.saveCnlCustAcntDtl()...");
		cnlCustAcntDtl.setId(null);
		cnlCustAcntDtlDao.saveOrUpdate(cnlCustAcntDtl);
		return cnlCustAcntDtl;
	}

	@Override
	public CnlCustAcntDtl updateCnlCustAcntDtl(CnlCustAcntDtl cnlCustAcntDtl) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.updateCnlCustAcntDtl()...");
		cnlCustAcntDtlDao.saveOrUpdate(cnlCustAcntDtl);
		return cnlCustAcntDtl;
	}

	@Override
	public void exportCnlCustAcntDtl(ExportSetting exportSetting) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.exportCnlCustAcntDtl()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlCustAcntDtl> cnlCustAcntDtlPage = (Page<CnlCustAcntDtl>) cnlCustAcntDtlDao
				.findCnlCustAcntDtlByPage(exportSetting);

		excelService.exportToFile(cnlCustAcntDtlPage.getRows(), exportSetting);
	}

	public ICnlCustAcntDtlDao getCnlCustAcntDtlDao() {
		logger.info("entering action::CnlCustAcntDtlBizImpl.getCnlCustAcntDtlDao()...");
		return cnlCustAcntDtlDao;
	}

	public void setCnlCustAcntDtlDao(ICnlCustAcntDtlDao cnlCustAcntDtlDao) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.setCnlCustAcntDtlDao()...");
		this.cnlCustAcntDtlDao = cnlCustAcntDtlDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCustAcntDtlBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	/**
	 * 与交易流水的多表查询
	 */
	@Override
	public IPage<CnlCustAcntDtlDto> findCnlCustAcntDtlByPageAndMoreTable(QueryPage queryPage,String condition) {
		logger.info("entering action::CnlCustAcntDtlBizImpl.findCnlCustAcntDtlByPageAndMoreTable...");
		return cnlCustAcntDtlDao.findCnlCustAcntDtlByPageAndMoreTable(queryPage,condition);
	}

}
