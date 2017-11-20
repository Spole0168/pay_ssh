/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.domain.CnlCommentDto;
import com.ibs.core.module.cnlcust.dao.ICnlCustCompanyDao;
import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlmgr.biz.ICnlCnlCfgBiz;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.dao.ICnlSysIntfDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.HibernateUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CNL_CFG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCnlCfgBizImpl extends BaseBiz implements ICnlCnlCfgBiz {

	private ICnlCnlCfgDao cnlCnlCfgDao;
	private ICnlSysIntfDao cnlSysIntfDao;
	private ICnlCustCompanyDao  cnlCustCompanyDao;
	private IExcelService excelService;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	HttpServletRequest request;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public ICnlSysIntfDao getCnlSysIntfDao() {
		logger.info("entering action::CnlCnlCfgBizImpl.getCnlSysIntfDao()...");
		return cnlSysIntfDao;
	}

	public void setCnlSysIntfDao(ICnlSysIntfDao cnlSysIntfDao) {
		logger.info("entering action::CnlCnlCfgBizImpl.setCnlSysIntfDao()...");
		this.cnlSysIntfDao = cnlSysIntfDao;
	}

	public ICnlCustCompanyDao getCnlCustCompanyDao() {
		logger.info("entering action::CnlCnlCfgBizImpl.getCnlCustCompanyDao()...");
		return cnlCustCompanyDao;
	}

	public void setCnlCustCompanyDao(ICnlCustCompanyDao cnlCustCompanyDao) {
		logger.info("entering action::CnlCnlCfgBizImpl.setCnlCustCompanyDao()...");
		this.cnlCustCompanyDao = cnlCustCompanyDao;
	}

	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		logger.info("entering action::CnlCnlCfgBizImpl.getCnlCnlCfgDao()...");
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		logger.info("entering action::CnlCnlCfgBizImpl.setCnlCnlCfgDao()...");
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCnlCfgBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCnlCfgBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	
	@Override
	public IPage<CnlCnlCfg> findCnlCnlCfgByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCnlCfgBizImpl.findCnlCnlCfgByPage()...");
		return cnlCnlCfgDao.findCnlCnlCfgByPage(queryPage);
	}

	@Override
	public CnlCnlCfg getCnlCnlCfgById(String id) {
		logger.info("entering action::CnlCnlCfgBizImpl.getCnlCnlCfgById()...");
		return cnlCnlCfgDao.load(id);
	}

	@Override
	public CnlCnlCfg saveCnlCnlCfg(CnlCnlCfg cnlCnlCfg) {
		logger.info("entering action::CnlCnlCfgBizImpl.saveCnlCnlCfg()...");
		cnlCnlCfg.setId(null);
		cnlCnlCfgDao.saveOrUpdate(cnlCnlCfg);
		return cnlCnlCfg;
	}

	@Override
	public CnlCnlCfg updateCnlCnlCfg(CnlCnlCfg cnlCnlCfg) {
		logger.info("entering action::CnlCnlCfgBizImpl.updateCnlCnlCfg()...");
		cnlCnlCfgDao.saveOrUpdate(cnlCnlCfg);
		return cnlCnlCfg;
	}

	@Override
	public void exportCnlCnlCfg(ExportSetting exportSetting) {
		logger.info("entering action::CnlCnlCfgBizImpl.exportCnlCnlCfg()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlCnlCfg> cnlCnlCfgPage = (Page<CnlCnlCfg>) cnlCnlCfgDao
				.findCnlCnlCfgByPage(exportSetting);

		excelService.exportToFile(cnlCnlCfgPage.getRows(), exportSetting);
	}


	@Override
	public IPage<CnlCommentDto> findCommentByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCnlCfgBizImpl.getCnlCommentById()...");
		
		
		return cnlCnlCfgDao.findCommentByPage(queryPage);
	}
	@Override
	public CnlCommentDto saveCnlComment(CnlCommentDto cnlComment,String creator) {
		logger.info("entering action::CnlCnlCfgBizImpl.saveCnlCnlCfg()...");

		
		CnlCnlCfg cnlcnlcfg= cnlComment.getCnlCnlCfg();
		
		CnlSysIntf cnlsysintf=cnlComment.getCnlSysIntf();
		
		CnlCustCompany cnlcustcompany=cnlComment.getCnlCustCompany();
		
		try{
			//关联字段
			String  cnlcode=cnlcnlcfg.getCnlCnlCode();
			
			cnlsysintf.setCnlCnlCode(cnlcode);
			
			cnlsysintf.setCreator(creator);
			
			cnlsysintf.setCreateTime(new Date());
			//必须填写
			cnlsysintf.setId(null);
			cnlsysintf.setIsValid(Constants.IS_VALID_VALID);
			
			String  cnlcustcode=cnlcustcompany.getCnlCustCode();
			
			cnlcnlcfg.setCnlCustCode(cnlcustcode);
			
			cnlcnlcfg.setCreator(creator);
			
			cnlcnlcfg.setCreateTime(new Date());
			
			cnlcnlcfg.setIsValid(Constants.IS_VALID_VALID);
			
			cnlcnlcfg.setId(null);
			//注意 
			cnlCnlCfgDao.saveOrUpdate(cnlcnlcfg);
			
			cnlSysIntfDao.saveOrUpdate(cnlsysintf);
			//更新 company中的数据
//			cnlCustCompanyDao.saveOrUpdate(cnlcustcompany);
			
			
			
		}catch(Exception e){
			logger.error("CnlCnlCfgBizImpl   saveCnlComment() --->"+e.getMessage());
			e.printStackTrace();
		}
		return cnlComment;
	}
	
	 
	
	@Override
	public CnlCommentDto updateCnlComment(CnlCommentDto cnlComment,String updator) {
		logger.info("entering action::CnlCnlCfgBizImpl.saveCnlCnlCfg()...");

		String cfgid=cnlComment.getCnlCnlCfg().getId();
	 
		CnlCnlCfg cfg=cnlCnlCfgDao.load(cfgid);
		CnlCustCompany cnlcustcompany=cnlComment.getCnlCustCompany();
		CnlCnlCfg cnlcnlcfg= cnlComment.getCnlCnlCfg();
		CnlSysIntf cnlsysintf=cnlComment.getCnlSysIntf();
		
		cfg.setCnlCnlCode(cnlcnlcfg.getCnlCnlCode());
		cfg.setCnlSysName(cnlcnlcfg.getCnlSysName());
		cfg.setCnlDesc(cnlcnlcfg.getCnlDesc());
		cfg.setContractEffectDate(cnlcnlcfg.getContractEffectDate());
		cfg.setContractExpireDate(cnlcnlcfg.getContractExpireDate());
		//关联字段
		String  cnlcode=cnlcnlcfg.getCnlCnlCode();
		cnlsysintf.setCnlCnlCode(cnlcode);
		cnlsysintf.setIsValid(Constants.IS_VALID_VALID);
		cnlsysintf.setUpdator(updator);
		cnlsysintf.setUpdateTime(new Date());
		
		String  cnlcustcode=cnlcustcompany.getCnlCustCode();
		cfg.setCnlCustCode(cnlcustcode);
		cfg.setUpdator(updator);
		cfg.setUpdateTime(new Date()); 
		
	 
		
		cnlCnlCfgDao.saveOrUpdate(cfg);
		cnlSysIntfDao.saveOrUpdate(cnlsysintf);
//		cnlCustCompanyDao.saveOrUpdate(cnlcustcompany);
		
		
		
		return cnlComment;
	}
	
	@Override
	public CnlCommentDto getInfo(String cnlCustCode) {
		// TODO Auto-generated method stub
		logger.info("entering action::CnlCnlCfgBizImpl.getInfo()...");
		CnlCustCompany custCompany=	cnlCustCompanyDao.getInfoByCnlCustCode(cnlCustCode);
		CnlCommentDto cnlCommentDto=new CnlCommentDto();
	    cnlCommentDto.setCnlCustCompany(custCompany);
		return  cnlCommentDto;
	}
 
	@Override
	public CnlCommentDto getData(String id, String csiid, CnlCommentDto cnlComment) {
		// TODO Auto-generated method stub
		logger.info("entering action::CnlCnlCfgBizImpl.getData()...");

		//获取cnlCnlCfg 的数据
		CnlCnlCfg  cnlcnlcfg=cnlCnlCfgDao.load(id);
		//根据cnlcnlCode获取cnlSysIntf的数据
		CnlSysIntf cnlsysintf=cnlSysIntfDao.load(csiid);
		
		//将数据放到cnlCommentDto
		cnlComment.setCnlCnlCfg(cnlcnlcfg); 
		cnlComment.setCnlSysIntf(cnlsysintf);
	 	
		return  cnlComment;
	}

	 
	
	@Override
	public CnlCommentDto getCommentByCode(String id ) {
		// TODO Auto-generated method stub
		CnlCommentDto cnlComment=new CnlCommentDto();
		
		//获取cnlCnlCfg 的数据
		CnlCnlCfg  cnlcnlcfg=cnlCnlCfgDao.load(id);
		
		//根据对应的cnlcnlcode获取intf数据
		String  code=cnlcnlcfg.getCnlCnlCode().trim();
		
		//根据对应的custcode获取对应的cnlcustcompany的数据
		String  cnlcustcode=cnlcnlcfg.getCnlCustCode().trim();
		
		//根据cnlcnlCode获取cnlSysIntf的数据
		CnlSysIntf cnlsysintf=cnlSysIntfDao.getIntf(code);
		
		//获取cnlcustcompany
		CnlCustCompany  custCompany=cnlCustCompanyDao.getInfoByCnlCustCode(cnlcustcode);
		
		//将数据放到cnlCommentDto
		cnlComment.setCnlCnlCfg(cnlcnlcfg); 
		cnlComment.setCnlSysIntf(cnlsysintf);
		cnlComment.setCnlCustCompany(custCompany);
		
		return cnlComment;
	}
	/**
	 * 获取所有数据
	 */
	public List<CnlCnlCfg> findAll(){
		return cnlCnlCfgDao.findAll();
	}
	/**
	 * 根据获取的数据判断渠道编号在数据库中是否存在
	 * @return
	 */
	public List<CnlCnlCfg> isSame(String cnlcnlcode){
	 
		//查询到所有的数据
		List<CnlCnlCfg> cfglist=cnlCnlCfgDao.checkSame(cnlcnlcode);
				
		 
		 
		return  cfglist;
	}
	
	/**
	 * 判断在渠道客户编码在cnl_cust_company表中是否存在
	 * 
	 * @return
	 */
	public boolean existCnlCustCode(String cnlCustCode){
		//TODO 判断渠道客户编码是否存在
		boolean falg=false;
		
		CnlCustCompany cnlCustCompany = cnlCustCompanyDao.getInfoByCnlCustCode(cnlCustCode);
		
		if(cnlCustCompany!=null){
		
			falg=true;
			
		} 
		
		return falg;
	}
	
	
	
	
}
