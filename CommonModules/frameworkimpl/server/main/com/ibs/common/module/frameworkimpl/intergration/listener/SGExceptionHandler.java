package com.ibs.common.module.frameworkimpl.intergration.listener;

/**
 * <pre>
 * *********************************************
 * Description: JMS异常处理接口
 * *********************************************
 * </pre>
 */
public interface SGExceptionHandler {

	/**
	 * 异常处理方法
	 * 
	 * @param e
	 *            异常对象
	 * @param message
	 *            消息文本
	 * @param packageName
	 *            消息处理的所在包名
	 */
	public void onException(Throwable e, String message, String packageName);

}
