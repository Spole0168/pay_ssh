package com.ibs.core.module.mdmbasedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.dao.IDataDictDao;
import com.ibs.core.module.mdmbasedata.dao.ILanguageDao;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.domain.Language;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.cache.ICache;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.exception.BaseRuntimeException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.StringUtils;

public class DataDictServiceImpl extends BaseService implements IDataDictService {

	private IDataDictDao dataDictDao;
	private ILanguageDao languageDao;

	@SuppressWarnings("unchecked")
	public List<DataDict> list(String type) {
		Locale local = Locale.getDefault();
		try{
			UserContext uc = UserContext.getUserContext();
			if (uc != null) {
				local = uc.getLocale();
				if (local == null) {
					local = Locale.getDefault();
				}
			}
		} catch (Throwable e) {
			logger.warn("无法获取Context信息");
		}
		
		String _type = (null == type) ? null : type.trim();
		String cacheKey = _type + Constants.CACHE_KEY_SEPERATOR
				+ local.toString();
		logger.trace(cacheKey);
		return (List<DataDict>) getDataDictCached(cacheKey);

	}

	@SuppressWarnings("unchecked")
	public List<DataDict> list(String type, String language) {

		String _type = (null == type) ? null : type.trim();
		String cacheKey = _type + Constants.CACHE_KEY_SEPERATOR + language;
		return (List<DataDict>) getDataDictCached(cacheKey);

	}

	public List<OptionObjectPair> listOptions(String type) {
		List<DataDict> list = list(type);

		if (null != list && !list.isEmpty()) {
			List<OptionObjectPair> options = new ArrayList<OptionObjectPair>(
					list.size());
			for (DataDict dataDict : list) {
				options.add(new OptionObjectPair(dataDict.getCode(), dataDict
						.getName(), dataDict.getId()));
			}
			return options;
		} else {
			return new ArrayList<OptionObjectPair>();

		}

	}

	public DataDict load(String id) {

		return dataDictDao.load(id);
	}

	public IDataDictDao getDataDictDao() {
		return dataDictDao;
	}

	public void setDataDictDao(IDataDictDao dataDictDao) {
		this.dataDictDao = dataDictDao;
	}

	public List<OptionObjectPair> listOptions(String type, String defaultValue) {
		OptionObjectPair bean = new OptionObjectPair();
		bean.setKey("");
		bean.setValue(defaultValue);
		List<OptionObjectPair> list = listOptions(type);
		list.add(0, bean);
		return list;

	}

	public List<OptionObjectPair> findSupportLangeage() {
		List<Language> list = languageDao.getLanguageList();
		if (null != list && !list.isEmpty()) {
			List<OptionObjectPair> options = new ArrayList<OptionObjectPair>(
					list.size());
			for (Language lan : list) {
				options.add(new OptionObjectPair(lan.getCode(), lan.getName(),
						lan.getId()));
			}
			return options;
		} else
			return new ArrayList<OptionObjectPair>();

	}

	public ILanguageDao getLanguageDao() {
		return languageDao;
	}

	public void setLanguageDao(ILanguageDao languageDao) {
		this.languageDao = languageDao;
	}

	public String getCodeName(String type, String code) {
		// UserContext uc = UserContext.getUserContext();
		// Locale local = uc.getLocale();
		// if (local == null) {
		// local = Locale.getDefault();
		// }
		List<DataDict> dataList = list(type);
		if (null == dataList || dataList.size() <= 0 || null == code
				|| "".equals(code.trim())) {
			return null;
		}
		String _code = code.trim();
		for (DataDict dd : dataList) {
			if (_code.equalsIgnoreCase(dd.getCode())) {
				return dd.getName();
			}
		}
		return "";
		// return dataDictDao.getCodeName(type, code, local.toString());
	}

	public Map<String, DataDict> listMap(String type) {
		Map<String, DataDict> map = new HashMap<String, DataDict>();
		List<DataDict> alist = list(type);
		if (alist != null) {
			for (int i = 0; i < alist.size(); i++) {
				DataDict o = alist.get(i);
				map.put(o.getCode(), o);

			}
		}

		return map;
	}

	/**
	 * 获取数据字典缓存
	 * 
	 * @return
	 */
	public ICache getDataDictCache() {
		return CacheManager.getInstance().getCache(Constants.DATA_DICT_CACHE);
	}

	/**
	 * 从Cache中获取值
	 * 
	 * @param key
	 *            = type#locale
	 * @return
	 */
	public Object getDataDictCached(String key) {
		if(StringUtils.isEmpty(key)){
			return null;
		}
		ICache ruleCache = getDataDictCache();
		if(null != ruleCache){
			return ruleCache.getData(key);
		}else{
			logger.warn("can not get DataDict Cache from CacheManager");
			
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
		}
	}

	public List<OptionObjectPair> listOptionsByLanguage(String type,
			String language) {
		if (language == null) {
			language = Locale.getDefault().toString();
		}
		List<DataDict> list = list(type, language);

		if (null != list && !list.isEmpty()) {
			List<OptionObjectPair> options = new ArrayList<OptionObjectPair>(
					list.size());
			for (DataDict dataDict : list) {
				options.add(new OptionObjectPair(dataDict.getCode(), dataDict
						.getName(), dataDict.getId()));
			}
			return options;
		} else {
			return new ArrayList<OptionObjectPair>();

		}

	}

	public DataDict getObject(String type, String code, String language) {

		if (language == null) {
			language = Locale.getDefault().toString();
		}
		List<DataDict> alist = list(type, language);
		if (null == alist || alist.size() <= 0 || null == code
				|| "".equals(code.trim())) {
			return null;
		}

		for (DataDict dd : alist) {
			if (code.equalsIgnoreCase(dd.getCode())) {
				return dd;
			}
		}

		return null;

	}

 

}
