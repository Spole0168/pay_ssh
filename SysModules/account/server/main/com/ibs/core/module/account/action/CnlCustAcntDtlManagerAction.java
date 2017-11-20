/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.action;

import java.util.Collection;
import java.util.List;


import com.ibs.core.module.account.biz.ICnlCustAcntDtlBiz;
import com.ibs.core.module.account.biz.ICnlTrans3mBiz;
import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlCustAcntDtlDto;
import com.ibs.core.module.account.utils.DtoUtils;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlCustAcntDtlManagerAction extends CrudBaseAction {

	private ICnlCustAcntDtlBiz cnlCustAcntDtlBiz;
	
	private String id;
	private CnlCustAcntDtl cnlCustAcntDtl;
	private IDataDictService dataDictService;
	private ICnlTrans3mBiz cnlTrans3mBiz;
	private IDataDictBiz dataDictBiz;
	private List<OptionObjectPair> custTypes;
	private List<OptionObjectPair> acntTypes;
	private List<OptionObjectPair> currencys;
	private List<OptionObjectPair> directions;
	private List<OptionObjectPair> cnlCnlCodes;
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.list()...");
		custTypes = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
		acntTypes = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__ACNT_TYPE);
		currencys = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		directions = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		cnlCnlCodes= cnlTrans3mBiz.getCnlSourceList();
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.modify()...");
		cnlCustAcntDtl = cnlCustAcntDtlBiz.getCnlCustAcntDtlById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();
		
		/**
		 * 第二步，执行查询,并设置结果
		 */
//		Page<CnlCustAcntDtl> result = (Page<CnlCustAcntDtl>) cnlCustAcntDtlBiz
//				.findCnlCustAcntDtlByPage(queryPage);
		//多表查询
		Page<CnlCustAcntDtlDto> result = (Page<CnlCustAcntDtlDto>) cnlCustAcntDtlBiz
				.findCnlCustAcntDtlByPageAndMoreTable(queryPage,getSearchCondition());	
		Collection<CnlCustAcntDtlDto> rows2 = result.getRows();
		//改变查询结果为实际意义的字符
		DtoUtils.changeToContent(rows2, dataDictService);
		/*for (CnlCustAcntDtlDto dto : rows2) {
			dto.setCurrency("人民币");
		}*/
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
		logger.info("entering action::CnlCustAcntDtlManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlCustAcntDtlBiz.saveCnlCustAcntDtl(cnlCustAcntDtl);
		}
		// 如果是修改
		else {
			cnlCustAcntDtlBiz.updateCnlCustAcntDtl(cnlCustAcntDtl);
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
		logger.info("entering action::CnlCustAcntDtlManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlCustAcntDtlBiz.exportCnlCustAcntDtl(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.setQueryCondition()...");


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


		// ACNT_DTL_CODE
		String acntDtlCode = getSearchFields().get("acntDtlCode");
		if (StringUtils.isNotEmpty(acntDtlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("acntDtlCode", "%" + acntDtlCode + "%");
				queryPage.addLikeSearch("acntDtlCode", acntDtlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("acntDtlCode", "%" + acntDtlCode + "%");
				exportSetting.addLikeSearch("acntDtlCode", acntDtlCode);
			}
		}


		// CNL_ACNT_CODE
		String cnlAcntCode = getSearchFields().get("cnlAcntCode");
		if (StringUtils.isNotEmpty(cnlAcntCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlAcntCode", "%" + cnlAcntCode + "%");
				queryPage.addLikeSearch("cnlAcntCode", cnlAcntCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlAcntCode", "%" + cnlAcntCode + "%");
				exportSetting.addLikeSearch("cnlAcntCode", cnlAcntCode);
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


		// TRANS_NUM
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


		// ACNT_TYPE
		String acntType = getSearchFields().get("acntType");
		if (StringUtils.isNotEmpty(acntType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("acntType", "%" + acntType + "%");
				queryPage.addLikeSearch("acntType", acntType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("acntType", "%" + acntType + "%");
				exportSetting.addLikeSearch("acntType", acntType);
			}
		}


		// CURRENCY
		String currency = getSearchFields().get("currency");
		if (StringUtils.isNotEmpty(currency)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("currency", "%" + currency + "%");
				queryPage.addLikeSearch("currency", currency);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("currency", "%" + currency + "%");
				exportSetting.addLikeSearch("currency", currency);
			}
		}


		// AMOUNT
		Long amount =null;
		if(getSearchFields().get("amount")!=null){
			amount= Long.parseLong(getSearchFields().get("amount"));
		}
		if (null != amount) {
			if (null != queryPage) {
				queryPage.addQueryCondition("amount", amount);
				queryPage.addEqualSearch("amount", amount);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("amount", amount);
				queryPage.addEqualSearch("amount", amount);
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

		// BALANCE
		Long balance =null;
		if(getSearchFields().get("balance")!=null){
			balance=Long.parseLong(getSearchFields().get("balance"));
		}
		if (null != balance) {
			if (null != queryPage) {
				queryPage.addQueryCondition("balance", balance);
				queryPage.addEqualSearch("balance", balance);
			}	
			if (null != exportSetting) {
				queryPage.addQueryCondition("balance", balance);
				queryPage.addEqualSearch("balance", balance);
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


		// TRANS_TIME6
		String transTime = getSearchFields().get("transTime");
		if (StringUtils.isNotEmpty(transTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("transTime",
						DateUtils.convert(transTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("transTime",
						DateUtils.convert(transTime,
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


		// VOUCHER_NUM
		String voucherNum = getSearchFields().get("voucherNum");
		if (StringUtils.isNotEmpty(voucherNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("voucherNum", "%" + voucherNum + "%");
				queryPage.addLikeSearch("voucherNum", voucherNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("voucherNum", "%" + voucherNum + "%");
				exportSetting.addLikeSearch("voucherNum", voucherNum);
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

	}

	public ICnlCustAcntDtlBiz getCnlCustAcntDtlBiz() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.getCnlCustAcntDtlBiz()...");
		return cnlCustAcntDtlBiz;
	}

	public void setCnlCustAcntDtlBiz(ICnlCustAcntDtlBiz cnlCustAcntDtlBiz) {
		logger.info("entering action::CnlCustAcntDtlManagerAction.setCnlCustAcntDtlBiz()...");
		this.cnlCustAcntDtlBiz = cnlCustAcntDtlBiz;
	}

	public CnlCustAcntDtl getCnlCustAcntDtl() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.getCnlCustAcntDtl()...");
		return cnlCustAcntDtl;
	}

	public void setCnlCustAcntDtl(CnlCustAcntDtl cnlCustAcntDtl) {
		logger.info("entering action::CnlCustAcntDtlManagerAction.setCnlCustAcntDtl()...");
		this.cnlCustAcntDtl = cnlCustAcntDtl;
	}

	public String getId() {
		logger.info("entering action::CnlCustAcntDtlManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlCustAcntDtlManagerAction.setId()...");
		this.id = id;
	}
	/**
	 * 为hql语句生成查询条件
	 */
	public String getSearchCondition(){
		StringBuffer condition = new StringBuffer();
		//清除condition
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		//acntDtlCode
		String acntDtlCode = getSearchFields().get("acntDtlCode");
		if (StringUtils.isNotEmpty(acntDtlCode)) {
			condition.append(" and d.acntDtlCode = ? ");
			queryPage.addQueryCondition("acntDtlCode", acntDtlCode);
		}
		//cnlCustType
		String cnlCustType = getSearchFields().get("cnlCustType");
		if (StringUtils.isNotEmpty(cnlCustType)) {
			condition.append(" and c.cnlCustType = '").append(cnlCustType).append("' ");
		}
		//localName
		String localName = getSearchFields().get("localName");
		if (StringUtils.isNotEmpty(localName)) {
			condition.append(" and c.localName like ? ");
			queryPage.addQueryCondition("localName", "%"+localName+"%");
		}
		//custCode
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) { 
			condition.append(" and d.cnlCustCode = ? ");
			queryPage.addQueryCondition("cnlCustCode", cnlCustCode);
		}
		//cnlCode
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) { 
			condition.append(" and c.cnlCnlCode = ? ");
			queryPage.addQueryCondition("cnlCnlCode", cnlCnlCode);
		}
		//acntType
		String acntType = getSearchFields().get("acntType");
		if (StringUtils.isNotEmpty(acntType)) {
			condition.append(" and d.acntType='").append(acntType).append("' ");
		}
		//currency
		String currency = getSearchFields().get("currency");
		if (StringUtils.isNotEmpty(currency)) {
			condition.append(" and d.currency='").append(currency).append("' ");
		}
		//voucherNum
		String voucherNum = getSearchFields().get("voucherNum");
		if (StringUtils.isNotEmpty(voucherNum)) {
			condition.append(" and d.voucherNum = ? ");
			queryPage.addQueryCondition("voucherNum", voucherNum);
		}
		//direction
		String direction = getSearchFields().get("direction");
		if (StringUtils.isNotEmpty(direction)) {
			condition.append(" and d.direction='").append(direction).append("' ");
		}
		//transNum
		String transNum = getSearchFields().get("transNum");
		if (StringUtils.isNotEmpty(transNum)) {
			condition.append(" and d.transNum = ? ");
			queryPage.addQueryCondition("transNum", transNum);
		}
		//time
		//startTransTime
		String startTime = getSearchFields().get("startTransTime");
		//endTime
		String endTime = getSearchFields().get("endTransTime");
		condition.append(" and d.createTime>=? and d.createTime<=? ");
		queryPage.addQueryCondition("startTime", DateUtils.convert(startTime, DateUtils.DATE_TIME_FORMAT));
		queryPage.addQueryCondition("endTime",DateUtils.convert(endTime, DateUtils.DATE_TIME_FORMAT));
		//isPrinted
//		String isPrinted = getSearchFields().get("isPrinted");
//		if (StringUtils.isNotEmpty(isPrinted)) {
//			condition.append(" and d.isPrinted ='").append(isPrinted).append("' ");
//		}
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
	public List<OptionObjectPair> getAcntTypes() {
		return acntTypes;
	}

	public void setAcntTypes(List<OptionObjectPair> acntTypes) {
		this.acntTypes = acntTypes;
	}

	public List<OptionObjectPair> getCurrencys() {
		return currencys;
	}

	public void setCurrencys(List<OptionObjectPair> currencys) {
		this.currencys = currencys;
	}

	public List<OptionObjectPair> getDirections() {
		return directions;
	}

	public void setDirections(List<OptionObjectPair> directions) {
		this.directions = directions;
	}

	public List<OptionObjectPair> getCustTypes() {
		return custTypes;
	}

	public void setCustTypes(List<OptionObjectPair> custTypes) {
		this.custTypes = custTypes;
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
	
}
