/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.topup.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.SelectRenderUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ibs.common.module.frameworkimpl.file.exception.FileServiceException;
import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.core.module.account.biz.ICnlTrans3mBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.mdmbasedata.service.impl.DataDictServiceImpl;
import com.ibs.core.module.topup.biz.ITopupTransTraceBiz;
import com.ibs.core.module.topup.domain.TopupTransTrace;
import com.ibs.core.module.topup.dto.TopupTransTraceDto;
import com.ibs.core.module.topup.utils.DtoUtils;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class TopupTransTraceManagerAction extends CrudBaseAction {

	private ITopupTransTraceBiz topupTransTraceBiz;
	private ICnlTrans3mBiz cnlTrans3mBiz;
	
	private String id;
	private TopupTransTrace topupTransTrace;
	private IDataDictService dataDictService;
	private String ids;

	private String reqNum;
	private String reqInnerNum;
	private String startTime;
	private String endTime;			// 检索
	private String cnlCnlCode;		// 渠道编码
	private String cnlSysName;		// 渠道名称
	private String cnlCustCode;		// 客户编号
	private String localName;		// 客户名称
	private String handleStatus;	// 处理状态
	
	// 审核画面交易状态名
	private String transStatusName;// 交易状态名称
	
	// 审核用到账时间
	private String bankReturnTime;
	
	// 画面检索条件
	private TopupTransTraceDto topupTransTraceConditionDto;

	// 一览检索页面：入账处理状态
	private List<OptionObjectPair> handleStatusList;
	private String handleStatusRender;
	// 审核页面：处理状态
	private List<OptionObjectPair> handleStatus1stList;
	private String handleStatus1stRender;
	// 复核页面：处理状态
	private List<OptionObjectPair> handleStatus2ndList;
	private String handleStatus2ndRender;
	// 交易币种
	private List<OptionObjectPair> transCurrencyList;
	private String transCurrencyRender;
	// 交易方向名值对
	private List<OptionObjectPair> transDcList;
	private String transDcRender;
	// 交易类型名值对
	private List<OptionObjectPair> transTypeList;
	private String transTypeRender;
	// 交易状态名值对
	private List<OptionObjectPair> transStatusList;
	private String transStatusRender;
	// 支付通道
	private List<OptionObjectPair> bankPmtCnlTypeList;
	private String bankPmtCnlTypeRender;
	// 交易终端类型
	private List<OptionObjectPair> termialTypeList;
	private String termialTypeRender;
	// 失败原因
	private List<OptionObjectPair> bankReturnResultList;
	//渠道名称
	private List<OptionObjectPair> cnlCnlCodesList;
	
	private String bankReturnResultRender;
	// 处理结果
	private List<OptionObjectPair> handleResultList;
	private String handleResultRender;
	// 上传文件域对象 
	private File file;  
	// 上传文件名  
	private String fileFileName;  
	// 上传文件类型  
	private String fileContentType;
	private IFilePersistenceService filePersistenceService;

	// 页面初期化设置
	protected void initialPage() {		
		
		logger.info("entering action::TopupTransTraceManagerAction.initialTypeList()...");
		
		// 取得数据字典
		getRenders();
		
	}

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::TopupTransTraceManagerAction.list()...");

		// 预处理
		// 对待处理数据的付款行和收款行进行预处理
		//   该处理与画面输入条件无关
		//   条件1）交易状态为待处理
		//   条件2）付款行和收款行全部为空
		//   条件3）IS_VALID为有效
		topupTransTraceBiz.updateAllTopupTransTraceBankInfo();

		// 页面初始化
		initialPage();
		
		// 入账处理状态下拉框的值
		handleStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__RECORD_HANDLE_STATUS);
		handleStatusRender = SelectRenderUtils.toRenderString(handleStatusList);


		// 开始时间和结束时间文本框的默认值
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = format.format(now) + " " + "00:00:00";
		String endTime = format.format(now) + " " + "23:59:59";
		this.setStartTime(startTime);
		this.setEndTime(endTime);

		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::TopupTransTraceManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::TopupTransTraceManagerAction.modify()...");
		topupTransTrace = topupTransTraceBiz.getTopupTransTraceById(id);
		return SUCCESS;
	}

	/**
	 * Action方法，批量审查处理 
	 *  
	 * @return
	 */
	public String checks() {
		logger.info("entering action::TopupTransTraceManagerAction.checks()...");

		// 选中的ID
		String[] idList = getIds().split(",");
		IUser iUser = this.getCurrentUser();
		ArrayList<TopupTransTrace> topupTransTracelist = new ArrayList<TopupTransTrace>();

		Date dt = new Date();
		for (int i = 0; i < idList.length; i++) {
			TopupTransTrace topupTransTrace = topupTransTraceBiz.getTopupTransTraceById(idList[i]);
			if(topupTransTrace != null){
				// 处理状态：设定为“审核失败”
				topupTransTrace.setHandleStatus(Constants.RECORD_HANDLE_STATUS_1ST_CHECK_FAIL);
				// 失败原因：设定为“未到账”
				topupTransTrace.setHandleResult(Constants.FAIL_REASON_01);
				// 交易状态：设定为“交易失败”
				topupTransTrace.setTransStatus(Constants.TRANS_STATUS_FAIL);
				// 审核人
				topupTransTrace.setOperator(iUser.getUserName());
				// 审核时间
				topupTransTrace.setHandleTime(dt);
				// 数据更新时间
				topupTransTrace.setUpdateTime(dt);
				topupTransTracelist.add(topupTransTrace);
			}
		}
		topupTransTraceBiz.checks(topupTransTracelist);
//		topupTransTraceBiz.updateTopupTransTrace(topupTransTrace);

//		// 发送报文
//		topupTransTraceBiz.sendMessage(topupTransTracelist);

		return SUCCESS;
	}

	/**
	 * Action方法，跳转到详情页面
	 * 
	 * @return
	 */
	public String detail() {
		logger.info("entering action::TopupTransTraceManagerAction.detail()...");
		
		// 取得数据字典
		getRenders();
		
		topupTransTrace = topupTransTraceBiz.getTopupTransTraceById(id);

		if (topupTransTrace != null) {
			// 检索渠道名称
			this.setCnlSysName(topupTransTraceBiz.getCnlSysNameByCnlCnlCode(topupTransTrace.getCnlCnlCode()));
		}

		return SUCCESS;
	}
	
	/**
	 * Action方法，取得数据字典
	 * 
	 * @return
	 */
	protected void getRenders() {		
		
		logger.info("entering action::TopupTransTraceManagerAction.getRenders()...");
		
		// 交易币种
		transCurrencyList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		transCurrencyRender = SelectRenderUtils.toRenderString(transCurrencyList);
		// 交易类型
		transTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		transTypeRender = SelectRenderUtils.toRenderString(transTypeList);
		// 交易方向
		transDcList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		transDcRender = SelectRenderUtils.toRenderString(transDcList);
		// 交易状态
		transStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		transStatusRender = SelectRenderUtils.toRenderString(transStatusList);
		// 支付通道
		bankPmtCnlTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL);
		bankPmtCnlTypeRender = SelectRenderUtils.toRenderString(bankPmtCnlTypeList);
		// 交易终端类型
		termialTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TERMIAL_TYPE);
		termialTypeRender = SelectRenderUtils.toRenderString(termialTypeList);
		// 失败原因
		bankReturnResultList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_RETURN_RESULT);
		bankReturnResultRender = SelectRenderUtils.toRenderString(bankReturnResultList);
		// 处理结果
		handleResultList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__FAIL_REASON);
		handleResultRender = SelectRenderUtils.toRenderString(handleResultList);
		
		cnlCnlCodesList= cnlTrans3mBiz.getCnlSourceList();
		handleResultRender = SelectRenderUtils.toRenderString(cnlCnlCodesList);
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::TopupTransTraceManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */

		// 将画面输入检索条件存入DTO
		setConditionDto();
		
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<TopupTransTraceDto> result = (Page<TopupTransTraceDto>) topupTransTraceBiz.findCnlTransBySql(queryPage, topupTransTraceConditionDto);
		setResult(result);

		Collection<TopupTransTraceDto> rows2 = result.getRows();

		//改变查询结果为实际意义的字符
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
		logger.info("entering action::TopupTransTraceManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			topupTransTraceBiz.saveTopupTransTrace(topupTransTrace);
		}
		// 如果是修改
		else {
			topupTransTraceBiz.updateTopupTransTrace(topupTransTrace);
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
		logger.info("entering action::TopupTransTraceManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::TopupTransTraceManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setConditionDto();
		
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("充值申请管理列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		topupTransTraceBiz.exportTopupTransTrace(exportSetting, topupTransTraceConditionDto);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::TopupTransTraceManagerAction.setQueryCondition()...");

		// 渠道申请单号
		String reqNum = getSearchFields().get("reqNum");
		if (StringUtils.isNotEmpty(reqNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("t1.reqNum", "%" + reqNum + "%");
				queryPage.addLikeSearch("reqNum", reqNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqNum", "%" + reqNum + "%");
				exportSetting.addLikeSearch("reqNum", reqNum);
			}
		}

		// 系统申请单流水号
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

		// 渠道编码
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

		// 渠道名称
		String cnlSysName = getSearchFields().get("cnlSysName");
		if (StringUtils.isNotEmpty(cnlSysName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlSysName", "%" + cnlSysName + "%");
				queryPage.addLikeSearch("cnlSysName", cnlSysName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlSysName", "%" + cnlSysName + "%");
				exportSetting.addLikeSearch("cnlSysName", cnlSysName);
			}
		}

		// 客户编号
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlCustCode", "%" + cnlCustCode + "%");
				queryPage.addLikeSearch("cnlCustCode", cnlCustCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlCustCode", "%" + cnlCustCode + "%");
				exportSetting.addLikeSearch("cnlCustCode", cnlCustCode);
			}
		}

		// 客户名称
		String localName = getSearchFields().get("localName");
		if (StringUtils.isNotEmpty(localName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("localName", "%" + localName + "%");
				queryPage.addLikeSearch("localName", localName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("localName", "%" + localName + "%");
				exportSetting.addLikeSearch("localName", localName);
			}
		}

		// 交易开始时间
		String startTime = getSearchFields().get("startTime");
		if(StringUtils.isNotEmpty(startTime)) {
			if(null != queryPage) {
				queryPage.addQueryCondition("transTime", Timestamp.valueOf(startTime));
				queryPage.addGreatEqualSearch("transTime", Timestamp.valueOf(startTime));
			}
			if(null != exportSetting) {
				exportSetting.addQueryCondition("transTime", Timestamp.valueOf(startTime));
				exportSetting.addGreatEqualSearch("transTime", Timestamp.valueOf(startTime));
			}
		}

		// 交易结束时间
		String endTime = getSearchFields().get("endTime");
		if(StringUtils.isNotEmpty(endTime)) {
			if(null != queryPage) {
				queryPage.addQueryCondition("transTime", Timestamp.valueOf(endTime));
				queryPage.addLessEqualSearch("transTime", Timestamp.valueOf(endTime));
			}
			if(null != exportSetting) {
				exportSetting.addQueryCondition("transTime", Timestamp.valueOf(endTime));
				exportSetting.addLessEqualSearch("transTime", Timestamp.valueOf(endTime));
			}
		}

		// 入账处理状态
		String handleStatus = getSearchFields().get("handleStatus");
		if (StringUtils.isNotEmpty(handleStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("handleStatus", "%" + handleStatus + "%");
				queryPage.addLikeSearch("handleStatus", handleStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("handleStatus", "%" + handleStatus + "%");
				exportSetting.addLikeSearch("handleStatus", handleStatus);
			}
		}

		if (null != queryPage) {
			queryPage.addQueryCondition("isValid", Constants.IS_VALID_VALID);
			queryPage.addEqualSearch("isValid", Constants.IS_VALID_VALID);
		}

		if (null != exportSetting) {
			exportSetting.addQueryCondition("isValid", Constants.IS_VALID_VALID);
			exportSetting.addEqualSearch("isValid", Constants.IS_VALID_VALID);
		}
	}

	public ITopupTransTraceBiz getTopupTransTraceBiz() {
		logger.info("entering action::TopupTransTraceManagerAction.getTopupTransTraceBiz()...");
		return topupTransTraceBiz;
	}

	public void setTopupTransTraceBiz(ITopupTransTraceBiz topupTransTraceBiz) {
		logger.info("entering action::TopupTransTraceManagerAction.setTopupTransTraceBiz()...");
		this.topupTransTraceBiz = topupTransTraceBiz;
	}

	public TopupTransTrace getTopupTransTrace() {
		logger.info("entering action::TopupTransTraceManagerAction.getTopupTransTrace()...");
		return topupTransTrace;
	}

	public void setTopupTransTrace(TopupTransTrace topupTransTrace) {
		logger.info("entering action::TopupTransTraceManagerAction.setTopupTransTrace()...");
		this.topupTransTrace = topupTransTrace;
	}
	public IDataDictService getDataDictService() {
		if(dataDictService == null) {
			dataDictService = new DataDictServiceImpl();
		}
		return dataDictService;
	}
	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}
	/**
	 * 为hql语句生成查询条件
	 */
	public String getSearchCondition(){
		StringBuffer condition = new StringBuffer();

		//清除condition
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		// T_CNL_TRANS_TRACE数据有效FLG检索条件设定
		condition.append(" and t1.isValid = ?");
		queryPage.addQueryCondition("isValid", Constants.IS_VALID_VALID);

		// T_CNL_CNL_CFG数据有效FLG检索条件设定
		condition.append(" and t2.isValid = ?");
		queryPage.addQueryCondition("isValid", Constants.IS_VALID_VALID);

		// T_COR_CUST数据有效FLG检索条件设定
		condition.append(" and t3.isValid = ?");
		queryPage.addQueryCondition("isValid", Constants.IS_VALID_VALID);

		// T_CNL_TRANS_TRACE.渠道申请单号检索条件设定
		String reqNum = getSearchFields().get("reqNum");
		if (StringUtils.isNotEmpty(reqNum)) {
			condition.append(" and t1.reqNum = ?");
			queryPage.addQueryCondition("reqNum", reqNum);
		}
		// T_CNL_TRANS_TRACE.系统申请单流水号检索条件设定
		String reqInnerNum = getSearchFields().get("reqInnerNum");
		if (StringUtils.isNotEmpty(reqInnerNum)) {
			condition.append(" and t1.reqInnerNum = ?");
			queryPage.addQueryCondition("reqInnerNum", reqInnerNum);
		}
		// T_CNL_TRANS_TRACE.渠道编码检索条件设定
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			condition.append(" and t1.cnlCnlCode = ?");
			queryPage.addQueryCondition("cnlCnlCode", cnlCnlCode);
		}
		// T_CNL_TRANS_TRACE.客户编号检索条件设定
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			condition.append(" and t1.cnlCustCode = ?");
			queryPage.addQueryCondition("cnlCustCode", cnlCustCode);
		}
		// T_CNL_TRANS_TRACE.交易时间检索条件设定
		String startTime = getSearchFields().get("startTime");
		String endTime   = getSearchFields().get("endTime");
		if (StringUtils.isNotEmpty(startTime) || StringUtils.isNotEmpty(endTime)) {
			if (null != queryPage) {
				if(StringUtils.isNotEmpty(startTime)){
					condition.append(" and t1.transTime >= ? ");
					queryPage.addQueryCondition("transTime", DateUtils.convert(startTime, DateUtils.DATE_TIME_FORMAT));
				}
				if(StringUtils.isNotEmpty(endTime)){
					condition.append(" and t1.transTime <= ? ");
					queryPage.addQueryCondition("transTime", DateUtils.convert(endTime, DateUtils.DATE_TIME_FORMAT));
				}
			}
		}
		// T_CNL_TRANS_TRACE.入账处理状态检索条件设定
		String handleStatus = getSearchFields().get("handleStatus");
		if (StringUtils.isNotEmpty(handleStatus)) {
			condition.append(" and t1.handleStatus = ?");
			queryPage.addQueryCondition("handleStatus", handleStatus);
		}
		// T_CNL_CNL_CFG.渠道名称检索条件设定
		String cnlSysName = getSearchFields().get("cnlSysName");
		if (StringUtils.isNotEmpty(cnlSysName)) { 
			condition.append(" and t2.cnlSysName like ? ");
			queryPage.addQueryCondition("cnlSysName", "%"+cnlSysName+"%");
		}
		// T_COR_CUST.客户名称检索条件设定
		String localName = getSearchFields().get("localName");
		if (StringUtils.isNotEmpty(localName)) {
			condition.append(" and t3.localName like ?");
			queryPage.addQueryCondition("localName", "%"+localName+"%");
		}
		return condition.toString();
	}

	public String getId() {
		logger.info("entering action::TopupTransTraceManagerAction.getId()...");
		return id;
	}
	public void setId(String id) {
		logger.info("entering action::TopupTransTraceManagerAction.setId()...");
		this.id = id;
	}
	public List<OptionObjectPair> getHandleStatusList() {
		return handleStatusList;
	}
	public void setHandleStatusList(List<OptionObjectPair> handleStatusList) {
		this.handleStatusList = handleStatusList;
	}
	public String getStartTime() {
		logger.info("entering action::TopupTransTraceManagerAction.getStartTime()...");
		return startTime;
	}
	public void setStartTime(String startTime) {
		logger.info("entering action::TopupTransTraceManagerAction.setStartTime()...");
		this.startTime = startTime;
	}
	public String getEndTime() {
		logger.info("entering action::TopupTransTraceManagerAction.getEndTime()...");
		return endTime;
	}
	public void setEndTime(String endTime) {
		logger.info("entering action::TopupTransTraceManagerAction.setEndTime()...");
		this.endTime = endTime;
	}
	public String handle(){
		// 取得数据字典
		initialPage();

		// 审核页面：处理状态下拉列表框
		handleStatus1stList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__RECORD_HANDLE_STATUS);
		int size = handleStatus1stList.size();
		for(int i = size -1; i >= 0 ;i--) {
			OptionObjectPair oop = handleStatus1stList.get(i);
			if(!(oop.getKey().equals(Constants.RECORD_HANDLE_STATUS_1ST_CHECK_PASS) || 
					oop.getKey().equals(Constants.RECORD_HANDLE_STATUS_1ST_CHECK_FAIL))) {
				handleStatus1stList.remove(i);
			}
		}
		handleStatus1stRender = SelectRenderUtils.toRenderString(handleStatus1stList);

		topupTransTrace = topupTransTraceBiz.getTopupTransTraceById(id);

		if (topupTransTrace != null) {
			// 检索渠道名称
			this.setCnlSysName(topupTransTraceBiz.getCnlSysNameByCnlCnlCode(topupTransTrace.getCnlCnlCode()));
			// 取得交易状态名称
			this.setTransStatusName(getTransStatusNameFromList(topupTransTrace.getTransStatus()));
		}

		// 到帐时间的默认值
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.setBankReturnTime(format.format(now) + " " + "00:00:00");

		return SUCCESS;
	}
	public String review(){
		// 取得数据字典
		initialPage();

		// 复核页面：处理状态下拉列表框
		handleStatus2ndList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__RECORD_HANDLE_STATUS);
		int size = handleStatus2ndList.size();
		for(int i = size -1; i >= 0 ;i--) {
			OptionObjectPair oop = handleStatus2ndList.get(i);
			if(!(oop.getKey().equals(Constants.RECORD_HANDLE_STATUS_2ND_CHECK_PASS) || 
					oop.getKey().equals(Constants.RECORD_HANDLE_STATUS_2ND_CHECK_FAIL))) {
				handleStatus2ndList.remove(i);
			}
		}
		handleStatus2ndRender = SelectRenderUtils.toRenderString(handleStatus2ndList);
		topupTransTrace = topupTransTraceBiz.getTopupTransTraceById(id);

		if (topupTransTrace != null) {
			// 检索渠道名称
			this.setCnlSysName(topupTransTraceBiz.getCnlSysNameByCnlCnlCode(topupTransTrace.getCnlCnlCode()));
		}

		// 到帐时间的默认值
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.setBankReturnTime(format.format(now) + " " + "00:00:00");

		return SUCCESS;
	}
	public String handleOrReview(){
//		TopupTransTrace topupTransTraceTemp = DealHandleReivewDate(topupTransTraceBiz.getTopupTransTraceById(topupTransTrace.getId()));
//		topupTransTraceBiz.updateTopupTransTrace(topupTransTraceTemp);
		
		logger.info("entering action::RefundTransRefundManagerAction.saveOrUpdate()...");
		//图片
		if(null!=file&&file.length()>0){
			// path
			String path = request.getContextPath();
			String voucherNum = null;
			String voucherLocation = null;
			try {
				voucherNum = filePersistenceService.saveFile(file, fileFileName, fileContentType);
				voucherLocation = path + "/downloadFile.action?fileId="+voucherNum;
			} catch (FileServiceException e1) {
				e1.printStackTrace();
			}
			// 图片ID
			topupTransTrace.setVoucherNum(voucherNum);
			// 图片地址
			topupTransTrace.setVoucherLocation(voucherLocation);

			
		}
//		TopupTransTrace topupTransTrace = topupTransTraceBiz.getTopupTransTraceById(id);
		
		if(topupTransTrace!=null){
		
			IUser iUser = this.getCurrentUser();
			topupTransTrace.setUpdator(iUser.getUserName());
			topupTransTrace.setUpdateTime(new Date());

		}
		topupTransTraceBiz.handleOrReview(topupTransTrace);
		return SUCCESS;
	}



	////////////////////// Getters & Setters\\\\\\\\\\\\\\\\\\\\\
	public String getHandleStatusRender() {
		return handleStatusRender;
	}
	public void setHandleStatusRender(String handleStatusRender) {
		this.handleStatusRender = handleStatusRender;
	}
	public String getTransCurrencyRender() {
		return transCurrencyRender;
	}
	public void setTransCurrencyRender(String transCurrencyRender) {
		this.transCurrencyRender = transCurrencyRender;
	}
	public String getTransDcRender() {
		return transDcRender;
	}
	public void setTransDcRender(String transDcRender) {
		this.transDcRender = transDcRender;
	}
	public String getTransTypeRender() {
		return transTypeRender;
	}
	public void setTransTypeRender(String transTypeRender) {
		this.transTypeRender = transTypeRender;
	}
	public String getTransStatusRender() {
		return transStatusRender;
	}
	public void setTransStatusRender(String transStatusRender) {
		this.transStatusRender = transStatusRender;
	}
	public List<OptionObjectPair> getTransCurrencyList() {
		return transCurrencyList;
	}
	public void setTransCurrencyList(List<OptionObjectPair> transCurrencyList) {
		this.transCurrencyList = transCurrencyList;
	}
	public List<OptionObjectPair> getTransDcList() {
		return transDcList;
	}
	public void setTransDcList(List<OptionObjectPair> transDcList) {
		this.transDcList = transDcList;
	}
	public List<OptionObjectPair> getTransTypeList() {
		return transTypeList;
	}
	public void setTransTypeList(List<OptionObjectPair> transTypeList) {
		this.transTypeList = transTypeList;
	}
	public List<OptionObjectPair> getTransStatusList() {
		return transStatusList;
	}
	public void setTransStatusList(List<OptionObjectPair> transStatusList) {
		this.transStatusList = transStatusList;
	}
	public List<OptionObjectPair> getTermialTypeList() {
		return termialTypeList;
	}
	public void setTermialTypeList(List<OptionObjectPair> termialTypeList) {
		this.termialTypeList = termialTypeList;
	}
	public String getTermialTypeRender() {
		return termialTypeRender;
	}
	public void setTermialTypeRender(String termialTypeRender) {
		this.termialTypeRender = termialTypeRender;
	}
	public List<OptionObjectPair> getBankPmtCnlTypeList() {
		return bankPmtCnlTypeList;
	}
	public void setBankPmtCnlTypeList(List<OptionObjectPair> bankPmtCnlTypeList) {
		this.bankPmtCnlTypeList = bankPmtCnlTypeList;
	}
	public String getBankPmtCnlTypeRender() {
		return bankPmtCnlTypeRender;
	}
	public void setBankPmtCnlTypeRender(String bankPmtCnlTypeRender) {
		this.bankPmtCnlTypeRender = bankPmtCnlTypeRender;
	}

	public List<OptionObjectPair> getBankReturnResultList() {
		return bankReturnResultList;
	}

	public void setBankReturnResultList(List<OptionObjectPair> bankReturnResultList) {
		this.bankReturnResultList = bankReturnResultList;
	}

	public String getBankReturnResultRender() {
		return bankReturnResultRender;
	}

	public void setBankReturnResultRender(String bankReturnResultRender) {
		this.bankReturnResultRender = bankReturnResultRender;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<OptionObjectPair> getHandleResultList() {
		return handleResultList;
	}

	public void setHandleResultList(List<OptionObjectPair> handleResultList) {
		this.handleResultList = handleResultList;
	}

	public String getHandleResultRender() {
		return handleResultRender;
	}

	public void setHandleResultRender(String handleResultRender) {
		this.handleResultRender = handleResultRender;
	}
	
	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getReqInnerNum() {
		return reqInnerNum;
	}

	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
	}

	public String getCnlCnlCode() {
		return cnlCnlCode;
	}

	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getCnlCustCode() {
		return cnlCustCode;
	}

	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}

	public String getCnlSysName() {
		return cnlSysName;
	}

	public void setCnlSysName(String cnlSysName) {
		this.cnlSysName = cnlSysName;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getBankReturnTime() {
		return bankReturnTime;
	}
	public void setBankReturnTime(String bankReturnTime) {
		this.bankReturnTime = bankReturnTime;
	}
	public String getTransStatusName() {
		return transStatusName;
	}
	public void setTransStatusName(String transStatusName) {
		this.transStatusName = transStatusName;
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the fileFileName
	 */
	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * @param fileFileName the fileFileName to set
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * @return the fileContentType
	 */
	public String getFileContentType() {
		return fileContentType;
	}

	/**
	 * @param fileContentType the fileContentType to set
	 */
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<OptionObjectPair> getHandleStatus1stList() {
		return handleStatus1stList;
	}

	public void setHandleStatus1stList(List<OptionObjectPair> handleStatus1stList) {
		this.handleStatus1stList = handleStatus1stList;
	}

	public String getHandleStatus1stRender() {
		return handleStatus1stRender;
	}

	public void setHandleStatus1stRender(String handleStatus1stRender) {
		this.handleStatus1stRender = handleStatus1stRender;
	}

	public List<OptionObjectPair> getHandleStatus2ndList() {
		return handleStatus2ndList;
	}

	public void setHandleStatus2ndList(List<OptionObjectPair> handleStatus2ndList) {
		this.handleStatus2ndList = handleStatus2ndList;
	}

	public String getHandleStatus2ndRender() {
		return handleStatus2ndRender;
	}

	public void setHandleStatus2ndRender(String handleStatus2ndRender) {
		this.handleStatus2ndRender = handleStatus2ndRender;
	}

	public ICnlTrans3mBiz getCnlTrans3mBiz() {
		return cnlTrans3mBiz;
	}

	public void setCnlTrans3mBiz(ICnlTrans3mBiz cnlTrans3mBiz) {
		this.cnlTrans3mBiz = cnlTrans3mBiz;
	}

	public List<OptionObjectPair> getCnlCnlCodesList() {
		return cnlCnlCodesList;
	}

	public void setCnlCnlCodesList(List<OptionObjectPair> cnlCnlCodesList) {
		this.cnlCnlCodesList = cnlCnlCodesList;
	}

	/**
	 * @return the filePersistenceService
	 */
	public IFilePersistenceService getFilePersistenceService() {
		return filePersistenceService;
	}

	/**
	 * @param filePersistenceService the filePersistenceService to set
	 */
	public void setFilePersistenceService(IFilePersistenceService filePersistenceService) {
		this.filePersistenceService = filePersistenceService;
	}

	private void setConditionDto() {
		topupTransTraceConditionDto = new TopupTransTraceDto();
		topupTransTraceConditionDto.setReqNum(getSearchFields().get("reqNum"));
		topupTransTraceConditionDto.setReqInnerNum(getSearchFields().get("reqInnerNum"));
		topupTransTraceConditionDto.setCnlCnlCode(getSearchFields().get("cnlCnlCode"));
		topupTransTraceConditionDto.setCnlCustCode(getSearchFields().get("cnlCustCode"));
		topupTransTraceConditionDto.setStartTime(getSearchFields().get("startTime"));
		topupTransTraceConditionDto.setEndTime(getSearchFields().get("endTime"));
		topupTransTraceConditionDto.setHandleStatus(getSearchFields().get("handleStatus"));
		topupTransTraceConditionDto.setLocalName(getSearchFields().get("localName"));
	}
	private String getTransStatusNameFromList(String transStatus) {
		 List<OptionObjectPair> obpist = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		 if (transStatus != null && !"".equals(transStatus)) {
			 if (obpist != null && obpist.size() > 0) {
				 for (OptionObjectPair obp : obpist) {
					 if (transStatus.equals(obp.getKey())) {
						 return obp.getValue();
					 }
				 }
			 }
		 }
		 return "";
	}
}
