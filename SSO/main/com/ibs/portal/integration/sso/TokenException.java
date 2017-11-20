package com.ibs.portal.integration.sso;

/**
 * 令牌异常基类
 *
 * @author luoyue
 *
 */
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造令牌字符串格式化异常
	 */
	public TokenException() {
		super();
	}

	/**
	 * 构造令牌字符串格式化异常
	 *
	 * @param message 异常信息
	 * @param cause 异常信息
	 */
	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造令牌字符串格式化异常
	 *
	 * @param cause 异常信息
	 */
	public TokenException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造令牌字符串格式化异常
	 *
	 * @param message 异常信息
	 */
	public TokenException(String message) {
		super(message);
	}

}
