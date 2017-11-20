/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.corcust.dao.impl;

import com.ibs.core.module.corcust.dao.ICorCustAddrDao;
import com.ibs.core.module.corcust.domain.CorCustAddr;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_ADDR
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustAddrDaoImpl extends BaseEntityDao<CorCustAddr> implements
		ICorCustAddrDao {

	public IPage<CorCustAddr> findCorCustAddrByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustAddrDaoImpl.findCorCustAddrByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorCustAddr load(String id) {
		logger.info("entering action::CorCustAddrDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorCustAddr corCustAddr) {
		logger.info("entering action::CorCustAddrDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corCustAddr);
	}
	
	@Override
	public CorCustAddr load1(String custCode) {
		return super.loadBy("custCode", custCode);
	}

}
