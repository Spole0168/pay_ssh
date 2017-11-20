package com.ibs.common.module.frameworkimpl.log.dao;

import java.io.Serializable;


public interface IDomainDao {

	public Object load(Class clz, Serializable id);
}
