package com.ibs.common.module.frameworkimpl.grid.biz.impl;

import com.ibs.common.module.frameworkimpl.grid.biz.IJqGridBiz;
import com.ibs.common.module.frameworkimpl.grid.dao.IJqGridDao;
import com.ibs.common.module.frameworkimpl.security.domain.JqGridObj;


public class JqGridBizImpl implements IJqGridBiz {
	private IJqGridDao jqGridDao;
	
	public IJqGridDao getJqGridDao() {
		return jqGridDao;
	}

	public void setJqGridDao(IJqGridDao jqGridDao) {
		this.jqGridDao = jqGridDao;
	}

	public JqGridObj findByJqGridObj(String userid, String jqgrid) {
		return jqGridDao.findByIDAndUserId(userid, jqgrid);
	}

	public void saveJqGridObj(JqGridObj jqGridObj) {
		this.jqGridDao.saveOrUpdate(jqGridObj);
	}

	public void delJqGridObj(String id) {
		this.jqGridDao.delete(id);
	}

	
}
