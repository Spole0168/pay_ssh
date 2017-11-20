/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.corcust.dao;

import com.ibs.core.module.corcust.domain.CorCustPhones;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PHONES
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustPhonesDao {

	public IPage<CorCustPhones> findCorCustPhonesByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorCustPhones corCustPhones);

	public CorCustPhones load(String id);

	public CorCustPhones getByCustCode(String custCode, String string, String string2);

	public CorCustPhones load1(String custCode);
}
