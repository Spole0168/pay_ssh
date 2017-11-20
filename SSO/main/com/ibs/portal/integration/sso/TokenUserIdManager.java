package com.ibs.portal.integration.sso;

/**
 * 用户凭证管理策略接口
 *
 * @author luoyue
 *
 */
public interface TokenUserIdManager extends IUserIdManager {

	/**
	 * 转换用户凭证
	 *
	 * @param token 令牌信息
	 * @return 用户凭证
	 */
	public abstract String resolveUserId(Token token);

}
