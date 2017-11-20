package com.ibs.common.module.frameworkimpl.security.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IUserDao {
	public String save(final User user);
	
	public void saveOrUpdate(User user);
	
	public User getUserByUserName(String userName);
	
	public User load(String id);
	
	public User loadCascade(String id);
	
	public void delete(String id);
	
	/*
	public void assignOrgPosition2User(Integer userId, Integer positionId, Integer orgId, Integer orgPosition, Integer isMaster, Integer displayOrder, String costCenter, String businessLine);
	
	public void unassignOrgPositionFromUser(Integer userId, Integer positionId, Integer orgId);*/
	
	public void assignRole2User(User user, String roleId);
	
	public void unassignRoleFromUser(User user, String roleId);
	
	/**
	 * 删除用户和角色的绑定关系
	 * @param userId
	 * @param roleId
	 * @return 
	 */
	public int unassignRoleFromUser(String userId, String roleId);
	
	public List<User> getUsersByRole(String roleId);
	
	
	public void updateUserPwd(String userId, String userPwd);
	
	public void updateLastLoginInfo(String userId, Date lastLoginTm, String lastLoginIP);
	
//	public IPage<User> findUserByPage(String userName, String userCode,
//			String employeeName, String employeeSN, String mobile, String type, String status,String orgId, int pageSize, int pageIndex);
	
	public IPage<User> getUsersByRoleByPage(String roleId, QueryPage page);
	
	public User getUserByUserCode(String userCode);
	
//	public IPage<User> getUserListByPage(QueryPage page);
//	
//	public IPage<User> getUserListByPage(String name, String code,
//			String status, String type, String displayName, String description,
//			String orgId, String orgCode, int pageSize, int pageIndex);

//	public IPage<User> findUserByPage(String userName, String userCode,
//			String employeeName, String employeeSN, String mobile, String type,
//			String status, String orgId, int pageSize, int pageIndex,
//			Map<String, String> sortMap);
	
	public IPage<UserDto> getUserListByPage(String name, String code,
			String status, String type, String displayName, String description,
			String roleId, int pageSize, int pageIndex, Map<String,String> sortMap);
	
	/**
	 * 根据角色codes，返回符合查询条件的用户
	 * @param roleCodes
	 * @param userType 可空，传入employee或者customer
	 * @param orgCodes	可空，传入指定的组织机构
	 * @param parm	可空，传入用户的名字或Code，支持模糊搜索
	 * @return
	 */
	public List<User> findUserByRole(List<String> roleCodes, String userType, String parm);
	
	/**
	 * 根据权限的名称，返回符合查询条件的用户
	 * @param permissionNames 权限名称列表（仅限于控制，页面按钮，url）
	 * @param userType 可空，用户类型
	 * @param orgCodes 可空，传入指定组织机构
	 * @param parm 可空，传入用户的名称或code支持模糊搜索
	 * @return
	 */
	public List<User> findUserByPermission(List<String> permissionNames, String userType, String parm);
	
	/**
	 * 检查是否有重复用户的Code
	 * @param code
	 * @return
	 */
	public List<User> checkUniqueUserCode(String code);
	
	/**
	 * 检查是否有重复的用户登录名
	 * @param name
	 * @return
	 */
	public List<User> checkUniqueUserName(String name);
	
	public List<User> findAll(int pageSize, int pageIndex);
	
	/**
	 * 批量删除某一个用户的所有角色
	 */
	public void unassigedAllRoleFromUser(final String userId);
	

	/**
	 * 查询用户信息
	 * @param clz，类，一般使用UserBasicInfo.class
	 * @param orgId 员工所属组织机构code
	 * @param needSubOrg  包含下属组织用户
	 * @param  userTypes[] 用户类型
	 * @param  status[] 状态
	 * @param userCodeOrNameOrPinYin 用户登录名或者名字或者拼音
	 * @param recordsSize 需要返回列表的记录数，默认为10
	 * @return   List<UserBasicInfo>  ，user只包括：   id ，name ，userType
	 */
	public<E> List<E> findUniversalUser(Class<E> clz, String[] userTypes, String[] status,
			String userCodeOrNameOrPinYin, Integer recordsSize);

	public void insertUser(User user, List<String> defaultRoleCodes);
	
	// qiyongping add 20160720
	public IPage<User> getUsersByPage(QueryPage queryPage);
}
