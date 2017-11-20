/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;

import com.ibs.core.module.account.common.ConstantsCnlAcnt;
import com.ibs.core.module.account.dao.ICnlCustAcntDao;
import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustAcntDto;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceServiceBalance;
import com.ibs.core.module.account.domain.OperatingAcntDto;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustAcntDaoImpl extends BaseEntityDao<CnlCustAcnt> implements
		ICnlCustAcntDao {
/**
 * 总帐户查询
 */
	public IPage<CnlCustAcntDto> findCnlCustAcntByPage(QueryPage queryPage,String custType,String cnlCustCode,String localName,String currency,
			String statisticalType,String max,String min) {
		logger.debug("entering dao::findCnlCustAcntByPage()...");
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		
		StringBuffer hql = new StringBuffer();
		hql.append("select cca.id as id,cca.cnlCustCode as cnlCustCode,cca.currency as currency,cc.localName as localName,cca.balanceAvalible as balanceAvalible,cca.balanceFrozen as balanceFrozen,cca.balance as balance,cca.acntStatus as acntStatus");
		hql.append(" from CnlCustAcnt cca,CnlCust cc where cca.cnlCustCode=cc.cnlCustCode and cca.cnlCnlCode=cc.cnlCnlCode") .append(" and cca.isValid = '").append(Constants.IS_VALID_VALID).append("'");
		
		if(StringUtils.isNotEmpty(custType)){
			hql.append(" and cc.cnlCustType like ?");
			queryPage.addQueryCondition("custType","%"+custType+"%");
			
		}
		if(StringUtils.isNotEmpty(cnlCustCode)){
			hql.append(" and cca.cnlCustCode = ?");
			queryPage.addQueryCondition("cnlCustCode",cnlCustCode);
		}
		if(StringUtils.isNotEmpty(localName)){
			hql.append(" and cc.localName like ?");
			queryPage.addQueryCondition("localName", "%"+localName+"%");
		}
		
		if(StringUtils.isNotEmpty(currency)){
			hql.append(" and cca.currency like ?");
			queryPage.addQueryCondition("currency", "%"+currency+"%");
		}
		
		
			if(StringUtils.equals(statisticalType, "balanceAvalible")){
				if(StringUtils.isNotEmpty(min)||StringUtils.isNotEmpty(max)){
					hql.append(" and cca.balanceAvalible<=? ");
					queryPage.addQueryCondition("max", new BigDecimal(max));
					hql.append(" and cca.balanceAvalible>=? ");
					queryPage.addQueryCondition("min", new BigDecimal(min));
					
				}
			}
				
				if(StringUtils.equals(statisticalType, "balanceFrozen")){
					if(StringUtils.isNotEmpty(min)||StringUtils.isNotEmpty(max)){
						hql.append(" and cca.balanceFrozen<=? ");
						queryPage.addQueryCondition("max",new BigDecimal(max));
						hql.append(" and cca.balanceFrozen>=? ");
						queryPage.addQueryCondition("min",new BigDecimal(min));
					}
				}
			
			
				queryPage.setHqlString(hql.toString());
		
		
		logger.debug("entering action::CnlCustAcntDaoImpl.findCnlCustAcntByPage()...");
		
		
		
		
		return super.findPageByHql(queryPage,CnlCustAcntDto.class);
	}
	
	/**
	 * 分帐户查询
	 */
	@Override
	public IPage<OperatingAcntDto> findOperatingAcntByPage(QueryPage queryPage, String custType, String cnlCustCode,
			String localName, String currency, String groupOfAccounts, String statisticalType, String max, String min) {
		logger.info("entering dao::findOperatingAcntByPage()...");
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		StringBuffer hql = new StringBuffer();
	  hql.append("select cca.id as id,cca.cnlCustCode as cnlCustCode,cc.localName as localName,cca.cnlAcntCode as cnlAcntCode,cca.acntType as acntType,cca.currency as currency,cca.balanceAvalible as balanceAvalible,cca.balanceFrozen as balanceFrozen,cca.balance as balance");
	  hql.append(" from CnlCustAcnt cca,CnlCust cc where cca.cnlCustCode=cc.cnlCustCode").append(" and cca.isValid = '").append(Constants.IS_VALID_VALID).append("'");
	  
	  if(StringUtils.isNotEmpty(custType)){
			hql.append(" and cc.cnlCustType like ?");
			queryPage.addQueryCondition("custType", "%"+custType+"%");
		}
		if(StringUtils.isNotEmpty(cnlCustCode)){
			hql.append(" and cca.cnlCustCode like ?");
			queryPage.addQueryCondition("cnlCustCode", cnlCustCode);
		}
		if(StringUtils.isNotEmpty(localName)){
			hql.append(" and cc.localName like ?");
			queryPage.addQueryCondition("localName", "%"+localName+"%");
		}
		
		if(StringUtils.isNotEmpty(currency)){
			hql.append(" and cca.currency like ?");
			queryPage.addQueryCondition("currency", "%"+currency+"%");
		}
		
		if(StringUtils.isNotEmpty(groupOfAccounts)){
			hql.append(" and cca.acntType like ?");
			queryPage.addQueryCondition("groupOfAccounts", "%"+groupOfAccounts+"%");
		}
		
		
			if(StringUtils.equals(statisticalType, "balanceAvalible")){
				if(StringUtils.isNotEmpty(min)||StringUtils.isNotEmpty(max)){
					hql.append(" and cca.balanceAvalible<=? ");
					queryPage.addQueryCondition("max",new BigDecimal(max));
					hql.append(" and cca.balanceAvalible>=? ");
					queryPage.addQueryCondition("min", new BigDecimal(min));
					
				}
			}
				
				if(StringUtils.equals(statisticalType, "balanceFrozen")){
					if(StringUtils.isNotEmpty(min)||StringUtils.isNotEmpty(max)){
						hql.append(" and cca.balanceFrozen<=? ");
						queryPage.addQueryCondition("max", new BigDecimal(max));
						hql.append(" and cca.balanceFrozen>=? ");
						queryPage.addQueryCondition("min", new BigDecimal(min));
					}
				}
	  
	  
	  
	           queryPage.setHqlString(hql.toString());
		return super.findPageByHql(queryPage, OperatingAcntDto.class);
	}

	
	
	
	
	public CnlCustAcnt load(String id) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.load()..."+id);
		return super.load(id);
	}

	public void saveOrUpdate(CnlCustAcnt cnlCustAcnt) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.saveOrUpdate()..."+cnlCustAcnt);
		super.saveOrUpdate(cnlCustAcnt);
	}

	@Override
	public CnlCustAcnt loadBy(String cnlCustCode) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.loadBy()..."+cnlCustCode);
		 CnlCustAcnt cnlCustAcnt = super.loadBy("cnlCustCode", cnlCustCode);
		 return cnlCustAcnt;
	}

	@Override
	public void saveCnlCustAcnt(CnlCustAcnt cnlCustAcnt) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.saveCnlCustAcnt()..."+cnlCustAcnt);
		super.save(cnlCustAcnt);
		
	}
	@Override
	public void updateCnlCustAcnt(CnlCustAcnt cnlCustAcnt){
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateCnlCustAcnt()..."+cnlCustAcnt);
		super.update(cnlCustAcnt);
	}
	
	/**
	 * duxiaolin修改于2016年8月2日15:47:46   根据客户编码查询账户  xw同用
	 * @param custCode
	 * @return
	 */
	@Override
	public CnlCustAcnt findByCustCode(String custCode) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.findByCustCode()..."+custCode);
		return super.loadBy("cnlCustCode", custCode);
	}
	
	
	
	
	@Override
	public BigDecimal findByBal(String cnlCustCode) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.findByBal()..."+cnlCustCode);
		
		String hql = "from CnlCustAcnt where cnlCustCode=? and acntStatus=? and isValid=? ";
		List<Object>  args = new ArrayList<Object>();
		args.add(cnlCustCode);
		args.add(Constants.ACNT_STATUS_NORMAL);
		args.add(Constants.IS_VALID_VALID);
		List<CnlCustAcnt> list = super.find(hql, args);
		 if(null!=list&&list.size()>0){
			 BigDecimal b = list.get(0).getBalanceAvalible();
			 return b;
			}
		 return null;
	}

	/**
	 * 查询余额
	 * @param cnlCnlCode
	 * @param cnlCustCode
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	@Override
	public List<CnlTransTraceServiceBalance> findBlance(String cnlCnlCode, String cnlCustCode, String pageSize,
			String pageIndex) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.findBlance()..."+cnlCnlCode+cnlCustCode+pageSize+pageIndex);
		int rowConut = Integer.parseInt(pageSize)*Integer.parseInt(pageIndex);
		int notCount = Integer.parseInt(pageSize)*(Integer.parseInt(pageIndex)-1);
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (SELECT rownum as r,");
		sql.append(" b.LOCAL_NAME AS name ,");
		sql.append(" a.CNL_CUST_CODE AS cnl_customer_code ,");
		sql.append(" a.BALANCE AS balance");
		sql.append(" FROM");
		sql.append(" CNL.T_CNL_CUST_ACNT a ");
		sql.append(" INNER JOIN ");
		sql.append(" CNL.T_CNL_CUST b");
		sql.append(" ON  ");
		sql.append(" a.CNL_CUST_CODE = b.CNL_CUST_CODE ");
		sql.append(" WHERE");
		sql.append(" a.CNL_CNL_CODE = '"+ cnlCnlCode +"' ");
		sql.append(" AND a.IS_VALID = '"+Constants.IS_VALID_VALID+"'");
		sql.append(" AND a.ACNT_STATUS = '"+Constants.ACNT_STATUS_NORMAL+"'");
		if(cnlCustCode!=null){
			sql.append(" AND a.CNL_CUST_CODE = '"+cnlCustCode+"'");
		}
		sql.append(" and rownum <="+rowConut+" ) where r>"+notCount);
		SQLQuery query= this.getSession().createSQLQuery(sql.toString());
		query.addScalar("name",new StringType() );
		query.addScalar("cnl_customer_code",new StringType() );
		query.addScalar("balance",new StringType() );
		List<Object[]> l = query.list();
		List<CnlTransTraceServiceBalance> list = new ArrayList<CnlTransTraceServiceBalance>();
		if(query.list().size()>0){
			for (Object[] c : l) {
				CnlTransTraceServiceBalance crsb =new CnlTransTraceServiceBalance();
				if(c[0]!=null){
					crsb.setName(c[0].toString());
				}
				if(c[1]!=null){
					crsb.setCnl_customer_code(c[1].toString());
				}
				if(c[2]!=null){
					crsb.setBalance(c[2].toString());
				}
				list.add(crsb);
			}
		}
		return (list.size()>0)?list:null;
	}

	@Override
	public int blanceCount(String cnlCnlCode, String cnlCustCode) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.blanceCount()..."+cnlCnlCode+cnlCustCode);
		Criteria criteria = getSession().createCriteria(CnlCustAcnt.class);
		criteria.add(Restrictions.eq("cnlCnlCode", cnlCnlCode));
		criteria.add(Restrictions.eq("isValid", Constants.IS_VALID_VALID));
		criteria.add(Restrictions.eq("acntStatus", Constants.ACNT_STATUS_NORMAL));
		if(cnlCustCode!=null&&!"".equals(cnlCustCode)){
			criteria.add(Restrictions.eq("cnlCustCode", cnlCustCode));
		}
		criteria.setProjection(Projections.rowCount());
		int count = (Integer) criteria.uniqueResult();
		return count;
	}
	
	/**
	 * 总账户余额提现
	 * 
	 */
	public boolean updateBalanceDebit(List<CnlTransTrace> list) {
		
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateBalanceDebit()..."+list);
		for (int i = 0; i < list.size(); i++) {
			getHibernateTemplate().bulkUpdate("UPDATE CnlCustAcnt SET balance=balance-? WHERE custCode=?",
					new Object[] { list.get(i).getTransAmount(), list.get(i).getCustCode() });
		}

		return true;
	}
	/**
	 * 
	 * 总账户余额充值
	 */
	public boolean updateBalanceCridit(List<CnlTransTrace> list) {
		
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateBalanceCridit()..."+list);
		
		for (int i = 0; i < list.size(); i++) {
			getHibernateTemplate().bulkUpdate("UPDATE CnlCustAcnt SET balance=balance+? WHERE custCode=?",
					new Object[] { list.get(i).getTransAmount(), list.get(i).getCustCode() });
		}

		return true;
	}
	/**
	 * 总账户余额充值提现
	 * 
	 */
	public boolean updateBalance(CnlCustAcnt cnlCustAcnt) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateBalance()..."+cnlCustAcnt);
			super.update(cnlCustAcnt);
		return true;
	}
	
	@Override
	public CnlCustAcnt findCompanyByCnlCnlCode(String cnlCnlCode) {
		// TODO Auto-generated method stub
		logger.debug("entering dao::CnlCustAcntDaoImpl.findCompanyByCnlCnlCode()..."+cnlCnlCode);
		return null;
	}

	@Override
	public CnlCustAcnt findPersonalByCnlCnlCodeAndCnlCustCode(String cnlCnlCode, String cnlCustCode) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.findPersonalByCnlCnlCodeAndCnlCustCode()..."+cnlCnlCode+cnlCustCode);
		DetachedCriteria criteria = DetachedCriteria.forClass(CnlCustAcnt.class);
		criteria.add(Restrictions.eq("cnlCnlCode", cnlCnlCode)).add(Restrictions.eq("cnlCustCode", cnlCustCode)).add(Restrictions.eq("isValid", Constants.IS_VALID_VALID));
		List<CnlCustAcnt> list = super.getHibernateTemplate().findByCriteria(criteria);
		return (list.size()>0)?(CnlCustAcnt)list.get(0):null;
	}
	
	/**
	 * 1提现出金成功 渠道金额变化
	 */
	public boolean updateChannelDibetSuccess(String cnlCnlCode,String cnlCustCode, BigDecimal amount) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.findPersonalByCnlCnlCodeAndCnlCustCode()..."+cnlCnlCode+cnlCustCode+amount);
		String hql = "update CnlCustAcnt set balance=balance-?, balanceAvalible=balanceAvalible-?,updateTime=?,updator=? where cnlCnlCode=? and cnlCustCode=? and isValid=?";
		getHibernateTemplate().bulkUpdate(hql, new Object[] { amount,amount,new Date(),Constants.SYSADMIN, cnlCnlCode,cnlCustCode,Constants.IS_VALID_VALID });
		return true;
	}

	/**
	 * 2提现出金成功 个人或企业金额变化
	 */
	public boolean updateChannelCustDibetSuccess(String cnlCnlCode,String cnlCustCode, BigDecimal amount) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateChannelCustDibetSuccess()..."+cnlCnlCode+cnlCustCode+amount);
		String hql = "update CnlCustAcnt set balance=balance-?, balanceFrozen=balanceFrozen-?,updateTime=?,updator=? where cnlCnlCode=? and cnlCustCode=? and isValid=? ";
		//String hql = "update CnlCustAcnt set balance=balance-? where id=?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{amount,amount,new Date(),Constants.SYSADMIN,cnlCnlCode,cnlCustCode,Constants.IS_VALID_VALID});
		return true;
	}
	/**
	 * 3提现出金失败 个人或企业金额变化
	 */
	public boolean updateChannelCustDibetFailed(String cnlCnlCode,String cnlCustCode, BigDecimal amount) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateChannelCustDibetFailed()..."+cnlCnlCode+cnlCustCode+amount);
		String hql = "update CnlCustAcnt set  balanceFrozen=balanceFrozen-?,balanceAvalible=balanceAvalible+?,updateTime=? ,updator=? where  cnlCnlCode=? and cnlCustCode=? and isValid=?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{amount,amount,new Date(),Constants.SYSADMIN,cnlCnlCode,cnlCustCode,Constants.IS_VALID_VALID});
		return true;
	}
	/**
	 * 4充值入金成功 企业金额变化
	 * 
	 * */
	public boolean updateCompanyCreditSuccess(CnlCustAcnt cnlCustAcnt, BigDecimal amount){
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateCompanyCreditFailed()..."+cnlCustAcnt+amount);

		String hql="update CnlCustAcnt set balance=balance+?,balanceAvalible=balanceAvalible+? where id=?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{amount,amount,cnlCustAcnt.getId()});
		return true;
	}
	
	/**
	 * 5充值入金失败 个人金额变化
	 * 
	 * */
	public boolean updatePersonalCreditFailed(CnlCustAcnt cnlCustAcnt, BigDecimal amount){
		logger.debug("entering dao::CnlCustAcntDaoImpl.updatePersonalCreditFailed()..."+cnlCustAcnt+amount);

		String hql="update CnlCustAcnt set balance=balance-?,balanceAvalible=balanceAvalible-? where id=?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{amount,amount,cnlCustAcnt.getId()});
		return true;
	}
	
	public boolean updatePersonalCreditSuccess(CnlCustAcnt cnlCustAcnt, BigDecimal amount){
//		String hql="update CnlCustAcnt set balance=balance+?,balanceAvalible=balanceAvalible+? where id=?";
		logger.debug("entering dao::CnlCustAcntDaoImpl.updatePersonalCreditSuccess()..."+cnlCustAcnt+amount);
	
		String hql="update CnlCustAcnt set balance=balance+? ,balanceAvalible=balanceAvalible+? where id=?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{amount,amount,cnlCustAcnt.getId()});
		return true;
	}

	@Override
	public BigDecimal findTransAmount(String cnlCustCode) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.updatePersonalCreditSuccess()..."+cnlCustCode);

		String sql = "select BALANCE from CNL.T_CNL_CUST_ACNT where CNL_CUST_CODE = ?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<Object> list = query.setParameter(0, cnlCustCode).list();
		BigDecimal obj = new BigDecimal(list.get(0).toString());
		return obj;
	}

	
	/**
	 * @author jicheng
	 * @param cnlCustAcnt
	 * @return
	 * 查询总帐户信息
	 */
	@Override
	public CnlCustAcnt findByCnlCustAcnt(CnlCustAcnt cnlCustAcnt){
		logger.debug("entering dao::CnlCustAcntDaoImpl.findByCnlCustAcnt()..."+cnlCustAcnt);

		Criteria criteria = getSession().createCriteria(CnlCustAcnt.class);
		criteria.add(Restrictions.eq("cnlCustCode",cnlCustAcnt.getCnlCustCode()))
		.add(Restrictions.eq("cnlCnlCode", cnlCustAcnt.getCnlCnlCode())).add(Restrictions.eq("isValid", Constants.IS_VALID_VALID))
		.add(Restrictions.eq("acntStatus", Constants.ACNT_STATUS_NORMAL));
		return (criteria.list().size()>0)?(CnlCustAcnt)criteria.list().get(0):null;
	}
	

	@SuppressWarnings("unused")
	@Override
	public void updateAmount(String stlNum) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateAmount()..."+stlNum);

		String sql = "update CNL.T_CNL_CUST_ACNT a SET a.BALANCE = a.BALANCE + (SELECT decode(trans_dc,?,TRANS_AMOUNT,trans_amount * -1) FROM CNL.T_CNL_TRANS_TRACE b where b.STL_NUM = ? and a.ACNT_STATUS='01' and a.CNL_CUST_CODE=b.CNL_CUST_CODE),"
				+ "a.BALANCE_AVALIBLE = a.BALANCE_AVALIBLE + (SELECT decode(trans_dc,?,TRANS_AMOUNT,trans_amount * -1) FROM CNL.T_CNL_TRANS_TRACE c where STL_NUM = ? and a.ACNT_STATUS='01' and a.CNL_CUST_CODE=c.CNL_CUST_CODE) "
				+ "where exists (SELECT CNL_CUST_CODE FROM CNL.T_CNL_TRANS_TRACE d where a.CNL_CUST_CODE=d.CNL_CUST_CODE and a.ACNT_STATUS='01' AND d.STL_NUM = ? )";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int update = query.setParameter(0, Constants.TRANS_DC_INCOMING).setParameter(1, stlNum).setParameter(2, Constants.TRANS_DC_INCOMING).setParameter(3, stlNum).setParameter(4, stlNum).executeUpdate();
	}

	@Override
	public void updateBlance(String cnlCustCode, BigDecimal amount) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateBlance()..."+cnlCustCode+amount);

		String sql = " update CNL.T_CNL_CUST_ACNT c set c.BALANCE_FROZEN = ( select  a.BALANCE_FROZEN  from CNL.T_CNL_CUST_ACNT a where a.CNL_CUST_CODE='"+cnlCustCode+"' and a.IS_VALID='"+Constants.IS_VALID_VALID+"')+'"+amount+"',"+
				" c.BALANCE_AVALIBLE = (select  b.BALANCE_AVALIBLE	from  CNL.T_CNL_CUST_ACNT b where b.CNL_CUST_CODE='"+cnlCustCode+"' and b.IS_VALID='"+Constants.IS_VALID_VALID+"')-'"+amount+"' where c.CNL_CUST_CODE = '"+cnlCustCode+"' and c.IS_VALID="+Constants.IS_VALID_VALID;
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public boolean cheackAccount(String cnlCnlCode, String cnlCustCode) {
		logger.debug("entering dao::CnlCustAcntDaoImpl.updateBlance()..."+cnlCnlCode+cnlCustCode);

		String sql = "select count(*) from  CNL.T_CNL_CUST_ACNT a where a.CNL_CUST_CODE='"+cnlCustCode+"' and a.CNL_CNL_CODE='"+cnlCnlCode+"' and a.IS_VALID='"+Constants.IS_VALID_VALID+"' and a.ACNT_STATUS='"+Constants.ACNT_STATUS_NORMAL+"'";
		Query query = getSession().createSQLQuery(sql);
		int ref = Integer.valueOf(query.uniqueResult().toString());
		if(ref>0){
			return true;
		}
		return false;
	}

	@Override
	public BigDecimal cheackBlance(String cnlCnlCode, String cnlCustCode) throws BizException{
		logger.debug("entering dao::CnlCustAcntDaoImpl.cheackBlance(String cnlCnlCode, String cnlCustCode)...cnlCnlCode:"+cnlCnlCode+"...cnlCustCode:"+cnlCustCode);
		String sql = "select c.BALANCE_AVALIBLE from CNL.T_CNL_CUST_ACNT c where c.CNL_CUST_CODE='"+cnlCustCode+"' and c.CNL_CNL_CODE='"+cnlCnlCode+"' and c.IS_VALID='"+Constants.IS_VALID_VALID+"'  and c.ACNT_STATUS='"+Constants.ACNT_STATUS_NORMAL+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		BigDecimal balance = (BigDecimal) query.uniqueResult();
		if(balance==null){
			throw new BizException("查找账户可用余额失败");
		}
		return balance;
	}
	
}
