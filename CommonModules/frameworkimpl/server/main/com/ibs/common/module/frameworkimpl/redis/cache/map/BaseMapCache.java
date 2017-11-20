package com.ibs.common.module.frameworkimpl.redis.cache.map;

import java.util.Map;

import com.ibs.common.module.frameworkimpl.redis.cache.BaseJedisCache;

public abstract class BaseMapCache<E> extends BaseJedisCache<E> implements IMapCache<E> {

	protected abstract Map<String, E> getData(String key);
	
}
