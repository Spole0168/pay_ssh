package com.ibs.portal.framework.server.exception;

/**
 * service异常,一般是业务约束相关
 * 
 * @author zhouxiang
 *
 */
public class ServiceException extends ApplicationException {

	private static final long serialVersionUID = 7865482707015545923L;

	public ServiceException(String message) {
		super(message, null, ERR_SOURCE_SERVICE);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause, ERR_SOURCE_SERVICE);
	}

	public ServiceException(String message, Throwable cause, int severity) {
		super(message, cause, ERR_SOURCE_SERVICE, severity);
	}

	public ServiceException(String message, Throwable cause, int source,
			int severity) {
		super(message, cause, ERR_SOURCE_SERVICE, source, severity);
	}

}
