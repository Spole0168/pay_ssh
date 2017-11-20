package com.ibs.common.module.frameworkimpl.security.exception;

public class ErrorPasswordException extends AuthorizeException {
     /**
	 *
	 */
	private static final long serialVersionUID = 593363796426507973L;

	private static String ErrorPasswordKey = "Authorize.error.password";

	public ErrorPasswordException(){
    	 super(ErrorPasswordKey);
     }
}
