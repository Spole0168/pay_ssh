package com.ibs.common.module.frameworkimpl.security.domain;

import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;

public class FltAgentSc1 extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 135059719685750641L;
	private String fltCode;
	private String fltName;
	private String fltStatus;
	private String fltUsed;

	public String getFltCode() {
		return fltCode;
	}

	public void setFltCode(String fltCode) {
		this.fltCode = fltCode;
	}

	public String getFltName() {
		return fltName;
	}

	public void setFltName(String fltName) {
		this.fltName = fltName;
	}

	public String getFltStatus() {
		return fltStatus;
	}

	public void setFltStatus(String fltStatus) {
		this.fltStatus = fltStatus;
	}

	public String getDisplayName() {
		return fltName;
	}

	public String getUserCode() {
		return fltCode;
	}

	public String getStatus() {
		if (PermissionConstant.AGENT_VALID.equalsIgnoreCase(this.fltStatus)) {
			return User.USER_STATUS_VALID;
		} else {
			return User.USER_STATUS_INVALID;
		}

	}

//	public String getUsed() {
//		return PermissionConstant.USER_USED;
//	}

	public String getFltUsed() {
		return fltUsed;
	}

	public void setFltUsed(String fltUsed) {
		this.fltUsed = fltUsed;
	}
	public String getUsed(){
		return this.fltUsed;
	}
	
	public void setUsed(String used){
		this.fltUsed = used;
	}

}
