package com.ibs.core.module.violationRecord.dao.impl;


import com.ibs.core.module.violationRecord.dao.ISysViolationRecordOverSpendDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordOverSpendDTO;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class SysViolationRecordOverSpendDaoImpl extends BaseEntityDao<SysViolationRecord> implements
ISysViolationRecordOverSpendDao
{


	@Override
	public IPage<SysViolationRecordOverSpendDTO> findViolationRecordOverSpendTable(QueryPage queryPage) {
		String condition = queryPage.getHqlString();
		//sql后期需要加入 黑名单类型    T_SYS_VIOLATION_RECORD.VIOLATION_TYPE
		StringBuffer s= new StringBuffer(" select a.id as id , a.reqNum as reqNum , a.cnlIntfCode as cnlIntfCode , a.createTime as createTime , b.cnlCustCode as cnlCustCode , b.reqInnerNum as reqInnerNum , b.transNum as transNum , b.bankTransNum as bankTransNum , b.transCurrency as transCurrency , a.violationId as violationId , b.transDc as transDc , b.transType as transType , b.transStatus as transStatus , a.violationDesc as violationDesc , c.transTime as transTime , b.bnakHandlePriority as bnakHandlePriority , b.bankReqTrnasDate as bankReqTrnasDate , b.bankReqTransTime as bankReqTransTime , b.bnakServiceFeeAct as bnakServiceFeeAct , b.bankAccepteTime as bankAccepteTime , b.bankReturnTime as bankReturnTime , b.bankDebitName as bankDebitName , b.bankDebitCustName as bankDebitCustName , b.bankDebitCardNum as bankDebitCardNum , b.bankCreditName as bankCreditName , b.bankCreditCustName as bankCreditCustName , b.bankCreditCardNum as bankCreditCardNum , b.bankPmtCnlType as bankPmtCnlType , b.cnlCustCode as cnlCustCode , b.termialType as termialType , b.isinGl as isinGl , d.transTime as transTimeS , b.isPrinted as isPrinted , b.printedTime as printedTime from SysViolationRecord a , CnlTrans b , CorBankAcntTrans c, CnlCustAcntDtl d  where a.reqNum = b.reqNum and c.transNum = b.transNum and d.transNum = c.transNum ").append(condition);
		String hql= s.toString();
		 queryPage.setHqlString(hql);
			IPage<SysViolationRecordOverSpendDTO> page= findPageByHql(queryPage,SysViolationRecordOverSpendDTO.class);
			// TODO Auto-generated method stub
		return page;
	}
  
	@Override
	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysViolationRecord load(String id) {
		// TODO Auto-generated method stub
		return null;
	}




}
