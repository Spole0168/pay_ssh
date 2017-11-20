package com.ibs.core.module.mdmbasedata.exception;

/**
 * 保存数据异常
 * @author 
 *
 */
public class SaveFailureException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveFailureException(String mes)
	{
		super(mes);
	}
	
}
