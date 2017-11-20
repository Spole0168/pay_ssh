package com.ibs.core.module.mdmbasedata.cache;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.dao.IDataDictDao;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.portal.framework.server.cache.lru.LRUBaseCacheDataProvider;
import com.ibs.portal.framework.server.cache.ttl.TTLBaseCacheDataProvider;
import com.ibs.portal.framework.server.exception.BaseRuntimeException;

/**
 * 数据字典缓存供给器，当尝试从缓存中获取数据字典信息时，如果需要获取的数据字典信息不存在于缓存中，则使用供给器从数据库中加载
 * 
 * 缓存的key为'type#locale'
 *
 * @author huolang
 *
 */
public class DataDictCacheProvider extends LRUBaseCacheDataProvider {
//public class DataDictCacheProvider extends TTLBaseCacheDataProvider {

	//log
	protected final Log logger = LogFactory.getLog(DataDictCacheProvider.class);
	
	private IDataDictDao dataDictDao;
	
	public IDataDictDao getDataDictDao() {
		return dataDictDao;
	}

	public void setDataDictDao(IDataDictDao dataDictDao) {
		this.dataDictDao = dataDictDao;
	}

	/**
	 * key 的组成格式是'type#locale'
	 */
	public Object getData(Object key) {
		if (key == null || "".equals(String.valueOf(key).trim())) {
			logger
					.debug("Can not get DataDict from DataDictRule without assign parametor");
			return null;
		}

		logger.info("从数据库加载获取数据字典(" + key + ")缓存数据-begin");
		try {
			String [] keys = String.valueOf(key).trim().split(Constants.CACHE_KEY_SEPERATOR);
			if(keys.length<2){
				throw new BaseRuntimeException(
						"find DataDict with parameter less than 2 ");
			}
			
			List<DataDict> dictList = dataDictDao.list(keys[0], keys[1]);
			if (null == dictList) {
				throw new BaseRuntimeException(
						"Can not find Data dict["
								+ key.toString()
								+ "].");
			}
			
			return dictList;
		} catch (Exception e) {
			logger.error("Can not get Data dict List from DataDictCache. parameters are ["
					+ (String) key + "]", e);
			return null;
		}
		finally {
			logger.info("从数据库加载获取系统缓存数据-end");
		}
	}
	

}
