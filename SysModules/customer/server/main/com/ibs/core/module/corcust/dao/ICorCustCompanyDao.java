/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.corcust.dao;

import com.ibs.core.module.corcust.domain.CorCustCompany;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustCompanyDao {

	public IPage<CorCustCompany> findCorCustCompanyByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorCustCompany corCustCompany);

	public CorCustCompany load(String id);

	public CorCustCompany load1(String custCode);
	
}
