/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.corcust.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.bank.domain.CorBankAcntTransDtlCh;
import com.ibs.core.module.corcust.dao.ICorBankAcntTransDtlDao;
import com.ibs.core.module.corcust.domain.CorBankAcntTransDtl;
import com.ibs.core.module.corcust.domain.CorDto;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankAcntTransDtlDaoImpl extends BaseEntityDao<CorBankAcntTransDtl> implements
		ICorBankAcntTransDtlDao {

	public IPage<CorBankAcntTransDtl> findCorBankAcntTransDtlByPage(QueryPage queryPage) {
		logger.info("entering action::CorBankAcntTransDtlDaoImpl.findCorBankAcntTransDtlByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorBankAcntTransDtl load(String id) {
		logger.info("entering action::CorBankAcntTransDtlDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorBankAcntTransDtl corBankAcntTransDtl) {
		logger.info("entering action::CorBankAcntTransDtlDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corBankAcntTransDtl);
	}

	@Override
	public List<CorBankAcntTransDtl> findDtl(Date startTime,Date endTime) {
		String sql = "from CorBankAcntTransDtl c where (c.transTime between ? "
				+ "and ?) and c.bankTransNum not in (select a.bankTransNum from "
				+ "CorBankAcntTransDtl a ,CnlTrans b where a.amount = b.transAmount and a.direction != b.transDc "
				+ "and a.bankTransNum = b.bankTransNum)"; 
		List<Object> args = new ArrayList<Object>();
		args.add(startTime);
		args.add(endTime);
		List<CorBankAcntTransDtl> list = super.find(sql, args);
		if(list != null&&list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	public void saveBycollection(Collection<CorBankAcntTransDtl> corBankAcntTransDtlList){
		super.saveBatch(corBankAcntTransDtlList);
	}
	public IPage<CorDto> findCorBankAcntTransDtlByPage(QueryPage queryPage, String st, String direction) {
		logger.info("entering action::CorBankAcntTransDtlDaoImpl.findCorBankAcntTransDtlByPage()...");
		StringBuffer str = new StringBuffer();
		str.append("select c.bankName as bankName,c.bankNum as bankNum,c.bankCardNum as bankCardNum,c.custName as custName,cb.transDate as transDate,"
				+ "cb.transTime as transTime,cb.direction as direction,cb.transType as transType,cb.bankTransNum as bankTransNum,cb.transCurrency as transCurrency,"
				+ "cb.amount as amount,cb.bankBalanceAfterTrans as bankBalanceAfterTrans,cb.bankAvaliableAmount as bankAvaliableAmount,cb.bankFrozenAmoumt as bankFrozenAmoumt,"
				+ "cb.transComments as transComments,cb.transRate as transRate,cb.documentStaff as documentStaff,cb.documentReviewer as documentReviewer,cb.certType as certType,"
				+ "cb.certNum as certNum,cb.interestsStartDate as interestsStartDate,");
		if(direction.equals("01")){
			str.append("cb.bankDebitCustName as otherCustName,cb.bankCreditName as otherBankName,cb.bankCreditCardNum as otherBankCarNum,"
					+ "cb.bankNum as otherBankNum from CorReservedFundAcnt as c,CorBankAcntTransDtl as cb "
					+ "where c.bankAcntCode = cb.bankCreditAcntCode and cb.isValid ='01' ");
		}
		if(direction.equals("02")){
			str.append("cb.bankDebitCustName as otherCustName,cb.bankDebitName as otherBankName,cb.bankDebitCardNum as otherBankCarNum,"
					+ "cb.bankNum as otherBankNum from CorReservedFundAcnt as c,CorBankAcntTransDtl as cb "
					+ "where c.bankAcntCode = cb.bankDebitAcntCode and cb.isValid ='01' ");
		}
		str.append(st);
		queryPage.setHqlString(str.toString());
		 IPage<CorDto> page = super.findPageByHql(queryPage, CorDto.class);
		 return page;
	}
	
	@Override
	public void updateStatus(List<CorBankAcntTransDtl> listDtl) {
		String sql = null;
		if(listDtl != null && listDtl.size() != 0){
			StringBuffer sb = new StringBuffer("");
			for(int i = 0 ;i <listDtl.size();i++){
				sb.append("'"+listDtl.get(i).getBankTransNum()+"'");
				if(i != listDtl.size()-1)
					sb.append(",");
			}
			sql = "update COR.T_COR_BANK_ACNT_TRANS_DTL set TRANS_STATUS = '01' where BANK_TRANS_NUM not in (" + sb.toString() +")";
		}else{
			sql = "update COR.T_COR_BANK_ACNT_TRANS_DTL set TRANS_STATUS = '01'";
		}
		super.executeSql(sql);
	}

	@Override
	public void updateStatus2(List<CorBankAcntTransDtl> listDtl) {
		String sql = null;
		StringBuffer sb = new StringBuffer("");
		for(int i = 0 ;i <listDtl.size();i++){
			sb.append("'"+listDtl.get(i).getBankTransNum()+"'");
			if(i != listDtl.size()-1)
				sb.append(",");
		}
		sql = "update COR.T_COR_BANK_ACNT_TRANS_DTL set TRANS_STATUS = '02' where BANK_TRANS_NUM in (" + sb.toString() +")";
		super.executeSql(sql);
	}

	@Override
	public List<CorBankAcntTransDtl> findCorBankAcntTransDtl() {
		String sql = "from CorBankAcntTransDtl where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<CorBankAcntTransDtl> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCorBankAcntTransDtlCh(Collection<CorBankAcntTransDtlCh> corBankAcntTransDtlChList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(corBankAcntTransDtlChList);
		
	}

	@Override
	public boolean deleteCorBankAcntTransDtl() {
		try {
			String sql = " delete from CorBankAcntTransDtl WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
