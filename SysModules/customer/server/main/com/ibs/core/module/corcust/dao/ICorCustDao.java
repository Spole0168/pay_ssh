/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.corcust.dao;

import com.ibs.core.module.corcust.domain.CorCust;
import com.ibs.core.module.corcust.dto.Cor;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustDao {

	public IPage<CorCust> findCorCustByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorCust corCust);

	public CorCust load(String id);

	public CorCust getByCustCode(String custCode);

	public IPage<Cor> findCorCustByPageAndMoreTable(QueryPage queryPage);
	
	public Cor load1(String id);

	public CorCust liad2(String custCode);

	public String getCustCodeById(String id);
}
