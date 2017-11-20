/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.reservedfund.biz.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.reservedfund.biz.ITcorReservedFundAcntBiz;
import com.ibs.core.module.reservedfund.dao.ICorReservedFundAcntDao;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.CollectionUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_RESERVED_FUND_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorReservedFundAcntBizImpl extends BaseBiz implements ITcorReservedFundAcntBiz {

	private ICorReservedFundAcntDao corReservedFundAcntDao;
	private IExcelService excelService;

	public IPage<CorReservedFundAcnt> findCorReservedFundAcntByPage(QueryPage queryPage) {
		logger.info("entering action::CorReservedFundAcntBizImpl.findCorReservedFundAcntByPage()...");
		return corReservedFundAcntDao.findCorReservedFundAcntByPage(queryPage);
		
	}

	public CorReservedFundAcnt getCorReservedFundAcntById(String id) {
		logger.info("entering action::CorReservedFundAcntBizImpl.getCorReservedFundAcntById()...");
		return corReservedFundAcntDao.load(id);
	}

	public CorReservedFundAcnt saveCorReservedFundAcnt(CorReservedFundAcnt corReservedFundAcnt) {
		logger.info("entering action::CorReservedFundAcntBizImpl.saveCorReservedFundAcnt()...");
		corReservedFundAcnt.setId(null);
		corReservedFundAcntDao.saveOrUpdate(corReservedFundAcnt);
		return corReservedFundAcnt;
	}

	public CorReservedFundAcnt updateCorReservedFundAcnt(CorReservedFundAcnt corReservedFundAcnt) {
		logger.info("entering action::CorReservedFundAcntBizImpl.updateCorReservedFundAcnt()...");
		corReservedFundAcntDao.saveOrUpdate(corReservedFundAcnt);
		return corReservedFundAcnt;
	}

	public void exportCorReservedFundAcnt(ExportSetting exportSetting) {
		logger.info("entering action::CorReservedFundAcntBizImpl.exportCorReservedFundAcnt()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorReservedFundAcnt> corReservedFundAcntPage = (Page<CorReservedFundAcnt>) corReservedFundAcntDao
				.findCorReservedFundAcntByPage(exportSetting);

		excelService.exportToFile(corReservedFundAcntPage.getRows(), exportSetting);
	}

	public ICorReservedFundAcntDao getCorReservedFundAcntDao() {
		logger.info("entering action::CorReservedFundAcntBizImpl.getCorReservedFundAcntDao()...");
		return corReservedFundAcntDao;
	}

	public void setCorReservedFundAcntDao(ICorReservedFundAcntDao corReservedFundAcntDao) {
		logger.info("entering action::CorReservedFundAcntBizImpl.setCorReservedFundAcntDao()...");
		this.corReservedFundAcntDao = corReservedFundAcntDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorReservedFundAcntBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorReservedFundAcntBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	

	@Override
	public IPage<CorReservedFundAcnt> findByBankNum(QueryPage queryPage,String bankInnerCode) {
		// TODO 徐伟龙  备付金
		return corReservedFundAcntDao.findByBankNum(queryPage,bankInnerCode);
	}
	
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum,String id){
		return corReservedFundAcntDao.CheckBankNum(bankInnerCode,bankCardNum,id);
	}
	
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum){
		return corReservedFundAcntDao.CheckBankNum(bankInnerCode, bankCardNum);
	}
	
	public void deleteById(String id){
		corReservedFundAcntDao.deleteById(id);
	}
	
}
