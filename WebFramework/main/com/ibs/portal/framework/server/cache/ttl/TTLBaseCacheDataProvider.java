package com.ibs.portal.framework.server.cache.ttl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.cache.BaseEhCacheDataProvider;
import com.ibs.portal.framework.server.cache.ICache;


/**
 * 具体的缓存实现模块继承该类
 * 注意：一个CACHE只能支持一个KEY
 * @author luoyue
 *
 */
public abstract class TTLBaseCacheDataProvider extends BaseEhCacheDataProvider {

	protected final Logger logger = LoggerFactory
			.getLogger(TTLBaseCacheDataProvider.class);

	/* (non-Javadoc)
	 * @see com.ibs.portal.framework.server.cache.BaseEhCacheDataProvider#createCache()
	 */
	@Override
	protected ICache createCache() {
		TTLBaseCache cache = new TTLBaseCache();
		cache.setCacheName(cacheName);
		cache.setTtl(ttl * 60 * 1000);
		return cache;
	}

	/**
	 * 分钟数
	 */
	long ttl;

	/**
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

}
