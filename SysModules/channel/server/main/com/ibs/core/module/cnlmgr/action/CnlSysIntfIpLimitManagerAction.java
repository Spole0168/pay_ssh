/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.action;

import java.io.IOException;

import com.ibs.core.module.cnlmgr.biz.ICnlSysIntfIpLimitBiz;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitConditionDto;
import com.ibs.core.module.cnlmgr.dto.CnlSysIntfIpLimitDto;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlSysIntfIpLimitManagerAction extends CrudBaseAction {


	private String id;
	
	private ICnlSysIntfIpLimitBiz cnlSysIntfIpLimitBiz;
	private CnlSysIntfIpLimitDto cnlSysIntfIpLimitDto;
	private CnlSysIntfIpLimitConditionDto cnlSysIntfIpLimitConditionDto; 
	
	private String checkResult;
	private String deleteResult;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.modify()...");
		cnlSysIntfIpLimitDto = cnlSysIntfIpLimitBiz.getCnlSysIntfById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlSysIntfIpLimitDto> result = (Page<CnlSysIntfIpLimitDto>) cnlSysIntfIpLimitBiz
				.findCnlSysIntfByPage(queryPage,cnlSysIntfIpLimitConditionDto);
		//System.out.println(cnlSysIntfIpLimitConditionDto);
		System.out.println(result.getRows().toString());
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
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.saveOrUpdate()...");
		System.out.println(cnlSysIntfIpLimitDto);
		cnlSysIntfIpLimitBiz.updateCnlSysIntf(cnlSysIntfIpLimitDto);
		return SUCCESS;
	}
	
	/**
	 * Action方法，删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.delete()...");
		boolean res = cnlSysIntfIpLimitBiz.deleteIpLimit(id);
		if(res){
			deleteResult = "01";
		}else{
			deleteResult = "02";  
		}
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * check
	 * @return
	 * @throws IOException
	 */
	public String check() throws IOException{
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.check()...");
		String cnlCustCode = request.getParameter("cnlCustCode");
		String cnlIntfCode = request.getParameter("cnlIntfCode");
		boolean res1 = cnlSysIntfIpLimitBiz.checkCnlSysIntf(cnlCustCode,cnlIntfCode);
		boolean res2 = cnlSysIntfIpLimitBiz.checkIpLimit(cnlCustCode,cnlIntfCode);
		if(res1 == false){
			checkResult = "01";
		}else if(res1 == true && res2 == true){
			checkResult = "02";
		}else{
			checkResult = "00";
		}
			
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlSysIntfIpLimitBiz.exportCnlSysIntf(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	/**
	 * 将查询条件放入DTO类中
	 * @return
	 */
	public CnlSysIntfIpLimitConditionDto setQueryCondition() {
		logger.info("entering action::CnlSysIntfIpLimitManagerAction.setQueryCondition()...");
		cnlSysIntfIpLimitConditionDto = new CnlSysIntfIpLimitConditionDto();
		cnlSysIntfIpLimitConditionDto.setCnlCustCode(getSearchFields().get("cnlCustCode"));
		cnlSysIntfIpLimitConditionDto.setCnlIntfCode(getSearchFields().get("cnlIntfCode"));
		cnlSysIntfIpLimitConditionDto.setIpRangeFrom(getSearchFields().get("ipRangeFrom"));
		cnlSysIntfIpLimitConditionDto.setIpRangeTo(getSearchFields().get("ipRangeTo"));
		System.out.println(cnlSysIntfIpLimitConditionDto);
		return cnlSysIntfIpLimitConditionDto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ICnlSysIntfIpLimitBiz getCnlSysIntfIpLimitBiz() {
		return cnlSysIntfIpLimitBiz;
	}

	public void setCnlSysIntfIpLimitBiz(ICnlSysIntfIpLimitBiz cnlSysIntfIpLimitBiz) {
		this.cnlSysIntfIpLimitBiz = cnlSysIntfIpLimitBiz;
	}

	public CnlSysIntfIpLimitDto getCnlSysIntfIpLimitDto() {
		return cnlSysIntfIpLimitDto;
	}

	public void setCnlSysIntfIpLimitDto(CnlSysIntfIpLimitDto cnlSysIntfIpLimitDto) {
		this.cnlSysIntfIpLimitDto = cnlSysIntfIpLimitDto;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(String deleteResult) {
		this.deleteResult = deleteResult;
	}

}
