/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;

import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustAcntDto;
import com.ibs.core.module.account.domain.OperatingAcntDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustAcntBiz {

	public IPage<CnlCustAcntDto> findCnlCustAcntByPage(QueryPage queryPage,String custType,String cnlCustCode,String localName,String currency,
			String statisticalType,String max,String min);

	public CnlCustAcnt getCnlCustAcntById(String id);
	
	public CnlCustAcnt saveCnlCustAcnt(CnlCustAcnt object);
	
	public CnlCustAcnt updateCnlCustAcnt(CnlCustAcnt object);
	
	public void exportCnlCustAcnt(ExportSetting exportSetting);

	public IPage<OperatingAcntDto> findOperatingAcntByPage(QueryPage queryPage, String custType, String cnlCustCode,
			String localName, String currency, String groupOfAccounts, String statisticalType, String max, String min);

}
