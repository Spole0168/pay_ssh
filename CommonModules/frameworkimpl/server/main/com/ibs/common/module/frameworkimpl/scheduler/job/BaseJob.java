package com.ibs.common.module.frameworkimpl.scheduler.job;

import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ibs.common.module.frameworkimpl.scheduler.dao.IScheduleJobDao;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerLog;

public abstract class BaseJob extends QuartzJobBean{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static String JOB_RESULT_SUCCESS="SUCCESS";
	public static String JOB_RESULT_FAILURE="FAILURE";
	public static String JOB_RESULT_EXCEPTION="EXCEPTION";
	
	protected String id;
	//计划ID
	protected String schedulerId;
	//
	protected String jobName;
	//
	protected String jobDesc;
	
	protected String param1;
	
	protected String param2;
	
	protected IScheduleJobDao scheduleJobDao;

	private String hostName;
	private String hostAddress;
	
	protected Date startTime;
	
	public BaseJob() {
		try {
			this.hostName = InetAddress.getLocalHost().getHostName();
			this.hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public IScheduleJobDao getScheduleJobDao() {
		return scheduleJobDao;
	}

	public void setScheduleJobDao(IScheduleJobDao scheduleJobDao) {
		this.scheduleJobDao = scheduleJobDao;
	}

	protected abstract void executeInternal(JobExecutionContext context) throws JobExecutionException;
	
	protected void beforeExecute(){
		this.startTime = new Date(System.currentTimeMillis());
		
		SchedulerLog scheduleLog = new SchedulerLog();
		scheduleLog.setFlag(true);
		scheduleLog.setSchedulerId(schedulerId);
		scheduleLog.setJobName(jobName);
		scheduleLog.setJobDesc(jobDesc);
		scheduleLog.setHostName(hostName);
		scheduleLog.setHostAddress(hostAddress);
		scheduleLog.setPrevStartTime(startTime);

		String id = (String) scheduleJobDao.saveScheduleInfo(scheduleLog);
		
		this.id = id;
	}
	
	protected void afterExecute(String result){
		Date now = new Date(System.currentTimeMillis());
		
		SchedulerLog scheduleLog = new SchedulerLog();
		scheduleLog.setSchedulerId(schedulerId);
		scheduleLog.setJobName(jobName);
		scheduleLog.setJobDesc(jobDesc);
		scheduleLog.setHostName(hostName);
		scheduleLog.setHostAddress(hostAddress);
		
		scheduleLog.setId(id);
		scheduleLog.setPrevStartTime(startTime);
		scheduleLog.setPrevEndTime(now);
		scheduleLog.setFlag(false);
		scheduleLog.setPrevResult(result);
		
		scheduleJobDao.updateSchduleInfo(scheduleLog);
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}

}
