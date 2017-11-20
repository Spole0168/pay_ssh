/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.interfaces.dao;

import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlReqTransInfDataInqueryDao {

	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlReqTrans cnlReqTrans);

	public CnlReqTrans load(String id);
	
}
