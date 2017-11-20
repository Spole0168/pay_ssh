package com.ibs.core.module.mdmbasedata.service.impl;

import java.util.List;

import com.ibs.core.module.mdmbasedata.dao.ILanguageDao;
import com.ibs.core.module.mdmbasedata.domain.Language;
import com.ibs.core.module.mdmbasedata.service.ILanguageService;
import com.ibs.portal.framework.server.service.BaseService;

public class LanguageServiceImpl extends BaseService implements ILanguageService {
	
	private ILanguageDao languageDao;

	public List<Language> getLanguageList() {
		return languageDao.getLanguageList();
	}

	public Language load(String id) {
		
		return languageDao.load(id);
	}

	public ILanguageDao getLanguageDao() {
		return languageDao;
	}

	public void setLanguageDao(ILanguageDao languageDao) {
		this.languageDao = languageDao;
	}

}
