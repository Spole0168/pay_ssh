/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz;

import com.ibs.core.module.corcust.domain.CorCustPersonal;
import com.ibs.core.module.corcust.dto.CorCompound;
import com.ibs.core.module.corcust.dto.CorCondition;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCustPersonalBiz {

	public IPage<CorCustPersonal> findCorCustPersonalByPage(QueryPage queryPage);

	public CorCustPersonal saveCorCustPersonal(CorCustPersonal object);
	
	public CorCustPersonal updateCorCustPersonal(CorCustPersonal object);
	
	public void exportCorCustPersonal(ExportSetting exportSetting);

	public CorCompound getCorCompoundByPersonalId(String id);
	
	public void saveCorCompound(CorCompound corCompound);

	public void updateCorCompound(CorCompound corCompound);

	public IPage<CorCompound> findCorCorCompoundByPage(QueryPage queryPage, CorCondition corCondition);

}
