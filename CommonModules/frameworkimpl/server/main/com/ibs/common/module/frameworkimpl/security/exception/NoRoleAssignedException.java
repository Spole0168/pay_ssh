package com.ibs.common.module.frameworkimpl.security.exception;

public class NoRoleAssignedException extends AuthorizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1450771675130745019L;

	private static String NoRoleAssigned = "authorize.no.role.assigned";
	
	public NoRoleAssignedException(){
		super(NoRoleAssigned);
	}
}
