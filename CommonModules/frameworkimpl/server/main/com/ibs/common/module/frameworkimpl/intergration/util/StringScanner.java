package com.ibs.common.module.frameworkimpl.intergration.util;

import java.util.StringTokenizer;

/**
 * <pre>
 * *********************************************
 * Description: StringScanner
 * *********************************************
 * </pre>
 */
public class StringScanner extends StringTokenizer {

	public StringScanner(String str, String delim, boolean returnDelims) {
		super(str, delim, returnDelims);
		this.delimiters = delim;
	}

	public StringScanner(String str, String delim) {
		this(str, delim, true);
	}

	public StringScanner(String str) {
		this(str, " \t\n\r\f");
	}

	/**
	 * Override nextToken方法，当遇到delimiters时，再读取下一个Token
	 */
	public String nextToken() {
		String token = super.nextToken();
		boolean preCommas = isCommas;
		isCommas = delimiters.equals(token);
		if (!preCommas && isCommas) {
			return hasMoreTokens() ? nextToken() : "";
		}
		return isCommas ? "" : token;
	}

	private String delimiters;

	private boolean isCommas = true;
}
