package com.ibs.portal.integration.sso;


/**
 * SSO配置
 *
 * @author luoyue
 *
 */
public class Application {

	private final String id;

	private final String validateURL;

	public Application(String id, String validateURL) {
		super();
		this.id = id;
		this.validateURL = validateURL;
	}

	public String getId() {
		return id;
	}

	public String getValidateURL() {
		return validateURL;
	}
}
