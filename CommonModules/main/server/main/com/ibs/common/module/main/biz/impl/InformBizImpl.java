package com.ibs.common.module.main.biz.impl;

import java.util.List;

import com.ibs.common.module.main.biz.IInformBiz;
import com.ibs.common.module.main.dao.IMainInformDao;
import com.ibs.common.module.main.dao.IMessageInformDao;
import com.ibs.common.module.main.domain.MainInform;
import com.ibs.common.module.main.domain.MessageInform;
import com.ibs.portal.framework.server.biz.BaseBiz;

/**
 * 提醒BIZ实现
 * @author 
 * $Id: InformBizImpl.java 20049 2010-12-07 08:05:57Z  $
 */
public class InformBizImpl extends BaseBiz implements IInformBiz {
	
	private IMainInformDao mainInformDao;
	private IMessageInformDao messageInformDao;
	
	// *************************  get/set methods ************************* //

	public IMainInformDao getMainInformDao() {
		return mainInformDao;
	}

	public void setMainInformDao(IMainInformDao mainInformDao) {
		this.mainInformDao = mainInformDao;
	}

	public IMessageInformDao getMessageInformDao() {
		return messageInformDao;
	}

	public void setMessageInformDao(IMessageInformDao messageInformDao) {
		this.messageInformDao = messageInformDao;
	}

	// *************************  Biz methods ************************* //

	public List<MainInform> findValidMainInform() {
		
		logger.trace("entering biz...");
		
		return mainInformDao.findValidMainInform();
	}

	public List<MessageInform> findValidMessageInform() {

		logger.trace("entering biz...");
		
		return messageInformDao.findValidMessageInform();
	}


}
