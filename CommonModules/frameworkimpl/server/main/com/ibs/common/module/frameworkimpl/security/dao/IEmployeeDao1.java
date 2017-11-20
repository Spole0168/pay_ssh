package com.ibs.common.module.frameworkimpl.security.dao;

import com.ibs.common.module.frameworkimpl.security.domain.EmployeeSc1;
import com.ibs.portal.framework.server.metadata.IPage;

public interface IEmployeeDao1 {
	public String save(EmployeeSc1 employee);

	public void saveOrUpdate(EmployeeSc1 employee);

	public EmployeeSc1 load(String id);

	public void delete(String id);

	public IPage<EmployeeSc1> findEmployeeByPage(String employeeName,
			String employeeSN, String mobile, String type, int pageSize,
			int pageIndex);
}
