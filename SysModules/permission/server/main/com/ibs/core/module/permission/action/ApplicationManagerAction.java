package com.ibs.core.module.permission.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.core.module.permission.biz.IPermissionBiz;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

public class ApplicationManagerAction extends CrudBaseAction {

	private static final long serialVersionUID = 861390291857277632L;

	private IPermissionBiz permissionBiz;

	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}

	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}

	private String id;
	private String appId;
	private String appName;
	private String description;
	private String appNameSearch;
	private String descriptionSearch;
	private Application app;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getDescription() {
		return description;
	}

	public String getAppNameSearch() {
		return appNameSearch;
	}

	public void setAppNameSearch(String appNameSearch) {
		this.appNameSearch = appNameSearch;
	}

	public String getDescriptionSearch() {
		return descriptionSearch;
	}

	public void setDescriptionSearch(String descriptionSearch) {
		this.descriptionSearch = descriptionSearch;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	private IPage<Application> appList;

	public IPage<Application> getAppList() {
		return appList;
	}

	public void setAppList(IPage<Application> appList) {
		this.appList = appList;
	}


	@Override
	public String create() {
		return null;
	}

	@Override
	public String delete() {
		logger.trace("entering "+ this.getClass().getSimpleName()+" method delete()");
		
		String[] idStringArray = getSelectIds();
		List<String> errorList = new ArrayList<String>();
		for (int i = 0; i < idStringArray.length; i++) {
			String appId = idStringArray[i];
			Application tempApp = permissionBiz.getAppCascadeById(appId);
			if (tempApp.getIncludedMenus() != null
					&& tempApp.getIncludedMenus().size() > 0) {
				logger.trace("can not delete "+tempApp.getAppName()+" , with included Menus");
				errorList.add(tempApp.getAppName());
				continue;
			}
			if (tempApp.getIncludedGuiMenus() != null
					&& tempApp.getIncludedGuiMenus().size() > 0) {
				logger.trace("can not delete "+tempApp.getAppName()+" , with included GuiMenus");
				errorList.add(tempApp.getAppName());
				continue;
			}
			if (tempApp.getIncludedOpers() != null
					&& tempApp.getIncludedOpers().size() > 0) {
				logger.trace("can not delete "+tempApp.getAppName()+" , with included operations");
				errorList.add(tempApp.getAppName());
				continue;
			}
			try {
				permissionBiz.deleteApp(appId);
			} catch (Exception e) {
				addActionError(e.getMessage());
				errorList.add(tempApp.getAppName());
			}
		}
		
		if(errorList.size()>0){
			message = "应用";
			for(String name:errorList){
				message += name+",";
			}
			message = message.substring(0, message.length()-1);
			message += "不允许删除";
		}else{
			message = "删除成功";
		}
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String export() {
		return null;
	}

	@Override
	public String list() {
		return SUCCESS;
	}

	@Override
	public String modify() {
		logger.trace("enter " + this.getClass().getSimpleName()
				+ " method modify()");
		
		String[] idStringArray = getSelectIds();
		if (idStringArray != null && idStringArray.length > 0) {
			id = idStringArray[0];
		}
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		app = permissionBiz.getAppById(appId);
		return SUCCESS;
	}

	public String toSaveOrUpdate() throws Exception {
		logger.trace("entering "+ this.getClass().getSimpleName()+" method toSaveOrUpdate()");
		if (null != id && !"".equals(id)) {
			logger.trace("application id "+id);
			app = permissionBiz.getAppById(id);
		}
		return SUCCESS;
	}

	@Override
	public String saveOrUpdate() {
		logger.trace("entering "+ this.getClass().getSimpleName()+" method saveOrUpdate()");
		message = "";
		if (null != appName) {
			app = new Application();
			app.setAppName(appName);
			app.setDescription(description);
			if (null != appId && !"".equals(appId)) {
				app.setId(appId);
			} else {
				app.setId(null);
			}
			try{
				permissionBiz.saveOrUpdateApp(app);
			}catch(Exception e){
				addActionError(e.getMessage());
				return ERROR;
			}
			message = "保存成功";
		}
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String search() {
		
		try {
			String appName = this.getSearchFields().get("appNameSearch");
			if (appName != null && !"".equals(appName.trim())) {
				queryPage.addLikeSearch("appName", appName);
			}
			String description = this.getSearchFields().get("descriptionSearch");
			if (description != null && !"".equals(description.trim())) {
				queryPage.addLikeSearch("description", description);
			}

			Page<Application> result = (Page<Application>) permissionBiz
					.getAppListByPage(queryPage);
			this.setResult(result);
		} catch (Exception e) {
			addActionError(e.getMessage());
			logger.error(e.getMessage());
		}
		return AJAX_RETURN_TYPE;
	}
	
	public String checkAppName(){
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(appName)) {
			Application app = permissionBiz.findApplicationByName(appName);
			if(app != null){
				sBuffer.append(false);				
			} else {
				sBuffer.append(true);
			}
			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.print(sBuffer.toString());
			pw.flush();
			pw.close();
		}
		return null;
	}
}
