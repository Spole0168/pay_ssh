package com.ibs.portal.framework.server.action;

import com.ibs.portal.framework.share.metadata.DataBundle;

/**
 * @author 
 *
 */
public abstract class BaseDataBundleAction extends BaseAction implements IDataBundleAction {

	// 请求数据包
	private DataBundle requestDataBundle;

	// 响应数据包
	private DataBundle responseDataBundle;

	/**
	 * 获取请求数据包
	 *
	 * @return 请求数据包
	 */
	public DataBundle getRequestDataBundle() {
		return requestDataBundle;
	}

	/**
	 * 设置响应数据包
	 *
	 * @param responseDataBundle 响应数据包
	 */
	public void setResponseDataBundle(DataBundle responseDataBundle) {
		this.responseDataBundle = responseDataBundle;
	}

	// 实现框架接口 ----

	public void setRequestDataBundle(DataBundle requestDataBundle) {
		this.requestDataBundle = requestDataBundle;
	}

	public DataBundle getResponseDataBundle() {
		return responseDataBundle;
	}

}