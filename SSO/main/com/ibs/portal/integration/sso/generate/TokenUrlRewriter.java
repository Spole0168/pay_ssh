package com.ibs.portal.integration.sso.generate;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.IUserIdManager;
import com.ibs.portal.integration.sso.Token;

/**
 * URL重写, 加入当前系统令牌
 *
 * @author luoyue
 *
 */
public class TokenUrlRewriter {

	private static Logger logger = LoggerFactory.getLogger(TokenUrlRewriter.class);
	/**
	 * 重写URL
	 *
	 * @param url 原始URL
	 * @param session 当前会话
	 * @return 加入当前系统令牌的URL
	 * @throws IOException
	 */
	public static String rewrite(String url, HttpSession session) throws IOException {
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter source url: " + url);
		if (url == null)
			throw new java.lang.IllegalArgumentException("url is null!");
		url = url.trim();
		int i = url.indexOf('?');
		StringBuffer buf = new StringBuffer(url);
		buf.append(i >= 0 ? "&" : "?");
		buf.append(ConfigFactory.TOKEN_PARAMETER_KEY);
		buf.append("=");
		// buf.append(URLEncoder.encode(generate(session).toString(), "UTF-8"));
		buf.append(URLEncoder.encode(TokenUtils.encodeString(generate(session).toString()), "UTF-8"));
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter result url: " + buf.toString());
		return buf.toString();
	}

	/**
	 * 通过当前会话生成令牌
	 *
	 * @param session 当前会话
	 * @return 令牌
	 */
	public static Token generate(HttpSession session) {
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter session: " + session);
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter session id: " + session == null ? null : session.getId());
		IUserIdManager userIdManager = ConfigFactory.getUserIdManager();
		String userId = userIdManager.getUserId(session);
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter user id: " + userId);
		String sessionId = session.getId();
		String applicationId = ConfigFactory.getCurrentApplicationId();
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter applicationId: " + applicationId);
		return new Token(userId, sessionId, applicationId);
	}

}
