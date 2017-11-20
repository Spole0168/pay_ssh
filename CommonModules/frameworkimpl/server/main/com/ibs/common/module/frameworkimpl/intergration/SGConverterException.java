package com.ibs.common.module.frameworkimpl.intergration;

/**********************************************
* Description:  消息报文转换异常
***********************************************/
public class SGConverterException extends SGException {

	private static final long serialVersionUID = -5056292330558444348L;

	public SGConverterException(String message) {
		super(message);
	}

	public SGConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	public SGConverterException(Throwable cause) {
		super(cause);
	}
}
