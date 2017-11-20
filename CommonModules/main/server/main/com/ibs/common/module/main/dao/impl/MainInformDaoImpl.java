package com.ibs.common.module.main.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.main.dao.IMainInformDao;
import com.ibs.common.module.main.domain.MainInform;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;


/**
 * 首页提醒DAO实现
 * @author 
 * $Id: MainInformDaoImpl.java 19438 2010-12-04 05:33:31Z  $
 */
public class MainInformDaoImpl extends BaseEntityDao<MainInform> implements IMainInformDao {

	public List<MainInform> findValidMainInform() {
		logger.trace("entering dao...");
		
		String hql = new String("select m from MainInform m where m.status = ? order by m.sort");
		
		List<Object> args = new ArrayList<Object>(1);
		args.add(Boolean.TRUE);
		
		return super.findByHql(hql.toString(), args, null);
		
	}


}
