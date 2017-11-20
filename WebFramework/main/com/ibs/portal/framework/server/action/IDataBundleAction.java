package com.ibs.portal.framework.server.action;

import com.ibs.portal.framework.share.metadata.DataBundle;

/**
 * 富客户端请求Action接口
 * 
 * @author 
 * 
 */
public interface IDataBundleAction {

	/**
	 * 接收从客户端传来的数据
	 * 
	 * @param data
	 *            从客户端传来的数据
	 */
	public void setRequestDataBundle(DataBundle data);

	/**
	 * 返回给客户端的数据
	 * 
	 * @return 返回给客户端的数据
	 */
	public DataBundle getResponseDataBundle();

}
