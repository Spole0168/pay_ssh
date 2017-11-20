/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.biz.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.cnlcust.utils.BeanUtil;
import com.ibs.core.module.cnlmgr.biz.ICnlSysIntfIpLimitBiz;
import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.cnlmgr.dao.ICnlSysIntfDao;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitConditionDto;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitDto;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlSysIntfIpLimitBizImpl extends BaseBiz implements ICnlSysIntfIpLimitBiz {

	private ICnlSysIntfDao cnlSysIntfDao;
	private ICnlCnlCfgDao cnlCnlCfgDao;
	private IExcelService excelService;
	
	/**
	 * 查询
	 */
	@Override
	public IPage<CnlSysIntfIpLimitDto> findCnlSysIntfByPage(QueryPage queryPage,
			CnlSysIntfIpLimitConditionDto cnlSysIntfIpLimitConditionDto) {
		logger.info("entering action::CnlSysIntfBizImpl.findCnlSysIntfByPage()...");
		return cnlSysIntfDao.findCnlSysIntfByPage(queryPage,cnlSysIntfIpLimitConditionDto);
	}
	
	/**
	 * 根据ID返回一条记录
	 */
	@Override
	public CnlSysIntfIpLimitDto getCnlSysIntfById(String id) {
		logger.info("entering action::CnlSysIntfBizImpl.getCnlSysIntfById()...");
		CnlSysIntfIpLimitDto cnlSysIntfIpLimitDto = new CnlSysIntfIpLimitDto();
		CnlSysIntf cnlSysIntf = cnlSysIntfDao.load(id);
		try {
			BeanUtil.copyBasicTypeProperties(cnlSysIntfIpLimitDto, cnlSysIntf);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cnlSysIntfIpLimitDto.setCnlCustCode(cnlCnlCfgDao.loadBy("cnlCnlCode", cnlSysIntf.getCnlCnlCode()).getCnlCustCode());
		System.out.println(cnlSysIntfIpLimitDto);
		return cnlSysIntfIpLimitDto;
	}

	/**
	 * 更新记录
	 */
	@Override
	public CnlSysIntf updateCnlSysIntf(CnlSysIntfIpLimitDto cnlSysIntfIpLimitDto) {
		logger.info("entering action::CnlSysIntfBizImpl.updateCnlSysIntf()...");
		CnlSysIntf cnlSysIntf = cnlSysIntfDao.loadBy("cnlIntfCode",cnlSysIntfIpLimitDto.getCnlIntfCode());
		System.out.println(cnlSysIntf);
		if(StringUtils.isNotEmpty(cnlSysIntfIpLimitDto.getIpRangeFrom())){
			cnlSysIntf.setIpRangeFrom(cnlSysIntfIpLimitDto.getIpRangeFrom());
		}
		if(StringUtils.isNotEmpty(cnlSysIntfIpLimitDto.getIpRangeTo())){
			cnlSysIntf.setIpRangeTo(cnlSysIntfIpLimitDto.getIpRangeTo());
		}
		if(StringUtils.isNotEmpty(cnlSysIntfIpLimitDto.getComments())){
			cnlSysIntf.setComments(cnlSysIntfIpLimitDto.getComments());
		}
		cnlSysIntf.setIpOptTime(new Date());
		cnlSysIntf.setIpOpt("admin");
		cnlSysIntfDao.saveOrUpdate(cnlSysIntf);
		return cnlSysIntf;
	}
	
	/**
	 * check
	 */
	@Override
	public boolean checkCnlSysIntf(String cnlCustCode, String cnlIntfCode) {
		logger.info("entering action::CnlSysIntfBizImpl.checkCnlSysIntf()...");
		
		return cnlSysIntfDao.checkCnlSysIntf(cnlCustCode,cnlIntfCode);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean checkIpLimit(String cnlCustCode, String cnlIntfCode) {
		logger.info("entering action::CnlSysIntfBizImpl.checkIpLimit()...");
		return cnlSysIntfDao.checkIpLimit(cnlCustCode,cnlIntfCode);
	}
	/**
	 * delete
	 * @return 
	 */
	@Override
	public boolean deleteIpLimit(String id) {
		logger.info("entering action::CnlSysIntfBizImpl.deleteCnlSysIntf()...");
		CnlSysIntf cnlSysIntf = cnlSysIntfDao.load(id);
		cnlSysIntf.setIpRangeFrom(null);
		cnlSysIntf.setIpRangeTo(null);
		cnlSysIntf.setComments(null);
		cnlSysIntf.setIpOptTime(new Date());
		cnlSysIntf.setIpOpt("admin");
		cnlSysIntfDao.saveOrUpdate(cnlSysIntf);
		return true;
	}
	@Override
	public void exportCnlSysIntf(ExportSetting exportSetting) {
		logger.info("entering action::CnlSysIntfBizImpl.exportCnlSysIntf()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlSysIntf> cnlSysIntfPage = (Page<CnlSysIntf>) cnlSysIntfDao
				.findCnlSysIntfByPage(exportSetting);

		excelService.exportToFile(cnlSysIntfPage.getRows(), exportSetting);
	}

	public ICnlSysIntfDao getCnlSysIntfDao() {
		logger.info("entering action::CnlSysIntfBizImpl.getCnlSysIntfDao()...");
		return cnlSysIntfDao;
	}

	public void setCnlSysIntfDao(ICnlSysIntfDao cnlSysIntfDao) {
		logger.info("entering action::CnlSysIntfBizImpl.setCnlSysIntfDao()...");
		this.cnlSysIntfDao = cnlSysIntfDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlSysIntfBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlSysIntfBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		return cnlCnlCfgDao;
	}

	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}









}
