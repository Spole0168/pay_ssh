package com.ibs.common.module.frameworkimpl.security.dto;

import java.util.Date;
import java.util.List;

public class MacDto {
	private String id;
	
	private String mac;
	
	private String cpuId;
	
	private String remark;
	
	private Boolean status;
	
	private Date recentlyUsedTime;
	
	private int userCount;
	
	private String menuId;
	
	private List<String> macs;

	public List<String> getMacs() {
		return macs;
	}

	public void setMacs(List<String> macs) {
		this.macs = macs;
	}

	public String getCpuId() {
		return cpuId;
	}

	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public Date getRecentlyUsedTime() {
		return recentlyUsedTime;
	}

	public void setRecentlyUsedTime(Date recentlyUsedTime) {
		this.recentlyUsedTime = recentlyUsedTime;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
