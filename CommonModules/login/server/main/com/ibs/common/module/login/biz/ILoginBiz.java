package com.ibs.common.module.login.biz;

import com.ibs.common.module.frameworkimpl.security.exception.AuthorizeException;

public interface ILoginBiz {

	/**
	 * 系统登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录后用户信息
	 * @throws AuthorizeException
	 *             登录失败时抛出
	 */
	public String login(String loginIp, String userName, String password)
			throws AuthorizeException;

	public String pdaLogin(String loginIp, String userName, String password)
			throws AuthorizeException;

	public String loginSSO(String loginIp, String userName)
			throws AuthorizeException;;

	/**
	 * 密码修改
	 * @param loginIp
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 */
	public void changePassword(String oldPassword, String newPassword) throws AuthorizeException ;
	
	/**
	 * 密码修改
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @throws AuthorizeException
	 */
	public void changePassword(String userId, String oldPassword,
			String newPassword) throws AuthorizeException;
	/**
	 * 已加密的密码修改
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @throws AuthorizeException
	 */
	public void changePasswordEncrypted(String oldPassword, String newPassword) throws AuthorizeException ;

	/**
	 * 退出登录
	 */
	public void logout() throws AuthorizeException;

	/**
	 * 检查用户输入的密码是否正确，以及距离过期还有多少天，供修改密码时使用
	 * @param oriPwd 用户输入的密码
	 * @return null if oriPwd is empty
	 * @return 0 if oriPwd matches user's password and is the initial password
	 * @return >0 if oriPwd matches user's password and the left active days
	 * @return <0 if oriPwd does not match user's password
	 */
	public Integer checkUserPwd(String userId, String oriPwd);
	
	/**
	 * 检查用户的角色信息
	 * @param userName
	 * @return
	 */
	public Integer checkUserRoles(String userName);
}
