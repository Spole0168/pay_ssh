package com.ibs.portal.integration.sso;

import javax.servlet.http.HttpSession;

/**
 * 用户凭证管理策略接口
 *
 * @author luoyue
 *
 */
public interface IUserIdManager {

	/**
	 * 获取用户凭证
	 *
	 * @param session 会话信息
	 * @return 用户凭证, 未登录时返回null
	 */
	public abstract String getUserId(HttpSession session);

	/**
	 * 注册用户凭证 (完成登录)
	 *
	 * @param session 会话信息
	 * @param userId 用户凭证
	 */
	public void setUserId(HttpSession session, String userId);

}
