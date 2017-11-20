package com.ibs.core.module.permission.biz.impl;

import java.util.List;

import com.ibs.core.module.permission.biz.IUserRoleProcessBiz;
import com.ibs.core.module.permission.service.IUserRoleProcessService;

public class UserRoleProcessBizImpl implements IUserRoleProcessBiz
{

private IUserRoleProcessService userRoleProcessService;
	
	public IUserRoleProcessService getUserRoleProcessService()
	{
		return userRoleProcessService;
	}

	public void setUserRoleProcessService(
			IUserRoleProcessService userRoleProcessService)
	{
		this.userRoleProcessService = userRoleProcessService;
	}
	
	public void updateassignUserRoles(String userName, List<String> roleCodes)
	{
		// TODO Auto-generated method stub
		userRoleProcessService.assignUserRoles(userName, roleCodes);

	}

	public void updateunAssignUserRoles(String userName, List<String> roleCodes)
	{
		// TODO Auto-generated method stub
		userRoleProcessService.unAssignUserRoles(userName, roleCodes);
	}

}
