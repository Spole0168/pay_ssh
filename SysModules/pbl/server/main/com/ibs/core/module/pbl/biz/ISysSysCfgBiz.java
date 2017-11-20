/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.pbl.biz;

import com.ibs.core.module.pbl.domain.SysSysCfg;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @create by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SYS_CFG
 */
public interface ISysSysCfgBiz {

	public IPage<SysSysCfg> findSysSysCfgByPage(QueryPage queryPage);

	public SysSysCfg getSysSysCfgById(String id);
	
	public SysSysCfg saveSysSysCfg(SysSysCfg object);
	
	public SysSysCfg updateSysSysCfg(SysSysCfg object);
	
	public void exportSysSysCfg(ExportSetting exportSetting);

}
