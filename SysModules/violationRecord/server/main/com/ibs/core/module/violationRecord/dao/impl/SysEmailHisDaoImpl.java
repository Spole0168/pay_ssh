/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.violationRecord.dao.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.violationRecord.dao.ISysEmailHisDao;
import com.ibs.core.module.violationRecord.domain.SysEmailHis;
import com.ibs.core.module.violationRecord.domain.SysEmailHisHis;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_EMAIL_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysEmailHisDaoImpl extends BaseEntityDao<SysEmailHis> implements
		ISysEmailHisDao {

	public IPage<SysEmailHis> findSysEmailHisByPage(QueryPage queryPage) {
		logger.info("entering action::SysEmailHisDaoImpl.findSysEmailHisByPage()...");
		return super.findPageBy(queryPage);
	}

	public SysEmailHis load(String id) {
		logger.info("entering action::SysEmailHisDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(SysEmailHis sysEmailHis) {
		logger.info("entering action::SysEmailHisDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(sysEmailHis);
	}

	@Override
	public List<SysEmailHis> findSysEmailHis() {
		String sql = "from SysEmailHis where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<SysEmailHis> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveSysEmailHisHis(Collection<SysEmailHisHis> sysEmailHisHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(sysEmailHisHisList);
		
	}

	@Override
	public boolean deleteSysEmailHis() {
		try {
			String sql = " delete from SysEmailHis WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
