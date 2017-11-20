package com.ibs.portal.integration.sso.access;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.Token;
import com.ibs.portal.integration.sso.TokenException;

/**
 * 过滤器. 在websphere下不能接收sso请求, 建议使用TokenServlet.
 *
 * @see com.ibs.integration.sso.access.TokenServlet
 * @author luoyue
 *
 */
public class TokenFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final TokenValidateClient tvc = new TokenValidateClient();

	public void init(FilterConfig config) throws ServletException {
		ConfigFactory.init(config.getServletContext());
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		
		if (logger.isDebugEnabled())
			logger.debug("TokenFilter.doFilter().......");
		String strToken = request.getParameter(ConfigFactory.TOKEN_PARAMETER_KEY);
		if (strToken != null) {
			logger.debug("[from sending]strToken : " + strToken);
			HttpServletResponse response = (HttpServletResponse)resp;
			HttpSession session = request.getSession(true);
			logger.debug("filter:" + request.getRequestURI());
			System.out.println("filter:" + request.getRequestURI());
			String userId = ConfigFactory.getUserIdManager().getUserId(session);
			logger.debug("userId:" + userId);
			logger.debug("[from sending]begin enter in tokens parse and validation");
			try {
				// 验证令牌
				Token token = Token.valueOf(strToken);
				String tokenUserId = ConfigFactory.resolveUserId(token);
				// 如果未登录, 则检查令牌
				if (userId == null || ! userId.equalsIgnoreCase(tokenUserId)) {
					tvc.validate(token);
					logger.debug("success login with user id : " + token.getUserId());
					ConfigFactory.setUserId(session, token);
				}
			} catch (TokenException e) {
				logger.debug("[validation] error!!!", e);
				// 验证过程异常返回错误码
				System.out.println(e.getMessage());
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
				logger.debug("*************** send to client with response code: " + HttpServletResponse.SC_FORBIDDEN);
				return;
			} catch (IOException e) {
				logger.debug("[validation] error!!!", e);
				// 连接过程异常返回错误码
				System.out.println(e.getMessage());
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
				logger.debug("*************** send to client with response code: " + HttpServletResponse.SC_NO_CONTENT);
				return;
			} catch (Exception e) {
				logger.debug("[validation] error!!!", e);
				// 连接过程异常返回错误码
				System.out.println(e.getMessage());
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
				logger.debug("*************** send to client with response code: " + HttpServletResponse.SC_NO_CONTENT);
				return;
			}
		}
		chain.doFilter(req, resp);
	}

}
