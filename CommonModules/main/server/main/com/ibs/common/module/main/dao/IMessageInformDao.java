package com.ibs.common.module.main.dao;

import java.util.List;

import com.ibs.common.module.main.domain.MessageInform;


/**
 * 消息提醒DAO接口
 * @author 
 * $Id: IMessageInformDao.java 20048 2010-12-07 08:05:51Z  $
 */
public interface IMessageInformDao {

	public List<MessageInform> findValidMessageInform();
	
}
