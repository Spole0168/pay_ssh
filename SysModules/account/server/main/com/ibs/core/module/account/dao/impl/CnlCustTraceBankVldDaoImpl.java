/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.core.module.account.dao.ICnlCustTraceBankVldDao;
import com.ibs.core.module.account.domain.CnlCustTraceBankVld;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE_BANK_VLD
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustTraceBankVldDaoImpl extends BaseEntityDao<CnlCustTraceBankVld> implements
		ICnlCustTraceBankVldDao {

	public IPage<CnlCustTraceBankVld> findCnlCustTraceBankVldByPage(QueryPage queryPage) {
		logger.debug("entering dao::CnlCustTraceBankVldDaoImpl.findCnlCustTraceBankVldByPage()..."+queryPage);
		return super.findPageBy(queryPage);
	}

	public CnlCustTraceBankVld load(String id) {
		logger.info("entering dao::CnlCustTraceBankVldDaoImpl.load()..."+id);
		return super.load(id);
	}

	public void saveOrUpdate(CnlCustTraceBankVld cnlCustTraceBankVld) {
		logger.info("entering dao::CnlCustTraceBankVldDaoImpl.saveOrUpdate()..."+cnlCustTraceBankVld);
		super.saveOrUpdate(cnlCustTraceBankVld);
	}
	
	/**
	 * 查询出所有处于某种交易状态的某类客户数据
	 */
	public List<CnlCustTraceBankVld> findCnlCustTranceBankVldOnHandle(String transStatus) {
		logger.info("entering dao::CnlCustTraceBankVldDaoImpl.findCnlCustTranceBankVldOnHandle()..."+transStatus);

		List<CnlCustTraceBankVld> list= getHibernateTemplate().find("select c from CnlCustTraceBankVld c where c.transStatus=? and c.isValid=?",
				new Object[]{transStatus,Constants.IS_VALID_VALID});
		return list;
	}

	@Override
	public CnlCustTraceBankVld findByReqInnerNum(String reqInnerNum) {
		logger.debug("entering dao::CnlCustTraceBankVldDaoImpl.findByReqInnerNum()...{}", reqInnerNum);
		String hql = "from CnlCustTraceBankVld where reqInnerNum = ? and isValid = ?";
		List<Object> args = new ArrayList<Object>();
		args.add(reqInnerNum);
		args.add(Constants.IS_VALID_VALID);
		
		List<CnlCustTraceBankVld> list = super.find(hql, args);
		
		return (list == null || list.isEmpty()) ? null:list.get(0);
	}
}
