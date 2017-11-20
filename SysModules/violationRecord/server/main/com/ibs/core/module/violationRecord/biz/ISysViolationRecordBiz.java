/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.biz;

import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordBlackListDTO;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordIPExceptionDTO;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordOverSpendDTO;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_VIOLATION_RECORD
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysViolationRecordBiz {
  
	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage);

	public SysViolationRecord getSysViolationRecordById(String id);
	
	public SysViolationRecord saveSysViolationRecord(SysViolationRecord object);
	
	public SysViolationRecord updateSysViolationRecord(SysViolationRecord object);
	
	public void exportSysViolationRecord(ExportSetting exportSetting);
	
	public IPage<SysViolationRecordIPExceptionDTO> findViolationRecordTable(QueryPage queryPage);

/*	public IPage<SysViolationRecordBlackListDTO> findViolationRecordBlackListTable(QueryPage queryPage);
	
	public IPage<SysViolationRecordOverSpendDTO> findViolationRecordOverSpendTable(QueryPage queryPage);
*/


}
