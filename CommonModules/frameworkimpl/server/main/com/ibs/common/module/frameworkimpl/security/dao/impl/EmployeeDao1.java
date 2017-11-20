package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.security.dao.IEmployeeDao1;
import com.ibs.common.module.frameworkimpl.security.domain.EmployeeSc1;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.util.StringUtils;

public class EmployeeDao1 extends BaseEntityDao<EmployeeSc1> implements
		IEmployeeDao1 {

	public String save(EmployeeSc1 employee) {
		return (String) super.save(employee);
	}

	public void saveOrUpdate(EmployeeSc1 employee) {
		super.saveOrUpdate(employee);
	}

	public EmployeeSc1 load(String id) {
		return super.load(id);
	}

	public void delete(String id) {
		super.remove(id);
	}

	public IPage<EmployeeSc1> findEmployeeByPage(String employeeName,
			String employeeSN, String mobile, String type, int pageSize,
			int pageIndex) {
		StringBuilder sb = new StringBuilder("from Employee where 1=1 ");
		List<Object> args = new ArrayList<Object>(0);
		if (!StringUtils.isEmpty(employeeName)) {
			sb.append(" and employeeName like ? ");
			args.add("%" + employeeName + "%");
		}

		if (!StringUtils.isEmpty(employeeSN)) {
			sb.append(" and employeeSN like ? ");
			args.add("%" + employeeSN + "%");
		}

		if (!StringUtils.isEmpty(mobile)) {
			sb.append(" and mobile like ? ");
			args.add("%" + mobile + "%");
		}

		if (!StringUtils.isEmpty(type)) {
			sb.append(" and type = ? ");
			args.add(type);
		}

		sb.append(" order by id");

		return super.findPage(sb.toString(), args, pageSize, pageIndex);
	}
}
