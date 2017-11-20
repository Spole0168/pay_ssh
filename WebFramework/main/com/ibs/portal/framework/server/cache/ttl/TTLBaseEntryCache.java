package com.ibs.portal.framework.server.cache.ttl;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

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
public class TTLBaseEntryCache extends BaseCache {

	protected final Logger logger = LoggerFactory.getLogger(TTLBaseEntryCache.class);

	ConcurrentHashMap<Object, TTLCacheEntry> cacheMap;

	/**
	 * 使用指定的初始化容量与并发访问度构造缓存。
	 * 
	 * @param initialCapactiy
	 *            初始化容量
	 * @param concurrentLevel
	 *            并发访问度
	 */
	public TTLBaseEntryCache(int initialCapactiy, int concurrentLevel) {
		cacheMap = new ConcurrentHashMap<Object, TTLCacheEntry>(
				initialCapactiy, 0.75f, concurrentLevel);

		// 启动缓存清洗线程
		new Thread(new PurgeJob()).start();
	}

	/**
	 * 缓存预警值，当缓存大小达到该值时，清洗线程将使用TTL策略进行缓存清洗。
	 */
	int threshHold = 1000;

	/**
	 * 缓存大小最大限制。
	 */
	int maxSize = 2 * threshHold;

	/**
	 * 缓存生存周期
	 */
	long ttl = 30 * 60 * 1000;

	boolean isRunning = true;

	// 缓存清洗线程休息时间。
	long sleepTime = 5 * 60 * 1000;

	public int getThreshHold() {
		return threshHold;
	}

	public void setThreshHold(int threshHold) {
		this.threshHold = threshHold;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public Object getData(Object key) {
		// 若provider未加载完成，此方法无效
		if (cacheProvider == null)
			return null;

		// 判断缓存数据是否在TTL缓存中存在
		TTLCacheEntry cacheEntry = cacheMap.get(key);
		if (cacheEntry != null && !cacheEntry.isExpired(ttl)) {
			return cacheEntry.getData();
		} else {
			if (cacheMap.size() >= maxSize)
				purgeCache();
			Object data = cacheProvider.getData(key);
			cacheEntry = new TTLCacheEntry();
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
	 * 根据TTL策略进行缓存清洗
	 */
	synchronized void purgeCache() {
		int cacheSize = cacheMap.size();
		if (cacheSize > threshHold) {
			Object[] cacheValues = cacheMap.values().toArray();
			Arrays.sort(cacheValues);
			int purgeCount = cacheSize - threshHold;
			for (int i = 0; i < purgeCount; i++) {
				TTLCacheEntry cacheEntry = (TTLCacheEntry) cacheValues[i];
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
			TTLCacheEntry entry = (TTLCacheEntry) obj;
			if (entry.isExpired(ttl)) {
				cacheMap.remove(entry.getKey());
			}
		}
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
		TTLCacheEntry cacheEntry = cacheMap.get(key);
		return cacheEntry != null && !cacheEntry.isExpired(ttl);
	}
}
