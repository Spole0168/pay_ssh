/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.biz;

import com.ibs.core.module.account.domain.CustPersonal;
import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.cnlcust.domain.CnlCust;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustBiz {

	public IPage<CustPersonal> findCnlCustByPage(QueryPage queryPage);

	public CnlCust getCnlCustById(String id);
	
	public CnlCust saveCnlCust(CnlCust object);
	
	public CnlCust updateCnlCust(CnlCust object);
	
	public void exportCnlCust(ExportSetting exportSetting);
	
	public IPage<CustPersonal> findCnlCustBylike(QueryPage queryPage,String cnlCustCode,String cnlSysName,String cnlCustType,String custStatus,
			String certType,String certNum,String realNamelevel,String custLevel,String createStartTime,String createEndTime);
	
}
