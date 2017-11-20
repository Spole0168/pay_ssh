package com.ibs.common.module.frameworkimpl.config.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.config.dao.IPropertiesDao;
import com.ibs.common.module.frameworkimpl.config.domain.Properties;
import com.ibs.portal.framework.server.dao.hibernate.BaseGenericDao;
import com.ibs.portal.framework.server.metadata.ColumnType;

public class PropertiesDaoImpl extends BaseGenericDao implements IPropertiesDao {
	
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
		
		List<Properties> list = super.findBySql(sql, args, null, columnTypes, Properties.class);
		
		for (Properties property : list) {
			properties.put(property.getKey(), property.getValue());
		}
		
		return properties;
	}

}

