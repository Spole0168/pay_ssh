/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.violationRecord.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.violationRecord.biz.ISysSmsHisBiz;
import com.ibs.core.module.violationRecord.domain.SysSmsHis;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SMS_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class SysSmsHisManagerAction extends CrudBaseAction {

	private ISysSmsHisBiz sysSmsHisBiz;
	
	private String id;
	private SysSmsHis sysSmsHis;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::SysSmsHisManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::SysSmsHisManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::SysSmsHisManagerAction.modify()...");
		sysSmsHis = sysSmsHisBiz.getSysSmsHisById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::SysSmsHisManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<SysSmsHis> result = (Page<SysSmsHis>) sysSmsHisBiz
				.findSysSmsHisByPage(queryPage);
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
		logger.info("entering action::SysSmsHisManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			sysSmsHisBiz.saveSysSmsHis(sysSmsHis);
		}
		// 如果是修改
		else {
			sysSmsHisBiz.updateSysSmsHis(sysSmsHis);
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
		logger.info("entering action::SysSmsHisManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::SysSmsHisManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		sysSmsHisBiz.exportSysSmsHis(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::SysSmsHisManagerAction.setQueryCondition()...");


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


		// PHONENUM
		String phonenum = getSearchFields().get("phonenum");
		if (StringUtils.isNotEmpty(phonenum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("phonenum", "%" + phonenum + "%");
				queryPage.addLikeSearch("phonenum", phonenum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("phonenum", "%" + phonenum + "%");
				exportSetting.addLikeSearch("phonenum", phonenum);
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


		// SMS_CONTENT
		String smsContent = getSearchFields().get("smsContent");
		if (StringUtils.isNotEmpty(smsContent)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("smsContent", "%" + smsContent + "%");
				queryPage.addLikeSearch("smsContent", smsContent);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("smsContent", "%" + smsContent + "%");
				exportSetting.addLikeSearch("smsContent", smsContent);
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


		// SMS_CNL_CODE
		String smsCnlCode = getSearchFields().get("smsCnlCode");
		if (StringUtils.isNotEmpty(smsCnlCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("smsCnlCode", "%" + smsCnlCode + "%");
				queryPage.addLikeSearch("smsCnlCode", smsCnlCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("smsCnlCode", "%" + smsCnlCode + "%");
				exportSetting.addLikeSearch("smsCnlCode", smsCnlCode);
			}
		}


		// MSG_TYPE
		String msgType = getSearchFields().get("msgType");
		if (StringUtils.isNotEmpty(msgType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgType", "%" + msgType + "%");
				queryPage.addLikeSearch("msgType", msgType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgType", "%" + msgType + "%");
				exportSetting.addLikeSearch("msgType", msgType);
			}
		}


		// MSG_STATUS
		String msgStatus = getSearchFields().get("msgStatus");
		if (StringUtils.isNotEmpty(msgStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgStatus", "%" + msgStatus + "%");
				queryPage.addLikeSearch("msgStatus", msgStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgStatus", "%" + msgStatus + "%");
				exportSetting.addLikeSearch("msgStatus", msgStatus);
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

	public ISysSmsHisBiz getSysSmsHisBiz() {
		logger.info("entering action::SysSmsHisManagerAction.getSysSmsHisBiz()...");
		return sysSmsHisBiz;
	}

	public void setSysSmsHisBiz(ISysSmsHisBiz sysSmsHisBiz) {
		logger.info("entering action::SysSmsHisManagerAction.setSysSmsHisBiz()...");
		this.sysSmsHisBiz = sysSmsHisBiz;
	}

	public SysSmsHis getSysSmsHis() {
		logger.info("entering action::SysSmsHisManagerAction.getSysSmsHis()...");
		return sysSmsHis;
	}

	public void setSysSmsHis(SysSmsHis sysSmsHis) {
		logger.info("entering action::SysSmsHisManagerAction.setSysSmsHis()...");
		this.sysSmsHis = sysSmsHis;
	}

	public String getId() {
		logger.info("entering action::SysSmsHisManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::SysSmsHisManagerAction.setId()...");
		this.id = id;
	}

}
