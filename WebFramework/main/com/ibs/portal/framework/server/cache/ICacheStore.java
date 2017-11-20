package com.ibs.portal.framework.server.cache;

/**
 * 缓存数据存储,基于缓存组件操作数据
 * @author 
 *
 */
public interface ICacheStore {

	/**
	 * 通过键值获取缓存数据。
	 * @param key
	 * @return
	 */
	public Object getCacheData(Object key);

	/**
	 * 清除指定的缓存数据
	 * @param key
	 */
	public void removeCacheData(Object key);

	/**
	 * 将数据存到缓存存储。
	 * @param key
	 * @param data
	 */
	public void putCacheData(Object key,Object data);

	/**
	 * 非同步方法，通过键值判断数据是否在缓存存储中存在。
	 * @param key
	 * @return
	 */
	public boolean isDataInCache(Object key);

	/**
	 * 释放资源
	 */
	public void shutdown();
}
