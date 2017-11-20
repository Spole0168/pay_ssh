/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.pbl.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.pbl.biz.ISysSysCfgBiz;
import com.ibs.core.module.pbl.domain.SysSysCfg;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @create by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SYS_CFG
 */
@SuppressWarnings("serial")
public class SysSysCfgManagerAction extends CrudBaseAction {

	private ISysSysCfgBiz sysSysCfgBiz;
	
	private String id;
	private SysSysCfg sysSysCfg;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::SysSysCfgManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::SysSysCfgManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::SysSysCfgManagerAction.modify()...");
		sysSysCfg = sysSysCfgBiz.getSysSysCfgById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::SysSysCfgManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<SysSysCfg> result = (Page<SysSysCfg>) sysSysCfgBiz
				.findSysSysCfgByPage(queryPage);
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
		logger.info("entering action::SysSysCfgManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			sysSysCfgBiz.saveSysSysCfg(sysSysCfg);
		}
		// 如果是修改
		else {
			sysSysCfgBiz.updateSysSysCfg(sysSysCfg);
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
		logger.info("entering action::SysSysCfgManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::SysSysCfgManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		sysSysCfgBiz.exportSysSysCfg(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::SysSysCfgManagerAction.setQueryCondition()...");


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


		// CATEGORY
		String category = getSearchFields().get("category");
		if (StringUtils.isNotEmpty(category)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("category", "%" + category + "%");
				queryPage.addLikeSearch("category", category);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("category", "%" + category + "%");
				exportSetting.addLikeSearch("category", category);
			}
		}


		// CODE
		String code = getSearchFields().get("code");
		if (StringUtils.isNotEmpty(code)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("code", "%" + code + "%");
				queryPage.addLikeSearch("code", code);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("code", "%" + code + "%");
				exportSetting.addLikeSearch("code", code);
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


		// VALUE
		String value = getSearchFields().get("value");
		if (StringUtils.isNotEmpty(value)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("value", "%" + value + "%");
				queryPage.addLikeSearch("value", value);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("value", "%" + value + "%");
				exportSetting.addLikeSearch("value", value);
			}
		}


		// VERSION
		String version = getSearchFields().get("version");
		if (StringUtils.isNotEmpty(version)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("version", "%" + version + "%");
				queryPage.addLikeSearch("version", version);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("version", "%" + version + "%");
				exportSetting.addLikeSearch("version", version);
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

	public ISysSysCfgBiz getSysSysCfgBiz() {
		logger.info("entering action::SysSysCfgManagerAction.getSysSysCfgBiz()...");
		return sysSysCfgBiz;
	}

	public void setSysSysCfgBiz(ISysSysCfgBiz sysSysCfgBiz) {
		logger.info("entering action::SysSysCfgManagerAction.setSysSysCfgBiz()...");
		this.sysSysCfgBiz = sysSysCfgBiz;
	}

	public SysSysCfg getSysSysCfg() {
		logger.info("entering action::SysSysCfgManagerAction.getSysSysCfg()...");
		return sysSysCfg;
	}

	public void setSysSysCfg(SysSysCfg sysSysCfg) {
		logger.info("entering action::SysSysCfgManagerAction.setSysSysCfg()...");
		this.sysSysCfg = sysSysCfg;
	}

	public String getId() {
		logger.info("entering action::SysSysCfgManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::SysSysCfgManagerAction.setId()...");
		this.id = id;
	}

}
