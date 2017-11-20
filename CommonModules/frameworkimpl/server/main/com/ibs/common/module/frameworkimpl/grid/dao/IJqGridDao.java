package com.ibs.common.module.frameworkimpl.grid.dao;

import com.ibs.common.module.frameworkimpl.security.domain.JqGridObj;

public interface IJqGridDao{

	public Long save(JqGridObj jqGridObj);

	public void saveOrUpdate(JqGridObj jqGridObj);

	public JqGridObj load(String id);

	public void delete(String id);

	public JqGridObj findByIDAndUserId(String userid,String jqgrid);
	
}
