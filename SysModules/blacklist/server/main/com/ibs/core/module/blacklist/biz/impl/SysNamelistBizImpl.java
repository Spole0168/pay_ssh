/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.blacklist.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.blacklist.biz.ISysNamelistBiz;
import com.ibs.core.module.blacklist.dao.ISysNamelistDao;
import com.ibs.core.module.blacklist.domain.SysNamelist;
import com.ibs.core.module.blacklist.dto.BlackListToNoticDto;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.violationRecord.dao.ISysViolationRecordDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.DateUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_NAMELIST
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysNamelistBizImpl extends BaseBiz implements ISysNamelistBiz {

	private ISysNamelistDao sysNamelistDao;
	private IExcelService excelService;
	private ISysViolationRecordDao sysViolationRecordDao;
	
	

	public ISysNamelistDao getSysNamelistDao() {
		logger.info("entering action::SysNamelistBizImpl.getSysNamelistDao()...");
		return sysNamelistDao;
	}

	public void setSysNamelistDao(ISysNamelistDao sysNamelistDao) {
		logger.info("entering action::SysNamelistBizImpl.setSysNamelistDao()...");
		this.sysNamelistDao = sysNamelistDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::SysNamelistBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::SysNamelistBizImpl.setExcelService()...");
		this.excelService = excelService;
	}


	@Override
	public IPage<SysNamelist> findSysNamelistByPage(QueryPage queryPage) {
		logger.info("entering action::SysNamelistBizImpl.findSysNamelistByPage()...");
		return sysNamelistDao.findSysNamelistByPage(queryPage);
	}

	@Override
	public SysNamelist getSysNamelistById(String id) {
		logger.info("entering action::SysNamelistBizImpl.getSysNamelistById()...");
		
		return sysNamelistDao.load(id);
	}

	public ISysViolationRecordDao getSysViolationRecordDao() {
		logger.info("entering action::SysNamelistBizImpl.getSysViolationRecordDao()...");
		return sysViolationRecordDao;
	}

	public void setSysViolationRecordDao(ISysViolationRecordDao sysViolationRecordDao) {
		logger.info("entering action::SysNamelistBizImpl.setSysViolationRecordDao()...");
		this.sysViolationRecordDao = sysViolationRecordDao;
	}
	
	/**
	 * 获取设置审批的字段
	 * @return
	 */
	public  SysNamelist getReview(String username,String id){
		
		 SimpleDateFormat date = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        
		SysNamelist snl=sysNamelistDao.load(id);
		snl.setReviewer(username);
		snl.setReviewTime(DateUtils.convert(date.format(new Date()),DateUtils.DATE_TIME_FORMAT));
		snl.setRtime(date.format(new Date()));
		return  snl;
	}

	@Override
	public SysNamelist saveSysNamelist(SysNamelist sysNamelist,String createuser) {
		logger.info("entering action::SysNamelistBizImpl.saveSysNamelist()...");
//		DATA_DICT_TYPE__IS_VALID
		sysNamelist.setId(null);
		sysNamelist.setIsValid(Constants.IS_VALID_VALID);
		sysNamelist.setCreator(createuser);
		sysNamelist.setCreateTime(new Date());
		sysNamelist.setUpdator(createuser);
		sysNamelist.setUpdateTime(new Date());
		sysNamelist.setStatus(Constants.IS_VALID_VALID);
		
		sysNamelistDao.saveOrUpdate(sysNamelist);
		return sysNamelist;
	}

	@Override
	public SysNamelist updateSysNamelist(SysNamelist sysNamelist,String updateuser) {
		logger.info("entering action::SysNamelistBizImpl.updateSysNamelist()...");
		
		String type=sysNamelist.getNlType();
		String nlid=sysNamelist.getNlId();
		String  desc=sysNamelist.getNlDesc();
		String id=sysNamelist.getId();
		SysNamelist  snl=sysNamelistDao.load(id);
		//状态
		String  status=snl.getStatus();
		//如果状态为拒绝的情况下进行修改,需要将状态改为待审核
		if(Constants.BLCAKLIST_STATUS_REJECT.equals(status)){
			
			snl.setStatus(Constants.BLCAKLIST_STATUS_WAITING);
		}
		snl.setNlDesc(desc);
		snl.setNlId(nlid);
		snl.setNlType(type);
		snl.setUpdator(updateuser);
		snl.setUpdateTime(new Date());
		snl.setIsValid(Constants.BLCAKLIST_STATUS_WAITING);
		sysNamelistDao.saveOrUpdate(snl);
		return snl;
	}
	 
	@Override
	public void exportSysNamelist(ExportSetting exportSetting) {
		logger.info("entering action::SysNamelistBizImpl.exportSysNamelist()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<SysNamelist> sysNamelistPage = (Page<SysNamelist>) sysNamelistDao
				.findSysNamelistByPage(exportSetting);

		excelService.exportToFile(sysNamelistPage.getRows(), exportSetting);
	}

	
	/**
	 * 删除选中的列
	 * @param idStringArray  选中列的id
	 */
	public void deleteBlackList(String[] idStringArray) {
		for (int i = 0; i < idStringArray.length; i++) {
//			sysNamelistDao.delete(idStringArray[i]);
			delByValid(idStringArray[i]);
		}
	}
	/**
	 * 根据id获取黑名单的数据
	 * @param id
	 * @return
	 */
	public SysNamelist getInfo(String id){
		SysNamelist  sysNamelist=sysNamelistDao.load(id);
		 
		return  sysNamelist;
	}
	
	@Override
	public void delByValid(String id) {
		// TODO Auto-generated method stub
		SysNamelist  sysnamelist=sysNamelistDao.load(id);
		
		sysnamelist.setIsValid(Constants.IS_VALID_NOT_VALID);

		sysNamelistDao.saveOrUpdate(sysnamelist);
		
		
	}
	/**
	 * 查询
	 */
	public IPage<SysNamelist> findSysNamelistByValidAndPage(QueryPage queryPage){
		
		return sysNamelistDao.findSysNamelistByValidAndPage(queryPage);
	}
	
	/**
	 * 审批
	 * @param sysnamelist
	 */
	public   void  doReview(SysNamelist  sysnamelist){
		//备注
		String  desc=sysnamelist.getNlDesc();
		
		//审批状态
		String  status=sysnamelist.getStatus();
		
		String  reviewer=sysnamelist.getReviewer();
		
		Date  reverTime=sysnamelist.getReviewTime();
		
		//获取id
		String  id=sysnamelist.getId();
		
		//获取数据
		SysNamelist  snl=  sysNamelistDao.load(id);
		
		snl.setStatus(status);
		
		snl.setNlDesc(desc);
		
		snl.setReviewer(reviewer);
		
		snl.setReviewTime(reverTime);
		
		sysNamelistDao.saveOrUpdate(snl);
	}
	
	
	/**
	 * ajax方式提交审批获取审批的信息
	 * @param sysnamelist
	 * @return
	 */
	public  boolean   doReviewByAjax(SysNamelist  sysnamelist){
		boolean  falg=true;
			
		try{
				//备注
				String  desc=sysnamelist.getNlDesc();
				
				//审批状态
				String  status=sysnamelist.getStatus();
				
				String  reviewer=sysnamelist.getReviewer();
				
				Date  reverTime=sysnamelist.getReviewTime();
				
				//获取id
				String  id=sysnamelist.getId();
				
				//获取数据
				SysNamelist  snl=  sysNamelistDao.load(id);
				
				snl.setStatus(status);
				
				snl.setNlDesc(desc);
				
				snl.setReviewer(reviewer);
				
				snl.setReviewTime(reverTime);
				
				sysNamelistDao.saveOrUpdate(snl);
				
				
		}catch(Exception e){
			falg=false;
			
			e.getStackTrace();
		}
		return   falg;
		
	}
	
 
	
	
	/**
	 * 校验银行卡号,国家信息是否存在于黑名单中
	 * @param card
	 * @param country
	 */
	public boolean  valid(BlackListToNoticDto blacklisttonoticdto){
		
		@SuppressWarnings("rawtypes")
		List list= sysNamelistDao.findByBlackList( blacklisttonoticdto);
		
		//SysViolationRecord
		List<SysViolationRecord> svr=new ArrayList<SysViolationRecord>();
		
		if(list==null){
			
			return  false;
		}else{
			 
			for (int i = 0; i < list.size(); i++) {
				
				
				Object[] obj=(Object[]) list.get(i);
				
				if(obj.length>0){
					String type=(String)obj[0];
					String nlId=(String)obj[1];
					
					blacklisttonoticdto.setViolationId(nlId);
					blacklisttonoticdto.setViolationSubType(type);;
					
//					blacklisttonoticdto.setViolationType();
					
					//推送到通知中 
					svr.add(setBlackListDto(blacklisttonoticdto));
				}
				
			}
			
			sysViolationRecordDao.saveBatch(svr);
			
			
			return  true;
			
		}
	}
	
	/**
	 * 封装对应的blacklist
	 * @param balckListToNoticDto
	 * @return
	 */
	public SysViolationRecord setBlackListDto(BlackListToNoticDto balckListToNoticDto){
		
		SysViolationRecord sysViolationRecord =new SysViolationRecord();
		//有相同的情况 推送到 通知事件查询的表中
		sysViolationRecord.setViolationType(Constants.IS_VALID_NOT_VALID);

		sysViolationRecord.setViolationSubType(balckListToNoticDto.getViolationSubType());
		sysViolationRecord.setViolationId(balckListToNoticDto.getViolationId());
		sysViolationRecord.setReqNum(balckListToNoticDto.getNum());
		sysViolationRecord.setCnlIntfCode(balckListToNoticDto.getCnlIntfCode());
		sysViolationRecord.setCnlCnlCode(balckListToNoticDto.getCnlCnlCode());
		sysViolationRecord.setCreateTime(new Date());
		sysViolationRecord.setId(null);
		
		if(Constants.BLACKLIST_TYPE_EMAIL.equals(balckListToNoticDto.getViolationSubType())){
//			BLACKLIST_TYPE		01	电子邮件
//			BLACKLIST_TYPE		02	手机号码
//			BLACKLIST_TYPE		03	银行卡号
//			BLACKLIST_TYPE		04	高风险国家
			 
			sysViolationRecord.setViolationDesc("黑名单 电子邮件 异常");
			
				
		}else if(Constants.BLACKLIST_TYPE_CELLPHONE.equals(balckListToNoticDto.getViolationSubType())){
			
			sysViolationRecord.setViolationDesc("黑名单 手机号码 异常");
			
		}else if(Constants.BLACKLIST_TYPE_BANK_CARD_NO.equals(balckListToNoticDto.getViolationSubType())){
			
			sysViolationRecord.setViolationDesc("黑名单 银行卡号 异常");
			
		}else if(Constants.BLACKLIST_TYPE_HIGH_RISK_COUNTRY.equals(balckListToNoticDto.getViolationSubType())){
			
			sysViolationRecord.setViolationDesc("黑名单 高风险国家 异常");
			
		}
		
	 
		
		return  sysViolationRecord;
		
	}
	
	
	
	
	/**
	 * 判断黑名单编辑时是否重复
	 */
	@Override
	public List<SysNamelist> isSame(String nlType, String nlId) {
		// TODO Auto-generated method stub
		List<SysNamelist> sysNamelist = sysNamelistDao.findByNlTypeAndNlId(nlType, nlId);
		
		return sysNamelist;
	}
	
	/**
	 * 编辑时进行判断
	 * @param nlType
	 * @param nlId
	 * @param id
	 * @return
	 */
	public List<SysNamelist> isSame(String nlType,String nlId,String id){
		
		SysNamelist sysnamelist=sysNamelistDao.load(id);
		
		String val=sysnamelist.getNlId();
		
		List<SysNamelist> sysNamelist =null;
		
		if(!val.equals(nlId)){
			sysNamelist= sysNamelistDao.findByNlTypeAndNlId(nlType, nlId);
			
		}else{
			
			sysNamelist =new ArrayList<SysNamelist>();
		}
		
		return sysNamelist;
		
	}
	
}
