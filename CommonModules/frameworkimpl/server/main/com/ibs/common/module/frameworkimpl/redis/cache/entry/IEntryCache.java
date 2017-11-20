package com.ibs.common.module.frameworkimpl.redis.cache.entry;

import com.ibs.common.module.frameworkimpl.redis.cache.IJedisCache;


public interface IEntryCache<E> extends IJedisCache {

	public abstract E get(String key);
	
}
