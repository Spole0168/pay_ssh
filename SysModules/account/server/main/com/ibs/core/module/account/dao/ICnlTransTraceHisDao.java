/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import java.util.List;

import com.ibs.core.module.account.domain.CnlTransTraceHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransTraceHisDao {

	public IPage<CnlTransTraceHis> findCnlTransTraceHisByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlTransTraceHis cnlTransTraceHis);

	public CnlTransTraceHis load(String id);

	public void save(List<CnlTransTraceHis> his);
	
}
