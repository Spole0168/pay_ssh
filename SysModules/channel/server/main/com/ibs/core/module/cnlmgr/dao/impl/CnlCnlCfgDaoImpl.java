/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.cnlmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.classfile.Constant;
import org.hibernate.Criteria;

import com.ibs.core.module.account.domain.CnlCommentDto;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CNL_CFG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCnlCfgDaoImpl extends BaseEntityDao<CnlCnlCfg> implements
		ICnlCnlCfgDao {

	
	public IPage<CnlCnlCfg> findCnlCnlCfgByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCnlCfgDaoImpl.findCnlCnlCfgByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlCnlCfg load(String id) {
		logger.info("entering action::CnlCnlCfgDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlCnlCfg cnlCnlCfg) {
		logger.info("entering action::CnlCnlCfgDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlCnlCfg);
	}
	/**
	 * 渠道管理添加
	 */
	@Override
	public IPage<CnlCommentDto> findCommentByPage(QueryPage queryPage) {
		// TODO Auto-generated method stub
		logger.info("entering action::CnlCommentDaoImpl.findCommentByPage()...");
		
		String condition = queryPage.getHqlString();
 
		StringBuffer s = new StringBuffer("select cfg.id as id,csi.id as csiid,ccc.id as cccid, cfg.custCode as custCode,"
				+ "cfg.cnlCnlCode as cnlCnlCode,cfg.cnlCustCode as cnlCustCode,"
				+ "cfg.cnlSysName as cnlSysName,cfg.createTime as createTime,cfg.cnlDesc as cnlDesc,"
//				+"csi.perTransLimit as perTransLimit,csi.dayLimit as dayLimit,csi.weekLimit  as weekLimit ,"
//				+ "csi.monthLimit as monthLimit,csi.yearLimit as yearLimit,"
				+ "ccc.addr as addr ,ccc.country  as country,cfg.contractEffectDate as contractEffectDate,"
				+ "cfg.contractExpireDate as contractExpireDate,csi.accessKey as accessKey, "
				+ "csi.accessType as accessType,"
				+ "csi.sendSms  as sendSms,csi.sendEmail as sendEmail,csi.techSupportName as techSupportName,"
				+ "csi.techSupportPhonenum as techSupportPhonenum,csi.techSupportEmail as techSupportEmail,"
				+ "csi.businessSupportName as businessSupportName,csi.businessSupportPhonenum as businessSupportPhonenum,"
				+ "csi.businessSupportEmail as businessSupportEmail,cfg.creator as creator,"
				+ "csi.comments  as comments  from  CnlCnlCfg as cfg ,CnlSysIntf as csi,"
				+ "CnlCustCompany as ccc  where cfg.cnlCustCode=ccc.cnlCustCode  and  cfg.cnlCnlCode=csi.cnlCnlCode  ").append(condition);
		
		String hql=s.toString();
		
		queryPage.setHqlString(hql);
		
//		queryPage.addEqualSearch("isValid",Constants.IS_VALID_VALID);
		
		IPage<CnlCommentDto> page = findPageByHql(queryPage,CnlCommentDto.class);
		
		return page;
	}
	
	public List<CnlCnlCfg> findAll(){
		String hql=" select cfg from CnlCnlCfg cfg where cfg.isValid=?";
		List<CnlCnlCfg> list=super.findByValue(hql, Constants.IS_VALID_VALID);
		
		return list;
	}

	/**
	 * 校验该渠道编码是否存在
	 * @param cnlcnlcode
	 * @return
	 */
	public List<CnlCnlCfg> checkSame(String cnlcnlcode){
		
		String  hql="select cfg from CnlCnlCfg cfg where cfg.cnlCnlCode=? and cfg.isValid=?";
		List<Object> obj=new  ArrayList<Object>();
		obj.add(cnlcnlcode);
		obj.add(Constants.IS_VALID_VALID);
		List<CnlCnlCfg> list=super.find(hql, obj);
		return list;
		
	}
	
	@Override
	public List<CnlCnlCfg> getList() {
		Criteria criteria = getSession().createCriteria(CnlCnlCfg.class);
		return criteria.list();
	}
	 
	/*xw*/ 
	public String findCnlCustCodeByCnlCnlCode(String cnlCnlCode){
		logger.info("enter findCnlCustCodeByCnlCnlCode()");
		List<CnlCnlCfg> list=getHibernateTemplate().find("from CnlCnlCfg where cnlCnlCode=? and isValid=?",new Object[]{ cnlCnlCode,Constants.IS_VALID_VALID});
		if(null!=list&&list.size()>0){
			return list.get(0).getCnlCustCode();
		}
		return null;
	}
	public List<CnlCnlCfg> getCfgList(){
		List<CnlCnlCfg> list=getHibernateTemplate().find("from CnlCnlCfg");
		if(null!=list&&list.size()>0){
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public String findByCnlName(String cnlSysName){
		String sql="from CnlCnlCfg where cnlSysName=?";
		List<CnlCnlCfg> list=getHibernateTemplate().find("from CnlCnlCfg where cnlSysName=?", cnlSysName);
		if(null!=list&&list.size()>0){
			return list.get(0).getCnlCnlCode();
		}
		return null;
	}
	
}
