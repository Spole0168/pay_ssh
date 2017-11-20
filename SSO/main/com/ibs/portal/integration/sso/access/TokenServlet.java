package com.ibs.portal.integration.sso.access;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.integration.sso.ConfigFactory;
import com.ibs.portal.integration.sso.Token;
import com.ibs.portal.integration.sso.TokenValidationException;
import com.ibs.portal.integration.sso.generate.TokenUtils;

/**
 * 令牌接收处理器
 *
 * @author luoyue
 *
 */
public class TokenServlet extends HttpServlet {

	private static final long serialVersionUID = 5195595204300406233L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void init() throws ServletException {
		super.init();
		ConfigFactory.init(super.getServletContext());
	}

	private final TokenValidateClient tvc = new TokenValidateClient();

	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("TokenServlet.doGet().......");
		try {
			logger.debug("TokenHandler.doToken()....");
			HttpSession session = request.getSession(true);
			logger.debug("filter:" + request.getRequestURI());
			System.out.println("filter:" + request.getRequestURI());
			String userId = ConfigFactory.getUserIdManager().getUserId(session);
			logger.debug("userId:" + userId);
			logger.debug("[from sending]begin enter in tokens parse and validation");
			// 截取令牌
			String strToken = request.getParameter(ConfigFactory.TOKEN_PARAMETER_KEY);
			strToken = TokenUtils.decodeString(strToken);
			logger.debug("[from sending]strToken : " + strToken);
			try {
				// 验证令牌
				Token token = Token.valueOf(strToken);
				// 如果未登录, 则检查令牌
				if (userId == null || !userId.equals(token.getUserId())) {
					tvc.validate(token);
					logger.debug("success login with user id : " + token.getUserId());
					ConfigFactory.setUserId(session, token);
				}else{
//					tvc.validate(token);
//					logger.debug("success login with user id : " + token.getUserId());
				}
			} catch (TokenValidationException e) {
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

			// 拼接实际页面路径
			String context = request.getContextPath();
			String uri = request.getRequestURI().toString();
			String qry = request.getQueryString();
			String forward = uri.substring(context.length(), uri.length() - ConfigFactory.TOKEN_URL_SUFFIX.length());
			int idx = qry.indexOf(ConfigFactory.TOKEN_PARAMETER_KEY + "=");
			if (idx > 1) {
				forward += "?" + qry.substring(0, idx - 1);
			}
			// 通过检查, 显示请求页
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
			logger.debug("dispatcher:" + dispatcher);
			if (null != dispatcher) {
				System.out.println("forward:" + forward);
				logger.debug("forward to:" + forward);
				dispatcher.forward(request, response);
			} else {
				String redirect = (null == context || "/".equals(context)
						?  "" : context) + forward;
				System.out.println("redirect:" + redirect);
				logger.debug("redirect to:" + redirect);
				response.sendRedirect(redirect);
			}
		} catch (Exception e) {
			logger.debug("[validation] error!!!", e);
			// 连接过程异常返回错误码
			System.out.println(e.getMessage());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			logger.debug("*************** send to client with response code: " + HttpServletResponse.SC_FORBIDDEN);
			return;
		}
	}

}
