package com.ibs.portal.integration.sso.access;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.integration.sso.Application;
import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.Token;
import com.ibs.portal.integration.sso.TokenValidationException;

/**
 * 令版验证客户产端
 *
 * @author luoyue
 *
 */
public class TokenValidateClient {

	private static final Logger logger = LoggerFactory.getLogger(TokenValidateClient.class);

	/**
	 * 验证令牌是否有
	 *
	 * @param token
	 *            令牌
	 * @return
	 * @throws IOException
	 * @throws TokenValidationException
	 */ 
	public void validate(Token token) throws IOException,
			TokenValidationException {
		logger.debug("begin sending token to remote server for validate: "
				+ token.getApplicationId());
		
		logger.debug("[sso] client app id: " + token.getApplicationId());
//		if (ConfigFactory.getCurrentApplicationId().equals(
//				token.getApplicationId()))
//			throw new TokenValidationException(
//					"产生令牌方的<sso currentApplication=\""
//							+ token.getApplicationId() + "\">配置错误! ID不能与接收方一样!");
		Application sc = ConfigFactory.getApplication(token.getApplicationId());
		logger.debug("remote server url: " + sc.getValidateURL());
		logger.debug("[sso] client send token to server: "
				+ sc.getValidateURL());
		URL url = new URL(sc.getValidateURL() + token.getSessionId());
		logger.debug("[sso] client send token to server url: "
				+ sc.getValidateURL() + token.getSessionId());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try {
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.addRequestProperty(ConfigFactory.TOKEN_HEADER_KEY, token.toString());
			conn.connect();
			
//			ObjectOutputStream out = new ObjectOutputStream(conn
//					.getOutputStream());
//			out.writeObject(token.toString());
			
//			System.out.println("[sso] client send token: "
//					+ token.toString());
			int code = conn.getResponseCode();
			if (logger.isDebugEnabled()) {
				logger.debug("remote server response code is:" + code);
				logger.debug("remote server response message is:" + conn.getResponseMessage());
			}
			if (code < 200 || code > 300)
				throw new TokenValidationException(conn.getResponseCode()
						+ ":" + conn.getResponseMessage());
		} finally {
			conn.disconnect();
		}
	}

}
