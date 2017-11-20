/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.ibs.core.module.account.biz.ICnlTrans3mBiz;
import com.ibs.core.module.account.biz.ICnlTransBiz;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.mdmbasedata.service.impl.DataDictServiceImpl;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

/**
 * @author  	: qiyp
 * @date    	: 2016-08-11
 * @comments	: 当前交易流水查询
 */
@SuppressWarnings("serial")
public class CnlTransManagerAction extends CrudBaseAction {

	private ICnlTransBiz cnlTransBiz;         // 当前交易流水查询Biz
	private ICnlTrans3mBiz cnlTrans3mBiz;     // 历史交易流水查询Biz
	private IDataDictService dataDictService; // 数据字典Service
	private String id;
	private CnlTrans cnlTrans;                // 当前交易流水对应的DominObject，与数据库表T_CNL_TRANS对应
	private String startTime;                 // 开始时间，与页面元素中的开始时间对应
	private String endTime;                   // 结束时间，与页面元素中的结束时间对应
	
	// 渠道来源标识
	private List<OptionObjectPair> cnlCnlList;
	private String cnlCnlRender;

	// 交易方向名值对
	private List<OptionObjectPair> transDcList;
	private String transDcRender;
	
	// 交易类型名值对
	private List<OptionObjectPair> transTypeList;
	private String transTypeRender;
	
	// 交易状态名值对
	private List<OptionObjectPair> transStatusList;
	private String transStatusRender;
	
	// 银行处理优先级名值对
	private List<OptionObjectPair> bankHandlePrioriryList;
	private String bankHandlePrioriryRender;
	
	// 交易终端类型名值对
	private List<OptionObjectPair> tradeTerminalTypeList;
	private String tradeTerminalTypeRender;
	
	// 入账标志名值对
	private List<OptionObjectPair> isInGlList;
	private String isInGlRender;
	
	// 打印标志名值对
	private List<OptionObjectPair> isPrintedList;
	private String isPrintedRender;
	
	// 交易币种
	private List<OptionObjectPair> transCurrencyList;
	private String transCurrencyListRender;
	
	// 支付通道类型
	private List<OptionObjectPair> bankPmtCnlTypeList;
	private String bankPmtCnlTypeRender;
	
	/** 
	 * 当前交易流水查询页面,页面默认值设置，下拉框内容取值等初期化设置方法
	 * @param 无
	 * @return 无
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	protected void initialPage() {		
		
		logger.info("entering action::CnlTransManagerAction.initialPage()...");
		
		// 渠道来源标识下拉框的值
		cnlCnlList = cnlTrans3mBiz.getCnlSourceList();
		cnlCnlRender = SelectRenderUtils.toRenderString(cnlCnlList);
		
		// 交易类型下拉框的值
		transTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		transTypeRender = SelectRenderUtils.toRenderString(transTypeList);
		
		// 交易方向下拉框的值
		transDcList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		transDcRender = SelectRenderUtils.toRenderString(transDcList);
		
		// 交易状态下拉框的值
		transStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		transStatusRender = SelectRenderUtils.toRenderString(transStatusList);
		
		// 币种
		transCurrencyList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		transCurrencyListRender = SelectRenderUtils.toRenderString(transCurrencyList);
		
		// 支付通道
		bankPmtCnlTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL);
		bankPmtCnlTypeRender = SelectRenderUtils.toRenderString(bankPmtCnlTypeList);
		
		// 开始时间和结束时间文本框的默认值
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = format.format(now) + " " + "00:00:00";
		String endTime = format.format(now) + " " + "23:59:59";
		this.setStartTime(startTime); 
		this.setEndTime(endTime);
		
		// 银行处理优先级 list中用code value值对
		bankHandlePrioriryList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY);
		bankHandlePrioriryRender = SelectRenderUtils.toRenderString(bankHandlePrioriryList);
		
		// 终端类型  list中用code value值对
		tradeTerminalTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TERMIAL_TYPE);
		tradeTerminalTypeRender = SelectRenderUtils.toRenderString(tradeTerminalTypeList);
		
		// 入账标志  list中用code value值对
		isInGlList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__ISIN_GL);
		isInGlRender = SelectRenderUtils.toRenderString(isInGlList);
		
		// 打印标志 list中用code value值对
		isPrintedList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__IS_PRINTED);
		isPrintedRender = SelectRenderUtils.toRenderString(isPrintedList);
		
	}

	/** 
	 * 显示当前交易流水页面
	 * @param 无
	 * @return "success"
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	@Override
	public String list() {
		
		logger.info("entering action::CnlTransManagerAction.list()...");
		
		// 页面初期化
		initialPage();
		
		return SUCCESS;
	}
	
	/** 
	 * 在当前交易流水页面中，点击[查询]按钮执行查询的方法
	 * @param 无
	 * @return "ajax"
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	public String search() {

		logger.info("entering action::CnlTransManagerAction.search()...");
		
		// 获取查询参数，并设置到queryPage
		setQueryCondition();
		
		// 调用cnlTransBiz的findCnlTransByPage(queryPage)方法执行查询，并返回结果
		Page<CnlTrans> result = (Page<CnlTrans>)cnlTransBiz.findCnlTransByPage(queryPage);
		
		// 调用setResults方法对上一步查询结果进行处理
		setResults(result);
		
		// 返回"ajax"
		return AJAX_RETURN_TYPE;
	}
	
	/** 
	 * 对查询结果进行处理,将查询结果中的要求转帐日期和要求转帐时间转换成要求的格式
	 * @param result 查询结果
	 * @return 无
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	private void setResults(Page<CnlTrans> result) {
		
		logger.info("entering action::CnlTransManagerAction.setResults()...");
		
		// 定义日期时间格式format
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Iterator<CnlTrans> it = result.getRows().iterator();
		
		while(it.hasNext()) {
			CnlTrans o = (CnlTrans) it.next();
			
			// 当结果中的要求转帐日期不为null时，将其转换成yyyy-MM-dd HH:mm:ss的格式
			String bankReqTrnasDate = (o.getBankReqTrnasDate() == null)? "" :format.format(o.getBankReqTrnasDate());
			
			// 当结果中的要求转帐时间不为null时，将其转换成yyyy-MM-dd HH:mm:ss的格式
			String bankReqTransTime = (o.getBankReqTransTime() == null)? "" :format.format(o.getBankReqTransTime());
			
			// 要求转帐日期只取yyyy-MM-dd部分
			if(! "".equals(bankReqTrnasDate)){
				o.setBankReqTrnasDateString((bankReqTrnasDate.split(" "))[0]);
			} else {
				o.setBankReqTrnasDateString("");
			}
			
			// 要求转帐时间只取HH:mm:ss部分
			if(! "".equals(bankReqTransTime)){
				o.setBankReqTransTimeString((bankReqTransTime.split(" "))[1]);
			} else {
				o.setBankReqTransTimeString("");
			}
		}
		super.setResult(result);
	}
	
	/** 
	 * 将查询结果导出到excel文件中
	 * @param 无
	 * @return "success"
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	public String export() {
		
		logger.info("entering action::CnlTransManagerAction.export()...");

        // 获取查询参数，并设置到queryPage
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("当前交易流水.xls");

		// 执行查询,并设置结果
		cnlTransBiz.exportCnlTrans(exportSetting);

		// 设置导出文件
		return SUCCESS;
	}

	/** 
	 * 获取查询参数，并设置到queryPage
	 * @param 无
	 * @return 无
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	public void setQueryCondition() {
		logger.info("entering action::CnlTransManagerAction.setQueryCondition()...");

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
		
		// 渠道名称（渠道来源标识）
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

		// 系统交易流水号
		String transNum = getSearchFields().get("transNum");
		if (StringUtils.isNotEmpty(transNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transNum", "%" + transNum + "%");
				queryPage.addLikeSearch("transNum", transNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transNum", "%" + transNum + "%");
				exportSetting.addLikeSearch("transNum", transNum);
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
		
		// 渠道申请单号
		String transOrderNum = getSearchFields().get("transOrderNum");
		if (StringUtils.isNotEmpty(transOrderNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transOrderNum", "%" + transOrderNum + "%");
				queryPage.addLikeSearch("transOrderNum", transOrderNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transOrderNum", "%" + transOrderNum + "%");
				exportSetting.addLikeSearch("transOrderNum", transOrderNum);
			}
		}
		
		// 银行流水号
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
		
		// 时间类型
		String timeType = getSearchFields().get("timeType");
		String dbColumn = "";
		if(StringUtils.isNotEmpty(timeType)) {
			if("1".equals(timeType)) {
				// 交易时间
				dbColumn = "transTime";
			} else if ("2".equals(timeType)) {
				// 银行网关进入时间
				dbColumn = "bankAccepteTime";
			} else if("3".equals(timeType)) {
				// 到帐时间
				dbColumn = "bankReturnTime";
			}
			
			// 开始时间
			String startTime = getSearchFields().get("startTime");
			if(StringUtils.isNotEmpty(startTime)) {
				if(null != queryPage) {
					queryPage.addQueryCondition(dbColumn, Timestamp.valueOf(startTime));
					queryPage.addGreatEqualSearch(dbColumn, Timestamp.valueOf(startTime));
				}
				if(null != exportSetting) {
					exportSetting.addQueryCondition(dbColumn, Timestamp.valueOf(startTime));
					exportSetting.addGreatEqualSearch(dbColumn, Timestamp.valueOf(startTime));
				}
			}
			
			// 结束时间
			String endTime = getSearchFields().get("endTime");
			if(StringUtils.isNotEmpty(endTime)) {
				if(null != queryPage) {
					queryPage.addQueryCondition(dbColumn, Timestamp.valueOf(endTime));
					queryPage.addLessEqualSearch(dbColumn, Timestamp.valueOf(endTime));
				}
				if(null != exportSetting) {
					exportSetting.addQueryCondition(dbColumn, Timestamp.valueOf(endTime));
					exportSetting.addLessEqualSearch(dbColumn, Timestamp.valueOf(endTime));
				}
			}
		}
			 
		// 交易类型
		String transType = getSearchFields().get("transType");
		if(StringUtils.isNotEmpty(transType)) {
			if(null != queryPage) {
				queryPage.addQueryCondition("transType", transType);
				queryPage.addEqualSearch("transType", transType);
			}
			if(null != exportSetting) {
				exportSetting.addQueryCondition("transType", transType);
				exportSetting.addEqualSearch("transType", transType);
			}
		}
		
		// 交易方向
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
		
		// 是否有效
		if (null != queryPage) {
			queryPage.addQueryCondition("isValid", Constants.IS_VALID_VALID);
			queryPage.addEqualSearch("isValid", Constants.IS_VALID_VALID);
		}
		if (null != exportSetting) {
			exportSetting.addQueryCondition("isValid", Constants.IS_VALID_VALID);
			exportSetting.addEqualSearch("isValid", Constants.IS_VALID_VALID);
		}
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlTransManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlTransManagerAction.modify()...");
		cnlTrans = cnlTransBiz.getCnlTransById(id);
		return SUCCESS;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CnlTransManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlTransBiz.saveCnlTrans(cnlTrans);
		}
		// 如果是修改
		else {
			cnlTransBiz.updateCnlTrans(cnlTrans);
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
		logger.info("entering action::CnlTransManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	public ICnlTransBiz getCnlTransBiz() {
		logger.info("entering action::CnlTransManagerAction.getCnlTransBiz()...");
		return cnlTransBiz;
	}
	
	public void setCnlTransBiz(ICnlTransBiz cnlTransBiz) {
		logger.info("entering action::CnlTransManagerAction.setCnlTransBiz()...");
		this.cnlTransBiz = cnlTransBiz;
	}
	
	public CnlTrans getCnlTrans() {
		logger.info("entering action::CnlTransManagerAction.getCnlTrans()...");
		if (cnlTrans == null) {
			cnlTrans = new CnlTrans();
		}
		return cnlTrans;
	}
	
	public void setCnlTrans(CnlTrans cnlTrans) {
		logger.info("entering action::CnlTransManagerAction.setCnlTrans()...");
		this.cnlTrans = cnlTrans;
	}
	
	public String getId() {
		logger.info("entering action::CnlTransManagerAction.getId()...");
		return id;
	}
	
	public void setId(String id) {
		logger.info("entering action::CnlTransManagerAction.setId()...");
		this.id = id;
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
	
	public List<OptionObjectPair> getBankHandlePrioriryList() {
		return bankHandlePrioriryList;
	}
	
	public void setBankHandlePrioriryList(List<OptionObjectPair> bankHandlePrioriryList) {
		this.bankHandlePrioriryList = bankHandlePrioriryList;
	}
	
	public List<OptionObjectPair> getTradeTerminalTypeList() {
		return tradeTerminalTypeList;
	}
	
	public void setTradeTerminalTypeList(List<OptionObjectPair> tradeTerminalTypeList) {
		this.tradeTerminalTypeList = tradeTerminalTypeList;
	}
	
	public List<OptionObjectPair> getIsInGlList() {
		return isInGlList;
	}
	
	public void setIsInGlList(List<OptionObjectPair> isInGlList) {
		this.isInGlList = isInGlList;
	}
	
	public List<OptionObjectPair> getIsPrintedList() {
		return isPrintedList;
	}
	
	public void setIsPrintedList(List<OptionObjectPair> isPrintedList) {
		this.isPrintedList = isPrintedList;
	}
	
	public String getStartTime() {
		logger.info("entering action::CnlTransManagerAction.getStartTime()...");
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		logger.info("entering action::CnlTransManagerAction.setStartTime()...");
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		logger.info("entering action::CnlTransManagerAction.getEndTime()...");
		return endTime;
	}

	public void setEndTime(String endTime) {
		logger.info("entering action::CnlTransManagerAction.setEndTime()...");
		this.endTime = endTime;
	}
	
	public String getTransDcRender() {
		return transDcRender;
	}
	
	public void setTransDcRender(String transDcRender) {
		this.transDcRender = transDcRender;
	}
	
	public List<OptionObjectPair> getTransStatusList() {
		return transStatusList;
	}
	
	public void setTransStatusList(List<OptionObjectPair> transStatusList) {
		this.transStatusList = transStatusList;
	}
	
	public String getTransStatusRender() {
		return transStatusRender;
	}
	
	public void setTransStatusRender(String transStatusRender) {
		this.transStatusRender = transStatusRender;
	}
	
	public String getTransTypeRender() {
		return transTypeRender;
	}
	
	public void setTransTypeRender(String transTypeRender) {
		this.transTypeRender = transTypeRender;
	}
	
	public String getBankHandlePrioriryRender() {
		return bankHandlePrioriryRender;
	}
	
	public void setBankHandlePrioriryRender(String bankHandlePrioriryRender) {
		this.bankHandlePrioriryRender = bankHandlePrioriryRender;
	}
	
	public String getTradeTerminalTypeRender() {
		return tradeTerminalTypeRender;
	}
	
	public void setTradeTerminalTypeRender(String tradeTerminalTypeRender) {
		this.tradeTerminalTypeRender = tradeTerminalTypeRender;
	}
	
	public String getIsInGlRender() {
		return isInGlRender;
	}
	
	public void setIsInGlRender(String isInGlRender) {
		this.isInGlRender = isInGlRender;
	}
	
	public String getIsPrintedRender() {
		return isPrintedRender;
	}
	
	public void setIsPrintedRender(String isPrintedRender) {
		this.isPrintedRender = isPrintedRender;
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
	
	public List<OptionObjectPair> getCnlCnlList() {
		return cnlCnlList;
	}
	
	public void setCnlCnlList(List<OptionObjectPair> cnlCnlList) {
		this.cnlCnlList = cnlCnlList;
	}
	
	public ICnlTrans3mBiz getCnlTrans3mBiz() {
		return cnlTrans3mBiz;
	}
	
	public void setCnlTrans3mBiz(ICnlTrans3mBiz cnlTrans3mBiz) {
		this.cnlTrans3mBiz = cnlTrans3mBiz;
	}

	public String getCnlCnlRender() {
		return cnlCnlRender;
	}

	public void setCnlCnlRender(String cnlCnlRender) {
		this.cnlCnlRender = cnlCnlRender;
	}
	
	public List<OptionObjectPair> getTransCurrencyList() {
		return transCurrencyList;
	}

	public void setTransCurrencyList(List<OptionObjectPair> transCurrencyList) {
		this.transCurrencyList = transCurrencyList;
	}

	public String getTransCurrencyListRender() {
		return transCurrencyListRender;
	}

	public void setTransCurrencyListRender(String transCurrencyListRender) {
		this.transCurrencyListRender = transCurrencyListRender;
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
}
