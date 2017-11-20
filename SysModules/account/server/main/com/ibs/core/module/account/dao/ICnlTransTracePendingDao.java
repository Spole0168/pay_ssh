/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransTracePendingDao {

	public IPage<CnlTransTrace> findCnlTransTraceByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlTransTrace cnlTransTrace);

	public CnlTransTrace load(String id);
	
	public void updateCnlTransTrace(CnlTransTrace cnlTransTrace);
	
}
