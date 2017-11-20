package com.ibs.portal.framework.server.security;

import com.ibs.portal.framework.server.domain.IUser;

/**
 * 资源权限检查
 * 
 * @author luoyue
 * 
 */
public interface IResourceSecurityChecker {

	/**
	 * 是否有权限
	 * 
	 * @param user
	 * @param resourceValue
	 * @return
	 */
	public boolean hasPrivilege(IUser user, String resourceValue);
}
