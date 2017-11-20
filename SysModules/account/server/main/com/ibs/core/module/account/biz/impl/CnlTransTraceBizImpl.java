/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlTransTraceBiz;
import com.ibs.core.module.account.dao.ICnlTransTraceDao;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceDto;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.interfaces.domain.CnlMsg;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.CollectionUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransTraceBizImpl extends BaseBiz implements ICnlTransTraceBiz {

	private ICnlTransTraceDao cnlTransTraceDao;
	private IExcelService excelService;

	private ICnlCnlCfgDao cnlCnlCfgDao;
	
	@Override
	public IPage<CnlTransTrace> findCnlTransTraceByPageTransFailed(QueryPage queryPage) {
		logger.info("entering action::CnlTransTraceBizImpl.findCnlTransTraceByPageTransFailed()...");
		
		
		IPage<CnlTransTrace> iPage = cnlTransTraceDao.findCnlTransTraceByPageTransFailed(queryPage);
		
		Collection<CnlTransTrace> cnlTransTraceColl = iPage.getRows();
		
		List<DataDict> dataCnlCnlCfgCode = new ArrayList<DataDict>();
		List<CnlCnlCfg> lists = cnlCnlCfgDao.getList();
		for (CnlCnlCfg c : lists) {
			DataDict dataDict = new DataDict();
			dataDict.setCode(c.getCnlCnlCode());
			dataDict.setName(c.getCnlSysName());
			dataCnlCnlCfgCode.add(dataDict);
		}
		CollectionUtils.transformCollection(cnlTransTraceColl, "cnlCnlCode", "cnlCnlCode", dataCnlCnlCfgCode, "code", "name");
		return iPage;
	}
	
	@Override
	public IPage<CnlTransTraceDto> findCnlTransTraceByPage(QueryPage queryPage,String msg) {
		logger.info("entering action::CnlTransTraceBizImpl.findCnlTransTraceByPage()...");
		return cnlTransTraceDao.findCnlTransTraceByPage(queryPage,msg);
	}

	@Override
	public CnlTransTrace getCnlTransTraceById(String id) {
		logger.info("entering action::CnlTransTraceBizImpl.getCnlTransTraceById()...");
		return cnlTransTraceDao.load(id);
	}

	@Override
	public CnlTransTrace saveCnlTransTrace(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceBizImpl.saveCnlTransTrace()...");
		cnlTransTrace.setId(null);
		cnlTransTraceDao.saveOrUpdate(cnlTransTrace);
		return cnlTransTrace;
	}

	@Override
	public CnlTransTrace updateCnlTransTrace(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceBizImpl.updateCnlTransTrace()...");
		cnlTransTraceDao.saveOrUpdate(cnlTransTrace);
		return cnlTransTrace;
	}

/*	@Override
	public void exportCnlTransTrace(ExportSetting exportSetting) {
		logger.info("entering action::CnlTransTraceBizImpl.exportCnlTransTrace()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlTransTrace> cnlTransTracePage = (Page<CnlTransTrace>) cnlTransTraceDao
				.findCnlTransTraceByPage(exportSetting);

		excelService.exportToFile(cnlTransTracePage.getRows(), exportSetting);
	}*/
	@Override
	public void exportCnlTransTrace(ExportSetting exportSetting) {
		logger.info("entering action::CnlTransTraceBizImpl.exportCnlTransTrace()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlTransTraceDto> cnlTransTracePage = (Page<CnlTransTraceDto>) cnlTransTraceDao
				.findCnlTransTraceByPage(exportSetting,null);
		
		excelService.exportToFile(cnlTransTracePage.getRows(), exportSetting);
	}

	public ICnlTransTraceDao getCnlTransTraceDao() {
		logger.info("entering action::CnlTransTraceBizImpl.getCnlTransTraceDao()...");
		return cnlTransTraceDao;
	}

	public void setCnlTransTraceDao(ICnlTransTraceDao cnlTransTraceDao) {
		logger.info("entering action::CnlTransTraceBizImpl.setCnlTransTraceDao()...");
		this.cnlTransTraceDao = cnlTransTraceDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlTransTraceBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlTransTraceBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	@Override
	public CnlTransTraceDto getCnlTransTraceDtoById(String id) {
		logger.info("entering action::CnlTransTraceBizImpl.getCnlTransTraceDtoById()...");
		return cnlTransTraceDao.getCnlTransTraceDtoById(id);
	}

	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}

}
