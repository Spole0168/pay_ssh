package com.ibs.core.module.demo.domain;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * demo
 * 
 * @author 
 * 
 */
public class Demo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5634114314212239630L;
	
	private String id;// ID
	private String demoName;// NAME
	private String demoDescription;// DESCRIPTION
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	public String getDemoDescription() {
		return demoDescription;
	}
	public void setDemoDescription(String demoDescription) {
		this.demoDescription = demoDescription;
	}
	
}
