/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.cnlmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibs.core.module.cnlmgr.dao.ICnlSysIntfDao;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntfCfg;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitConditionDto;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitDto;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSysIntfDaoImpl extends BaseEntityDao<CnlSysIntf> implements
		ICnlSysIntfDao {

	public IPage<CnlSysIntf> findCnlSysIntfByPage(QueryPage queryPage) {
		logger.info("entering action::CnlSysIntfDaoImpl.findCnlSysIntfByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlSysIntf load(String id) {
		logger.info("entering action::CnlSysIntfDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlSysIntf cnlSysIntf) {
		logger.info("entering action::CnlSysIntfDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlSysIntf);
	}
	

	public  CnlSysIntf getIntf(String  cnlcnlcode){
		
		String  hql="select intf  from CnlSysIntf  intf  where intf.cnlCnlCode=? and intf.isValid=?";
		
		List<Object> obj=new ArrayList<Object>();
		obj.add(cnlcnlcode);
		obj.add(Constants.IS_VALID_VALID);
		
		List<CnlSysIntf> list=super.find(hql,obj);
		
		return list.isEmpty()?null:list.get(0);
		
	}
	
	public CnlSysIntf findByCnlCode(String cnlCode, String serviceCode) {
		logger.info("sentering action::CnlSysIntfDaoImpl.findByCnlCode()...");
		
		CnlSysIntf cnlSysIntf=new CnlSysIntf();
		
		List<CnlSysIntf> list=getHibernateTemplate().find("from CnlSysIntf where cnlCnlCode =? and cnlIntfCode=?", new Object[]{cnlCode,serviceCode});
		if(null!=list&&list.size()>0){
			cnlSysIntf=list.get(0);
			return cnlSysIntf;
		}
		return null;
	}

	/**
	 * 查找
	 */
	@Override
	public IPage<CnlSysIntfIpLimitDto> findCnlSysIntfByPage(QueryPage queryPage,
			CnlSysIntfIpLimitConditionDto cnlSysIntfIpLimitConditionDto) {
		logger.info("sentering action::CnlSysIntfDaoImpl.findCnlSysIntfByPage()...");
		StringBuffer hql = new StringBuffer("select cnl.id as id"
				+ " ,cnl.cnlCnlCode as cnlCnlCode"
				+ " ,cnl.cnlIntfCode as cnlIntfCode"
				+ " ,cnl.ipRangeFrom as ipRangeFrom"
				+ " ,cnl.ipRangeTo as ipRangeTo"
				+ " ,cfg.cnlCustCode as cnlCustCode"
				+ " ,cnl.ipOpt as ipOpt"
				+ " ,cnl.ipOptTime as ipOptTime"
				+ " from CnlSysIntf cnl,CnlCnlCfg cfg where cnl.cnlCnlCode = cfg.cnlCnlCode "
				+ " and cnl.ipRangeFrom is not null and cnl.ipRangeTo is not null ");
		String cnlCustCode = cnlSysIntfIpLimitConditionDto.getCnlCustCode();
		if(StringUtils.isNotEmpty(cnlCustCode)){
			hql.append(" and cfg.cnlCustCode = '" + cnlCustCode + "'");
		}
		String cnlIntfCode = cnlSysIntfIpLimitConditionDto.getCnlIntfCode();
		if (StringUtils.isNotEmpty(cnlIntfCode)) {
			hql.append(" and cnl.cnlIntfCode = '" + cnlIntfCode + "'");
		}
		String ipRangeFrom = cnlSysIntfIpLimitConditionDto.getIpRangeFrom();
		if (StringUtils.isNotEmpty(ipRangeFrom)) {
			//hql.append(" and to_number(replace('cnl.ipRangeFrom','.','')) <= to_number(replace('" + ipRangeFrom + "','.',''))");
			hql.append(" and cnl.ipRangeFrom >= '" + ipRangeFrom + "'");
		}
		String ipRangeTo = cnlSysIntfIpLimitConditionDto.getIpRangeTo();
		if (StringUtils.isNotEmpty(ipRangeTo)) {
			//hql.append(" and to_number(replace('cnl.ipRangeTo','.','')) >= to_number(replace('" + ipRangeTo + "','.',''))");
			hql.append(" and cnl.ipRangeTo <= '" + ipRangeTo + "'");
		}
		queryPage.setHqlString(hql.toString());
		System.out.println(hql.toString());
		IPage<CnlSysIntfIpLimitDto> page = super.findPageByHql(queryPage,CnlSysIntfIpLimitDto.class);
		System.out.println(page.getRows().toString());
		return page;
	}
	/**
	 * 根据ID得到需要修改的数据
	 */
	@Override
	public CnlSysIntfCfg findSysIntfCfgbyId(String id) {
		logger.info("entering action::CnlSysIntfDaoImpl.load()...");
		CnlSysIntfCfg csif = null;
		StringBuffer hql = new StringBuffer("select cs.id as id,cs.cnlCnlCode as cnlCnlCode,cf.cnlCustCode as cnlCustCode,cs.accessCode as accessCode,"
				+ "cs.currency as currency,cs.perTransLimit as perTransLimit,cs.dayLimit as dayLimit,"
				+ "cs.weekLimit as weekLimit,cs.monthLimit as monthLimit,cs.yearLimit as yearLimit,cs.lmtOpt as lmtOpt,"
				+ "cs.lmtOptTime as lmtOptTime,cs.comments as comments from CnlSysIntf as cs,CnlCnlCfg as cf "
				+ "where cs.isValid='"+Constants.IS_VALID_VALID+"' and cf.isValid='"+Constants.IS_VALID_VALID+"' "
				+ "and  cs.cnlCnlCode = cf.cnlCnlCode and cs.id = '"+id+"'");
		List<CnlSysIntfCfg> list = super.findByHql(hql.toString(), null, null, CnlSysIntfCfg.class);
		if(list.size() > 0){
			csif = list.get(0);
		}
		return csif;
	}
	/**
	 * 判断币种不能为空 得到ipage
	 */
	@Override
	public IPage<CnlSysIntfCfg> findSysIntf(QueryPage queryPage,String cnlCustCode,String accessCode,String currency){
		StringBuffer hql = new StringBuffer("select cs.id as id,cs.cnlCnlCode as cnlCnlCode,cf.cnlCustCode as cnlCustCode,cs.accessCode as accessCode,"
				+ "cs.currency as currency,cs.perTransLimit as perTransLimit,cs.dayLimit as dayLimit,"
				+ "cs.weekLimit as weekLimit,cs.monthLimit as monthLimit,cs.yearLimit as yearLimit,cs.lmtOpt as lmtOpt,"
				+ "cs.lmtOptTime as lmtOptTime,cs.comments as comments from CnlSysIntf as cs,CnlCnlCfg as cf "
				+ "where cs.isValid='"+Constants.IS_VALID_VALID+"' and cf.isValid='"+Constants.IS_VALID_VALID+"' "
						+ "and cs.cnlCnlCode = cf.cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			hql.append(" and cf.cnlCustCode = '").append(cnlCustCode).append("'");
		}
		if (StringUtils.isNotEmpty(accessCode)) {
			hql.append(" and cs.accessCode = '").append(accessCode).append("'");
		}
		if (StringUtils.isNotEmpty(currency)) {
			hql.append(" and cs.currency = '").append(currency).append("'");
		}
		hql.append(" order by cs.createTime desc");
		System.out.println("hql====="+hql.toString());
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		queryPage.setHqlString(hql.toString());
		IPage<CnlSysIntfCfg> page = findPageByHql(queryPage,CnlSysIntfCfg.class);
		return page;
	}

	@Override
	public void deleteSysIntfByid(CnlSysIntf cnlSysIntf){
		super.remove(cnlSysIntf);
	}
	/**
	 * 网关接入号与商户号是否匹配
	 */
	@Override
	public CnlSysIntfCfg isExitAccessCode(String accessCode,String cnlCustCode){
		CnlSysIntfCfg cnlSysIntfCfg = null;
		String hql = "select cs.id as id,cs.cnlCnlCode as cnlCnlCode,cf.cnlCustCode as cnlCustCode,cs.accessCode as accessCode,"
				+ "cs.currency as currency,cs.perTransLimit as perTransLimit,cs.dayLimit as dayLimit,"
				+ "cs.weekLimit as weekLimit,cs.monthLimit as monthLimit,cs.yearLimit as yearLimit,cs.lmtOpt as lmtOpt,"
				+ "cs.lmtOptTime as lmtOptTime,cs.comments as comments from CnlSysIntf as cs,CnlCnlCfg as cf "
				+ "where cs.isValid='"+Constants.IS_VALID_VALID+"' and cf.isValid='"+Constants.IS_VALID_VALID+"' "
				+ "and cs.accessCode = '"+accessCode+"' and cf.cnlCustCode='"+cnlCustCode+"' and cs.cnlCnlCode = cf.cnlCnlCode";
		List<CnlSysIntfCfg> list = super.findByHql(hql, null, null, CnlSysIntfCfg.class);
		if(list.size() > 0)
			cnlSysIntfCfg = list.get(0);
		return cnlSysIntfCfg;
	}
	/**
	 * 检验币种是否为空且网关接入号与商户号是否匹配
	 */
	public CnlSysIntfCfg isExitCurrency(String accessCode,String cnlCustCode){
		CnlSysIntfCfg cnlSysIntfCfg = null;
		String hql = "select cs.id as id,cs.cnlCnlCode as cnlCnlCode,cf.cnlCustCode as cnlCustCode,cs.accessCode as accessCode,"
				+ "cs.currency as currency,cs.perTransLimit as perTransLimit,cs.dayLimit as dayLimit,"
				+ "cs.weekLimit as weekLimit,cs.monthLimit as monthLimit,cs.yearLimit as yearLimit,cs.lmtOpt as lmtOpt,"
				+ "cs.lmtOptTime as lmtOptTime,cs.comments as comments from CnlSysIntf as cs,CnlCnlCfg as cf "
				+ "where cs.isValid='"+Constants.IS_VALID_VALID+"' and cf.isValid='"+Constants.IS_VALID_VALID+"' and cs.currency is null "
				+ "and cs.accessCode = '"+accessCode+"' and cf.cnlCustCode='"+cnlCustCode+"' and cs.cnlCnlCode = cf.cnlCnlCode";
		List<CnlSysIntfCfg> list = super.findByHql(hql, null, null, CnlSysIntfCfg.class);
		if(list.size() > 0)
			cnlSysIntfCfg = list.get(0);
		return cnlSysIntfCfg;
	}
	/**
	 * 根据网关接入号得到渠道系统信息
	 */
	public CnlSysIntf findByAccessCode(String accessCode){
		
		String  hql="select intf  from CnlSysIntf  intf  where intf.accessCode=? and intf.isValid='"+Constants.IS_VALID_VALID+"'";
		CnlSysIntf csi=super.findByValue(hql, accessCode).get(0);
		
		return csi;
	}
	/**
	 * 检验币种是否为空且网关接入号与商户号是否匹配
	 */
	public List<CnlSysIntfCfg> findBycurrency(){
		String hql = "select cs.id as id,cs.cnlCnlCode as cnlCnlCode,cf.cnlCustCode as cnlCustCode,cs.accessCode as accessCode,"
				+ "cs.currency as currency,cs.perTransLimit as perTransLimit,cs.dayLimit as dayLimit,"
				+ "cs.weekLimit as weekLimit,cs.monthLimit as monthLimit,cs.yearLimit as yearLimit,cs.lmtOpt as lmtOpt,"
				+ "cs.lmtOptTime as lmtOptTime,cs.comments as comments from CnlSysIntf as cs,CnlCnlCfg as cf "
				+ "where cs.isValid='"+Constants.IS_VALID_VALID+"' and cf.isValid='"+Constants.IS_VALID_VALID+"' "
						+ "and cs.cnlCnlCode = cf.cnlCnlCode and cs.currency is null";
		List<CnlSysIntfCfg> list = super.findByHql(hql, null, null,CnlSysIntfCfg.class);
		return list;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean checkCnlSysIntf(String cnlCustCode, String cnlIntfCode) {
		String hql = "from CnlCnlCfg cnl,CnlSysIntf sys where cnl.cnlCnlCode = sys.cnlCnlCode and cnl.cnlCustCode = ? "
				+ "and sys.cnlIntfCode = ? and cnl.isValid='"+Constants.IS_VALID_VALID+"' and sys.isValid='"+Constants.IS_VALID_VALID+"'";
		Object[] params = new Object[]{cnlCustCode,cnlIntfCode};
		List<?> list = super.getHibernateTemplate().find(hql, params);
		if(list.size() == 1){
			return true;
		}
		return false;
	}
	/**
	 * 根据渠道编号得到渠道系统信息
	 */
	public CnlSysIntf findByCnlCnlCode(String cnlCnlCode){
		String  hql="select intf  from CnlSysIntf  intf  where intf.cnlCnlCode=? and intf.isValid='"+Constants.IS_VALID_VALID+"'";
		CnlSysIntf csi=super.findByValue(hql, cnlCnlCode).get(0);
		if(csi != null)
			return csi;
		else
			return null;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean checkIpLimit(String cnlCustCode, String cnlIntfCode) {
		String hql = "from CnlCnlCfg cnl,CnlSysIntf sys where cnl.cnlCnlCode = sys.cnlCnlCode and cnl.cnlCustCode = ? and sys.cnlIntfCode = ? and sys.ipRangeFrom is not null and sys.ipRangeTo is not null";
		Object[] params = new Object[]{cnlCustCode,cnlIntfCode};
		List<?> list = super.getHibernateTemplate().find(hql, params);
		if(list.size() == 1){
			return true;
		}
		return false;
	}

}
