package com.ibs.core.module.permission.biz;

import java.util.List;

public interface IUserRoleProcessBiz
{
	/**
	 * 给用户赋角色
	 * @param userName 用户名
	 * @param roleCodes 角色代码list
	 */
	public void updateassignUserRoles(String userName,List<String> roleCodes);
	
	
	/**
	 * 取消用户角色
	 * @param userName 用户名
	 * @param roleCodes 角色代码list
	 */
	public void updateunAssignUserRoles(String userName,List<String> roleCodes);
}
