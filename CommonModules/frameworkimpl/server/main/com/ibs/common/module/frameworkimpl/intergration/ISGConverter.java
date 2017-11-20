package com.ibs.common.module.frameworkimpl.intergration;

/**
 * <pre>
 * *********************************************
 * Description: 服务网关数据转换接口
 * *********************************************
 * </pre>
 */
public interface ISGConverter {

	/**
	 * 传输对象 ---> XML报文字符串
	 * 
	 * @param to
	 *            传输对象
	 * @return XML报文
	 * @throws SGConverterException
	 */
	public String toXML(Object to) throws SGConverterException;

	/**
	 * XML报文字符串 ---> 传输对象
	 * 
	 * @param xml
	 *            XML报文
	 * @return
	 * @throws SGConverterException
	 */
	public Object fromXML(String xml) throws SGConverterException;

	/**
	 * 指定对象类型，XML报文字符串 ---> 传输对象
	 * 
	 * @param xml
	 * @param ObjClass
	 * @return
	 * @throws SGConverterException
	 */
	public Object fromXML(String xml, Class ObjClass) throws SGConverterException;

}
