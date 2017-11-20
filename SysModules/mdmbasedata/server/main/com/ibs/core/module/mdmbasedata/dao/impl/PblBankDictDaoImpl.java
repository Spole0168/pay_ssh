/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.mdmbasedata.dao.impl;



import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.mdmbasedata.dao.IPblBankDictDao;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_BANK_DICT
 * @modify		: your comments goes here (author,date,reason).
 */
public class PblBankDictDaoImpl extends BaseEntityDao<PblBankDict> implements
		IPblBankDictDao {

	public IPage<PblBankDict> findPblBankDictByPage(QueryPage queryPage) {
		logger.info("entering action::PblBankDictDaoImpl.findPblBankDictByPage()...");
		return super.findPageBy(queryPage);
	}

	public PblBankDict load(String id) {
		logger.info("entering action::PblBankDictDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(PblBankDict pblBankDict) {
		logger.info("entering action::PblBankDictDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(pblBankDict);
	}
	
	public PblBankDict findByBankBranchCode(String bankBranchCode){
		logger.info("entering action::PblBankDictDaoImpl.findByBankBranchCode()...");
		return super.loadBy("bankBranchCode", bankBranchCode);
		/*Criteria criteria = getSession().createCriteria(PblBankDict.class);
		criteria.add(Restrictions.eq("bankBranchCode", bankBranchCode));
		return (criteria.list().size()>0)?(PblBankDict)criteria.list().get(0):null;*/
		
	}

}
