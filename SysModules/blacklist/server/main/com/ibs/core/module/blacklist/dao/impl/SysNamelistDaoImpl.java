/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.blacklist.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.core.module.blacklist.dao.ISysNamelistDao;
import com.ibs.core.module.blacklist.domain.SysNamelist;
import com.ibs.core.module.blacklist.dto.BlackListToNoticDto;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_NAMELIST
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysNamelistDaoImpl extends BaseEntityDao<SysNamelist> implements
		ISysNamelistDao {

	public IPage<SysNamelist> findSysNamelistByPage(QueryPage queryPage) {
		logger.info("entering action::SysNamelistDaoImpl.findSysNamelistByPage()...");
		return super.findPageBy(queryPage);
	}

	public SysNamelist load(String id) {
		logger.info("entering action::SysNamelistDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(SysNamelist sysNamelist) {
		logger.info("entering action::SysNamelistDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(sysNamelist);
	}

	/**
	 * 删除数据
	 */
	public void delete(String id) {
		logger.info("entering action::SysNamelistDaoImpl.delete()...");
		
		super.remove(id);
	}
	
	public IPage<SysNamelist> findSysNamelistByValidAndPage(QueryPage queryPage) {
		logger.info("entering action::SysNamelistDaoImpl.findSysNamelistByValidAndPage()...");
		String hql=" select  sysnamelist from SysNamelist  sysnamelist   ";
		
		queryPage.addEqualSearch("isValid",Constants.IS_VALID_VALID);
		queryPage.setHqlString(hql);
		return super.findPageBy(queryPage);
	}

	
	 
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SysNamelist> findByBlackList(BlackListToNoticDto blackListToNoticDto){
		//银行卡号
		String card=blackListToNoticDto.getCard();
		//危险国家
		String country=blackListToNoticDto.getCountry();
		//邮箱
		String email=blackListToNoticDto.getEmail();
		//手机号
		String phone=blackListToNoticDto.getPhone();
		
		//hql   过滤重复  只用查询 nlType 和 nlID
		
		StringBuffer before=new StringBuffer("select  distinct snl.nlType, snl.nlId from  SysNamelist snl  where 1=0  ");
		
		//param 参数
		List<Object> args=new  ArrayList<Object>();
		
	 
		
		
		
		if(StringUtils.isNotBlank(card)){
			//银行卡号存在
			before.append("or (snl.nlType=? and snl.nlId=?) ");
			args=setObject(args, Constants.BLACKLIST_TYPE_BANK_CARD_NO, card);
		}
		
		if(StringUtils.isNotBlank(country)){
			
			before.append("or (snl.nlType=? and snl.nlId=?) ");
			
			args=setObject(args, Constants.BLACKLIST_TYPE_HIGH_RISK_COUNTRY, country);
			
		}
		
		if(StringUtils.isNotBlank(email)){
			
			 
			before.append("or (snl.nlType=? and snl.nlId=?) ");
			
			args=setObject(args, Constants.BLACKLIST_TYPE_EMAIL, email);
			
		}
		
		if(StringUtils.isNotBlank(phone)){
			
			 
			before.append("or (snl.nlType=? and snl.nlId=?) ");
			
			args=setObject(args, Constants.BLACKLIST_TYPE_CELLPHONE, phone);
			
		}
		
		String hql=before.append("  and  snl.isValid=? ").toString();
		
		args.add(Constants.BLACKLIST_TYPE_EMAIL);
		
		Object[] arg=args.toArray();
		
		List  snl=super.findByValue(hql, arg);
		
//		Object[] snlObj=super.findByValue(hql, arg);
		
		
		 return snl;
	}
	
	
	/**
	 * setList
	 * @param args
	 * @param obj0
	 * @param obj1
	 * @return
	 */
	public List<Object> setObject(List<Object> args,String obj0,String  obj1){
		
		args.add(obj0);
		args.add(obj1);
		
		return args;
		
	}
	
	/**
	 * 根据黑名单类型和值判断是否存在
	 */
	@Override
	public List<SysNamelist> findByNlTypeAndNlId(String nlType, String nlId) {
		// TODO Auto-generated method stub
		
		String  hql="select snl  from SysNamelist snl where  snl.nlType=? and snl.nlId=? and snl.isValid=?";
		
		List<Object>  list= new ArrayList<Object>();
		
		list.add(nlType);
		list.add(nlId);
		list.add(Constants.IS_VALID_VALID);
		
		List<SysNamelist> sysNamelistList = super.find(hql, list);
		
		
		return sysNamelistList;
	}
 
}
