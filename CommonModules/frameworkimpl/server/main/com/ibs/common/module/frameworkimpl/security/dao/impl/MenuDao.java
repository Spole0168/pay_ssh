package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.common.module.frameworkimpl.security.dao.IMenuDao;
import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.server.metadata.SystemMenu;
import com.ibs.portal.framework.util.StringUtils;

import edu.emory.mathcs.backport.java.util.Arrays;

public class MenuDao extends BaseEntityDao<Menu> implements IMenuDao {
	public String save(Menu menu) {
		return (String) super.save(menu);
	}

	public void saveOrUpdate(Menu menu) {
		super.saveOrUpdate(menu);
	}

	public Menu load(String id) {
		return super.load(id);
	}

	public Menu loadCascade(final String id) {
		return (Menu) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Menu menu = (Menu) session.load(Menu.class, id);
				Hibernate.initialize(menu.getParentMenu());
				Hibernate.initialize(menu.getApplication());
				Hibernate.initialize(menu.getIncludedRoles());
				return menu;
			}
		});
	}

	public List<Menu> getAllMenu() {
		return super
				.find("from Menu where menuType = 'menu' order by displayOrder desc, id");
	}

	public void delete(String id) {
		super.remove(id);
	}

	public List<Menu> getAllMenuByApplicationId(String applicationId) {
		return super
		.findByValue(
				"from Menu where application.id = ? and menuType = 'menu' order by displayOrder desc, id",
				applicationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getOperRelatedRoleIds(final String operId){
		final String sql = "select r.role_name from pbl.t_mdm_role_operation o left join pbl.t_mdm_role r on o.role_id=r.role_id where menu_id=?";
		return (List<String>)getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						query.setString(0, operId);

						return query.list();
					}
				});
	}
	
	public List<Menu> getAllMenuByApplicationIdAndLevel(String applicationId,int level) {
		return super
		.findByValue(
				"from Menu where  application.id = ? and menuLevel <= ? order by displayOrder desc, id",
				new Object[]{applicationId,level});
	}

	public List<Menu> getSubMenuByParentId(String parentId) {
		return super
				.findByValue(
						"from Menu where parentMenu.id = ? order by displayOrder asc, id",
						parentId);
	}

	public Integer getSubMenuCountByParentId(String parentId) {
		return super
				.countByValue("from Menu where parentMenu.id = ?", parentId);
	}
	
	public boolean userHasOperation(final String userId, final String operId){
		final String sql = "select count(*) from pbl.t_mdm_role_operation where role_id in (select role_id from pbl.t_mdm_user_role where user_id = ?) and menu_id = ?";

		Integer count = ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						query.setString(0, userId);
						query.setString(1, operId);

						return query.uniqueResult();
					}
				})).intValue();

		if (null != count && count > 0) {
			return true;
		} else
			return false;
	}
	
	public Menu loadByTypeAndValue(final String[] menuTypes,
			final String location) {
		final String hql = "from Menu where menuType in (:menuType) and location = :location";

		return (Menu) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						Map<String, Object> args = new HashMap<String, Object>();
						args.put("menuType", Arrays.asList(menuTypes));
						
						List<Menu> menus = session.createQuery(hql)
								.setProperties(args)
								.setString("location", location)
								.setMaxResults(5)
//								.setCacheable(true)
//								.setCacheRegion("pojoCache")
								.list();

						if (logger.isDebugEnabled())
							logger
									.debug("execute load by [type] & [value] ...");
						
						

						if (null != menus && !menus.isEmpty())
							return menus.get(0);
						else
							return null;
					}
				});

	}
	public Menu loadByTypeAndName(final String menuType,
			final String menuName) {
		final String hql = "from Menu where menuType = :menuType and menuName = :menuName";
		
		return (Menu) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
					throws HibernateException {
						List<Menu> menus = session.createQuery(hql)
						.setString("menuType", menuType)
						.setString("menuName", menuName)
						.setMaxResults(5)
//								.setCacheable(true)
//								.setCacheRegion("pojoCache")
						.list();
						
						if (logger.isDebugEnabled())
							logger
							.debug("execute load by [type] & [name] ...");
						
						
						
						if (null != menus && !menus.isEmpty())
							return menus.get(0);
						else
							return null;
					}
				});
		
	}
	
	public IPage<Menu> getOperListByPage(QueryPage page) {
		return super.findPageBy(page);
	}

	public IPage<Menu> getMenuByRoleByPage(String roleId, QueryPage page, String [] type) {
		StringBuilder hql = new StringBuilder("select m from Menu m,Role r where m in elements(r.grantedMenus) and r.id = ? ");
		List<Object> args = new ArrayList<Object>();
		args.add(roleId);
		if(null != type && type.length>0){
			StringBuilder temp = new StringBuilder("");
			for(int i = 0;i<type.length;i++){
				if(null != type[i] && !"".equals(type[i])){
					temp.append("'"+type[i]+"'");
				}
				temp.append(",");
			}
			if (temp.length() > 0) {
				hql.append(" and m.menuType in (");
				hql.append(temp.substring(0, temp.length()-1));
				hql.append(")");
			}
		}
		return super.findPage(hql.toString(), args, page.getPageSize(), page
				.getPageIndex());
	}
	
	public String saveOrUpdateMenu(final String menuId, final String menuName,
			final String menuTitle, final String menuTitleEn,
			final String location, final String description,
			final Integer displayOrder, final String target,
			final String parentMenuId, final String appId, final String menuType) {
		return (String)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Menu menu = null;
				if (null != menuId && !"".equals(menuId)) {
					menu = (Menu) session.load(Menu.class, menuId);
				}
				if (null == menu) {
					menu = new Menu();
				}
				menu.setMenuName(menuName);
				menu.setMenuTitle(menuTitle);
				menu.setMenuTitleEn(menuTitleEn);
				menu.setLocation(location);
				menu.setDescription(description);
				menu.setDisplayOrder(displayOrder);
				menu.setTarget(target);
				menu.setMenuType(menuType);
				// if it has parent , it should follow parent's   menu level
				if (null != parentMenuId && !"".equals(parentMenuId)) {
					Menu parentMenu = (Menu) session.load(Menu.class,
							parentMenuId);
//					if (null != parentMenu
//							&& null != parentMenu.getApplication()) {
//						menu.setApplication(new Application(parentMenu
//								.getApplication().getId()));
//					}else if(null != appId){
//						menu.setApplication(new Application(appId));
					//}
					menu.setApplication(new Application(appId));
					menu.setParentMenu(parentMenu);
					menu.setMenuLevel(parentMenu.getMenuLevel() + 1);
					 
				} else {
					// root
					menu.setMenuLevel(1);
					menu.setParentMenu(null);
					if (null != appId && !"".equals(appId)) {
						menu.setApplication(new Application(appId));
					}
				}
				session.saveOrUpdate(menu);
				return menu.getId();
			}
		});
	}
	
	public List<Menu> getAllMenuByLevel(int level) {
		return super
		.findByValue(
				"from Menu where menuLevel <= ? order by displayOrder asc, menuTitle asc",
				new Object[]{level});
//		"from Menu where menuLevel <= ? and menuType in (?,?) order by displayOrder asc, menuTitle asc",
//		new Object[]{level,Menu.MENU_TYPE_MENU,Menu.MENU_TYPE_GUI_MENU});
	}
	
	public void updateLevels(String menuId,int levels)
	{
		String hql = "update t_mdm_menu set   menu_level = menu_level+ ? where menu_id in( select menu_id from t_mdm_menu start with menu_id = ? connect by prior menu_id =parent_menu_id  )";
		List<Object> args = new ArrayList<Object>();
		args.add(levels);
		args.add(menuId); 
		
		super.executeSql(  hql, args);
	}

	public Menu getRestrictResourceByValue(String value) {
		String hql = "from Menu where menuType in (?,?) and location=? order by menuType desc";
		Object[] args = new Object[] { Menu.MENU_TYPE_URL, Menu.MENU_TYPE_MENU,
				value };
		List<Menu> menus = super.findByValue(hql, args);
		if (null == menus) {
			return null;
		}
		return menus.get(0);
	}
	
	public List<Menu> searchMenuByTitleWithParent(String str){
		if(null == str || "".equals(str.trim())){
			return null;
		}
		Map<String, String> map = new HashMap<String,String>();
		List<Menu> openMenu = new ArrayList<Menu>();
		String hql = "from Menu where menuTitle like ?";
		List<Menu> searchedMenu = super.findByValue(hql, "%"+str+"%");
		if(null == searchedMenu || searchedMenu.size()<=0){
			return null;
		}
		
		for(Menu menu: searchedMenu){
			if(null == menu){
				continue;
			}
			if(!map.containsKey(menu.getId())){
				map.put(menu.getId(), menu.getId());
				openMenu.add(menu);
			}
			
			String pid = (null == menu.getParentMenu())?null:menu.getParentMenu().getId();
			int cnt = 0;
			while(null != pid && !"".equals(pid) && cnt<10){
				cnt++;
				if(!map.containsKey(pid)){
					Menu pmenu = super.load(pid);
					map.put(pid, pid);
					openMenu.add(pmenu);
					pid = (null == pmenu.getParentMenu())?null:pmenu.getParentMenu().getId();
				}else{
					break;
				}
			}
		}
		Comp com = new Comp();
		Collections.sort(openMenu, com);
		return openMenu;
	}
	
	public static class Comp implements Comparator<Menu> {

		public int compare(Menu o1, Menu o2) {
			return (o1.getMenuLevel()-o2.getMenuLevel());

		}

	}
	
	public List<Menu> getMenuByRoleCodes(List<String> roleCodes, Integer level,
			String parentMenuId,List<String> menuTypes) {
		logger.trace("" + this.getClass().getSimpleName()
				+ " method getMenuByRoleCodes");

		StringBuffer sql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		sql
				.append("select distinct m.menu_id as id,m.menu_name as menuName,"
						+ "m.menu_title as menuTitle,m.menu_title_en as menuTitleEn,"
						+ "m.location as location,m.description as description,"
						+ "m.display_order as displayOrder,m.menu_level as menuLevel,"
						+ "m.menu_type as menuType,"
						+ "(select count(*) from pbl.t_mdm_menu where parent_menu_id=m.menu_id) as subMenuCount"
						+ " from pbl.t_mdm_menu m");
		if (null != roleCodes && roleCodes.size() > 0) {
			sql.append(", pbl.t_mdm_role_menu rm, pbl.t_mdm_role r"
					+ " where m.menu_id=rm.menu_id and rm.role_id=r.role_id");
			sql.append(" and r.role_code in (");
			for (String code : roleCodes) {
				sql.append("?,");
				args.add(code);
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
		} else {
			sql.append(" where 1=1");
		}
		if (null != level) {
			sql.append(" and m.menu_level=?");
			args.add(level);
		}

		if (StringUtils.isNotEmpty(parentMenuId)) {
			sql.append(" and m.parent_menu_id=?");
			args.add(parentMenuId);
		}
		
		if(null != menuTypes && menuTypes.size()>0){
			sql.append(" and m.menu_type in (");
			for(String type:menuTypes){
				sql.append("?,");
				args.add(type);
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
		}

		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("id", Hibernate.STRING));
		scallarList.add(new ColumnType("menuName", Hibernate.STRING));
		scallarList.add(new ColumnType("menuTitle", Hibernate.STRING));
		scallarList.add(new ColumnType("menuTitleEn", Hibernate.STRING));
		scallarList.add(new ColumnType("location", Hibernate.STRING));
		scallarList.add(new ColumnType("description", Hibernate.STRING));
		scallarList.add(new ColumnType("displayOrder", Hibernate.INTEGER));
		scallarList.add(new ColumnType("menuLevel", Hibernate.INTEGER));
		scallarList.add(new ColumnType("menuType", Hibernate.STRING));
		scallarList.add(new ColumnType("subMenuCount", Hibernate.INTEGER));
		
		return super.findBySql(sql.toString(), args, null, scallarList,
				Menu.class);
	}

	public List<SystemMenu> findMenuByRoleIds(List<String> roleIds) {
		logger.trace("entering dao");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct ");
		sql.append(" A.Menu_Id as id, ");
		sql.append(" A.App_Id as appId, ");
		sql.append(" A.Menu_Name as name, ");
		sql.append(" A.Menu_Title as title, ");
		sql.append(" A.Location as location, ");
		sql.append(" A.Menu_Level as \"LEVEL\", ");
		sql.append(" A.Display_Order as displayOrder, ");
		sql.append(" A.Target as target, ");
		sql.append(" A.Menu_Type as type, ");
		sql.append(" A.Parent_Menu_Id as parentId ");
		sql.append(" from pbl.t_mdm_menu A, pbl.t_mdm_role_menu B ");
		sql.append(" where A.Menu_Id = B.Menu_Id and ");
		sql.append(" B.Role_Id in (:roleIds) and A.Menu_Type = :menuType ");
		sql.append(" order by A.Menu_Level, A.Parent_Menu_Id, A.Display_Order ");
		
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("id", Hibernate.STRING));
		scallarList.add(new ColumnType("appId", Hibernate.STRING));
		scallarList.add(new ColumnType("name", Hibernate.STRING));
		scallarList.add(new ColumnType("title", Hibernate.STRING));
		scallarList.add(new ColumnType("location", Hibernate.STRING));
		scallarList.add(new ColumnType("level", Hibernate.INTEGER));
		scallarList.add(new ColumnType("displayOrder", Hibernate.INTEGER));
		scallarList.add(new ColumnType("target", Hibernate.STRING));
		scallarList.add(new ColumnType("type", Hibernate.STRING));
		scallarList.add(new ColumnType("parentId", Hibernate.STRING));
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleIds", roleIds);
		args.put("menuType", Menu.MENU_TYPE_MENU);
		
		return super.findBySqlWithIn(sql.toString(), args, null, scallarList, SystemMenu.class);
	}

	public List<SystemMenu> findGuiMenuByRoleIds(List<String> roleIds) {
		logger.trace("entering dao");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct ");
		sql.append(" A.Menu_Id as id, ");
		sql.append(" A.App_Id as appId, ");
		sql.append(" A.Menu_Name as name, ");
		sql.append(" A.Menu_Title as title, ");
		sql.append(" A.Location as location, ");
		sql.append(" A.Menu_Level as \"LEVEL\", ");
		sql.append(" A.Display_Order as displayOrder, ");
		sql.append(" A.Target as target, ");
		sql.append(" A.Menu_Type as type, ");
		sql.append(" A.Parent_Menu_Id as parentId ");
		sql.append(" from pbl.t_mdm_menu A ");
		sql.append(" where ");
		sql.append(" (A.Menu_Type = :menuType or A.Menu_Type = :menuType2) ");
		sql.append(" order by A.Menu_Level, A.Parent_Menu_Id, A.Display_Order ");
		
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("id", Hibernate.STRING));
		scallarList.add(new ColumnType("appId", Hibernate.STRING));
		scallarList.add(new ColumnType("name", Hibernate.STRING));
		scallarList.add(new ColumnType("title", Hibernate.STRING));
		scallarList.add(new ColumnType("location", Hibernate.STRING));
		scallarList.add(new ColumnType("level", Hibernate.INTEGER));
		scallarList.add(new ColumnType("displayOrder", Hibernate.INTEGER));
		scallarList.add(new ColumnType("target", Hibernate.STRING));
		scallarList.add(new ColumnType("type", Hibernate.STRING));
		scallarList.add(new ColumnType("parentId", Hibernate.STRING));
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleIds", roleIds);
		args.put("menuType", Menu.MENU_TYPE_GUI_MENU);
		args.put("menuType2", Menu.MENU_TYPE_PDA_MENU);
		
		return super.findBySqlWithIn(sql.toString(), args, null, scallarList, SystemMenu.class);
	}

	public List<SystemMenu> findOperationByRoleIds(List<String> roleIds) {
		logger.trace("entering dao");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct ");
		sql.append(" A.Menu_Id as id, ");
		sql.append(" A.App_Id as appId, ");
		sql.append(" A.Menu_Name as name, ");
		sql.append(" A.Menu_Title as title, ");
		sql.append(" A.Location as location, ");
		sql.append(" A.Menu_Level as \"LEVEL\", ");
		sql.append(" A.Display_Order as displayOrder, ");
		sql.append(" A.Target as target, ");
		sql.append(" A.Menu_Type as type, ");
		sql.append(" A.Parent_Menu_Id as parentId ");
		sql.append(" from pbl.t_mdm_menu A, pbl.t_MDM_ROLE_OPERATION B ");
		sql.append(" where A.Menu_Id = B.Menu_Id and ");
		sql.append(" B.Role_Id in (:roleIds) ");
		sql.append(" order by A.Menu_Level, A.Parent_Menu_Id, A.Display_Order ");
		
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("id", Hibernate.STRING));
		scallarList.add(new ColumnType("appId", Hibernate.STRING));
		scallarList.add(new ColumnType("name", Hibernate.STRING));
		scallarList.add(new ColumnType("title", Hibernate.STRING));
		scallarList.add(new ColumnType("location", Hibernate.STRING));
		scallarList.add(new ColumnType("level", Hibernate.INTEGER));
		scallarList.add(new ColumnType("displayOrder", Hibernate.INTEGER));
		scallarList.add(new ColumnType("target", Hibernate.STRING));
		scallarList.add(new ColumnType("type", Hibernate.STRING));
		scallarList.add(new ColumnType("parentId", Hibernate.STRING));
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleIds", roleIds);
		
		return super.findBySqlWithIn(sql.toString(), args, null, scallarList, SystemMenu.class);
	}

	/**
	 * 通过menuId反查包含此菜单的用户
	 * @param menuId
	 * @return
	 */
	public List<String> getUserIdListByMenuId(String menuId) {
		List<Object> args = new ArrayList<Object>(0);

		StringBuffer sql = new StringBuffer();
		sql.append("select distinct user_id as key,1 as value" +
				" from pbl.t_mdm_user_role ur inner join (" +
				"select distinct role_id from pbl.t_mdm_role_menu where menu_id=?" +
				" union all" +
				" select distinct role_id from pbl.t_mdm_role_operation where menu_id=? ) t" +
				" on t.role_id=ur.role_id");
		args.add(menuId);
		args.add(menuId);
		//args.add(menuId);
		
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("key", Hibernate.STRING));
		scallarList.add(new ColumnType("value", Hibernate.STRING));
		
		List<OptionObjectPair> userList = super.findBySql(sql.toString(), args, null, scallarList, OptionObjectPair.class);
		List<String> userIdList = new ArrayList<String>();
		
		if(null != userList && userList.size()>0){
			for(OptionObjectPair u:userList){
				if(null != u && null != u.getKey()){
					userIdList.add(u.getKey());
				}
			}
		}
		
		return userIdList;
	}
	
	public IPage<RoleDto> getRoleByMenu(String menuId,
			int pageSize, int pageIndex, Map<String, String> sortMap){
		
		StringBuffer sql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		sql
		.append(" select * from (select r.role_id as id,r.role_name as roleName,r.role_code as roleCode ,R.DESCRIPTION as description"
				+ " from pbl.t_mdm_role r, pbl.t_mdm_role_menu me where r.role_id = me.role_id "						
				+ " and me.menu_id = ? "
				+ " union " 
				+ "select r.role_id as id,r.role_name as roleName,r.role_code as roleCode ,R.DESCRIPTION as description"
				+ " from pbl.t_mdm_role r, pbl.t_MDM_ROLE_OPERATION me where r.role_id = me.role_id "						
				+ " and me.menu_id = ? )"
				);
		args.add(menuId);
		args.add(menuId);
		//args.add(menuId);

		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("id", Hibernate.STRING));
		scallarList.add(new ColumnType("roleName", Hibernate.STRING));
		scallarList.add(new ColumnType("roleCode", Hibernate.STRING));
		scallarList.add(new ColumnType("description", Hibernate.STRING));

		return super.findPageBySql(sql.toString(), args, pageSize, pageIndex, sortMap, scallarList, RoleDto.class);

	}

	public List<Menu> findMenuByParm(String menuName, String menuType) {
		logger.trace("entering Dao...");
		StringBuffer hql = new StringBuffer();
		List<Object> args=new ArrayList<Object>(0);
		
		hql.append(" from Menu where 1=1");
		
		if(StringUtils.isNotEmpty(menuName)){
			hql.append(" and menuName = ?");
			args.add(menuName.toUpperCase());
		}
		
		if(StringUtils.isNotEmpty(menuType)){
			hql.append(" and menuType = ?");
			args.add(menuType);
		}
		
		return super.findByValue(hql.toString(), args.toArray());
	}
	
	public Menu getMenuByMenuName(String menuName) {
		return super.loadBy("menuName", menuName);
	}
	
	public Menu getMenuByMenuTitle(String menuTitle) {
		return super.loadBy("menuTitle", menuTitle);
	}
}
