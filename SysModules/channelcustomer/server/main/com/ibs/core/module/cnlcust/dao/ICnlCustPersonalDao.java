/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.cnlcust.dao;

import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.cnlcust.domain.CnlCustPersonal;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustPersonalDao {

	public IPage<CnlCustPersonal> findCnlCustPersonalByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlCustPersonal cnlCustPersonal);

	public CnlCustPersonal load(String id);

	public CnlCustPersonal loadBy(String string, String cnlCustCode);

}
