package com.ibs.common.module.frameworkimpl.security.exception;

import com.ibs.portal.framework.server.exception.BizException;



public class AuthorizeException extends BizException {

	private static final long serialVersionUID = 7862756769053963983L;
	public static String AUTHORIZE_NOT_EXIST_USER="Authorize.not.exist.user";
	public static String AUTHORIZE_INVALID_USER="Authorize.invalid.user";
	public static String AUTHORIZE_ILLEGAL_USERNAME="Authorize.illegal.username";
	public static String AUTHORIZE_ILLEGAL_PASSWORD="Authorize.illegal.password";
	public static String AUTHORIZE_ERROR_PASSWORD="Authorize.error.password";
	public static String AUTHORIZE_ERROR_VARIFICATION="Authorize.error.verification";
	public static String AUTHORIZE_PASSWORD_CHANGE="authorize.password.change";
	public static String AUTHORIZE_NO_ROLE_ASSIGNED ="authorize.no.role.assigned";

	public AuthorizeException() {
		super("error.login.authorize");
	}

	public AuthorizeException(String message) {
		super(message);
	}

}
