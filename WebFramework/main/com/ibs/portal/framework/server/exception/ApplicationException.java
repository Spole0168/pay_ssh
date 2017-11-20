package com.ibs.portal.framework.server.exception;

import java.io.Serializable;

public abstract class ApplicationException extends BaseRuntimeException
		implements Serializable, ExceptionSource, ExceptionSeverity {

	private static final long serialVersionUID = 1L;

	public ApplicationException(String message, Throwable cause, int code) {
		super(message, cause, code, ERR_SOURCE_APPLICAITON, ERR_SEVERITY_NORMAL);
	}

	public ApplicationException(String message, Throwable cause, int code,
			int severity) {
		super(message, cause, code, ERR_SOURCE_APPLICAITON, severity);
	}

	public ApplicationException(String message, Throwable cause, int code,
			int source, int severity) {
		super(message, cause, code, source, severity);
	}
}
