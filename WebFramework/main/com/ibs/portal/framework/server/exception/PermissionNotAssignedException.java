package com.ibs.portal.framework.server.exception;

/**
 * 未授权异常
 *
 * @author 
 *
 */
public class PermissionNotAssignedException extends SecurityException {

	private static final long serialVersionUID = 1L;

	private final Object resource;

	public PermissionNotAssignedException() {
		super("permission not assigned or not login");
		this.resource = null;
	}

	public PermissionNotAssignedException(Object resource) {
		super(String.valueOf(resource));
		this.resource = resource;
	}

	public PermissionNotAssignedException(Object resource, String message, Throwable cause) {
		super(message, cause);
		this.resource = resource;
	}

	public PermissionNotAssignedException(Object resource, String message) {
		super(message);
		this.resource = resource;
	}

	public PermissionNotAssignedException(Object resource, Throwable cause) {
		super(cause);
		this.resource = resource;
	}

	/**
	 * 获取未授权的资源
	 *
	 * @return 未授权的资源
	 */
	public Object getResource() {
		return resource;
	}

}
