/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.violationRecord.biz.ISysViolationRecordBiz;
import com.ibs.core.module.violationRecord.dao.ISysViolationRecordDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordIPExceptionDTO;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_VIOLATION_RECORD
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysViolationRecordBizImpl extends BaseBiz implements ISysViolationRecordBiz {

	private ISysViolationRecordDao sysViolationRecordDao;
	private IExcelService excelService;
	
	@Override
	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage) {
		logger.info("entering action::SysViolationRecordBizImpl.findSysViolationRecordByPage()...");
		return sysViolationRecordDao.findSysViolationRecordByPage(queryPage);
	}
  
	@Override
	public SysViolationRecord getSysViolationRecordById(String id) {
		logger.info("entering action::SysViolationRecordBizImpl.getSysViolationRecordById()...");
		return sysViolationRecordDao.load(id);
	}

	@Override
	public SysViolationRecord saveSysViolationRecord(SysViolationRecord sysViolationRecord) {
		logger.info("entering action::SysViolationRecordBizImpl.saveSysViolationRecord()...");
		sysViolationRecord.setId(null);
		sysViolationRecordDao.saveOrUpdate(sysViolationRecord);
		return sysViolationRecord;
	}

	@Override
	public SysViolationRecord updateSysViolationRecord(SysViolationRecord sysViolationRecord) {
		logger.info("entering action::SysViolationRecordBizImpl.updateSysViolationRecord()...");
		sysViolationRecordDao.saveOrUpdate(sysViolationRecord);
		return sysViolationRecord;
	}

	@Override
	public void exportSysViolationRecord(ExportSetting exportSetting) {
		logger.info("entering action::SysViolationRecordBizImpl.exportSysViolationRecord()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<SysViolationRecord> sysViolationRecordPage = (Page<SysViolationRecord>) sysViolationRecordDao
				.findSysViolationRecordByPage(exportSetting);

		excelService.exportToFile(sysViolationRecordPage.getRows(), exportSetting);
	}

	public ISysViolationRecordDao getSysViolationRecordDao() {
		logger.info("entering action::SysViolationRecordBizImpl.getSysViolationRecordDao()...");
		return sysViolationRecordDao;
	}

	public void setSysViolationRecordDao(ISysViolationRecordDao sysViolationRecordDao) {
		logger.info("entering action::SysViolationRecordBizImpl.setSysViolationRecordDao()...");
		this.sysViolationRecordDao = sysViolationRecordDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::SysViolationRecordBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::SysViolationRecordBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	


	
	
	
	/**
	 *IP异常查询页面
	 */
	@Override
	public IPage<SysViolationRecordIPExceptionDTO> findViolationRecordTable(QueryPage queryPage) {
		// TODO Auto-generated method stub
		logger.info("entering action::SysViolationRecordBizImpl.findViolationRecordTable()...");
		return sysViolationRecordDao.findViolationRecordTable(queryPage);
	}





}
