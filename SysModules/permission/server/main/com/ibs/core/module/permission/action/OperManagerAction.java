package com.ibs.core.module.permission.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.core.module.permission.biz.IPermissionBiz;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

public class OperManagerAction extends CrudBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6814910349123828279L;
	private IPermissionBiz permissionBiz;

	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}

	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}

	private String id;
	private String operName;
	private String appId;
	private String description;
	private String operType;
	private String operValue;
	private Integer displayOrder;
	
	private String menuTypeColumnRender;
	
	private List<OptionObjectPair> operTypePairs;
	private Menu operation;
	
	public String getMenuTypeColumnRender() {
		return menuTypeColumnRender;
	}

	public void setMenuTypeColumnRender(String menuTypeColumnRender) {
		this.menuTypeColumnRender = menuTypeColumnRender;
	}

	public List<OptionObjectPair> getOperTypePairs() {
		return operTypePairs;
	}

	public void setOperTypePairs(List<OptionObjectPair> operTypePairs) {
		this.operTypePairs = operTypePairs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public Menu getOperation() {
		return operation;
	}

	public void setOperation(Menu operation) {
		this.operation = operation;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOperValue() {
		return operValue;
	}

	public void setOperValue(String operValue) {
		this.operValue = operValue;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Override
	public String create() {
		operTypePairs = Menu.operTypePairs;
		return SUCCESS;
	}

	@Override
	public String delete() {
		logger.trace("entering OperManagerAction delete()...");
		String[] idStringArray = getSelectIds();
		int failed = 0;
		StringBuffer msg = new StringBuffer();
		for (int i = 0; i < idStringArray.length; i++) {
			String operId = idStringArray[i];
//			Menu tempOper = permissionBiz.getMenuCascadeById(operId);
//			if(tempOper.getIncludedRoles() != null && tempOper.getIncludedRoles().size() >0){
//				message = "存在与角色关联，不能删除";
//				addActionMessage(message);
//				continue;
//			}
			try {
				permissionBiz.deleteOper(operId);
			} catch (Exception e) {
//				addActionError(e.getMessage());
				msg.append(e.getMessage()+",");
				failed ++;
			}
		}
		if(failed > 0){
			addActionError(msg.toString());
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String export() {
		return null;
	}

	@Override
	public String list() {
		operTypePairs = Menu.operTypePairs;
		menuTypeColumnRender = Menu.menuTypeColumnRender;
		return SUCCESS;
	}

	@Override
	public String modify() {
		logger.trace("enter "+this.getClass().getSimpleName()+" method modify");
		String[] idStringArray = getSelectIds();
		if (idStringArray != null && idStringArray.length > 0) {
			id = idStringArray[0];
		}
		if (null != id && !"".equals(id)) {
			operation = permissionBiz.getOperById(id);
			operType = operation.getMenuType();
		}else{
			operation = new Menu();
		}
		operTypePairs = Menu.operTypePairs;
		return SUCCESS;
	}

	@Override
	public String saveOrUpdate() {
		operation = new Menu();

		operation.setMenuName(operName);
		operation.setMenuType(operType);
		operation.setLocation(operValue);
		operation.setDescription(description);
		operation.setId(id);
		permissionBiz.saveOrUpdateOperation(operation);
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String search() {
		try {
			String operName = this.getSearchFields().get("operNameSearch");
			if (operName != null && !"".equals(operName.trim())) {
				queryPage.addLikeSearch("menuName", operName);
			}
			String operType = this.getSearchFields().get("operTypeSearch");
			if (operType != null && !"".equals(operType.trim())) {
				queryPage.addEqualSearch("menuType", operType);
			}
			String operValue = this.getSearchFields().get("operValueSearch");
			if (operValue != null && !"".equals(operValue.trim())) {
				queryPage.addLikeSearch("location", operValue);
			}
			String description = this.getSearchFields().get("descriptionSearch");
			if (description != null && !"".equals(description.trim())) {
				queryPage.addLikeSearch("description", description);
			}
			
			Page<Menu> result = (Page<Menu>) permissionBiz
					.getOperListByPage(queryPage);
			this.setResult(result);
		} catch (Exception e) {
			addActionError(e.getMessage());
			logger.error(e.getMessage());
		}
		return AJAX_RETURN_TYPE;
	}
	
	public String validateName(){
		
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(operName) && StringUtils.isNotEmpty(operType)) {
			Menu oper = permissionBiz.getOperByName(operType, operName);
			if(null!=oper){
				sBuffer.append(false);
			} else{
				sBuffer.append(true);
			}
		}else {
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
		return null;
	}

}
