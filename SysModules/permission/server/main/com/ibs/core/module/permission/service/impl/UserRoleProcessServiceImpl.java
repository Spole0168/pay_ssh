package com.ibs.core.module.permission.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibs.common.module.frameworkimpl.security.dao.IRoleDao;
import com.ibs.common.module.frameworkimpl.security.dao.IUserDao;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.core.module.permission.service.IUserRoleProcessService;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.service.BaseService;

/**
 * @author 
 * 
 *         用户角色处理对外接口
 * 
 */
public class UserRoleProcessServiceImpl extends BaseService implements
		IUserRoleProcessService {

	public IUserDao userDao;
	public IRoleDao roleDao;

	/**
	 * 给用户赋角色
	 * 
	 * @param userName
	 *            用户名
	 * @param roleCodes
	 *            角色代码list
	 */
	public void assignUserRoles(String userName, List<String> roleCodes) {
		logger.info("enter assignUserRoles");
		User user = userDao.getUserByUserName(userName);
		Map<String, String> hasRoles = new HashMap<String, String>();
		if (null == user) {
			logger.info("user is null");
			return;
		}
		if (roleCodes == null || roleCodes.size() < 1) {
			logger.info("roleCodes is null");
			return;
		}
		Set<Role> roles = user.getAssignedRoles();

		if (null != roles && roles.size() > 0) {
			Iterator<Role> it = roles.iterator();
			while (it.hasNext()) {
				Role role = it.next();
				if (null == role) {
					continue;
				}
				String rCode = role.getRoleCode();
				if (null == rCode || "".equals(rCode)) {
					continue;
				}
				hasRoles.put(rCode, rCode);
			}
		}

		for (int j = 0; j < roleCodes.size(); j++) {
			String roleCode = roleCodes.get(j);
			if (hasRoles.get(roleCode) == null) {
				// 需要添加该角色
				Role role = roleDao.getRoleByCode(roleCode);
				if (role != null && role.getId() != null) {
					userDao.assignRole2User(user, role.getId());
				}

			}

		}
		
		try {
			// 刷新Cache
			String userCache = com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE;
			CacheManager.getInstance().refresh(userCache, user.getId());
		} catch (Exception e) {
			logger.error("刷新缓存失败 : " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 取消用户角色
	 * 
	 * @param userName
	 *            用户名
	 * @param roleCodes
	 *            角色代码list
	 */
	public void unAssignUserRoles(String userName, List<String> roleCodes) {

		logger.info("enter assignUserRoles");
		User user = userDao.getUserByUserName(userName);
		Map<String, String> hasRoles = new HashMap<String, String>();
		if (null == user) {
			logger.info("user is null");
			return;
		}
		if (roleCodes == null || roleCodes.size() < 1) {
			logger.info("roleCodes is null");
			return;
		}
		Set<Role> roles = user.getAssignedRoles();

		if (null != roles && roles.size() > 0) {
			Iterator<Role> it = roles.iterator();
			while (it.hasNext()) {
				Role role = it.next();
				if (null == role) {
					continue;
				}
				String rCode = role.getRoleCode();
				if (null == rCode || "".equals(rCode)) {
					continue;
				}
				hasRoles.put(rCode, rCode);
			}
		}

		for (int j = 0; j < roleCodes.size(); j++) {
			String roleCode = roleCodes.get(j);
			if (hasRoles.get(roleCode) != null) {
				// 需要去掉该角色
				Role role = roleDao.getRoleByCode(roleCode);
				if (role != null && role.getId() != null) {
					userDao.unassignRoleFromUser(user, role.getId());
				}

			}

		}

		try {
			// 刷新Cache
			String userCache = com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE;
			CacheManager.getInstance().refresh(userCache, user.getId());
		} catch (Exception e) {
			logger.error("刷新缓存失败 : " + e.getMessage());
			e.printStackTrace();
		}

	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
