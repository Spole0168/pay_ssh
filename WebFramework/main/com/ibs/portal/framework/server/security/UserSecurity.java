package com.ibs.portal.framework.server.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.context.BeanHolder;
import com.ibs.portal.framework.server.domain.IUser;

public class UserSecurity {

	private static final Logger logger = LoggerFactory
			.getLogger(UserSecurity.class);

	public static final String FORM_SECURITY_TYPE = "FORM_SECURITY";
	public static final String URL_SECURITY_TYPE = "URL_SECURITY";

	/**
	 * 检查用户是否具有某个资源的访问权限
	 * 
	 * @param user
	 * @param resourceName
	 * @param resourceType
	 *            TODO
	 * @return
	 */
	public static boolean hasPrivilege(IUser user, String resourceName,
			String resourceType) {
		// TODO 需要定制实现
		IResourceSecurityChecker checker = null;

		if (FORM_SECURITY_TYPE.equals(resourceType)) {
			checker = (IResourceSecurityChecker) BeanHolder
					.getBean("formSecurityChecker");
		} else if (URL_SECURITY_TYPE.equals(resourceType)) {
			checker = (IResourceSecurityChecker) BeanHolder
					.getBean("urlSecurityChecker");
		} else {
			// 可增加
		}

		if (null != checker) {
			return checker.hasPrivilege(user, resourceName);
		} else {
			logger
					.warn("应用需定义[formSecurityChecker/urlSecurityChecker]并实现资源权限检查接口[IResourceSecurityChecker]...");
		}

		return true;
	}
}
