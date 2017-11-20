package com.ibs.portal.integration.sso.validate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.TokenException;

/**
 * 令牌验证Servlet
 *
 * @author luoyue
 *
 */
public class TokenValidateServlet extends HttpServlet {

	private static final long serialVersionUID = -5781567481020208874L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final TokenValidator tokenValidator = new TokenValidator();

	public void init() throws ServletException {
		super.init();
		ConfigFactory.init(super.getServletContext());
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String token = req.getHeader(ConfigFactory.TOKEN_HEADER_KEY);

			logger.debug("begin validate token on remote server, token2 is : " + token);
			System.out.println("begin validate token on remote server, token2 is : " + token);
//			ObjectInputStream in =  new ObjectInputStream(req.getInputStream());
//			String token = null ;
//			Object remoteSIGN = in.readObject();
//			if (null != remoteSIGN)
//				token = remoteSIGN.toString();
			logger.debug("begin validate token on remote server, token is : " + token);
			tokenValidator.validate(token, req.getSession());
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (TokenException e) {
			logger.debug("TokenValidateServlet===TokenException:" + e.getMessage(), e);
			System.out.println("令牌验证失败: " + e.getMessage());
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		} catch (Exception e) {
			logger.debug("TokenValidateServlet===Exception:" + e.getMessage(), e);
			System.out.println("令牌验证失败: " + e.getMessage());
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}
}
