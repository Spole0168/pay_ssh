package com.ibs.core.module.mdmbasedata.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.security.dao.IUserDao;
import com.ibs.common.module.frameworkimpl.security.dto.UserSimpleInfo;
import com.ibs.core.module.mdmbasedata.biz.UserQueryBiz;
import com.ibs.portal.framework.server.biz.BaseBiz;

public class UserQueryBizImpl extends BaseBiz implements UserQueryBiz {

	private static Logger logger = LoggerFactory
			.getLogger(UserQueryBizImpl.class);

	private IUserDao userDao;

	public List<UserSimpleInfo> listUser(String[] userTypes, String[] status, String userCodeOrNameOrPinYin,
			Integer recordsSize) {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: listUser() "); 
		return userDao.findUniversalUser(UserSimpleInfo.class, userTypes, status, userCodeOrNameOrPinYin,
				recordsSize);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
