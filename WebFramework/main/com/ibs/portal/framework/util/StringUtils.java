package com.ibs.portal.framework.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDHexGenerator;

import edu.emory.mathcs.backport.java.util.Arrays;

//import bsh.This;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static final String ENCODE_ISO8859_1 = "iso8859-1";

	private static final String PARAM_SEPARATOR = ";";
	private static final String KEY_VALUE_SEPARATOR = "=";

	// UTF-8

	public static String getStringAsISO8859_1(String source)
			throws UnsupportedEncodingException {
		if (null == source)
			return source;

		return new String(source.getBytes(ENCODE_ISO8859_1));
	}

	public static List<String> delimiterScanner(String arg, String delimiter) {
		return delimiterScanner(arg, delimiter, false);
	}

	/**
	 * 去掉空格
	 * 
	 * @param arg
	 * @param delimiter
	 * @param trim
	 * @return
	 */
	public static List<String> delimiterScanner(String arg, String delimiter,
			boolean trim) {
		if (isEmpty(arg) || "null".equals(arg)) {
			return null;
		}

		List<String> ret = new ArrayList<String>(0);
		Scanner tokenize = new Scanner(arg).useDelimiter(delimiter);
		while (tokenize.hasNext()) {
			if (trim)
				ret.add(tokenize.next().trim());
			else
				ret.add(tokenize.next());
		}

		if (ret.isEmpty())
			ret.add(arg);

		return ret;
	}

	public static Map<String, String> parseParameter(String bizParameters) {
		return parseParameter(bizParameters, PARAM_SEPARATOR,
				KEY_VALUE_SEPARATOR);
	}

	public static Map<String, String> parseParameter(String bizParameters,
			String paramSeparator, String keyValueSeparator) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		if (!isEmpty(bizParameters)) {
			List<String> pairs = delimiterScanner(bizParameters, paramSeparator);
			for (String pair : pairs) {
				List<String> item = delimiterScanner(pair, keyValueSeparator);
				if (item.size() != 2)
					continue;
				paramsMap.put(item.get(0), item.get(1));
			}
		}
		return paramsMap;
	}

	public static boolean isEmpty(String arg) {
		return (null == arg || arg.trim().length() < 1);
	}

	public static boolean isNotEmpty(String arg) {
		return !isEmpty(arg);
	}

	/**
	 * convert line break convert "\n" to </br>
	 * 
	 * @param source
	 * @return
	 */
	public static String convertLineBreak(String source) {
		String rtn = "";
		if (source == null) {
			return rtn;
		}
		rtn = source.replace("\n", "</br>");
		return rtn;
	}

	public static String getUUID() {
		IdentifierGenerator gen2 = new UUIDHexGenerator();
		return (String) gen2.generate(null, null);
	}

	/**
	 * 对html标签进行转换
	 * 
	 * @param txt
	 * @return
	 */
	public static String toTextForHtml(String txt) {
		if (null == txt) {
			return "";
		}
		txt = txt.replaceAll("&", "&amp;");
		txt = txt.replaceAll("<", "&lt;");
		txt = txt.replaceAll(">", "&gt;");
		txt = txt.replaceAll("\"", "&quot;");
		txt = txt.replaceAll("'", "&#146;");
		return txt;
	}

	/**
	 * 对html标签进行转换
	 * 
	 * @param <T>
	 * @param txt
	 * @return
	 */
	public static <T> void toTextForHtml(Collection<T> txt) {
		List list = new ArrayList();
		if (txt != null) {
			for (Iterator iterator = txt.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				toTextForHtml(object.toString());
				list.add(object);
			}
			txt = list;
		}
	}

	/**
	 * 对单引号进行转换
	 * 
	 * @param orginalStr
	 * @return
	 */
	public static String escapeSQL(String orginalStr) {
		return orginalStr.replaceAll("'", "''");
	}

	public static void main(String[] args) {
		/*
		 * String s = "stateId//userId/orgId/positionId/userName";
		 * System.out.println(delimiterScanner(s, "/"));
		 */
		String waybillNo = ",,,  ,,  , , qwqwqwqw";
		System.out.println(hasWaybillNoInput(waybillNo));
		getWaybillNoSql(waybillNo);
	}

	public static boolean hasWaybillNoInput(String waybillNo) {
		if (org.apache.commons.lang.StringUtils.isBlank(waybillNo)) {
			return false;
		}
		String[] temp_waybillNo = waybillNo.split(",");
		for (int i = 0; i < temp_waybillNo.length; i++) {
			if (org.apache.commons.lang.StringUtils
					.isNotBlank(temp_waybillNo[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 把以逗号分隔的运单号转换为SQL拼接形式
	 * 
	 * @param waybillNo
	 * @return
	 */
	public static String getWaybillNoSql(String waybillNo) {
		StringBuffer buf_waybillNo = new StringBuffer();
		String[] temp_waybillNo = waybillNo.split(",");
		for (int i = 0; i < temp_waybillNo.length; i++) {
			if (org.apache.commons.lang.StringUtils
					.isNotBlank(temp_waybillNo[i])) {
				buf_waybillNo.append("'" + escapeSQL(temp_waybillNo[i].trim())
						+ "',");
			}
		}
		if (null != buf_waybillNo && buf_waybillNo.length() > 0) {
			buf_waybillNo.deleteCharAt(buf_waybillNo.length() - 1);
		}

		return buf_waybillNo.toString();
	}

	public static <T> List<String> concatOracleInString(List<T> values) {
		if (values == null || values.size() == 0)
			return null;

		int maxSize = 1000;
		List<String> results = new ArrayList<String>();

		String sep = "";

		T t = null;
		if (t instanceof String) {
			sep = "'";
		} else if (t instanceof Long || t instanceof Integer
				|| t instanceof Short) {

		} else {
			sep = "'";
		}

		StringBuffer condition = new StringBuffer();
		for (int i = 0; i < values.size(); ++i) {
			if (i % maxSize == 0 && i > 0) {
				results.add(condition.toString());
				condition = new StringBuffer();
			}

			condition.append(sep).append(values.get(i).toString()).append(sep);

			if (i < values.size() - 1) {
				condition.append(",");
			}
		}

		if (results.size() == 0 && condition.length() != 0)
			results.add(condition.toString());

		return results;
	}

	@SuppressWarnings("unchecked")
	public static List<String> splitCheckBoxString(String value) {
		if (StringUtils.isEmpty(value))
			return new ArrayList<String>(0);

		String[] strs = value.replaceAll(" ", "").split(",");

		return Arrays.asList(strs);
	}

	public static String getStringFromClob(Clob clob) {
		try {
			Reader reader = clob.getCharacterStream();
			BufferedReader in = new BufferedReader(reader);
			String buffer;
			StringBuffer output = new StringBuffer();
			while (!(null == (buffer = in.readLine()))) {
				output.append(buffer);
			}
			return output.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getCompactStringFromJson(JSONObject jsonObject) {

		if (jsonObject == null)
			return null;

		try {
			Iterator keys = jsonObject.keys();
			StringBuffer sb = new StringBuffer("{");

			while (keys.hasNext()) {
				Object key = keys.next();
				Object value = jsonObject.get(key);

				if (value == null)
					continue;
				if (value instanceof String
						&& StringUtils.isEmpty((String) value))
					continue;

				if (sb.length() > 1) {
					sb.append(',');
				}

				sb.append(JSONUtils.quote(key.toString()));
				sb.append(':');
				sb.append(JSONUtils.valueToString(jsonObject.get(key)));
			}
			sb.append('}');
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
