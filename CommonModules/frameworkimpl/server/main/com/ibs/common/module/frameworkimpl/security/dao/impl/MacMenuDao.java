package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.common.module.frameworkimpl.security.dao.IMacMenuDao;
import com.ibs.common.module.frameworkimpl.security.domain.MacMenu;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;

public class MacMenuDao extends BaseEntityDao<MacMenu> implements IMacMenuDao{
	public void deleteAllBindingMacByMenuId(final String menuId) {
		logger.trace("Entering dao...");
		
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
				String sql = "delete from pbl.T_PBL_MAC_MENU where MENU_ID = ?";
				session.createSQLQuery(sql).setString(0, menuId).executeUpdate();
				return null;
			}
		});
		
	}

	public String save(MacMenu macMenu) {
		logger.trace("Entering dao...");
		
		return (String)super.save(macMenu);
	}

	public List<String> getMenuIdsByMacIds(List<String> macIds) {
		logger.trace("entering dao...");

		List<String> menuIds = new ArrayList<String>();
		
		if (macIds == null || macIds.size() == 0)
			return menuIds;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.MENU_ID as menuId from pbl.T_PBL_MAC_MENU t where t.MAC_ID in (:MAC_IDS)");
		
		Map<String, Object> args = new HashMap<String, Object>();

		args.put("MAC_IDS", macIds);
		
		List<ColumnType> scallar = new ArrayList<ColumnType>();
		scallar.add(new ColumnType("menuId", Hibernate.STRING));
		
		List<MacMenu> macMenus = super.findBySqlWithIn(sql.toString(), args, null, scallar, MacMenu.class);
		for (MacMenu macMenu : macMenus) {
			menuIds.add(macMenu.getMenuId());
		}
		
		return menuIds;
	}
}
