package com.ibs.core.module.violationRecord.biz;

import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordOverSpendDTO;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface ISysViolationRecordOverSpendBiz 
{
	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage);

	public SysViolationRecord getSysViolationRecordById(String id);
	
	public SysViolationRecord saveSysViolationRecord(SysViolationRecord object);
	
	public SysViolationRecord updateSysViolationRecord(SysViolationRecord object);
	
	public void exportSysViolationRecord(ExportSetting exportSetting);

	public IPage<SysViolationRecordOverSpendDTO> findViolationRecordOverSpendTable(QueryPage queryPage);
	
  


}
