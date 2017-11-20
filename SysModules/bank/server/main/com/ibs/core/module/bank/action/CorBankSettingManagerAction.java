/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.bank.biz.ICorBankSettingBiz;
import com.ibs.core.module.bank.domain.CorBankSetting;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CorBankSettingManagerAction extends CrudBaseAction {
	////
	private ICorBankSettingBiz corBankSettingBiz;
	
	private String id;
	private CorBankSetting corBankSetting;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CorBankSettingManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CorBankSettingManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CorBankSettingManagerAction.modify()...");
		corBankSetting = corBankSettingBiz.getCorBankSettingById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CorBankSettingManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CorBankSetting> result = (Page<CorBankSetting>) corBankSettingBiz
				.findCorBankSettingByPage(queryPage);
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
		logger.info("entering action::CorBankSettingManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			corBankSettingBiz.saveCorBankSetting(corBankSetting);
		}
		// 如果是修改
		else {
			corBankSettingBiz.updateCorBankSetting(corBankSetting);
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
		logger.info("entering action::CorBankSettingManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CorBankSettingManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		corBankSettingBiz.exportCorBankSetting(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CorBankSettingManagerAction.setQueryCondition()...");


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


		// BANK_INNER_CODE
		String bankInnerCode = getSearchFields().get("bankInnerCode");
		if (StringUtils.isNotEmpty(bankInnerCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankInnerCode", "%" + bankInnerCode + "%");
				queryPage.addLikeSearch("bankInnerCode", bankInnerCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankInnerCode", "%" + bankInnerCode + "%");
				exportSetting.addLikeSearch("bankInnerCode", bankInnerCode);
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


		// BANK_LEVEL
		String bankLevel = getSearchFields().get("bankLevel");
		if (StringUtils.isNotEmpty(bankLevel)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankLevel", "%" + bankLevel + "%");
				queryPage.addLikeSearch("bankLevel", bankLevel);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankLevel", "%" + bankLevel + "%");
				exportSetting.addLikeSearch("bankLevel", bankLevel);
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


		// DESC
		String desc = getSearchFields().get("desc");
		if (StringUtils.isNotEmpty(desc)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("desc", "%" + desc + "%");
				queryPage.addLikeSearch("desc", desc);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("desc", "%" + desc + "%");
				exportSetting.addLikeSearch("desc", desc);
			}
		}


		// BANK_ADDR
		String bankAddr = getSearchFields().get("bankAddr");
		if (StringUtils.isNotEmpty(bankAddr)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankAddr", "%" + bankAddr + "%");
				queryPage.addLikeSearch("bankAddr", bankAddr);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankAddr", "%" + bankAddr + "%");
				exportSetting.addLikeSearch("bankAddr", bankAddr);
			}
		}


		// CONTRACT_EFFECT_DATE
		String contractEffectDate = getSearchFields().get("contractEffectDate");
		if (StringUtils.isNotEmpty(contractEffectDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("contractEffectDate",
						DateUtils.convert(contractEffectDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("contractEffectDate",
						DateUtils.convert(contractEffectDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// CONTRACT_EXPIRE_DATE
		String contractExpireDate = getSearchFields().get("contractExpireDate");
		if (StringUtils.isNotEmpty(contractExpireDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("contractExpireDate",
						DateUtils.convert(contractExpireDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("contractExpireDate",
						DateUtils.convert(contractExpireDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// SWIFT_CODE
		String swiftCode = getSearchFields().get("swiftCode");
		if (StringUtils.isNotEmpty(swiftCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("swiftCode", "%" + swiftCode + "%");
				queryPage.addLikeSearch("swiftCode", swiftCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("swiftCode", "%" + swiftCode + "%");
				exportSetting.addLikeSearch("swiftCode", swiftCode);
			}
		}


		// BRANCH_CODE
		String branchCode = getSearchFields().get("branchCode");
		if (StringUtils.isNotEmpty(branchCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("branchCode", "%" + branchCode + "%");
				queryPage.addLikeSearch("branchCode", branchCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("branchCode", "%" + branchCode + "%");
				exportSetting.addLikeSearch("branchCode", branchCode);
			}
		}


		// NGSN_CODE
		String ngsnCode = getSearchFields().get("ngsnCode");
		if (StringUtils.isNotEmpty(ngsnCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("ngsnCode", "%" + ngsnCode + "%");
				queryPage.addLikeSearch("ngsnCode", ngsnCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("ngsnCode", "%" + ngsnCode + "%");
				exportSetting.addLikeSearch("ngsnCode", ngsnCode);
			}
		}


		// SINGLE_PAY_LIMIT
		if(StringUtils.isNotEmpty(getSearchFields().get("singlePayLimit"))){
		Long singlePayLimit = Long.parseLong(getSearchFields().get("singlePayLimit"));
		if (null != singlePayLimit) {
			if (null != queryPage) {
				queryPage.addQueryCondition("singlePayLimit", singlePayLimit);
				queryPage.addEqualSearch("singlePayLimit", singlePayLimit);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("singlePayLimit", singlePayLimit);
				queryPage.addEqualSearch("singlePayLimit", singlePayLimit);
			}
		}
		}

		// MONTH_PAY_LIMIT
		if(StringUtils.isNotEmpty(getSearchFields().get("monthPayLimit"))){
		Long monthPayLimit = Long.parseLong(getSearchFields().get("monthPayLimit"));
		
		if (null != monthPayLimit) {
			if (null != queryPage) {
				queryPage.addQueryCondition("monthPayLimit", monthPayLimit);
				queryPage.addEqualSearch("monthPayLimit", monthPayLimit);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("monthPayLimit", monthPayLimit);
				queryPage.addEqualSearch("monthPayLimit", monthPayLimit);
			}
		}
		}

		// YEAR_PAY_LIMIT
		if(StringUtils.isNotEmpty(getSearchFields().get("yearPayLimit"))){
		Long yearPayLimit = Long.parseLong(getSearchFields().get("yearPayLimit"));
		if (null != yearPayLimit) {
			if (null != queryPage) {
				queryPage.addQueryCondition("yearPayLimit", yearPayLimit);
				queryPage.addEqualSearch("yearPayLimit", yearPayLimit);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("yearPayLimit", yearPayLimit);
				queryPage.addEqualSearch("yearPayLimit", yearPayLimit);
			}
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

	public ICorBankSettingBiz getCorBankSettingBiz() {
		logger.info("entering action::CorBankSettingManagerAction.getCorBankSettingBiz()...");
		return corBankSettingBiz;
	}

	public void setCorBankSettingBiz(ICorBankSettingBiz corBankSettingBiz) {
		logger.info("entering action::CorBankSettingManagerAction.setCorBankSettingBiz()...");
		this.corBankSettingBiz = corBankSettingBiz;
	}

	public CorBankSetting getCorBankSetting() {
		logger.info("entering action::CorBankSettingManagerAction.getCorBankSetting()...");
		return corBankSetting;
	}

	public void setCorBankSetting(CorBankSetting corBankSetting) {
		logger.info("entering action::CorBankSettingManagerAction.setCorBankSetting()...");
		this.corBankSetting = corBankSetting;
	}

	public String getId() {
		logger.info("entering action::CorBankSettingManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CorBankSettingManagerAction.setId()...");
		this.id = id;
	}

}
