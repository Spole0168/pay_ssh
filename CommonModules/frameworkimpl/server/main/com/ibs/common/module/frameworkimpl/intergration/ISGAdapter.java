package com.ibs.common.module.frameworkimpl.intergration;

import java.util.Map;
/**********************************************
* Description: *	服务网关适配器
***********************************************/
public interface ISGAdapter {

	/**
	 * 服务处理
	 * @param to 消息对象
	 * @return
	 * @throws SGException
	 */
	public Object send(Object to) throws SGException;
	
	/**
	 * 服务处理，设置消息关联ID
	 * @param to 消息对象
	 * @param correlationID
	 * @return
	 * @throws SGException
	 */
	public Object send(Object to, final String correlationID) throws SGException;
	
	/**
	 * 服务处理，设置消息头属性
	 * @param to 消息对象
	 * @param header 消息头
	 * @return
	 * @throws SGException
	 */
	public Object send(Object to, final Map<String, ?> header) throws SGException;
	
	/**
	 * 服务处理，设置消息头属性、关联ID
	 * @param to 消息对象
	 * @param correlationID 关联ID
	 * @param header 消息头属性
	 * @return
	 * @throws SGException
	 */
	public Object send(Object to, final String correlationID, final Map<String, ?> header) throws SGException;

	

}
