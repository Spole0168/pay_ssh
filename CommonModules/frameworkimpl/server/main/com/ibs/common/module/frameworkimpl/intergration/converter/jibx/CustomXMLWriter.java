package com.ibs.common.module.frameworkimpl.intergration.converter.jibx;

import java.io.IOException;

import org.jibx.runtime.impl.GenericXMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * *********************************************
 * Description: 自定义XMLWriter
 *   解决非法字符在转XML时的问题
 * *********************************************
 * </pre>
 */
public class CustomXMLWriter extends GenericXMLWriter {

	private static Logger logger = LoggerFactory.getLogger(CustomXMLWriter.class);

	public CustomXMLWriter(String[] uris) {
		super(uris);
	}

	/**
	 * 重载方法，当有非法字符时，删除非法字符
	 */
	public void writeTextContent(String text) throws IOException {
		try {
			super.writeTextContent(text);
		} catch (IOException e) {
			String newText = removeInvalidChar(text);
			super.writeTextContent(newText);
			logger.warn("remove invalid character", e);
		}
	}

	/**
	 * 删除非法字符
	 * 
	 * @param text
	 * @return
	 */
	private String removeInvalidChar(String text) {
		if (text != null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (validChar(c)) {
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return text;
	}

	/**
	 * 判断是否合法字符
	 * 
	 * @param chr
	 * @return
	 */
	private boolean validChar(char chr) {
		if (chr < 0x20) {
			if (chr != 0x9 && chr != 0xA && chr != 0xD) {
				return false;
			}
		} else if (chr > 0xD7FF && (chr < 0xE000 || chr == 0xFFFE || chr == 0xFFFF || chr > 0x10FFFF)) {
			return false;
		}
		return true;
	}
}
