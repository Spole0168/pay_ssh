package com.ibs.common.module.frameworkimpl.intergration.converter;

import com.ibs.common.module.frameworkimpl.intergration.ISGConverter;
import com.ibs.common.module.frameworkimpl.intergration.SGConverterException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * <pre>
 * *********************************************
 * Description: XStream实现的消息转换器
 * *********************************************
 * </pre>
 */
public class XStreamConverter implements ISGConverter {

	private static XStream xstream = new XStream(new XppDriver());

	public Object fromXML(String xml) throws SGConverterException {
		try {
			return xstream.fromXML(xml);
		} catch (Exception e) {
			throw new SGConverterException(e);
		}
	}

	public Object fromXML(String xml, Class ObjClass) throws SGConverterException {
		return fromXML(xml);
	}

	public String toXML(Object to) throws SGConverterException {
		try {
			return xstream.toXML(to);
		} catch (Exception e) {
			throw new SGConverterException(e);
		}
	}

}
