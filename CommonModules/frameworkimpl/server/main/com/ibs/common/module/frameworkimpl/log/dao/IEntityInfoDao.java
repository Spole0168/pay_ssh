package com.ibs.common.module.frameworkimpl.log.dao;

import java.util.List;

import com.ibs.common.module.frameworkimpl.log.domain.EntityInfo;


public interface IEntityInfoDao {

	public List<EntityInfo> getLogEntityInfos();
	
	public List<EntityInfo> getAppEntityInfos(String appId);
}
