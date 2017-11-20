/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlCustAcntBiz;
import com.ibs.core.module.account.dao.ICnlCustAcntDao;
import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustAcntDto;
import com.ibs.core.module.account.domain.OperatingAcntDto;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.CollectionUtils;




/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustAcntBizImpl extends BaseBiz implements ICnlCustAcntBiz {

	private ICnlCustAcntDao cnlCustAcntDao;
	 private IDataDictService dataDictService;
	
	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	/**
	 * 总帐户查询
	 */
	@Override
	public IPage<CnlCustAcntDto> findCnlCustAcntByPage(QueryPage queryPage,String custType,String cnlCustCode,String localName,String currency,
			String statisticalType,String max,String min) {
		logger.info("entering action::CnlCustAcntBizImpl.findCnlCustAcntByPage()...");
		IPage<CnlCustAcntDto> acntPageList =  cnlCustAcntDao.findCnlCustAcntByPage(queryPage,custType,cnlCustCode,localName,currency,
				statisticalType,max,min);
		Collection<CnlCustAcntDto> acntList = acntPageList.getRows();
		
		List<DataDict> currencyDicts = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		List<DataDict> acntStatusDicts = dataDictService.list(IDataDictService.DATA_DICT_TYPE__ACNT_STATUS);
		
		try {								
			CollectionUtils.transformCollection(acntList, "currency","currency", currencyDicts, "code", "name");	
			CollectionUtils.transformCollection(acntList, "acntStatus","acntStatus", acntStatusDicts, "code", "name");
		} catch (Exception e) {								
			throw new BizException(e.getMessage());							
		}								
      

		 return acntPageList;
	}

	/**
	 * 分帐户查询
	 */
	
	@Override
	public IPage<OperatingAcntDto> findOperatingAcntByPage(QueryPage queryPage, String custType, String cnlCustCode,
			String localName, String currency, String groupOfAccounts, String statisticalType, String max, String min) {
		
		logger.info("entering action::CnlCustAcntBizImpl.findOperatingAcntByPage()...");
		
		IPage<OperatingAcntDto>	operatingAcntPage = cnlCustAcntDao.findOperatingAcntByPage(queryPage, custType, cnlCustCode, localName,currency,groupOfAccounts,statisticalType,max,min);
		Collection<OperatingAcntDto> operatingList = operatingAcntPage.getRows();
		List<DataDict> currencyDicts = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		try {								
			CollectionUtils.transformCollection(operatingList, "currency",							
					"currency", currencyDicts, "code", "name");					
		} catch (Exception e) {								
			throw new BizException(e.getMessage());							
		}	
		List<DataDict> acntTypeDicts = dataDictService.list(IDataDictService.DATA_DICT_TYPE__ACNT_TYPE);
		try {								
			CollectionUtils.transformCollection(operatingList, "acntType",							
					"acntType", acntTypeDicts, "code", "name");					
		} catch (Exception e) {								
			throw new BizException(e.getMessage());							
		}	
		return operatingAcntPage;
	
	}
	
	
	
	@Override
	public CnlCustAcnt getCnlCustAcntById(String id) {
		logger.info("entering action::CnlCustAcntBizImpl.getCnlCustAcntById()...");
		return cnlCustAcntDao.load(id);
	}

	@Override
	public CnlCustAcnt saveCnlCustAcnt(CnlCustAcnt cnlCustAcnt) {
		logger.info("entering action::CnlCustAcntBizImpl.saveCnlCustAcnt()...");
		cnlCustAcnt.setId(null);
		cnlCustAcntDao.saveOrUpdate(cnlCustAcnt);
		return cnlCustAcnt;
	}

	@Override
	public CnlCustAcnt updateCnlCustAcnt(CnlCustAcnt cnlCustAcnt) {
		logger.info("entering action::CnlCustAcntBizImpl.updateCnlCustAcnt()...");
		cnlCustAcntDao.saveOrUpdate(cnlCustAcnt);
		return cnlCustAcnt;
	}

	@Override
	public void exportCnlCustAcnt(ExportSetting exportSetting) {
		
	}

	public ICnlCustAcntDao getCnlCustAcntDao() {
		logger.info("entering action::CnlCustAcntBizImpl.getCnlCustAcntDao()...");
		return cnlCustAcntDao;
	}

	public void setCnlCustAcntDao(ICnlCustAcntDao cnlCustAcntDao) {
		logger.info("entering action::CnlCustAcntBizImpl.setCnlCustAcntDao()...");
		this.cnlCustAcntDao = cnlCustAcntDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlCustAcntBizImpl.getExcelService()...");
		return null;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlCustAcntBizImpl.setExcelService()...");
		
	}

	

	

}
