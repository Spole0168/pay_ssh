package com.ibs.common.module.frameworkimpl.security.dto;



public class UserSimpleInfo {
	private String id;
	private String userName; // 登录用户名 
	private String userType;//参考 User  USER_TYPE_EMPLOYEE  USER_TYPE_CUSTOMER
	private String displayName; // 员工/客户/货代的名字  即用户名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
 
	 

}
