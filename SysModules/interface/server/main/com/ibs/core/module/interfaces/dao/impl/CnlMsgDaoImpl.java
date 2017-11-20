/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.interfaces.dao.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.cnltrans.domain.CnlMsgHis;
import com.ibs.core.module.interfaces.dao.ICnlMsgDao;
import com.ibs.core.module.interfaces.domain.CnlMsg;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlMsgDaoImpl extends BaseEntityDao<CnlMsg> implements
		ICnlMsgDao {

	public IPage<CnlMsg> findCnlMsgByPage(QueryPage queryPage) {
		logger.info("entering action::CnlMsgDaoImpl.findCnlMsgByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlMsg load(String id) {
		logger.info("entering action::CnlMsgDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlMsg cnlMsg) {
		logger.info("entering action::CnlMsgDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlMsg);
	}

	public List<CnlMsg> findByMsgCode(String msgCode){
		logger.info("entering action::CnlMsgDaoImpl.findByMsgCode()...");
		String sql="from CnlMsg where msgCode= '"+msgCode+"'";
		List<CnlMsg> list= find(sql);
		
		
		return list;
	}
	
	public CnlMsg findByMsgCodeCnlCnlCode(String msgCode,String cnlCnlCode){
		logger.debug("enter findByMsgCodeCnlCnlCode()");
		String sql="from CnlMsg where msgCode=? and cnlCnlCode=? and isValid=?";
		List<CnlMsg> list=getHibernateTemplate().find(sql, new Object[]{msgCode,cnlCnlCode,Constants.IS_VALID_VALID});
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public CnlMsg saveCnlMsg(CnlMsg cnlMsg) {
		logger.debug("enter saveMsg()");
		// TODO Auto-generated method stub
		String msgCode=(String) getHibernateTemplate().save(cnlMsg);
		System.out.println(msgCode);
		return load(msgCode);
	}

	@Override
	public List<CnlMsg> findCnlMsg() {
		String sql = "from CnlMsg where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
		List<CnlMsg> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCnlMsgHis(Collection<CnlMsgHis> cnlMsgHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(cnlMsgHisList);
	}

	@Override
	public boolean deleteCnlMsg() {
		try {
			String sql = " delete from CnlMsg WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=90";
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
