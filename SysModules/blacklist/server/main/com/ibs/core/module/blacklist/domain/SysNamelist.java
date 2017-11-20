/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.blacklist.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_NAMELIST
 * @modify		: your comments goes here (author,date,reason).
 */
public class SysNamelist extends BaseEntity {
	
	private String id; // ID
	private String nlType; // NL_TYPE
	private String nlId; // NL_ID
	private String nlDesc; // NL_DESC
	private String accessType; // ACCESS_TYPE
	private String status; // STATUS
	private String reviewer; // REVIEWER
	private Date reviewTime; // REVIEW_TIME
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String isValid; // IS_VALID
	private String rtime;
	
	
	
	
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getNlType(){
		return nlType;
	}
	public void setNlType(String nlType){
		this.nlType = nlType;
	}

	public String getNlId(){
		return nlId;
	}
	public void setNlId(String nlId){
		this.nlId = nlId;
	}

	public String getNlDesc(){
		return nlDesc;
	}
	public void setNlDesc(String nlDesc){
		this.nlDesc = nlDesc;
	}

	public String getAccessType(){
		return accessType;
	}
	public void setAccessType(String accessType){
		this.accessType = accessType;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getReviewer(){
		return reviewer;
	}
	public void setReviewer(String reviewer){
		this.reviewer = reviewer;
	}

	public Date getReviewTime(){
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime){
		this.reviewTime = reviewTime;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	
}
