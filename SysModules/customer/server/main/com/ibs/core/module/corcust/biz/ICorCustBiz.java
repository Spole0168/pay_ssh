/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz;

import com.ibs.core.module.corcust.domain.CorCust;
import com.ibs.core.module.corcust.dto.Cor;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustBiz {

	public IPage<CorCust> findCorCustByPage(QueryPage queryPage);

	public CorCust getCorCustById(String id);
	
	public CorCust saveCorCust(CorCust object);
	
	public CorCust updateCorCust(CorCust object);
	
	public void exportCorCust(ExportSetting exportSetting);
	
	public IPage<Cor> findCorCustByPageAndMoreTable(QueryPage queryPage);

	public Cor load1(String id);

	public CorCust load2(String custCode);

}
