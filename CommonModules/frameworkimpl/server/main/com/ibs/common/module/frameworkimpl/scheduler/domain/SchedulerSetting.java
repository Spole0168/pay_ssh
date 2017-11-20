/**
 * 定时任务信息
 */
package com.ibs.common.module.frameworkimpl.scheduler.domain;


import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;
import com.ibs.portal.framework.util.StringUtils;

public class SchedulerSetting extends BaseEntity{

	private static final long serialVersionUID = 1322454477015596134L;
	
	public static String CRON_TYPE = "1";
	public static String SIMPLE_TYPE = "2";
	
	//id
	private String id;
	
	
	//id
	private String schedulerId;
	
	//应用war名称
	private String schedulerWar;
	
	//任务名称
	private String name;
	
	//任务描述
	private String description;
	
	//类型：Cron/Simple
	private String type;
	
	//是否启用
	private boolean enable;
	
	//Cron Express
	private String cronExpress;
	
	//开始时间
	private Date simpleStartTime;
	
	//结束时间
	private Date simpleEndTime;
	
	//重复次数
	private Integer simpleRepeatCount;
	
	//重复间隔
	private Integer simpleRepeatIntervel;
	
	//参数1
	private String param1;
	
	//参数2
	private String param2;
	
	//参考值
	private String refValue;
	
	//备注
	private String remark;
	
	//当前任务所处的状态.
	private  String currentStatus;
	
	 
	 
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getCronExpress() {
		return cronExpress;
	}

	public void setCronExpress(String cronExpress) {
		this.cronExpress = cronExpress;
	}

	public Date getSimpleStartTime() {
		return simpleStartTime;
	}

	public void setSimpleStartTime(Date simpleStartTime) {
		this.simpleStartTime = simpleStartTime;
	}

	public Date getSimpleEndTime() {
		return simpleEndTime;
	}

	public void setSimpleEndTime(Date simpleEndTime) {
		this.simpleEndTime = simpleEndTime;
	}

	public Integer getSimpleRepeatCount() {
		return simpleRepeatCount;
	}

	public void setSimpleRepeatCount(Integer simpleRepeatCount) {
		this.simpleRepeatCount = simpleRepeatCount;
	}

	public Integer getSimpleRepeatIntervel() {
		return simpleRepeatIntervel;
	}

	public void setSimpleRepeatIntervel(Integer simpleRepeatIntervel) {
		this.simpleRepeatIntervel = simpleRepeatIntervel;
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

	public boolean isCronType(){
		return StringUtils.equals(CRON_TYPE, this.type);
	}
	
	public boolean isSimpleType(){
		return StringUtils.equals(SIMPLE_TYPE, this.type);
	}
	
	@Override
	public String toString() {
		return "SchedulerSetting [id=" + id + ", schedulerId=" + schedulerId
				+ ", name=" + name + ", description=" + description + ", type="
				+ type + ", enable=" + enable + ", cronExpress=" + cronExpress
				+ ", param1=" + param1 + ", param2=" + param2
				+ ", simpleEndTime=" + simpleEndTime + ", simpleRepeatCount="
				+ simpleRepeatCount + ", simpleRepeatIntervel="
				+ simpleRepeatIntervel + ", simpleStartTime=" + simpleStartTime
				+ "]" + super.toString();
	}

	public String getSchedulerWar() {
		return schedulerWar;
	}

	public void setSchedulerWar(String schedulerWar) {
		this.schedulerWar = schedulerWar;
	}
	
	public void setMinute(int minute) {
		this.simpleRepeatIntervel = minute * 60 * 1000;
	}
	
	public int getMinute() {
		return (this.simpleRepeatIntervel == null ? 0 : this.simpleRepeatIntervel) / (60 * 1000);
		 
	}

	public void setSeconds(int seconds) {
		this.simpleRepeatIntervel = seconds * 1000;
	}
	
	public long getSeconds() {
		return (this.simpleRepeatIntervel == null ? 0 : this.simpleRepeatIntervel) / (1000);
	}

	public String getRefValue() {
		return refValue;
	}

	public void setRefValue(String refValue) {
		this.refValue = refValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
