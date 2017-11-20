package com.ibs.portal.framework.server.cache.lru;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.cache.BaseCache;

/**
 * LRU Cache 用来通过LRU 控制策略进行缓存数据的刷新，为了避免在高并发情况下对整个缓存存储进行锁定，
 * 使用自有的缓存类,不使用CacheStore
 * 
 * @author 
 * 
 */
public class LRUBaseCache extends BaseCache {

	protected final Logger logger = LoggerFactory.getLogger(LRUBaseCache.class);

	ConcurrentHashMap<Object, LRUCacheEntry> cacheMap;

	/**
	 * 使用指定的初始化容量与并发访问度构造缓存。
	 * 
	 * @param initialCapactiy
	 *            初始化容量
	 * @param concurrentLevel
	 *            并发访问度
	 */
	public LRUBaseCache(int initialCapactiy, int concurrentLevel) {
		cacheMap = new ConcurrentHashMap<Object, LRUCacheEntry>(
				initialCapactiy, 0.75f, concurrentLevel);

		// 启动缓存清洗线程
		new Thread(new PurgeJob()).start();
	}

	/**
	 * 缓存预警值，当缓存大小达到该值时，清洗线程将使用LRU策略进行缓存清洗。
	 */
	int threshHold = 1000;

	public int getThreshHold() {
		return threshHold;
	}

	public void setThreshHold(int threshHold) {
		this.threshHold = threshHold;
	}

	/**
	 * 缓存大小最大限制。
	 */
	int maxSize = 2 * threshHold;

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 缓存生存周期
	 */
	long ttl = 30 * 60 * 1000;

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

	public Object getData(Object key) {
		// 若provider未加载完成，此方法无效
		if (cacheProvider == null)
			return null;

		// 判断缓存数据是否在LRU缓存中存在
		LRUCacheEntry cacheEntry = cacheMap.get(key);
		if (cacheEntry != null && !cacheEntry.isExpired(ttl)) {
			// 2011-03-30 delete 以下行，避免cache一直生效
			cacheEntry.setLastAccessTime(System.currentTimeMillis());
			return cacheEntry.getData();
		} else {
			if (cacheMap.size() >= maxSize)
				purgeCache();
			Object data = cacheProvider.getData(key);
			cacheEntry = new LRUCacheEntry();
			cacheEntry.setData(data);
			cacheEntry.setKey(key);
			cacheMap.put(key, cacheEntry);
			return data;
		}
	}

	public void removeData(Object key) {
		cacheMap.remove(key);
	}

	/**
	 * 根据LRU策略进行缓存清洗
	 */
	synchronized void purgeCache() {
		int cacheSize = cacheMap.size();
		if (cacheSize > threshHold) {
			Object[] cacheValues = cacheMap.values().toArray();
			Arrays.sort(cacheValues);
			int purgeCount = cacheSize - threshHold;
			for (int i = 0; i < purgeCount; i++) {
				LRUCacheEntry cacheEntry = (LRUCacheEntry) cacheValues[i];
				cacheMap.remove(cacheEntry.getKey());
			}
		}
	}

	/*
	 * 删除掉过期的数据
	 */
	public void refresh() {
		Object[] cacheValues = cacheMap.values().toArray();
		for (Object obj : cacheValues) {
			LRUCacheEntry entry = (LRUCacheEntry) obj;
			if (entry.isExpired(ttl)) {
				cacheMap.remove(entry.getKey());
			}
		}
	}

	boolean isRunning = true;

	// 缓存清洗线程休息时间。
	long sleepTime = 5 * 60 * 1000;

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * 缓存清洗线程。
	 */
	class PurgeJob implements Runnable {

		public void run() {
			while (isRunning) {
				refresh();
				int cacheSize = cacheMap.size();
				boolean needSleep = true;
				if (cacheSize > threshHold) {
					purgeCache();
					needSleep = false;
				}
				if (needSleep) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						if(logger.isErrorEnabled()){
							logger.error("缓存清洗线程执行错误", e);
						}
						//logger.error("缓存清洗线程执行错误", e);
					}
				}
			}
		}
	}

	/**
	 * 销毁线程。
	 */
	public void shutdown() {
		isRunning = false;
		super.shutdown();
	}

	@Override
	public boolean containsKey(Object key) {
		LRUCacheEntry cacheEntry = cacheMap.get(key);
		return cacheEntry != null && !cacheEntry.isExpired(ttl);
	}
	
	public Enumeration<Object> getKeys(){
		
		return cacheMap.keys();
	}

}
