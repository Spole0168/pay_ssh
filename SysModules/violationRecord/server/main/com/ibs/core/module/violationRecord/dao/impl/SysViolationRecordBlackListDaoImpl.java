package com.ibs.core.module.violationRecord.dao.impl;

import com.ibs.core.module.violationRecord.dao.ISysViolationRecordBlackListDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordBlackListDTO;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class SysViolationRecordBlackListDaoImpl extends BaseEntityDao<SysViolationRecord> implements
ISysViolationRecordBlackListDao {

	@Override
	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage) {
		// TODO Auto-generated method stub
		return super.findPageBy(queryPage);
	}

	@Override
	public SysViolationRecord load(String id) {
		// TODO Auto-generated method stub
		return super.load(id);
	}
  
//黑名单
	@Override
	public IPage<SysViolationRecordBlackListDTO> findViolationRecordBlackListTable(QueryPage queryPage) {
		String condition = queryPage.getHqlString();
		//sql后期需要加入 黑名单类型    T_SYS_VIOLATION_RECORD.VIOLATION_TYPE
		StringBuffer s= new StringBuffer(" select a.id as id , a.reqNum as reqNum , a.cnlIntfCode as cnlIntfCode , b.cnlCustCode as cnlCustCode , a.violationType as violationType , a.violationId as violationId , b.transType as transType , a.createTime as createTime , a.violationSubType as violationSubType from SysViolationRecord a , CnlTrans b where a.reqNum = b.reqNum  ").append(condition);
		String hql= s.toString();
		System.out.println(hql);
		 queryPage.setHqlString(hql);
			IPage<SysViolationRecordBlackListDTO> page= findPageByHql(queryPage,SysViolationRecordBlackListDTO.class);
		return page;
	}


}
