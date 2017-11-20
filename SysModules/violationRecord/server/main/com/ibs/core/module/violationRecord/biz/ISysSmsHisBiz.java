/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.biz;

import com.ibs.core.module.violationRecord.domain.SysSmsHis;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SMS_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysSmsHisBiz {

	public IPage<SysSmsHis> findSysSmsHisByPage(QueryPage queryPage);

	public SysSmsHis getSysSmsHisById(String id);
	
	public SysSmsHis saveSysSmsHis(SysSmsHis object);
	
	public SysSmsHis updateSysSmsHis(SysSmsHis object);
	
	public void exportSysSmsHis(ExportSetting exportSetting);

}
