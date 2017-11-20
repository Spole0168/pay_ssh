/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.reservedfund.action;

import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.reservedfund.biz.ITcorReservedFundAcntBiz;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_RESERVED_FUND_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CorReservedFundAcntManagerAction extends CrudBaseAction {

	private ITcorReservedFundAcntBiz corReservedFundAcntBiz;
	
	private String id;
	private CorReservedFundAcnt corReservedFundAcnt;

	
	private List<OptionObjectPair> bankNameList;
	private List<OptionObjectPair> statusList;
	private List<OptionObjectPair> currencyList;
	private IDataDictBiz dataDictBiz;
	private IDataDictService dataDictService;
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CorReservedFundAcntManagerAction.list()...");
		 bankNameList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CorReservedFundAcntManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CorReservedFundAcntManagerAction.modify()...");
		corReservedFundAcnt = corReservedFundAcntBiz.getCorReservedFundAcntById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		
		logger.info("entering action::CorReservedFundAcntManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();
		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CorReservedFundAcnt> result = (Page<CorReservedFundAcnt>) corReservedFundAcntBiz
				.findCorReservedFundAcntByPage(queryPage);
		
		Collection<CorReservedFundAcnt> cnlTransTraceColl = result.getRows();
		List<DataDict> bankNameList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME);// 银行名称
		List<DataDict> statusList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__ACNT_STATUS);
		List<DataDict> currencyList = dataDictService.list(IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);//币种
		try {								
			CollectionUtils.transformCollection(cnlTransTraceColl, "bankName","bankName", bankNameList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "status","status", statusList, "code", "name");
			CollectionUtils.transformCollection(cnlTransTraceColl, "currency","currency", currencyList, "code", "name");
		} catch (Exception e) {								
			throw new BizException(e.getMessage());							
		}
		
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
		logger.info("entering action::CorReservedFundAcntManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			corReservedFundAcntBiz.saveCorReservedFundAcnt(corReservedFundAcnt);
		}
		// 如果是修改
		else {
			corReservedFundAcntBiz.updateCorReservedFundAcnt(corReservedFundAcnt);
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
		logger.info("entering action::CorReservedFundAcntManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CorReservedFundAcntManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		corReservedFundAcntBiz.exportCorReservedFundAcnt(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CorReservedFundAcntManagerAction.setQueryCondition()...");


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


		// BANK_ACNT_CODE
		String bankAcntCode = getSearchFields().get("bankAcntCode");
		if (StringUtils.isNotEmpty(bankAcntCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankAcntCode", "%" + bankAcntCode + "%");
				queryPage.addLikeSearch("bankAcntCode", bankAcntCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankAcntCode", "%" + bankAcntCode + "%");
				exportSetting.addLikeSearch("bankAcntCode", bankAcntCode);
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


		// COUNTRY
		String country = getSearchFields().get("country");
		if (StringUtils.isNotEmpty(country)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("country", "%" + country + "%");
				queryPage.addLikeSearch("country", country);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("country", "%" + country + "%");
				exportSetting.addLikeSearch("country", country);
			}
		}


		// CUST_NAME
		String custName = getSearchFields().get("custName");
		if (StringUtils.isNotEmpty(custName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("custName", "%" + custName + "%");
				queryPage.addLikeSearch("custName", custName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("custName", "%" + custName + "%");
				exportSetting.addLikeSearch("custName", custName);
			}
		}


		// CERT_TYPE
		String certType = getSearchFields().get("certType");
		if (StringUtils.isNotEmpty(certType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("certType", "%" + certType + "%");
				queryPage.addLikeSearch("certType", certType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("certType", "%" + certType + "%");
				exportSetting.addLikeSearch("certType", certType);
			}
		}


		// CERT_NUM
		String certNum = getSearchFields().get("certNum");
		if (StringUtils.isNotEmpty(certNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("certNum", "%" + certNum + "%");
				queryPage.addLikeSearch("certNum", certNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("certNum", "%" + certNum + "%");
				exportSetting.addLikeSearch("certNum", certNum);
			}
		}


		// BANK_NAME
		String bankName = getSearchFields().get("bankName");
		if (StringUtils.isNotEmpty(bankName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankName", "%" + bankName + "%");
				queryPage.addLikeSearch("bankName", bankName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankName", "%" + bankName + "%");
				exportSetting.addLikeSearch("bankName", bankName);
			}
		}


		// BANK_CODE
		String bankCode = getSearchFields().get("bankCode");
		if (StringUtils.isNotEmpty(bankCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCode", "%" + bankCode + "%");
				queryPage.addLikeSearch("bankCode", bankCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCode", "%" + bankCode + "%");
				exportSetting.addLikeSearch("bankCode", bankCode);
			}
		}


		// BANK_BRANCH_CODE
		String bankBranchCode = getSearchFields().get("bankBranchCode");
		if (StringUtils.isNotEmpty(bankBranchCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankBranchCode", "%" + bankBranchCode + "%");
				queryPage.addLikeSearch("bankBranchCode", bankBranchCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankBranchCode", "%" + bankBranchCode + "%");
				exportSetting.addLikeSearch("bankBranchCode", bankBranchCode);
			}
		}


		// BANK_BRANCH_NAME
		String bankBranchName = getSearchFields().get("bankBranchName");
		if (StringUtils.isNotEmpty(bankBranchName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankBranchName", "%" + bankBranchName + "%");
				queryPage.addLikeSearch("bankBranchName", bankBranchName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankBranchName", "%" + bankBranchName + "%");
				exportSetting.addLikeSearch("bankBranchName", bankBranchName);
			}
		}


		// BANK_CARD_NUM
		String bankCardNum = getSearchFields().get("bankCardNum");
		if (StringUtils.isNotEmpty(bankCardNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCardNum", "%" + bankCardNum + "%");
				queryPage.addLikeSearch("bankCardNum", bankCardNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCardNum", "%" + bankCardNum + "%");
				exportSetting.addLikeSearch("bankCardNum", bankCardNum);
			}
		}


		// BANK_CARD_TYPE
		String bankCardType = getSearchFields().get("bankCardType");
		if (StringUtils.isNotEmpty(bankCardType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCardType", "%" + bankCardType + "%");
				queryPage.addLikeSearch("bankCardType", bankCardType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCardType", "%" + bankCardType + "%");
				exportSetting.addLikeSearch("bankCardType", bankCardType);
			}
		}


		// STATUS
		String status = getSearchFields().get("status");
		if (StringUtils.isNotEmpty(status)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("status", "%" + status + "%");
				queryPage.addLikeSearch("status", status);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("status", "%" + status + "%");
				exportSetting.addLikeSearch("status", status);
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


		// ACNT_CATEGORY
		String acntCategory = getSearchFields().get("acntCategory");
		if (StringUtils.isNotEmpty(acntCategory)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("acntCategory", "%" + acntCategory + "%");
				queryPage.addLikeSearch("acntCategory", acntCategory);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("acntCategory", "%" + acntCategory + "%");
				exportSetting.addLikeSearch("acntCategory", acntCategory);
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


		// LAST_TRANS_TIME
		String lastTransTime = getSearchFields().get("lastTransTime");
		if (StringUtils.isNotEmpty(lastTransTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("lastTransTime",
						DateUtils.convert(lastTransTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("lastTransTime",
						DateUtils.convert(lastTransTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// FROZEN_AMOUMT
		String frozenAmoumt = getSearchFields().get("frozenAmoumt");
		if (null != frozenAmoumt) {
			if (null != queryPage) {
				queryPage.addQueryCondition("frozenAmoumt", frozenAmoumt);
				queryPage.addEqualSearch("frozenAmoumt", frozenAmoumt);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("frozenAmoumt", frozenAmoumt);
				queryPage.addEqualSearch("frozenAmoumt", frozenAmoumt);
			}
		}


		// AVALIABLE_AMOUNT
		String avaliableAmount = getSearchFields().get("avaliableAmount");
		if (null != avaliableAmount) {
			if (null != queryPage) {
				queryPage.addQueryCondition("avaliableAmount", avaliableAmount);
				queryPage.addEqualSearch("avaliableAmount", avaliableAmount);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("avaliableAmount", avaliableAmount);
				queryPage.addEqualSearch("avaliableAmount", avaliableAmount);
			}
		}


		// BALANCE
		String balance = getSearchFields().get("balance");
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


		// BANK_NUM
		String bankNum = getSearchFields().get("bankNum");
		if (StringUtils.isNotEmpty(bankNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankNum", "%" + bankNum + "%");
				queryPage.addLikeSearch("bankNum", bankNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankNum", "%" + bankNum + "%");
				exportSetting.addLikeSearch("bankNum", bankNum);
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

	}

	public ITcorReservedFundAcntBiz getCorReservedFundAcntBiz() {
		logger.info("entering action::CorReservedFundAcntManagerAction.getCorReservedFundAcntBiz()...");
		return corReservedFundAcntBiz;
	}

	public void setCorReservedFundAcntBiz(ITcorReservedFundAcntBiz corReservedFundAcntBiz) {
		logger.info("entering action::CorReservedFundAcntManagerAction.setCorReservedFundAcntBiz()...");
		this.corReservedFundAcntBiz = corReservedFundAcntBiz;
	}

	public CorReservedFundAcnt getCorReservedFundAcnt() {
		logger.info("entering action::CorReservedFundAcntManagerAction.getCorReservedFundAcnt()...");
		return corReservedFundAcnt;
	}

	public void setCorReservedFundAcnt(CorReservedFundAcnt corReservedFundAcnt) {
		logger.info("entering action::CorReservedFundAcntManagerAction.setCorReservedFundAcnt()...");
		this.corReservedFundAcnt = corReservedFundAcnt;
	}

	public String getId() {
		logger.info("entering action::CorReservedFundAcntManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CorReservedFundAcntManagerAction.setId()...");
		this.id = id;
	}

	public List<OptionObjectPair> getBankNameList() {
		return bankNameList;
	}

	public void setBankNameList(List<OptionObjectPair> bankNameList) {
		this.bankNameList = bankNameList;
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

	public List<OptionObjectPair> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<OptionObjectPair> statusList) {
		this.statusList = statusList;
	}

	public List<OptionObjectPair> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<OptionObjectPair> currencyList) {
		this.currencyList = currencyList;
	}

}
