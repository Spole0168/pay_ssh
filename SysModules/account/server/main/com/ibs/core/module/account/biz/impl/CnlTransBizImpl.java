/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlTransBiz;
import com.ibs.core.module.account.dao.ICnlTransDao;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.dao.impl.CnlCnlCfgDaoImpl;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransBizImpl extends BaseBiz implements ICnlTransBiz {

	private ICnlTransDao cnlTransDao;
	private IExcelService excelService;
	private ICnlCnlCfgDao cnlCnlCfgDao;
	
	@Override
	public IPage<CnlTrans> findCnlTransByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransBizImpl.findCnlTransByPage()...");
		return cnlTransDao.findCnlTransByPage(queryPage);
	}
	
	@Override
	public IPage<CnlTrans> findCnlTransByHql(QueryPage queryPage,CnlTrans cnlTrans) {
		logger.info("entering action::CnlTransBizImpl.findCnlTransByHql()...");
		return cnlTransDao.findCnlTransBySql(queryPage,cnlTrans);
	}

	@Override
	public CnlTrans getCnlTransById(String id) {
		logger.info("entering action::CnlTransBizImpl.getCnlTransById()...");
		return cnlTransDao.load(id);
	}

	@Override
	public CnlTrans saveCnlTrans(CnlTrans cnlTrans) {
		logger.info("entering action::CnlTransBizImpl.saveCnlTrans()...");
		cnlTrans.setId(null);
		cnlTransDao.saveOrUpdate(cnlTrans);
		return cnlTrans;
	}

	@Override
	public CnlTrans updateCnlTrans(CnlTrans cnlTrans) {
		logger.info("entering action::CnlTransBizImpl.updateCnlTrans()...");
		cnlTransDao.saveOrUpdate(cnlTrans);
		return cnlTrans;
	}

	@Override
	public void exportCnlTrans(ExportSetting exportSetting) {
		logger.info("entering action::CnlTransBizImpl.exportCnlTrans()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlTrans> cnlTransPage = (Page<CnlTrans>) cnlTransDao
				.findCnlTransByPage(exportSetting);

		excelService.exportToFile(cnlTransPage.getRows(), exportSetting);
	}

	public ICnlTransDao getCnlTransDao() {
		logger.info("entering action::CnlTransBizImpl.getCnlTransDao()...");
		return cnlTransDao;
	}

	public void setCnlTransDao(ICnlTransDao cnlTransDao) {
		logger.info("entering action::CnlTransBizImpl.setCnlTransDao()...");
		this.cnlTransDao = cnlTransDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlTransBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlTransBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	
	// 从cnlcnlcfg表中取得渠道来源标识
	public List<CnlCnlCfg> getCnlCnlCodeVal(CnlCnlCfg cnlCnlCfg) {
		return this.getCnlCnlCfgDao().findBy(cnlCnlCfg);
	}

	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		if(cnlCnlCfgDao == null) {
			cnlCnlCfgDao = new CnlCnlCfgDaoImpl();
		}
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}

}
