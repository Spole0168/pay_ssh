package com.ibs.core.module.violationRecord.dao;

import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordOverSpendDTO;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface ISysViolationRecordOverSpendDao 
{
public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage);
	
	public void saveOrUpdate(SysViolationRecord sysViolationRecord);

	public SysViolationRecord load(String id);

  
	public IPage<SysViolationRecordOverSpendDTO> findViolationRecordOverSpendTable(QueryPage queryPage);
	
}
