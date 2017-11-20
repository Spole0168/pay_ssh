package com.ibs.common.module.frameworkimpl.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.dao.IMacDao;
import com.ibs.common.module.frameworkimpl.security.domain.Mac;
import com.ibs.common.module.frameworkimpl.security.dto.MacInfoDto;
import com.ibs.portal.framework.server.cache.lru.LRUBaseCacheDataProvider;

/**
 * menu name cache provider
 * @author 
 * $Id: MacCacheProvider.java 44367 2011-09-01 15:58:58Z  $
 */
public class MacCacheProvider extends LRUBaseCacheDataProvider {

	private IMacDao macDao;

	public void setMacDao(IMacDao macDao) {
		this.macDao = macDao;
	}

	/* (non-Javadoc)
	 * return Map<Mac, List<String>>  返回值key是Mac，value是List<userCode>
	 * @see com.ibs.portal.framework.server.cache.ICacheDataProvider#getData(java.lang.Object)
	 */
	public Object getData(Object key) {
		if (key == null) 
			return null;
		
		logger.trace("loading " + key + " from db begin...");
		
		List<MacInfoDto> list = macDao.getMacInfosByMenuId((String)key);
		
		Map<Mac, List<String>> map = new HashMap<Mac, List<String>>();
		
		for (MacInfoDto dto : list) {
			Mac mac = new Mac(dto.getMac(), dto.getCpuId());
			List<String> users = map.get(mac);
			if (users == null) {
				users = new ArrayList<String>();
				users.add(dto.getUserCode());
				map.put(mac, users);
			} else {
				users.add(dto.getUserCode());
			}
		}
		
		return map;
	}

}
