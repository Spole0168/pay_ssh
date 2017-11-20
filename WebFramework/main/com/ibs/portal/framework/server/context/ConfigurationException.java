package com.ibs.portal.framework.server.context;

import com.ibs.portal.framework.server.exception.BaseRuntimeException;
import com.ibs.portal.framework.server.exception.ExceptionSource;

public class ConfigurationException extends BaseRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4277559422262305510L;

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
		setSource(ExceptionSource.ERR_SOURCE_DEPLOY);
	}

	public ConfigurationException(String message, Throwable cause, int code) {
		super(message, cause, code);
		setSource(ExceptionSource.ERR_SOURCE_DEPLOY);
	}

	public ConfigurationException(String message) {
		super(message);
		setSource(ExceptionSource.ERR_SOURCE_DEPLOY);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
		setSource(ExceptionSource.ERR_SOURCE_DEPLOY);
	}

	public ConfigurationException() {
		super();
		setSource(ExceptionSource.ERR_SOURCE_DEPLOY);
	}
}
