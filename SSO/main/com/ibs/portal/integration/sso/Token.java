package com.ibs.portal.integration.sso;

/**
 * 令牌信息
 *
 * @author luoyue
 *
 */
public final class Token {

	/**
	 * 从字符串格式化构造令牌, 与toString()方法对应.
	 *
	 * @param strToken 令牌字符串
	 * @return 令牌
	 * @throws TokenFormatException 当字符串格式错误或信息不完整时抛出
	 * @see com.ibs.integration.sso.Token#toString()
	 */
	public static Token valueOf(String strToken) throws TokenFormatException {
		if (strToken == null)
			throw new TokenFormatException("token is null!");
		String[] ts = strToken.split("\\|");
		if (ts.length != 3)
			throw new TokenFormatException("illegal token!");
		String userId = ts[0];
		if (userId == null || userId.length() == 0)
			throw new TokenFormatException("illegal token!");
		String sessionId = ts[1];
		if (sessionId == null || sessionId.length() == 0)
			throw new TokenFormatException("illegal token!");
		String applicationId = ts[2];
		if (applicationId == null || applicationId.length() == 0)
			throw new TokenFormatException("illegal token!");
		return new Token(userId, sessionId, applicationId);
	}

	//  用户ID
	private final String userId;

	// 会话ID
	private final String sessionId;

	// 应用系统ID
	private final String applicationId;

	/**
	 * 构造令牌
	 *
	 * @param userId 用户ID
	 * @param sessionId 会话ID
	 */
	public Token(String userId, String sessionId, String applicationId) {
		this.userId = userId;
		this.sessionId = sessionId;
		this.applicationId = applicationId;
	}

	/**
	 * 获取用户ID
	 *
	 * @return 用户ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 获取会话ID
	 *
	 * @return 会话ID
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 获取应用系统ID
	 *
	 * @return 应用系统ID
	 */
	public String getApplicationId() {
		return applicationId;
	}

	public String toString() {
		return userId + "|" + sessionId + "|" + applicationId;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Token other = (Token) obj;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
