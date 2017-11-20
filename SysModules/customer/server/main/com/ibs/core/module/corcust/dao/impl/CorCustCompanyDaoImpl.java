/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.corcust.dao.impl;

import com.ibs.core.module.corcust.dao.ICorCustCompanyDao;
import com.ibs.core.module.corcust.domain.CorCustCompany;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustCompanyDaoImpl extends BaseEntityDao<CorCustCompany> implements
		ICorCustCompanyDao {

	public IPage<CorCustCompany> findCorCustCompanyByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustCompanyDaoImpl.findCorCustCompanyByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorCustCompany load(String id) {
		logger.info("entering action::CorCustCompanyDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorCustCompany corCustCompany) {
		logger.info("entering action::CorCustCompanyDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corCustCompany);
	}

	@Override
	public CorCustCompany load1(String custCode) {
		
		return super.loadBy("custCode", custCode);
	}

}
