package com.ibs.portal.framework.server.interceptor.support;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibs.portal.framework.util.DateUtils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private String dateFormat = DEFAULT_DATE_PATTERN;

	public JsonDateValueProcessor() {

	}

	public JsonDateValueProcessor(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		if (value == null)
			return null;

		String[] array = (String[]) value;
		Date[] dates = new Date[array.length];
		for (int i = 0, n = dates.length; i < n; i++) {
			dates[i] = DateUtils.convert(array[i]);
		}
		return dates;
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if (value == null)
			return null;

		return new SimpleDateFormat(dateFormat).format(value);
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

}
