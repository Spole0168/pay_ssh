package com.ibs.portal.framework.server.cache;

/**
 * 缓存管理器
 * 
 * @author luoyue
 * 
 */
public interface ICacheManager {
	ICache getCache(String name);

}
