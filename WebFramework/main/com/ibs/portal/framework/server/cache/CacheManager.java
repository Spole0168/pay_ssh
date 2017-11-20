package com.ibs.portal.framework.server.cache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.json.annotations.SMDMethod;
import org.omg.CORBA.portable.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibs.portal.framework.server.cache.support.CacheRefreshProperty;
import com.ibs.portal.framework.server.context.BeanHolder;
import com.ibs.portal.framework.util.StringUtils;
import com.opensymphony.xwork2.Action;

import net.sf.json.JSONArray;

/**
 * CHANGELOG
 *
 * 2010/12/28
 * 修改CacheManager以支持跨节点、跨集群、跨应用的cache同步；
 * 为了减轻网络压力，cache的同步仅仅是向节点发送删除某个缓存的消息；
 * 
 * 目前采用基于JSON-RPC的方式进行Cache同步；需要同步的节点的地址(cacheManagerPeers)需要在CacheManager初始化的时候
 * 定义好；同时，也必须定义好每个节点提供同步服务的方法名称（jsonRpcServiceMethodName）;
 * 
 * Struts的配置为：(在${WebFramework/struts-plugin.xml中定义)
	<package name="json-rpc" extends="json-default">
		<interceptors>
			<interceptor name="security" class="com.ibs.portal.framework.server.interceptor.SecurityInterceptor"/>
		</interceptors>
		<action name="refreshCache" class="com.ibs.portal.framework.server.cache.CacheManager" method="smd">
			<interceptor-ref name="json">
				<param name="enableSMD">true</param>
			</interceptor-ref>
			<interceptor-ref name="security"></interceptor-ref>
			<result type="json">
				<param name="enableSMD">true</param>
			</result>
		</action>
	</package>
 * 
 * 
 * CacheManager的初始化的bean定义为(在${Modules}/build/resources/${phase}/deployContext.xml中定义)：
	<bean id="cacheManager" class="com.ibs.portal.framework.server.cache.CacheManager">
		<property name="jsonRpcServiceMethodName" value="refreshSelf"></property>
		<property name="cacheManagerPeers">
			<list>
				<value>http://192.168.2.202:9094/mdm/refreshCache.action</value>
				<value>http://192.168.2.202:9095/mdm/refreshCache.action</value>
			</list>
		</property>
	</bean>
 * 每个节点的定义必须指定绝对URL，端口号也必须是WAS实例的监听端口；
 * 
 * 
 * 2011/05/13
 * 针对多server、多模块环境下，节点的膨胀带来的刷新缓存时间过长的问题，
 * 修改为针对每个缓存，配置对应的关注节点列表，比如：
 		<property name="endpoints">
			<map>
				<entry key="mdm" value="http://localhost:9080/mdm/refreshCache.action"></entry>
				<entry key="mot" value="http://localhost:9080/mot/refreshCache.action"></entry>
			</map>
		</property>
		<property name="cacheManagerPeers">
			<map>
				<entry key="_data_dict_cache_region">	<!-- 缓存名称 -->
					<list>	<!-- 关注此缓存的节点名称 -->
						<value>mdm</value>
						<value>mot</value>
					</list>
				</entry>
			</map>
		</property>
 *
 * 技术实现上需要注意的是，目前各模块使用CacheManager的地方都是使用单例模式CacheManager.getInstance()来取得CacheManager的引用；
 * 而不是使用spring的IOC注入的方式来取得CacheManager的引用；这会导致两者不是同一个对象！
 * 这里使用构造值注入的方式来保证两者是同一个对象；
 * 配置如下：
 * 
 * 	<bean id="cacheManager" class="com.ibs.portal.framework.server.cache.CacheManager"
		factory-method="createInstance">
		<constructor-arg>
			<ref local="cacheManageConfiguration"/>
		</constructor-arg>
	</bean>
 * 具体的做法请参考createInstance()的实现；
 * 
 * @author 
 *
 */
public class CacheManager {

	private static final Logger logger = LoggerFactory.getLogger(CacheManager.class) ;
	
	private CacheManageConfiguration configuration;
	
	static CacheManager singleton;
	
	private CacheManager(){}

	public static CacheManager getInstance() {
		if ( singleton == null ) {
			singleton = new CacheManager();
			logger.debug("cache manager: " + singleton);
		}
		return singleton;
	}

	private Map<String, ICache> storages = new ConcurrentHashMap<String, ICache>();

	public void initialize(CacheManageConfiguration configuration) {
		this.setConfiguration(configuration);
		
		logger.info("cache manager: " + this);
		if ( logger.isDebugEnabled() ) {
			logger.debug("Endpoints: ");
			Iterator it = configuration.getEndpoints().entrySet().iterator();
			while ( it.hasNext() ) {
				Map.Entry entry = (Map.Entry)it.next();
				logger.debug(entry.getKey() + ": " + entry.getValue());
			}
		}
		
		logger.info("Configured cache manager peers: ");
		Iterator iterator = configuration.getCacheManagerPeers().entrySet().iterator();
		while ( iterator.hasNext() ) {
			Map.Entry entry = (Map.Entry)iterator.next();
			
			String key = (String)entry.getKey();
			List peers = (List)entry.getValue();
			
			if ( CollectionUtils.isNotEmpty(peers) ) {
				for(int i=0; i<peers.size(); i++ ) {
					String endpoint = configuration.getEndpoints().get(peers.get(i));
					if ( StringUtils.isBlank(endpoint) ) {
						logger.error("Could not find endpoint for: " + endpoint);
					} else {
						logger.info("\t" + key + ": " + endpoint);
					}
				}
			}
		}
	}
	
	public static CacheManager createInstance(CacheManageConfiguration configuration) {
		CacheManager manager = getInstance();
		
		manager.initialize(configuration);
		
		return manager;
	}
	/**
	 * @return the storages
	 */
	public ICache getCache(String name) {
		return storages.get(name);
	}

	/**
	 * @param storages
	 *            the storages to set
	 */
	public void regCache(String name, ICache cache) {
		if(logger.isDebugEnabled()){
			logger.debug("注册Cache : "+name);
		}
		storages.put(name, cache);
	}
	
	public Map<String, ICache> getStorages() {
		return storages;
	}

	/**
	 * 刷新内存中某一条数据
	 * 目前刷新缓存仅仅是将缓存清除；
	 * @param name
	 * @param key
	 */
	public void refresh(String name, String key) {
		if ( StringUtils.isBlank(name) || StringUtils.isBlank(key) ) {
			logger.info("Empty name or Empty key!!");
			return;
		}
		
		List<String> keys = new ArrayList<String>();
		keys.add(key);
		
		this.refresh(name, keys);
	}
	
	public void refresh(String name, List<String> keys) {
		if ( CollectionUtils.isEmpty(keys) ) {
			logger.info("Empty keys!!");
			return;
		}
		logger.info("Begin refreshing cache: " + keys + " in [" + name + "]");
		CacheRefreshProperty property = new CacheRefreshProperty();
		
		property.setCacheKeys(keys);
		property.setCacheName(name);
		
		try {
			this.refreshSelf(property);
//			this.refreshOthers(property);
			this.refreshAsync(name, keys);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.info("Successfully refresh cache: " + keys + " in [" + name + "]");
	}
	
	public void refreshAsync(String name, List<String> keys) {
		DataSource dataSource = (DataSource) BeanHolder.getBean("dataSource");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(keys);
			
			conn = dataSource.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO pbl.T_PBL_CACHE_MESSAGE (MESSAGE_ID, CACHE_NAME, MESSAGE_STATUS, MESSAGE_TIME, CACHE_KEYS) values (?, ?, 0, sysdate, ?)");
			
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, StringUtils.getUUID());
			stmt.setString(2, name);
			stmt.setString(3, jsonArray.toString());
			
			stmt.execute();

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	/**
	 * 接收外部发送过来的刷新缓存的服务方法
	 * 方法名称必须和CacheManageConfiguration中的jsonRpcServiceMethodName一致！
	 * @param property
	 * @return
	 */
	@SMDMethod
	public CacheRefreshProperty refreshSelf(CacheRefreshProperty property) {
		logger.debug("In JSON-PRC service refreshSelf...");
		
		/**
		 * 这里不能直接使用storages变量，是因为作为RPC服务提供方，storages变量为空，
		 * 只能通过CacheManager.getInstance().getStorages()来取得目前缓存的所有项目；
		 */
		Map<String, ICache> thisStorage = CacheManager.getInstance().getStorages();
		
		logger.debug("Storage size: " + thisStorage.size());
		if ( logger.isTraceEnabled() )
			logger.trace("Storage: " + thisStorage);
		
		ICache cache = thisStorage.get(property.getCacheName());
		
		if ( cache == null ) {
			logger.warn("Cache not exists: " + property.getCacheName());
		}
		
		if ( cache != null ) {
			for(String key: property.getCacheKeys()) {
				if ( cache.containsKey(key) ) {
					logger.trace("Remove cache: " + key);
					cache.removeData(key);
					
					// If one cache is refreshed, set the refreshed flag to TRUE;
					property.setRefreshed(true);
				
					logger.trace("Successfully remove cache: " + key);
				} else {
					logger.warn("cache entry doesn't exists: " + key);
				}
			}
		} else {
			logger.warn("Couldn't find cache for: " + property.getCacheName());
		}
		
		return property;
	}
	
//	public void refreshOthers(CacheRefreshProperty property) {
//		logger.debug("Refresh cache in peers...");
//		logger.info("--------CacheManager instance id: " + this);
//		List peers = this.getConfiguration().getCacheManagerPeers().get(property.getCacheName());
//		if ( CollectionUtils.isNotEmpty(peers) ) {
//			Map endpoints = configuration.getEndpoints();
//			if ( logger.isDebugEnabled() ) {
//				logger.debug("~~~~~~~~~~~~~Endpoints: ");
//				Iterator it = endpoints.entrySet().iterator();
//				while ( it.hasNext() ) {
//					Map.Entry entry = (Map.Entry)it.next();
//					logger.debug(entry.getKey() + ": " + entry.getValue());
//				}
//			}
//			for(int i=0; i<peers.size(); i++) {
//				
//				String peerUrl = (String)endpoints.get(peers.get(i));
//				logger.trace("refresh peer: " + peerUrl);
//				this.syncCache(peerUrl, property);
//			}
//		} else {
//			logger.error("Could not find cache refresh peers for: " + property.getCacheName());
//		}
//	}
	
	public String smd() {
		return Action.SUCCESS;
	}
	
	/**
	 * 通过HttpClient调用同步Cache的服务；
	 */
//	private void syncCache(String url, CacheRefreshProperty property) {
//		if ( StringUtils.isBlank(url) ) {
//			logger.error("CacheManager peer's URL is empty, please check!!");
//			return;
//		}
//		
//		Gson gson = new Gson();
//		
//		JsonArray jsonArray = new JsonArray();
//		jsonArray.add(gson.toJsonTree(property));
//		
//		JsonObject jsonObject = new JsonObject();
//		jsonObject.addProperty("id", "1");
//		
//		// 设置远程服务端的RPC方法名称；
//		jsonObject.addProperty("method", configuration.getJsonRpcServiceMethodName());
//		jsonObject.add("params", jsonArray);
//		
//		HttpClient httpclient = new DefaultHttpClient();
//
//		HttpPost post = new HttpPost(url);
//
//		logger.debug("executing request " + post.getURI());
//		
//		logger.debug("JSON-RPC Request: " + jsonObject.toString());
//		
//		try {
//			StringEntity entity = new StringEntity(jsonObject.toString());
//			entity.setContentType("application/json-rpc");
//			entity.setContentEncoding("UTF-8");
//			
//			post.setEntity(entity);
//			
//			HttpParams params = new BasicHttpParams();
//			HttpConnectionParams.setConnectionTimeout(params, 
//					configuration.getConnectionTimeout());
//            HttpConnectionParams.setSoTimeout(params, 
//            		configuration.getSocketTimeout());
//            post.setParams(params);
//			
//			// Create a response handler
//            ResponseHandler<String> responseHandler = new BasicResponseHandler();
//            String responseBody = httpclient.execute(post, responseHandler);
//
//			//HttpResponse response = httpclient.execute(post);
//	
//			//HttpEntity responseEntity = response.getEntity();
//	
//			//logger.debug("HTTP Response status: " + response.getStatusLine());
//			
//			// 显示结果
//			//String responseString = EntityUtils.toString(responseEntity);
//			logger.debug("JSON-RPC response string: " + responseBody);
//			JsonParser parser = new JsonParser();
//			JsonObject responseObject = (JsonObject)parser.parse(responseBody);
//			
//			//JsonElement error = responseObject.hget("error");
//			if ( responseObject.has("error") ) {
//				
//				String error = responseObject.get("error").toString();
//				if ( StringUtils.isNotBlank(error) && !("null".equals(error)) ) {
//					logger.error("Failed to sync cache for: " + error);
//					logger.error("\tURL: " + url);
//					logger.error("\tCache name: " + property.getCacheName());
//					logger.error("\tCache keys: " + property.getCacheKeys());
//				}
//			}
//			
//			logger.debug("Sync cache result: " + responseObject.get("result"));
//			CacheRefreshProperty result = gson.fromJson(responseObject.get("result"), CacheRefreshProperty.class);
//			
//			logger.debug("Deserialized result: " + result.getCacheName() + ": " + result.getCacheKeys());
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("Exception happens when request sync cache: " + e.getMessage());
//		} finally {
//			// When HttpClient instance is no longer needed,
//			// shut down the connection manager to ensure
//			// immediate deallocation of all system resources
//			httpclient.getConnectionManager().shutdown();
//		}
//	}
	public CacheManageConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(CacheManageConfiguration configuration) {
		this.configuration = configuration;
	}
	
}
