package com.ibs.portal.integration.sso;

/**
 * 配置异常
 *
 * @author luoyue
 *
 */
public class ConfigException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造配置异常
	 *
	 * @param message 异常信息
	 */
	public ConfigException(String message) {
		super(message);
	}

	/**
	 * 构造配置异常
	 *
	 * @param message 异常信息
	 * @param cause 异常原因
	 */
	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}

}
