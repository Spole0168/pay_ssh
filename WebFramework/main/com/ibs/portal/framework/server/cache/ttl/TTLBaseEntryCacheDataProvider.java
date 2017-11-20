package com.ibs.portal.framework.server.cache.ttl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.cache.BaseEhCacheDataProvider;
import com.ibs.portal.framework.server.cache.ICache;


/**
 * 具体的缓存实现模块继承该类
 * 注意：一个CACHE可以支持一个KEY
 * @author 
 */
public abstract class TTLBaseEntryCacheDataProvider extends BaseEhCacheDataProvider {

	protected final Logger logger = LoggerFactory
			.getLogger(TTLBaseEntryCacheDataProvider.class);

	@Override
	protected ICache createCache() {
		
		if (initialCapacity <= 0)
			initialCapacity = 100;
		
		if (concurrencyLevel <= 0)
			concurrencyLevel = 100;
		
		TTLBaseEntryCache cache = new TTLBaseEntryCache(initialCapacity, concurrencyLevel);
		cache.setCacheName(cacheName);
		cache.setTtl(ttl * 60 * 1000);

		if (sleepTime <= 0)
			sleepTime = 5;
		
		cache.setSleepTime(sleepTime * 60 * 1000);

		if (threshHold <= 0)
			threshHold = 1000;
		
		if (maxSize < threshHold + 10)
			maxSize = 2 * threshHold;
		
		cache.setThreshHold(threshHold);
		cache.setMaxSize(maxSize);
		return cache;
	}

	/**
	 * 分钟数
	 */
	long ttl;
	int initialCapacity;
	int concurrencyLevel;
	int threshHold;
	int maxSize;

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

	/**
	 * 分钟数
	 */
	long sleepTime;

	/**
	 * @return the sleepTime
	 */
	public long getSleepTime() {
		return sleepTime;
	}

	/**
	 * @param sleepTime
	 *            the sleepTime to set
	 */
	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize
	 *            the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return the initialCapacity
	 */
	public int getInitialCapacity() {
		return initialCapacity;
	}

	/**
	 * @return the concurrencyLevel
	 */
	public int getConcurrencyLevel() {
		return concurrencyLevel;
	}

	/**
	 * @return the threshHold
	 */
	public int getThreshHold() {
		return threshHold;
	}

	/**
	 * @param initialCapacity
	 *            the initialCapacity to set
	 */
	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	/**
	 * @param concurrencyLevel
	 *            the concurrencyLevel to set
	 */
	public void setConcurrencyLevel(int concurrencyLevel) {
		this.concurrencyLevel = concurrencyLevel;
	}

	/**
	 * @param threshHold
	 *            the threshHold to set
	 */
	public void setThreshHold(int threshHold) {
		this.threshHold = threshHold;
	}

}
