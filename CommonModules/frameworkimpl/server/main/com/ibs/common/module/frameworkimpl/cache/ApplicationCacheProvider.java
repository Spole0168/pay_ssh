package com.ibs.common.module.frameworkimpl.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.common.module.frameworkimpl.security.dao.IApplicationDao;
import com.ibs.common.module.frameworkimpl.security.domain.Application;
import com.ibs.portal.framework.server.cache.lru.LRUBaseCacheDataProvider;
import com.ibs.portal.framework.server.exception.BaseRuntimeException;
import com.ibs.portal.framework.server.exception.BizException;

/**
 * 用户缓存供给�? 当用户尝试从缓存中获取用户时，如果需要获取的用户不存在于缓存中，则使用供给器从数据库中加�?
 *
 * @author Qin Zhengming
 *
 */
public class ApplicationCacheProvider extends LRUBaseCacheDataProvider {

	//log
	protected final Log logger = LogFactory.getLog(ApplicationCacheProvider.class);
	
	private IApplicationDao applicationDao;

	public IApplicationDao getApplicationDao() {
		return applicationDao;
	}


	public void setApplicationDao(IApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}


	@SuppressWarnings("unchecked")
	public Object getData(Object key) {
		if (key == null) {
			logger
					.debug("Can not get Application from ApplicationCache without assign a application name");
			return null;
		}

		logger.info("从数据库加载获取系统(" + key + ")缓存数据-begin");
		try {
			String appName = (String) key;
			Application application = applicationDao.findByName(appName);
			if (null == application) {
				throw new BaseRuntimeException(
						"Can not find application["
								+ key.toString()
								+ "].");
			}
			
			return application;
		} catch (Exception e) {
			logger.error("Can not get Application from ApplicationCache. application_name["
					+ (String) key + "]", e);
			throw new BizException(e.getMessage());
		}
		finally {
			logger.info("从数据库加载获取系统缓存数据-end");
		}
	}
	

}
