/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.bank.dao.impl;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;

import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.bank.dao.ICorBankAcntDao;
import com.ibs.core.module.bank.domain.CorBankAcnt;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankAcntDaoImpl extends BaseEntityDao<CorBankAcnt> implements
		ICorBankAcntDao {

	public IPage<CorBankAcnt> findCorBankAcntByPage(QueryPage queryPage) {
		logger.debug("entering dao::CorBankAcntDaoImpl.findCorBankAcntByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorBankAcnt load(String id) {
		logger.debug("entering dao::CorBankAcntDaoImpl.load()..."+id);
		return super.load(id);
	}

	public void saveOrUpdate(CorBankAcnt corBankAcnt) {
		logger.debug("entering dao::CorBankAcntDaoImpl.saveOrUpdate()..."+corBankAcnt);
		super.saveOrUpdate(corBankAcnt);
	}

	@Override
	public void saveCorBankAcnt(CorBankAcnt corBankAcnt) {
		logger.debug("entering dao::CorBankAcntDaoImpl.saveCorBankAcnt()..."+corBankAcnt);
		// TODO Auto-generated method stub
		super.save(corBankAcnt);
		
	}

	@Override
	public void updateCorBankAcnt(CorBankAcnt corBankAcnt) {
		logger.debug("entering dao::CorBankAcntDaoImpl.updateCorBankAcnt()..."+corBankAcnt);

		// TODO Auto-generated method stub
		super.update(corBankAcnt);
	}

	@Override
	public List<CorBankAcnt> findByCorBankAcnt(CorBankAcnt corBankAcnt) {
		logger.debug("entering dao::CorBankAcntDaoImpl.findByCorBankAcnt()..."+corBankAcnt);

		// TODO Auto-generated method stub
		List<CorBankAcnt> acnt = super.findBy(corBankAcnt);
		
		return acnt;
	}

	@Override
	public CorBankAcnt findByCustCode(String cnlCustCode) {
		logger.debug("entering dao::CorBankAcntDaoImpl.findByCustCode()..."+cnlCustCode);
		Criteria criteria = getSession().createCriteria(CorBankAcnt.class);
		criteria.add(Restrictions.eq("cnlCustCode",cnlCustCode))
		.add(Restrictions.eq("isValid", Constants.IS_VALID_VALID));
		return (criteria.list().size()>0)?(CorBankAcnt)criteria.list().get(0):null;
		
	}

	/**
	 *  银行转账所用：根据银行卡号和渠道客户编码 查询一条记录
	 */
	@SuppressWarnings("all")
	@Override
	public CorBankAcnt findByCardNumAndCnlCustCode(String cardNum, String cnlCustCode) {
		logger.debug("entering dao::CorBankAcntDaoImpl.findByCardNumAndCnlCustCode()..."+cardNum+cnlCustCode);

		String isValid = Constants.IS_VALID_VALID;
		String hql = "from CorBankAcnt where bankCardNum = ? and cnlCustCode = ? and isValid = ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(cardNum);
		paramList.add(cnlCustCode);
		paramList.add(isValid);
		List<CorBankAcnt> list = super.find(hql, paramList);
		
		return (list == null || list.isEmpty())?null:list.get(0);
	}

   public CorBankAcnt findByBankAcntCode(String bankAcntCode){
		logger.debug("entering dao::CorBankAcntDaoImpl.findByBankAcntCode()..."+bankAcntCode);

	   Criteria criteria = getSession().createCriteria(CorBankAcnt.class);
	   criteria.add(Restrictions.eq("bankAcntCode", bankAcntCode)).add(Restrictions.eq("isValid", Constants.IS_VALID_VALID));
	   return (criteria.list().size()>0)?(CorBankAcnt)criteria.list().get(0):null;
   }
}
