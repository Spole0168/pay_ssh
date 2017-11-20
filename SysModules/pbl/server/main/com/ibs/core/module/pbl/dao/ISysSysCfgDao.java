/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.pbl.dao;

import com.ibs.core.module.pbl.domain.SysSysCfg;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @create by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SYS_CFG
 */
public interface ISysSysCfgDao {

	public IPage<SysSysCfg> findSysSysCfgByPage(QueryPage queryPage);
	
	public void saveOrUpdate(SysSysCfg sysSysCfg);

	public SysSysCfg load(String id);
	
}
