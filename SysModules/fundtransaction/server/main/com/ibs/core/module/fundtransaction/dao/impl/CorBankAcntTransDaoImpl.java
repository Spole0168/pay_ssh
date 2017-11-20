/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.fundtransaction.dao.impl;



import java.util.Collection;
import java.util.List;

import com.ibs.core.module.bank.domain.CorBankAcntTransHis;
import com.ibs.core.module.corcust.domain.CorDto;
import com.ibs.core.module.fundtransaction.dao.ICorBankAcntTransDao;
import com.ibs.core.module.fundtransaction.domain.CorBankAcntTrans;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;


/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankAcntTransDaoImpl extends BaseEntityDao<CorBankAcntTrans> implements ICorBankAcntTransDao {

	public IPage<CorDto> findCorBankAcntTransByPage(QueryPage queryPage, String st,String direction) {
		logger.info("entering action::CorBankAcntTransDaoImpl.findCorBankAcntTransByPage()...");
		StringBuffer str = new StringBuffer();
		System.out.println(direction);
		str.append(
				"select c.bankName as bankName,c.bankAcntCode as bankAcntCode,c.bankCardNum as bankCardNum,c.custName as custName,cb.transDate as transDate,"
						+ "cb.transTime as transTime,cb.direction as direction,"
						+ "cb.transType as transType,cb.bankTransNum as bankTransNum,cb.transCurrency as transCurrency,"
						+ "cb.amount as amount,cb.bankBalanceAfterTrans as bankBalanceAfterTrans,"
						+ "cb.bankAvaliableAmount as bankAvaliableAmount,cb.bankFrozenAmoumt as bankFrozenAmoumt,cb.transComments as transComments,"
						+ "cb.transRate as transRate,cb.documentStaff as documentStaff, cb.documentReviewer as documentReviewer, cb.certType as certType, "
						+ "cb.certNum as certNum, cb.interestsStartDate as interestsStartDate");

		if (direction.equals("01")) {
			str.append(" ,c.custName as otherCustName,cb.bankDebitName as otherBankName,"
					+ "cb.bankDebitCardNum as otherBankCarNum ,cb.bankNum as otherBankNum ");
		}
		if (direction.equals("02")) {
			str.append(" ,c.custName as otherCustName,cb.bankCreditName as otherBankName,"
					+ "cb.bankCreditCardNum as otherBankCarNum,cb.bankNum as otherBankNum");
		}
		str.append(" from CorReservedFundAcnt as c,CorBankAcntTrans as cb where c.bankNum=cb.bankNum");
		str.append(st);

		System.out.println(str.toString() + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		queryPage.setHqlString(str.toString());
		 IPage<CorDto> page = super.findPageByHql(queryPage, CorDto.class);
		 return page;
	}

	public CorBankAcntTrans load(String id) {
		logger.info("entering action::CorBankAcntTransDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorBankAcntTrans corBankAcntTrans) {
		logger.info("entering action::CorBankAcntTransDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corBankAcntTrans);
	}
	
	//更新批量处理操作 
	public void savaOrUpdateBatch(List<CorBankAcntTrans> list){
		logger.info("entering action::CorBankAcntTransDaoImpl.saveOrUpdateBatch()...");
		super.saveOrUpdateBatch(list);
	}

	@Override
	public List<CorBankAcntTrans> findCorBankAcntTrans() {
		String sql ="from CorBankAcntTrans where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<CorBankAcntTrans> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCorBankAcntTransHis(Collection<CorBankAcntTransHis> corBankAcntTransHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(corBankAcntTransHisList);
	}

	@Override
	public boolean deleteCorBankAcntTrans() {
		try {
			String sql = " delete from CorBankAcntTrans WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
			int execute = super.execute(sql);
			if (execute > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
