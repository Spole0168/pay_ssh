package com.ibs.common.module.frameworkimpl.redis.cache.entry;

import com.ibs.common.module.frameworkimpl.redis.cache.BaseJedisCache;

public abstract class BaseEntryCache<E> extends BaseJedisCache<E> implements IEntryCache<E> {

	protected abstract E getData(String key);
	
}
