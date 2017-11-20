package com.ibs.portal.framework.server.exception;

/**
 * 未登录异常
 *
 * @author 
 *
 */
public class NotLoginException extends PermissionNotAssignedException {

	private static final long serialVersionUID = 1L;

	public NotLoginException() {
		super();
	}

	public NotLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotLoginException(String message) {
		super(message);
	}

	public NotLoginException(Throwable cause) {
		super(cause);
	}

}
