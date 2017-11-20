package com.ibs.core.module.violationRecord.biz.impl;


import com.ibs.core.module.violationRecord.biz.ISysViolationRecordBlackListBiz;
import com.ibs.core.module.violationRecord.dao.ISysViolationRecordBlackListDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordBlackListDTO;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class SysViolationRecordBlackListBizImpl extends BaseBiz implements ISysViolationRecordBlackListBiz
{


	private	ISysViolationRecordBlackListDao sysViolationRecordBlackListDao;

	
	public ISysViolationRecordBlackListDao getSysViolationRecordBlackListDao() {
		return sysViolationRecordBlackListDao;
	}

	public void setSysViolationRecordBlackListDao(ISysViolationRecordBlackListDao sysViolationRecordBlackListDao) {
		this.sysViolationRecordBlackListDao = sysViolationRecordBlackListDao;
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

	//黑名单
		@Override
		public IPage<SysViolationRecordBlackListDTO> findViolationRecordBlackListTable(QueryPage queryPage) {
			// TODO Auto-generated method stub
			logger.info("entering action::SysViolationRecordBlackListBizImpl.findViolationRecordBlackListTable()...");
			return sysViolationRecordBlackListDao.findViolationRecordBlackListTable(queryPage);

		}

	

	
}
