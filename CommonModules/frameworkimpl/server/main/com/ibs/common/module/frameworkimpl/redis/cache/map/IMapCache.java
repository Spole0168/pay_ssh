package com.ibs.common.module.frameworkimpl.redis.cache.map;

import java.util.Map;

import com.ibs.common.module.frameworkimpl.redis.cache.IJedisCache;


public interface IMapCache<E> extends IJedisCache {

	public abstract Map<String, E> get(String key);
	
	public abstract void set(String key, Map<String, E> obj);
	
	public abstract void set(String key, String field, E e);
}
