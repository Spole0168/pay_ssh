/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.cnlcust.dao.impl;

import com.ibs.core.module.cnlcust.dao.ICnlCustPersonalDao;
import com.ibs.core.module.cnlcust.domain.CnlCustPersonal;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustPersonalDaoImpl extends BaseEntityDao<CnlCustPersonal> implements
		ICnlCustPersonalDao {
	
	public IPage<CnlCustPersonal> findCnlCustPersonalByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustPersonalDaoImpl.findCnlCustPersonalByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlCustPersonal load(String id) {
		logger.info("entering action::CnlCustPersonalDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlCustPersonal cnlCustPersonal) {
		logger.info("entering action::CnlCustPersonalDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlCustPersonal);
	}

	@Override
	public CnlCustPersonal loadBy(String propertyName, String propertyValue) throws DaoException {
		// TODO Auto-generated method stub
		return super.loadBy(propertyName, propertyValue);
	}
	
	

}
