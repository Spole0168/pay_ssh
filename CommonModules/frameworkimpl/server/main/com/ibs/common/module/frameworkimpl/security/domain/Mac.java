package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class Mac extends BaseEntity{
	
	private static final long serialVersionUID = -4975900649708794141L;
	
	private String mac;
	
	private String cpuId;
	
	private String remark;
	
	private Boolean status;
	
	private Date recentlyUsedTime;
	
	public Mac() {
	}
	
	public Mac(String mac, String cpuid) {
		this.mac = mac;
		this.cpuId = cpuid;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMac() {
		return mac;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getStatus() {
		return status;
	}

	public String getCpuId() {
		return cpuId;
	}

	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getRecentlyUsedTime() {
		return recentlyUsedTime;
	}

	public void setRecentlyUsedTime(Date recentlyUsedTime) {
		this.recentlyUsedTime = recentlyUsedTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Mac other = (Mac)obj;
		// 目前只检验mac，不校验cpuid
		if (this.getMac() != null && other.getMac() != null)
			return this.getMac().equals(other.getMac());
		
//		if (this.getMac() != null && other.getMac() != null
//				&& this.getCpuId() != null && other.getCpuId() != null)
//			return this.getMac().equals(other.getMac()) && this.getCpuId().equals(other.getCpuId());
		
		return false;
	}

}
