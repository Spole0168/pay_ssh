/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.violationRecord.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordHis;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordIPExceptionDTO;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_VIOLATION_RECORD
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysViolationRecordDao {

	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage);
	
	public void saveOrUpdate(SysViolationRecord sysViolationRecord);

	public SysViolationRecord load(String id);

	public IPage<SysViolationRecordIPExceptionDTO> findViolationRecordTable(QueryPage queryPage);
	
	/**
	 * 批量操作
	 * @param list
	 */
	public  void  saveBatch(List<SysViolationRecord>list);

/*	public IPage<SysViolationRecordBlackListDTO> findViolationRecordBlackListTable(QueryPage queryPage);
	
	public IPage<SysViolationRecordOverSpendDTO> findViolationRecordOverSpendTable(QueryPage queryPage);*/
	
	public List<SysViolationRecord> findSysViolationRecord();
	
	public void saveSysViolationRecordHis(Collection<SysViolationRecordHis> sysViolationRecordHisList);
	
	public boolean deleteSysViolationRecord();
	
  	
}
