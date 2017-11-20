package com.ibs.portal.framework.server.exception;

import java.io.Serializable;

/**
 * 应用异常,一般是业务约束相关,Checked异常
 * 
 * @author zhouxiang
 * 
 */
public class BizCheckedException extends BaseException implements Serializable,
		ExceptionSource, ExceptionSeverity {

	private static final long serialVersionUID = 7865482707056545923L;

	public BizCheckedException(String message) {
		super(message, null, ERR_SOURCE_BIZ);
	}

	public BizCheckedException(String message, Throwable cause) {
		super(message, cause, ERR_SOURCE_BIZ);
	}

	public BizCheckedException(String message, Throwable cause, int severity) {
		super(message, cause, -1, ERR_SOURCE_BIZ, severity);
	}

	public BizCheckedException(String message, Throwable cause, int source,
			int severity) {
		super(message, cause, -1, source, severity);
	}

	public BizCheckedException(String message, Throwable cause, int code, int source,
			int severity) {
		super(message, cause, code, source, severity);
	}

}
