/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.action;

import com.ibs.portal.framework.util.DateUtils;

import java.util.List;

import com.ibs.core.module.account.biz.ICnlCustAcntBiz;
import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustAcntDto;
import com.ibs.core.module.account.domain.OperatingAcntDto;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlCustAcntManagerAction extends CrudBaseAction {

	private ICnlCustAcntBiz cnlCustAcntBiz;
	
	private String id;
	private CnlCustAcnt cnlCustAcnt;
	
	private IDataDictBiz dataDictBiz;
	
	private List<OptionObjectPair> custTypeList;
	
	private List<OptionObjectPair> currencyTypeList;
	
	private List<OptionObjectPair> acntTypeList;
	

	public List<OptionObjectPair> getAcntTypeList() {
		return acntTypeList;
	}

	public void setAcntTypeList(List<OptionObjectPair> acntTypeList) {
		this.acntTypeList = acntTypeList;
	}

	public List<OptionObjectPair> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<OptionObjectPair> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
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

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlCustAcntManagerAction.list()...");
		//数据字典获取下拉框内容
		custTypeList= dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
		currencyTypeList= dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		acntTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__ACNT_TYPE);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlCustAcntManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlCustAcntManagerAction.modify()...");
		cnlCustAcnt = cnlCustAcntBiz.getCnlCustAcntById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据  总帐户查询
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlCustAcntManagerAction.search()...");

		/**
		 * 第一步，获取查询参数
		 */
		try {
			String custType = this.getSearchFields().get("custTypeSearch");
			String cnlCustCode = this.getSearchFields().get("cnlCustCodeSearch");
			String localName = this.getSearchFields().get("localNameSearch");
			String currency = this.getSearchFields().get("currencySearch");
			String statisticalType = this.getSearchFields().get("statisticalTypeSearch");
			String  max = this.getSearchFields().get("maxSearch");
			String  min = this.getSearchFields().get("minSearch");
			//设置默认值
			if(StringUtils.isNotEmpty(statisticalType)){
				if(StringUtils.isEmpty(min)){
					min="0";
				}
				if(StringUtils.isEmpty(max)){
					max="999999";
				}
			}
			/**
			 * 第二步，执行查询,并设置结果
			 */
			Page<CnlCustAcntDto> result = (Page<CnlCustAcntDto>) cnlCustAcntBiz
					.findCnlCustAcntByPage(queryPage,custType,cnlCustCode,localName,currency,
							statisticalType,max,min);
			setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	
	/**
	 * Action方法，异步查询列表数据 分帐户
	 * 
	 * @return
	 */
	public String query() {
	
		logger.info("entering action::CnlCustAcntManagerAction.query()...");
		try {
			/**
			 * 第一步，获取查询参数
			 */
			String custType = this.getSearchFields().get("custTypeSearch");
			String cnlCustCode = this.getSearchFields().get("cnlCustCodeSearch");
			String localName = this.getSearchFields().get("localNameSearch");
			String currency = this.getSearchFields().get("currencySearch");
			String groupOfAccounts = this.getSearchFields().get("GroupOfAccounts");
			String statisticalType = this.getSearchFields().get("statisticalTypeSearch");
			String max = this.getSearchFields().get("maxSearch");
			String min = this.getSearchFields().get("minSearch");

			if(StringUtils.isNotEmpty(statisticalType)){
				if(StringUtils.isEmpty(min)){
					min="0";
				}
				if(StringUtils.isEmpty(max)){
					max="999999";
				}
			}
			
			/**
			 * 第二步，执行查询,并设置结果
			 */
			Page<OperatingAcntDto> result = (Page<OperatingAcntDto>)cnlCustAcntBiz.findOperatingAcntByPage(queryPage,custType,cnlCustCode,localName,currency,
							groupOfAccounts,statisticalType,max,min);
			setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}

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
		logger.info("entering action::CnlCustAcntManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlCustAcntBiz.saveCnlCustAcnt(cnlCustAcnt);
		}
		// 如果是修改
		else {
			cnlCustAcntBiz.updateCnlCustAcnt(cnlCustAcnt);
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
		logger.info("entering action::CnlCustAcntManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	
	
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlCustAcntManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlCustAcntBiz.exportCnlCustAcnt(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		

	}

	public ICnlCustAcntBiz getCnlCustAcntBiz() {
		logger.info("entering action::CnlCustAcntManagerAction.getCnlCustAcntBiz()...");
		return cnlCustAcntBiz;
	}

	public void setCnlCustAcntBiz(ICnlCustAcntBiz cnlCustAcntBiz) {
		logger.info("entering action::CnlCustAcntManagerAction.setCnlCustAcntBiz()...");
		this.cnlCustAcntBiz = cnlCustAcntBiz;
	}

	public CnlCustAcnt getCnlCustAcnt() {
		logger.info("entering action::CnlCustAcntManagerAction.getCnlCustAcnt()...");
		return cnlCustAcnt;
	}

	public void setCnlCustAcnt(CnlCustAcnt cnlCustAcnt) {
		logger.info("entering action::CnlCustAcntManagerAction.setCnlCustAcnt()...");
		this.cnlCustAcnt = cnlCustAcnt;
	}

	public String getId() {
		logger.info("entering action::CnlCustAcntManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlCustAcntManagerAction.setId()...");
		this.id = id;
	}

}
