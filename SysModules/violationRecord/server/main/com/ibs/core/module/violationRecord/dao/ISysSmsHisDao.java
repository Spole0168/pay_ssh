/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.violationRecord.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.violationRecord.domain.SysSmsHis;
import com.ibs.core.module.violationRecord.domain.SysSmsHisHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SMS_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysSmsHisDao {

	public IPage<SysSmsHis> findSysSmsHisByPage(QueryPage queryPage);
	
	public void saveOrUpdate(SysSmsHis sysSmsHis);

	public SysSmsHis load(String id);
	
	public List<SysSmsHis> findSysSmsHis();
	
	public void saveSysSmsHisHis(Collection<SysSmsHisHis> sysSmsHisHisList);
	
	public boolean deleteSysSmsHis();
}
