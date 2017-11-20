package com.ibs.portal.framework.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;

public abstract class BaseService {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * ��ȡ��ǰ��¼�û��� (��ݹ��ߺ���)
	 *
	 * @return ��ǰ��¼�û���
	 */
	protected String getUserName() {
		return UserContext.getUserContext().getUserName();
	}

	/**
	 * ��ȡ��ǰ�û���Ϣ
	 *
	 * @return ��ǰ�û���Ϣ
	 */
	protected IUser getCurrentUser() {
		return UserContext.getUserContext().getCurrentUser();
	}

}
