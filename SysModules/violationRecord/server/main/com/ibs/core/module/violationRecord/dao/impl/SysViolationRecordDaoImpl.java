/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.violationRecord.dao.impl;



import java.util.Collection;
import java.util.List;

import com.ibs.core.module.violationRecord.dao.ISysViolationRecordDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordHis;
import com.ibs.core.module.violationRecord.domain.SysViolationRecordIPExceptionDTO;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_VIOLATION_RECORD
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysViolationRecordDaoImpl extends BaseEntityDao<SysViolationRecord> implements
		ISysViolationRecordDao {

	  
/*
 * 对页面提交数据进行模糊查询
* <p>Title: findSysViolationRecordByPage</p>
* <p>Description: </p>
* @param queryPage
* @return
* @see com.ibs.core.module.pbl.dao.ISysViolationRecordDao#findSysViolationRecordByPage(com.ibs.portal.framework.server.metadata.QueryPage)
 */
	public IPage<SysViolationRecord> findSysViolationRecordByPage(QueryPage queryPage) {

		return super.findPageBy(queryPage);
	}

	public SysViolationRecord load(String id) {
		logger.info("entering action::SysViolationRecordDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(SysViolationRecord sysViolationRecord) {
		logger.info("entering action::SysViolationRecordDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(sysViolationRecord);
	}

	/*
	 * 页面查询IP异常
	* <p>Title: findViolationRecordTable</p>
	* <p>Description: </p>
	* @param queryPage
	* @return
	* @see com.ibs.core.module.pbl.dao.ISysViolationRecordDao#findViolationRecordTable(com.ibs.portal.framework.server.metadata.QueryPage)
	 */
	@Override
	public IPage<SysViolationRecordIPExceptionDTO> findViolationRecordTable(QueryPage queryPage) {
		String condition = queryPage.getHqlString();
		StringBuffer s= new StringBuffer("select a.id as id , b.cnlCustCode as cnlCustCode , a.cnlIntfCode as cnlIntfCode , a.reqNum as reqNum , a.violationId as violationId , b.transType as transType , a.createTime as createTime from SysViolationRecord a , CnlTrans b where a.reqNum = b.reqNum ").append(condition);
		String hql= s.toString();
		 queryPage.setHqlString(hql);
			IPage<SysViolationRecordIPExceptionDTO> page= findPageByHql(queryPage,SysViolationRecordIPExceptionDTO.class);
		return page;
	}


	
	
	public  void  saveBatch(List<SysViolationRecord>list){
		
		 super.saveBatch(list);
	}

	@Override
	public List<SysViolationRecord> findSysViolationRecord() {
		String sql = "from SysViolationRecord where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<SysViolationRecord> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveSysViolationRecordHis(Collection<SysViolationRecordHis> sysViolationRecordHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(sysViolationRecordHisList);
	}

	@Override
	public boolean deleteSysViolationRecord() {
		try {
			String sql = " delete from SysViolationRecord WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
