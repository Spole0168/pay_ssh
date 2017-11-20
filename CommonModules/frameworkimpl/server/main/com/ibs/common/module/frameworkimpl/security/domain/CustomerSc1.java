package com.ibs.common.module.frameworkimpl.security.domain;

/**
 * 客户
 * 
 * @author huolang
 * 
 */
public class CustomerSc1 extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 540464822814826907L;

	private String customerCode;
	private String customerName;
	private String customerUsed;
	private String customerStatus;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerUsed() {
		return customerUsed;
	}

	public void setCustomerUsed(String customerUsed) {
		this.customerUsed = customerUsed;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getDisplayName() {
		return customerName;
	}

	public void setDisplayName(String displayName) {
		this.customerName = displayName;
	}

	public String getUserCode() {
		return customerCode;
	}

	public void setUserCode(String userCode) {
		this.customerCode = userCode;
	}

	public String getStatus() {
		return customerStatus;
	}

	public void setStatus(String status) {
		this.customerStatus = status;
	}

	public String getUsed(){
		return this.customerUsed;
	}
	
	public void setUsed(String used){
		this.customerUsed = used;
	}
}
