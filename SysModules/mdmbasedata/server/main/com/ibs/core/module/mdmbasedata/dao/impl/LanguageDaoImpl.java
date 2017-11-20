package com.ibs.core.module.mdmbasedata.dao.impl;

import java.util.List;

import com.ibs.core.module.mdmbasedata.dao.ILanguageDao;
import com.ibs.core.module.mdmbasedata.domain.Language;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

public class LanguageDaoImpl extends BaseEntityDao<Language> implements ILanguageDao {

	public List<Language> getLanguageList() {
		StringBuffer hql = new StringBuffer();		 
		hql.append(" from Language order by displayOrder  ");
		return super.find(hql.toString());
		 
	}

	public Language load(String id) {
		return super.load(id);
	}

}
