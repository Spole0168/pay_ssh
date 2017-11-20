package com.ibs.portal.framework.server.cache.lru;

import com.ibs.portal.framework.server.cache.CacheEntry;

/**
 * LRU 缓存对象，包含数据对象与最近访问时间
 * 
 * @author 
 * 
 */
public class LRUCacheEntry extends CacheEntry implements
		Comparable<LRUCacheEntry> {

	// 最近访问时间
	long lastAccessTime = System.currentTimeMillis();

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long time) {
		lastAccessTime = time;
	}

	public boolean isExpired(long ttl) {
		// ttl为负数时永不过期
		if (ttl < 0)
			return false;

		return (System.currentTimeMillis() - lastAccessTime) > ttl;
	}

	public int compareTo(LRUCacheEntry o) {
		if (lastAccessTime > o.getLastAccessTime())
			return 1;
		if (lastAccessTime < o.getLastAccessTime())
			return -1;
		return 0;
	}

}
