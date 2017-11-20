package com.ibs.portal.framework.server.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.ibs.portal.framework.util.DateUtils;

public class DateConverter extends StrutsTypeConverter {
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length == 0)
			return null;
		if (toClass.isArray()) {
			Date[] dates = new Date[values.length];
			for (int i = 0, n = dates.length; i < n; i++) {
				dates[i] = DateUtils.convert(values[i]);
			}
			return dates;
		}
		return DateUtils.convert(values[0]);
	}

	public String convertToString(Map context, Object value) {
		if (!(value instanceof Date))
			return String.valueOf(value);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
	}
}
