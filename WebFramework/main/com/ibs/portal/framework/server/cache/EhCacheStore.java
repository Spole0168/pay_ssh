package com.ibs.portal.framework.server.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheStore implements ICacheStore {

	private final net.sf.ehcache.Cache cache;

	public EhCacheStore(String name) {
		final CacheManager manager = CacheManager.getInstance();
		Cache c = manager.getCache(name);
		if (c == null) {
			manager.addCache(name);
			c = manager.getCache(name);
		}
		this.cache = c;
		if (this.cache == null)
			throw new java.lang.IllegalStateException(
					"Can not create ehCache entity! Name[" + name + "]");
	}

	public Object getCacheData(Object key) {
		Element element = cache.get(key);
		if (element == null)
			return null;
		return element.getObjectValue();
	}

	public boolean isDataInCache(Object key) {
		return cache.isKeyInCache(key);
	}

	public void putCacheData(Object key, Object data) {
		cache.put(new Element(key, data));
	}

	public void shutdown() {
		cache.dispose();
	}

	public void removeCacheData(Object key) {
		if (cache != null) {
			cache.remove(key);
		}
	}

}
