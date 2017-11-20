/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.cnltrans.biz.ICnlSmsValidCodeBiz;
import com.ibs.core.module.cnltrans.domain.CnlSmsValidCode;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_SMS_VALID_CODE
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlSmsValidCodeManagerAction extends CrudBaseAction {

	private ICnlSmsValidCodeBiz cnlSmsValidCodeBiz;
	
	private String id;
	private CnlSmsValidCode cnlSmsValidCode;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.modify()...");
		cnlSmsValidCode = cnlSmsValidCodeBiz.getCnlSmsValidCodeById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlSmsValidCode> result = (Page<CnlSmsValidCode>) cnlSmsValidCodeBiz
				.findCnlSmsValidCodeByPage(queryPage);
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
		logger.info("entering action::CnlSmsValidCodeManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlSmsValidCodeBiz.saveCnlSmsValidCode(cnlSmsValidCode);
		}
		// 如果是修改
		else {
			cnlSmsValidCodeBiz.updateCnlSmsValidCode(cnlSmsValidCode);
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
		logger.info("entering action::CnlSmsValidCodeManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlSmsValidCodeBiz.exportCnlSmsValidCode(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.setQueryCondition()...");


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


		// VALID_NUM
		String validNum = getSearchFields().get("validNum");
		if (StringUtils.isNotEmpty(validNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("validNum", "%" + validNum + "%");
				queryPage.addLikeSearch("validNum", validNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("validNum", "%" + validNum + "%");
				exportSetting.addLikeSearch("validNum", validNum);
			}
		}


		// VALID_CODE
		String validCode = getSearchFields().get("validCode");
		if (StringUtils.isNotEmpty(validCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("validCode", "%" + validCode + "%");
				queryPage.addLikeSearch("validCode", validCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("validCode", "%" + validCode + "%");
				exportSetting.addLikeSearch("validCode", validCode);
			}
		}


		// EXPIRE_DATE
		String expireDate = getSearchFields().get("expireDate");
		if (StringUtils.isNotEmpty(expireDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("expireDate",
						DateUtils.convert(expireDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("expireDate",
						DateUtils.convert(expireDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// TYPE
		String type = getSearchFields().get("type");
		if (StringUtils.isNotEmpty(type)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("type", "%" + type + "%");
				queryPage.addLikeSearch("type", type);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("type", "%" + type + "%");
				exportSetting.addLikeSearch("type", type);
			}
		}


		// RESULT
		String result = getSearchFields().get("result");
		if (StringUtils.isNotEmpty(result)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("result", "%" + result + "%");
				queryPage.addLikeSearch("result", result);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("result", "%" + result + "%");
				exportSetting.addLikeSearch("result", result);
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

	public ICnlSmsValidCodeBiz getCnlSmsValidCodeBiz() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.getCnlSmsValidCodeBiz()...");
		return cnlSmsValidCodeBiz;
	}

	public void setCnlSmsValidCodeBiz(ICnlSmsValidCodeBiz cnlSmsValidCodeBiz) {
		logger.info("entering action::CnlSmsValidCodeManagerAction.setCnlSmsValidCodeBiz()...");
		this.cnlSmsValidCodeBiz = cnlSmsValidCodeBiz;
	}

	public CnlSmsValidCode getCnlSmsValidCode() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.getCnlSmsValidCode()...");
		return cnlSmsValidCode;
	}

	public void setCnlSmsValidCode(CnlSmsValidCode cnlSmsValidCode) {
		logger.info("entering action::CnlSmsValidCodeManagerAction.setCnlSmsValidCode()...");
		this.cnlSmsValidCode = cnlSmsValidCode;
	}

	public String getId() {
		logger.info("entering action::CnlSmsValidCodeManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlSmsValidCodeManagerAction.setId()...");
		this.id = id;
	}

}
