/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.IntegerType;

import com.ibs.core.module.account.dao.ICnlCustTraceDao;
import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.core.module.account.domain.CnlTransTraceServiceBalance;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustTraceDaoImpl extends BaseEntityDao<CnlCustTrace> implements
		ICnlCustTraceDao {

	public IPage<CnlCustTrace> findCnlCustTraceByPage(QueryPage queryPage) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findCnlCustTraceByPage()..."+queryPage);
		return super.findPageBy(queryPage);
	}

	public CnlCustTrace load(String id) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.load()..."+id);
		return super.load(id);
	}

	public void saveOrUpdate(CnlCustTrace cnlCustTrace) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.saveOrUpdate()..."+cnlCustTrace);
		super.saveOrUpdate(cnlCustTrace);
	}

	/**
	 * 修改申请单状态
	 */
	public int updateReqStatus(String reqInnerNum,String reqStatus, String errMsg) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.updateReqStatus()..."+reqInnerNum+reqStatus);
		//创建sql
		
		String s ="update CNL.T_CNL_CUST_TRACE set REQ_STATUS=?,err_msg=?, UPDATE_TIME=?,UPDATOR=? where REQ_INNER_NUM=? and is_Valid=?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(s);
		int update = query.setParameter(0, reqStatus).setParameter(1, errMsg).setParameter(2, new Date()).setParameter(3, Constants.SYSADMIN)
				.setParameter(4, reqInnerNum).setParameter(5, Constants.IS_VALID_VALID).executeUpdate();
		return update;
	}
	
	@Override
	public CnlCustTrace findByCustCode(String custCode) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findByCustCode()..."+custCode);

		return super.loadBy("cnlCustCode", custCode);
	}

	@Override
	public CnlCustTrace findFirstTrace(String cnlCustCode, String reqTraceType) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findFirstTrace()..."+cnlCustCode+reqTraceType);

		String hql = "from CnlCustTrace cnl where cnl.cnlCustCode = ? and cnl.reqType = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(cnlCustCode);
		list.add(reqTraceType);
		List<CnlCustTrace> cnlCustTraces = super.find(hql, list);
		if(cnlCustTraces.size() == 1)
			return cnlCustTraces.get(0);
		return null;
	}

	@Override
	public CnlCustTrace findByInnerNum(String innerNum) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findByInnerNum()..."+innerNum);

		String hql = "from CnlCustTrace cnl where cnl.innerNum = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(innerNum);
		List<CnlCustTrace> cnlCustTraces = super.find(hql, list);
		if(cnlCustTraces.size() == 1)
			return cnlCustTraces.get(0);
		return null;
	}

	/**
	 * 验证是否有重复开户记录
	 */
	@Override
	public boolean findBooleanByCnlCustCodeAndCnlCnlCode(String cnlCustCode, String cnlCnlCode) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findBooleanByCnlCustCodeAndCnlCnlCode()..."+cnlCustCode+cnlCnlCode);

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM");
		sql.append(" CNL.T_CNL_CUST_TRACE c LEFT JOIN CNL.T_CNL_REQ_TRANS r");
		sql.append(" ON c.REQ_INNER_NUM = r.REQ_INNER_NUM");
		sql.append(" WHERE");    
		sql.append("  c.CNL_CUST_CODE = ?");
		sql.append(" AND r.CNL_CNL_CODE = ?");
		sql.append(" AND (c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_PENDING+"'");
		sql.append(" OR c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_HANDLING+"'");
		sql.append("  OR c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_BANK_TRANSFER_FAILED+"'");
		sql.append(" OR c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_WAITING_FOR_CUST_VALID+"')");
		
		SQLQuery query= this.getSession().createSQLQuery(sql.toString());
		query.setString(0,cnlCustCode);
		query.setString(1,cnlCnlCode);
		query.addScalar("count", new IntegerType());
		Integer count = (Integer) query.uniqueResult();
		if(count==null||count>0){
			
			return false;
		}else{
			
			return true;
		}	
	}

	@Override
	public boolean findBooleanByNameAndCretNum(String name, String cretNum) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findBooleanByNameAndCretNum()..."+name+cretNum);

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM");
		sql.append(" CNL.T_CNL_CUST_TRACE c ");
		sql.append(" WHERE");    
		sql.append(" c.CERT_NUM=? ");
		sql.append(" AND c.NAME = ?");
		sql.append(" AND (c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_PENDING+"'");
		sql.append(" OR c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_HANDLING+"'");
		sql.append("  OR c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_BANK_TRANSFER_FAILED+"'");
		sql.append(" OR c.REQ_STATUS = '"+Constants.CUST_TRACE_STATUS_WAITING_FOR_CUST_VALID+"')");
		
		SQLQuery query= this.getSession().createSQLQuery(sql.toString());
		query.setString(0,cretNum);
		query.setString(1,name);
		query.addScalar("count", new IntegerType());
		Integer count = (Integer) query.uniqueResult();
		if(count==null||count>0){
			
			return false;
		}else{
			
			return true;
		}		
	}

	@Override
	public CnlCustTrace findByReqInnerNum(String reqInnerNum) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findByReqInnerNum()...{}", reqInnerNum);
		String hql = "from CnlCustTrace where reqInnerNum = ? and isValid = ?";
		List<Object> args = new ArrayList<Object>();
		args.add(reqInnerNum);
		args.add(Constants.IS_VALID_VALID);
		
		List<CnlCustTrace> list = super.find(hql, args);
		
		return (list == null || list.isEmpty()) ? null:list.get(0);
	}

	@Override
	public CnlCustTrace findByCnlCustCode(String cnlCustCode, String cnlCnlcode) {
		logger.debug("entering dao::CnlCustTraceDaoImpl.findByCnlCustCode()...{}", cnlCustCode,cnlCnlcode);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" c.PROVIENCE AS provience ,");
		sql.append(" c.CITY AS city");
		sql.append(" FROM");
		sql.append(" CNL.T_CNL_CUST_TRACE c INNER JOIN CNL.T_CNL_REQ_TRANS r");
		sql.append(" ON c.REQ_INNER_NUM = r.REQ_INNER_NUM");
		sql.append(" WHERE");
		sql.append(" c.CNL_CUST_CODE = '"+cnlCustCode+"'");
		sql.append(" AND r.CNL_CNL_CODE = '"+cnlCnlcode+"'");
		sql.append("  AND c.REQ_STATUS='"+Constants.CUST_TRACE_STATUS_WAITING_FOR_CUST_VALID+"'");
		Query query = getSession().createSQLQuery(sql.toString());
		List<Object[]> l = query.list();
		List<CnlCustTrace> list = new ArrayList<CnlCustTrace>();
		if(query.list().size()>0){
			for (Object[] c : l) {
				CnlCustTrace crsb = new CnlCustTrace();
				crsb.setProvience(c[0].toString());
				crsb.setCity(c[1].toString());
				list.add(crsb);
			}
		}
		return (list.size()>0)?list.get(0):null;
	}
}
