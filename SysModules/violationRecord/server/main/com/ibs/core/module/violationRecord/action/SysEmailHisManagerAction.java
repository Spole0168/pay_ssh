/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.violationRecord.biz.ISysEmailHisBiz;
import com.ibs.core.module.violationRecord.domain.SysEmailHis;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_EMAIL_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class SysEmailHisManagerAction extends CrudBaseAction {

	private ISysEmailHisBiz sysEmailHisBiz;
	
	private String id;
	private SysEmailHis sysEmailHis;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::SysEmailHisManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::SysEmailHisManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::SysEmailHisManagerAction.modify()...");
		sysEmailHis = sysEmailHisBiz.getSysEmailHisById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::SysEmailHisManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<SysEmailHis> result = (Page<SysEmailHis>) sysEmailHisBiz
				.findSysEmailHisByPage(queryPage);
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
		logger.info("entering action::SysEmailHisManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			sysEmailHisBiz.saveSysEmailHis(sysEmailHis);
		}
		// 如果是修改
		else {
			sysEmailHisBiz.updateSysEmailHis(sysEmailHis);
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
		logger.info("entering action::SysEmailHisManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::SysEmailHisManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		sysEmailHisBiz.exportSysEmailHis(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::SysEmailHisManagerAction.setQueryCondition()...");


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


		// EMAIL_ADDR
		String emailAddr = getSearchFields().get("emailAddr");
		if (StringUtils.isNotEmpty(emailAddr)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("emailAddr", "%" + emailAddr + "%");
				queryPage.addLikeSearch("emailAddr", emailAddr);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("emailAddr", "%" + emailAddr + "%");
				exportSetting.addLikeSearch("emailAddr", emailAddr);
			}
		}


		// TPL_CODE
		String tplCode = getSearchFields().get("tplCode");
		if (StringUtils.isNotEmpty(tplCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("tplCode", "%" + tplCode + "%");
				queryPage.addLikeSearch("tplCode", tplCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("tplCode", "%" + tplCode + "%");
				exportSetting.addLikeSearch("tplCode", tplCode);
			}
		}


		// EMAIL_CNL_CODE
		String emailCnlCode = getSearchFields().get("emailCnlCode");
		if (StringUtils.isNotEmpty(emailCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("emailCnlCode", "%" + emailCnlCode + "%");
				queryPage.addLikeSearch("emailCnlCode", emailCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("emailCnlCode", "%" + emailCnlCode + "%");
				exportSetting.addLikeSearch("emailCnlCode", emailCnlCode);
			}
		}


		// SMS_TYPE
		String smsType = getSearchFields().get("smsType");
		if (StringUtils.isNotEmpty(smsType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("smsType", "%" + smsType + "%");
				queryPage.addLikeSearch("smsType", smsType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("smsType", "%" + smsType + "%");
				exportSetting.addLikeSearch("smsType", smsType);
			}
		}


		// SMS_STATUS
		String smsStatus = getSearchFields().get("smsStatus");
		if (StringUtils.isNotEmpty(smsStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("smsStatus", "%" + smsStatus + "%");
				queryPage.addLikeSearch("smsStatus", smsStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("smsStatus", "%" + smsStatus + "%");
				exportSetting.addLikeSearch("smsStatus", smsStatus);
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

	public ISysEmailHisBiz getSysEmailHisBiz() {
		logger.info("entering action::SysEmailHisManagerAction.getSysEmailHisBiz()...");
		return sysEmailHisBiz;
	}

	public void setSysEmailHisBiz(ISysEmailHisBiz sysEmailHisBiz) {
		logger.info("entering action::SysEmailHisManagerAction.setSysEmailHisBiz()...");
		this.sysEmailHisBiz = sysEmailHisBiz;
	}

	public SysEmailHis getSysEmailHis() {
		logger.info("entering action::SysEmailHisManagerAction.getSysEmailHis()...");
		return sysEmailHis;
	}

	public void setSysEmailHis(SysEmailHis sysEmailHis) {
		logger.info("entering action::SysEmailHisManagerAction.setSysEmailHis()...");
		this.sysEmailHis = sysEmailHis;
	}

	public String getId() {
		logger.info("entering action::SysEmailHisManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::SysEmailHisManagerAction.setId()...");
		this.id = id;
	}

}
