package com.ibs.portal.framework.server.interceptor.support;

import net.sf.json.processors.DefaultValueProcessor;

public class JsonNumberValueProcessor implements DefaultValueProcessor {

	@SuppressWarnings("unchecked")
	public Object getDefaultValue(Class type) {
		return "";
		// return JSONNull.getInstance();
	}
}
