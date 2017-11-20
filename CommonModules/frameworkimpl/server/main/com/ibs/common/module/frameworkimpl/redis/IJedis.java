package com.ibs.common.module.frameworkimpl.redis;

import redis.clients.jedis.Jedis;


public interface IJedis {
	
	public Jedis openJedis();
	
	public void closeJedis(Jedis jedis);
}
