package com.ibs.common.module.frameworkimpl.security.exception;

public class IllPasswordException extends AuthorizeException {
	 /**
	 *
	 */
	private static final long serialVersionUID = -269700900349390123L;

	private static String IllPasswordKey = "Authorize.illegal.password";

	public IllPasswordException(){
    	 super(IllPasswordKey);
     }
}
