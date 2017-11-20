package com.ibs.portal.framework.server.cache;

/**
 * 缓存数据提供器,从实际的数据源中获取数据
 * @author 
 *
 */
public interface ICacheDataProvider {

	/**
	 * 根据键值从实际的数据源中获取数据
	 * @param key
	 * @return
	 */
	public Object getData(Object key);
}
