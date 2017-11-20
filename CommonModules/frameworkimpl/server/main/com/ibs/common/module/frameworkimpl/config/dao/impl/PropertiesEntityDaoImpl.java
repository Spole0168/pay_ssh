package com.ibs.common.module.frameworkimpl.config.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.config.dao.IPropertiesEntityDao;
import com.ibs.common.module.frameworkimpl.config.domain.Properties;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class PropertiesEntityDaoImpl extends BaseEntityDao<Properties>
		implements IPropertiesEntityDao {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public HashMap<String, String> findAllValid() {
		logger.trace("entering dao...");

		HashMap<String, String> properties = new HashMap<String, String>();
		String sql = "SELECT key, value FROM pbl.T_PBL_PROPERTIES where status = ?";

		List<Object> args = new ArrayList<Object>();
		args.add(Boolean.TRUE);
		List<ColumnType> columnTypes = new ArrayList<ColumnType>();
		columnTypes.add(new ColumnType("key", Hibernate.STRING));
		columnTypes.add(new ColumnType("value", Hibernate.STRING));

		List<Properties> list = super.findBySql(sql, args, null, columnTypes,
				Properties.class);

		for (Properties property : list) {
			properties.put(property.getKey(), property.getValue());
		}

		return properties;
	}

	public Page<Properties> findConfigByPage(QueryPage queryPage,
			Map<String, String> searchFields) {

		logger.trace("entering dao...");

		StringBuffer sqlBuffer = new StringBuffer();
		List<Object> queryList = new ArrayList<Object>();

		sqlBuffer
				.append("select id as id,key as key,value as value,description as description,status as status from pbl.t_pbl_properties where 1=1");

		if (StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("key")))) {
			queryList
					.add("%" + StringUtils.trim(searchFields.get("key")) + "%");
			sqlBuffer.append(" and key like ?");
		}

		if (StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("value")))) {
			queryList.add("%" + StringUtils.trim(searchFields.get("value"))
					+ "%");
			sqlBuffer.append(" and value like ?");
		}

		if (StringUtils
				.isNotEmpty(StringUtils.trim(searchFields.get("status")))) {
			queryList.add(searchFields.get("status"));
			sqlBuffer.append(" and status = ?");
		}

		queryPage.addScalar("id").addScalar("key").addScalar("value")
				.addScalar("description")
				.addScalar("status", Hibernate.BOOLEAN);

		return (Page<Properties>) super.findPageBySql(sqlBuffer.toString(),
				queryList, queryPage.getPageSize(), queryPage.getPageIndex(),
				queryPage.getSortMap(), queryPage.getScalarList(),
				Properties.class);
	}

	public String saveProperties(Properties properties) {

		logger.trace("entering dao...");

		return (String) super.save(properties);
	}

	public void updateProperties(Properties properties) {

		logger.trace("entering dao...");

		super.update(properties);
	}

	public Properties getPropertiesById(String id) {

		logger.trace("entering dao...");

		return super.load(id);
	}

	public Properties getPropertiesByKey(String key) {
		
		logger.trace("entering dao...");
		
		List<Properties> list = super.findByValue("from Properties p where p.key = ?", key);
		
		if (list == null || list.isEmpty())
			return null;
		
		return list.get(0);
	}
}
