package com.ibs.portal.framework.server.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;

public abstract class BaseBiz {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 返回当前用户的用户名
	 * 
	 * @return
	 */
	protected String getUserName() {
		return UserContext.getUserContext().getUserName();
	}

	
	/**
	 * 返回当前用户
	 * 
	 * @return
	 */
	protected IUser getCurrentUser() {
		return UserContext.getUserContext().getCurrentUser();
	}

}
