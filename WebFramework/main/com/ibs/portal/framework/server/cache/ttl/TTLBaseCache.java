package com.ibs.portal.framework.server.cache.ttl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.cache.BaseCache;

/**
 * 基础数据缓存类，用于缓存有定期的数据过期策略的数据，数据的获取并不能延长该数据在缓存中的生命周期，
 * 同时该数据在内存中存储，不需利用磁盘的存储,而且缓存数据为单一的对象。
 * 
 * @author 
 * 
 */
public class TTLBaseCache extends BaseCache {

	TTLCacheEntry cacheObject = new TTLCacheEntry(); // 主要用于缓存集合

	protected final Logger logger = LoggerFactory
			.getLogger(TTLBaseCache.class);

	/**
	 * 数据缓存的生命周期
	 */
	long ttl = 60 * 1000;

	/**
	 * 
	 * @return the ttl
	 */
	public long getTtl() {
		return ttl;
	}

	/**
	 * @param ttl
	 *            the ttl to set
	 */
	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	/*
	 * 为了加快缓存数据的存取,使用本地缓存镜像进行缓存数据的存取； 缓存数据存储中只存储键值，利用缓存数据存储的数据过期控制策略
	 */
	public Object getData(Object key) {
		// 若provider未加载完成，此方法无效
		if (cacheProvider == null)
			return null;

		// 判断缓存数据是否在缓存存储中存在
		if (cacheObject.getData() == null || cacheObject.isExpired(ttl)) {
			// 直接从缓存镜像中返回
			// 使用缓存数据提供器获取数据进行缓存存储及缓存镜像的刷新
			cacheObject.setData(null);
			synchronized (this) {
				if (cacheObject.getData() == null) {
					Object data = cacheProvider.getData(key);
					cacheObject.setData(data);
					cacheObject.setKey(key);
					cacheObject.setCreateTime(System.currentTimeMillis());
				}
			}
		}
		return cacheObject.getData();
	}

	/*
	 * 根据缓存存储中目前有效的缓存数据，从实际数据源中进行刷新;用于定时调度进行缓存数据的刷新。
	 */
	public void refresh() {
		synchronized (this) {
			if (cacheObject.getData() != null) {
				Object data = cacheProvider.getData(cacheObject.getKey());
				cacheObject.setData(data);
				cacheObject.setCreateTime(System.currentTimeMillis());
			}
		}
	}

	public void removeData(Object key) {
		cacheObject.setData(null);
	}

	@Override
	public boolean containsKey(Object key) {
		if (null != cacheObject && key.equals(cacheObject.getKey())
				&& !cacheObject.isExpired(ttl)) {
			return true;
		} else {
			return false;
		}
	}
}
