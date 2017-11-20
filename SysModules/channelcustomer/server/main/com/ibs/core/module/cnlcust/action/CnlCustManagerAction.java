/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.action;

import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;

import java.util.List;
import java.util.Collection;

import com.ibs.core.module.account.domain.CustPersonal;
import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.cnlcust.biz.ICnlCustBiz;
import com.ibs.core.module.cnlcust.domain.CnlCust;
import com.ibs.core.module.cnlmgr.biz.ICnlCnlCfgBiz;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlCustManagerAction extends CrudBaseAction {

	private ICnlCustBiz cnlCustBiz;
	private String id;
	private CnlCust cnlCust;
	private CustPersonalInfo custPersonalInfo;

	private List<OptionObjectPair> custTypeList;
	private List<OptionObjectPair> certTypeList;
	private List<OptionObjectPair> realNameLevelList;
	private List<OptionObjectPair> custLevelList;
	private List<OptionObjectPair> cnlCustStatusList;
	private IDataDictBiz dataDictBiz;
	private List<CnlCnlCfg> cfgList;
	
	private IDataDictService dataDictService;
	private ICnlCnlCfgBiz cnlCnlCfgBiz;
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	
	public String list() {
		logger.info("entering action::CnlCustManagerAction.list()...");
		custTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
		certTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		realNameLevelList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CERTIFICATION_LEVEL);
		custLevelList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_LEVEL);
		cnlCustStatusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CNL_CUST_STATUS);
		cfgList = cnlCnlCfgBiz.findAll();
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlCustManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlCustManagerAction.modify()...");
		cnlCust = cnlCustBiz.getCnlCustById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search(){
		logger.info("entering action::CnlCustManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
			String cnlCustCode = getSearchFields().get("cnlCustCode");
			String cnlSysName = getSearchFields().get("cnlSysName");
			String cnlCustType = getSearchFields().get("cnlCustType");
			String custStatus = getSearchFields().get("custStatus");
			String certType = getSearchFields().get("certType");
			String certNum = getSearchFields().get("certNum");
			String realNamelevel = getSearchFields().get("realNamelevel");
			String custLevel = getSearchFields().get("custLevel");
			String createStartTime = getSearchFields().get("createStartTime");
			String createEndTime = getSearchFields().get("createEndTime");
			/**
			 * 第二步，执行查询,并设置结果
			 */
			
			Page<CustPersonal> result = (Page<CustPersonal>) cnlCustBiz
					.findCnlCustBylike(queryPage, cnlCustCode, cnlSysName, cnlCustType, custStatus,
							 certType, certNum, realNamelevel, custLevel, createStartTime, createEndTime);
			Collection<CustPersonal> custPersonal = result.getRows();
			List<DataDict> custTypelist = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
			List<DataDict> certTypelist = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
			List<DataDict> realNameLevellist = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CERTIFICATION_LEVEL);
			List<DataDict> custLevellist = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CUST_LEVEL);
			List<DataDict> cnlCustStatus = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CNL_CUST_STATUS);
			List<DataDict> counteyList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__COUNTRY);
			try {								
				CollectionUtils.transformCollection(custPersonal, "cnlCustType","cnlCustType", custTypelist, "code", "name");	
				CollectionUtils.transformCollection(custPersonal, "certType","certType", certTypelist, "code", "name");		
				CollectionUtils.transformCollection(custPersonal, "realNamelevel","realNamelevel", realNameLevellist, "code", "name");		
				CollectionUtils.transformCollection(custPersonal, "custLevel","custLevel", custLevellist, "code", "name");		
				CollectionUtils.transformCollection(custPersonal, "custStatus","custStatus", cnlCustStatus, "code", "name");
				CollectionUtils.transformCollection(custPersonal, "country","country", counteyList, "code", "name");
			} catch (Exception e) {								
				throw new BizException(e.getMessage());							
			}								

			setResult(result);
	
			/**
			 * 第三步，返回
			 */
			return AJAX_RETURN_TYPE;
		
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CnlCustManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlCustBiz.saveCnlCust(cnlCust);
		}
		// 如果是修改
		else {
			cnlCustBiz.updateCnlCust(cnlCust);
		}

		return SUCCESS;
	}
	
	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CnlCustManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlCustManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlCustBiz.exportCnlCust(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlCustManagerAction.setQueryCondition()...");
		
		// realNamelevel
		String realNamelevel = getSearchFields().get("realNamelevel");
		if (StringUtils.isNotEmpty(realNamelevel)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.realNamelevel",  realNamelevel);
				queryPage.addEqualSearch("c.realNamelevel", realNamelevel);
			}
		}
		// CNL_CUST_CODE
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.cnlCustCode", "%" + cnlCustCode + "%");
				queryPage.addLikeSearch("c.cnlCustCode", cnlCustCode);
			}
		}

		// CNL_CNL_CODE
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.cnlCnlCode", "%" + cnlCnlCode + "%");
				queryPage.addLikeSearch("c.cnlCnlCode", cnlCnlCode);
			}
		}

		// CNL_CUST_TYPE
		String cnlCustType = getSearchFields().get("cnlCustType");
		if (StringUtils.isNotEmpty(cnlCustType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.cnlCustType", cnlCustType);
				queryPage.addEqualSearch("c.cnlCustType", cnlCustType);
			}
		}

		// CUST_STATUS
		String custStatus = getSearchFields().get("custStatus");
		if (StringUtils.isNotEmpty(custStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.custStatus", custStatus);
				queryPage.addEqualSearch("c.custStatus", custStatus);
			}
		}

		// CERT_TYPE
		String certType = getSearchFields().get("certType");
		if (StringUtils.isNotEmpty(certType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.certType", certType);
				queryPage.addEqualSearch("c.certType", certType);
			}
		}

		// CERT_NUM
		String certNum = getSearchFields().get("certNum");
		if (StringUtils.isNotEmpty(certNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.certNum", "%" + certNum + "%");
				queryPage.addLikeSearch("c.certNum", certNum);
			}
		}

		// createStartTime
		String createStartTime = getSearchFields().get("createStartTime");
		if (StringUtils.isNotEmpty(createStartTime)) {
			if (null != queryPage) {
				queryPage.addGreatEqualSearch("c.createStartTime",
						DateUtils.convert(createStartTime,
								DateUtils.DATE_TIME_FORMAT));
			}
		}
		
		// createEndTime
		String createEndTime = getSearchFields().get("createEndTime");
		if (StringUtils.isNotEmpty(createEndTime)) {
			if (null != queryPage) {
				queryPage.addLessEqualSearch("c.createEndTime",
						DateUtils.convert(createEndTime,
								DateUtils.DATE_TIME_FORMAT));
			}
		}

		// CUST_LEVEL
		String custLevel = getSearchFields().get("custLevel");
		if (StringUtils.isNotEmpty(custLevel)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("c.custLevel", custLevel);
				queryPage.addEqualSearch("c.custLevel", custLevel);
			}
		}
	}

	public ICnlCustBiz getCnlCustBiz() {
		logger.info("entering action::CnlCustManagerAction.getCnlCustBiz()...");
		return cnlCustBiz;
	}

	public void setCnlCustBiz(ICnlCustBiz cnlCustBiz) {
		logger.info("entering action::CnlCustManagerAction.setCnlCustBiz()...");
		this.cnlCustBiz = cnlCustBiz;
	}

	public CnlCust getCnlCust() {
		logger.info("entering action::CnlCustManagerAction.getCnlCust()...");
		return cnlCust;
	}

	public void setCnlCust(CnlCust cnlCust) {
		logger.info("entering action::CnlCustManagerAction.setCnlCust()...");
		this.cnlCust = cnlCust;
	}

	public String getId() {
		logger.info("entering action::CnlCustManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlCustManagerAction.setId()...");
		this.id = id;
	}

	public CustPersonalInfo getCustPersonalInfo() {
		return custPersonalInfo;
	}

	public void setCustPersonalInfo(CustPersonalInfo custPersonalInfo) {
		this.custPersonalInfo = custPersonalInfo;
	}

	
	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public List<OptionObjectPair> getCustTypeList() {
		return custTypeList;
	}

	public void setCustTypeList(List<OptionObjectPair> custTypeList) {
		this.custTypeList = custTypeList;
	}

	public List<OptionObjectPair> getCertTypeList() {
		return certTypeList;
	}

	public void setCertTypeList(List<OptionObjectPair> certTypeList) {
		this.certTypeList = certTypeList;
	}

	public List<OptionObjectPair> getRealNameLevelList() {
		return realNameLevelList;
	}

	public void setRealNameLevelList(List<OptionObjectPair> realNameLevelList) {
		this.realNameLevelList = realNameLevelList;
	}

	public List<OptionObjectPair> getCustLevelList() {
		return custLevelList;
	}

	public void setCustLevelList(List<OptionObjectPair> custLevelList) {
		this.custLevelList = custLevelList;
	}

	public List<OptionObjectPair> getCnlCustStatusList() {
		return cnlCustStatusList;
	}

	public void setCnlCustStatusList(List<OptionObjectPair> cnlCustStatusList) {
		this.cnlCustStatusList = cnlCustStatusList;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public List<CnlCnlCfg> getCfgList() {
		return cfgList;
	}

	public void setCfgList(List<CnlCnlCfg> cfgList) {
		this.cfgList = cfgList;
	}

	public ICnlCnlCfgBiz getCnlCnlCfgBiz() {
		return cnlCnlCfgBiz;
	}

	public void setCnlCnlCfgBiz(ICnlCnlCfgBiz cnlCnlCfgBiz) {
		this.cnlCnlCfgBiz = cnlCnlCfgBiz;
	}
	
	
}
