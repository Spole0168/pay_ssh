package com.ibs.common.module.frameworkimpl.config;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.config.dao.IPropertiesDao;

/**
 * @author 
 * $Id: DatabasePropertyLoaderStrategy.java 23226 2010-12-23 06:59:30Z  $
 */
public class DatabasePropertyLoaderStrategy {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private IPropertiesDao propertiesDao;

    /**
     * Load properties from the configured data source. If overrideExisting is true,
     * replace any existing properties in the {@link Properties} instance with the ones loaded from
     * the data source. Otherwise, the existing properties take precedence in the case of property key collisions
     *
     * @param properties {@link Properties} instance containing any propreties that have already been loaded
     * @param overrideExisting if true, the properties loaded from this data source take precedence in the case of key collisions.
     */
	public void loadProperties(Properties properties, boolean overrideExisting) {
		
		logger.trace("entering...");

		Map<String, String> maps = this.propertiesDao.findAllValid();
		
		properties.putAll(maps);
	}

	public IPropertiesDao getPropertiesDao() {
		return propertiesDao;
	}

	public void setPropertiesDao(IPropertiesDao propertiesDao) {
		this.propertiesDao = propertiesDao;
	}

}
