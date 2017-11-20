package com.ibs.common.module.frameworkimpl.log.dao;

import java.util.List;

import com.ibs.common.module.frameworkimpl.log.domain.BizInfo;

public interface IBizInfoDao {
	public List<BizInfo> getAllBizInfos();
	
	public BizInfo getBizInfo(String bizClass, String bizMethod);
	
	public List<BizInfo> getAppBizInfos(String appId);
}
