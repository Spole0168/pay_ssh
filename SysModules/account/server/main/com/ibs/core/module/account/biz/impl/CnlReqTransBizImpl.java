/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.account.biz.ICnlReqTransBiz;
import com.ibs.core.module.account.dao.ICnlReqTransDao;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.account.utils.DtoUtils;
import com.ibs.core.module.cnlcust.dao.ICnlCustDao;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlReqTransBizImpl extends BaseBiz implements ICnlReqTransBiz {

	private ICnlReqTransDao cnlReqTransDao;
	private IExcelService excelService;
	private ICnlCustDao cnlCustDao;
	
	@Override
	public IPage<CnlReqTrans> findCnlReqTransByPage(QueryPage queryPage) {
		logger.info("entering action::CnlReqTransBizImpl.findCnlReqTransByPage()...");
		return cnlReqTransDao.findCnlReqTransByPage(queryPage);
	}

	@Override
	public CnlReqTrans getCnlReqTransById(String id) {
		logger.info("entering action::CnlReqTransBizImpl.getCnlReqTransById()...");
		return cnlReqTransDao.load(id);
	}

	@Override
	public CnlReqTrans saveCnlReqTrans(CnlReqTrans cnlReqTrans) {
		logger.info("entering action::CnlReqTransBizImpl.saveCnlReqTrans()...");
		cnlReqTrans.setId(null);
		cnlReqTransDao.saveOrUpdate(cnlReqTrans);
		return cnlReqTrans;
	}

	@Override
	public CnlReqTrans updateCnlReqTrans(CnlReqTrans cnlReqTrans) {
		logger.info("entering action::CnlReqTransBizImpl.updateCnlReqTrans()...");
		cnlReqTransDao.saveOrUpdate(cnlReqTrans);
		return cnlReqTrans;
	}

	@Override
	public void exportCnlReqTrans(ExportSetting exportSetting) {
		logger.info("entering action::CnlReqTransBizImpl.exportCnlReqTrans()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlReqTrans> cnlReqTransPage = (Page<CnlReqTrans>) cnlReqTransDao
				.findCnlReqTransByPage(exportSetting);

		excelService.exportToFile(cnlReqTransPage.getRows(), exportSetting);
	}

	public ICnlReqTransDao getCnlReqTransDao() {
		logger.info("entering action::CnlReqTransBizImpl.getCnlReqTransDao()...");
		return cnlReqTransDao;
	}

	public void setCnlReqTransDao(ICnlReqTransDao cnlReqTransDao) {
		logger.info("entering action::CnlReqTransBizImpl.setCnlReqTransDao()...");
		this.cnlReqTransDao = cnlReqTransDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlReqTransBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlReqTransBizImpl.setExcelService()...");
		this.excelService = excelService;
	}
	/**
	 *多表查询
	 */
	@Override
	public IPage<CnlReqTransDto> findReqTranBizByMoreTable(QueryPage queryPage, String conditon) {
		logger.info("entering action::CnlReqTransBizImpl.findReqTranBizByMoreTable()...");
		IPage<CnlReqTransDto> page = this.cnlReqTransDao.findReqTranBizByMoreTable(queryPage,conditon);
		return page;
	}

	@Override
	public List<CnlReqTrans> findCnlReqTransByOneCondition(String columName, String condition) {
		logger.info("entering action::CnlReqTransBizImpl.findCnlReqTransByOneCondition()...");
		List<CnlReqTrans> list =cnlReqTransDao.findCnlReqTransByOneCondition(columName,condition);
		return list;
	}
	/**
	 * 返回一个关于企业用户的明细的结果
	 */
	@Override
	public Map<String, Object> findDetail(String reqInnerNum, String reqType) {
		logger.info("entering action::CnlReqTransBizImpl.findDetail()...");
		//判定申请单类型
		//如果是充值，提现
		if(Constants.REQ_TYPE_TOP_UP_VALUE.equals(reqType)||
				Constants.REQ_TYPE_WITHDRAW_VALUE.equals(reqType)){
			//调用方法
			CnlReqTransDto dto=cnlReqTransDao.findTransDetail(reqInnerNum);
			//判断查询结果是否为空
			if(dto!=null){
				//设置返回结果
				Map<String, Object> result = new HashMap<String,Object>();
				result.put("cnlReqTransDto", dto);
				result.put("resultString", "bank");
				return result;
			}
		}
		
		//如果是清结算
		/*if(Constants.REQ_TYPE_CLEARING_AND_SETTLEMENT_VALUE.equals(reqType)){
			//调用方法
			CnlReqTransDto dto=cnlReqTransDao.findBalanceDetail(reqInnerNum);
			//判断查询结果是否为空
			if(dto!=null){
				//设置返回结果
				Map<String, Object> result = new HashMap<String,Object>();
				result.put("cnlReqTransDto", dto);
				//个人,添加个人类型的结果集
				result.put("resultString", "balance");
				return result;
			}
		}*/
		
		//如果是个人开户更新注销
		if(Constants.REQ_TYPE_INDIVIDUAL_SIGN_UP_VALUE.equals(reqType)||
				Constants.REQ_TYPE_UPDATE_INDIVIDUAL_CUSTOMER_VALUE.equals(reqType)||
				Constants.REQ_TYPE_INDIVIDUAL_SIGN_OFF_VALUE.equals(reqType)){
			//调用方法,获得req表和cust_trace表中数据
			CnlReqTransDto dto=cnlReqTransDao.findPersonAboutCustTrace(reqInnerNum);
			//判断不为空
			if(dto==null){
				return null;
			}
			Map<String, Object> result = new HashMap<String,Object>();
			//如果类型为开户且状态不为成功
			if(Constants.REQ_TYPE_ENTERPRISE_SIGN_UP_VALUE.equals(reqType)&&!Constants.REQ_STATUS_SUCCESS.equals(dto.getReqStatus())){
				result.put("cnlReqTransDto", dto);
			}else{
				//查询T_CNL_CUST、T_CNL_CUST_COMPANY
				CnlReqTransDto dto2=cnlCustDao.findPersonAboutCustPersonal(dto.getCnlCustCode());
				//如果dto2为空,返回dto
				if(dto2==null){
					result.put("cnlReqTransDto", dto);
				}else{
					try {
						//判断是否注销
						if(Constants.CNL_CUST_STATUS_WRITTEN_OFF.equals(dto2.getCustStatus())){
							dto.setLogoutTime(dto.getUpdateTime());
						}
						DtoUtils.copyBasicTypeProperties(dto2, dto);
						result.put("cnlReqTransDto", dto2);
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			//个人,添加个人类型的结果集
			result.put("resultString", "personal");
			return result;
		}
		
		//如果是企业开户、开户确认、跟新和注销
		if(Constants.REQ_TYPE_ENTERPRISE_SIGN_UP_VALUE.equals(reqType)||
				Constants.REQ_TYPE_UPDATE_ENTERPRISE_CUSTOMER_VALUE.equals(reqType)||
				Constants.REQ_TYPE_ENTERPRISE_SIGN_OFF_VALUE.equals(reqType)||
				Constants.REQ_TYPE_ENTERPRISE_OPEN_ACCOUNT_VALUE.equals(reqType)){
			//调用方法
			StringBuffer hql = new StringBuffer();
			//查询T_CNL_REQ_TRANS、T_CNL_TRANS_TRACE
			CnlReqTransDto dto = cnlReqTransDao.findCompanyAboutCustTrace(reqInnerNum);
			if(dto==null){
				return null;
			}
			Map<String, Object> result = new HashMap<String,Object>();
			//如果类型为开户且状态不为成功
			if(Constants.REQ_TYPE_ENTERPRISE_SIGN_UP_VALUE.equals(reqType)&&!Constants.REQ_STATUS_SUCCESS.equals(dto.getReqStatus())){
				result.put("cnlReqTransDto", dto);
			}else{
				//查询T_CNL_CUST、T_CNL_CUST_COMPANY
				CnlReqTransDto dto2= cnlCustDao.findCompanyAboutCustCompany(dto.getCnlCustCode());
				//如果dto2为空,返回dto
				if(dto2==null){
					result.put("cnlReqTransDto", dto);
				}
				try {
					//判断是否注销
					if(Constants.CNL_CUST_STATUS_WRITTEN_OFF.equals(dto2.getCustStatus())){
						dto.setLogoutTime(dto.getUpdateTime());
					}
					DtoUtils.copyBasicTypeProperties(dto2, dto);
					result.put("cnlReqTransDto", dto2);
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//个人,添加个人类型的结果集
			result.put("resultString", "company");
			return result;
		}
		return null;
	}

	public ICnlCustDao getCnlCustDao() {
		return cnlCustDao;
	}

	public void setCnlCustDao(ICnlCustDao cnlCustDao) {
		this.cnlCustDao = cnlCustDao;
	}

	@Override
	public IPage<CnlReqTransDto> findBalance(QueryPage queryPage, String reqInnerNum) {
		//调用方法
		IPage<CnlReqTransDto> page=cnlReqTransDao.findBalanceDetail(queryPage,reqInnerNum);
		//判断查询结果是否为空
		return page;
	}
	
}
