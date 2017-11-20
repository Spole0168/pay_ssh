package com.ibs.common.module.frameworkimpl.security.exception;

public class NotExistUserException extends AuthorizeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 4046355604349959744L;
	private static String NotExistUserKey = "Authorize.not.exist.user";

	public NotExistUserException() {
		super(NotExistUserKey);
	}
}
