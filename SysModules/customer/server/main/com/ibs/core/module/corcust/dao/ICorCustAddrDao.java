/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.corcust.dao;

import com.ibs.core.module.corcust.domain.CorCustAddr;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_ADDR
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustAddrDao {

	public IPage<CorCustAddr> findCorCustAddrByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorCustAddr corCustAddr);

	public CorCustAddr load(String id);

	public CorCustAddr load1(String custCode);
	
}
