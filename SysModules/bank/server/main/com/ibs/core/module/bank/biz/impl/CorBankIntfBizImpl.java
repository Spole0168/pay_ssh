/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.biz.impl;

import java.util.Collection;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.bank.biz.ICorBankIntfBiz;
import com.ibs.core.module.bank.dao.ICorBankIntfDao;

import com.ibs.core.module.bank.domain.BankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.reservedfund.dao.ICorReservedFundAcntDao;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.CollectionUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_INTF
 * @modify		: your comments goes here (author,date,reason).
 */

public class CorBankIntfBizImpl extends BaseBiz implements ICorBankIntfBiz {
	////
	private ICorBankIntfDao corBankIntfDao;
	private IExcelService excelService;
	 private IDataDictService dataDictService;


	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	@Override
	public IPage<CorBankIntf> findCorBankIntfByPage(QueryPage queryPage) {
		logger.info("entering action::CorBankIntfBizImpl.findCorBankIntfByPage()...");
		return corBankIntfDao.findCorBankIntfByPage(queryPage);
	}

	@Override
	public CorBankIntf getCorBankIntfById(String id) {
		logger.info("entering action::CorBankIntfBizImpl.getCorBankIntfById()...");
		return corBankIntfDao.load(id);
	}

	@Override
	public CorBankIntf saveCorBankIntf(CorBankIntf corBankIntf) {
		logger.info("entering action::CorBankIntfBizImpl.saveCorBankIntf()...");
		corBankIntf.setId(null);
		corBankIntfDao.saveOrUpdate(corBankIntf);
		return corBankIntf;
	}

	@Override
	public CorBankIntf updateCorBankIntf(CorBankIntf corBankIntf) {
		logger.info("entering action::CorBankIntfBizImpl.updateCorBankIntf()...");
		corBankIntfDao.saveOrUpdate(corBankIntf);
		return corBankIntf;
	}

	@Override
	public void exportCorBankIntf(ExportSetting exportSetting) {
		logger.info("entering action::CorBankIntfBizImpl.exportCorBankIntf()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorBankIntf> corBankIntfPage = (Page<CorBankIntf>) corBankIntfDao
				.findCorBankIntfByPage(exportSetting);

		excelService.exportToFile(corBankIntfPage.getRows(), exportSetting);
	}


	
	public ICorBankIntfDao getCorBankIntfDao() {
		logger.info("entering action::CorBankIntfBizImpl.getCorBankIntfDao()...");
		return corBankIntfDao;
	}

	public void setCorBankIntfDao(ICorBankIntfDao corBankIntfDao) {
		logger.info("entering action::CorBankIntfBizImpl.setCorBankIntfDao()...");
		this.corBankIntfDao = corBankIntfDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorBankIntfBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorBankIntfBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	//public saveBankInfoDTO (){}
	
	//DTO 页面查询
	@Override
	public IPage<CorBankInfoDTO> findCorBankInfoByPageAndMoreTable(QueryPage queryPage) {
		// TODO Auto-generated method stub
		logger.info("entering action::CorBankIntfBizImpl.findCorBankInfoByPageAndMoreTable()...");
		 IPage<CorBankInfoDTO> moreTable = corBankIntfDao.findCorBankInfoByPageAndMoreTable(queryPage);
		
		 
		 return moreTable;
	}

	@Override
	public CorBankInfoDTO getBankInnerCode(String  id,String  bid) {
		// TODO Auto-generated method stub
		return corBankIntfDao.findAddByPage(id, bid);
	}

/*	@Override
	public CorBankInfoDTO getInnerCode(String bankInnerCode) {

		return corBankIntfDao.findInnerCode(bankInnerCode) ;
	}*/
		@Override
	public BankInfoDTO getInnerCode(String id) {

		return corBankIntfDao.findInnerCode(id) ;
	}

		/*
		 * (non-Javadoc)判断页面信息bankInnerCode是否存在
		 * @see com.ibs.core.module.bank.biz.ICorBankIntfBiz#isSame(java.lang.String)
		 */
		@Override
		public List<CorBankIntf>  isSame(String bankInnerCode) {
			// TODO Auto-generated method stub
			 
			//查询到所有的数据
			List<CorBankIntf> cfglist=corBankIntfDao.checkBankInnerCode(bankInnerCode);
				
				 
			
			return  cfglist ;
		}



		@Override
		public boolean isSameEdit(String bankInnerCode, String id) {
			// TODO Auto-generated method stub
			boolean  falg=false;
			try{
				
				//根据Id获取对应对象
				CorBankIntf corBankIntf=corBankIntfDao.load(id);
				
				String  bankcode=corBankIntf.getBankInnerCode();
				//查询到所有的数据
				List<CorBankIntf> cfglist=corBankIntfDao.findAll();
				
				for (int i = 0; i < cfglist.size(); i++) {
					
					CorBankIntf cfg=cfglist.get(i);
					
					String code=cfg.getBankInnerCode();
					
					if(!code.equals(bankcode)){
						
					
					
						if(code.equals(bankInnerCode)||code==bankInnerCode){
							
							falg=true;
							
						}
					}
					
				}
			}catch(Exception  e){
		
				e.printStackTrace();
			}
			
			
			return  falg;
		}

		@Override
		public List<CorBankIntf> findAll() {
			

			return 	corBankIntfDao.findAll();
		}
	
	//查找银行名称
/*		public CorBankSetting getBankName(String bankInnerCode){
			
			return CorBankSettingBizImpl.;
		}*/
		
/*		
	//查找分行名称
		public BankInfoDTO getBankBranchName(String bankInnerCode){
			return null;
		}
		*/
		
		//查找备付金列表 public CorReservedFundAcnt findByBankNum(String bankNum);
	/*	public CorReservedFundAcnt findAcntInfo(String bankNum){
			return corReservedFundAcntDao.findByBankNum(bankNum);
		}*/
		
		
}
