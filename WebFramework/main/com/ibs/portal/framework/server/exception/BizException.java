package com.ibs.portal.framework.server.exception;

import java.io.Serializable;

/**
 * 应用异常,一般是业务约束相关,unChecked异常
 * 
 * @author luoyue
 * 
 */
public class BizException extends ApplicationException implements Serializable,
		ExceptionSource, ExceptionSeverity {

	private static final long serialVersionUID = 7865482707056545923L;

	public BizException(String message) {
		super(message, null, ERR_SOURCE_BIZ);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause, ERR_SOURCE_BIZ);
	}

	public BizException(String message, Throwable cause, int severity) {
		super(message, cause, -1, ERR_SOURCE_BIZ, severity);
	}

	public BizException(String message, Throwable cause, int source,
			int severity) {
		super(message, cause, -1, source, severity);
	}

	public BizException(String message, Throwable cause, int code, int source,
			int severity) {
		super(message, cause, code, source, severity);
	}

}
