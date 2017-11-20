/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.account.dao.ICnlTrans3mDao;
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.cnltrans.domain.CnlTrans5y;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_3M
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTrans3mDaoImpl extends BaseEntityDao<CnlTrans3m> implements
		ICnlTrans3mDao {

	public IPage<CnlTrans3m> findCnlTrans3mByPage(QueryPage queryPage) {
		logger.debug("entering dao::CnlTrans3mDaoImpl.findCnlTrans3mByPage()..."+queryPage);
		return super.findPageBy(queryPage);
	}

	public CnlTrans3m load(String id) {
		logger.debug("entering dao::CnlTrans3mDaoImpl.load()..."+id);
		return super.load(id);
	}

	public void saveOrUpdate(CnlTrans3m cnlTrans3m) {
		logger.debug("entering dao::CnlTrans3mDaoImpl.saveOrUpdate()..."+cnlTrans3m);
		super.saveOrUpdate(cnlTrans3m);
	}

	@Override
	public CnlTrans3m findCnlTran3m(CnlTrans3m cnlTrans3m) {
		logger.debug("entering dao::CnlTrans3mDaoImpl.findCnlTran3m()..."+cnlTrans3m);

		// TODO Auto-generated method stub
		return super.findBy(cnlTrans3m).get(0);
	}

	@Override
	public CnlTrans3m queryCnlTrans3m(String cnlCustCode, String startTransTime, String endTransTime, String transType) {
		logger.debug("entering dao::CnlTrans3mDaoImpl.queryCnlTrans3m()..."+cnlCustCode+startTransTime+endTransTime+transType);

		// TODO Auto-generated method stub
	//String hql = "select ct3 from CnlTrans3m ct3 where ct3.cnlCustCode =" + cnlCustCode + " and to_char(ct3.transTime,'yyyy-mm-dd hh24:mi:ss') between " + startTransTime + " and " + endTransTime +" and ct3.transType = " + transType;
	String hql = "select ct3 from CnlTrans3m ct3 where ct3.cnlCustCode = '"+cnlCustCode +"' and ct3.transTime between to_date('" + startTransTime+ "','yyyy-MM-dd HH24-mi-ss') and to_date('"+ endTransTime +"','yyyy-MM-dd HH24-mi-ss') and ct3.transType = '"+transType+"'";
		
		 List<CnlTrans3m> list = super.find(hql);
		 if(list.size()>0){
			return list.get(0);
		 }
         return null;
	}

	@Override
	public List<CnlTrans3m> findCnlTrans3m() {
		logger.debug("entering dao::CnlTrans3mDaoImpl.findCnlTrans3m()...");

		String sql = "from CnlTrans3m where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<CnlTrans3m> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCnlTrans5y(Collection<CnlTrans5y> cnlTrans5yList) {
		logger.debug("entering dao::CnlTrans3mDaoImpl.saveCnlTrans5y()..."+cnlTrans5yList);

		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(cnlTrans5yList);
	}

	@Override
	public boolean deleteCnlTrans3m() {
		logger.debug("entering dao::CnlTrans3mDaoImpl.deleteCnlTrans3m()...");

		try {
			String sql = " delete from CnlTrans3m WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
