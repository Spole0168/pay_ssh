package com.ibs.common.module.frameworkimpl.scheduler.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.scheduler.dao.IScheduleJobDao;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerLog;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class ScheduleJobDaoImpl extends BaseEntityDao<SchedulerLog> implements IScheduleJobDao  {


	public IPage<SchedulerLog> findScheduleInfos(QueryPage queryPage) {
		//String hql = " from SchedulerLog where 1 = 1 ";
		//queryPage.setHqlString(hql);
		queryPage.setSort("prevStartTime", "desc");
		return super.findPageBy(queryPage);
	}

	public Serializable saveScheduleInfo(SchedulerLog scheduleInfo) {
		return super.save(scheduleInfo);
	}

	public void updateSchduleInfo(SchedulerLog scheduleInfo) {
		StringBuffer hql = new StringBuffer();
		hql.append("update SchedulerLog t set t.prevEndTime = ?, t.flag = ?, t.prevResult = ? ");
		hql.append("where t.id = ? and t.prevStartTime = ? ");
		
		List<Object> args = new ArrayList<Object>();
		args.add(scheduleInfo.getPrevEndTime());
		args.add(scheduleInfo.isFlag());
		args.add(scheduleInfo.getPrevResult());
		args.add(scheduleInfo.getId());
		args.add(scheduleInfo.getPrevStartTime());
		
		super.execute(hql.toString(), args);
	}

	public SchedulerLog findScheduleInfoById(String id) {
		return super.load(id);
	}

	public SchedulerLog findScheduleInfoByScheduleId(String schedulerId) {
		return super.loadBy("schedulerId", schedulerId);
	}
	
}
