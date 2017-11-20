/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.springframework.jdbc.object.SqlQuery;

import com.ibs.core.module.account.dao.ICnlReqTransDao;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.cnltrans.domain.CnlReqTransHis;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("unchecked")
public class CnlReqTransDaoImpl extends BaseEntityDao<CnlReqTrans> implements
		ICnlReqTransDao {

	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage) {
		logger.debug("entering dao::CnlReqTransDaoImpl.findCnlReqTransByPage()..."+queryPage);
		return super.findPageBy(queryPage);
	}

	public CnlReqTrans load(String id) {
		logger.debug("entering dao::CnlReqTransDaoImpl.load()..."+id);
		return super.load(id);
	}

	public void saveOrUpdate(CnlReqTrans cnlReqTrans) {
		logger.debug("entering dao::CnlReqTransDaoImpl.saveOrUpdate()..."+cnlReqTrans);
		super.saveOrUpdate(cnlReqTrans);
	}
	/**
	 * 通过申请单流水号修改req表的状态
	 * @author chenyanyan
	 * @param reqInnerNum
	 * @param reqStatus
	 * @return
	 */
	@Override
	public int updateReqStatus(String reqInnerNum, String reqStatus) {
		logger.debug("entering dao::CnlReqTransDaoImpl.updateReqStatus()..."+reqInnerNum+reqStatus);

		logger.info("entering action::CnlReqTransDaoImpl.updateReqStatus()...");
		String s ="update CNL.T_CNL_REQ_TRANS set REQ_STATUS=?,UPDATE_TIME=?,UPDATOR=? where REQ_INNER_NUM=?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(s);
		int update = query.setParameter(0, reqStatus).setParameter(1, new Date()).setParameter(2, Constants.SYSADMIN).setParameter(3, reqInnerNum).executeUpdate();
		return update;
	}
	
	public boolean updateReqestStatus(String reqInnerNum, String reqStatus) {
		logger.debug("entering dao::CnlReqTransDaoImpl.updateReqestStatus()..."+reqInnerNum+reqStatus);

		logger.info("entering action::CnlReqTransDaoImpl.updateReqestStatus()...");
		String hql ="update CnlCustTrace set reqStatus=reqStatus+?,updateTime=updateTime+?,updator=updator+? where reqInnerNum=reqInnerNum+?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{reqStatus,new Date(),Constants.SYSADMIN,reqInnerNum});
		
		return true;
	}
	
	
	/*
	 	public int updateReqStatus(String reqInnerNum, String reqStatus) {
		logger.info("entering action::CnlReqTransDaoImpl.updateReqStatus()...");
		String s ="update CNL.T_cnl_req_trans set req_status=?,update_time=?,updator=? where req_inner_num=?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(s);
		int update = query.setParameter(0, reqStatus).setParameter(1, new Date()).setParameter(2, Constants.SYSADMIN).setParameter(3, reqInnerNum).executeUpdate();
		return update;
	}
	  
	  
	 * */
	
	
	/*
		public boolean updatePersonalCreditSuccess(CnlCustAcnt cnlCustAcnt, BigDecimal amount){
//		String hql="update CnlCustAcnt set balance=balance+?,balanceAvalible=balanceAvalible+? where id=?";
		String hql="update CnlCustAcnt set balance=balance+? where id=?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{amount,cnlCustAcnt.getId()});
		return true;
	} 
	 * */
	
	/**
	 *	渠道业务跟踪的表格查询
	 * @author chenyanyan 
	 * @param queryPage
	 * @param conditon
	 */
	@Override
	public IPage<CnlReqTransDto> findReqTranBizByMoreTable(QueryPage queryPage, String conditon) {
		logger.debug("entering dao::CnlReqTransDaoImpl.findReqTranBizByMoreTable()..."+queryPage+conditon);

		// TODO Auto-generated method stub
		/**
		 *  select 
			r.REQ_INNER_NUM as reqInnerNum,
			t.CNL_CUST_CODE as cnlCustCode,
			t.NAME as name,
			r.REQ_TYPE as reqType,
			r.REQ_STATUS as reqStatus,
			r.MSG_TIME as msgTime,
			r.RECIEVE_TIME as recieveTime,
			r.HANDLE_TIME as handleTime 
			 from CNL.T_CNL_REQ_TRANS r 
					left join 
				(select * from
					(select REQ_INNER_NUM,CNL_CUST_CODE,NAME from CNL.T_CNL_CUST_TRACE )
							union all
					(select s.REQ_INNER_NUM,c.CNL_CUST_CODE,c.LOCAL_NAME from CNL.T_CNL_TRANS_TRACE s,CNL.T_CNL_CUST c where s.CNL_CUST_CODE=c.CNL_CUST_CODE)
				) t
			on r.REQ_INNER_NUM = t.REQ_INNER_NUM 
			where r.IS_VALID='01'
		 */
		//拼接hql
		StringBuffer sqlBuffer = new StringBuffer("select ")
				.append("r.REQ_INNER_NUM as reqInnerNum,")
				.append("r.REQ_NUM as reqNum,")
				//.append("t.CNL_CUST_CODE as cnlCustCode,")
				//.append("t.NAME as localName,")
				.append("r.REQ_TYPE as reqType,")
				.append("r.REQ_STATUS as reqStatus,")
				.append("r.MSG_TIME as msgTime,")
				.append("r.RECIEVE_TIME as recieveTime,")
				.append("r.HANDLE_TIME as handleTime")
				.append(" from CNL.T_CNL_REQ_TRANS r  ")
				//.append("left join ")
				//.append("(select * from ")
				//.append("(select REQ_INNER_NUM,CNL_CUST_CODE,NAME from CNL.T_CNL_CUST_TRACE )")
				//.append("union all")
				//.append("(select s.REQ_INNER_NUM,c.CNL_CUST_CODE,c.LOCAL_NAME from CNL.T_CNL_TRANS_TRACE s,CNL.T_CNL_CUST c where s.CNL_CUST_CODE=c.CNL_CUST_CODE)")
				//.append(") t ")
				//.append("on r.REQ_INNER_NUM = t.REQ_INNER_NUM ")
				.append("where r.IS_VALID='")
				.append(Constants.IS_VALID_VALID).append("' ")
				.append(conditon);
		StringType s = new StringType();
		DateType d = new DateType();
		queryPage.addScalar("reqInnerNum",s);
		queryPage.addScalar("reqNum",s);
		//queryPage.addScalar("cnlCustCode",s);
		//queryPage.addScalar("localName",s);
		queryPage.addScalar("reqType",s);
		queryPage.addScalar("reqStatus",s);
		queryPage.addScalar("msgTime",d);
		queryPage.addScalar("recieveTime",d);
		queryPage.addScalar("handleTime",d);
		//添加hql语句
		queryPage.setSqlString(sqlBuffer.toString());
		//关联cnlreqtrans和cnltrans表查询
		//设置排序条件（按创建时间降序）
		queryPage.addSort("r.create_Time", DESC);
		IPage<CnlReqTransDto> page = super.findPageBySql(queryPage, CnlReqTransDto.class);
		return page;
	}
	/**
	 * 根据单个字段查询
	 */
	@Override
	public List<CnlReqTrans> findCnlReqTransByOneCondition(String columName, String condition) {
		logger.debug("entering dao::CnlReqTransDaoImpl.findCnlReqTransByOneCondition()..."+columName+condition);

		String hql="select  c from CnlReqTrans c where c."+columName+"="+condition;
		Query query = getSession().createQuery(hql);
		List<CnlReqTrans> list = query.list();
		return list;
	}
	@Override
	public List<CnlReqTrans> findTrans(String stlNum) {
		logger.debug("entering dao::CnlReqTransDaoImpl.findTrans()..."+stlNum);

			logger.info("entering action::CnlTransTraceDaoImpl.findTrans()...");
			String hql = "from CnlReqTrans where stlNum=? and isValid = ? ";
			List<Object> args = new ArrayList<Object>();
			args.add(stlNum);
			args.add(Constants.IS_VALID_VALID);
			List<CnlReqTrans> list = super.find(hql, args);
			return list;
	}

	@SuppressWarnings("unused")
	@Override
	public void updateStatus(String stlNum,String handleStatus,String reqDataErr,String errMsg,Date updateTime,String updator) {
		logger.debug("entering dao::CnlReqTransDaoImpl.updateStatus()..."+stlNum+handleStatus+reqDataErr+updateTime+updator);

		String sql = "update CNL.T_CNL_REQ_TRANS set REQ_STATUS = ?,ERR_CODE = ?,ERR_MSG=?,UPDATE_TIME=?,UPDATOR=? where STL_NUM=?";
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int update = query.setParameter(0, handleStatus).setParameter(1, reqDataErr).setParameter(2, errMsg).setParameter(3, updateTime).setParameter(4, updator).setParameter(5, stlNum).executeUpdate();
	}
	
	@Override
	public CnlReqTrans maxBatch(String stlNum) {
		logger.debug("entering dao::CnlReqTransDaoImpl.maxBatch()..."+stlNum);

		
		String hql = " from CnlReqTrans a where a.stlNum = '"+stlNum+"' order by a.reqBatch desc";
		
		Query query = getSession().createQuery(hql);
		
		if(query.list().size()>0){
			return (CnlReqTrans)query.list().get(0);
		}else
		{
			return null;
		}
	}
	
	public void updateBatch(List<CnlReqTrans> list){
		logger.debug("entering dao::CnlReqTransDaoImpl.updateBatch()..."+list);

		super.updateBatch(list);
		
	}


	@Override
	public void updateReqTrans(CnlReqTrans cnlReqTrans) {
		// TODO Auto-generated method stub
		logger.debug("entering dao::CnlReqTransDaoImpl.updateReqTrans()..."+cnlReqTrans);

		
		super.update(cnlReqTrans);
		
	}

	
	public CnlReqTrans findByInnerNum(String innerNum){
		logger.debug("entering dao::CnlReqTransDaoImpl.findByInnerNum()..."+innerNum);

		return super.loadBy("reqInnerNum", innerNum);
	}

	@Override
	public CnlReqTrans getReq(String stlNum) {
		logger.debug("entering dao::CnlReqTransDaoImpl.getReq()..."+stlNum);

		return super.loadBy("stlNum", stlNum);
	}

	
	public CnlReqTrans findByReqInnerNum(String reqInnerNum){
		logger.debug("entering dao::CnlReqTransDaoImpl.findByReqInnerNum()..."+reqInnerNum);
	List<CnlReqTrans> list=	getHibernateTemplate().find("from CnlReqTrans where reqInnerNum=?", reqInnerNum);
	if(null!=list&&list.size()>0){
		return list.get(0);
	}
	return null;
	}
	@Override
	public boolean findByCustCodeAndCnlCode(String custCode, String cnlCode) {
		logger.debug("entering dao::CnlReqTransDaoImpl.findByCustCodeAndCnlCode()..."+custCode+cnlCode);

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(" t.ID as id");
		sb.append(" FROM ");
		sb.append(" CNL.T_CNL_REQ_TRANS t INNER JOIN CNL.T_CNL_CUST_TRACE c ");
		sb.append(" ON t.REQ_INNER_NUM = c.REQ_INNER_NUM ");
		sb.append(" WHERE ");
		sb.append(" c.CNL_CUST_CODE = '"+custCode+"' ");
		sb.append(" AND ");
		sb.append(" t.CNL_CNL_CODE = '"+cnlCode+"' ");
		sb.append(" AND ");
		sb.append(" t.REQ_TYPE = '"+Constants.REQ_TRACE_TYPE_ENTERPRISE_SIGN_UP+"' ");
		QueryPage queryPage = new QueryPage(10, 1);
		queryPage.setSqlString(sb.toString());
		queryPage.addScalar("id", new StringType());
		IPage<CnlReqTrans> p = super.findPageBySql(queryPage, CnlReqTrans.class);
		List<CnlReqTrans> list = (List<CnlReqTrans>) p.getRows();
		if(list.size()>0){
			return false;
		}else{
			return true;
		}		
	}

	/**
	 *  根据reqInnerNum更新 交易状态
	 */
	@Override
	public void updateReqTransByReqInnerNum(String reqInnerNum, String reqStatus, String errCode, String errMsg) {
		logger.debug("entering dao::CnlReqTransDaoImpl.updateReqTransByReqNum()..."+reqInnerNum+reqStatus+errCode+errMsg);
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		
		String hql = "update CnlReqTrans c set c.reqStatus = ?, c.updateTime = ?, c.errCode = ?, c.errMsg = ?,c.updator = ? where c.reqInnerNum = ? and c.isValid=?";
		super.getHibernateTemplate().bulkUpdate(hql, new Object[]{reqStatus,nowTime,errCode,errMsg,Constants.SYSADMIN,reqInnerNum, Constants.IS_VALID_VALID});
	}
	
	@Override
	public List<CnlReqTrans> findCnlReqTrans() {
		logger.debug("entering dao::CnlReqTransDaoImpl.findCnlReqTrans()...");

		String sql = "from CnlReqTrans where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<CnlReqTrans> list = super.find(sql);
		return list.isEmpty()?null:list;
	}

	@Override
	public void saveCnlReqTransHis(Collection<CnlReqTransHis> cnlReqTransHisList) {
		logger.debug("entering dao::CnlReqTransDaoImpl.saveCnlReqTransHis()...");

		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(cnlReqTransHisList);
	}

	@Override
	public boolean deleteCnlReqTrans() {
		logger.debug("entering dao::CnlReqTransDaoImpl.deleteCnlReqTrans()...");

		try {
			String sql = " delete from CnlReqTrans WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
	/**
	 * 充值提现
	 * @param reqInnerNum
	 * @return
	 * 
	 */
	@Override
	public CnlReqTransDto findTransDetail(String reqInnerNum) {
		logger.info("entering action::CnlReqTransDaoImpl.findTransDetail()...");
		//编写sql
		StringBuffer sql=new StringBuffer();
		sql.append("select r.req_Inner_Num as \"reqInnerNum\"")
		.append(",r.req_Num as \"reqNum\"")
		.append(",r.req_Type as \"reqType\"")
		.append(",r.req_Status as \"reqStatus\"")
		.append(",r.msg_Time as \"msgTime\"")
		.append(",r.recieve_Time as \"recieveTime\"")
		.append(",r.handle_Time as \"handleTime\"")
		.append(",t.bank_Accepte_Time as \"bankAccepteTime\"")
		.append(",t.bank_Trans_Num as \"bankTransNum\"")
		.append(",t.isin_Gl as \"isinGl\"")
		.append(",t.bank_Return_Time as \"bankReturnTime\"")
		.append(",t.cnl_Cust_Code as \"cnlCustCode\"")
		.append(",t.trans_Type as \"transType\"")
		.append(",t.trans_Dc as \"transDc\"")
		.append(",t.trans_Currency as \"transCurrency\"")
		.append(",t.trans_Amount as \"transAmount\"")
		.append(",t.trans_Status as \"transStatus\"")
		.append(",t.trans_Date as \"transDate\"")
		.append(",t.trans_Time as \"transTime\"")
		.append(",t.trans_Comments as \"transComments\"")
		.append(",t.bank_Return_Result as \"bankReturnResult\"")
		.append(",t.bank_Return_Time as \"bankReturnTime\"")
		.append(",t.bank_Pmt_Cnl_Type as \"bankPmtCnlType\"")
		.append(",t.bank_Pmt_Cnl_Code as \"bankPmtCnlCode\"")
		.append(",t.bank_Credit_Name as \"bankCreditName\"")
		.append(",t.bank_Credit_Cust_Name as \"bankCreditCustName\"")
		.append(",t.bank_Credit_Acnt_Code as \"bankCreditAcntCode\"")
		.append(",t.bank_Debit_Name as \"bankDebitName\"")
		.append(",t.bank_Debit_Cust_Name as \"bankDebitCustName\"")
		.append(",t.bank_Debit_Acnt_Code as \"bankDebitAcntCode\"")
		.append(",t.bank_Req_Trnas_Date as \"bankReqTransDate\"")
		.append(",t.bnak_Service_Fee_Act as \"bankServiceFeeAct\"")
		.append(",t.bnak_Handle_Priority as \"bankHandLePriority\" ")
		.append("from cnl.T_CNL_REQ_TRANS r,cnl.T_CNL_TRANS_TRACE t ")
		.append("where r.req_inner_Num=t.req_inner_num and r.req_inner_Num=?");
		//创建查询
		Query query = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(CnlReqTransDto.class));  
		//添加条件参数
		query.setString(0, reqInnerNum);
		//执行
		List<CnlReqTransDto> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 返回一个申请单下的所有清结算明细分页数据
	 * @author chenyanyan
	 * @param queryPage
	 * @param reqInnerNum
	 * @return
	 */
	@Override
	public IPage<CnlReqTransDto> findBalanceDetail(QueryPage queryPage,String reqInnerNum) {
		logger.info("entering action::CnlReqTransDaoImpl.findTransDetail()...");
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		//编写sql
		StringBuffer sql=new StringBuffer();
		sql.append("select r.req_Inner_Num as \"reqInnerNum\"")
		.append(",r.reqNum as \"reqNum\"")
		.append(",t.isin_Gl as \"isinGl\"")
		.append(",t.cnl_Cust_Code as \"cnlCustCode\"")
		.append(",t.trans_Dc as \"transDc\"")
		.append(",t.trans_Currency as \"transCurrency\"")
		.append(",t.trans_Amount as \"transAmount\"")
		.append(",t.trans_Status as \"transStatus\"")
		.append(",t.trans_Date as \"transDate\"")
		.append(",t.trans_Time as \"transTime\"")
		.append(",t.trans_Comments as \"transComments\"")
		.append("from cnl.T_CNL_REQ_TRANS r,cnl.T_CNL_TRANS_TRACE t ")
		.append("where r.req_inner_Num=t.req_inner_num and r.req_Inner_Num=?");
		queryPage.setSqlString(sql.toString());
		//关联cnlreqtrans和cnltrans表查询
		//设置排序条件（按创建时间降序）
		queryPage.addSort("r.create_Time", DESC);
		queryPage.addQueryCondition("req_Inner_Num", reqInnerNum);
		IPage<CnlReqTransDto> page = super.findPageBySql(queryPage, CnlReqTransDto.class);
		return page;
	}
	
	@Override
	public String getReturnUrl(String msgId) {
		logger.debug("entering dao::CnlReqTransDaoImpl.getReturnUrl(String msgId)...msgId:"+msgId);
		Criteria criteria = getSession().createCriteria(CnlReqTrans.class);
		criteria.add(Restrictions.eq("reqNum", msgId));
		List<CnlReqTrans> list = criteria.list();
		if(list!=null&&list.size()>0){
			CnlReqTrans cnlReqTrans = list.get(0);
			return cnlReqTrans.getReturnUrl();
		}
		return null;
	}

	@Override
	public String getClearingTotalBatch(String stlNum) {
		logger.debug("entering dao::CnlReqTransDaoImpl.getClearingTotalBatch(String stlNum)...stlNum:"+stlNum);
		String sql = " select  r.REQ_TOTAL_BATCH as totalBatch from  CNL.T_CNL_REQ_TRANS r where r.STL_NUM='"+stlNum+"' GROUP by r.REQ_TOTAL_BATCH ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("totalBatch",new StringType());
		return (query.uniqueResult()!=null)?(String)query.uniqueResult():null;
	}

	@Override
	public List<String> getClearingBatch(String stlNum) {
		logger.debug("entering dao::CnlReqTransDaoImpl.getClearingBatch(String stlNum)...stlNum:"+stlNum);
		String sql = " select r.REQ_BATCH as batch from CNL.T_CNL_REQ_TRANS r where r.STL_NUM='"+stlNum+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("batch", new StringType());
		List<String> list = query.list();
		return (list!=null&&list.size()>0)?list:null;
	}
	/**
	 * 返回一个关于客户跟踪的客户信息（个人）
	 * @author chenyanyan
	 * @param reqInnerNum
	 * @return
	 */
	@Override
	public CnlReqTransDto findPersonAboutCustTrace(String reqInnerNum) {
		logger.info("entering action::CnlReqTransDaoImpl.findPersonAboutCustTrace()...");
		//创建hql语句,查询cnlreqTrans和cnltranstrace
		StringBuffer hql = new StringBuffer();
		hql.append("select t.cnlCustCode as cnlCustCode")
			.append( ",r.reqNum as reqNum")
			.append( ",t.name as localName")
			.append( ",t.phonenum as phonenum")
			.append( ",r.reqInnerNum as reqInnerNum")
			.append( ",r.reqType as reqType")
			.append( ",r.reqStatus as reqStatus")
			.append( ",r.msgTime as msgTime")
			.append( ",t.certType as certType")
			.append( ",t.certExpire as certExpireDate")
			.append( ",t.country as country")
			.append( ",t.provience as provience")
			.append( ",t.city as city")
			.append( ",t.addr as addr")
			.append( ",t.certType as certType")
			.append( ",r.cnlCnlCode as cnlCnlCode")
			.append( ",r.recieveTime as recieveTime")
			.append( ",r.handleTime as handleTime")
			.append(" from CnlReqTrans r ,CnlCustTrace t where r.reqInnerNum= ? and r.reqInnerNum=t.reqInnerNum ");
		Query query = getSession().createQuery(hql.toString()).setResultTransformer(Transformers.aliasToBean(CnlReqTransDto.class));
		//添加条件参数
		query.setString(0, reqInnerNum);
		//执行
		List<CnlReqTransDto> list = query.list();
		//如果查询无结果，返回空值
		if(list==null||list.size()<1){
			return null;
		}
		return list.get(0);
	}
	/**
	 * 返回一个关于客户跟踪的客户信息（企业）
	 * @author chenyanyan
	 * @param reqInnerNum
	 * @return
	 */
	@Override
	public CnlReqTransDto findCompanyAboutCustTrace(String reqInnerNum) {
		logger.info("entering action::CnlReqTransDaoImpl.findCompanyAboutCustTrace()...");
		StringBuffer hql = new StringBuffer();
		//查询T_CNL_REQ_TRANS、T_CNL_TRANS_TRACE
		hql.append("select r.reqNum as reqNum,r.cnlCnlCode as cnlCnlCode,t.cnlCustCode as cnlCustCode,t.certType as certType,t.certNum as certNum,t.certExpire as certExpireDate,t.country as country,t.certType as certType")
			.append(",t.provience as provience,t.city as city,t.name as companyName,r.reqInnerNum as reqInnerNum,r.reqType as reqType,r.reqStatus as reqStatus,r.msgTime as msgTime")
			.append(",t.addr as addr,t.phonenum as companyTel,r.recieveTime as recieveTime,r.handleTime as handleTime from CnlReqTrans r ,CnlCustTrace  t where r.reqInnerNum='").append(reqInnerNum)
			.append("' and t.reqInnerNum=r.reqInnerNum ");
		Query query = getSession().createQuery(hql.toString());
		List<CnlReqTransDto> list = query.setResultTransformer(Transformers.aliasToBean(CnlReqTransDto.class)).list();
		//如果查询无结果，返回空值
		if(list==null||list.size()<1||list.get(0)==null){
			return null;
		}
		return list.get(0);
	}

}
