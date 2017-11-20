/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.violationRecord.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.violationRecord.domain.SysEmailHis;
import com.ibs.core.module.violationRecord.domain.SysEmailHisHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_EMAIL_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysEmailHisDao {

	public IPage<SysEmailHis> findSysEmailHisByPage(QueryPage queryPage);
	
	public void saveOrUpdate(SysEmailHis sysEmailHis);

	public SysEmailHis load(String id);
	
	public List<SysEmailHis> findSysEmailHis();
	
	public void saveSysEmailHisHis(Collection<SysEmailHisHis> sysEmailHisHisList);
	
	public boolean deleteSysEmailHis();
	
}
