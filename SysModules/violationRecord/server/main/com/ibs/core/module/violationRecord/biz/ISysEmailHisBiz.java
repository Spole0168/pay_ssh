/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.biz;

import com.ibs.core.module.violationRecord.domain.SysEmailHis;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_EMAIL_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysEmailHisBiz {

	public IPage<SysEmailHis> findSysEmailHisByPage(QueryPage queryPage);

	public SysEmailHis getSysEmailHisById(String id);
	
	public SysEmailHis saveSysEmailHis(SysEmailHis object);
	
	public SysEmailHis updateSysEmailHis(SysEmailHis object);
	
	public void exportSysEmailHis(ExportSetting exportSetting);

}
