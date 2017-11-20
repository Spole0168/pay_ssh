package com.ibs.portal.integration.sso;

/**
 * 令牌字符串格式化异常
 *
 * @author luoyue
 *
 */
public class TokenFormatException extends TokenException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造令牌字符串格式化异常
	 *
	 * @param message 异常信息
	 */
	public TokenFormatException(String message) {
		super(message);
	}

}
