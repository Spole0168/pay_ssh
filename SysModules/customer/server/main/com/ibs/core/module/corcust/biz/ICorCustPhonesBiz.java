/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz;

import com.ibs.core.module.corcust.domain.CorCustPhones;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PHONES
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustPhonesBiz {

	public IPage<CorCustPhones> findCorCustPhonesByPage(QueryPage queryPage);

	public CorCustPhones getCorCustPhonesById(String id);
	
	public CorCustPhones saveCorCustPhones(CorCustPhones object);
	
	public CorCustPhones updateCorCustPhones(CorCustPhones object);
	
	public void exportCorCustPhones(ExportSetting exportSetting);

	public CorCustPhones load1(String custCode);
}
