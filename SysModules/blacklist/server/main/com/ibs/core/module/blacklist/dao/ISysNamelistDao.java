/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.blacklist.dao;

import java.util.List;

import com.ibs.core.module.blacklist.domain.SysNamelist;
import com.ibs.core.module.blacklist.dto.BlackListToNoticDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_NAMELIST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysNamelistDao {

	public IPage<SysNamelist> findSysNamelistByPage(QueryPage queryPage);
	
	public void saveOrUpdate(SysNamelist sysNamelist);

	public SysNamelist load(String id);
	/**
	 * 删除数据
	 * @param id
	 */
	public void delete(String  id);
	/**
	 * 查询
	 * @param queryPage
	 * @return
	 */
	public IPage<SysNamelist> findSysNamelistByValidAndPage(QueryPage queryPage);
 
	
	public List<SysNamelist> findByBlackList(BlackListToNoticDto blackListToNoticDto);
	
	/**
	 * 根据类型和值判断是否存在
	 * @param nlType
	 * @param nlId
	 * @return
	 */
	public List<SysNamelist> findByNlTypeAndNlId(String nlType,String nlId);
}
