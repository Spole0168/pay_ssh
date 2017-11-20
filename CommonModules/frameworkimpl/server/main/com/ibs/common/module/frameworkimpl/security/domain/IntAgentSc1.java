package com.ibs.common.module.frameworkimpl.security.domain;


public class IntAgentSc1 extends User { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 6760534388777906916L;
	/**
	 * 国际件货代
	 */
	 
	private String intCode;
	private String intName; 
	private String intStatus;
	private String intUsed;
	public String getIntCode() {
		return intCode;
	}
	public void setIntCode(String intCode) {
		this.intCode = intCode;
	}
	public String getIntName() {
		return intName;
	}
	public void setIntName(String intName) {
		this.intName = intName;
	}
	public String getIntStatus() {
		return intStatus;
	}
	public void setIntStatus(String intStatus) {
		this.intStatus = intStatus;
	}
	public String getIntUsed() {
		return intUsed;
	}
	public void setIntUsed(String intUsed) {
		this.intUsed = intUsed;
	}
	public String getUsed(){
		return this.intUsed;
	}
	
	public void setUsed(String used){
		this.intUsed = used;
	}
	 
	public String getDisplayName() {
		return intName;
	}

	public String getUserCode() {
		return intCode;
	}
	public String getStatus()
	{
		return intStatus;
	}
	
	
}
