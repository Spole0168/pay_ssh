package com.ibs.common.module.login.biz.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.exception.AuthorizeException;
import com.ibs.common.module.frameworkimpl.security.service.IPermissionService;
import com.ibs.common.module.login.biz.ILoginBiz;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.context.UserContext;

public class LoginBiz extends BaseBiz implements ILoginBiz {

	protected final Log log = LogFactory.getLog(getClass());

	private IPermissionService permissionService;

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void changePassword(String oldPassword, String newPassword)
			throws AuthorizeException {
		permissionService.resetUserPwd(oldPassword, newPassword, false);
	}

	/**
	 * 已加密的密码修改
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @throws AuthorizeException
	 */
	public void changePasswordEncrypted(String oldPassword, String newPassword)
			throws AuthorizeException {
		permissionService.resetUserPwd(oldPassword, newPassword, true);
	}
	
	/**
	 * 通过用户ID来修改密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @throws AuthorizeException
	 */
	public void changePassword(String userId, String oldPassword,
			String newPassword) throws AuthorizeException {
		permissionService.resetUserPwd(userId, oldPassword, newPassword, false);
	}

	public String login(String loginIp, String userName, String password)
			throws AuthorizeException {
		return permissionService.login(loginIp, userName, password);
	}

	public String pdaLogin(String loginIp, String userName, String password)
			throws AuthorizeException {
		return permissionService.pdaLogin(userName, password);
	}

	public String loginSSO(String loginIp, String userName)
			throws AuthorizeException {
		return permissionService.loginSSO(loginIp, userName);
	}

	public void logout() throws AuthorizeException {
		UserContext.getUserContext().removeUserId();
	}

	public Integer checkUserPwd(String userId, String oriPwd) {
		return permissionService.checkUserPwd(userId, oriPwd);
	}
	
	public Integer checkUserRoles(String userName) {
		List<Role> list = permissionService.getAllRolesByUserName(userName);
		if(null == list){
			return 0;
		}else {
			return list.size();
		}
//		return permissionService.checkUserPwd(userId, oriPwd);
	}

}
