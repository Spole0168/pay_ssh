/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.interfaces.action;

import java.io.IOException;

import com.ibs.core.module.cnlmgr.dao.ICnlCnlCfgDao;
import com.ibs.core.module.interfaces.biz.ICnlMsgBiz;
import com.ibs.core.module.interfaces.domain.CnlMsg;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlMsgManagerAction extends CrudBaseAction {
	private ICnlMsgBiz cnlMsgBiz;
	private ICnlCnlCfgDao cnlCnlCfgDao;
	private String id;
	private CnlMsg cnlMsg;
	
	
	




	public ICnlCnlCfgDao getCnlCnlCfgDao() {
		return cnlCnlCfgDao;
	}


	public void setCnlCnlCfgDao(ICnlCnlCfgDao cnlCnlCfgDao) {
		this.cnlCnlCfgDao = cnlCnlCfgDao;
	}


	public String searchByMsgCode(){
		logger.info("entering action::CnlMsgManagerAction.searchByMsgCode()...");
		String msgCode=request.getParameter("msgCode");
		String cnlCnlName=request.getParameter("cnlCnlCode");
		if(null!=cnlCnlName){
		logger.debug("msgCode:"+msgCode+"...cnlCnlName:"+cnlCnlName);
		String cnlCnlCode=cnlCnlCfgDao.findByCnlName(cnlCnlName);
		String text=cnlMsgBiz.findByMsgCode(msgCode,cnlCnlCode);
		logger.debug("file Content:"+text);
		if(text.contains("{")){
			String header=text.substring(text.indexOf("{")+1, text.indexOf("}"));
			String content=text.substring(text.lastIndexOf("{")+1, text.lastIndexOf("}"));
			text="<ul><li>header:</li><li>"+header.replace(",", "</li><li>")+"<li>content:</li><li>"+content.replace(",", "</li><li>")+"</li></ul>";
		}
		try {
			  response.setContentType("text/html;charset=utf-8");  
		      response.setHeader("Cache-Control", "no-cache");   
		      response.getWriter().println(text);
		      logger.debug("ajax read msg end...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}	
		logger.info("exit searchByMsgCode() ");
		return AJAX_RETURN_TYPE;
	}
	
	
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlMsgManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlMsgManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlMsgManagerAction.modify()...");
		cnlMsg = cnlMsgBiz.getCnlMsgById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlMsgManagerAction.search()...");
		System.out.println("&&&&&&");
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlMsg> result = (Page<CnlMsg>) cnlMsgBiz
				.findCnlMsgByPage(queryPage);
		setResult(result);

		System.out.println(result);
		
		
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
		logger.info("entering action::CnlMsgManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlMsgBiz.saveCnlMsg(cnlMsg);
		}
		// 如果是修改
		else {
			cnlMsgBiz.updateCnlMsg(cnlMsg);
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
		logger.info("entering action::CnlMsgManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlMsgManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlMsgBiz.exportCnlMsg(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlMsgManagerAction.setQueryCondition()...");


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


		// MSG_CODE
		String msgCode = getSearchFields().get("msgCode");
		
		if (StringUtils.isNotEmpty(msgCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgCode", "%" + msgCode + "%");
				queryPage.addLikeSearch("msgCode", msgCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgCode", "%" + msgCode + "%");
				exportSetting.addLikeSearch("msgCode", msgCode);
			}
		}


		// MSG_FILE_LOCATION
		String msgFileLocation = getSearchFields().get("msgFileLocation");
		if (StringUtils.isNotEmpty(msgFileLocation)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("msgFileLocation", "%" + msgFileLocation + "%");
				queryPage.addLikeSearch("msgFileLocation", msgFileLocation);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("msgFileLocation", "%" + msgFileLocation + "%");
				exportSetting.addLikeSearch("msgFileLocation", msgFileLocation);
			}
		}


		// RECIEVE_TIME
		String recieveTime = getSearchFields().get("recieveTime");
		if (StringUtils.isNotEmpty(recieveTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("recieveTime",
						DateUtils.convert(recieveTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("recieveTime",
						DateUtils.convert(recieveTime,
								DateUtils.DATE_FORMAT));
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


		// CNL_INTF_CODE
		String cnlIntfCode = getSearchFields().get("cnlIntfCode");
		if (StringUtils.isNotEmpty(cnlIntfCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlIntfCode", "%" + cnlIntfCode + "%");
				queryPage.addLikeSearch("cnlIntfCode", cnlIntfCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlIntfCode", "%" + cnlIntfCode + "%");
				exportSetting.addLikeSearch("cnlIntfCode", cnlIntfCode);
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

	public ICnlMsgBiz getCnlMsgBiz() {
		logger.info("entering action::CnlMsgManagerAction.getCnlMsgBiz()...");
		return cnlMsgBiz;
	}

	public void setCnlMsgBiz(ICnlMsgBiz cnlMsgBiz) {
		logger.info("entering action::CnlMsgManagerAction.setCnlMsgBiz()...");
		this.cnlMsgBiz = cnlMsgBiz;
	}

	public CnlMsg getCnlMsg() {
		logger.info("entering action::CnlMsgManagerAction.getCnlMsg()...");
		return cnlMsg;
	}

	public void setCnlMsg(CnlMsg cnlMsg) {
		logger.info("entering action::CnlMsgManagerAction.setCnlMsg()...");
		this.cnlMsg = cnlMsg;
	}

	public String getId() {
		logger.info("entering action::CnlMsgManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlMsgManagerAction.setId()...");
		this.id = id;
	}

}
