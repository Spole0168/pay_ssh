package com.ibs.common.module.frameworkimpl.intergration.converter;

import java.io.StringReader;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.IXMLWriter;
import org.jibx.runtime.JiBXException;

import com.ibs.common.module.frameworkimpl.intergration.ISGConverter;
import com.ibs.common.module.frameworkimpl.intergration.SGConverterException;
import com.ibs.common.module.frameworkimpl.intergration.converter.jibx.CustomXMLWriter;

/**
 * <pre>
 * *********************************************
 * Description: JiBX实现的消息转换器
 * *********************************************
 * </pre>
 */
public class JiBXConverter implements ISGConverter {

	private Class clazz;

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Object fromXML(String xml) throws SGConverterException {
		return fromXML(xml, clazz);
	}

	public Object fromXML(String xml, Class ObjClass) throws SGConverterException {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(ObjClass);
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();

			StringReader sr = new StringReader(xml);
			return uctx.unmarshalDocument(sr, null);
		} catch (JiBXException e) {
			throw new SGConverterException(e);
		}
	}

	public String toXML(Object to) throws SGConverterException {
		StringWriter sw = new StringWriter();
		IBindingFactory bfact;
		try {
			bfact = BindingDirectory.getFactory(to.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			mctx.marshalDocument(to, "UTF-8", null, sw);
			return sw.toString();
		} catch (JiBXException e) {
			// 当对象to有非法字符时，会有异常，此时，用自定义的Writer来转换XML
			return toCustomXML(to);
		}
	}

	/**
	 * 用自定义的XMLWriter转换XML
	 * 
	 * @param to
	 * @return
	 * @throws SGConverterException
	 */
	private String toCustomXML(Object to) throws SGConverterException {
		StringWriter sw = new StringWriter();
		IBindingFactory bfact;
		try {
			bfact = BindingDirectory.getFactory(to.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			// 创建自定义Writer
			IXMLWriter writer = new CustomXMLWriter(bfact.getNamespaces());
			mctx.setXmlWriter(writer);
			mctx.setIndent(2);
			mctx.marshalDocument(to, "UTF-8", null, sw);
			return sw.toString();
		} catch (JiBXException e) {
			throw new SGConverterException(e);
		}
	}

}
