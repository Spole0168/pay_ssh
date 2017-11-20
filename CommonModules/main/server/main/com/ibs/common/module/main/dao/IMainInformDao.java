package com.ibs.common.module.main.dao;

import java.util.List;

import com.ibs.common.module.main.domain.MainInform;


/**
 * 首页提醒DAO接口
 * @author 
 * $Id: IMainInformDao.java 19438 2010-12-04 05:33:31Z  $
 */
public interface IMainInformDao {

	public List<MainInform> findValidMainInform();
	
}
