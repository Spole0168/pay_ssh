package com.ibs.core.module.mdmbasedata.service;

import java.util.List;

import com.ibs.core.module.mdmbasedata.domain.Language;



/**
 * 系统支持语言对外服务类接口
 * @author 
 *
 */
public interface ILanguageService {
	 
	/**
	 * @param parm 系统支持语言ID
	 * @return Language
	 */
	public Language load(String id);
	
	/**
	 *  返回所有系统支持语言
	 * @return List<Language>
	 */
	public List<Language>getLanguageList();
	
}
