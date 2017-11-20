package com.ibs.core.module.permission.service;

import java.util.List;

/**
 * @author 
 * 
 *   用户角色处理对外接口
 *
 */
public interface IUserRoleProcessService {
	
	/**
	 * 给用户赋角色
	 * @param userName 用户名
	 * @param roleCodes 角色代码list
	 */
	public void assignUserRoles(String userName,List<String> roleCodes);
	
	
	/**
	 * 取消用户角色
	 * @param userName 用户名
	 * @param roleCodes 角色代码list
	 */
	public void unAssignUserRoles(String userName,List<String> roleCodes);

}
