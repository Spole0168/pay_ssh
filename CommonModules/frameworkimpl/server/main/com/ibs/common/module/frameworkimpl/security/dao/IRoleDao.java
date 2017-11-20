package com.ibs.common.module.frameworkimpl.security.dao;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IRoleDao {
	public String save(Role role);
	
	public void saveOrUpdate(Role role);
	
	public Role load(String id);
	
	public Role loadCascade(String id);
	
	public void delete(String id);
	
	public List<Role> findAll();
	
	public void grantMenu2Role(final Role role, String menuId);
	
	public void unGrantMenu2Role(final Role role, final String menuId);
	
	public void ungrantAllMenuFromRole(Role role);
	
//	public void grantResource2Role(Role role, String resourceId);
//	
//	public void ungrantResourceFromRole(Role role, String resourceId);
	
	public void grantOper2Role(final Role role, final String operId);
	
	public void ungrantOperFromRole(Role role, String operId);
	
	public IPage<Role> findRoleByPage(String roleName, String roleCode, String description, int pageSize,
			int pageIndex);

	public List<Role> getAllRoleByApplicationNameAndUserId(String appName,String userId);
	
	public List<Role> getRolesByApplicationId(String applicationId);
	
	public IPage<Role> getRoleListByPage(QueryPage page);
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, List<String> creatorIds,
			int pageSize, int pageIndex);
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, List<String> creatorIds,
			int pageSize, int pageIndex, Map<String, String> sortMap);
	
	public List<Role> getAdminRoleByUser(String userId);
	
	public Role getRoleByCode(String code);
	
	public Role getRoleByName(String name);
	
	/**
	 * 使用sql查询role列表，支持翻页的情况
	 * @param roleName
	 * @param roleCode
	 * @param description
	 * @param scopes 使用范围，列表
	 * @param appIds 使用的应用，列表
	 * @param groupIds 使用的角色组，列表
	 * @param rejectRoleIds 查询可以和某几个角色共同选择的角色
	 * @param userId 查询某一用户是否具有角色，和userHasRole共同使用
	 * @param userHasRole true-查询用户包含的角色；false-用户可以选择的角色；null-返回全部符合查询条件的角色，其中用户有的mark为1
	 * @param pageSize
	 * @param pageIndex
	 * @param sortMap
	 * @parm  userType
	 * @return
	 */
	public IPage<RoleDto> getRolePageBySql(String roleName, String roleCode,
			String description, List<String> scopes,List<String> manageScopes, List<String> appIds,
			List<String> groupIds, List<String> rejectRoleIds, String userId,
			Boolean userHasRole, int pageSize, int pageIndex,
			Map<String, String> sortMap,String userType);
	
	public int grantManageMenuToRole(final String roleId, final String menuId);
	
	public int unGrantManageMenuToRole(final String roleId, final String menuId);
	
}
