package com.ibs.common.module.frameworkimpl.grid.biz;

import com.ibs.common.module.frameworkimpl.security.domain.JqGridObj;


public interface IJqGridBiz {

	public JqGridObj findByJqGridObj(String userid,String jqgrid);
	public void saveJqGridObj(JqGridObj jqGridObj);
	public void delJqGridObj(String id);
}
