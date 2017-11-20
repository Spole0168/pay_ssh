/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.cnltrans.dao.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.cnltrans.dao.ICnlTrans5yDao;
import com.ibs.core.module.cnltrans.domain.CnlTrans5y;
import com.ibs.core.module.cnltrans.domain.CnlTrans5yHis;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_5Y
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlTrans5yDaoImpl extends BaseEntityDao<CnlTrans5y> implements
		ICnlTrans5yDao {

	public IPage<CnlTrans5y> findCnlTrans5yByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTrans5yDaoImpl.findCnlTrans5yByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlTrans5y load(String id) {
		logger.info("entering action::CnlTrans5yDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlTrans5y cnlTrans5y) {
		logger.info("entering action::CnlTrans5yDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlTrans5y);
	}

	@Override
	public List<CnlTrans5y> findCnlTrans5y() {
		String sql = "from CnlTrans5y where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=5*365";
		List<CnlTrans5y> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCnlTrans5yHis(Collection<CnlTrans5yHis> cnlTrans5yHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(cnlTrans5yHisList);
		
	}

	@Override
	public boolean deleteCnlTrans5y() {
		try {
			String sql = " delete from CnlTrans5y WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
