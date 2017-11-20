package com.ibs.portal.framework.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * CacheStore缺省使用ehcache,具体的实现可只继承该类,抽象类已经代理实现了缓存组件的初始化
 * 
 * @author luoyue
 *
 */
public abstract class BaseEhCacheDataProvider implements ICacheDataProvider,
		InitializingBean, DisposableBean {

	protected final Logger logger = LoggerFactory
			.getLogger(BaseEhCacheDataProvider.class);

	protected String cacheName;

	/**
	 * @return the cacheName
	 */
	public String getCacheName() {
		return cacheName;
	}

	/**
	 * @param cacheName
	 *            the cacheName to set
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	/* (non-Javadoc)
	 * 向CacheManager注册缓存
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		ICache cache = initializeCache();
		if (cache != null)
			CacheManager.getInstance().regCache(cache.getCacheName(), cache);
	}

	protected ICache cache = null;

	protected ICache initializeCache() {
		ICache cache = getCache();
		if (cache == null) {
			cache = this.createCache();
			cache.setCacheProvider(this);
			setCache(cache);
		}
		return cache;
	}

	/**
	 * 不同的子类可能使用的CACHE类型不同,子类覆盖该方法,缺省使用ehcache
	 */
	protected ICache createCache() {
		ICache cache = new BaseCache();
		cache.setCacheName(cacheName);
		EhCacheStore store = new EhCacheStore(cacheName);
		cache.setCacheStore(store);
		return cache;
	}

	/**
	 * @return the cache
	 */
	protected ICache getCache() {
		return cache;
	}

	/**
	 * @param cache
	 *            the cache to set
	 */
	protected void setCache(ICache cache) {
		this.cache = cache;
	}

	public void destroy() throws Exception {
		ICache cache = getCache();
		if (cache != null)
			cache.shutdown();
	}

}
