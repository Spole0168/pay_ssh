package com.ibs.common.module.frameworkimpl.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.common.module.frameworkimpl.security.dao.IMenuDao;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.portal.framework.server.cache.lru.LRUBaseCacheDataProvider;
import com.ibs.portal.framework.server.exception.BizException;

/**
 * 用户缓存供给�? 当用户尝试从缓存中获取用户时，如果需要获取的用户不存在于缓存中，则使用供给器从数据库总加�?
 * 
 * @author Qin Zhengming
 * 
 */
public class UrlResourceCacheProvider extends LRUBaseCacheDataProvider {

	protected final Log logger = LogFactory
			.getLog(UrlResourceCacheProvider.class);

	private IMenuDao menuDao;

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@SuppressWarnings("unchecked")
	public Object getData(Object key) {
		if (key == null) {
			//logger.trace("Can not get Resource from UserCache without assign a resourceId");
			return null;
		}

//		logger.trace("从数据库加载获取资源(" + key + ")缓存数据-begin");
		try {
			String resourceValue = (String) key;
			Menu menu = menuDao.loadByTypeAndValue(new String[]{Menu.MENU_TYPE_URL, Menu.MENU_TYPE_MENU},
					resourceValue);
//			if (null == menu) {
//				logger.trace("Can not find resource["
//								+ key.toString()
//								+ "], if this record exists in database, please check if there a resource exists with its resource value.");
//			}

			return menu;
		} catch (Exception e) {
//			logger.debug("Can not get Url Resource from ResourceCache. resource_value["
//								+ (String) key + "]");
			throw new BizException(e.getMessage());
		} finally {
//			logger.trace("从数据库加载获取资源缓存数据-end");
		}

	}

}
