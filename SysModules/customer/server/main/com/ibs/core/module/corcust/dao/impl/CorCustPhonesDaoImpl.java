/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.corcust.dao.impl;

import java.util.List;

import com.ibs.core.module.corcust.dao.ICorCustPhonesDao;
import com.ibs.core.module.corcust.domain.CorCustPhones;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PHONES
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustPhonesDaoImpl extends BaseEntityDao<CorCustPhones> implements
		ICorCustPhonesDao {

	public IPage<CorCustPhones> findCorCustPhonesByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustPhonesDaoImpl.findCorCustPhonesByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorCustPhones load(String id) {
		logger.info("entering action::CorCustPhonesDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorCustPhones corCustPhones) {
		logger.info("entering action::CorCustPhonesDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corCustPhones);
	}

	/**
	 * 未写具体办法，此方法在用户只有一个电话的情况下成立
	 */
	@Override
	public CorCustPhones getByCustCode(String custCode, String isDefault, String phoneType) {
		return super.loadBy("custCode", custCode);
	}
	
	@Override
	public CorCustPhones load1(String custCode) {
		return super.loadBy("custCode", custCode);
	}

}
