package com.ibs.portal.framework.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManageConfiguration {
	/**
	 * 所有可能需要接收缓存刷新的节点列表；
	 * key为名称，value为节点的接收JSON-RPC刷新缓存消息的访问地址；
	 * 比如：http://192.168.2.202:9094/mdm/refreshCache.action；
	 * 
	 */
	private Map<String, String> endpoints = new HashMap();
	
	/**
	 * 针对不同缓存的关注列表，
	 * 其中，key为缓存的名称，value为节点名称的列表；
	 * 通过节点名称，在endpoints中可以找到对应的url；
	 */
	private Map<String, List<String>> cacheManagerPeers;
	
	/**
	 * http请求超时时间，
	 * 不能定义的过大，避免在服务不能用的时候阻塞用户操作；
	 * 单位为毫秒；
	 */
	private int connectionTimeout = 1000;
	
	/**
	 * socket超时时间；
	 * 不能定义的过大；避免在服务不能用的时候阻塞用户操作；
	 * 单位为毫秒；
	 */
	private int socketTimeout = 1000;
	
	/**
	 * JSON-RPC的服务方法名称，发出JSON-RPC调用时需要指定的接收端的服务方法名称；
	 */
	private String jsonRpcServiceMethodName;

	public Map<String, String> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(Map<String, String> endpoints) {
		this.endpoints = endpoints;
	}

	public Map<String, List<String>> getCacheManagerPeers() {
		return cacheManagerPeers;
	}

	public void setCacheManagerPeers(Map<String, List<String>> cacheManagerPeers) {
		this.cacheManagerPeers = cacheManagerPeers;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public String getJsonRpcServiceMethodName() {
		return jsonRpcServiceMethodName;
	}

	public void setJsonRpcServiceMethodName(String jsonRpcServiceMethodName) {
		this.jsonRpcServiceMethodName = jsonRpcServiceMethodName;
	}
}
