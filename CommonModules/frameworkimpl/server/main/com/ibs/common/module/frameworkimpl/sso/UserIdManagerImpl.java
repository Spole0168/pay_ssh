package com.ibs.common.module.frameworkimpl.sso;

import javax.servlet.http.HttpSession;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.integration.sso.IUserIdManager;

public class UserIdManagerImpl implements IUserIdManager {

	//获取用户ID
	public String getUserId(HttpSession session) {
		if(session == null) {
			return null;
		}
		
		String userId = (String)session.getAttribute(UserContext.USER_UID_KEY);
		
		return userId;
	}

	//设置用户ID
	public void setUserId(HttpSession session, String userId) {
		UserContext.getUserContext().setUserId(session,userId);
	}

}
