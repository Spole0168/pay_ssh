package com.ibs.portal.framework.server.exception;

import java.io.Serializable;


/**
 * 操作异常
 *
 * @author 
 *
 */
public class OperationException extends ApplicationException 
	implements Serializable, ExceptionSource, ExceptionSeverity{

	public OperationException(String message) {
		super(message,null,ERR_SOURCE_OPERATE);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1769701091027843492L;

}
