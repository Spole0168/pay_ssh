/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.biz;

import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitConditionDto;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlSysIntfIpLimitBiz {

	public CnlSysIntfIpLimitDto getCnlSysIntfById(String id);
	
	public CnlSysIntf updateCnlSysIntf(CnlSysIntfIpLimitDto cnlSysIntfIpLimitDto);
	
	public void exportCnlSysIntf(ExportSetting exportSetting);

	public IPage<CnlSysIntfIpLimitDto> findCnlSysIntfByPage(QueryPage queryPage,
			CnlSysIntfIpLimitConditionDto cnlSysIntfIpLimitConditionDto);

	public boolean checkCnlSysIntf(String cnlCustCode, String cnlIntfCode);

	public boolean deleteIpLimit(String id);

	public boolean checkIpLimit(String cnlCustCode, String cnlIntfCode);


}
