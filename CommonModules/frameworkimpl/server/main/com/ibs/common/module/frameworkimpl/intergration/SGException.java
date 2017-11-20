package com.ibs.common.module.frameworkimpl.intergration;

/**********************************************
* Description:  服务网关异常基础类
***********************************************/
public class SGException extends RuntimeException {

	private static final long serialVersionUID = -8184025670038174183L;
	
	public SGException(String message) {
		super(message);
	}

	public SGException(String message, Throwable cause) {
		super(message, cause);
	}

	public SGException(Throwable cause) {
		super(cause);
	}

}
