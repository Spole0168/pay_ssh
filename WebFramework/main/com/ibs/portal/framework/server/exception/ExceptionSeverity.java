package com.ibs.portal.framework.server.exception;

/**
 * 错误严重程度
 * 
 * @author 
 *
 */
public interface ExceptionSeverity {
	public static int ERR_SEVERITY_HINT  = 0;
	public static int ERR_SEVERITY_LOW  = 1;
	public static int ERR_SEVERITY_NORMAL  = 2;
	public static int ERR_SEVERITY_HIGH  = 3;
	public static int ERR_SEVERITY_FATAL  = 4;
}
