package com.ibs.portal.framework.server.cache.ttl;

import com.ibs.portal.framework.server.cache.CacheEntry;

/**
 * 缓存实体,提供缓存创建时间,用于比对缓存是否已过期
 * 
 * @author luoyue
 *
 */
public class TTLCacheEntry extends CacheEntry implements
	Comparable<TTLCacheEntry> {

	long	createTime	= System.currentTimeMillis ();

	public boolean isExpired( long ttl ) {
		if ( ttl < 0 )
			return false;
		return ( System.currentTimeMillis () - createTime ) > ttl;
	}

	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime( long createTime ) {
		this.createTime = createTime;
	}

	public int compareTo(TTLCacheEntry o) {
		if (createTime > o.getCreateTime())
			return 1;
		if (createTime < o.getCreateTime())
			return -1;
		return 0;
	}
}
