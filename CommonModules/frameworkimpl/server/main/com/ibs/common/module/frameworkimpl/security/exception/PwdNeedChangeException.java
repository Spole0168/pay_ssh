package com.ibs.common.module.frameworkimpl.security.exception;

public class PwdNeedChangeException extends AuthorizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1450771675130745019L;

	public PwdNeedChangeException(){
		super(AUTHORIZE_PASSWORD_CHANGE);
	}
}
