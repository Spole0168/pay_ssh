package com.ibs.common.module.frameworkimpl.redis.cache.map;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisException;


public abstract class TtlMapCache<E> extends BaseMapCache<E> implements IMapCache<E> {
	
	public Map<String, E> get(String key) {
		
		Map<String, E> obj = null;

		if (StringUtils.isEmpty(key))
			return obj;

		Jedis jedis = null;
		
		try {
			jedis = this.openJedis();
			
			// get cache data
			String mapKey = this.getCacheKey(key);
			Map<String, String> value = jedis.hgetAll(mapKey);
			StringBuffer sb = new StringBuffer();
			
			if (value == null || value.size() == 0) {	// cache not exists
				sb.append("key:[" + mapKey + "] ,get data from database...");
				
				obj = this.getData(key);
				value = new HashMap<String, String>();
				for (Map.Entry<String, E> entry : obj.entrySet()) {
					String entryValue = this.serialize(entry.getValue());
					value.put(entry.getKey(), entryValue);
					sb.append("\nfield key: " + entry.getKey() + " , value: " + entryValue);
				}
				
				Pipeline p = jedis.pipelined();
				p.hmset(mapKey, value);
				p.expire(mapKey, this.getTtl());
				p.syncAndReturnAll();
			} else {
				sb.append("key:[" + mapKey + "] ,get data from cache...");
				
				obj = new HashMap<String, E>();
				for (Map.Entry<String, String> entry : value.entrySet()) {
					obj.put(entry.getKey(), this.deserialize(entry.getValue()));
					sb.append("\nfield key: " + entry.getKey() + " , value: " + entry.getValue());
				}
			}
			
			logger.trace(sb.toString());
		} catch (JedisException e) {
			logger.error(e.getMessage(), e);
			obj = this.getData(key);
		} catch (RuntimeException t) {
			throw t;
		} finally {
			this.closeJedis(jedis);
		}

		return obj;
	}

	public void set(String key, Map<String, E> obj) {
		
		if (StringUtils.isEmpty(key) || obj == null || obj.size() == 0)
			return;

		Jedis jedis = null;
		
		try {
			jedis = this.openJedis();
			String mapKey = this.getCacheKey(key);
			Map<String, String> value = new HashMap<String, String>();
			
			for (Map.Entry<String, E> entry : obj.entrySet()) {
				value.put(entry.getKey(), this.serialize(entry.getValue()));
			}
			
			Pipeline p = jedis.pipelined();
			p.hmset(mapKey, value);
			p.expire(mapKey, this.getTtl());
			p.syncAndReturnAll();
		} catch (JedisException e) {
			logger.error(e.getMessage(), e);
		} catch (RuntimeException t) {
			throw t;
		} finally {
			this.closeJedis(jedis);
		}
		
	}
	
	public void set(String key, String field, E obj) {

		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field) ||  obj == null)
			return;

		Jedis jedis = null;
		
		try {
			jedis = this.openJedis();
			String mapKey = this.getCacheKey(key);

			jedis.hset(mapKey, field, this.serialize(obj));
			
		} catch (JedisException e) {
			logger.error(e.getMessage(), e);
		} catch (RuntimeException t) {
			throw t;
		} finally {
			this.closeJedis(jedis);
		}
		
	}
}
