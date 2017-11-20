/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlTrans3mBiz;
import com.ibs.core.module.account.dao.ICnlTrans3mDao;
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.dao.impl.CnlCnlCfgDaoImpl;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_3M
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTrans3mBizImpl extends BaseBiz implements ICnlTrans3mBiz {

	private ICnlTrans3mDao cnlTrans3mDao;
	private IExcelService excelService;
	private ICnlCnlCfgDao cnlCnlCfgDao;
	
	public List<OptionObjectPair> getCnlSourceList(){
		CnlCnlCfg c = new CnlCnlCfg();
		c.setIsValid(Constants.IS_VALID_VALID);
		List<CnlCnlCfg> list = cnlCnlCfgDao.findBy(c);
		List<OptionObjectPair> returnList = new ArrayList<OptionObjectPair>();
		returnList.add(new OptionObjectPair("","请选择"));
		if(list != null) {
			for(CnlCnlCfg cfg : list) {
				returnList.add(new OptionObjectPair(cfg.getCnlCnlCode(),cfg.getCnlSysName()));
			}
		}
		return returnList;
	}
	
	@Override
	public IPage<CnlTrans3m> findCnlTrans3mByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTrans3mBizImpl.findCnlTrans3mByPage()...");
		return cnlTrans3mDao.findCnlTrans3mByPage(queryPage);
	}

	@Override
	public CnlTrans3m getCnlTrans3mById(String id) {
		logger.info("entering action::CnlTrans3mBizImpl.getCnlTrans3mById()...");
		return cnlTrans3mDao.load(id);
	}

	@Override
	public CnlTrans3m saveCnlTrans3m(CnlTrans3m cnlTrans3m) {
		logger.info("entering action::CnlTrans3mBizImpl.saveCnlTrans3m()...");
		cnlTrans3m.setId(null);
		cnlTrans3mDao.saveOrUpdate(cnlTrans3m);
		return cnlTrans3m;
	}

	@Override
	public CnlTrans3m updateCnlTrans3m(CnlTrans3m cnlTrans3m) {
		logger.info("entering action::CnlTrans3mBizImpl.updateCnlTrans3m()...");
		cnlTrans3mDao.saveOrUpdate(cnlTrans3m);
		return cnlTrans3m;
	}

	@Override
	public void exportCnlTrans3m(ExportSetting exportSetting) {
		logger.info("entering action::CnlTrans3mBizImpl.exportCnlTrans3m()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlTrans3m> cnlTrans3mPage = (Page<CnlTrans3m>) cnlTrans3mDao
				.findCnlTrans3mByPage(exportSetting);

		excelService.exportToFile(cnlTrans3mPage.getRows(), exportSetting);
	}

	public ICnlTrans3mDao getCnlTrans3mDao() {
		logger.info("entering action::CnlTrans3mBizImpl.getCnlTrans3mDao()...");
		return cnlTrans3mDao;
	}

	public void setCnlTrans3mDao(ICnlTrans3mDao cnlTrans3mDao) {
		logger.info("entering action::CnlTrans3mBizImpl.setCnlTrans3mDao()...");
		this.cnlTrans3mDao = cnlTrans3mDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlTrans3mBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlTrans3mBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		if(this.cnlCnlCfgDao == null){
			this.cnlCnlCfgDao = new CnlCnlCfgDaoImpl();
		}
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}
}
