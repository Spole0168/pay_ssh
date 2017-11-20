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
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

/**
 * @author  	: qiyp
 * @date    	: 2016-08-11
 * @comments	: 历史交易流水查询
 */
@SuppressWarnings("serial")
public class CnlTrans3mManagerAction extends CrudBaseAction {
	
	// 渠道来源标识
	private List<OptionObjectPair> cnlList;
	private String cnlListRender;
	
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
	
	private String id;
	private CnlTrans3m cnlTrans3m;           // 历史交易流水对应的DominObject，与数据库表T_CNL_TRANS_3M对应
	private ICnlTrans3mBiz cnlTrans3mBiz;    // 历史交易流水查询Biz
	private IDataDictService dataDictService;// 数据字典Service 
	private String beginTime;                // 开始时间，与页面元素中的开始时间对应
	private String endTime;                  // 结束时间，与页面元素中的结束时间对应
	private Integer dateType;                // 时间类型，与页面元素中的时间类型对应
	
	/** 
	 * 历史交易流水查询页面,页面默认值设置，下拉框内容取值等初期化设置方法
	 * @param 无
	 * @return 无
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	private void initialListPage() {
		
		logger.info("entering action::CnlTrans3mManagerAction.initialListPage()...");
		
		// 开始时间和结束时间默认值设置
		Date now = new Date(new Date().getTime()-24*60*60*1000);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = format.format(now) + " " + "00:00:00";
		String endTime = format.format(now) + " " + "23:59:59";
		this.setBeginTime(startTime); 
		this.setEndTime(endTime); 
		
		// 交易类型下拉框的值
		transTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		transTypeRender = SelectRenderUtils.toRenderString(transTypeList);

		// 交易方向下拉框的值
		transDcList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		transDcRender = SelectRenderUtils.toRenderString(transDcList);

		// 交易状态下拉框的值
		transStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		transStatusRender = SelectRenderUtils.toRenderString(transStatusList);
		
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
		
		// 币种
		transCurrencyList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		transCurrencyListRender = SelectRenderUtils.toRenderString(transCurrencyList);
		
		// 支付通道
		bankPmtCnlTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL);
		bankPmtCnlTypeRender = SelectRenderUtils.toRenderString(bankPmtCnlTypeList);
		
		// 渠道来源标示
		cnlList = cnlTrans3mBiz.getCnlSourceList();
		cnlListRender = SelectRenderUtils.toRenderString(cnlList);
	}
	
	/** 
	 * 显示历史交易流水页面
	 * @param 无
	 * @return "success"
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	@Override
	public String list() {
		
		// 页面初期化
		initialListPage();
		
		logger.info("entering action::CnlTrans3mManagerAction.list()...");
		return SUCCESS;
	}

	/** 
	 * 在历史交易流水页面中，点击[查询]按钮执行查询的方法
	 * @param 无
	 * @return "ajax"
	 * @exception
	 * @author qiyp
	 * @Date 2016-08-11
	 */
	public String search() {

		logger.info("entering action::CnlTrans3mManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */

		Page<CnlTrans3m> result = (Page<CnlTrans3m>) cnlTrans3mBiz
				.findCnlTrans3mByPage(queryPage);

		setResults(result);

		/**
		 * 第三步，返回
		 */
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
	private void setResults(Page<CnlTrans3m> result) {
		
		logger.info("entering action::CnlTrans3mManagerAction.setResults()...");
		
		Iterator<CnlTrans3m> it = result.getRows().iterator();
		
		// 定义日期时间格式format
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		while(it.hasNext()) {
			CnlTrans3m o = (CnlTrans3m) it.next();
			
			// 当结果中的要求转帐日期不为null时，将其转换成yyyy-MM-dd HH:mm:ss的格式
			String bankReqTrnasDate = (o.getBankReqTrnasDate() == null)? "" :format.format(o.getBankReqTrnasDate());
			
			// 当结果中的要求转帐时间不为null时，将其转换成yyyy-MM-dd HH:mm:ss的格式
			String bankReqTransTime = (o.getBankReqTransTime() == null)? "" :format.format(o.getBankReqTransTime());
			
			// 要求转帐日期只取yyyy-MM-dd部分
			if(! "".equals(bankReqTrnasDate)){
				o.setStrBankReqTrnasDate((bankReqTrnasDate.split(" "))[0]);
			} else {
				o.setStrBankReqTrnasDate("");
			}
			
			// 要求转帐时间只取HH:mm:ss部分
			if(! "".equals(bankReqTransTime)){
				o.setStrBankReqTransTime((bankReqTransTime.split(" "))[1]);
			} else {
				o.setStrBankReqTransTime("");
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
		logger.info("entering action::CnlTrans3mManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("历史交易流水.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlTrans3mBiz.exportCnlTrans3m(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
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
		
		logger.info("entering action::CnlTrans3mManagerAction.setQueryCondition()...");

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
				queryPage.addQueryCondition("transNum", "%" + transNum.trim() + "%");
				queryPage.addLikeSearch("transNum", transNum.trim());
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
				queryPage.addQueryCondition("reqInnerNum", "%" + reqInnerNum.trim() + "%");
				queryPage.addLikeSearch("reqInnerNum", reqInnerNum.trim());
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
				queryPage.addQueryCondition("transOrderNum", "%" + transOrderNum.trim() + "%");
				queryPage.addLikeSearch("transOrderNum", transOrderNum.trim());
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
				queryPage.addQueryCondition("bankTransNum", "%" + bankTransNum.trim() + "%");
				queryPage.addLikeSearch("bankTransNum", bankTransNum.trim());
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankTransNum", "%" + bankTransNum + "%");
				exportSetting.addLikeSearch("bankTransNum", bankTransNum);
			}
		}
		
		// 时间类型
		String dateType = getSearchFields().get("dateType");
		String dbColumn = "";
		if(StringUtils.isNotEmpty(dateType)) {
			if("1".equals(dateType)) {
				dbColumn = "transTime";
			} else if ("2".equals(dateType)) {
				dbColumn = "bankAccepteTime";
			} else if("3".equals(dateType)) {
				dbColumn = "bankReturnTime";
			}
			
			// 开始时间
			String beginTime = getSearchFields().get("beginTime");
			if(StringUtils.isNotEmpty(beginTime)) {
				if(null != queryPage) {
					queryPage.addQueryCondition(dbColumn, Timestamp.valueOf(beginTime));
					queryPage.addGreatEqualSearch(dbColumn, Timestamp.valueOf(beginTime));
				}
				if (null != exportSetting) {
					exportSetting.addQueryCondition(dbColumn, Timestamp.valueOf(beginTime));
					exportSetting.addGreatEqualSearch(dbColumn, Timestamp.valueOf(beginTime));
				}
			}
			
			// 结束时间
			String endTime = getSearchFields().get("endTime");
			if(StringUtils.isNotEmpty(endTime)) {
				if(null != queryPage) {
					queryPage.addQueryCondition(dbColumn, Timestamp.valueOf(endTime));
					queryPage.addLessEqualSearch(dbColumn, Timestamp.valueOf(endTime));
				}
				if (null != exportSetting) {
					exportSetting.addQueryCondition(dbColumn, Timestamp.valueOf(endTime));
					exportSetting.addLessEqualSearch(dbColumn, Timestamp.valueOf(endTime));
				}
			}
		}
		
		// 交易类型
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
		logger.info("entering action::CnlTrans3mManagerAction.create()...");
		return SUCCESS;
	}

	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlTrans3mManagerAction.modify()...");
		cnlTrans3m = cnlTrans3mBiz.getCnlTrans3mById(id);
		return SUCCESS;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CnlTrans3mManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlTrans3mBiz.saveCnlTrans3m(cnlTrans3m);
		}
		// 如果是修改
		else {
			cnlTrans3mBiz.updateCnlTrans3m(cnlTrans3m);
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
		logger.info("entering action::CnlTrans3mManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	public ICnlTrans3mBiz getCnlTrans3mBiz() {
		logger.info("entering action::CnlTrans3mManagerAction.getCnlTrans3mBiz()...");
		return cnlTrans3mBiz;
	}

	public void setCnlTrans3mBiz(ICnlTrans3mBiz cnlTrans3mBiz) {
		logger.info("entering action::CnlTrans3mManagerAction.setCnlTrans3mBiz()...");
		this.cnlTrans3mBiz = cnlTrans3mBiz;
	}

	public CnlTrans3m getCnlTrans3m() {
		logger.info("entering action::CnlTrans3mManagerAction.getCnlTrans3m()...");
		return cnlTrans3m;
	}

	public void setCnlTrans3m(CnlTrans3m cnlTrans3m) {
		logger.info("entering action::CnlTrans3mManagerAction.setCnlTrans3m()...");
		this.cnlTrans3m = cnlTrans3m;
	}

	public String getId() {
		logger.info("entering action::CnlTrans3mManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlTrans3mManagerAction.setId()...");
		this.id = id;
	}

	public List<OptionObjectPair> getCnlList() {
		return cnlList;
	}

	public void setCnlList(List<OptionObjectPair> cnlList) {
		this.cnlList = cnlList;
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
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}
	
	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
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
	
	public List<OptionObjectPair> getIsInGlList() {
		return isInGlList;
	}

	public void setIsInGlList(List<OptionObjectPair> isInGlList) {
		this.isInGlList = isInGlList;
	}
	
	public List<OptionObjectPair> getTradeTerminalTypeList() {
		return tradeTerminalTypeList;
	}

	public void setTradeTerminalTypeList(List<OptionObjectPair> tradeTerminalTypeList) {
		this.tradeTerminalTypeList = tradeTerminalTypeList;
	}
	
	public List<OptionObjectPair> getBankHandlePrioriryList() {
		return bankHandlePrioriryList;
	}

	public void setBankHandlePrioriryList(List<OptionObjectPair> bankHandlePrioriryList) {
		this.bankHandlePrioriryList = bankHandlePrioriryList;
	}
	
	public List<OptionObjectPair> getTransStatusList() {
		return transStatusList;
	}

	public void setTransStatusList(List<OptionObjectPair> transStatusList) {
		this.transStatusList = transStatusList;
	}
	
	public List<OptionObjectPair> getTransTypeList() {
		return transTypeList;
	}

	public void setTransTypeList(List<OptionObjectPair> transTypeList) {
		this.transTypeList = transTypeList;
	}
	
	public List<OptionObjectPair> getTransDcList() {
		return transDcList;
	}

	public void setTransDcList(List<OptionObjectPair> transDcList) {
		this.transDcList = transDcList;
	}
	
	public List<OptionObjectPair> getIsPrintedList() {
		return isPrintedList;
	}

	public void setIsPrintedList(List<OptionObjectPair> isPrintedList) {
		this.isPrintedList = isPrintedList;
	}

	public String getCnlListRender() {
		return cnlListRender;
	}

	public void setCnlListRender(String cnlListRender) {
		this.cnlListRender = cnlListRender;
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
