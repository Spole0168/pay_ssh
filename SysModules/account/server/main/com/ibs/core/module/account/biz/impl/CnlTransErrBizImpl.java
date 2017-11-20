/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlTransErrBiz;
import com.ibs.core.module.account.dao.ICnlTransErrDao;
import com.ibs.core.module.account.domain.CnlTransErr;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
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
 * @comments	: code generated based on database T_CNL_TRANS_ERR
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransErrBizImpl extends BaseBiz implements ICnlTransErrBiz {

	private ICnlTransErrDao cnlTransErrDao;
	private IExcelService excelService;
	private ICnlCnlCfgDao cnlCnlCfgDao;
	
	@Override
	public IPage<CnlTransErr> findCnlTransErrByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransErrBizImpl.findCnlTransErrByPage()...");
		IPage<CnlTransErr> iPage = cnlTransErrDao.findCnlTransErrByPage(queryPage);
		
		Collection<CnlTransErr> CnlTransErrColl = iPage.getRows();
		
		List<DataDict> dataCnlCnlCfgCode = new ArrayList<DataDict>();
		List<CnlCnlCfg> lists = cnlCnlCfgDao.getList();
		for (CnlCnlCfg c : lists) {
			DataDict dataDict = new DataDict();
			dataDict.setCode(c.getCnlCnlCode());
			dataDict.setName(c.getCnlSysName());
			dataCnlCnlCfgCode.add(dataDict);
		}
		CollectionUtils.transformCollection(CnlTransErrColl, "cnlCnlCode", "cnlCnlCode", dataCnlCnlCfgCode, "code", "name");
		return iPage;
	}

	@Override
	public CnlTransErr getCnlTransErrById(String id) {
		logger.info("entering action::CnlTransErrBizImpl.getCnlTransErrById()...");
		return cnlTransErrDao.load(id);
	}

	@Override
	public CnlTransErr saveCnlTransErr(CnlTransErr cnlTransErr) {
		logger.info("entering action::CnlTransErrBizImpl.saveCnlTransErr()...");
		cnlTransErr.setId(null);
		cnlTransErrDao.saveOrUpdate(cnlTransErr);
		return cnlTransErr;
	}

	@Override
	public CnlTransErr updateCnlTransErr(CnlTransErr cnlTransErr) {
		logger.info("entering action::CnlTransErrBizImpl.updateCnlTransErr()...");
		cnlTransErrDao.saveOrUpdate(cnlTransErr);
		return cnlTransErr;
	}

	@Override
	public void exportCnlTransErr(ExportSetting exportSetting) {
		logger.info("entering action::CnlTransErrBizImpl.exportCnlTransErr()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlTransErr> cnlTransErrPage = (Page<CnlTransErr>) cnlTransErrDao
				.findCnlTransErrByPage(exportSetting);

		excelService.exportToFile(cnlTransErrPage.getRows(), exportSetting);
	}

	public ICnlTransErrDao getCnlTransErrDao() {
		logger.info("entering action::CnlTransErrBizImpl.getCnlTransErrDao()...");
		return cnlTransErrDao;
	}

	public void setCnlTransErrDao(ICnlTransErrDao cnlTransErrDao) {
		logger.info("entering action::CnlTransErrBizImpl.setCnlTransErrDao()...");
		this.cnlTransErrDao = cnlTransErrDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlTransErrBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlTransErrBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	
	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}
}
