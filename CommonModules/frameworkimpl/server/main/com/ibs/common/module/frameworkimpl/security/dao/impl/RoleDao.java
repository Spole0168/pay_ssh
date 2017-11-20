package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.common.module.frameworkimpl.security.dao.IRoleDao;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class RoleDao extends BaseEntityDao<Role> implements IRoleDao {

	public String save(Role role) {
		return (String) super.save(role);
	}

	public void saveOrUpdate(Role role) {
		super.saveOrUpdate(role);
	}

	public Role load(String id) {
		return super.load(id);
	}

	public Role loadCascade(final String id) {
		return (Role) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Role role = (Role) session.load(Role.class, id);
				Hibernate.initialize(role.getIncludedUsers());
				Hibernate.initialize(role.getGrantedMenus());
				Hibernate.initialize(role.getGrantedOpers());
				Hibernate.initialize(role.getGrantedGuiMenus());
				Hibernate.initialize(role.getApplication());
				return role;
			}
		});
	}

	public void delete(String id) {
		super.remove(id);
	}

	public void grantMenu2Role(final Role role, final String menuId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				if (menuId == null) {
					return null;
				}
				String idstr = menuId;
				if (idstr.startsWith(Menu.MENU_TYPE_GUI_MENU)) {
					idstr = idstr.replace(Menu.MENU_TYPE_GUI_MENU + "-", "");
					role.getGrantedGuiMenus().add(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_PDA_MENU)) {
					idstr = idstr.replace(Menu.MENU_TYPE_PDA_MENU + "-", "");
					role.getGrantedGuiMenus().add(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_MENU)) {
					idstr = idstr.replace(Menu.MENU_TYPE_MENU + "-", "");
					role.getGrantedMenus().add(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_BUTTON)) {
					idstr = idstr.replace(Menu.MENU_TYPE_BUTTON + "-", "");
					role.getGrantedOpers().add(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_URL)) {
					idstr = idstr.replace(Menu.MENU_TYPE_URL + "-", "");
					role.getGrantedOpers().add(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_CONTROL)) {
					idstr = idstr.replace(Menu.MENU_TYPE_CONTROL + "-", "");
					role.getGrantedOpers().add(new Menu(idstr));
				} else {
					return null;
				}

				session.update(role);
				return null;
			}
		});
	}
	
	public void unGrantMenu2Role(final Role role, final String menuId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				if(menuId == null){
					return null;
				}
				String idstr = menuId;
				if(idstr.startsWith(Menu.MENU_TYPE_GUI_MENU)){
					idstr = idstr.replace(Menu.MENU_TYPE_GUI_MENU+"-", "");
					role.getGrantedGuiMenus().remove(new Menu(idstr));
				} else if(idstr.startsWith(Menu.MENU_TYPE_PDA_MENU)){
					idstr = idstr.replace(Menu.MENU_TYPE_PDA_MENU+"-", "");
					role.getGrantedGuiMenus().remove(new Menu(idstr));
				} else if(idstr.startsWith(Menu.MENU_TYPE_MENU)){
					idstr = idstr.replace(Menu.MENU_TYPE_MENU+"-", "");
					role.getGrantedMenus().remove(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_BUTTON)) {
					idstr = idstr.replace(Menu.MENU_TYPE_BUTTON + "-", "");
					role.getGrantedOpers().remove(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_URL)) {
					idstr = idstr.replace(Menu.MENU_TYPE_URL + "-", "");
					role.getGrantedOpers().remove(new Menu(idstr));
				} else if (idstr.startsWith(Menu.MENU_TYPE_CONTROL)) {
					idstr = idstr.replace(Menu.MENU_TYPE_CONTROL + "-", "");
					role.getGrantedOpers().remove(new Menu(idstr));
				}else{
					return null;
				}
				
				session.update(role);
				return null;
			}
		});
	}

	public void ungrantAllMenuFromRole(Role role) {
		role.getGrantedMenus().clear();
		role.getGrantedGuiMenus().clear();
		role.getGrantedOpers().clear();
		super.saveOrUpdate(role);
	}

	public void grantOper2Role(final Role role, final String operId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				role.getGrantedOpers().add(new Menu(operId));
				session.update(role);
				return null;
			}
		});
	}

	public void ungrantOperFromRole(Role role, String operId) {
		Iterator<Menu> grantedOpers = role.getGrantedOpers().iterator();
		boolean hasChange = false;
		while (grantedOpers.hasNext()) {
			Menu oper = grantedOpers.next();
			if (operId.equals(oper.getId())) {
				grantedOpers.remove();
				hasChange = true;
			}
		}
		if (hasChange == true) {
			super.saveOrUpdate(role);
		}
	}

	public IPage<Role> findRoleByPage(String roleName, String roleCode,
			String description, int pageSize, int pageIndex) {
		StringBuilder sb = new StringBuilder("from Role where 1=1 ");
		List<Object> args = new ArrayList<Object>(0);
		if (null != roleName && roleName.trim().length() > 0) {
			sb.append(" and roleName like ? ");
			args.add("%" + roleName + "%");
		}

		if (null != roleCode && roleCode.trim().length() > 0) {
			sb.append(" and roleCode like ? ");
			args.add("%" + roleCode + "%");
		}

		if (null != description && description.trim().length() > 0) {
			sb.append(" and description like ? ");
			args.add("%" + description + "%");
		}
		
		sb.append(" order by id");
		
		return super.findPage(sb.toString(), args, pageSize, pageIndex);
	}

	public List<Role> getAllRoleByApplicationNameAndUserId(String appName,String userId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		
//		criteria.createAlias("application", "a");
//		criteria.add(Expression.eq("a.appName", appName));
		
		criteria.createAlias("includedUsers", "b");
		criteria.add(Expression.eq("b.id", userId));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return super.findBy(criteria);
	}

	public List<Role> getRolesByApplicationId(String applicationId) {
		String hql = "from Role where application.id = ?";
		return super.findByValue(hql, applicationId);
	}
	
	public IPage<Role> getRoleListByPage(QueryPage page){
		return super.findPageBy(page);
	}
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, List<String> creatorIds,
			int pageSize, int pageIndex) {
		StringBuffer hql = new StringBuffer("from Role as r where 1=1");
		List<Object> args = new ArrayList<Object>();
		if (null != roleName && !"".equals(roleName)) {
			hql.append(" and r.roleName like ?");
			args.add("%" + roleName + "%");
		}
		if (null != roleCode && !"".equals(roleCode)) {
			hql.append(" and r.roleCode like ?");
			args.add("%" + roleCode.toUpperCase() + "%");
		}
		if (null != description && !"".equals(description)) {
			hql.append(" and r.description like ?");
			args.add("%" + description + "%");
		}
		if (null != isAdmin && !"".equals(isAdmin)) {
			hql.append(" and r.isAdmin=?");
			args.add(isAdmin);
		}
		if (null != creatorIds && creatorIds.size() > 0) {
			hql.append(" and (");
			for (String id : creatorIds) {
				hql.append("r.wholeCreateRoleId like ? or ");
				args.add("%" + Role.WHOLE_ROLE_SEPARATOR + id.trim()
						+ Role.WHOLE_ROLE_SEPARATOR + "%");
			}
			hql = hql.delete(hql.length() - 4, hql.length());
			hql.append(")");
		}

		hql.append(" order by r.roleCode");
		return super.findPage(hql.toString(), args, pageSize, pageIndex);

	}
	
	public IPage<Role> getRoleListByPage(String roleName, String roleCode,
			String description, String isAdmin, List<String> creatorIds,
			int pageSize, int pageIndex, Map<String, String> sortMap) {
		StringBuffer hql = new StringBuffer("from Role as r where 1=1");
		List<Object> args = new ArrayList<Object>();
		if (null != roleName && !"".equals(roleName)) {
			hql.append(" and r.roleName like ?");
			args.add("%" + roleName + "%");
		}
		if (null != roleCode && !"".equals(roleCode)) {
			hql.append(" and r.roleCode like ?");
			args.add("%" + roleCode.toUpperCase() + "%");
		}
		if (null != description && !"".equals(description)) {
			hql.append(" and r.description like ?");
			args.add("%" + description + "%");
		}
		if (null != isAdmin && !"".equals(isAdmin)) {
			hql.append(" and r.isAdmin=?");
			args.add(isAdmin);
		}
		if (null != creatorIds && creatorIds.size() > 0) {
			hql.append(" and (");
			for (String id : creatorIds) {
				hql.append("r.wholeCreateRoleId like ? or ");
				args.add("%" + Role.WHOLE_ROLE_SEPARATOR + id.trim()
						+ Role.WHOLE_ROLE_SEPARATOR + "%");
			}
			hql = hql.delete(hql.length() - 4, hql.length());
			hql.append(")");
		}
		
//		hql.append(" order by r.roleCode");
//		return super.findPage(hql.toString(), args, pageSize, pageIndex);
		return super.findPageByHql(hql.toString(), args, pageSize, pageIndex, sortMap);
	}
	
	public List<Role> getAdminRoleByUser(String userId){
		String hql = "select r from Role r, User u where r in elements(u.assignedRoles) and u.id=? and r.isAdmin=?";
		List<Object> args = new ArrayList<Object>();
		args.add(userId);
		args.add(Role.ROLE_IS_ADMIN);
		return super.find(hql, args);
	}
	
	public Role getRoleByCode(String code){
		return super.loadBy("roleCode", code);
	}
	
	public Role getRoleByName(String name){
		return super.loadBy("roleName", name);
	}
	
	public IPage<RoleDto> getRolePageBySql(
			String roleName, 
			String roleCode,
			String description,
			List<String> scopes,
			List<String> manageScopes,
			List<String> appIds,
			List<String> groupIds,
			List<String> rejectRoleIds,
			String userId,
			Boolean userHasRole,
			int pageSize, int pageIndex, Map<String, String> sortMap,String userType){
		
		logger.trace("" + this.getClass().getSimpleName()
				+ " method getRolePageBySql"); 
		
		StringBuffer sql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		
		sql.append("select distinct r.role_id as id," +
				"r.role_name as roleName," +
				"r.role_code as roleCode," +
				"r.description as description," +
				"a.app_id as appId," +
				"a.app_name as appName," +
				"r.is_admin as isAdmin," +
				"r.deletable as deletable" +
				" from pbl.t_mdm_role r" +
				" left join pbl.t_mdm_app a on r.app_id=a.app_id");
/*		if (null != groupIds && groupIds.size()>0){
			sql.append(" left join pbl.t_mdm_role_group_role grp on r.role_id=grp.role_id");
		}*/
		if(null != userId && !"".equals(userId)){
			sql.append(" left join pbl.t_mdm_user_role ur on r.role_id=ur.role_id and user_id=?");
			args.add(userId);
		}
/*		if(null != rejectRoleIds && rejectRoleIds.size()>0){
			sql.append(" left join t_mdm_role_reject rej on (r.role_id=rej.role_id1 or r.role_id=rej.role_id2)");
		}*/
		sql.append(" where 1=1");
		
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("id", Hibernate.STRING));
		scallarList.add(new ColumnType("roleName", Hibernate.STRING));
		scallarList.add(new ColumnType("roleCode", Hibernate.STRING));
		scallarList.add(new ColumnType("description", Hibernate.STRING));
		scallarList.add(new ColumnType("appId", Hibernate.STRING));
		scallarList.add(new ColumnType("appName", Hibernate.STRING));
		scallarList.add(new ColumnType("isAdmin", Hibernate.STRING));
		scallarList.add(new ColumnType("deletable", Hibernate.STRING));
		
		if(null != roleName && !"".equals(roleName)){
			sql.append(" and r.role_name like ?");
			args.add("%"+roleName.trim()+"%");
		}
		if(null != roleCode && !"".equals(roleCode)){
			sql.append(" and r.role_code like ?");
			args.add("%"+roleCode.trim().toUpperCase()+"%");
		}
		if(null != description && !"".equals(description)){
//			sql.append("  and r.role_code like ?");
			sql.append("  and r.DESCRIPTION like ?");
			args.add("%"+description+"%");
		}

		// 使用范围
//		if (null != scopes && scopes.size() == 1) {
//			sql.append(" and r.scope like ?");
//			args.add("%" + Role.WHOLE_SCOPE_SEPARATOR + scopes.get(0)
//					+ Role.WHOLE_SCOPE_SEPARATOR + "%");
//		} else if (null != scopes && scopes.size() > 1) {
//			sql.append(" and (");
//			for (String sc : scopes) {
//				sql.append(" r.scope like ? or");
//				args.add("%" + Role.WHOLE_SCOPE_SEPARATOR + sc
//						+ Role.WHOLE_SCOPE_SEPARATOR + "%");
//			}
//			sql = sql.delete(sql.length() - 3, sql.length());
//			sql.append(")");
//		}
		
		//管理范围
//		if (null != manageScopes && manageScopes.size() == 1) {
//			sql.append(" and r.manage_scope like ?");
//			args.add("%" + Role.WHOLE_SCOPE_SEPARATOR + manageScopes.get(0)
//					+ Role.WHOLE_SCOPE_SEPARATOR + "%");
//		} else if (null != manageScopes && manageScopes.size() > 1) {
//			sql.append(" and (");
//			for (String msc : manageScopes) {
//				sql.append(" r.manage_scope like ? or");
//				args.add("%" + Role.WHOLE_SCOPE_SEPARATOR + msc
//						+ Role.WHOLE_SCOPE_SEPARATOR + "%");
//			}
//			sql = sql.delete(sql.length() - 3, sql.length());
//			sql.append(")");
//		}
		
//		if(StringUtils.isNotEmpty(userType))
//		{
//			sql.append(" and r.user_scope like ?");
//			args.add("%" + Role.WHOLE_SCOPE_SEPARATOR + userType
//					+ Role.WHOLE_SCOPE_SEPARATOR + "%");
//		}

		// 所属应用
		if (null != appIds && appIds.size() == 1) {
			sql.append(" and r.app_id=?");
			args.add(appIds.get(0));
		} else if (null != appIds && appIds.size() > 1) {
			sql.append(" and (");
			for (String app : appIds) {
				sql.append(" r.app_id=? or");
				args.add(app);
			}
			sql = sql.delete(sql.length() - 3, sql.length());
			sql.append(")");
		}
		
		// 所属组(暂时不用）
/*		if (null != groupIds && groupIds.size() == 1) {
			sql.append(" and grp.group_id=?");
			args.add(groupIds.get(0));
		} else if (null != groupIds && groupIds.size() > 1) {
			sql.append(" and (");
			for (String gp : groupIds) {
				sql.append(" grp.group_id=? or");
				args.add(gp);
			}
			sql = sql.delete(sql.length() - 3, sql.length());
			sql.append(")");
		}*/
		
		// 与rejectRoleId可共存的角色（暂时不用）
/*		if (null != rejectRoleIds && rejectRoleIds.size() == 1) {
			sql.append(" and (rej.role_id1 is null or rej.role_id1!=?) and (rej.role_id2 is null or rej.role_id2!=?)");
			args.add(rejectRoleIds.get(0));
			args.add(rejectRoleIds.get(0));
		} else if (null != rejectRoleIds && rejectRoleIds.size() > 1) {
			sql.append(" and (rej.role_id1 is null or rej.role_id1 not in (");
			for (String rej : rejectRoleIds) {
				sql.append("?,");
				args.add(rej);
			}
			sql = sql.delete(sql.length()-1, sql.length());
			sql.append(")");
			sql.append(")");
			
			sql.append(" and (rej.role_id2 is null or rej.role_id2 not in (");
			for (String rej : rejectRoleIds) {
				sql.append("?,");
				args.add(rej);
			}
			sql = sql.delete(sql.length()-1, sql.length());
			sql.append(")");
			sql.append(")");
		}*/
		
		// 用户身份
		/**
		 * 如果userHasRole为空，则要采用UNION的查法，返回所有的信息，并且把当前用户有的放在前面
		 */
		if (null != userId && !"".equals(userId)) {
			if (null == userHasRole) { // 
				scallarList.add(new ColumnType("mark", Hibernate.STRING));
//				StringBuffer sql1 = new StringBuffer(sql);
//				StringBuffer sql2 = new StringBuffer(sql);
				
				int pos = sql.indexOf("from"); // 将from 替换为" ,1 as mark from"
//				sql1 = sql1.insert(pos, ",1 as mark ");
//				sql2 = sql2.insert(pos, ",0 as mark ");
				
				sql = sql.insert(pos, ", ur.user_id as mark ");

//				sql1.append(" and ur.user_id=?");
//				sql2.append(" and r.role_id not in (select role_id from pbl.t_mdm_user_role where user_id=?)");
				
//				args.add(userId);
//				args.addAll(args);
				
//				sql = new StringBuffer();
//				sql.append("select * from (").append(sql1).append(" union ").append(sql2).append(")");
				
				Map<String,String> linkedMap = new LinkedHashMap<String,String>();
				linkedMap.put("mark", "asc");
				if(null != sortMap && !sortMap.isEmpty()){
					linkedMap.putAll(sortMap);
				}
				sortMap = linkedMap;

			} else if (true == userHasRole) {
				sql.append(" and ur.user_id=?");
				args.add(userId);
			} else {
				sql.append(" and r.role_id not in (select role_id from pbl.t_mdm_user_role where user_id=?)");
				args.add(userId);
			}
		}
		return super.findPageBySql(sql.toString(), args, pageSize, pageIndex, sortMap, scallarList, RoleDto.class);
	}
	
	public int grantManageMenuToRole(final String roleId, final String menuId) {
		String sql = "insert into pbl.t_mdm_role_man_menu(role_id, menu_id) values(?,?)";
		List<Object> args = new ArrayList<Object>();
		args.add(roleId);
		args.add(menuId);
		return super.executeSql(sql, args);
	}
	public int unGrantManageMenuToRole(final String roleId, final String menuId) {
		String sql = "delete from pbl.t_mdm_role_man_menu where role_id=? and menu_id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(roleId);
		args.add(menuId);
		return super.executeSql(sql, args);
	}
}
