/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.action;

import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ibs.core.module.cnlmgr.biz.ICnlCnlCfgBiz;
import com.ibs.core.module.cnlmgr.biz.ICnlSysIntfBiz;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntfCfg;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SYS_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlSysIntfManagerAction extends CrudBaseAction {

	private ICnlSysIntfBiz cnlSysIntfBiz;
	private ICnlCnlCfgBiz cnlCnlCfgBiz;
	private IDataDictBiz dataDictBiz;
	private IDataDictService dataDictService; 
	
	private String id;
	private CnlSysIntf cnlSysIntf;
	private CnlSysIntfCfg cnlSysIntfCfg;
	private List<CnlCnlCfg> cnlCnlCfgList;
	private List<OptionObjectPair> currencyList;
	private String cnlCnlCode;
	private String accessCode;
	private List<CnlSysIntfCfg> cnlSysIntfCfgList;
	private String cnlCustCode;
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlSysIntfManagerAction.list()...");
		currencyList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlSysIntfManagerAction.create()...");
		cnlSysIntfCfgList = cnlSysIntfBiz.findBycurrency();
		System.out.println();
		currencyList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlSysIntfManagerAction.modify()...");
		cnlSysIntfCfg = cnlSysIntfBiz.findSysIntfCfgbyId(id);
		currencyList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlSysIntfManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		String accessCode = getSearchFields().get("accessCode");
		String currency = getSearchFields().get("currency");
		Page<CnlSysIntfCfg> result = (Page<CnlSysIntfCfg>) cnlSysIntfBiz.findSysIntf(queryPage,cnlCustCode,accessCode, currency);
		
		List<DataDict> currencylist = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		try{
			CollectionUtils.transformCollection(result.getRows(), "currency","currency", currencylist, "code", "name");	
		}catch(Exception e){
			throw new BizException(e.getMessage());							
		}

		/**
		 * 第二步，执行查询,并设置结果
		 */
		
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
	public void saveOrUp(){
		IUser user = getCurrentUser();
		logger.info("entering action::CnlSysIntfManagerAction.saveOrUpdate()...");
		Date newDate = new Date();
		String dateTime = DateUtils.convert(newDate, DateUtils.DATE_TIME_FORMAT);
		Date time = DateUtils.convert(dateTime, DateUtils.DATE_TIME_FORMAT);
		// 如果是新增
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		CnlSysIntf cnl = null;
		try{
			if (this.getIsModify() == false) {
				cnlSysIntf = cnlSysIntfBiz.findByAccessCode(cnlSysIntfCfg.getAccessCode());
				cnlSysIntf.setCurrency(cnlSysIntfCfg.getCurrency());
				System.out.println("cnlSysIntfCfg.getCurrency()===="+cnlSysIntfCfg.getCurrency());
				cnlSysIntf.setPerTransLimit(cnlSysIntfCfg.getPerTransLimit());
				cnlSysIntf.setDayLimit(cnlSysIntfCfg.getDayLimit());
				cnlSysIntf.setWeekLimit(cnlSysIntfCfg.getWeekLimit());
				cnlSysIntf.setMonthLimit(cnlSysIntfCfg.getMonthLimit());
				cnlSysIntf.setYearLimit(cnlSysIntfCfg.getYearLimit());
				cnlSysIntf.setComments(cnlSysIntfCfg.getComments());
				cnlSysIntf.setLmtOpt(user.getUserName());
				cnlSysIntf.setLmtOptTime(time);
				cnl = cnlSysIntfBiz.updateCnlSysIntf(cnlSysIntf);
				if(cnl != null)
					response.getWriter().write("1"); 
				else
					response.getWriter().write("0"); 
			}
			// 如果是修改
			else {
				cnlSysIntf = cnlSysIntfBiz.getCnlSysIntfById(cnlSysIntfCfg.getId());
				cnlSysIntf.setCurrency(cnlSysIntfCfg.getCurrency());
				cnlSysIntf.setPerTransLimit(cnlSysIntfCfg.getPerTransLimit());
				cnlSysIntf.setDayLimit(cnlSysIntfCfg.getDayLimit());
				cnlSysIntf.setWeekLimit(cnlSysIntfCfg.getWeekLimit());
				cnlSysIntf.setMonthLimit(cnlSysIntfCfg.getMonthLimit());
				cnlSysIntf.setYearLimit(cnlSysIntfCfg.getYearLimit());
				cnlSysIntf.setComments(cnlSysIntfCfg.getComments());
				cnlSysIntf.setLmtOpt(user.getUserName());
				cnlSysIntf.setLmtOptTime(time);
				cnlSysIntfBiz.updateCnlSysIntf(cnlSysIntf);
				
				cnl = cnlSysIntfBiz.updateCnlSysIntf(cnlSysIntf);
				if(cnl != null)
					response.getWriter().write("1"); 
				else
					response.getWriter().write("0"); 
			}
		}catch(Exception e){
			
		}
	}
	
	public String saveOrUpdate() {
		logger.info("entering action::CnlSysIntfManagerAction.saveOrUpdate()...");

		// 如果是新增
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		CnlSysIntf cnl = null;
		try{
			if (this.getIsModify() == false) {
				cnlSysIntf = cnlSysIntfBiz.findByAccessCode(cnlSysIntfCfg.getAccessCode());
				cnlSysIntf.setCurrency(cnlSysIntfCfg.getCurrency());
				System.out.println("cnlSysIntfCfg.getCurrency()===="+cnlSysIntfCfg.getCurrency());
				cnlSysIntf.setPerTransLimit(cnlSysIntfCfg.getPerTransLimit());
				cnlSysIntf.setDayLimit(cnlSysIntfCfg.getDayLimit());
				cnlSysIntf.setWeekLimit(cnlSysIntfCfg.getWeekLimit());
				cnlSysIntf.setMonthLimit(cnlSysIntfCfg.getMonthLimit());
				cnlSysIntf.setYearLimit(cnlSysIntfCfg.getYearLimit());
				cnlSysIntf.setComments(cnlSysIntfCfg.getComments());
				cnlSysIntf.setLmtOpt(cnlSysIntfCfg.getLmtOpt());
				cnlSysIntf.setLmtOptTime(cnlSysIntfCfg.getLmtOptTime());
				cnl = cnlSysIntfBiz.updateCnlSysIntf(cnlSysIntf);
				if(cnl != null)
					response.getWriter().write("1"); 
				else
					response.getWriter().write("0"); 
			}
			// 如果是修改
			else {
				cnlSysIntf = cnlSysIntfBiz.getCnlSysIntfById(cnlSysIntfCfg.getId());
				cnlSysIntf.setCurrency(cnlSysIntfCfg.getCurrency());
				cnlSysIntf.setPerTransLimit(cnlSysIntfCfg.getPerTransLimit());
				cnlSysIntf.setDayLimit(cnlSysIntfCfg.getDayLimit());
				cnlSysIntf.setWeekLimit(cnlSysIntfCfg.getWeekLimit());
				cnlSysIntf.setMonthLimit(cnlSysIntfCfg.getMonthLimit());
				cnlSysIntf.setYearLimit(cnlSysIntfCfg.getYearLimit());
				cnlSysIntf.setComments(cnlSysIntfCfg.getComments());
				cnlSysIntf.setLmtOpt(cnlSysIntfCfg.getLmtOpt());
				cnlSysIntf.setLmtOptTime(cnlSysIntfCfg.getLmtOptTime());
				cnlSysIntfBiz.updateCnlSysIntf(cnlSysIntf);
				
				cnl = cnlSysIntfBiz.updateCnlSysIntf(cnlSysIntf);
				if(cnl != null)
					response.getWriter().write("1"); 
				else
					response.getWriter().write("0"); 
			}
		}catch(Exception e){
			
		}
		return SUCCESS;
	}
	
	/**
	 * 异步查询网关接入号是否存在
	 * */
	public void isExitAccessCode()throws Exception{
		CnlSysIntfCfg cnlSysIntfCfg = cnlSysIntfBiz.isExitAccessCode(accessCode,cnlCustCode);
		CnlSysIntfCfg cnl = cnlSysIntfBiz.isExitCurrency(accessCode, cnlCustCode);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		if(cnlSysIntfCfg != null){
			if(cnl == null){
				response.getWriter().write("0"); 
			}
		}else{
			response.getWriter().write("-1"); 
		}
	}
	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CnlSysIntfManagerAction.delete()...");
		cnlSysIntf = cnlSysIntfBiz.getCnlSysIntfById(id);
		cnlSysIntf.setCurrency(null);
		cnlSysIntf.setPerTransLimit(null);
		cnlSysIntf.setDayLimit(null);
		cnlSysIntf.setWeekLimit(null);
		cnlSysIntf.setMonthLimit(null);
		cnlSysIntf.setYearLimit(null);
		cnlSysIntfBiz.updateCnlSysIntf(cnlSysIntf);
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlSysIntfManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlSysIntfBiz.exportCnlSysIntf(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlSysIntfManagerAction.setQueryCondition()...");


		// ID
		String id = getSearchFields().get("id");
		if (StringUtils.isNotEmpty(id)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("id", "%" + id + "%");
				queryPage.addLikeSearch("id", id);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("id", "%" + id + "%");
				exportSetting.addLikeSearch("id", id);
			}
		}


		// CNL_CNL_CODE
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlCnlCode", "%" + cnlCnlCode + "%");
				queryPage.addLikeSearch("cnlCnlCode", cnlCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlCnlCode", "%" + cnlCnlCode + "%");
				exportSetting.addLikeSearch("cnlCnlCode", cnlCnlCode);
			}
		}


		// CNL_INTF_CODE
		String cnlIntfCode = getSearchFields().get("cnlIntfCode");
		if (StringUtils.isNotEmpty(cnlIntfCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlIntfCode", "%" + cnlIntfCode + "%");
				queryPage.addLikeSearch("cnlIntfCode", cnlIntfCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlIntfCode", "%" + cnlIntfCode + "%");
				exportSetting.addLikeSearch("cnlIntfCode", cnlIntfCode);
			}
		}


		// ACCESS_CODE
		String accessCode = getSearchFields().get("accessCode");
		if (StringUtils.isNotEmpty(accessCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("accessCode", "%" + accessCode + "%");
				queryPage.addLikeSearch("accessCode", accessCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("accessCode", "%" + accessCode + "%");
				exportSetting.addLikeSearch("accessCode", accessCode);
			}
		}


		// ACCESS_KEY
		String accessKey = getSearchFields().get("accessKey");
		if (StringUtils.isNotEmpty(accessKey)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("accessKey", "%" + accessKey + "%");
				queryPage.addLikeSearch("accessKey", accessKey);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("accessKey", "%" + accessKey + "%");
				exportSetting.addLikeSearch("accessKey", accessKey);
			}
		}


		// VERIFICATION_TYPE
		String verificationType = getSearchFields().get("verificationType");
		if (StringUtils.isNotEmpty(verificationType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("verificationType", "%" + verificationType + "%");
				queryPage.addLikeSearch("verificationType", verificationType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("verificationType", "%" + verificationType + "%");
				exportSetting.addLikeSearch("verificationType", verificationType);
			}
		}


		// ACCESS_TYPE
		String accessType = getSearchFields().get("accessType");
		if (StringUtils.isNotEmpty(accessType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("accessType", "%" + accessType + "%");
				queryPage.addLikeSearch("accessType", accessType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("accessType", "%" + accessType + "%");
				exportSetting.addLikeSearch("accessType", accessType);
			}
		}


		// SEND_EMAIL
		String sendEmail = getSearchFields().get("sendEmail");
		if (StringUtils.isNotEmpty(sendEmail)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("sendEmail", "%" + sendEmail + "%");
				queryPage.addLikeSearch("sendEmail", sendEmail);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("sendEmail", "%" + sendEmail + "%");
				exportSetting.addLikeSearch("sendEmail", sendEmail);
			}
		}


		// SEND_SMS
		String sendSms = getSearchFields().get("sendSms");
		if (StringUtils.isNotEmpty(sendSms)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("sendSms", "%" + sendSms + "%");
				queryPage.addLikeSearch("sendSms", sendSms);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("sendSms", "%" + sendSms + "%");
				exportSetting.addLikeSearch("sendSms", sendSms);
			}
		}


		// RETRUN_TYPE
		String retrunType = getSearchFields().get("retrunType");
		if (StringUtils.isNotEmpty(retrunType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("retrunType", "%" + retrunType + "%");
				queryPage.addLikeSearch("retrunType", retrunType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("retrunType", "%" + retrunType + "%");
				exportSetting.addLikeSearch("retrunType", retrunType);
			}
		}


		// LOW_LIMIT
		String lowLimit = getSearchFields().get("lowLimit");
		if (null != lowLimit) {
			if (null != queryPage) {
				queryPage.addQueryCondition("lowLimit", lowLimit);
				queryPage.addEqualSearch("lowLimit", lowLimit);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("lowLimit", lowLimit);
				queryPage.addEqualSearch("lowLimit", lowLimit);
			}
		}


		// MAX_LIMIT
		String maxLimit = getSearchFields().get("maxLimit");
		if (null != maxLimit) {
			if (null != queryPage) {
				queryPage.addQueryCondition("maxLimit", maxLimit);
				queryPage.addEqualSearch("maxLimit", maxLimit);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("maxLimit", maxLimit);
				queryPage.addEqualSearch("maxLimit", maxLimit);
			}
		}


		// MONITOR_LIMIT
		String monitorLimit = getSearchFields().get("monitorLimit");
		if (null != monitorLimit) {
			if (null != queryPage) {
				queryPage.addQueryCondition("monitorLimit", monitorLimit);
				queryPage.addEqualSearch("monitorLimit", monitorLimit);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("monitorLimit", monitorLimit);
				queryPage.addEqualSearch("monitorLimit", monitorLimit);
			}
		}


		// SUPPORT_SDK
		String supportSdk = getSearchFields().get("supportSdk");
		if (StringUtils.isNotEmpty(supportSdk)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("supportSdk", "%" + supportSdk + "%");
				queryPage.addLikeSearch("supportSdk", supportSdk);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("supportSdk", "%" + supportSdk + "%");
				exportSetting.addLikeSearch("supportSdk", supportSdk);
			}
		}


		// SHOW_LOGO
		String showLogo = getSearchFields().get("showLogo");
		if (StringUtils.isNotEmpty(showLogo)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("showLogo", "%" + showLogo + "%");
				queryPage.addLikeSearch("showLogo", showLogo);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("showLogo", "%" + showLogo + "%");
				exportSetting.addLikeSearch("showLogo", showLogo);
			}
		}


		// COMMENTS
		String comments = getSearchFields().get("comments");
		if (StringUtils.isNotEmpty(comments)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("comments", "%" + comments + "%");
				queryPage.addLikeSearch("comments", comments);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("comments", "%" + comments + "%");
				exportSetting.addLikeSearch("comments", comments);
			}
		}


		// DIRECTION
		String direction = getSearchFields().get("direction");
		if (StringUtils.isNotEmpty(direction)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("direction", "%" + direction + "%");
				queryPage.addLikeSearch("direction", direction);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("direction", "%" + direction + "%");
				exportSetting.addLikeSearch("direction", direction);
			}
		}


		// SERVICE_URL
		String serviceUrl = getSearchFields().get("serviceUrl");
		if (StringUtils.isNotEmpty(serviceUrl)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("serviceUrl", "%" + serviceUrl + "%");
				queryPage.addLikeSearch("serviceUrl", serviceUrl);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("serviceUrl", "%" + serviceUrl + "%");
				exportSetting.addLikeSearch("serviceUrl", serviceUrl);
			}
		}


		// SERVICE_URL_FORMAT
		String serviceUrlFormat = getSearchFields().get("serviceUrlFormat");
		if (StringUtils.isNotEmpty(serviceUrlFormat)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("serviceUrlFormat", "%" + serviceUrlFormat + "%");
				queryPage.addLikeSearch("serviceUrlFormat", serviceUrlFormat);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("serviceUrlFormat", "%" + serviceUrlFormat + "%");
				exportSetting.addLikeSearch("serviceUrlFormat", serviceUrlFormat);
			}
		}


		// TECH_SUPPORT_NAME
		String techSupportName = getSearchFields().get("techSupportName");
		if (StringUtils.isNotEmpty(techSupportName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("techSupportName", "%" + techSupportName + "%");
				queryPage.addLikeSearch("techSupportName", techSupportName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("techSupportName", "%" + techSupportName + "%");
				exportSetting.addLikeSearch("techSupportName", techSupportName);
			}
		}


		// TECH_SUPPORT_PHONENUM
		String techSupportPhonenum = getSearchFields().get("techSupportPhonenum");
		if (StringUtils.isNotEmpty(techSupportPhonenum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("techSupportPhonenum", "%" + techSupportPhonenum + "%");
				queryPage.addLikeSearch("techSupportPhonenum", techSupportPhonenum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("techSupportPhonenum", "%" + techSupportPhonenum + "%");
				exportSetting.addLikeSearch("techSupportPhonenum", techSupportPhonenum);
			}
		}


		// TECH_SUPPORT_EMAIL
		String techSupportEmail = getSearchFields().get("techSupportEmail");
		if (StringUtils.isNotEmpty(techSupportEmail)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("techSupportEmail", "%" + techSupportEmail + "%");
				queryPage.addLikeSearch("techSupportEmail", techSupportEmail);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("techSupportEmail", "%" + techSupportEmail + "%");
				exportSetting.addLikeSearch("techSupportEmail", techSupportEmail);
			}
		}


		// BUSINESS_SUPPORT_NAME
		String businessSupportName = getSearchFields().get("businessSupportName");
		if (StringUtils.isNotEmpty(businessSupportName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("businessSupportName", "%" + businessSupportName + "%");
				queryPage.addLikeSearch("businessSupportName", businessSupportName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("businessSupportName", "%" + businessSupportName + "%");
				exportSetting.addLikeSearch("businessSupportName", businessSupportName);
			}
		}


		// BUSINESS_SUPPORT_PHONENUM
		String businessSupportPhonenum = getSearchFields().get("businessSupportPhonenum");
		if (StringUtils.isNotEmpty(businessSupportPhonenum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("businessSupportPhonenum", "%" + businessSupportPhonenum + "%");
				queryPage.addLikeSearch("businessSupportPhonenum", businessSupportPhonenum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("businessSupportPhonenum", "%" + businessSupportPhonenum + "%");
				exportSetting.addLikeSearch("businessSupportPhonenum", businessSupportPhonenum);
			}
		}


		// BUSINESS_SUPPORT_EMAIL
		String businessSupportEmail = getSearchFields().get("businessSupportEmail");
		if (StringUtils.isNotEmpty(businessSupportEmail)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("businessSupportEmail", "%" + businessSupportEmail + "%");
				queryPage.addLikeSearch("businessSupportEmail", businessSupportEmail);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("businessSupportEmail", "%" + businessSupportEmail + "%");
				exportSetting.addLikeSearch("businessSupportEmail", businessSupportEmail);
			}
		}


		// IP_RANGE_FROM
		String ipRangeFrom = getSearchFields().get("ipRangeFrom");
		if (StringUtils.isNotEmpty(ipRangeFrom)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("ipRangeFrom", "%" + ipRangeFrom + "%");
				queryPage.addLikeSearch("ipRangeFrom", ipRangeFrom);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("ipRangeFrom", "%" + ipRangeFrom + "%");
				exportSetting.addLikeSearch("ipRangeFrom", ipRangeFrom);
			}
		}


		// IP_RANGE_TO
		String ipRangeTo = getSearchFields().get("ipRangeTo");
		if (StringUtils.isNotEmpty(ipRangeTo)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("ipRangeTo", "%" + ipRangeTo + "%");
				queryPage.addLikeSearch("ipRangeTo", ipRangeTo);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("ipRangeTo", "%" + ipRangeTo + "%");
				exportSetting.addLikeSearch("ipRangeTo", ipRangeTo);
			}
		}


		// REVIEWER
		String reviewer = getSearchFields().get("reviewer");
		if (StringUtils.isNotEmpty(reviewer)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewer", "%" + reviewer + "%");
				queryPage.addLikeSearch("reviewer", reviewer);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewer", "%" + reviewer + "%");
				exportSetting.addLikeSearch("reviewer", reviewer);
			}
		}


		// REVIEW_TIME
		String reviewTime = getSearchFields().get("reviewTime");
		if (StringUtils.isNotEmpty(reviewTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reviewTime",
						DateUtils.convert(reviewTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reviewTime",
						DateUtils.convert(reviewTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// IS_VALID
		String isValid = getSearchFields().get("isValid");
		if (StringUtils.isNotEmpty(isValid)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isValid", "%" + isValid + "%");
				queryPage.addLikeSearch("isValid", isValid);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isValid", "%" + isValid + "%");
				exportSetting.addLikeSearch("isValid", isValid);
			}
		}


		// CREATE_TIME
		String createTime = getSearchFields().get("createTime");
		if (StringUtils.isNotEmpty(createTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// UPDATE_TIME
		String updateTime = getSearchFields().get("updateTime");
		if (StringUtils.isNotEmpty(updateTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// CREATOR
		String creator = getSearchFields().get("creator");
		if (StringUtils.isNotEmpty(creator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("creator", "%" + creator + "%");
				queryPage.addLikeSearch("creator", creator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("creator", "%" + creator + "%");
				exportSetting.addLikeSearch("creator", creator);
			}
		}


		// UPDATOR
		String updator = getSearchFields().get("updator");
		if (StringUtils.isNotEmpty(updator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updator", "%" + updator + "%");
				queryPage.addLikeSearch("updator", updator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updator", "%" + updator + "%");
				exportSetting.addLikeSearch("updator", updator);
			}
		}

	}

	public ICnlSysIntfBiz getCnlSysIntfBiz() {
		logger.info("entering action::CnlSysIntfManagerAction.getCnlSysIntfBiz()...");
		return cnlSysIntfBiz;
	}

	public void setCnlSysIntfBiz(ICnlSysIntfBiz cnlSysIntfBiz) {
		logger.info("entering action::CnlSysIntfManagerAction.setCnlSysIntfBiz()...");
		this.cnlSysIntfBiz = cnlSysIntfBiz;
	}

	public CnlSysIntf getCnlSysIntf() {
		logger.info("entering action::CnlSysIntfManagerAction.getCnlSysIntf()...");
		return cnlSysIntf;
	}

	public void setCnlSysIntf(CnlSysIntf cnlSysIntf) {
		logger.info("entering action::CnlSysIntfManagerAction.setCnlSysIntf()...");
		this.cnlSysIntf = cnlSysIntf;
	}

	public String getId() {
		logger.info("entering action::CnlSysIntfManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlSysIntfManagerAction.setId()...");
		this.id = id;
	}

	public CnlSysIntfCfg getCnlSysIntfCfg() {
		return cnlSysIntfCfg;
	}

	public void setCnlSysIntfCfg(CnlSysIntfCfg cnlSysIntfCfg) {
		this.cnlSysIntfCfg = cnlSysIntfCfg;
	}

	public List<CnlCnlCfg> getCnlCnlCfgList() {
		return cnlCnlCfgList;
	}

	public void setCnlCnlCfgList(List<CnlCnlCfg> cnlCnlCfgList) {
		this.cnlCnlCfgList = cnlCnlCfgList;
	}

	public ICnlCnlCfgBiz getCnlCnlCfgBiz() {
		return cnlCnlCfgBiz;
	}

	public void setCnlCnlCfgBiz(ICnlCnlCfgBiz cnlCnlCfgBiz) {
		this.cnlCnlCfgBiz = cnlCnlCfgBiz;
	}

	public List<OptionObjectPair> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<OptionObjectPair> currencyList) {
		this.currencyList = currencyList;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public String getCnlCnlCode() {
		return cnlCnlCode;
	}

	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public List<CnlSysIntfCfg> getCnlSysIntfCfgList() {
		return cnlSysIntfCfgList;
	}

	public void setCnlSysIntfCfgList(List<CnlSysIntfCfg> cnlSysIntfCfgList) {
		this.cnlSysIntfCfgList = cnlSysIntfCfgList;
	}

	public String getCnlCustCode() {
		return cnlCustCode;
	}

	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
}
