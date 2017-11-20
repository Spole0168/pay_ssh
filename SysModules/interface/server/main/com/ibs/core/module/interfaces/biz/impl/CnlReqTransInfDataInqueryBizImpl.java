/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.interfaces.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.interfaces.biz.ICnlReqTransInfDataInqueryBiz;
import com.ibs.core.module.interfaces.dao.ICnlReqTransInfDataInqueryDao;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
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
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlReqTransInfDataInqueryBizImpl extends BaseBiz implements ICnlReqTransInfDataInqueryBiz {

	private ICnlReqTransInfDataInqueryDao cnlReqTransDao;
	private IExcelService excelService;
	private IDataDictService dataDictService;
	private ICnlCnlCfgDao cnlCnlCfgDao;
	
	
	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	@Override
	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage) {
		logger.info("entering action::CnlReqTransBizImpl.findCnlReqTransByPage()...");
		
		IPage<CnlReqTrans> cnlReqTranses=cnlReqTransDao.findCnlReqTransByPage(queryPage);
		Collection<CnlReqTrans> list=cnlReqTranses.getRows();
		List<DataDict> dataDictReqStatus =dataDictService.list(IDataDictService.DATA_DICT_TYPE__REQ_STATUS);
		List<DataDict> dataCnlCnlCfgCode = new ArrayList<DataDict>();
		List<CnlCnlCfg> lists = cnlCnlCfgDao.getCfgList();
		for (CnlCnlCfg c : lists) {
			DataDict dataDict = new DataDict();
			dataDict.setCode(c.getCnlCnlCode());
			dataDict.setName(c.getCnlSysName());
			dataCnlCnlCfgCode.add(dataDict);
		}
		try {
		CollectionUtils.transformCollection(list, "reqStatus", "reqStatus", dataDictReqStatus, "code","name");
		CollectionUtils.transformCollection(list, "cnlCnlCode", "cnlCnlCode", dataCnlCnlCfgCode, "code", "name");
		} catch (Exception e) {
			throw new BizException(e.getMessage());
		}
		

		
		logger.info("exit action::CnlReqTransBizImpl.findCnlReqTransByPage()...");
		return cnlReqTranses;
	}

	@Override
	public CnlReqTrans getCnlReqTransById(String id) {
		logger.info("entering action::CnlReqTransBizImpl.getCnlReqTransById()...");
		return cnlReqTransDao.load(id);
	}

	@Override
	public CnlReqTrans saveCnlReqTrans(CnlReqTrans cnlReqTrans) {
		logger.info("entering action::CnlReqTransBizImpl.saveCnlReqTrans()...");
		cnlReqTrans.setId(null);
		cnlReqTransDao.saveOrUpdate(cnlReqTrans);
		return cnlReqTrans;
	}

	@Override
	public CnlReqTrans updateCnlReqTrans(CnlReqTrans cnlReqTrans) {
		logger.info("entering action::CnlReqTransBizImpl.updateCnlReqTrans()...");
		cnlReqTransDao.saveOrUpdate(cnlReqTrans);
		return cnlReqTrans;
	}

	@Override
	public void exportCnlReqTrans(ExportSetting exportSetting) {
		logger.info("entering action::CnlReqTransBizImpl.exportCnlReqTrans()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlReqTrans> cnlReqTransPage = (Page<CnlReqTrans>) cnlReqTransDao
				.findCnlReqTransByPage(exportSetting);

		excelService.exportToFile(cnlReqTransPage.getRows(), exportSetting);
	}

	public ICnlReqTransInfDataInqueryDao getCnlReqTransDao() {
		logger.info("entering action::CnlReqTransBizImpl.getCnlReqTransDao()...");
		return cnlReqTransDao;
	}

	public void setCnlReqTransDao(ICnlReqTransInfDataInqueryDao cnlReqTransDao) {
		logger.info("entering action::CnlReqTransBizImpl.setCnlReqTransDao()...");
		this.cnlReqTransDao = cnlReqTransDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlReqTransBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlReqTransBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
