/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.account.dao.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.account.dao.ICnlCustAcntDtlDao;
import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlCustAcntDtlDto;
import com.ibs.core.module.cnltrans.domain.CnlCustAcntDtlHis;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustAcntDtlDaoImpl extends BaseEntityDao<CnlCustAcntDtl> implements
		ICnlCustAcntDtlDao {

	public IPage<CnlCustAcntDtl> findCnlCustAcntDtlByPage(QueryPage queryPage) {
		logger.debug("entering action::CnlCustAcntDtlDaoImpl.findCnlCustAcntDtlByPage()..."+queryPage);
		return super.findPageBy(queryPage);
	}

	public CnlCustAcntDtl load(String id) {
		logger.info("entering action::CnlCustAcntDtlDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlCustAcntDtl cnlCustAcntDtl) {
		logger.info("entering action::CnlCustAcntDtlDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlCustAcntDtl);
	}
	/**
	 * 实现与交易流水的多表连查
	 */
	@Override
	public IPage<CnlCustAcntDtlDto> findCnlCustAcntDtlByPageAndMoreTable(QueryPage queryPage,String condition) {
		StringBuffer s;
		//判断是否是三个月内的
			//生成hql语句
		s = new StringBuffer("select g.cnlSysName as cnlCnlCode,d.acntDtlCode as acntDtlCode ,d.cnlAcntCode as cnlAcntCode,d.cnlCustCode as cnlCustCode,d.transNum as transNum,d.acntType as acntType,c.localName as localName,d.currency as currency")
			.append(",d.amount as amount,d.direction as direction,d.transTime as trnasTime,d.balance as balance,d.createTime as createTime,d.voucherNum as voucherNum,d.comments as comments from CnlCustAcntDtl d,CnlCust c,CnlCnlCfg g")
			.append(" where c.cnlCustCode=d.cnlCustCode and g.cnlCnlCode=c.cnlCnlCode and d.isValid ='").append(Constants.IS_VALID_VALID).append("' ").append(condition);
		String hql = s.toString();
		//添加hql语句
		queryPage.setHqlString(hql);
		//设置排序条件（按创建时间降序）
		queryPage.addSort("d.createTime", DESC);
		IPage<CnlCustAcntDtlDto> page = findPageByHql(queryPage,CnlCustAcntDtlDto.class);
		return page;
	}
	
	/**
	 * 保存
	 * */
	public boolean saveCnlCustAcntDtl(CnlCustAcntDtl cnlCustAcntDtl){
		getHibernateTemplate().save(cnlCustAcntDtl);
		return true;
	}

	@Override
	public void save(List<CnlCustAcntDtl> list) {
		super.saveBatch(list);
	}

	@Override
	public List<CnlCustAcntDtl> findCnlCustAcntDtl() {
		String sql = "from CnlCustAcntDtl where (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=5*365";
		List<CnlCustAcntDtl> list = super.find(sql);
		return (list == null || list.isEmpty())?null:list;
	}

	@Override
	public void saveCnlCustAcntDtlHis(Collection<CnlCustAcntDtlHis> cnlCustAcntDtlHisList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().saveOrUpdateAll(cnlCustAcntDtlHisList);
	}

	@Override
	public boolean deleteCnlCustAcntDtl() {
		try {
			String sql = " delete from CnlCustAcntDtl WHERE (sysdate-(TO_DATE(TO_CHAR( createTime ,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))>=5*365";
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
