/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.blacklist.biz;

import java.util.List;

import com.ibs.core.module.blacklist.domain.SysNamelist;
import com.ibs.core.module.blacklist.dto.BlackListToNoticDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_NAMELIST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ISysNamelistBiz {

	public IPage<SysNamelist> findSysNamelistByPage(QueryPage queryPage);

	public SysNamelist getSysNamelistById(String id);
	
	
	
	public void exportSysNamelist(ExportSetting exportSetting);
	/**
	 * 添加
	 * @param sysNamelist
	 * @param createuser
	 * @param dataDictlist
	 * @return
	 */
	public SysNamelist saveSysNamelist(SysNamelist sysNamelist,String createuser);

	/**
	 * 更新
	 * @param sysNamelist
	 * @param updateuser
	 * @param dataDictlist
	 * @return
	 */
	public SysNamelist updateSysNamelist(SysNamelist sysNamelist, String updateuser);
	/**
	 * 删除选中的列
	 * @param idStringArray  选中列的id的集合
	 */
	public void deleteBlackList(String[] idStringArray) ;
 
	/**
	 * 删除更新状态
	 * @param id
	 */
	public void delByValid(String id);
	/**
	 * 查询
	 * @param queryPage
	 * @return
	 */
	public IPage<SysNamelist> findSysNamelistByValidAndPage(QueryPage queryPage);
	
	/**
	 * 审批页面获取数据
	 * @param username
	 * @param id
	 * @return
	 */
	public  SysNamelist getReview(String username,String id);
	
	/**
	 * 审批
	 * @param sysnamelist
	 * @param reviewer
	 */
	public   void  doReview(SysNamelist  sysnamelist);
	
	/**
	 * ajax方式提交审批获取审批的信息
	 * @param sysnamelist
	 * @return
	 */
	public  boolean   doReviewByAjax(SysNamelist  sysnamelist);
	
	
	/**
	 * 校验在黑名单中是否存在此数据
	 * @param blacklisttonoticdto
	 */
	public  boolean valid(BlackListToNoticDto blacklisttonoticdto);
	
	/**
	 * 判断黑名单添加时是否重复
	 * @param nlType
	 * @param nlId
	 * @return
	 */
	public List<SysNamelist> isSame(String nlType,String nlId);
	
	/**
	 * 编辑时判断
	 * @param nlType
	 * @param nlId
	 * @param id
	 * @return
	 */
	public List<SysNamelist> isSame(String nlType,String nlId,String id);
}
