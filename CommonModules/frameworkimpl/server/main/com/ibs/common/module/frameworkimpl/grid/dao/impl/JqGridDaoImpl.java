package com.ibs.common.module.frameworkimpl.grid.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.common.module.frameworkimpl.grid.dao.IJqGridDao;
import com.ibs.common.module.frameworkimpl.security.domain.JqGridObj;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

public class JqGridDaoImpl extends BaseEntityDao<JqGridObj> implements IJqGridDao {

	public void delete(String id) {
		super.remove(id);
	}

	public JqGridObj findByIDAndUserId(final String userid,final String jqgrid) {
		return (JqGridObj)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String hqlString = "from JqGridObj where userid = '" 
                    + userid +"' and jqgrid = '" + jqgrid+"'";
				Query query = session.createQuery(hqlString);
				Object obj =  query.uniqueResult();
				return obj;
			}
		});
	}

	public JqGridObj load(String id) {
		return null;
	}

	public Long save(JqGridObj jqGridObj) {
		return (Long)super.save(jqGridObj);
	}

	public void saveOrUpdate(JqGridObj jqGridObj) {
		super.saveOrUpdate(jqGridObj);
	}

}
