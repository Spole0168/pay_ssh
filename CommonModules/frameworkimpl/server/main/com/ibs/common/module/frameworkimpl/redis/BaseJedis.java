package com.ibs.common.module.frameworkimpl.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.redis.helper.JsonBinder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

public abstract class BaseJedis implements IJedis {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected JsonBinder jsonBinder = new JsonBinder();

	protected JedisPool jedisPool;
	
	public Jedis openJedis() {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
		} catch (JedisException e) {
			jedisPool.returnBrokenResource(jedis);
			throw e;
		}
		
		return jedis;
	}
	
	public void closeJedis(Jedis jedis) {
		if (jedis != null)
			jedisPool.returnResource(jedis);
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}
	
}
