package com.ibs.common.module.frameworkimpl.scheduler.dao;

import java.io.Serializable;

import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerLog;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IScheduleJobDao {

	/**
	 * 查询
	 * @param queryPage
	 * @return
	 */
	public IPage<SchedulerLog> findScheduleInfos(QueryPage queryPage) ;
	/**
	 * 添加
	 * @param scheduleInfo
	 * @return
	 */
	public Serializable saveScheduleInfo(SchedulerLog scheduleInfo);
	/**
	 * 更新
	 * @param scheduleInfo
	 */
	public void updateSchduleInfo(SchedulerLog scheduleInfo);
	
	/**
	 * 根据自定义的ID查询单条定时任务信息
	 */
	public SchedulerLog findScheduleInfoByScheduleId(String scheduleid);
	/**
	 * 根据定时任务ID查询
	 * @param id
	 * @return
	 */
	public SchedulerLog findScheduleInfoById(String id);
}
