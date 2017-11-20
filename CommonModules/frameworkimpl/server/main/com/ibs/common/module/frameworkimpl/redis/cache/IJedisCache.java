package com.ibs.common.module.frameworkimpl.redis.cache;

import com.ibs.common.module.frameworkimpl.redis.IJedis;


public interface IJedisCache extends IJedis {
	
	public Long del(String... keys);
	
	public String getCacheKey(String key);
	
	public String[] getCacheKeys(String... keys);
}
