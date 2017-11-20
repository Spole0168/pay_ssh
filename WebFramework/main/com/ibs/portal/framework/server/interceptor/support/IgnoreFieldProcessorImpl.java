package com.ibs.portal.framework.server.interceptor.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.util.PropertyFilter;

public class IgnoreFieldProcessorImpl implements PropertyFilter {
	
	private static Logger logger = LoggerFactory.getLogger(IgnoreFieldProcessorImpl.class);

	/**
	 * 忽略的属性名称
	 */
	private String[] fields;

	/**
	 * 空参构造方法<br/> 默认不忽略集合
	 */
	public IgnoreFieldProcessorImpl() {
		// empty
	}

	/**
	 * 构造方法
	 * 
	 * @param fields
	 *            忽略属性名称数组
	 */
	public IgnoreFieldProcessorImpl(String[] fields) {
		this.fields = fields;
	}

	public boolean apply(Object source, String name, Object value) {		

		// 忽略设定的属性
		if (fields != null && fields.length > 0) {
			if (ignore(name)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * 过滤忽略的属性
	 * 
	 * @param name
	 * @return
	 */
	public boolean ignore(String name) {
		boolean b = false;
		for (String field : fields) {
			if (name.equals(field)) {
				b = true;
			}
		}
		return b;
	}

	public String[] getFields() {
		return fields;
	}

	/**
	 * 设置忽略的属性
	 * 
	 * @param fields
	 */
	public void setFields(String[] fields) {
		this.fields = fields;
	}
}
