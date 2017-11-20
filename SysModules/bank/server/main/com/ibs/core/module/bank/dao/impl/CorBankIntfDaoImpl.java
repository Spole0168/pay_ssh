/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.bank.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ibs.core.module.bank.dao.ICorBankIntfDao;
import com.ibs.core.module.bank.dao.ICorBankSettingDao;
import com.ibs.core.module.bank.domain.BankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankAcnt;
import com.ibs.core.module.bank.domain.CorBankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.bank.domain.CorBankSetting;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_INTF
 * @modify		: your comments goes here (author,date,reason).
 */


public class CorBankIntfDaoImpl extends BaseEntityDao<CorBankIntf> implements
		ICorBankIntfDao {
	
	private ICorBankSettingDao corBankSettingDao = new CorBankSettingDaoImpl();
	
	private CorBankSetting corBankSetting=new CorBankSetting();

	public CorBankSetting getCorBankSetting() {
		return corBankSetting;
	}

	public void setCorBankSetting(CorBankSetting corBankSetting) {
		this.corBankSetting = corBankSetting;
	}

	public ICorBankSettingDao getCorBankSettingDao() {
		return corBankSettingDao;
	}

	public void setCorBankSettingDao(ICorBankSettingDao corBankSettingDao) {
		this.corBankSettingDao = corBankSettingDao;
	}

	public IPage<CorBankIntf> findCorBankIntfByPage(QueryPage queryPage) {
		logger.info("entering action::CorBankIntfDaoImpl.findCorBankIntfByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorBankIntf load(String id) {
		logger.info("entering action::CorBankIntfDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorBankIntf corBankIntf) {
		logger.info("entering action::CorBankIntfDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corBankIntf);
	}
/**
 * 查询方法
 */
	@Override
	public IPage<CorBankInfoDTO> findCorBankInfoByPageAndMoreTable(QueryPage queryPage) 
	{

		String condition = queryPage.getHqlString();

		StringBuffer s= new StringBuffer("select a.id as id ,  b.id as bid , "
				+ "a.bankInnerCode as bankInnerCode ,  "
				+"a.isValid as isValid,"
				+ "b.bankName as bankName , "
				+ "b.bankBranchName as bankBranchName ,"
				+ "b.bankLevel as bankLevel , "
				+ "b.country as country , "
				+ "b.bankAddr as bankAddr  , "
				+ "b.contractEffectDate as contractEffectDate ,"
				+ "b.contractExpireDate as contractExpireDate  , "
				+ "a.techSupportName as techSupportName ,"
				+ "a.techSupportPhonenum as techSupportPhonenum ,"
				+ "a.techSupportEmail as techSupportEmail ,"
				+ "a.businessSupportName as businessSupportName , "
				+ "a.businessSupportPhonenum as businessSupportPhonenum ,"
				+ " a.businessSupportEmail as businessSupportEmail , "
				+ "b.bankNum as bankNum ,"
				+ " b.swiftCode as swiftCode , "
				+ "b.branchCode as branchCode ,"
				+ " b.ngsnCode as ngsnCode , "
				+ "b.createTime as createTime , "
				+ "b.creator as creator , "
				+ "b.desc as desc  "
				+ "from CorBankIntf  a  ,  CorBankSetting  b  "
				+ "where  a.bankInnerCode = b.bankInnerCode ").append(condition);

		String hql= s.toString();
		 System.out.println("HQL:=============================="+hql);
		 queryPage.setHqlString(hql);
//		 queryPage.addQueryCondition("  and b.isValid = ? ", Constants.IS_VALID_VALID );
//		 queryPage.addAfterLikeSearch("isValid",Constants.IS_VALID_VALID);
//		 queryPage.addQueryCondition("  and a.isValid = ? ", Constants.IS_VALID_VALID );
//		 queryPage.addAfterLikeSearch("isValid",Constants.IS_VALID_VALID);
		IPage<CorBankInfoDTO> page= findPageByHql(queryPage,CorBankInfoDTO.class);
		// TODO Auto-generated method stub
		return page;
	}
	
	/**
	 * 银行修改查询
	 * 
	 */

	@Override
	public CorBankInfoDTO findAddByPage(String  id,String bid) {
		// TODO Auto-generated method stub
		logger.info("entering action::CorBankIntfDaoImpl.AddByPage()...");
		
		CorBankIntf cbi= super.load(id);
		
		CorBankSetting cbs= corBankSettingDao.load(bid);
		
		CorBankInfoDTO corBankInfoDTO=new CorBankInfoDTO();
		
		corBankInfoDTO.setCorBankIntf(cbi);
		
		corBankInfoDTO.setCorBankSetting(cbs);
		
//		String condition = queryPage.getHqlString();
//		
//		StringBuffer s = new StringBuffer("select a.id as id ,  a.bankInnerCode as bankInnerCode ,  b.bankName as bankName , b.bankLevel as bankLevel ,  b.bankBranchName as bankBranchName , b.bankAddr as bankAddr  , b. contractEffectDate as contractEffectDate , b.contractExpireDate as contractExpireDate  , a.techSupportName as techSupportName ,a.techSupportPhonenum as techSupportPhonenum ,a.techSupportEmail as techSupportEmail ,a.businessSupportName as businessSupportName , a.businessSupportPhonenum as businessSupportPhonenum , a.businessSupportEmail as businessSupportEmail , b.bankNum as bankNum , b.swiftCode as swiftCode , b.branchCode as branchCode , b.ngsnCode as ngsnCode ,b.createTime as createTime , b.creator as creator , b.desc as desc  from CorBankIntf  a  ,  CorBankSetting  b  where  a.bankInnerCode = b.bankInnerCode ").append(condition);
//		
//		String hql=s.toString();
//		
//		queryPage.setHqlString(hql);
//		
//		IPage<CorBankInfoDTO> page = findPageByHql(queryPage,CorBankInfoDTO.class);
//		
		return corBankInfoDTO;
	}
	
	
	/**
	 * 获取数据
	 * @param id
	 */
	public CorBankInfoDTO getData(String id,String csiid,CorBankInfoDTO corBankInfoDTO){
		//获取CorBankIntf 的数据
		CorBankIntf  corBankIntf=super.load(id);
		
		//根据cnlcnlCode获取cnlSysIntf的数据
		CorBankSetting corBankSetting=corBankSettingDao.load(csiid);
		
		//将数据放到cnlCommentDto
		corBankInfoDTO.setCorBankSetting(corBankSetting);
		corBankInfoDTO.setCorBankIntf(corBankIntf);
		
		
		return corBankInfoDTO;
	}
	
	/**
	 * 添加   
	 */
	@Override
	public void saveCorBankInfoDTO(CorBankInfoDTO corBankInfoDTO) {
		// TODO Auto-generated method stub
		logger.info("entering action::CorBankIntfDaoImpl.saveCorBankInfoDTO()...");
		String id=null;
		
		CorBankIntf corBankIntf= corBankInfoDTO.getCorBankIntf();
		
		CorBankSetting corBankSetting=corBankInfoDTO.getCorBankSetting();

		//关联字段
		String  bankInnerCode=corBankInfoDTO.getBankInnerCode();
		corBankSetting.setBankInnerCode(bankInnerCode);
		//必须填写
		corBankSetting.setId(null);
		
		corBankIntf.setBankInnerCode(bankInnerCode);
		corBankIntf.setId(null);
		//注意 
		super.saveOrUpdate(corBankIntf);
//	cnlSysIntfDao.saveOrUpdate(cnlsysintf);
		
		corBankSettingDao.saveOrUpdate(corBankSetting);
	}

/*	@SuppressWarnings("unused")
	@Override
	public CorBankInfoDTO findInnerCode(String bankInnerCode) {
		String hql = "select  a.bankInnerCode as bankInnerCode ,  b.bankName as bankName , b.bankBranchName as bankBranchName ,b.bankLevel as bankLevel ,a.country as country , b.bankAddr as bankAddr  , b. contractEffectDate as contractEffectDate , b.contractExpireDate as contractExpireDate  , a.techSupportName as techSupportName ,a.techSupportPhonenum as techSupportPhonenum ,a.techSupportEmail as techSupportEmail ,a.businessSupportName as businessSupportName , a.businessSupportPhonenum as businessSupportPhonenum , a.businessSupportEmail as businessSupportEmail , b.bankNum as bankNum , b.swiftCode as swiftCode , b.branchCode as branchCode , b.ngsnCode as ngsnCode ,b.createTime as createTime , b.creator as creator , b.desc as desc  from CorBankIntf  a,CorBankSetting  b  where  a.bankInnerCode=b.bankInnerCode and a.bankInnerCode'"+bankInnerCode+"'";
		List<CorBankInfoDTO> list = super.findByHql(hql,null,null,CorBankInfoDTO.class);
		return list.isEmpty()?null:list.get(0);
	}*/
	
	@SuppressWarnings("all")
	@Override
	public BankInfoDTO findInnerCode(String id) {
		String hql = "select a.id as id , "
				+ " a.bankInnerCode as bankInnerCode , "
				+ " b.bankName as bankName , "
				+ " b.bankBranchName as bankBranchName ,"
				+ " b.bankLevel as bankLevel ,"
				+ " b.country as country , "
				+ " b.bankAddr as bankAddr  , "
				+ " b.contractEffectDate as contractEffectDate , "
				+ " b.contractExpireDate as contractExpireDate  , "
				+ " a.techSupportName as techSupportName ,"
				+ " a.techSupportPhonenum as techSupportPhonenum ,"
				+ " a.techSupportEmail as techSupportEmail ,"
				+ " a.businessSupportName as businessSupportName , "
				+ " a.businessSupportPhonenum as businessSupportPhonenum , "
				+ " a.businessSupportEmail as businessSupportEmail , "
				+ " b.bankNum as bankNum ,"
				+ " b.swiftCode as swiftCode , "
				+ " b.branchCode as branchCode , "
				+ " b.ngsnCode as ngsnCode ,"
				+ " b.createTime as createTime , "
				+ " b.creator as creator , "
				+ " b.desc as desc  from CorBankIntf  a,CorBankSetting  b  where  a.bankInnerCode=b.bankInnerCode and a.id = '"+id+"'";
		System.out.println(hql+"*********************************************");
//		List<CorBankInfoDTO> list = super.findByHql(hql,id,CorBankInfoDTO.class);
		List<BankInfoDTO> list = super.findByHql(hql,null,null,BankInfoDTO.class);
		System.out.println("==================================="+list.get(0));
		return list.isEmpty()?null:list.get(0);
	}

 

	/*
	 * 杜小林 
	 * 2016-7-19
	 */
	
	public String findCode(String code) {
		Criteria criteria = getSession().createCriteria(CorBankIntf.class);
		criteria.add(Restrictions.eq("bankCode", code));
		List<CorBankIntf> list = criteria.list();
		if(list.size()>0){
			return list.get(0).getIsValid();
		}else{
			return Constants.CNL_STATUS_VALID;
			//IS_VALID_VALID
		}
		
	}
	
	//
	public List<CorBankIntf> findAll(String bankInnerCode){
		String hql="from CorBankIntf where isValid=? ";
		
		return super.findByValue(hql, Constants.IS_VALID_VALID);
	}
	/**
	 * 校验银行编码是否存在
	 * @param bankInnerCode
	 * @return
	 */
	public List<CorBankIntf> checkBankInnerCode(String bankInnerCode){
		
		String hql="from CorBankIntf where isValid=? and bankInnerCode=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(Constants.IS_VALID_VALID);
		obj.add(bankInnerCode);		
		List<CorBankIntf> list=super.find(hql,obj);
		
		return list;
	}
	
	@Override
	public CorBankIntf findByCode(String code) {
	 List list = super.getHibernateTemplate().find("BANK_INNER_CODE", code);
		return (list.size()>0)?(CorBankIntf)list.get(0):null;
	};
	
	

	
	
}
