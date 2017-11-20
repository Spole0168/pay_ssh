package com.ibs.common.module.frameworkimpl.security.dao;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.domain.MacUser;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IMacUserDao {
	Page<MacUser> findMacUsersByPage(QueryPage queryPage, Map<String, String> searchFields);

	List<MacUser> getUsersByMacId(String macId);

	Page<UserDto> findUsersByPage(QueryPage queryPage, Map<String, String> searchFields);

	void deleteAllBindingUserByMacId(String macId);

	String save(MacUser macUser);

	MacUser getUserByUserId(String userId);
	
	MacUser getMacUserBy(String macId, String userCode);
}
