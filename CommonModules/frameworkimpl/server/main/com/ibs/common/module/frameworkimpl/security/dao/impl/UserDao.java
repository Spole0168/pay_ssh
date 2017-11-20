package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.common.module.frameworkimpl.security.dao.IUserDao;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

import net.sf.cglib.beans.BeanCopier;

public class UserDao extends BaseEntityDao<User> implements IUserDao {

	public String save(final User user) {
		return (String) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				long versionNo = System.currentTimeMillis();
				user.setVersionNo(versionNo);
				Serializable v = session.save(user);
				session.flush();
				return v;
			}
		});
		// return (String) super.save(user);
	}

	public void saveOrUpdate(User user) {
		logger.error("userName=" + user.getUserName() + "---userType=" + user.getUserType());
		long versionNo = System.currentTimeMillis();
		user.setVersionNo(versionNo);
		super.saveOrUpdate(user);
	}
	
	public void saveInfoForIp(User user){
		StringBuffer sql=new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		sql.append("insert into pbl.t_mdm_employee_message (id,create_time,operate_type,password,user_code,user_name,user_org_code,user_org_status,user_status,send_times) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?)");
		args.add(StringUtils.getUUID());
		args.add(new Date());
		args.add("modify");
		args.add(user.getUserPwd());
		args.add(user.getUserCode());
		args.add(user.getDisplayName());
		args.add("null");	
		args.add("null");	
		if("INVALID".equals(user.getStatus())){
			args.add(User.USER_STATUS_INVALID);
		}else{
			args.add(User.USER_STATUS_VALID);
		}
		args.add(0);
		super.executeSql(sql.toString(), args);
	}

	public User load(String id) {
		return super.load(id);
	}

	public User getUserByUserName(String userName) {
		// return super.loadBy("userName", userName);
		String hql = "from User user where user.userName=?";// or
		List<Object> args = new ArrayList<Object>();
		args.add(userName);
		List<User> list = super.find(hql, args);
		if (null == list || list.size() <= 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 级联加载用户包含的角色和组织信息
	 */
	public User loadCascade(final String id) {
		return (User) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				User user = (User) session.get(User.class, id);
				Hibernate.initialize(user.getAssignedRoles());
				return user;
			}
		});

	}

	public void delete(String id) {
		super.remove(id);
	}

	/*
	 * public void assignOrgPosition2User(final Integer userId, final Integer
	 * positionId, final Integer orgId, final Integer orgPosition, final Integer
	 * isMaster, final Integer displayOrder, final String costCenter, final
	 * String businessLine) { getHibernateTemplate().execute(new
	 * HibernateCallback() { public Object doInHibernate(Session session) throws
	 * HibernateException { UserOrgPositionRelation uor = new
	 * UserOrgPositionRelation();
	 * 
	 * uor.setUser(new User(userId)); uor.setPosition(new Position(positionId));
	 * uor.setOrg(new Org(orgId)); uor.setOrgPosition(orgPosition);
	 * uor.setIsMaster(isMaster); uor.setDisplayOrder(displayOrder);
	 * uor.setCostCenter(costCenter); uor.setBusinessLine(businessLine);
	 * 
	 * session.save(uor); return null; } }); }
	 * 
	 * 
	 * public void unassignOrgPositionFromUser(final Integer userId, final
	 * Integer positionId, final Integer orgId) {
	 * getHibernateTemplate().execute(new HibernateCallback() { public Object
	 * doInHibernate(Session session) throws HibernateException {
	 * UserOrgPositionRelation uor = new UserOrgPositionRelation();
	 * 
	 * uor.setUser(new User(userId)); uor.setPosition(new Position(positionId));
	 * uor.setOrg(new Org(orgId));
	 * 
	 * session.delete(uor); return null; } }); }
	 */
	public void assignRole2User(final User user, final String roleId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				long versionNo = System.currentTimeMillis();
				user.setVersionNo(versionNo);
				user.assignRole(new Role(roleId));
				session.update(user);
				return null;
			}
		});
	}

	public void unassignRoleFromUser(final User user, final String roleId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Role role = (Role) session.load(Role.class, roleId);

				user.unassignRole(role);
				// session.update(user);
				long versionNo = System.currentTimeMillis();
				user.setVersionNo(versionNo);
				session.merge(user);
				return null;
			}
		});
	}

	public void updateUserPwd(String userId, String userPwd) {
		User user = load(userId);
		user.setUserPwd(userPwd);

		saveOrUpdate(user);
	}

	public void updateLastLoginInfo(String userId, Date lastLoginTm,
			String lastLoginIP) {
		User user = load(userId);
		user.setLastLoginTm(lastLoginTm);
		user.setLastLoginIP(lastLoginIP);

		saveOrUpdate(user);
	}

	public List<User> getUsersByRole(String roleId) {
		String hql = "select u from User u,Role r where u in elements(r.includedUsers) and r.id = ?";
		return super.findByValue(hql, roleId);
	}

	public IPage<User> getUsersByRoleByPage(String roleId, QueryPage page) {
		String hql = "select user from User user,Role role where user in elements(role.includedUsers) and role.id = ?";
		List<Object> args = new ArrayList<Object>();
		args.add(roleId);
		// return super.findPage(hql, args, page.getPageSize(), page
		// .getPageIndex());
		return super.findPageByHql(hql, args, page.getPageSize(), page
				.getPageIndex(), page.getSortMap());
	}

	// add by huolang 2010-09-08
	public User getUserByUserCode(String userCode) {
		String hql = "from User user where user.userCode=? ";
		List<Object> args = new ArrayList<Object>();
		args.add(userCode);
		List<User> list = super.find(hql, args);
		if (null == list || list.size() <= 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	// @Deprecated
	// public IPage<User> getUserListByPage(QueryPage page) {
	// return super.findPageBy(page);
	// }

	@Deprecated
	// public IPage<User> getUserListByPage(String name, String code,
	// String status, String type, String displayName, String description,
	// String orgId, String orgCode, int pageSize, int pageIndex) {
	// List<Object> args = new ArrayList<Object>(0);
	//
	// StringBuffer hql = new StringBuffer();
	// // 取出有效状态的用户-组织关系
	// hql
	// .append("select distinct userorg.user from UserOrg as userorg where userorg.status=?");
	// args.add(UserOrg.STATUS_VALID);
	//
	// if (null != name && !"".equals(name)) {
	// hql.append(" and userorg.user.userName like ?");
	// args.add("%" + name + "%");
	// }
	// if (null != code && !"".equals(code)) {
	// hql.append(" and userorg.user.userCode like ?");
	// args.add("%" + code.toUpperCase() + "%");
	// }
	// if (null != status && !"".equals(status)) {
	// hql.append(" and userorg.user.status=?");
	// args.add(status);
	// }
	// if (null != type && !"".equals(type)) {
	// hql.append(" and userorg.user.userType=?");
	// args.add(type);
	// }
	// if (null != displayName && !"".equals(displayName)) {
	// hql.append(" and userorg.user.displayName like ?");
	// args.add("%" + displayName + "%");
	// }
	// if (null != description && !"".equals(description)) {
	// hql.append(" and userorg.user.description like ?");
	// args.add("%" + description + "%");
	// }
	// if (null != orgId && !"".equals(orgId)) {
	// hql.append(" and userorg.org.id=?");
	// args.add(orgId);
	// }
	// // if (null != orgCode && !"".equals(orgCode) &&
	// // !Constants.ROOT_ORG_CODE.equals(orgCode)) {
	// // hql.append(" and userorg.org.orgCode=?");
	// // args.add(orgCode.toUpperCase());
	// // }
	// List<User> userListAll = super.find(hql.toString(), args);
	// int total = (null == userListAll) ? 0 : userListAll.size();
	//
	// // edit by huolang 2011-01-14
	// // 修正翻页后无法回到最后一页问题
	// int newPageIndex = pageIndex;
	// if (total <= pageSize * (pageIndex)) {
	// if (total > 0)
	// newPageIndex = (total + pageSize - 1) / pageSize - 1;
	// }
	//
	// List<User> userList = super.find(hql.toString(), args, pageSize,
	// newPageIndex);
	//
	// return new Page<User>(userList, total, pageSize, newPageIndex);
	// // return super.findPage(hql.toString(), args, pageSize, pageIndex);
	// }
	/**
	 * 根据用户登录名，代码，状态，类型，显示名，描述信息，组织id获取翻页的数据
	 */
	public IPage<UserDto> getUserListByPage(String name, String code,
			String status, String type, String displayName, String description,
			String roleId, int pageSize, int pageIndex,
			Map<String, String> sortMap) {
		List<Object> args = new ArrayList<Object>(0);

		StringBuffer hql = new StringBuffer();
		// 取出有效状态的用户-组织关系
		// hql
		hql.append("select distinct user.id as id,"
				+ "user.userName as userName," + "user.userCode as userCode,"
				+ "user.description as description," + "user.status as status,"
				+ "user.userType as userType"
				+ " from User user");
		if (null != roleId && !"".equals(roleId)) {
			hql.append(", Role role");
		}
		hql.append(" where 1=1");

		if (null != roleId && !"".equals(roleId)) {
			hql
					.append(" and user in elements(role.includedUsers) and role.id = ?");
			args.add(roleId);
		}
		if (null != name && !"".equals(name)) {
			hql.append(" and user.userName like ?");
			args.add("%" + name + "%");
		}
		if (null != code && !"".equals(code)) {
			hql
					.append(" and (user.userCode like ? )");
			args.add("%" + code + "%");
//			args.add("%" + code.toUpperCase() + "%");
		}
		if (null != status && !"".equals(status)) {
			hql.append(" and user.status=?");
			args.add(status);
		}
		if (null != type && !"".equals(type)) {
			hql.append(" and user.userType=?");
			args.add(type);
		}
		if (null != description && !"".equals(description)) {
			hql.append(" and user.description like ?");
			args.add("%" + description + "%");
		}

		return super.findPageByHqlWithColumn(hql.toString(), args, pageSize,
				pageIndex, sortMap, UserDto.class, "distinct user.id");

	}

	public List<User> findUserByPermission(List<String> permissionNames,
			String userType, String parm) {
		if (null == permissionNames || permissionNames.size() <= 0) { // 
			return null;
		}
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>(0);
		hql
				.append("select distinct user from User user,Role role,Menu menu where 1=1");

		hql
				.append(" and user in elements(role.includedUsers) and menu in elements(role.grantedOpers) and menu.menuName in (");

		for (String code : permissionNames) {
			hql.append("?,");
			args.add(code);
		}

		hql = hql.deleteCharAt(hql.length() - 1);
		hql.append(")");

		if (null != userType && !"".equals(userType)) {
			hql.append(" and user.userType=?");
			args.add(userType);
		}

		if (null != parm && !"".equals(parm)) {
			hql
					.append(" and (user.userCode like ? )");
			args.add("%" + parm.trim().toUpperCase() + "%");
		}
		return super.find(hql.toString(), args);

	}

	public List<User> findUserByRole(List<String> roleCodes, String userType, String parm) {

		if (null == roleCodes || roleCodes.size() <= 0) { // 
			return null;
		}
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>(0);
		hql
				.append("select distinct user from User user,Role role where 1=1");

		hql
				.append(" and user in elements(role.includedUsers) and role.roleCode in (");

		for (String code : roleCodes) {
			hql.append("?,");
			args.add(code);
		}

		hql = hql.deleteCharAt(hql.length() - 1);
		hql.append(")");

		if (null != userType && !"".equals(userType)) {
			hql.append(" and user.userType=?");
			args.add(userType);
		}

		if (null != parm && !"".equals(parm)) {
			hql
					.append(" and (user.userCode like ? )");
			args.add("%" + parm.trim().toUpperCase() + "%");
		}
		return super.find(hql.toString(), args);
	}

	public List<User> checkUniqueUserCode(String code) {
		if (null == code || "".equals(code.trim())) { // 
			return null;
		}
		String hql = "select distinct user from User user where user.userCode=? and status = '01'";
		List<Object> args = new ArrayList<Object>(0);
		args.add(code.trim().toUpperCase());
		return super.find(hql, args);
	}

	public List<User> checkUniqueUserName(String name) {
		if (null == name || "".equals(name.trim())) { // 
			return null;
		}
		String hql = "select distinct user from User user where user.userName=? and status = '01'";
		List<Object> args = new ArrayList<Object>(0);
		args.add(name.trim());		 
		return super.find(hql, args);
	}

	// public IPage<UserDto> getUserPageBySql(
	// String name,
	// String code,
	// String status,
	// String type,
	// String displayName,
	// String description,
	// String orgId,
	// String districtId,
	// int pageSize,
	// int pageIndex,
	// Map<String,String> sortMap) {
	// StringBuffer sql = new StringBuffer();
	// List<Object> args = new ArrayList<Object>();
	//		
	// sql.append("select distinct emp.id as ");
	// return null;
	// }

	public List<User> findAll(int pageSize, int pageIndex) {
		return super.findAll(pageSize, pageIndex);
	}

	public void unassigedAllRoleFromUser(final String userId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				User user = (User) session.load(User.class, userId);
				user.getAssignedRoles().clear();
				long versionNo = System.currentTimeMillis();
				user.setVersionNo(versionNo);
				session.saveOrUpdate(user);
				// user.unassignRole(role);
				// session.update(user);
				// session.merge(user);
				return null;
			}
		});
	}

	public int unassignRoleFromUser(String userId, String roleId) {
		String sql = "delete from pbl.t_mdm_user_role where user_id=? and role_id=?";
		List<Object> args = new ArrayList<Object>(0);
		args.add(userId);
		args.add(roleId);
		return super.executeSql(sql, args);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findUniversalUser(Class<E> clz, 
			String[] userTypes, String[] userStatus,
			String userCodeOrNameOrPinYin, Integer recordsSize) {
		List<Object> args = new ArrayList<Object>(0);
		StringBuffer hql = new StringBuffer();
		StringBuffer bf = new StringBuffer();
		hql.append("select distinct user.id as id,"
				+ "user.userName as userName," + "user.userCode as userCode,"
				+ "user.description as description," + "user.status as status,"
				+ "user.userType as userType"
				+ " from User user");

		if (null != userCodeOrNameOrPinYin
				&& !"".equals(userCodeOrNameOrPinYin)) {
			hql
					.append(" and ( user.userName like ? )");
			args.add("%" + userCodeOrNameOrPinYin + "%");
		}

		if (userStatus != null && userStatus.length > 0) {
			hql.append(" and user.status in ( ");
			for (int j = 0; j < userStatus.length; j++) {
				bf.append("'");
				bf.append(userStatus[j]);
				bf.append("'");
				bf.append(",");
			}
			String typewhere = bf.toString();
			typewhere = typewhere.substring(0, typewhere.length() - 1);
			hql.append(typewhere + " ) ");
		}

		if (userTypes != null && userTypes.length > 0) {
			bf = new StringBuffer();
			hql.append(" and user.userType in ( ");
			for (int j = 0; j < userTypes.length; j++) {
				bf.append("'");
				bf.append(userTypes[j]);
				bf.append("'");
				bf.append(",");
			}
			String typewhere = bf.toString();
			typewhere = typewhere.substring(0, typewhere.length() - 1);
			hql.append(typewhere + " ) ");
		}

		IPage<UserDto> findResult = findPageByHqlWithColumn(hql.toString(),
				args, recordsSize, 0, null, UserDto.class, "distinct user.id");
		if (findResult == null) {
			return null;
		}

		if (UserDto.class.getName().equals(clz.getName()))
			return (List<E>) findResult.getRows();

		List<E> result = new ArrayList<E>(findResult.getRows().size());

		try {
			BeanCopier copy = BeanCopier.create(UserDto.class, clz, false);
			Iterator<UserDto> iter = findResult.getRows().iterator();
			while (iter.hasNext()) {
				E dto = clz.newInstance();
				copy.copy(iter.next(), dto, null);
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void insertUser(User user, List<String> defaultRoleCodes) {
		logger.error("userName=" + user.getUserName() + "---userType=" + user.getUserType());
		try {

			// 添加用户信息
			StringBuffer userSql = new StringBuffer();
			userSql
					.append("INSERT INTO pbl.t_MDM_USER (USER_ID, USER_NAME, USER_PWD, DEFAULT_LOCALE, STATUS,CREATE_TM,CREATOR,CREATE_ID, LAST_UPDATE_TM,LAST_UPDATOR,LAST_UPDATE_ID,USER_TYPE,CREATE_CODE,LAST_UPDATE_CODE,VERSION_NO,DESCRIPTION)"
							+ " values (?, ?, ?,?,?, sysdate, ?,?,?,?,?,?,?,?,?,?)");

			List<Object> argsuser = new ArrayList<Object>();
			argsuser.add(user.getId());
			argsuser.add(user.getUserName());
			argsuser.add(user.getUserPwd());
			argsuser.add(user.getDefaultLocate());
			argsuser.add(user.getStatus());
			argsuser.add(user.getCreator());
			argsuser.add(user.getCreateId());
			argsuser.add(user.getLastUpdateTm());
			argsuser.add(user.getLastUpdator());
			argsuser.add(user.getLastUpdateId());
			argsuser.add(user.getUserType());
			argsuser.add(user.getCreateCode());
			argsuser.add(user.getLastUpdateCode());
			argsuser.add(1);
			argsuser.add(user.getDescription());

			// 添加用户角色
			this.executeSql(userSql.toString(), argsuser);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}

	}
	
	// qiyongping add 20160720
	public IPage<User> getUsersByPage(QueryPage queryPage) {
		return super.findPageBy(queryPage);
	}

}
