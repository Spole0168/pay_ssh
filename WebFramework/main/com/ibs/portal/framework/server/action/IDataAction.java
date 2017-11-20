package com.ibs.portal.framework.server.action;

/**
 * 二进制数据接收Action接口
 * 
 * @author 
 *
 */
public interface IDataAction {

	/**
	 * 接收从客户端传来的数据
	 * 
	 * @param data
	 *            从客户端传来的数据
	 */
	public void setRequestData(Object data);

	/**
	 * 返回给客户端的数据
	 * 
	 * @return 返回给客户端的数据
	 */
	public Object getResponseData();

}
