package com.ibs.common.module.frameworkimpl.redis.cache;

import org.apache.commons.lang.xwork.StringUtils;

import com.ibs.common.module.frameworkimpl.redis.BaseJedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

public abstract class BaseJedisCache<E> extends BaseJedis implements IJedisCache {

	protected String cacheName;
	protected int ttl = 60 * 60;	// default one hour

	protected abstract E deserialize(String str);
	
	protected abstract String serialize(E obj);
	
	public Long del(String... keys) {
		Long result = Long.valueOf(0);
		
		Jedis jedis = null;
		
		try {
			jedis = this.openJedis();
			result = jedis.del(keys);
		} catch (JedisException e) {
			logger.error(e.getMessage(), e);
		} catch (RuntimeException t) {
			throw t;
		} finally {
			this.closeJedis(jedis);
		}
		
		return result;
	}
	
	public String getCacheKey(String key) {
		
		String cacheKey = null;
		if (StringUtils.isEmpty(key))
			return cacheKey;
		
		return this.cacheName + "_" + key;
	}
	
	public String[] getCacheKeys(String... keys) {

		if (keys == null || keys.length == 0)
			return null;
		
		String[] cacheKeys = new String[keys.length];
		for (int i = 0; i < keys.length; ++i) {
			cacheKeys[i] = this.getCacheKey(keys[i]);
		}
		
		return cacheKeys;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

}
