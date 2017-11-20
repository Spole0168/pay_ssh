package com.ibs.common.module.frameworkimpl.scheduler.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ibs.common.module.frameworkimpl.scheduler.dao.ISchedulerSettingDao;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnValue;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class SchedulerSettingDaoImpl extends BaseEntityDao<SchedulerSetting> implements ISchedulerSettingDao{

	public SchedulerSetting findScheduleSettingById(String id) {
		return super.load(id);
	}

	public List<SchedulerSetting> findSchedulerSettingByName(String name) {
		String hql = new String("from SchedulerSetting where name = ?");
		
		List<Object> args = new ArrayList<Object>();
		args.add(name);
		return super.find(hql, args);
	}

	public Serializable saveScheduleSetting(SchedulerSetting schedulerSetting) {
		return super.save(schedulerSetting);
	}

	public void updateScheduleSetting(SchedulerSetting schedulerSetting) {
		super.saveOrUpdate(schedulerSetting);
		super.flush();
	 
	}

	public List<SchedulerSetting> findSchedulerSettingBySchedulerWar(
			String schedulerWar) {
		logger.trace("entering dao...");
		 String hql = " from SchedulerSetting where schedulerWar = ? ";
		 List<Object> queryArgs = new ArrayList<Object>();
		 queryArgs.add(schedulerWar);
		 List<SchedulerSetting> result = super.find(hql,queryArgs);
		return result;
	}

	public SchedulerSetting findSchedulerSettingBySchedulerId(String schedulerId) {
		logger.trace("entering dao...");
		 String hql = " from SchedulerSetting where schedulerId = ? ";
		 List<Object> queryArgs = new ArrayList<Object>();
		 queryArgs.add(schedulerId);
		 List<SchedulerSetting> list = super.find(hql, queryArgs);
		 if (!list.isEmpty()) {
			 return list.iterator().next();
		 }
		return null;
	}

	public IPage<SchedulerSetting> findSchedulerByPage(QueryPage queryPage) {
		StringBuilder hql = new StringBuilder(" from SchedulerSetting t where 1 = 1 ");
		List<Object> queryList = new ArrayList<Object>();
		for (ColumnValue cv : queryPage.getQueryConditionList()) {
			if (cv.isNotNullValue()) {
 
				if ("id".equals(cv.getColumnName())) {
					hql.append(" and t.id  = ? ");
				}

				if ("name".equals(cv.getColumnName())) {
					hql.append(" and t.name like ? ");
				}
		
				if ("description".equals(cv.getColumnName())) {
					hql.append(" and t.description like ? ");
				}

				if ("param1".equals(cv.getColumnName())) {
					hql.append(" and t.param1 =  ? ");
				}

				if ("param2".equals(cv.getColumnName())) {
					hql.append(" and t.param2 = ? ");
				}
				
				if ("schedulerWar".equals(cv.getColumnName())) {
					hql.append(" and t.schedulerWar like ? ");
					 
				}
				queryList.add(cv.getValue());
			}
		}
		hql.append(" order by t.schedulerId");
		queryPage.setHqlString(hql.toString());
		return this.findPage(hql.toString(),queryList,queryPage.getPageSize(),queryPage.getPageIndex());
	}

	public void saveOrUpdate(SchedulerSetting schSet){
		super.saveOrUpdate(schSet);
	}

	public List<SchedulerSetting> list() {
		logger.trace("entering dao...");
		StringBuffer hql = new StringBuffer();
		return super.find(hql.toString());
	}

}
