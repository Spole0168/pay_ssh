package com.ibs.common.module.main.biz;

import java.util.List;

import com.ibs.common.module.frameworkimpl.security.dto.UserComplex;
import com.ibs.common.module.frameworkimpl.security.exception.AuthorizeException;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.SystemMenu;

public interface IMainBiz {

	/**
	 * 退出登录
	 */
	public void logout(IUser user) throws AuthorizeException;

//	void changePassword(String loginIp, String userId, String oldPassword,
//			String newPassword) throws AuthorizeException;
	/**
	 * 修改当前用的密码
	 * @param oldPassword 校验旧的密码
	 * @param newPassword 修改的新密码
	 * @throws AuthorizeException
	 */
	public void changePassword(String oldPassword, String newPassword) throws AuthorizeException ;
	/**
	 * 登录后初始化用户菜单
	 * 
	 * @param suser
	 */
	public List<SystemMenu> loadUserMenu(UserComplex suser);
	
	/**
	 * 根据用户选择的角色加载用户菜单
	 * 
	 * @param suser
	 */
	public List<SystemMenu> loadCurrentRoleMenu(UserComplex suser,String nextRoleId);
		
	/**
	 * 岗位显示列表,用于切换岗位
	 * 
	 * @param suser
	 * @return
	 */
	
	public List<OptionObjectPair> getAllRolesByUser(UserComplex suser);
	
	public void switchRole(UserComplex suser, String nextRoleId);

	/**
	 * 检查用户输入的密码是否正确，以及距离过期还有多少天
	 * @param oriPwd 用户输入的密码，如果为空，则查询用户当前密码所剩天数
	 * @param userId 用户ID，如果为空，则取当前用户的ID
	 * 
	 * @return <0 输入密码不匹配用户的密码
	 * @return >0 输入密码匹配用户密码，且未过期；返回为密码剩余天数（如果输入密码为空，则返回当前密码剩余天数）
	 * @return =0 输入的密码匹配用户密码，但是已经过期（包括初始化密码）
	 */
	public Integer checkUserPwd(String userId, String oriPwd);
}
