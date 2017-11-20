/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.interfaces.action;

import com.ibs.portal.framework.util.DateUtils;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import com.ibs.core.module.account.biz.ICnlTransBiz;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.interfaces.biz.ICnlReqTransInfDataInqueryBiz;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlReqTransManagerInfDataInqueryAction extends CrudBaseAction {

	private ICnlReqTransInfDataInqueryBiz cnlReqTransBiz;

	private String id;
	private CnlReqTrans cnlReqTrans;

	private ICnlTransBiz cnlTransBiz;
	private List<OptionObjectPair> reqStatusList;
	private IDataDictBiz dataDictBiz;
	private List<OptionObjectPair> cnlCnlCodeList;
	private List<CnlCnlCfg> cnlCnlCfgList;
	
	public List<CnlCnlCfg> getCnlCnlCfgList() {
		return cnlCnlCfgList;
	}

	public void setCnlCnlCfgList(List<CnlCnlCfg> cnlCnlCfgList) {
		this.cnlCnlCfgList = cnlCnlCfgList;
	}

	public List<OptionObjectPair> getCnlCnlCodeList() {
		return cnlCnlCodeList;
	}

	public void setCnlCnlCodeList(List<OptionObjectPair> cnlCnlCodeList) {
		this.cnlCnlCodeList = cnlCnlCodeList;
	}

	public ICnlTransBiz getCnlTransBiz() {
		return cnlTransBiz;
	}

	public void setCnlTransBiz(ICnlTransBiz cnlTransBiz) {
		this.cnlTransBiz = cnlTransBiz;
	}

	public List<OptionObjectPair> getReqStatusList() {
		return reqStatusList;
	}

	public void setReqStatusList(List<OptionObjectPair> reqStatusList) {
		this.reqStatusList = reqStatusList;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlReqTransManagerAction.list()...");
		String reqType=request.getParameter("reqType");
		session.put("reqType", reqType);
		logger.debug("reqType:"+reqType);
		reqStatusList=dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__REQ_STATUS);
		CnlCnlCfg c = new CnlCnlCfg();
		c.setIsValid(Constants.CNL_STATUS_VALID);
		c.setIsValid(Constants.IS_VALID_VALID);
		setCnlCnlCfgList(cnlTransBiz.getCnlCnlCodeVal(c));
		cnlCnlCodeList = toOptionPairList(cnlCnlCfgList);
		logger.info("exit action::CnlReqTransManagerAction.list()...");
		return SUCCESS;
	}
	private List<OptionObjectPair> toOptionPairList(List<CnlCnlCfg> list) {
		logger.debug("enter toOptionPairList()");
		List<OptionObjectPair> optionPairList = new ArrayList<OptionObjectPair>();
		if(null != list) {
			for(CnlCnlCfg o : list) {
				optionPairList.add(new OptionObjectPair(o.getCnlCnlCode(),o.getCnlSysName()));
			}
		}
		return optionPairList;
	}
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlReqTransManagerAction.create()...");
		return SUCCESS;
	}

	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlReqTransManagerAction.modify()...");
		cnlReqTrans = cnlReqTransBiz.getCnlReqTransById(id);
		return SUCCESS;
	}

	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlReqTransManagerAction.search()...");
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlReqTrans> result = (Page<CnlReqTrans>) cnlReqTransBiz.findCnlReqTransByPage(queryPage);
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
		logger.info("entering action::CnlReqTransManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlReqTransBiz.saveCnlReqTrans(cnlReqTrans);
		}
		// 如果是修改
		else {
			cnlReqTransBiz.updateCnlReqTrans(cnlReqTrans);
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
		logger.info("entering action::CnlReqTransManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlReqTransManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlReqTransBiz.exportCnlReqTrans(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlReqTransManagerAction.setQueryCondition()...");
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

		// timeType
		String timeType = getSearchFields().get("timeType");
		// startTime
		String startTime = getSearchFields().get("startTime");
		// endTime
		String endTime = getSearchFields().get("endTime");

		if ((StringUtils.isNotEmpty(startTime) && (StringUtils.isNotEmpty(endTime)))) {
			String searchTime = "";
			if (timeType.equals("1")) {
				searchTime = "recieveTime";
			}
			;
			if (timeType.equals("2")) {
				searchTime = "handleTime";
			}
			;
			if (timeType.equals("3")) {
				searchTime = "msgTime";
			}
			;

			if (null != queryPage) {
			
				
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				try {
					Date sTime = sim.parse(startTime);
					Date eTime = sim.parse(endTime);
					queryPage.addBetweenSearch(searchTime, sTime, eTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		// REQ_INNER_NUM
		String reqInnerNum = getSearchFields().get("reqInnerNum");
		if (StringUtils.isNotEmpty(reqInnerNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqInnerNum", "%" + reqInnerNum + "%");
				queryPage.addLikeSearch("reqInnerNum", reqInnerNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqInnerNum", "%" + reqInnerNum + "%");
				exportSetting.addLikeSearch("reqInnerNum", reqInnerNum);
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

		// REQ_NUM
		String reqNum = getSearchFields().get("reqNum");
		if (StringUtils.isNotEmpty(reqNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqNum", "%" + reqNum + "%");
				queryPage.addLikeSearch("reqNum", reqNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqNum", "%" + reqNum + "%");
				exportSetting.addLikeSearch("reqNum", reqNum);
			}
		}

		// REQ_BATCH
		String reqBatch = getSearchFields().get("reqBatch");
		if (null != reqBatch) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqBatch", reqBatch);
				queryPage.addEqualSearch("reqBatch", reqBatch);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("reqBatch", reqBatch);
				queryPage.addEqualSearch("reqBatch", reqBatch);
			}
		}
		
		
		// REQ_TYPE
		String reqType=(String)session.get("reqType");
		if (null==reqType) {
				reqType="1";
				session.put("reqType", reqType);
		}
		if (null != queryPage) {
			System.out.println("&&&&&&&&&&&&"+reqType);
			queryPage.addQueryCondition("reqType", reqType);
			queryPage.addLikeSearch("reqType", reqType);
		}

		// REQ_STATUS
		String reqStatus = getSearchFields().get("reqStatus");
		
		if (StringUtils.isNotEmpty(reqStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqStatus", "%" + reqStatus + "%");
				queryPage.addLikeSearch("reqStatus", reqStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqStatus", "%" + reqStatus + "%");
				exportSetting.addLikeSearch("reqStatus", reqStatus);
			}
		}

		// TIME_ZONE
		String timeZone = getSearchFields().get("timeZone");
		if (StringUtils.isNotEmpty(timeZone)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("timeZone", "%" + timeZone + "%");
				queryPage.addLikeSearch("timeZone", timeZone);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("timeZone", "%" + timeZone + "%");
				exportSetting.addLikeSearch("timeZone", timeZone);
			}
		}

		// MSG_TIME
		String msgTime = getSearchFields().get("msgTime");
		if (StringUtils.isNotEmpty(msgTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgTime", DateUtils.convert(msgTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgTime", DateUtils.convert(msgTime, DateUtils.DATE_FORMAT));
			}
		}

		// RECIEVE_TIME
		String recieveTime = getSearchFields().get("recieveTime");
		if (StringUtils.isNotEmpty(recieveTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("recieveTime", DateUtils.convert(recieveTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("recieveTime", DateUtils.convert(recieveTime, DateUtils.DATE_FORMAT));
			}
		}

		// HANDLE_TIME
		String handleTime = getSearchFields().get("handleTime");
		if (StringUtils.isNotEmpty(handleTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("handleTime", DateUtils.convert(handleTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("handleTime", DateUtils.convert(handleTime, DateUtils.DATE_FORMAT));
			}
		}

		// MSG_CODE
		String msgCode = getSearchFields().get("msgCode");
		if (StringUtils.isNotEmpty(msgCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgCode", "%" + msgCode + "%");
				queryPage.addLikeSearch("msgCode", msgCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgCode", "%" + msgCode + "%");
				exportSetting.addLikeSearch("msgCode", msgCode);
			}
		}

		// ERR_CODE
		String errCode = getSearchFields().get("errCode");
		if (StringUtils.isNotEmpty(errCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errCode", "%" + errCode + "%");
				queryPage.addLikeSearch("errCode", errCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errCode", "%" + errCode + "%");
				exportSetting.addLikeSearch("errCode", errCode);
			}
		}

		// ERR_MSG
		String errMsg = getSearchFields().get("errMsg");
		if (StringUtils.isNotEmpty(errMsg)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errMsg", "%" + errMsg + "%");
				queryPage.addLikeSearch("errMsg", errMsg);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errMsg", "%" + errMsg + "%");
				exportSetting.addLikeSearch("errMsg", errMsg);
			}
		}

		// IS_VALID
		String isValid = Constants.IS_VALID_VALID;
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
				queryPage.addQueryCondition("createTime", DateUtils.convert(createTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("createTime", DateUtils.convert(createTime, DateUtils.DATE_FORMAT));
			}
		}

		// UPDATE_TIME
		String updateTime = getSearchFields().get("updateTime");
		if (StringUtils.isNotEmpty(updateTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updateTime", DateUtils.convert(updateTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updateTime", DateUtils.convert(updateTime, DateUtils.DATE_FORMAT));
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

	public ICnlReqTransInfDataInqueryBiz getCnlReqTransBiz() {
		logger.info("entering action::CnlReqTransManagerAction.getCnlReqTransBiz()...");
		return cnlReqTransBiz;
	}

	public void setCnlReqTransBiz(ICnlReqTransInfDataInqueryBiz cnlReqTransBiz) {
		logger.info("entering action::CnlReqTransManagerAction.setCnlReqTransBiz()...");
		this.cnlReqTransBiz = cnlReqTransBiz;
	}

	public CnlReqTrans getCnlReqTrans() {
		logger.info("entering action::CnlReqTransManagerAction.getCnlReqTrans()...");
		return cnlReqTrans;
	}

	public void setCnlReqTrans(CnlReqTrans cnlReqTrans) {
		logger.info("entering action::CnlReqTransManagerAction.setCnlReqTrans()...");
		this.cnlReqTrans = cnlReqTrans;
	}

	public String getId() {
		logger.info("entering action::CnlReqTransManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlReqTransManagerAction.setId()...");
		this.id = id;
	}

}
