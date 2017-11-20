/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.account.biz.ICnlCustTraceBiz;
import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlCustTraceManagerAction extends CrudBaseAction {

	private ICnlCustTraceBiz cnlCustTraceBiz;
	
	private String id;
	private CnlCustTrace cnlCustTrace;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlCustTraceManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlCustTraceManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlCustTraceManagerAction.modify()...");
		cnlCustTrace = cnlCustTraceBiz.getCnlCustTraceById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlCustTraceManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlCustTrace> result = (Page<CnlCustTrace>) cnlCustTraceBiz
				.findCnlCustTraceByPage(queryPage);
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
		logger.info("entering action::CnlCustTraceManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlCustTraceBiz.saveCnlCustTrace(cnlCustTrace);
		}
		// 如果是修改
		else {
			cnlCustTraceBiz.updateCnlCustTrace(cnlCustTrace);
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
		logger.info("entering action::CnlCustTraceManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlCustTraceManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlCustTraceBiz.exportCnlCustTrace(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlCustTraceManagerAction.setQueryCondition()...");


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


		// REQ_BATCH
		Long reqBatch = Long.parseLong( getSearchFields().get("reqBatch"));
		if (null != reqBatch) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqBatch", reqBatch);
				queryPage.addEqualSearch("reqBatch", reqBatch);
			}
			if (null != exportSetting) {
				queryPage.addQueryCondition("reqBatch", reqBatch);
				queryPage.addEqualSearch("reqBatch", reqBatch);
			}
		}


		// REQ_TYPE
		String reqType = getSearchFields().get("reqType");
		if (StringUtils.isNotEmpty(reqType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqType", "%" + reqType + "%");
				queryPage.addLikeSearch("reqType", reqType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqType", "%" + reqType + "%");
				exportSetting.addLikeSearch("reqType", reqType);
			}
		}


		// REQ_STATUS
		String reqStatus = getSearchFields().get("reqStatus");
		if (StringUtils.isNotEmpty(reqStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("reqStatus", "%" + reqStatus + "%");
				queryPage.addLikeSearch("reqStatus", reqStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("reqStatus", "%" + reqStatus + "%");
				exportSetting.addLikeSearch("reqStatus", reqStatus);
			}
		}


		// NAME
		String name = getSearchFields().get("name");
		if (StringUtils.isNotEmpty(name)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("name", "%" + name + "%");
				queryPage.addLikeSearch("name", name);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("name", "%" + name + "%");
				exportSetting.addLikeSearch("name", name);
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


		// CERT_EXPIRE
		String certExpire = getSearchFields().get("certExpire");
		if (StringUtils.isNotEmpty(certExpire)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("certExpire",
						DateUtils.convert(certExpire,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("certExpire",
						DateUtils.convert(certExpire,
								DateUtils.DATE_FORMAT));
			}
		}


		// BANK_CUST_NAME
		String bankCustName = getSearchFields().get("bankCustName");
		if (StringUtils.isNotEmpty(bankCustName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("bankCustName", "%" + bankCustName + "%");
				queryPage.addLikeSearch("bankCustName", bankCustName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("bankCustName", "%" + bankCustName + "%");
				exportSetting.addLikeSearch("bankCustName", bankCustName);
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


		// ADDR
		String addr = getSearchFields().get("addr");
		if (StringUtils.isNotEmpty(addr)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("addr", "%" + addr + "%");
				queryPage.addLikeSearch("addr", addr);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("addr", "%" + addr + "%");
				exportSetting.addLikeSearch("addr", addr);
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


		// MSG_TIME
		String msgTime = getSearchFields().get("msgTime");
		if (StringUtils.isNotEmpty(msgTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgTime",
						DateUtils.convert(msgTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgTime",
						DateUtils.convert(msgTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// ERR_TYPE
		String errType = getSearchFields().get("errType");
		if (StringUtils.isNotEmpty(errType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errType", "%" + errType + "%");
				queryPage.addLikeSearch("errType", errType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errType", "%" + errType + "%");
				exportSetting.addLikeSearch("errType", errType);
			}
		}


		// ERR_MSG
		String errMsg = getSearchFields().get("errMsg");
		if (StringUtils.isNotEmpty(errMsg)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("errMsg", "%" + errMsg + "%");
				queryPage.addLikeSearch("errMsg", errMsg);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("errMsg", "%" + errMsg + "%");
				exportSetting.addLikeSearch("errMsg", errMsg);
			}
		}


		// CUST_TYPE
		String custType = getSearchFields().get("custType");
		if (StringUtils.isNotEmpty(custType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("custType", "%" + custType + "%");
				queryPage.addLikeSearch("custType", custType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("custType", "%" + custType + "%");
				exportSetting.addLikeSearch("custType", custType);
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

	public ICnlCustTraceBiz getCnlCustTraceBiz() {
		logger.info("entering action::CnlCustTraceManagerAction.getCnlCustTraceBiz()...");
		return cnlCustTraceBiz;
	}

	public void setCnlCustTraceBiz(ICnlCustTraceBiz cnlCustTraceBiz) {
		logger.info("entering action::CnlCustTraceManagerAction.setCnlCustTraceBiz()...");
		this.cnlCustTraceBiz = cnlCustTraceBiz;
	}

	public CnlCustTrace getCnlCustTrace() {
		logger.info("entering action::CnlCustTraceManagerAction.getCnlCustTrace()...");
		return cnlCustTrace;
	}

	public void setCnlCustTrace(CnlCustTrace cnlCustTrace) {
		logger.info("entering action::CnlCustTraceManagerAction.setCnlCustTrace()...");
		this.cnlCustTrace = cnlCustTrace;
	}

	public String getId() {
		logger.info("entering action::CnlCustTraceManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlCustTraceManagerAction.setId()...");
		this.id = id;
	}

}
