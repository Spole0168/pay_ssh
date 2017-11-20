package com.ibs.portal.framework.server.exception;

import java.io.Serializable;


/**
 * 操作异常
 *
 * @author 
 *
 */
public class ConfigException extends ApplicationException 
	implements Serializable, ExceptionSource, ExceptionSeverity{

	public ConfigException(String message) {
		super(message,null,ERR_SOURCE_CONFIG);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1769701091027843492L;

}
