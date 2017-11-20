package com.ibs.common.module.frameworkimpl.security.dto;

public class UserDto {
	private String id;
	private String userName; // 登录用户名
	private String userCode; // 代码
	private String description;
	private String status; // 0-失效;1-生效.缺省为1.用于临时禁止用户
	private String userType;//参考 User  USER_TYPE_EMPLOYEE  USER_TYPE_CUSTOMER

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
