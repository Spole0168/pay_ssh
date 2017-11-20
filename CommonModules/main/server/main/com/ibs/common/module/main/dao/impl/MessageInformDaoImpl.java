package com.ibs.common.module.main.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.main.dao.IMessageInformDao;
import com.ibs.common.module.main.domain.MainInform;
import com.ibs.common.module.main.domain.MessageInform;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;


/**
 * 消息提醒DAO实现
 * @author 
 * $Id: MessageInformDaoImpl.java 20048 2010-12-07 08:05:51Z  $
 */
public class MessageInformDaoImpl extends BaseEntityDao<MainInform> implements IMessageInformDao {

	public List<MessageInform> findValidMessageInform() {
		logger.trace("entering dao...");
		
		String hql = new String("select m from MessageInform m where m.status = ? order by m.sort");
		
		List<Object> args = new ArrayList<Object>(1);
		args.add(Boolean.TRUE);
		
		return super.findByHql(hql.toString(), args, null);
		
	}

}
