package com.ibs.portal.framework.server.cache;

/**
 * 数据缓存代理接口,用于缓存数据的获取
 * @author 
 *
 */
public interface ICache {
	/**
	 * 获取缓存名
	 * @return
	 */
	public String getCacheName();

	/**
	 * 设置缓存名
	 * @param name
	 */
	public void setCacheName(String name);

	/**
	 * 获取缓存数据存储
	 * @return
	 */
	public ICacheStore getCacheStore();

	/**
	 * 设置缓存数据存储
	 * @param cacheStore
	 */
	public void setCacheStore(ICacheStore cacheStore);

	/**
	 * 获取缓存数据提供器
	 * @return
	 */
	public ICacheDataProvider getCacheDataProvider();

	/**
	 * 设置缓存的数据提供器，用于从数据源中获取实际的数据。
	 * @param dataProvider
	 */
	public void setCacheProvider(ICacheDataProvider dataProvider);

	/**
	 * 是否存在某个key值得缓存
	 * @param key
	 * @return
	 */
	public boolean containsKey(Object key);
	/**
	 * 获取缓存数据
	 * @param key
	 * @return
	 */
	public Object getData(Object key);

	/**
	 * 从缓存中清除指定的数据
	 * @param key
	 */
	public void removeData(Object key);

	/**
	 * 根据缓存存储中目前有效的缓存数据，从实际数据源中进行刷新;用于定时调度进行缓存数据的刷新。
	 */
	public void refresh();

	/**
	 * 释放资源
	 */
	public void shutdown();
}
