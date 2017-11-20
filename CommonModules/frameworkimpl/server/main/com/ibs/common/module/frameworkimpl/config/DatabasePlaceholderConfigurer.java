package com.ibs.common.module.frameworkimpl.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

/**
 * @author 
 * $Id: DatabasePlaceholderConfigurer.java 23353 2010-12-23 12:49:26Z  $
 */
public class DatabasePlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	private Resource properties;

	private DatabasePropertyLoaderStrategy databasePropertyLoaderStrategy;

	public DatabasePlaceholderConfigurer() {
		super();
	}

	public DatabasePlaceholderConfigurer(Resource properties,
			DatabasePropertyLoaderStrategy databasePropertyLoaderStrategy) {

		super();
		this.properties = properties;
		this.databasePropertyLoaderStrategy = databasePropertyLoaderStrategy;

	}

	@Override
	protected void loadProperties(final Properties props) throws IOException {
		
		logger.trace("entering...");
		
		List<Resource> resources = new ArrayList<Resource>();

		if (properties != null) {
			resources.add(properties);
		}
		
		Resource[] locations = new Resource[resources.size()];
		resources.toArray(locations);
		setLocations(locations);
		
		super.loadProperties(props);

		try {
			databasePropertyLoaderStrategy.loadProperties(props, Boolean.TRUE);
			Set<Entry<Object, Object>> entrySet =  props.entrySet();
			StringBuffer sb = new StringBuffer();
			for (Entry<Object, Object> entry : entrySet) {
				sb.append(ToStringBuilder.reflectionToString(entry)).append("\n");
			}
			logger.debug(sb.toString());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
