package com.ibs.common.module.frameworkimpl.log.dao.impl;

import java.io.Serializable;

import com.ibs.common.module.frameworkimpl.log.dao.IDomainDao;
import com.ibs.portal.framework.server.dao.hibernate.BaseDaoHibernateImpl;

/**
 * @author 
 * $Id: DomainDaoImpl.java 24631 2011-01-07 05:32:48Z  $
 */
public class DomainDaoImpl extends BaseDaoHibernateImpl implements IDomainDao {

	public Object load(Class clz, Serializable id) {
		
		return getHibernateTemplate().load(clz, id);
	}

}
