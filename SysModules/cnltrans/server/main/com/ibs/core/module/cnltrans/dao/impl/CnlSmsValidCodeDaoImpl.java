/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.cnltrans.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.ibs.core.module.cnltrans.dao.ICnlSmsValidCodeDao;
import com.ibs.core.module.cnltrans.domain.CnlSmsValidCode;
import com.ibs.core.module.cnltrans.domain.CnlSmsValidCodeHis;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SMS_VALID_CODE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSmsValidCodeDaoImpl extends BaseEntityDao<CnlSmsValidCode> implements
		ICnlSmsValidCodeDao {

	public IPage<CnlSmsValidCode> findCnlSmsValidCodeByPage(QueryPage queryPage) {
		logger.info("entering action::CnlSmsValidCodeDaoImpl.findCnlSmsValidCodeByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlSmsValidCode load(String id) {
		logger.info("entering action::CnlSmsValidCodeDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlSmsValidCode cnlSmsValidCode) {
		logger.info("entering action::CnlSmsValidCodeDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlSmsValidCode);
	}

	@Override
	public List<CnlSmsValidCode> findCnlSmsValidCode() {
		String sql = "from CnlSmsValidCode where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<CnlSmsValidCode> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCnlSmsValidCodeHis(Collection<CnlSmsValidCodeHis> cnlSmsValidCodeHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(cnlSmsValidCodeHisList);
	}

	@Override
	public boolean deleteCnlSmsValidCode() {
		try {
			String sql = " delete from CnlSmsValidCode WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
