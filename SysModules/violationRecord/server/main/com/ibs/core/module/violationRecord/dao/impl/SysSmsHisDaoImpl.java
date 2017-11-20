/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.violationRecord.dao.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.violationRecord.dao.ISysSmsHisDao;
import com.ibs.core.module.violationRecord.domain.SysSmsHis;
import com.ibs.core.module.violationRecord.domain.SysSmsHisHis;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SMS_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysSmsHisDaoImpl extends BaseEntityDao<SysSmsHis> implements
		ISysSmsHisDao {

	public IPage<SysSmsHis> findSysSmsHisByPage(QueryPage queryPage) {
		logger.info("entering action::SysSmsHisDaoImpl.findSysSmsHisByPage()...");
		return super.findPageBy(queryPage);
	}

	public SysSmsHis load(String id) {
		logger.info("entering action::SysSmsHisDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(SysSmsHis sysSmsHis) {
		logger.info("entering action::SysSmsHisDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(sysSmsHis);
	}

	@Override
	public List<SysSmsHis> findSysSmsHis() {
		String sql = "from SysSmsHis where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<SysSmsHis> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveSysSmsHisHis(Collection<SysSmsHisHis> sysSmsHisHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(sysSmsHisHisList);
	}

	@Override
	public boolean deleteSysSmsHis() {
		try {
			String sql = " delete from SysSmsHis WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
