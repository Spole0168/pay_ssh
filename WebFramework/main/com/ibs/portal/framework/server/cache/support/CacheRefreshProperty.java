package com.ibs.portal.framework.server.cache.support;

import java.util.List;

public class CacheRefreshProperty {
	private String cacheName;
	private List<String> cacheKeys;
	private boolean refreshed = false;
	
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	public List<String> getCacheKeys() {
		return cacheKeys;
	}
	public void setCacheKeys(List<String> cacheKeys) {
		this.cacheKeys = cacheKeys;
	}
	public boolean isRefreshed() {
		return refreshed;
	}
	public void setRefreshed(boolean refreshed) {
		this.refreshed = refreshed;
	}
}
