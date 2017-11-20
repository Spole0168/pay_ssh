/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.action;

import java.util.List;

import com.ibs.core.module.corcust.biz.ICorCustPersonalBiz;
import com.ibs.core.module.corcust.dto.CorCompound;
import com.ibs.core.module.corcust.dto.CorCondition;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CorCustPersonalManagerAction extends CrudBaseAction {

	private ICorCustPersonalBiz corCustPersonalBiz;
	private String id;
	private CorCompound corCompound;
	private CorCondition corCondition;
	private IDataDictBiz dataDictBiz;
	
	private List<OptionObjectPair> custTypeList;
	private List<OptionObjectPair> certTypeList;
	private List<OptionObjectPair> statusList;
	
	
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CorCustPersonalManagerAction.list()...");
		this.load();
		return SUCCESS;
	}
	
	private void load() {
		custTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
//		certTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
//		statusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_STATUS);
	}

	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CorCustPersonalManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CorCustPersonalManagerAction.modify()...");
		corCompound = corCustPersonalBiz.getCorCompoundByPersonalId(id);
		return SUCCESS;
	}
	/**
	 * Action方法，跳转到详情页面
	 * 
	 * @return
	 */
	public String detail() {
		logger.info("entering action::CorCustPersonalManagerAction.detail()...");
		corCompound = corCustPersonalBiz.getCorCompoundByPersonalId(id);
		return SUCCESS;
	}
	/**
	 * Action方法，跳转到验证页面
	 * 
	 * @return
	 */
	public String verify() {
		logger.info("entering action::CorCustPersonalManagerAction.detail()...");
		corCompound = corCustPersonalBiz.getCorCompoundByPersonalId(id);
		return SUCCESS;
	}

	
	/**
	 * 将JSP页面的查询条件放入CorCondition对象中
	 * 
	 * 
	 */
	public void setQueryCondition() {
		logger.info("entering action::CorCustPersonalManagerAction.setQueryCondition()...");
		corCondition = new CorCondition();
		String custCode = getSearchFields().get("custCode");
		if (StringUtils.isNotEmpty(custCode)) {
			corCondition.setCustCode(custCode);
		}
		String localName = getSearchFields().get("localName");
		if (StringUtils.isNotEmpty(localName)) {
			corCondition.setLocalName(localName);
		}
		String status = getSearchFields().get("status");
		if (StringUtils.isNotEmpty(status)) {
			corCondition.setStatus(status);
		}
		String certType = getSearchFields().get("certType");
		if (StringUtils.isNotEmpty(certType)) {
			corCondition.setCertType(certType);
		}
		String certNum = getSearchFields().get("certNum");
		if (StringUtils.isNotEmpty(certNum)) {
			corCondition.setCertNum(certNum);
		}
		String phoneNum = getSearchFields().get("phoneNum");
		if (StringUtils.isNotEmpty(phoneNum)) {
			corCondition.setPhoneNum(phoneNum);
		}
		String realNameLeve = getSearchFields().get("realNameLeve");
		if (StringUtils.isNotEmpty(realNameLeve)) {
			corCondition.setRealNameLeve(realNameLeve);
		}
		String custLevel = getSearchFields().get("custLevel");
		if (StringUtils.isNotEmpty(custLevel)) {
			corCondition.setCustLevel(custLevel);
		}
		String createTimeStart = getSearchFields().get("createTimeStart");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			corCondition.setCreateTimeStart(createTimeStart);
		}
		String createTimeEnd = getSearchFields().get("createTimeEnd");
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			corCondition.setCreateTimeEnd(createTimeEnd);
		}
	}

	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CorCustPersonalManagerAction.search()...");

		/**
		 * 第一步，获取查询条件
		 */
		setQueryCondition();
		
		/**
		 * 第二步，执行查询,并设置结果
		 */
		
		//Page<CorCustPersonal> result = (Page<CorCustPersonal>) corCustPersonalBiz.findCorCustPersonalByPage(queryPage);
		System.out.println(corCondition);
		Page<CorCompound> result = (Page<CorCompound>) corCustPersonalBiz.findCorCorCompoundByPage(queryPage,corCondition);
		
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}

	
	/**
	 * 保存
	 * 获取JSP页面中的数据，放入对应的各个实体对象中，将对象放入list集合传给biz层
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CorCustPersonalManagerAction.saveOrUpdate()...");
		System.out.println(corCompound);
		// 如果是新增
		if (this.getIsModify() == false) {
			corCustPersonalBiz.saveCorCompound(corCompound);
		}
		// 如果是修改
		else {
			corCustPersonalBiz.updateCorCompound(corCompound);
		}

		return SUCCESS;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CorCustPersonalManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 设置导出文件名
		 */
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		corCustPersonalBiz.exportCorCustPersonal(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	

	public ICorCustPersonalBiz getCorCustPersonalBiz() {
		logger.info("entering action::CorCustPersonalManagerAction.getCorCustPersonalBiz()...");
		return corCustPersonalBiz;
	}

	public void setCorCustPersonalBiz(ICorCustPersonalBiz corCustPersonalBiz) {
		logger.info("entering action::CorCustPersonalManagerAction.setCorCustPersonalBiz()...");
		this.corCustPersonalBiz = corCustPersonalBiz;
	}

	public String getId() {
		logger.info("entering action::CorCustPersonalManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CorCustPersonalManagerAction.setId()...");
		this.id = id;
	}

	public CorCompound getCorCompound() {
		return corCompound;
	}

	public void setCorCompound(CorCompound corCompound) {
		this.corCompound = corCompound;
	}

	public CorCondition getCorCondition() {
		return corCondition;
	}

	public void setCorCondition(CorCondition corCondition) {
		this.corCondition = corCondition;
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
	
	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public List<OptionObjectPair> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<OptionObjectPair> statusList) {
		this.statusList = statusList;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
