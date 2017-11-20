/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Message;
import javax.jms.TextMessage;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.dto.DownloadDto;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceException;
import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.core.module.account.biz.ICnlTransBiz;
import com.ibs.core.module.account.biz.ICnlTransTracePendingBiz;
import com.ibs.core.module.account.common.ConstantsCnlTrans;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.mq.producer.IProducer;
import com.ibs.core.module.bank.dao.ICorBankIntfDao;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.cnlmgr.domain.CnlCnlCfg;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.context.BeanHolder;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlTransTracePendingManagerAction extends CrudBaseAction{
	private static final int BUFFER_SIZE = 16 * 1024;
	private ICnlTransTracePendingBiz cnlTransTracePendingBiz;
	private String beginTime;
	private String endTime;
	private Integer dateType;
    private String reviewMsg;

    
	public String getReviewMsg() {
		return reviewMsg;
	}

	public void setReviewMsg(String reviewMsg) {
		this.reviewMsg = reviewMsg;
	}

	private IDataDictBiz dataDictBiz;  
	private ICnlTransBiz cnlTransBiz;
	private IFilePersistenceService filePersistenceService;
	private String bankReturnTime;
	private String bankPmtCnlType;
	private String handleMsg;
	private String transStatus;
	private String bankTransNum;
	private String voucherNum;
	private String handleStatus;
	private String message;
	private String msg;
	private String errorMsg;

	

	
	

	public IFilePersistenceService getFilePersistenceService() {
		return filePersistenceService;
	}

	public void setFilePersistenceService(IFilePersistenceService filePersistenceService) {
		this.filePersistenceService = filePersistenceService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private CnlTransTrace Trace;
	

	// 文件标题  
	private String title;  
	// 上传文件域对象  
	private File file;  
	// 上传文件名  
	private String fileFileName;  
	// 上传文件类型  
	private String fileContentType;  
	// 保存文件的目录路径(通过依赖注入)  
	private String savePath;  
	//文件id
	private String fileId;
	
	private static final Integer DEFAULT_BUFFER_SIZE = new Integer(2048);
	private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream,charset=ISO8859-1";
	
	private DownloadDto downloadDto = new DownloadDto();
	public String getFilePathId() {
		return fileId;
	}

	public void setFilePathId(String filePathId) {
		this.fileId = filePathId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public CnlTransTrace getTrace() {
		return Trace;
	}

	public void setTrace(CnlTransTrace trace) {
		Trace = trace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBankReturnTime() {
		return bankReturnTime;
	}

	public void setBankReturnTime(String bankReturnTime) {
		this.bankReturnTime = bankReturnTime;
	}

	public String getBankPmtCnlType() {
		return bankPmtCnlType;
	}

	public void setBankPmtCnlType(String bankPmtCnlType) {
		this.bankPmtCnlType = bankPmtCnlType;
	}

	public String getHandleMsg() {
		return handleMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getBankTransNum() {
		return bankTransNum;
	}

	public void setBankTransNum(String bankTransNum) {
		this.bankTransNum = bankTransNum;
	}

	public String getVoucherNum() {
		return voucherNum;
	}

	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public ICnlTransBiz getCnlTransBiz() {
		return cnlTransBiz;
	}

	public void setCnlTransBiz(ICnlTransBiz cnlTransBiz) {
		this.cnlTransBiz = cnlTransBiz;
	}

	public List<CnlCnlCfg> getCnlCnlCfgList() {
		return cnlCnlCfgList;
	}

	public void setCnlCnlCfgList(List<CnlCnlCfg> cnlCnlCfgList) {
		this.cnlCnlCfgList = cnlCnlCfgList;
	}

	private List<CnlCnlCfg> cnlCnlCfgList;


	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	private List<OptionObjectPair> typeList;
	private List<OptionObjectPair> statusList;
	private List<OptionObjectPair> reqNumList;
	private List<OptionObjectPair> dcList;
	private List<OptionObjectPair> handleStatusList;
	private List<OptionObjectPair> transStautsList;
	private List<OptionObjectPair> bankPmtCnlTypeList;
	private List<OptionObjectPair> errTypeList;
	private List<OptionObjectPair> errCodeList;
	public List<OptionObjectPair> getErrTypeList() {
		return errTypeList;
	}

	public void setErrTypeList(List<OptionObjectPair> errTypeList) {
		this.errTypeList = errTypeList;
	}

	public List<OptionObjectPair> getErrCodeList() {
		return errCodeList;
	}

	public void setErrCodeList(List<OptionObjectPair> errCodeList) {
		this.errCodeList = errCodeList;
	}

	public List<OptionObjectPair> getBankPmtCnlTypeList() {
		return bankPmtCnlTypeList;
	}

	public void setBankPmtCnlTypeList(List<OptionObjectPair> bankPmtCnlTypeList) {
		this.bankPmtCnlTypeList = bankPmtCnlTypeList;
	}

	public List<OptionObjectPair> getTransStautsList() {
		return transStautsList;
	}

	public void setTransStautsList(List<OptionObjectPair> transStautsList) {
		this.transStautsList = transStautsList;
	}

	public List<OptionObjectPair> getHandleStatusList() {
		return handleStatusList;
	}

	public void setHandleStatusList(List<OptionObjectPair> handleStatusList) {
		this.handleStatusList = handleStatusList;
	}

	public List<OptionObjectPair> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<OptionObjectPair> typeList) {
		this.typeList = typeList;
	}

	public List<OptionObjectPair> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<OptionObjectPair> statusList) {
		this.statusList = statusList;
	}







	public List<OptionObjectPair> getReqNumList() {
		return reqNumList;
	}

	public void setReqNumList(List<OptionObjectPair> reqNumList) {
		this.reqNumList = reqNumList;
	}

	public List<OptionObjectPair> getDcList() {
		return dcList;
	}

	public void setDcList(List<OptionObjectPair> dcList) {
		this.dcList = dcList;
	}

	public ICnlTransTracePendingBiz getCnlTransTracePendingBiz() {
		return cnlTransTracePendingBiz;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

	private String id;
	private CnlTransTrace cnlTransTrace;
	private IUser user ; 
	

	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlTransTraceManagerAction.list()...");
		load();
		return SUCCESS;
	}

	/**
	 * 查找需要增加补救单信息
	 * @return 查询结果
	 */
	public String addHelp(){
		message = "";
		cnlTransTrace = cnlTransTracePendingBiz.getCnlTransTraceById(id);
		String code = cnlTransTrace.getBankDebitCode();
		String c = cnlTransTracePendingBiz.findStatus(code);
		if(c==null||"".equals(c)){
			return ERROR;
		}
		if((Constants.CNL_STATUS_NOT_VALID).equals(c)){	
			bankPmtCnlTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__PAY_WAY);
			return SUCCESS;
		}else{
			message = "银行接口正常";
			return ERROR;
		}



	}

	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlTransTraceManagerAction.create()...");
		return SUCCESS;
	}
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		handleStatusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__PENDING_HANDLE_STATUS);
		 transStautsList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		 bankPmtCnlTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__PAY_WAY);
		logger.info("entering action::CnlTransTraceManagerAction.modify()...");
		cnlTransTrace = cnlTransTracePendingBiz.findDicCnlTransTraceById(id);
		return SUCCESS;
	}
	
	
	
	/**
	 * Action方法，跳转到明细页面
	 * 
	 * @return
	 */
	public String detail() {
		logger.info("entering action::CnlTransTraceManagerAction.detail()...");
		cnlTransTrace = cnlTransTracePendingBiz.findDicCnlTransTraceById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到审核页面
	 * 
	 * @return
	 */
	public String verify() {
		logger.info("entering action::CnlTransTraceManagerAction.verify()...");
		cnlTransTrace = cnlTransTracePendingBiz.findDicCnlTransTraceById(id);
		return SUCCESS;
	}

	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlTransTraceManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */

		Page<CnlTransTrace> result = (Page<CnlTransTrace>) cnlTransTracePendingBiz
				.findCnlTransTraceByPage(queryPage);
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
		logger.info("entering action::CnlTransTraceManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlTransTracePendingBiz.saveCnlTransTrace(cnlTransTrace);
		}
		// 如果是修改
		else {
			cnlTransTracePendingBiz.updateCnlTransTrace(cnlTransTrace);
		}

		return SUCCESS;
	}

	public String modifyAddHelp(){
		 //根据服务器的文件保存地址和原文件名创建目录文件全路径  
		String id="";
		try {
			id = filePersistenceService.saveFile(file,fileFileName,fileContentType);
		} catch (FileServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cnlTransTrace = cnlTransTracePendingBiz.getCnlTransTraceById(id);
		cnlTransTrace.setBankReturnTime(DateUtils.convert( bankReturnTime,DateUtils.DATE_FORMAT));
		cnlTransTrace.setBankPmtCnlType(bankPmtCnlType);
		cnlTransTrace.setHandleMsg(handleMsg);
		cnlTransTrace.setTransStatus(Constants.TRANS_STATUS_PROCESSING);
		cnlTransTrace.setBankTransNum(bankTransNum);
		cnlTransTrace.setHandleStatus(Constants.PENDING_HANDLE_STATUS_SUBMIT);
		cnlTransTrace.setVoucherLocation(id);
		cnlTransTrace.setHandleTime(new Date());
		cnlTransTrace.setOperator(this.getUserName());
		cnlTransTracePendingBiz.updateStatus(cnlTransTrace);

		
        

		return SUCCESS;
	}

	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CnlTransTraceManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlTransTraceManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlTransTracePendingBiz.exportCnlTransTrace(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlTransTraceManagerAction.setQueryCondition()...");


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


		// CUST_CODE
		String custCode = getSearchFields().get("custCode");
		if (StringUtils.isNotEmpty(custCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("custCode", "%" + custCode + "%");
				queryPage.addLikeSearch("custCode", custCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("custCode", "%" + custCode + "%");
				exportSetting.addLikeSearch("custCode", custCode);
			}
		}


		// CNL_CUST_CODE
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

		System.out.println("cnlCustCode:::::;           "+cnlCustCode);
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






		// TRANS_TYPE
		String transType = getSearchFields().get("transType");
		if (StringUtils.isNotEmpty(transType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transType", "%" + transType + "%");
				queryPage.addLikeSearch("transType", transType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transType", "%" + transType + "%");
				exportSetting.addLikeSearch("transType", transType);
			}
		}


		// TRANS_DC
		String transDc = getSearchFields().get("transDc");
		if (StringUtils.isNotEmpty(transDc)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transDc", "%" + transDc + "%");
				queryPage.addLikeSearch("transDc", transDc);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transDc", "%" + transDc + "%");
				exportSetting.addLikeSearch("transDc", transDc);
			}
		}


		// TRADE_SUB_TYPE
		String tradeSubType = getSearchFields().get("tradeSubType");
		if (StringUtils.isNotEmpty(tradeSubType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("tradeSubType", "%" + tradeSubType + "%");
				queryPage.addLikeSearch("tradeSubType", tradeSubType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("tradeSubType", "%" + tradeSubType + "%");
				exportSetting.addLikeSearch("tradeSubType", tradeSubType);
			}
		}


		// TRANS_CURRENCY
		String transCurrency = getSearchFields().get("transCurrency");
		if (StringUtils.isNotEmpty(transCurrency)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transCurrency", "%" + transCurrency + "%");
				queryPage.addLikeSearch("transCurrency", transCurrency);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transCurrency", "%" + transCurrency + "%");
				exportSetting.addLikeSearch("transCurrency", transCurrency);
			}
		}


		// TRANS_AMOUNT
		Long transAmount =Long.getLong(getSearchFields().get("transAmount"));
		if (null != transAmount) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transAmount", transAmount);
				queryPage.addEqualSearch("transAmount", transAmount);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("transAmount", transAmount);
				queryPage.addEqualSearch("transAmount", transAmount);
			}
		}


		// TRNAS_STATUS
		String transStatus = getSearchFields().get("transStatus");
		if (StringUtils.isNotEmpty(transStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transStatus", "%" + transStatus + "%");
				queryPage.addLikeSearch("transStatus", transStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transStatus", "%" + transStatus + "%");
				exportSetting.addLikeSearch("transStatus", transStatus);
			}
		}


		// TRANS_DATE
		String transDate = getSearchFields().get("transDate");
		if (StringUtils.isNotEmpty(transDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transDate",
						DateUtils.convert(transDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transDate",
						DateUtils.convert(transDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// TRNAS_TIME
		String trnasTime = getSearchFields().get("trnasTime");
		if (StringUtils.isNotEmpty(trnasTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("trnasTime",
						DateUtils.convert(trnasTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("trnasTime",
						DateUtils.convert(trnasTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// TRANS_RATE
		Long transRate =Long.getLong( getSearchFields().get("transRate"));
		if (null != transRate) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transRate", transRate);
				queryPage.addEqualSearch("transRate", transRate);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("transRate", transRate);
				queryPage.addEqualSearch("transRate", transRate);
			}
		}


		// TRANS_COMMENTS
		String transComments = getSearchFields().get("transComments");
		if (StringUtils.isNotEmpty(transComments)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transComments", "%" + transComments + "%");
				queryPage.addLikeSearch("transComments", transComments);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transComments", "%" + transComments + "%");
				exportSetting.addLikeSearch("transComments", transComments);
			}
		}


		// BANK_ACCEPTE_TIME
		String bankAccepteTime = getSearchFields().get("bankAccepteTime");
		if (StringUtils.isNotEmpty(bankAccepteTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankAccepteTime",
						DateUtils.convert(bankAccepteTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankAccepteTime",
						DateUtils.convert(bankAccepteTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// BANK_TRANS_NUM
		String bankTransNum = getSearchFields().get("bankTransNum");
		if (StringUtils.isNotEmpty(bankTransNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankTransNum", "%" + bankTransNum + "%");
				queryPage.addLikeSearch("bankTransNum", bankTransNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankTransNum", "%" + bankTransNum + "%");
				exportSetting.addLikeSearch("bankTransNum", bankTransNum);
			}
		}


		// BANK_HANDLE_NUM
		String bankHandleNum = getSearchFields().get("bankHandleNum");
		if (StringUtils.isNotEmpty(bankHandleNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankHandleNum", "%" + bankHandleNum + "%");
				queryPage.addLikeSearch("bankHandleNum", bankHandleNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankHandleNum", "%" + bankHandleNum + "%");
				exportSetting.addLikeSearch("bankHandleNum", bankHandleNum);
			}
		}


		// BANK_RETURN_TIME
		String bankReturnTime = getSearchFields().get("bankReturnTime");
		if (StringUtils.isNotEmpty(bankReturnTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReturnTime",
						DateUtils.convert(bankReturnTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReturnTime",
						DateUtils.convert(bankReturnTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// BANK_RETURN_RESULT
		String bankReturnResult = getSearchFields().get("bankReturnResult");
		if (StringUtils.isNotEmpty(bankReturnResult)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReturnResult", "%" + bankReturnResult + "%");
				queryPage.addLikeSearch("bankReturnResult", bankReturnResult);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReturnResult", "%" + bankReturnResult + "%");
				exportSetting.addLikeSearch("bankReturnResult", bankReturnResult);
			}
		}


		// BANK_PMT_CNL_TYPE
		String bankPmtCnlType = getSearchFields().get("bankPmtCnlType");
		if (StringUtils.isNotEmpty(bankPmtCnlType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankPmtCnlType", "%" + bankPmtCnlType + "%");
				queryPage.addLikeSearch("bankPmtCnlType", bankPmtCnlType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankPmtCnlType", "%" + bankPmtCnlType + "%");
				exportSetting.addLikeSearch("bankPmtCnlType", bankPmtCnlType);
			}
		}


		// BANK_PMT_CNL_CODE
		String bankPmtCnlCode = getSearchFields().get("bankPmtCnlCode");
		if (StringUtils.isNotEmpty(bankPmtCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankPmtCnlCode", "%" + bankPmtCnlCode + "%");
				queryPage.addLikeSearch("bankPmtCnlCode", bankPmtCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankPmtCnlCode", "%" + bankPmtCnlCode + "%");
				exportSetting.addLikeSearch("bankPmtCnlCode", bankPmtCnlCode);
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


		// ISIN_GL
		String isinGl = getSearchFields().get("isinGl");
		if (StringUtils.isNotEmpty(isinGl)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isinGl", "%" + isinGl + "%");
				queryPage.addLikeSearch("isinGl", isinGl);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isinGl", "%" + isinGl + "%");
				exportSetting.addLikeSearch("isinGl", isinGl);
			}
		}


		// PRINTED_TIME
		String printedTime = getSearchFields().get("printedTime");
		if (StringUtils.isNotEmpty(printedTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("printedTime",
						DateUtils.convert(printedTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("printedTime",
						DateUtils.convert(printedTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// IS_PRINTED
		String isPrinted = getSearchFields().get("isPrinted");
		if (StringUtils.isNotEmpty(isPrinted)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isPrinted", "%" + isPrinted + "%");
				queryPage.addLikeSearch("isPrinted", isPrinted);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isPrinted", "%" + isPrinted + "%");
				exportSetting.addLikeSearch("isPrinted", isPrinted);
			}
		}


		// BANK_CREDIT_ACNT_CODE
		String bankCreditAcntCode = getSearchFields().get("bankCreditAcntCode");
		if (StringUtils.isNotEmpty(bankCreditAcntCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditAcntCode", "%" + bankCreditAcntCode + "%");
				queryPage.addLikeSearch("bankCreditAcntCode", bankCreditAcntCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditAcntCode", "%" + bankCreditAcntCode + "%");
				exportSetting.addLikeSearch("bankCreditAcntCode", bankCreditAcntCode);
			}
		}


		// BANK_CREDIT_NAME
		String bankCreditName = getSearchFields().get("bankCreditName");
		if (StringUtils.isNotEmpty(bankCreditName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditName", "%" + bankCreditName + "%");
				queryPage.addLikeSearch("bankCreditName", bankCreditName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditName", "%" + bankCreditName + "%");
				exportSetting.addLikeSearch("bankCreditName", bankCreditName);
			}
		}


		// BANK_CREDIT_CODE
		String bankCreditCode = getSearchFields().get("bankCreditCode");
		if (StringUtils.isNotEmpty(bankCreditCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditCode", "%" + bankCreditCode + "%");
				queryPage.addLikeSearch("bankCreditCode", bankCreditCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditCode", "%" + bankCreditCode + "%");
				exportSetting.addLikeSearch("bankCreditCode", bankCreditCode);
			}
		}


		// BANK_CREDIT_BRANCH_NAME
		String bankCreditBranchName = getSearchFields().get("bankCreditBranchName");
		if (StringUtils.isNotEmpty(bankCreditBranchName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditBranchName", "%" + bankCreditBranchName + "%");
				queryPage.addLikeSearch("bankCreditBranchName", bankCreditBranchName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditBranchName", "%" + bankCreditBranchName + "%");
				exportSetting.addLikeSearch("bankCreditBranchName", bankCreditBranchName);
			}
		}


		// BANK_CREDIT_BRANCH_CODE
		String bankCreditBranchCode = getSearchFields().get("bankCreditBranchCode");
		if (StringUtils.isNotEmpty(bankCreditBranchCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditBranchCode", "%" + bankCreditBranchCode + "%");
				queryPage.addLikeSearch("bankCreditBranchCode", bankCreditBranchCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditBranchCode", "%" + bankCreditBranchCode + "%");
				exportSetting.addLikeSearch("bankCreditBranchCode", bankCreditBranchCode);
			}
		}


		// BANK_CREDIT_CUST_NAME
		String bankCreditCustName = getSearchFields().get("bankCreditCustName");
		if (StringUtils.isNotEmpty(bankCreditCustName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditCustName", "%" + bankCreditCustName + "%");
				queryPage.addLikeSearch("bankCreditCustName", bankCreditCustName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditCustName", "%" + bankCreditCustName + "%");
				exportSetting.addLikeSearch("bankCreditCustName", bankCreditCustName);
			}
		}


		// BANK_CREDIT_CARD_NUM
		String bankCreditCardNum = getSearchFields().get("bankCreditCardNum");
		if (StringUtils.isNotEmpty(bankCreditCardNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCreditCardNum", "%" + bankCreditCardNum + "%");
				queryPage.addLikeSearch("bankCreditCardNum", bankCreditCardNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCreditCardNum", "%" + bankCreditCardNum + "%");
				exportSetting.addLikeSearch("bankCreditCardNum", bankCreditCardNum);
			}
		}






		// BANK_DEBIT_CODE
		String bankDebitCode = getSearchFields().get("bankDebitCode");
		if (StringUtils.isNotEmpty(bankDebitCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankDebitCode", "%" + bankDebitCode + "%");
				queryPage.addLikeSearch("bankDebitCode", bankDebitCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankDebitCode", "%" + bankDebitCode + "%");
				exportSetting.addLikeSearch("bankDebitCode", bankDebitCode);
			}
		}










		if(dateType!=null){
			if(beginTime==null||"".equals(beginTime)){

				beginTime = DateUtils.getStartDatetime(new Date()).toString();
			}
			if(endTime==null||"".equals(endTime)){
				endTime = DateUtils.getEndDatetime(new Date()).toString();
			}



			Date begin = DateUtils.getStartDatetime(DateUtils.convert(beginTime));
			Date end = DateUtils.getEndDatetime(DateUtils.convert(endTime));
			System.out.println("参数"+dateType+"开始"+beginTime+"结束"+endTime);
			switch(dateType){
			//交易时间
			case 1:
				//大于开始时间
				if (StringUtils.isNotEmpty(beginTime)&&StringUtils.isNotEmpty(endTime)) {



					if (null != queryPage) {
						queryPage.addQueryCondition("transDate",
								beginTime	);
						queryPage.addBetweenSearch("transDate", begin, end);
					}
					if (null != exportSetting) {
						exportSetting.addQueryCondition("transDate",
								beginTime);
						exportSetting.addBetweenSearch("transDate", begin, end);
					}

				}
				break;
				//银行网关进入时间
			case 2:
				//大于开始时间
				if (StringUtils.isNotEmpty(beginTime)&&StringUtils.isNotEmpty(endTime)) {


					if (null != queryPage) {
						queryPage.addQueryCondition("bankAccepteTime",
								beginTime	);
						queryPage.addBetweenSearch("bankAccepteTime", begin, end);
					}
					if (null != exportSetting) {
						exportSetting.addQueryCondition("bankAccepteTime",
								beginTime);
						exportSetting.addBetweenSearch("bankAccepteTime", begin, end);
					}

				}
				break;
				//到账时间

			}
		}








		// BANK_REQ_TRANS_TIME
		String bankReqTransTime = getSearchFields().get("bankReqTransTime");
		if (StringUtils.isNotEmpty(bankReqTransTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankReqTransTime",
						DateUtils.convert(bankReqTransTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankReqTransTime",
						DateUtils.convert(bankReqTransTime,
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





		// IS_VALID
		String isValid = Constants.CNL_STATUS_VALID;
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



		// HANDLE_STATUS
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





		// TERMIAL_TYPE
		String termialType = getSearchFields().get("termialType");
		if (StringUtils.isNotEmpty(termialType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("termialType", "%" + termialType + "%");
				queryPage.addLikeSearch("termialType", termialType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("termialType", "%" + termialType + "%");
				exportSetting.addLikeSearch("termialType", termialType);
			}
		}

	}

	public ICnlTransTracePendingBiz getCnlTransTraceBiz() {
		logger.info("entering action::CnlTransTraceManagerAction.getCnlTransTraceBiz()...");
		return cnlTransTracePendingBiz;
	}

	public void setCnlTransTracePendingBiz(ICnlTransTracePendingBiz cnlTransTraceBiz) {
		logger.info("entering action::CnlTransTraceManagerAction.setCnlTransTraceBiz()...");
		this.cnlTransTracePendingBiz = cnlTransTraceBiz;
	}

	public CnlTransTrace getCnlTransTrace() {
		logger.info("entering action::CnlTransTraceManagerAction.getCnlTransTrace()...");
		return cnlTransTrace;
	}

	public void setCnlTransTrace(CnlTransTrace cnlTransTrace) {
		logger.info("entering action::CnlTransTraceManagerAction.setCnlTransTrace()...");
		this.cnlTransTrace = cnlTransTrace;
	}

	public String getId() {
		logger.info("entering action::CnlTransTraceManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlTransTraceManagerAction.setId()...");
		this.id = id;
	}
	private void load(){
		//交易类型
		typeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		//交易状态
		statusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		//交易方向
		dcList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		//错误类型
		errTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__FAIL_CODE);
		//处理状态
		handleStatusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__PENDING_HANDLE_STATUS);
		//错误代码
		errCodeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_TRANS_ERROR_CODE);
		//渠道
		CnlCnlCfg c = new CnlCnlCfg();
		c.setIsValid(Constants.CNL_STATUS_VALID);
		setCnlCnlCfgList(cnlTransBiz.getCnlCnlCodeVal(c));
		reqNumList = toOptionPairList(cnlCnlCfgList);

		// 开始时间和结束时间文本框的默认值
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = format.format(now)+" 00:00:00";
		String endTime = format.format(now)+" 23:59:59";
		this.setBeginTime(startTime); 
		this.setEndTime(endTime);

	}

	private List<OptionObjectPair> toOptionPairList(List<CnlCnlCfg> list) {
		List<OptionObjectPair> optionPairList = new ArrayList<OptionObjectPair>();
		if(null != list) {
			for(CnlCnlCfg o : list) {
				optionPairList.add(new OptionObjectPair(o.getCnlCnlCode(),o.getCnlSysName()));
			}
		}
		return optionPairList;
	}


	public String findById(){
		Trace = cnlTransTracePendingBiz.getCnlTransTraceById(id);
		return SUCCESS;
	}

	//自己封装的一个把源文件对象复制成目标文件对象  
	private static void copy(File src, File dst) {  
		InputStream in = null;  
		OutputStream out = null;  
		try {  
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);  
			out = new BufferedOutputStream(new FileOutputStream(dst),  
					BUFFER_SIZE);  
			byte[] buffer = new byte[BUFFER_SIZE];  
			int len = 0;  
			while ((len = in.read(buffer)) > 0) {  
				out.write(buffer, 0, len);  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		} finally {  
			if (null != in) {  
				try {  
					in.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
			if (null != out) {  
				try {  
					out.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}
	}
	
	/**
	 * @author jicheng
	 * 修改操作
	 */
	public String updateCnlTransTrace(){
		logger.debug("entering action::CnlTransTraceManagerAction.updateCnlTransTrace()...");
		//正确信息
		msg = "";
		//错误信息
		errorMsg = "";
		
		//避免修改数据会变成数据字典中的数据
		if(null!=cnlTransTrace){
			
	    //根据服务器的文件保存地址和原文件名创建目录文件全路径  
	    String id="";
		try {
			id = filePersistenceService.saveFile(file,fileFileName,fileContentType);
		} catch (FileServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CnlTransTrace transTrace = cnlTransTracePendingBiz.getCnlTransTraceById(cnlTransTrace.getId());
		 IUser iUser = this.getCurrentUser();
		//判断是否有效
		if(transTrace!=null&&transTrace.getIsValid().equals(Constants.IS_VALID_VALID)){
		//修改处理状态为已提交
		transTrace.setHandleStatus(Constants.PENDING_HANDLE_STATUS_SUBMIT);
		
		if(null!=cnlTransTrace.getBankReturnTime()){
		transTrace.setBankReturnTime(cnlTransTrace.getBankReturnTime());
		}
		if(StringUtils.isNotEmpty(cnlTransTrace.getBankPmtCnlType())){
		transTrace.setBankPmtCnlType(cnlTransTrace.getBankPmtCnlType());
		}
		if(StringUtils.isNotEmpty(cnlTransTrace.getTransComments())){
		transTrace.setTransComments(cnlTransTrace.getTransComments());
		}
		if(StringUtils.isNotEmpty(cnlTransTrace.getBankTransNum())){
		transTrace.setBankTransNum(cnlTransTrace.getBankTransNum());
	    }
		if(StringUtils.isNotEmpty(cnlTransTrace.getVoucherNum())){
		transTrace.setVoucherNum(cnlTransTrace.getVoucherNum());
        }
		//文件路径
		transTrace.setVoucherLocation(id);
		//处理人
			transTrace.setOperator(iUser.getUserName());
		
		//处理时间
			transTrace.setHandleTime(new Date());
		
		
		cnlTransTracePendingBiz.updateCnlTransTrace(transTrace);
		
		msg = "修改成功";
		return SUCCESS;
		}
		errorMsg = "修改异常失败";
		return SUCCESS;
		}
		return SUCCESS;
		
		
	}
	
	
	
	/**
	 * @author jicheng
	 * 审核页面 审核成功 根据id 修改状态为审核成功
	 */
	
	public String verifyStatusSuccess(){
		logger.debug("entering action::CnlTransTraceManagerAction.verifyStatusSuccess()...");
		msg = "" ;
		errorMsg = "";
	   CnlTransTrace transTrace = cnlTransTracePendingBiz.getCnlTransTraceById(id);	
	   if(transTrace!=null&&transTrace.getIsValid().equals(Constants.IS_VALID_VALID)){
	   transTrace.setHandleStatus(Constants.PENDING_HANDLE_STATUS_SUCCESS);
	   transTrace.setTransStatus(Constants.TRANS_STATUS_SUCCESS);
	   IUser iUser = this.getCurrentUser();
	   transTrace.setReviewer(iUser.getUserName());
	   transTrace.setReviewTime(new Date());
	   cnlTransTracePendingBiz.verifyCnlTransTrace(transTrace);
	   msg = "审核完毕";
	   return SUCCESS;
	   }
	   errorMsg = "审核异常失败";
	   return SUCCESS;
	   
	}
	/**
	 * @author jicheng
	 * 审核页面 审核失败 根据id 修改状态为审核失败
	 */
	
	public String verifyStatusFail(){
		logger.debug("entering action::CnlTransTraceManagerAction.verifyStatusFail()...");
		msg = "" ;
		errorMsg = "";
	   CnlTransTrace transTrace = cnlTransTracePendingBiz.getCnlTransTraceById(id);	
	   if(transTrace!=null&&transTrace.getIsValid().equals(Constants.IS_VALID_VALID)){
	   transTrace.setHandleStatus(Constants.PENDING_HANDLE_STATUS_FAIL);
	   if(StringUtils.isNotEmpty(reviewMsg)){
	   transTrace.setReviewMsg(reviewMsg);
	   }
	   IUser iUser = this.getCurrentUser();
	   transTrace.setReviewer(iUser.getUserName());
	   transTrace.setReviewTime(new Date());
	   cnlTransTracePendingBiz.verifyCnlTransTrace(transTrace);
	   msg = "审核完毕";
	   return SUCCESS;
	   }
	   errorMsg = "审核异常失败";
       return SUCCESS;
	   
	 }
	
	/**
	 * 用于文件上传后的下载功能
	 * @return
	 */
	public String downloadFile(){
		
		logger.trace("entering action...");
		
		logger.debug("fileId: " + downloadDto.getFileId());
              
		
		try {
			
			if(downloadDto.getFileId()==null)
				downloadDto.setFileId(fileId);
			if (downloadDto.getBufferSize() == null)
				downloadDto.setBufferSize(DEFAULT_BUFFER_SIZE);
			if (downloadDto.getContentType() == null)
				downloadDto.setContentType(DEFAULT_CONTENT_TYPE);
			
			FilePersistence fp = this.filePersistenceService.getDownloadFile(downloadDto.getFileId());
			
			downloadDto.setFileName(new String(fp.getFileName().getBytes("GBK"), "ISO8859-1"));
			downloadDto.setContentType(fp.getContentType());
			downloadDto.setInputStream(new FileInputStream(fp.getPhsicalName()));
		} catch (FileServiceException fse) {
			this.addActionError(fse.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return ERROR;
		}
		
		return SUCCESS;
		
	}

	
}
