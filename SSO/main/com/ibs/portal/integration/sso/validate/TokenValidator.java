package com.ibs.portal.integration.sso.validate;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.Token;
import com.ibs.portal.integration.sso.TokenValidationException;

/**
 * 令版验证器 
 * 
 * @author luoyue
 * 
 */
public class TokenValidator {

	private Logger logger = LoggerFactory.getLogger(getClass());
 
	/**
	 * 验证令牌是否有效
	 * 
	 * @param tokenString
	 *            令牌信息串
	 * @throws TokenValidationException
	 *             令牌无效时抛出
	 */
	public void validate(String tokenString, HttpSession session)
			throws TokenValidationException {
		logger.debug("validate tokenString: " + tokenString);

		// 前置条件, 验证令牌不为空
		if (tokenString == null)
			throw new TokenValidationException("令牌不能为空值!");

		// 创建令牌
		Token token = Token.valueOf(tokenString);

		// 验证令牌是否完整
		if (token.getUserId() == null || token.getSessionId() == null
				|| token.getApplicationId() == null)
			throw new TokenValidationException("令牌的 userId:"
					+ token.getUserId() + "sessionId:" + token.getSessionId()
					+ "applicationId:" + token.getApplicationId() + " 不能有空值!");

		// 查找会话信息
		// HttpSession session = SessionHolder.getSession(token.getSessionId());

		// 验证会话不为空
		if (session == null)
			throw new TokenValidationException("sessionId:"
					+ token.getSessionId() + "所指的session不存在.");

		// 测试
		// System.out.println("当前session里的用户名:" +
		// ConfigFactory.getUserIdManager().getUserId(session));
		// HttpSession _session =
		// SessionHolder.getSession(token.getSessionId());
		// System.out.println("尝试找session:" + _session);
		// Enumeration e = _session.getAttributeNames();
		// while (e.hasMoreElements()) {
		// Object o = e.nextElement();
		// System.out.println("尝试找session Element Name:" + o);
		// System.out.println("尝试找session Element Value:" +
		// _session.getAttribute(o.toString()));
		// }

		String userInSession = ConfigFactory.getUserIdManager().getUserId(session);
		logger.debug("UserId in HttpSession: " + userInSession);
		logger.debug("UserId in token: " + token.getUserId());
		// 验证会话用户是否匹配
		if (!token.getUserId().equals(userInSession)) {
			// PORTAL SERVER获取不到，尝试从SessionHolder里寻找

			logger.debug("search for uid from SessionHolder...");
			System.out.println("search for uid from SessionHolder...");

			HttpSession _session = SessionHolder.getSession(token
					.getSessionId());
			if (!token.getUserId().equals(
					ConfigFactory.getUserIdManager().getUserId(_session)))
				throw new TokenValidationException("userId:"
						+ token.getUserId() + "与sessionId:"
						+ token.getSessionId() + "不匹配!");
		}
	}

}
