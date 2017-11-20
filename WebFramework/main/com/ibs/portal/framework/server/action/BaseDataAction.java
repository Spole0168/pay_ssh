package com.ibs.portal.framework.server.action;

/**
 * 富客户Action基础实现
 *
 * @author 
 *
 */
public abstract class BaseDataAction extends BaseAction implements IDataAction {

	// 请求数据
	private Object requestData;

	// 响应数据
	private Object responseData;

	/**
	 * 获取请求数据
	 *
	 * @return 请求数据
	 */
	public Object getRequestData() {
		return requestData;
	}

	/**
	 * 设置响应数据
	 *
	 * @param responseData 响应数据
	 */
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	// 框架接口实现 ----

	public void setRequestData(Object requestData) {
		this.requestData = requestData;
	}

	public Object getResponseData() {
		return responseData;
	}

}