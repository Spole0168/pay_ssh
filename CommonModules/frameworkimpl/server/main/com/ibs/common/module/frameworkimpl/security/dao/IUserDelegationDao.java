package com.ibs.common.module.frameworkimpl.security.dao;

import com.ibs.common.module.frameworkimpl.security.domain.UserDelegation;
import com.ibs.portal.framework.server.metadata.IPage;

public interface IUserDelegationDao {
	public IPage<UserDelegation> getDelegationByOriginUserId(String userId);

	public IPage<UserDelegation> getDelegationByDelegateUserId(String userId);
	
	public UserDelegation loadDelegationInfo(String userId, String orgId, String positionId);

	public void saveOrUpdate(UserDelegation delegationInfo);
	
	public void remove(String id);
	
	public UserDelegation load(String id);
}
