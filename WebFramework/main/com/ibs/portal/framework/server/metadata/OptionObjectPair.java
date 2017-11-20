package com.ibs.portal.framework.server.metadata;

import java.io.Serializable;

/**
 * 键值对,用于下拉框等
 * 
 * @author luoyue
 * 
 */
public class OptionObjectPair implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5529507407512236563L;

	private String key;
	private String value;
	private String extend;
	
	private static OptionObjectPair defaultOptionObjectPair = new OptionObjectPair(
			"", "不限定");

	public OptionObjectPair() {

	}

	public OptionObjectPair(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public OptionObjectPair(String key, String value, String extend) {
		this.key = key;
		this.value = value;
		this.extend = extend;
	}

	public static OptionObjectPair getDefaultOptionObjectPair() {
		return defaultOptionObjectPair;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
