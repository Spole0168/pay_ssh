/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;

import com.ibs.core.module.account.dao.ICnlTransTraceDao;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlTransServiceClearing;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceDto;
import com.ibs.core.module.account.domain.CnlTransTraceServiceBalance;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransTraceDaoImpl extends BaseEntityDao<CnlTransTrace> implements ICnlTransTraceDao {

	public IPage<CnlTransTrace> findCnlTransTraceByPageTransFailed(QueryPage queryPage) {
		logger.info("entering action::CnlTransTraceDaoImpl.findCnlTransTraceByPageTransFailed()...");
		return super.findPageBy(queryPage);
	}
	
	public IPage<CnlTransTraceDto> findCnlTransTraceByPage(QueryPage queryPage, String msg) {
		logger.info("entering action::CnlTransTraceDaoImpl.findCnlTransTraceByPage()...");
		// 生成hql语句
		String condition = queryPage.getHqlString();
		StringBuffer s = new StringBuffer(
				"select t.id as id,t.bankTransNum as bankTransNum,d.transNum as transNum,d.bankMsgCode as bankMsgCode,t.transType as transType,t.custCode as custCode,t.cnlCustCode "
						+ "as cnlCustCode,t.reqBatch as reqBatch,t.transDc as transDc,t.transSubType as transSubType,t.transCurrency as transCurrency,t.transAmount as transAmount,t.transStatus "
						+ "as transStatus,t.transDate as transDate,t.transTime as transTime,t.transRate as transRate,t.transComments as transComments,t.bankAccepteTime as bankAccepteTime,"
						+ "t.bankHandleNum as bankHandleNum,t.bankReturnTime as bankReturnTime,t.bankPmtCnlType as bankPmtCnlType,t.bankPmtCnlCode as bankPmtCnlCode,t.cnlCnlCode as cnlCnlCode,"
						+ "t.isinGl as isinGl,t.isPrinted as isPrinted,t.bankCreditName as bankCreditName,t.bankCreditCustName as bankCreditCustName,t.bankCreditAcntCode as bankCreditAcntCode,"
						+ "t.bankDebitName as bankDebitName,t.bankDebitCustName as bankDebitCustName,t.bankDebitAcntCode as bankDebitAcntCode,t.bankReqTrnasDate as bankReqTrnasDate,t.bnakServiceFeeAct "
						+ "as bnakServiceFeeAct,t.bankReqTransTime as bankReqTransTime,t.bnakHandlePriority as bnakHandlePriority,t.errType as errType,t.errCode as errCode,t.errMsg as errMsg,"
						+ "t.handleStatus as handleStatus,t.handleMsg as handleMsg,t.handleTime as handleTime,t.operator as operator,t.isValid as isValid,t.createTime as createTime,t.updateTime as updateTime,"
						+ "t.transSubType as transSubType,t.transOrderNum as transOrderNum,t.transLatestAmount as transLatestAmount,t.stlNum as stlNum,t.transStatus as transStatus,t.termialType "
						+ "as termialType,t.bankCreditCode as bankCreditCode,t.bankCreditBranchName as bankCreditBranchName,t.bankCreditBranchCode as bankCreditBranchCode,t.bankDebitCode as bankDebitCode,"
						+ "t.bankDebitBranchName as bankDebitBranchName,t.bankDebitBranchCode as bankDebitBranchCode,t.returnUrl as returnUrl,"
						+ "t.creator as creator,t.updator as updator,t.bankDebitCardNum as bankDebitCardNum,t.bankCreditCardNum as bankCreditCardNum,t.printedTime as printedTime, ")
								.append(" t.reqNum as reqNum,t.reqInnerNum as reqInnerNum,t.bankAccepteTime as bankAccepteTime,t.bankReturnResult as bankReturnResult,t.bankReqTime as bankReqTime ")
								.append(" from CnlTransTrace as t, CorBankAcntTrans as d ")
								.append(" where t.bankTransNum = d.bankTransNum ").append(condition);

		String hql = s.toString();
		System.out.println(hql);
		// 添加hql语句
		queryPage.setHqlString(hql);

		IPage<CnlTransTraceDto> page = findPageByHql(queryPage, CnlTransTraceDto.class);
		Page<CnlTransTraceDto> p = (Page<CnlTransTraceDto>) page;
		List<CnlTransTraceDto> rows = (List<CnlTransTraceDto>) p.getRows();
		
		String bankReqTime = null;
		String bankAccepteTime = null;
		Date brt = null;
		Date bat = null;
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < rows.size(); i++) {
			brt = rows.get(i).getBankReqTime();
			bat = rows.get(i).getBankAccepteTime();
			
			if(null != brt){
				bankReqTime = dd.format(rows.get(i).getBankReqTime());
			}
			if(null != bat){
				bankAccepteTime = dd.format(rows.get(i).getBankAccepteTime());
			}
			
			if(StringUtils.isNotEmpty(bankReqTime) && StringUtils.isEmpty(bankAccepteTime)){
				// 01-已发送：存在请求时间且不存在接入银行网关时间
				rows.get(i).setMsgHandleStatus(Constants.MSG_HANDLE_STATUS_SEND);
			}
			if(StringUtils.isEmpty(bankReqTime) && StringUtils.isEmpty(bankAccepteTime)){
				// 02-未发送：不存在请求时间
				rows.get(i).setMsgHandleStatus(Constants.MSG_HANDLE_STATUS_UNSEND);
			}
			if(StringUtils.isNotEmpty(bankReqTime) && StringUtils.isNotEmpty(bankAccepteTime)){
				// 03-已处理：存在接入银行网关时间
				rows.get(i).setMsgHandleStatus(Constants.MSG_HANDLE_STATUS_HANDLED);
			}
			bankReqTime = null;
			bankAccepteTime = null;
		}
		return page;
	}

	public CnlTransTrace load(String id) {
		logger.info("entering action::CnlTransTraceDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlTransTrace);
	}

	@SuppressWarnings("all")
	@Override
	public CnlTransTraceDto getCnlTransTraceDtoById(String id) {
		logger.info("entering action::CnlTransTraceDaoImpl.getCnlTransTraceDtoById(String id)...id:"+id);
		StringBuffer s = new StringBuffer(
				"select t.id as id,t.bankTransNum as bankTransNum,d.transNum as transNum,d.bankMsgCode as bankMsgCode,t.transType as transType,t.custCode as custCode,t.cnlCustCode "
						+ "as cnlCustCode,t.reqBatch as reqBatch,t.transDc as transDc,t.transSubType as transSubType,t.transCurrency as transCurrency,t.transAmount as transAmount,t.transStatus "
						+ "as trnasStatus,t.transDate as transDate,t.transTime as transTime,t.transRate as transRate,t.transComments as transComments,t.bankAccepteTime as bankAccepteTime,"
						+ "t.bankHandleNum as bankHandleNum,t.bankReturnTime as bankReturnTime,t.bankPmtCnlType as bankPmtCnlType,t.bankPmtCnlCode as bankPmtCnlCode,t.cnlCnlCode as cnlCnlCode,"
						+ "t.isinGl as isinGl,t.isPrinted as isPrinted,t.bankCreditName as bankCreditName,t.bankCreditCustName as bankCreditCustName,t.bankCreditAcntCode as bankCreditAcntCode,"
						+ "t.bankDebitName as bankDebitName,t.bankDebitCustName as bankDebitCustName,t.bankDebitAcntCode as bankDebitAcntCode,t.bankReqTrnasDate as bankReqTrnasDate,t.bnakServiceFeeAct "
						+ "as bnakServiceFeeAct,t.bankReqTransTime as bankReqTransTime,t.bnakHandlePriority as bnakHandlePriority,t.errType as errType,t.errCode as errCode,t.errMsg as errMsg,"
						+ "t.handleStatus as handleStatus,t.handleMsg as handleMsg,t.handleTime as handleTime,t.operator as operator,t.isValid as isValid,t.createTime as createTime,t.updateTime as updateTime,"
						+ "t.transSubType as transSubType,t.transOrderNum as transOrderNum,t.transLatestAmount as transLatestAmount,t.stlNum as stlNum,t.transStatus as transStatus,t.termialType "
						+ "as termialType,t.bankCreditCode as bankCreditCode,t.bankCreditBranchName as bankCreditBranchName,t.bankCreditBranchCode as bankCreditBranchCode,t.bankDebitCode as bankDebitCode,"
						+ "t.bankDebitBranchName as bankDebitBranchName,t.bankDebitBranchCode as bankDebitBranchCode,t.returnUrl as returnUrl,"
						+ "t.creator as creator,t.updator as updator,t.bankDebitCardNum as bankDebitCardNum,t.bankCreditCardNum as bankCreditCardNum,t.printedTime as printedTime, ")
								.append(" t.reqNum as reqNum,t.reqInnerNum as reqInnerNum,t.bankAccepteTime as bankAccepteTime,t.bankReturnResult as bankReturnResult,t.bankReqTime as bankReqTime ")
								.append(" from CnlTransTrace as t, CorBankAcntTrans as d ")
								.append(" where t.bankTransNum = d.bankTransNum ");

		String hql = s.toString();
		System.out.println(hql);

		List<CnlTransTraceDto> list = super.getHibernateTemplate().find(hql, null);
		return (list == null || list.isEmpty())? null : list.get(0);
	}

	@Override
	public void saveBatch(List<CnlTransTrace> cnlTransTraces) {
		logger.info("entering action::CnlTransTraceDaoImpl.saveBatch()...");
		super.saveBatch(cnlTransTraces);
	}

	@SuppressWarnings("all")
	@Override
	public BigDecimal findSumTransAmount(String stlNum, String cnlCnlCode, String transDc) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.findSumTransAmount(String stlNum, String cnlCnlCode, String transDc)...stlNum:"+stlNum+"...cnlCnlCode:"+cnlCnlCode+"...transDc:"+transDc);
		String sql = "select nvl(sum(TRANS_AMOUNT),0) from CNL.T_CNL_TRANS_TRACE where STL_NUM = ? and CNL_CNL_CODE = ? and TRANS_DC = ? and IS_VALID= ? ";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<Object> list = query.setParameter(0, stlNum).setParameter(1, cnlCnlCode).setParameter(2, transDc).setParameter(3, Constants.IS_VALID_VALID).list();
		BigDecimal obj = new BigDecimal(list.get(0).toString());
		return obj;
	}

	@Override
	public List<CnlTransTrace> findDcBalAnce(String stlNum) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.findDcBalAnce(String stlNum)...stlNum:"+stlNum);
		String hql = "from CnlTransTrace where stlNum=? and isValid = ? ";
		List<Object> args = new ArrayList<Object>();
		args.add(stlNum);
		args.add(Constants.IS_VALID_VALID);
		List<CnlTransTrace> list = super.find(hql, args);
		return list;
	}

	@Override
	public void updateStatus(String cnlCustCode, String stlNum, String handleStatus, String reqDataErr,String errMsg,Date updateTime,String updator) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.updateStatus(String cnlCustCode, String stlNum, String handleStatus, String reqDataErr,Date updateTime,String updator)...cnlCustCode:"+cnlCustCode+"...stlNum:"+stlNum+"...handleStatus:"+handleStatus+"...reqDataErr"+reqDataErr+"...updateTime:"+updateTime+"...updator:"+updator+"");
		String sql = "update CNL.T_CNL_TRANS_TRACE set TRANS_STATUS = ? ,ERR_CODE = ?,ERR_MSG=?,UPDATE_TIME=?,UPDATOR=? where STL_NUM= ? and CNL_CUST_CODE= ?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int update = query.setParameter(0, handleStatus).setParameter(1, reqDataErr).setParameter(2, errMsg).setParameter(3, updateTime).setParameter(4, updator).setParameter(5, stlNum).setParameter(6, cnlCustCode).executeUpdate();
		
	}

	@Override
	public void deleTransTrace(String stlNum) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.deleTransTrace(String stlNum)...stlNum:"+stlNum+"");
		String sql = "delete from CNL.T_CNL_TRANS_TRACE where STL_NUM=?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int update = query.setParameter(0, stlNum).executeUpdate();
	}

	// 查询处理状态为处理中
	public List<CnlTransTrace> findHandler(String transType) {
		logger.info("enter CnlTransTraceDaoImpl.findHadler()...transType:"+transType+"");
		return getHibernateTemplate().find("from CnlTransTrace where transStatus=? and transType=? and isValid=?",
				new Object[] { Constants.TRANS_STATUS_PROCESSING, transType ,Constants.IS_VALID_VALID});
	}

	@Override
	public CnlTransTrace findByCnlOrderNum(String OrderNum) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.findByCnlOrderNum(String OrderNum)...OrderNum:"+OrderNum+"");
		return super.loadBy("transOrderNum", OrderNum);
	}

	public CnlTransTrace findByReqNum(String reqNum) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.findByReqNum(String reqNum)...reqNum:"+reqNum+"");
		return super.loadBy("reqNum", reqNum);
	}
	
	@Override
	public CnlTransTrace findByReqInnerNum(String reqInnerNum) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.findByReqInnerNum(String reqInnerNum)...{}" , reqInnerNum);
		
		String hql = "from CnlTransTrace where reqInnerNum = ? and isValid = ?";
		List<Object> args = new ArrayList<Object>();
		args.add(reqInnerNum);
		args.add(Constants.IS_VALID_VALID);
		
		List<CnlTransTrace> list = super.find(hql, args);
		
		return (list == null || list.isEmpty()) ? null:list.get(0);
	}

	public void deleteBatch(List<CnlTransTrace> list) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.deleteBatch(List<CnlTransTrace> list)");
		super.removeBatch(list);
	}

	@Override
	public List<CnlTransServiceClearing> getClearing(String stlNum, String pageSize, String pageIndex) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.List<CnlTransServiceClearing> getClearing(String stlNum, String pageSize, String pageIndex)...stlNum:"+stlNum+"...pageSize:"+pageSize+"...pageIndex:"+pageIndex+"");
		int rowConut = Integer.parseInt(pageSize)*Integer.parseInt(pageIndex);
		int notCount = Integer.parseInt(pageSize)*(Integer.parseInt(pageIndex)-1);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM(");
		sql.append(" SELECT rownum as r,a.UPDATE_TIME AS clearing_date ,");
		sql.append(" a.CNL_CUST_CODE AS cnl_customer_code ,");
		sql.append(" b.LOCAL_NAME AS name ,");
		sql.append(" a.TRANS_AMOUNT AS amount ,a.TRANS_LATEST_AMOUNT AS cnl_balance ,");
		sql.append(" c.BALANCE AS balance ,(a.TRANS_LATEST_AMOUNT + decode(a.TRANS_DC,'01',a.TRANS_AMOUNT*-1,a.TRANS_AMOUNT) - c.BALANCE ) AS gap_balance, a.ERR_MSG AS fail_reason ");
		sql.append(" FROM CNL.T_CNL_TRANS_TRACE a LEFT JOIN CNL.T_CNL_CUST b");
		sql.append(" ON a.CNL_CUST_CODE = b.CNL_CUST_CODE LEFT JOIN CNL.T_CNL_CUST_ACNT c");
		sql.append(" ON a.CNL_CUST_CODE = c.CNL_CUST_CODE");
		sql.append(" WHERE a.ERR_CODE IS NOT NULL AND");
		sql.append(" a.STL_NUM = '" + stlNum + "' ");
		sql.append(" AND rownum <="+rowConut+" ) where r>"+notCount);
		SQLQuery query= this.getSession().createSQLQuery(sql.toString());
		List<Object[]> l = query.list();
		List<CnlTransServiceClearing> list = new ArrayList<CnlTransServiceClearing>();
		if(query.list().size()>0){
			for (Object[] c : l) {
				CnlTransServiceClearing crsb =new CnlTransServiceClearing();
				if(c[1]!=null){
					crsb.setClearing_date(c[1].toString());
				}
				if(c[2]!=null){
					crsb.setCnl_customer_code(c[2].toString());
				}
				if(c[3]!=null){
					crsb.setName(c[3].toString());
				}				
				if(c[4]!=null){
					crsb.setAmount(c[4].toString());
				}
				if(c[5]!=null){
					crsb.setCnl_balance(c[5].toString());
				}
				if(c[6]!=null){
					crsb.setBalance(c[6].toString());
				}
				if(c[7]!=null){
					crsb.setGap_balance(c[7].toString());
				}	
				if(c[8]!=null){
					crsb.setFail_reason(c[8].toString());
				}
				list.add(crsb);
			}
		}
		return (list.size()>0)?list:null; 
	}

	@Override
	public int countList(String stlNum) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.countList(String stlNum)...stlNum:"+stlNum+"");
		DetachedCriteria criteria = DetachedCriteria.forClass(CnlTransTrace.class);
		criteria.add(Restrictions.eq("stlNum", stlNum)).add(Property.forName("errCode").isNotNull());
		criteria.setProjection(Projections.rowCount());
		return (Integer) super.getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	@Override
	public int countStlNum(String stlNum) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.countStlNum(String stlNum)...stlNum:"+stlNum+"");
		// String sql= "select count(STL_NUM) from CNL.T_CNL_TRANS_TRACE where
		// STL_NUM= ?";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CnlTransTrace.class);
		detachedCriteria.add(Restrictions.eq("stlNum", stlNum));
		return super.countBy(detachedCriteria);
	}
	@Override
	public boolean updateBatchTransTrace(List<CnlTransTrace> list){
		logger.debug("entering dao::CnlTransTraceDaoImpl.updateBatchTransTrace(List<CnlTransTrace> list)");
		super.updateBatch(list);
		return true;
	}

	//@SuppressWarnings("unused")
	@Override
	public void updateTransTrace(String stlNum, String handleStatus, String reqDataErr , String errMsg , Date updateTime,String updator) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.updateTransTrace(String stlNum, String handleStatus, String reqDataErr, Date updateTime,String updator)...stlNum:"+stlNum+"...handleStatus:"+handleStatus+"...reqDataErr:"+reqDataErr+"...updateTime:"+updateTime+"...updator:"+updator+"");
		String sql = "update CNL.T_CNL_TRANS_TRACE set TRANS_STATUS = ? ,ERR_CODE = ?,ERR_MSG=?,UPDATE_TIME=?,UPDATOR=? where STL_NUM= ?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int update = query.setParameter(0, handleStatus).setParameter(1, reqDataErr).setParameter(2, errMsg).setParameter(3, updateTime).setParameter(4, updator).setParameter(5, stlNum).executeUpdate();
		
	}
	
	public void update(CnlTransTrace cnlTransTrace){
		logger.debug("entering dao::CnlTransTraceDaoImpl.update(CnlTransTrace cnlTransTrace)");
		super.update(cnlTransTrace);
	}
	
	public void deleteByEntity(CnlTransTrace cnlTransTrace){
		logger.info("enter deleteByEntity()");
		getHibernateTemplate().delete(cnlTransTrace);
	}

	/**
	 *   根据reqNum更新 交易状态
	 */
	@Override
	public void updateTransTraceByReqNum(String reqNum, String transNo, String transStatus, String errCode, String errMsg) {
		logger.info("entering action::CnlTransTraceDaoImpl.updateTransTraceByReqNum()...reqNum:"+reqNum+"...transNo:"+transNo+"...transStatus:"+transStatus+"...errCode:"+errCode+"...errMsg:"+errMsg+"");
		
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		
		if(StringUtils.equals(transStatus, Constants.TRANS_STATUS_PROCESSING)){
			// 交易状态转成 02-处理中的时候，设置上 银行接入网关时间
			
			if(StringUtils.isNotEmpty(transNo)){
				String hql = "update CnlTransTrace c set c.transStatus = ?, c.bankTransNum = ?, c.bankAccepteTime = ?, c.updateTime = ?, c.errCode=?, c.errMsg=? where c.reqNum = ?";
				super.getHibernateTemplate().bulkUpdate(hql, new Object[]{transStatus,transNo,nowTime,nowTime,errCode, errMsg, reqNum});
				return;
			}
			String hql = "update CnlTransTrace c set c.transStatus = ?, c.bankAccepteTime = ?, c.updateTime = ?, c.errCode=?, c.errMsg=? where c.reqNum = ?";
			super.getHibernateTemplate().bulkUpdate(hql, new Object[]{transStatus,nowTime,nowTime,errCode, errMsg, reqNum});
			return;
		}
		// 非转成处理状态的时候，不加  银行接入网关时间,加上更新时间
		if(StringUtils.isNotEmpty(transNo)){
			String hql = "update CnlTransTrace c set c.transStatus = ?, c.bankTransNum = ?, c.updateTime = ?, c.errCode=?, c.errMsg=? where c.reqNum = ?";
			super.getHibernateTemplate().bulkUpdate(hql, new Object[]{transStatus,transNo,nowTime,errCode, errMsg, reqNum});
			return;
		}
		String hql = "update CnlTransTrace c set c.transStatus = ?, c.updateTime = ?, c.errCode=?, c.errMsg=?  where c.reqNum = ?";
		super.getHibernateTemplate().bulkUpdate(hql, new Object[]{transStatus,nowTime, errCode, errMsg, reqNum});
	}

	/**
	 *  根据渠道订单号 更新银行接入信息
	 */
	@Override
	public void updateTransTraceByOrderNum(String orderNum, String bankNumCode) {
		logger.info("entering action::CnlTransTraceDaoImpl.updateTransTraceByOrderNum()...orderNum:"+orderNum+"...bankNumCode:"+bankNumCode+"");
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		String hql = "update CnlTransTrace c set c.bankTransNum = ?, c.updateTime = ? where c.transOrderNum = ?";
		super.getHibernateTemplate().bulkUpdate(hql, new Object[]{bankNumCode,nowTime,orderNum});
	}

	/**
	 *  根据渠道客户编号和 渠道编号 查询一条记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CnlTransTrace findByCustCodeAndCnlCode(String cnlCustCode, String cnlCode) {
		logger.debug("entering dao::CnlTransTraceDaoImpl.findByCustCodeAndCnlCode(String cnlCustCode, String cnlCode)...cnlCustCode:"+cnlCustCode+"...cnlCode:"+cnlCode+"");
		String hql = "from CnlTransTrace where cnlCustCode = ? and cnlCnlCode = ? and isValid = ?";
		List<CnlTransTrace> list = super.getHibernateTemplate().find(hql, new Object[]{cnlCustCode,cnlCode,Constants.IS_VALID_VALID});
		
		return (list == null || list.isEmpty())?null:list.get(0);
	}
}
