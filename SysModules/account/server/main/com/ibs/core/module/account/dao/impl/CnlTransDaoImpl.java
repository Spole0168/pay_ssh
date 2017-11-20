/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DbTimestampType;
import org.hibernate.type.StringType;

import com.ibs.core.module.account.dao.ICnlTransDao;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.account.domain.Reconciliation;
import com.ibs.core.module.account.domain.SumAmountDto;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTransDaoImpl extends BaseEntityDao<CnlTrans> implements
ICnlTransDao {
	
	public IPage<CnlTrans> findCnlTransByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransDaoImpl.findCnlTransByPage()...");
		return super.findPageBy(queryPage);
	}

	public IPage<CnlTrans> findCnlTransBySql(QueryPage queryPage,CnlTrans cnlTrans) {
		logger.debug("entering dao::CnlTransDaoImpl.findCnlTransBySql(QueryPage queryPage,CnlTrans cnlTrans)...cnlTrans:"+cnlTrans);
		queryPage.clearSortMap();
		queryPage.clearQueryCondition();

		StringBuilder hql = new StringBuilder();
		hql.append("select a.REQ_INNER_NUM as reqInnerNum,");
		hql.append("a.TRANS_NUM as transNum,");
		hql.append("a.REQ_NUM as reqNum,");
		hql.append("a.BANK_TRANS_NUM as bankTransNum,");
		hql.append("a.TRANS_CURRENCY as transCurrency,");
		hql.append("a.TRANS_AMOUNT as transAmount,");
		hql.append("a.TRANS_DC as transDc,");
		hql.append("a.TRANS_TYPE as transType,");
		hql.append("a.TRANS_STATUS as transStatus,");
		hql.append("a.TRANS_COMMENTS as transComments,");
		hql.append("a.TRANS_TIME as transTime,");
		hql.append("a.BNAK_HANDLE_PRIORITY as bnakHandlePriority,");
		hql.append("to_char(a.BANK_REQ_TRNAS_DATE,'yyyy-mm-dd') as bankReqTrnasDateString,");
		hql.append("to_char(a.BANK_REQ_TRANS_TIME,'hh24:mi:ss') as bankReqTransTimeString,");
		hql.append("a.BNAK_SERVICE_FEE_ACT as bnakServiceFeeAct,");
		hql.append("a.BANK_ACCEPTE_TIME as bankAccepteTime,");
		hql.append("a.BANK_RETURN_TIME as bankReturnTime,");
		hql.append("a.BANK_DEBIT_NAME as bankDebitName,");
		hql.append("a.BANK_DEBIT_CUST_NAME as bankDebitCustName,");
		hql.append("a.BANK_DEBIT_ACNT_CODE as bankDebitAcntCode,");
		hql.append("a.BANK_CREDIT_NAME as bankCreditName,");
		hql.append("a.BANK_CREDIT_CUST_NAME as bankCreditCustName,");
		hql.append("a.BANK_CREDIT_ACNT_CODE as bankCreditAcntCode,");
		hql.append("a.BANK_PMT_CNL_CODE as bankPmtCnlCode,");
		hql.append("a.CNL_CNL_CODE as cnlCnlCode,");
		hql.append("a.TERMIAL_TYPE as termialType,");
		hql.append("a.ISIN_GL as isinGl,");
		hql.append("a.IS_PRINTED as isPrinted,");
		hql.append("a.PRINTED_TIME as printedTime,");
		hql.append("to_char(b.TRANS_TIME,'yyyy-mm-dd hh24:mi:ss') as transTimeB");
		hql.append(" from cnl.T_CNL_TRANS a left join cnl.T_CNL_CUST_ACNT_DTL b");
		hql.append(" on a.TRANS_NUM=b.TRANS_NUM");
		hql.append(" and a.IS_VALID= b.IS_VALID");
		//hql.append(" where a.IS_VALID='00'");
		hql.append(" where a.IS_VALID='").append(Constants.IS_VALID_VALID).append("'");


		String cnlCnlCode = cnlTrans.getCnlCnlCode();		
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			//queryPage.addQueryCondition("cnlCnlCode", "%" + cnlCnlCode + "%");
			hql.append(" and a.CNL_CNL_CODE like '%").append(cnlCnlCode).append("%'");
		}

		String transNum = cnlTrans.getTransNum();		
		if (StringUtils.isNotEmpty(transNum)) {
			//queryPage.addQueryCondition("transNum", "%" + transNum + "%");
			hql.append(" and a.TRANS_NUM like '%").append(transNum.trim()).append("%'");
		}

		String reqInnerNum = cnlTrans.getReqInnerNum();		
		if (StringUtils.isNotEmpty(reqInnerNum)) {
			//queryPage.addQueryCondition("reqInnerNum", "%" + reqInnerNum + "%");
			hql.append(" and a.REQ_INNER_NUM like '%").append(reqInnerNum.trim()).append("%'");
		}

		String reqNum = cnlTrans.getReqNum();		
		if (StringUtils.isNotEmpty(reqNum)) {
			//queryPage.addQueryCondition("reqNum", "%" + reqNum + "%");
			hql.append(" and a.REQ_NUM like '%").append(reqNum.trim()).append("%'");
		}

		String bankTransNum = cnlTrans.getBankTransNum();		
		if (StringUtils.isNotEmpty(bankTransNum)) {
			//queryPage.addQueryCondition("bankTransNum", "%" + bankTransNum + "%");
			hql.append(" and a.BANK_TRANS_NUM like '%").append(bankTransNum.trim()).append("%'");
		}

		String timeType = cnlTrans.getTimeType();
		String dbColumn = "";
		if (StringUtils.isNotEmpty(timeType)) {
			if("1".equals(timeType)) {
				dbColumn = "a.TRANS_TIME";
			} else if ("2".equals(timeType)) {
				dbColumn = "a.BANK_ACCEPTE_TIME";
			} else if("3".equals(timeType)) {
				dbColumn = "a.BANK_RETURN_TIME";
			}
		}

		String startTime = cnlTrans.getStartTime();		
		if (StringUtils.isNotEmpty(startTime)) {
			//queryPage.addQueryCondition("startTime", Timestamp.valueOf(startTime));
			hql.append(" and to_char(").append(dbColumn).append(",'yyyy-mm-dd hh24:mi:ss')").append(" >= '").append(startTime).append("'");
		}

		String endTime = cnlTrans.getEndTime();		
		if (StringUtils.isNotEmpty(endTime)) {
			//queryPage.addQueryCondition("endTime", Timestamp.valueOf(endTime));
			hql.append(" and to_char(").append(dbColumn).append(",'yyyy-mm-dd hh24:mi:ss')").append(" <= '").append(endTime).append("'");
		}

		String transType = cnlTrans.getTransType();		
		if (StringUtils.isNotEmpty(transType)) {
			//queryPage.addQueryCondition("transType", "%" + transType + "%");
			hql.append(" and a.TRANS_TYPE like '%").append(transType).append("%'");
		}

		String transDc = cnlTrans.getTransDc();		
		if (StringUtils.isNotEmpty(transDc)) {
			//queryPage.addQueryCondition("transDc", "%" + transDc + "%");
			hql.append(" and a.TRANS_DC like '%").append(transDc).append("%'");
		}

		String trnasStatus = cnlTrans.getTransStatus();		
		if (StringUtils.isNotEmpty(trnasStatus)) {
			//queryPage.addQueryCondition("trnasStatus", "%" + trnasStatus + "%");
			hql.append(" and a.TRANS_STATUS like '%").append(trnasStatus).append("%'");
		}

		queryPage.setSqlString(hql.toString());
		StringType s = new StringType();
		BigDecimalType b = new BigDecimalType();
		DbTimestampType d = new DbTimestampType(); 
		queryPage.addScalar("reqInnerNum",s);
		queryPage.addScalar("transNum",s);
		queryPage.addScalar("reqNum",s);
		queryPage.addScalar("bankTransNum",s);
		queryPage.addScalar("transCurrency",s);
		queryPage.addScalar("transAmount",b);
		queryPage.addScalar("transDc",s);
		queryPage.addScalar("transType",s);
		queryPage.addScalar("transStatus",s);
		queryPage.addScalar("transComments",s);
		queryPage.addScalar("transTime",d);
		queryPage.addScalar("bnakHandlePriority",s);
		queryPage.addScalar("bankReqTrnasDateString",s);
		queryPage.addScalar("bankReqTransTimeString",s);
		queryPage.addScalar("bnakServiceFeeAct",s);
		queryPage.addScalar("bankAccepteTime",d);
		queryPage.addScalar("bankReturnTime",d);
		queryPage.addScalar("bankDebitName",s);
		queryPage.addScalar("bankDebitCustName",s);
		queryPage.addScalar("bankDebitAcntCode",s);
		queryPage.addScalar("bankCreditName",s);
		queryPage.addScalar("bankCreditCustName",s);
		queryPage.addScalar("bankCreditAcntCode",s);
		queryPage.addScalar("bankPmtCnlCode",s);
		queryPage.addScalar("cnlCnlCode",s);
		queryPage.addScalar("isinGl",s);
		queryPage.addScalar("isPrinted",s);
		queryPage.addScalar("transTimeB",s);
		queryPage.addScalar("printedTime",d);
		queryPage.addScalar("termialType",s);
		return super.findPageBySql(queryPage,CnlTrans.class);
	}

	public CnlTrans load(String id) {
		logger.info("entering action::CnlTransDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlTrans cnlTrans) {
		logger.info("entering action::CnlTransDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlTrans);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CnlTrans> findTrans(Date startTime,Date endTime) {
		logger.debug("entering dao::CnlTransDaoImpl.findTrans(Date startTime,Date endTime)...startTime:"+startTime+"endTime:"+endTime);
		String sql = "from CnlTrans c where (c.bankReturnTime between ? "
				+ "and ?) and c.bankTransNum not in (select a.bankTransNum from "
				+ "CorBankAcntTransDtl a ,CnlTrans b where a.amount = b.transAmount and a.direction != b.transDc "
				+ "and a.bankTransNum = b.bankTransNum)"; 
		List<Object> args = new ArrayList<Object>();
		args.add(startTime);
		args.add(endTime);
		List<CnlTrans> list = super.find(sql, args);
		/*Query query = super.getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql);
		List<CnlTrans> list = query.setParameter(0, startTime).setParameter(1, endTime).list();
		System.out.println(list);*/
		if(list != null&&list.size() > 0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public void updateStatus(List<CnlTrans> list) {
		logger.debug("entering dao::CnlTransDaoImpl.updateStatus(List<CnlTrans> list)");
		String sql = null;
		if(list != null && list.size() != 0){
			StringBuffer sb = new StringBuffer("");
			for(int i = 0 ;i <list.size();i++){
				sb.append("'"+list.get(i).getBankTransNum()+"'");
				if(i != list.size()-1)
					sb.append(",");
			}
			sql = "update T_CNL_TRANS set RECON_STATUS = '01' where BANK_TRANS_NUM not in (" + sb.toString() +")";
		}else{
			sql = "update T_CNL_TRANS set RECON_STATUS = '01'";
		}
		super.executeSql(sql);
	}

	@Override
	public void updateStatus2(List<CnlTrans> list) {
		logger.debug("entering dao::CnlTransDaoImpl.updateStatus2(List<CnlTrans> list)");
		String sql = null;
		StringBuffer sb = new StringBuffer("");
		for(int i = 0 ;i <list.size();i++){
			sb.append("'"+list.get(i).getBankTransNum()+"'");
			if(i != list.size()-1)
				sb.append(",");
		}
		sql = "update T_CNL_TRANS set RECON_STATUS = '02' where BANK_TRANS_NUM in (" + sb.toString() +")";
		super.executeSql(sql);
	}

	public void saveBatch(List<CnlTrans> list){
		logger.debug("entering dao::CnlTransDaoImpl.saveBatch(List<CnlTrans> list)");
		super.saveBatch(list);
	}
	
	/**
	 * 充值和提现记录查询
	 */
	@Override
	public List<Reconciliation> getList(String cnlCustomerCode, String typeCode,String pageSize,String pageIndex) {
		logger.debug("entering dao::CnlTransDaoImpl.getList(String cnlCustomerCode, String typeCode,String pageSize,String pageIndex)...cnlCustomerCode:"+cnlCustomerCode+"...typeCode:"+typeCode+"...pageSize:"+pageSize+"...pageIndex"+pageIndex);
		int rowConut = Integer.parseInt(pageSize)*Integer.parseInt(pageIndex);
		int notCount = Integer.parseInt(pageSize)*(Integer.parseInt(pageIndex)-1);
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (SELECT rownum as r,");
		sql.append(" a.LOCAL_NAME as name,b.CNL_CUST_CODE as cnl_customer_code,");
		sql.append(" b.TRANS_NUM as trans_num,");
		sql.append(" b.REQ_INNER_NUM as request_num,");
		sql.append(" b.BANK_TRANS_NUM as bank_trans_num,");
		sql.append(" b.TRANS_DATE as trans_date,");
		sql.append(" b.TRANS_TYPE as trans_type,");
		sql.append(" b.TRANS_AMOUNT as amount");
		sql.append(" FROM");
		sql.append(" CNL.T_CNL_TRANS b ");
		sql.append(" INNER JOIN ");
		sql.append(" CNL.T_CNL_CUST a");
		sql.append(" ON ");
		sql.append(" a.CNL_CUST_CODE = b.CNL_CUST_CODE");
		sql.append(" WHERE");
		if(cnlCustomerCode!=null){
			sql.append(" b.CNL_CUST_CODE = '"+cnlCustomerCode +"' AND");
		}
		if(typeCode.equals(Constants.TRANS_TYPE_TOPUP)||typeCode.equals(Constants.TRANS_TYPE_WITHDRAW)){
			sql.append(" b.TRANS_TYPE = '"+typeCode+"'");
		}else{
			sql.append(" (b.TRANS_TYPE ='"+Constants.TRANS_TYPE_TOPUP+"' OR b.TRANS_TYPE ='"+Constants.TRANS_TYPE_WITHDRAW+"')");
		}
		sql.append(" and rownum <="+rowConut+" 	and a.CUST_STATUS='"+Constants.CNL_CUST_STATUS_PASSED+"' and 	b.IS_VALID='"+Constants.IS_VALID_VALID+"') where r>"+notCount);
		SQLQuery query= this.getSession().createSQLQuery(sql.toString());
		query.addScalar("name",new StringType() );
		query.addScalar("cnl_customer_code",new StringType() );
		query.addScalar("trans_num",new StringType() );
		query.addScalar("request_num",new StringType() );
		query.addScalar("bank_trans_num",new StringType() );
		query.addScalar("trans_date",new StringType() );
		query.addScalar("trans_type",new StringType() );
		query.addScalar("amount",new StringType() );
		List<Object[]> l = query.list();
		List<Reconciliation> list = new ArrayList<Reconciliation>();
		if(query.list().size()>0){
			for (Object[] c : l) {
				Reconciliation crsb =new Reconciliation();
				if (c[0] != null) {
					crsb.setName(c[0].toString());
				}
				if (c[1] != null) {
					crsb.setCnl_customer_code(c[1].toString());
				}
				if (c[2] != null) {
					crsb.setTrans_num(c[2].toString());
				}
				if (c[3] != null) {
					crsb.setRequest_num(c[3].toString());
				}
				if (c[4] != null) {
					crsb.setBank_trans_num(c[4].toString());
				}
				if (c[5] != null) {
					crsb.setTrans_date(c[5].toString());
				}
				if (c[6] != null) {
					crsb.setTrans_type(c[6].toString());
				}
				if (c[7] != null) {
					crsb.setAmount(c[7].toString());
				}
				list.add(crsb);
			}
		}
		return (list.size()>0)?list:null;
	}

	
	
	@Override
	public CnlTrans findTrans(String stlNum) {
		logger.debug("entering dao::CnlTransDaoImpl.findTrans(String stlNum)...stlNum:"+stlNum);
		return super.loadBy("stlNum", stlNum);
	}

	@Override
	public int listCount(String cnlCustomerCode, String typeCode) {
		logger.debug("entering dao::CnlTransDaoImpl.listCount(String cnlCustomerCode, String typeCode)...cnlCustomerCode:"+cnlCustomerCode+"...typeCode:"+typeCode);
		StringBuffer sql = new StringBuffer("select count(*) from  CNL.T_CNL_CUST cc inner join CNL.T_CNL_TRANS ct on cc.CNL_CUST_CODE=ct.CNL_CUST_CODE where "); 
		if(cnlCustomerCode!=null){
			sql.append(" cc.CNL_CUST_CODE = '"+cnlCustomerCode +"'");
		}
		if(typeCode.equals(Constants.TRANS_TYPE_TOPUP)||typeCode.equals(Constants.TRANS_TYPE_WITHDRAW)){
			if(typeCode.equals(Constants.TRANS_TYPE_TOPUP)){
				sql.append(" and ct.TRANS_TYPE='"+Constants.TRANS_TYPE_TOPUP+"'");
			}else{
				sql.append(" and ct.TRANS_TYPE='"+Constants.TRANS_TYPE_WITHDRAW+"'");
			}
		}else{
			sql.append(" and (ct.TRANS_TYPE='"+Constants.TRANS_TYPE_TOPUP+"' or ct.TRANS_TYPE='"+Constants.TRANS_TYPE_WITHDRAW+"')");
		}
				
				sql.append(" and cc.CUST_STATUS='"+Constants.CNL_CUST_STATUS_PASSED);
				sql.append("' and cc.IS_VALID='"+Constants.IS_VALID_VALID+"'");
		Query query = getSession().createSQLQuery(sql.toString());
		int count = Integer.valueOf(query.uniqueResult().toString());
		return count;
	}

	@Override
	public List<CnlTrans> findTransDtl(String stlNum) {
		logger.debug("entering dao::CnlTransDaoImpl.findTransDtl(String stlNum)...stlNum:"+stlNum);
		String hql = "from CnlTrans where stlNum=?";
		return super.findByValue(hql, stlNum);
	}
	
	public SumAmountDto sumAmount(String custCode,Date startTime,Date endTime,Date startWeek,Date endWeek,Date startMonth,Date startYear){
		logger.debug("entering dao::CnlTransDaoImpl.sumAmount(String custCode,Date startTime,Date endTime,Date startWeek,Date endWeek,Date startMonth,Date startYear)...custCode:"+custCode+"...startTime:"+startTime+"...endTime:"+endTime+"...startWeek:"+startWeek+"...endWeek:"+endWeek+"...startMonth:"+startMonth+"...startYear:"+startYear);
		SumAmountDto sumAmountDto = null;
		String sql = "select (select sum(nvl(trans_amount,0)) from cnl.t_cnl_trans where cust_code=? and trans_time between ? and ?),"
				+ "(select sum(nvl(trans_amount,0)) from cnl.t_cnl_trans where cust_code=? and trans_time between ? and ?),"
				+ "(select sum(nvl(trans_amount,0)) from cnl.t_cnl_trans where cust_code=? and trans_time between ? and ?),"
				+ "(select sum(nvl(trans_amount,0)) from cnl.t_cnl_trans where cust_code=? and trans_time between ? and ?) from dual";
		
		Query query = super.getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql);
		List<Object> object = query.setParameter(0, custCode).setParameter(1, startTime)
				.setParameter(2, endTime).setParameter(3, custCode).setParameter(4, startWeek)
				.setParameter(5, endWeek).setParameter(6, custCode).setParameter(7, startMonth)
				.setParameter(8, endTime).setParameter(9, custCode).setParameter(10, startYear).setParameter(11, endTime).list();
		Object[] object1 = new Object[4];
		object1 = (Object[])object.get(0);
		sumAmountDto = new SumAmountDto((BigDecimal)object1[0], (BigDecimal)object1[1], (BigDecimal)object1[2], (BigDecimal)object1[3]);
		return sumAmountDto;
	}

	@Override
	public List<CnlTrans> findCnlTrans() {
		logger.debug("entering dao::CnlTransDaoImpl.findCnlTrans()");
		String sql = "from CnlTrans ";
		List<CnlTrans> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCnlTrans3m(Collection<CnlTrans3m> cnlTrans3mList) {
		logger.debug("entering dao::CnlTransDaoImpl.saveCnlTrans3m(CnlTrans3m cnlTrans3m)...cnlTrans3m:"+cnlTrans3mList);
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(cnlTrans3mList);
	}

	@Override
	public boolean deleteCnlTrans() {
		logger.debug("entering dao::CnlTransDaoImpl.deleteCnlTrans()");
		try {
			String sql = "delete from CnlTrans";
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
