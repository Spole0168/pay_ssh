package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class UserDelegation extends BaseEntity {

	public static final int FULL_DELEGATION = 3002;

	public static final String FULL_DELEGATION_VALUE = "完全授权";

	public static final int OUT_OF_OFFICE_DELEGATION = 3001;

	public static final String OUT_OF_OFFICE_DELEGATION_VALUE = "出差授权";

	private static final long serialVersionUID = -1356836627626939650L;
	
	private String id;

	private String originUserId;
	private String originUserName;
	private String originOrgId;
	private String originOrgName;
	private String originPositionId;
	private String originPositionName;
	
	private String delegateUserId;
	private String delegateUserName;
	private String delegateOrgId;
	private String delegateOrgName;
	private String delegatePositionId;
	private String delegatePositionName;
	
	private Date startTime;
	private Date endTime;
	
	private Integer type;

	public UserDelegation() {
	}

	public String getOriginUserId() {
		return originUserId;
	}

	public void setOriginUserId(String originUserId) {
		this.originUserId = originUserId;
	}

	public String getOriginOrgId() {
		return originOrgId;
	}

	public void setOriginOrgId(String originOrgId) {
		this.originOrgId = originOrgId;
	}

	public String getOriginPositionId() {
		return originPositionId;
	}

	public void setOriginPositionId(String originPositionId) {
		this.originPositionId = originPositionId;
	}
	
	public String getOriginUserName() {
		return originUserName;
	}

	public void setOriginUserName(String originUserName) {
		this.originUserName = originUserName;
	}

	public String getOriginOrgName() {
		return originOrgName;
	}

	public void setOriginOrgName(String originOrgName) {
		this.originOrgName = originOrgName;
	}

	public String getOriginPositionName() {
		return originPositionName;
	}

	public void setOriginPositionName(String originPositionName) {
		this.originPositionName = originPositionName;
	}

	public String getDelegateUserId() {
		return delegateUserId;
	}

	public void setDelegateUserId(String delegateUserId) {
		this.delegateUserId = delegateUserId;
	}

	public String getDelegateUserName() {
		return delegateUserName;
	}

	public void setDelegateUserName(String delegateUserName) {
		this.delegateUserName = delegateUserName;
	}

	public String getDelegateOrgId() {
		return delegateOrgId;
	}

	public void setDelegateOrgId(String delegateOrgId) {
		this.delegateOrgId = delegateOrgId;
	}

	public String getDelegateOrgName() {
		return delegateOrgName;
	}

	public void setDelegateOrgName(String delegateOrgName) {
		this.delegateOrgName = delegateOrgName;
	}

	public String getDelegatePositionId() {
		return delegatePositionId;
	}

	public void setDelegatePositionId(String delegatePositionId) {
		this.delegatePositionId = delegatePositionId;
	}

	public String getDelegatePositionName() {
		return delegatePositionName;
	}

	public void setDelegatePositionName(String delegatePositionName) {
		this.delegatePositionName = delegatePositionName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String toString() {
		return "originUserName: " + originUserName + 
				";originOrgName: " + originOrgName + 
				";originPositionName: " + originPositionName +
				";delegateUser: " + delegateUserName +
				";originOrg: " + delegateOrgName + 
				";originPosition: " + delegatePositionName +
				";type: " + type;
	}
}
