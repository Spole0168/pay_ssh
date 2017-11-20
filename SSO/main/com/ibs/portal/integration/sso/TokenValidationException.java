package com.ibs.portal.integration.sso;

/**
 * 令牌验证异常
 *
 * @author luoyue
 *
 */
public class TokenValidationException extends TokenException {

	private static final long serialVersionUID = 1L;

	public TokenValidationException() {
		super();
	}

	public TokenValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenValidationException(String message) {
		super(message);
	}

	public TokenValidationException(Throwable cause) {
		super(cause);
	}

}
