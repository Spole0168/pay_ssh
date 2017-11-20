/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.bank.dao.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.bank.dao.ICorBankMsgDao;
import com.ibs.core.module.bank.domain.CorBankMsg;
import com.ibs.core.module.bank.domain.CorBankMsgHis;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankMsgDaoImpl extends BaseEntityDao<CorBankMsg> implements ICorBankMsgDao {

	public IPage<CorBankMsg> findCorBankMsgByPage(QueryPage queryPage) {
		logger.info("entering action::CorBankMsgDaoImpl.findCorBankMsgByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorBankMsg load(String id) {
		logger.info("entering action::CorBankMsgDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorBankMsg corBankMsg) {
		logger.info("entering action::CorBankMsgDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corBankMsg);
	}

	@Override
	public List<CorBankMsg> findCorBankMsg() {
		String sql = "select * from CorBankMsg where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<CorBankMsg> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCorBankMsgHis(Collection<CorBankMsgHis> corBankMsgHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(corBankMsgHisList);
		
	}

	@Override
	public boolean deleteCorBankMsg() {
		try {
			String sql = " delete from CorBankMsg WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
	 * 根据报文编码 和 支付渠道编码 查询 一条记录
	 */
	@SuppressWarnings("unchecked")
	@Override
//	public CorBankMsg findByMsgCodeCnlCnlCode(String msgCode, String bankPmtCnlCode) {
	public CorBankMsg findByMsgCodeCnlCnlCode(String msgCode) {
		logger.info("entering action::CorBankMsgDaoImpl.findByMsgCodeCnlCnlCode()...");

//		String hql = "from CorBankMsg c where c.msgCode = ? and c.pmtCnlCode = ? and c.isValid = ? ";
		String hql = "from CorBankMsg c where c.msgCode = ? and c.isValid = ? ";

		List<CorBankMsg> list = super.getHibernateTemplate().find(hql,
//				new Object[] { msgCode, bankPmtCnlCode, Constants.IS_VALID_VALID });
				new Object[] { msgCode, Constants.IS_VALID_VALID });

		return (list == null || list.isEmpty()) ? null : list.get(0);
	}

}
