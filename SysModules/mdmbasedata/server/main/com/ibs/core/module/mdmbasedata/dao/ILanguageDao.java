package com.ibs.core.module.mdmbasedata.dao;

import java.util.List;

import com.ibs.core.module.mdmbasedata.domain.Language;

public interface ILanguageDao {
	/**
	 * @param parm 系统支持语言的ID
	 * @return Language
	 */
	public Language load(String id);
	
	/**
	 *  返回所有系统支持语言
	 * @return List<Language>
	 */
	public List<Language>getLanguageList();
}
