package com.ibs.portal.framework.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 完全使用CacheStore进行缓存数据存取,获取数据时忽略缓存实体的过期时间.未从缓存里获取到数据时会更新缓存
 * 
 * @author 
 * 
 */
public class BaseCache implements ICache {

	String name;
	ICacheStore cacheStore;
	protected ICacheDataProvider cacheProvider;

	protected final Logger logger = LoggerFactory.getLogger(BaseCache.class);

	public String getCacheName() {
		return name;
	}

	public void setCacheName(String name) {
		this.name = name;
	}

	public ICacheStore getCacheStore() {
		return cacheStore;
	}

	public void setCacheStore(ICacheStore cacheStore) {
		this.cacheStore = cacheStore;
	}

	public ICacheDataProvider getCacheDataProvider() {
		return cacheProvider;
	}

	public void setCacheProvider(ICacheDataProvider dataProvider) {
		this.cacheProvider = dataProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibs.portal.framework.server.cache.ICache#getData(java.lang
	 * .Object)
	 */
	public Object getData(Object key) {
		// 若provider未加载完成，此方法无效
		if (cacheProvider == null)
			return null;

		// 判断缓存数据是否在缓存存储中存在
		Object data = cacheStore.getCacheData(key);
		if (data == null) {
			// 从实际的数据源中获取数据
			data = cacheProvider.getData(key);
			cacheStore.putCacheData(key, data);
		}
		return data;
	}

	public void refresh() {

	}

	public void shutdown() {
		if (cacheStore != null)
			cacheStore.shutdown();
	}

	public void removeData(Object key) {
		if (this.cacheStore != null) {
			this.cacheStore.removeCacheData(key);
		}
	}

	public boolean containsKey(Object key) {
		if (this.cacheStore != null) {
			Object data = cacheStore.getCacheData(key);
			return data != null;
		}
		return false;
	}

}
