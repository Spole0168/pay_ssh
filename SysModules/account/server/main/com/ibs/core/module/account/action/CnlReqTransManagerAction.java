/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ibs.core.module.account.biz.ICnlReqTransBiz;
import com.ibs.core.module.account.biz.ICnlTrans3mBiz;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.account.utils.CnlReqTransDtoUtil;
import com.ibs.core.module.account.utils.DtoUtils;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlReqTransManagerAction extends CrudBaseAction {
	private ICnlReqTransBiz cnlReqTransBiz;
	private ICnlTrans3mBiz cnlTrans3mBiz;
	private String id;
	private CnlReqTrans cnlReqTrans;
	private CnlReqTransDto cnlReqTransDto;
	private IDataDictService dataDictService;
	private IDataDictBiz dataDictBiz;
	private List<OptionObjectPair> reqTypes;
	private List<OptionObjectPair> reqStatuss;
	private List<OptionObjectPair> cnlCnlCodes;
	private String reqInnerNum;
	/**
	 * 清结算的明细查询
	 */
	public String query() {
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlReqTransDto> result = (Page<CnlReqTransDto>) cnlReqTransBiz
				.findBalance(queryPage,reqInnerNum);
		Collection<CnlReqTransDto> rows2 = result.getRows();
		//将部分编码转为实际的内容
		DtoUtils.changeToContent(rows2, dataDictService);
		setResult(result);
		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlReqTransManagerAction.list()...");
		reqTypes = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__REQ_TYPE);
		reqStatuss = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__REQ_STATUS);
		cnlCnlCodes= cnlTrans3mBiz.getCnlSourceList();
		return SUCCESS;
	}
	
	/**
	 * 跳转明细列表
	 */
	public String detail(){
		logger.info("entering action::CnlReqTransManagerAction.detail()...");
		//得到参数
		String reqType = request.getParameter("reqType").trim();
		reqInnerNum = request.getParameter("reqInnerNum");
		if(Constants.REQ_TYPE_CLEARING_AND_SETTLEMENT_VALUE.equals(reqType)){
			return "balance";
		}
		//调用方法的到明细结果()
		Map<String,Object> result=cnlReqTransBiz.findDetail(reqInnerNum,reqType);
		//判断查询结果是否为空
		if(result!=null){
			//得到dto对象
			cnlReqTransDto=(CnlReqTransDto) result.get("cnlReqTransDto");
			//转换code值为实际意义的值
			if(cnlReqTransDto!=null){
				//封装dto
				Collection<CnlReqTransDto> c =new ArrayList<CnlReqTransDto>();
				c.add(cnlReqTransDto);
				CnlReqTransDtoUtil.changeToContent(c, dataDictService);
			}else{
				return NONE;
			}
			//得到结果集
			String resultString=(String) result.get("resultString");
			//返回结果集
			return resultString;
		}
		return NONE;
	}

	private Object condition() {
		// TODO Auto-generated method stub
		return null;
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
//		Page<CnlReqTrans> result = (Page<CnlReqTrans>) cnlReqTransBiz
//				.findCnlReqTransByPage(queryPage);
		String conditon=getHqlCondtionToString();
		Page<CnlReqTransDto> result = (Page<CnlReqTransDto>) cnlReqTransBiz
				.findReqTranBizByMoreTable(queryPage,conditon);
		Collection<CnlReqTransDto> rows2 = result.getRows();
		//将部分编码转为实际的内容
		DtoUtils.changeToContent(rows2, dataDictService);
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
		if(getSearchFields().get("reqBatch")!=null){
			Long reqBatch = Long.parseLong(getSearchFields().get("reqBatch"));
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
		}


		// REQ_TYPE
		String reqType = getSearchFields().get("reqType");
		if (StringUtils.isNotEmpty(reqType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqType", "%" + reqType + "%");
				queryPage.addLikeSearch("reqType", reqType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqType", "%" + reqType + "%");
				exportSetting.addLikeSearch("reqType", reqType);
			}
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
				queryPage.addQueryCondition("msgTime",
						DateUtils.convert(msgTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgTime",
						DateUtils.convert(msgTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// RECIEVE_TIME
		String recieveTime = getSearchFields().get("recieveTime");
		if (StringUtils.isNotEmpty(recieveTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("recieveTime",
						DateUtils.convert(recieveTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("recieveTime",
						DateUtils.convert(recieveTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// HANDLE_TIME
		String handleTime = getSearchFields().get("handleTime");
		if (StringUtils.isNotEmpty(handleTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("handleTime",
						DateUtils.convert(handleTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("handleTime",
						DateUtils.convert(handleTime,
								DateUtils.DATE_FORMAT));
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

	public ICnlReqTransBiz getCnlReqTransBiz() {
		logger.info("entering action::CnlReqTransManagerAction.getCnlReqTransBiz()...");
		return cnlReqTransBiz;
	}

	public void setCnlReqTransBiz(ICnlReqTransBiz cnlReqTransBiz) {
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
	/**
	 * 封装条件生产hql的选择条件语句
	 * 
	 */
	public String getHqlCondtionToString(){
		StringBuffer condition = new StringBuffer("");
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		//reqInnerNum
		String reqInnerNum = getSearchFields().get("reqInnerNum");
		if (StringUtils.isNotEmpty(reqInnerNum)) {
			condition.append(" and r.req_Inner_Num = '"+reqInnerNum.trim()+"'");
		}
		//cnlCnlCode
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			condition.append(" and r.cnl_Cnl_Code = '"+cnlCnlCode.trim()+"'");
		}
		//reqType
		String reqType = getSearchFields().get("reqType");
		if (StringUtils.isNotEmpty(reqType)) {
			condition.append(" and r.req_Type = '"+ reqType+"'");
		}
		//reqStatus
		String reqStatus = getSearchFields().get("reqStatus");
		if (StringUtils.isNotEmpty(reqStatus)) {
			condition.append(" and r.req_Status = '"+reqStatus+"'");
		}
		//time
		//timeType
		String timeType = getSearchFields().get("timeType");
		//startTime
		String startTime = getSearchFields().get("startTime");
		//endTime
		String endTime = getSearchFields().get("endTime");
		if (StringUtils.isNotEmpty(timeType)) {
			timeType=timeType.trim();
			if(timeType.equals("1")){
				timeType="msg_Time ";
			}else if(timeType.equals("2")){
				timeType="recieve_Time ";
			}else{
				timeType="handle_Time ";
			}
			condition.append(" and r."+timeType+" >= ? and r."+timeType+" <= ? ");
			queryPage.addQueryCondition("start_Time", DateUtils.convert(startTime, DateUtils.DATE_TIME_FORMAT));
			queryPage.addQueryCondition("end_Time",DateUtils.convert(endTime, DateUtils.DATE_TIME_FORMAT));
		}
		return condition.toString();
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public List<OptionObjectPair> getReqTypes() {
		return reqTypes;
	}

	public void setReqTypes(List<OptionObjectPair> reqTypes) {
		this.reqTypes = reqTypes;
	}

	public List<OptionObjectPair> getReqStatuss() {
		return reqStatuss;
	}

	public void setReqStatuss(List<OptionObjectPair> reqStatuss) {
		this.reqStatuss = reqStatuss;
	}

	public CnlReqTransDto getCnlReqTransDto() {
		return cnlReqTransDto;
	}

	public void setCnlReqTransDto(CnlReqTransDto cnlReqTransDto) {
		this.cnlReqTransDto = cnlReqTransDto;
	}

	public ICnlTrans3mBiz getCnlTrans3mBiz() {
		return cnlTrans3mBiz;
	}

	public void setCnlTrans3mBiz(ICnlTrans3mBiz cnlTrans3mBiz) {
		this.cnlTrans3mBiz = cnlTrans3mBiz;
	}

	public List<OptionObjectPair> getCnlCnlCodes() {
		return cnlCnlCodes;
	}

	public void setCnlCnlCodes(List<OptionObjectPair> cnlCnlCodes) {
		this.cnlCnlCodes = cnlCnlCodes;
	}
	public String getReqInnerNum() {
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
	}
	
}
