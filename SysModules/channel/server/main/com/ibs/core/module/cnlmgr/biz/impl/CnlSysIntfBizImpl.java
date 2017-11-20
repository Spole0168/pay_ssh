/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.biz.impl;

import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.cnlmgr.biz.ICnlSysIntfBiz;
import com.ibs.core.module.cnlmgr.dao.ICnlSysIntfDao;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntfCfg;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSysIntfBizImpl extends BaseBiz implements ICnlSysIntfBiz {

	private ICnlSysIntfDao cnlSysIntfDao;
	private IExcelService excelService;
	
	@Override
	public IPage<CnlSysIntf> findCnlSysIntfByPage(QueryPage queryPage) {
		logger.info("entering action::CnlSysIntfBizImpl.findCnlSysIntfByPage()...");
		return cnlSysIntfDao.findCnlSysIntfByPage(queryPage);
	}

	@Override
	public CnlSysIntf getCnlSysIntfById(String id) {
		logger.info("entering action::CnlSysIntfBizImpl.getCnlSysIntfById()...");
		return cnlSysIntfDao.load(id);
	}

	@Override
	public CnlSysIntf saveCnlSysIntf(CnlSysIntf cnlSysIntf) {
		logger.info("entering action::CnlSysIntfBizImpl.saveCnlSysIntf()...");
		cnlSysIntf.setId(null);
		cnlSysIntfDao.saveOrUpdate(cnlSysIntf);
		return cnlSysIntf;
	}

	@Override
	public CnlSysIntf updateCnlSysIntf(CnlSysIntf cnlSysIntf) {
		logger.info("entering action::CnlSysIntfBizImpl.updateCnlSysIntf()...");
		cnlSysIntfDao.saveOrUpdate(cnlSysIntf);
		return cnlSysIntf;
	}

	@Override
	public void exportCnlSysIntf(ExportSetting exportSetting) {
		logger.info("entering action::CnlSysIntfBizImpl.exportCnlSysIntf()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlSysIntf> cnlSysIntfPage = (Page<CnlSysIntf>) cnlSysIntfDao
				.findCnlSysIntfByPage(exportSetting);

		excelService.exportToFile(cnlSysIntfPage.getRows(), exportSetting);
	}

	public ICnlSysIntfDao getCnlSysIntfDao() {
		logger.info("entering action::CnlSysIntfBizImpl.getCnlSysIntfDao()...");
		return cnlSysIntfDao;
	}

	public void setCnlSysIntfDao(ICnlSysIntfDao cnlSysIntfDao) {
		logger.info("entering action::CnlSysIntfBizImpl.setCnlSysIntfDao()...");
		this.cnlSysIntfDao = cnlSysIntfDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlSysIntfBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlSysIntfBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	
	public IPage<CnlSysIntfCfg> findSysIntf(QueryPage queryPage,String cnlCustCode,String accessCode,String currency){
		return cnlSysIntfDao.findSysIntf(queryPage, cnlCustCode, accessCode, currency);
	}
	
	public CnlSysIntfCfg findSysIntfCfgbyId(String id){
		return cnlSysIntfDao.findSysIntfCfgbyId(id);
	}
	
	public void deleteSysIntfByid(CnlSysIntf cnlSysIntf){
		cnlSysIntfDao.deleteSysIntfByid(cnlSysIntf);
	}
		
	public  CnlSysIntf getIntf(String  cnlcnlcode){
		return cnlSysIntfDao.getIntf(cnlcnlcode);
	}
	
	public CnlSysIntfCfg isExitAccessCode(String accessCode,String cnlCustCode){
		return cnlSysIntfDao.isExitAccessCode(accessCode,cnlCustCode);
	}
	
	public List<CnlSysIntfCfg> findBycurrency(){
		return cnlSysIntfDao.findBycurrency();
	}
	
	public CnlSysIntf findByAccessCode(String accessCode){
		return cnlSysIntfDao.findByAccessCode(accessCode);
	}
	
	public CnlSysIntfCfg isExitCurrency(String accessCode,String cnlCustCode){
		return cnlSysIntfDao.isExitCurrency(accessCode, cnlCustCode);
	}
}
