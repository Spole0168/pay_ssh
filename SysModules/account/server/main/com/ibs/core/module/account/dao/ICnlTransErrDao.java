/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import com.ibs.core.module.account.domain.CnlTransErr;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_ERR
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransErrDao {

	public IPage<CnlTransErr> findCnlTransErrByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlTransErr cnlTransErr);

	public CnlTransErr load(String id);
	
}
