package com.ibs.core.module.violationRecord.biz.impl;

import com.ibs.core.module.violationRecord.biz.ISysViolationRecordOverSpendBiz;
import com.ibs.core.module.violationRecord.dao.ISysViolationRecordOverSpendDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordOverSpendDTO;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class SysViolationRecordOverSpendBizImpl extends BaseBiz implements ISysViolationRecordOverSpendBiz
{
	private ISysViolationRecordOverSpendDao sysViolationRecordOverSpendDao;
	
	public ISysViolationRecordOverSpendDao getSysViolationRecordOverSpendDao() {
		return sysViolationRecordOverSpendDao;
	}

	public void setSysViolationRecordOverSpendDao(ISysViolationRecordOverSpendDao sysViolationRecordOverSpendDao) {
		this.sysViolationRecordOverSpendDao = sysViolationRecordOverSpendDao;
	}

	@Override
	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage) {
		// TODO Auto-generated method stub
		return null;
	}
  
	@Override
	public SysViolationRecord getSysViolationRecordById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysViolationRecord saveSysViolationRecord(SysViolationRecord object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysViolationRecord updateSysViolationRecord(SysViolationRecord object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exportSysViolationRecord(ExportSetting exportSetting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPage<SysViolationRecordOverSpendDTO> findViolationRecordOverSpendTable(QueryPage queryPage) {
		logger.info("entering action::SysViolationRecordBlackListBizImpl.findViolationRecordBlackListTable()...");
		return sysViolationRecordOverSpendDao.findViolationRecordOverSpendTable(queryPage);
	}







}
