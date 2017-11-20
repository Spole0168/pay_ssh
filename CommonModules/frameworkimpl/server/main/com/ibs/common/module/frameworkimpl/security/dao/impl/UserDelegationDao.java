package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.security.dao.IUserDelegationDao;
import com.ibs.common.module.frameworkimpl.security.domain.UserDelegation;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;

public class UserDelegationDao extends
		BaseEntityDao<UserDelegation> implements
		IUserDelegationDao {

	public IPage<UserDelegation> getDelegationByOriginUserId(String userId) {
		String hql = "from UserDelegation where originUserId = ?";
		return super.findPageByValue(hql, userId, 100, 0);
	}
	
	public IPage<UserDelegation> getDelegationByDelegateUserId(String userId) {
		String hql = "from UserDelegation where delegateUserId = ?";
		return super.findPageByValue(hql, userId, 100, 0);
	}

	public UserDelegation loadDelegationInfo(String userId, String orgId,String positionId) {
		String hql = "from UserDelegation where originUserId = ? and originOrgId = ? and originPositionId = ?";
		List<Object> args = new ArrayList<Object>();
		args.add(userId);
		args.add(orgId);
		args.add(positionId);
		List<UserDelegation> result = super.find(hql, args);
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public UserDelegation load(String id) {
		return super.load(id);
	}

	public void remove(String id) {
		super.remove(id);
	}
}
