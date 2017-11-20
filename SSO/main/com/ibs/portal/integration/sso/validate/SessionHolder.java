package com.ibs.portal.integration.sso.validate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 会话持有者, 用于查找Session
 *
 * @author luoyue
 *
 */
public class SessionHolder implements HttpSessionListener {

	// -FIX ME-DONE 未完善并发, 活性较低
	private static Map map = new ConcurrentHashMap(); // Collections.synchronizedMap(new HashMap());

	private static Logger logger = LoggerFactory.getLogger(SessionHolder.class);

	public void sessionCreated(HttpSessionEvent e) {
		logger.debug("[SessionHolder] ======== sessionCreated:" + e.getSession().getId());
		map.put(e.getSession().getId(), e.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent e) {
		logger.debug("[SessionHolder] ======== sessionDestroyed:" + e.getSession().getId());
		map.remove(e.getSession().getId());
	}

	/**
	 * 通过会话ID获取会话信息
	 *
	 * @param sessionId 会话ID
	 * @return 会话信息
	 */
	public static HttpSession getSession(String sessionId) {
		logger.debug("[SessionHolder] ======== all sessions:" + map.size());
		HttpSession session = (HttpSession)map.get(sessionId);
		logger.debug("[SessionHolder] ======== get session:" + (session == null ? "null" : session.getId()));
		return session;
	}

}
