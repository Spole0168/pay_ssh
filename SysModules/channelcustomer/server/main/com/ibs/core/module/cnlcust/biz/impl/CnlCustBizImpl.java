/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.domain.CustPersonal;
import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.cnlcust.biz.ICnlCustBiz;
import com.ibs.core.module.cnlcust.dao.ICnlCustDao;
import com.ibs.core.module.cnlcust.domain.CnlCust;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustBizImpl extends BaseBiz implements ICnlCustBiz {

	private ICnlCustDao cnlCustDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CustPersonal> findCnlCustByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustBizImpl.findCnlCustByPage()...");
		return cnlCustDao.findCnlCustByPage(queryPage);
	}
	
	public IPage<CustPersonal> findCnlCustBylike(QueryPage queryPage,String cnlCustCode,String cnlSysName,String cnlCustType,String custStatus,
			String certType,String certNum,String realNamelevel,String custLevel,String createStartTime,String createEndTime){
		return cnlCustDao.findCnlCustBylike(queryPage, cnlCustCode, cnlSysName, 
				cnlCustType, custStatus, certType, certNum, realNamelevel,custLevel, createStartTime, createEndTime);
	}
	
	@Override
	public CnlCust getCnlCustById(String id) {
		logger.info("entering action::CnlCustBizImpl.getCnlCustById()...");
		return cnlCustDao.load(id);
	}

	@Override
	public CnlCust saveCnlCust(CnlCust cnlCust) {
		logger.info("entering action::CnlCustBizImpl.saveCnlCust()...");
		cnlCust.setId(null);
		cnlCustDao.saveOrUpdate(cnlCust);
		return cnlCust;
	}

	@Override
	public CnlCust updateCnlCust(CnlCust cnlCust) {
		logger.info("entering action::CnlCustBizImpl.updateCnlCust()...");
		cnlCustDao.saveOrUpdate(cnlCust);
		return cnlCust;
	}

	@Override
	public void exportCnlCust(ExportSetting exportSetting) {
		logger.info("entering action::CnlCustBizImpl.exportCnlCust()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CustPersonal> cnlCustPage = (Page<CustPersonal>) cnlCustDao
				.findCnlCustByPage(exportSetting);

		excelService.exportToFile(cnlCustPage.getRows(), exportSetting);
	}

	public ICnlCustDao getCnlCustDao() {
		logger.info("entering action::CnlCustBizImpl.getCnlCustDao()...");
		return cnlCustDao;
	}

	public void setCnlCustDao(ICnlCustDao cnlCustDao) {
		logger.info("entering action::CnlCustBizImpl.setCnlCustDao()...");
		this.cnlCustDao = cnlCustDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCustBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCustBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
