/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.pbl.dao.impl;

import com.ibs.core.module.pbl.dao.ISysSysCfgDao;
import com.ibs.core.module.pbl.domain.SysSysCfg;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @create by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SYS_CFG
 */
public class SysSysCfgDaoImpl extends BaseEntityDao<SysSysCfg> implements
		ISysSysCfgDao {

	public IPage<SysSysCfg> findSysSysCfgByPage(QueryPage queryPage) {
		logger.info("entering action::SysSysCfgDaoImpl.findSysSysCfgByPage()...");
		return super.findPageBy(queryPage);
	}

	public SysSysCfg load(String id) {
		logger.info("entering action::SysSysCfgDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(SysSysCfg sysSysCfg) {
		logger.info("entering action::SysSysCfgDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(sysSysCfg);
	}

}
