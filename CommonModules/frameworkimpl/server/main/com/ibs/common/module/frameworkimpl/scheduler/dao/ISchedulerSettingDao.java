package com.ibs.common.module.frameworkimpl.scheduler.dao;

import java.io.Serializable;
import java.util.List;

import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
 

public interface ISchedulerSettingDao {

	/**
	 * 添加
	 * @param scheduleInfo
	 * @return
	 */
	public Serializable saveScheduleSetting(SchedulerSetting schedulerSetting);
	/**
	 * 更新
	 * @param scheduleInfo
	 */
	public void updateScheduleSetting(SchedulerSetting schedulerSetting);
	
	/**
	 * 根据名字查询定时任务设置信息
	 */
	public List<SchedulerSetting> findSchedulerSettingByName(String name);
	
	/**
	 * 根据定时任务ID查询
	 * @param id
	 * @return
	 */
	public SchedulerSetting findScheduleSettingById(String id);
	
	
	
	/**
	 * 根据schedulerWar名称查询SchedulerSetting列表.
	 * @param schdulerWar 
	 * @return 返回SchedulerSetting列表.
	 */
	public List<SchedulerSetting> findSchedulerSettingBySchedulerWar(String schedulerWar);
	/**
	 * 根据schedulerId 查询SchedulerSetting对象.
	 * @param schedulerId
	 * @return SchedulerSetting对象.
	 */
	public SchedulerSetting findSchedulerSettingBySchedulerId(String schedulerId);
	
	public IPage<SchedulerSetting> findSchedulerByPage(QueryPage queryPage);
	
	public void saveOrUpdate(SchedulerSetting schSet);
	
	public List<SchedulerSetting> list();
	
}
