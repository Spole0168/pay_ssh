/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.cnlcust.biz.ICnlCustCompanyBiz;
import com.ibs.core.module.cnlcust.dao.ICnlCustCompanyDao;
import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyAndOtherDto;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyDto;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustCompanyBizImpl extends BaseBiz implements ICnlCustCompanyBiz {

	private ICnlCustCompanyDao cnlCustCompanyDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlCustCompany> findCnlCustCompanyByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustCompanyBizImpl.findCnlCustCompanyByPage()...");
		return cnlCustCompanyDao.findCnlCustCompanyByPage(queryPage);
	}

	@Override
	public CnlCustCompany getCnlCustCompanyById(String id) {
		logger.info("entering action::CnlCustCompanyBizImpl.getCnlCustCompanyById()...");
		return cnlCustCompanyDao.load(id);
	}

	@Override
	public CnlCustCompany saveCnlCustCompany(CnlCustCompany cnlCustCompany) {
		logger.info("entering action::CnlCustCompanyBizImpl.saveCnlCustCompany()...");
		cnlCustCompany.setId(null);
		cnlCustCompanyDao.saveOrUpdate(cnlCustCompany);
		return cnlCustCompany;
	}

	@Override
	public CnlCustCompany updateCnlCustCompany(CnlCustCompany cnlCustCompany) {
		logger.info("entering action::CnlCustCompanyBizImpl.updateCnlCustCompany()...");
		cnlCustCompanyDao.saveOrUpdate(cnlCustCompany);
		return cnlCustCompany;
	}

	@Override
	public void exportCnlCustCompany(ExportSetting exportSetting) {
		logger.info("entering action::CnlCustCompanyBizImpl.exportCnlCustCompany()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlCustCompany> cnlCustCompanyPage = (Page<CnlCustCompany>) cnlCustCompanyDao
				.findCnlCustCompanyByPage(exportSetting);

		excelService.exportToFile(cnlCustCompanyPage.getRows(), exportSetting);
	}

	public ICnlCustCompanyDao getCnlCustCompanyDao() {
		logger.info("entering action::CnlCustCompanyBizImpl.getCnlCustCompanyDao()...");
		return cnlCustCompanyDao;
	}

	public void setCnlCustCompanyDao(ICnlCustCompanyDao cnlCustCompanyDao) {
		logger.info("entering action::CnlCustCompanyBizImpl.setCnlCustCompanyDao()...");
		this.cnlCustCompanyDao = cnlCustCompanyDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCustCompanyBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCustCompanyBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	@Override
	public CnlCustCompany getInfoByCnlCustCode(String cnlCustCode) {
		logger.info("entering action::CnlCustCompanyBizImpl.getCnlCustCompanyById()...");
		return cnlCustCompanyDao.getInfoByCnlCustCode(cnlCustCode);
	}
	/**
	 * 多表查询返回查询结果（T_CNL_CUST_COMPANY、T_CNL_CUST）
	 */
	@Override
	public IPage<CnlCustCompanyAndOtherDto> findCnlCustCompanyByMoreTable(QueryPage queryPage,String hqlCondition) {
		IPage<CnlCustCompanyAndOtherDto> page = cnlCustCompanyDao.findCnlCustCompanyByMoreTable(queryPage,hqlCondition);
		return page;
	}
	/**
	 * 企业客户的明细查询
	 */
	@Override
	public CnlCustCompanyAndOtherDto findDetail(String cnlCustCode) {
		CnlCustCompanyAndOtherDto dto=cnlCustCompanyDao.findDetail(cnlCustCode);
		return dto;
	}

}
