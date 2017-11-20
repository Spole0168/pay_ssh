package com.ibs.core.module.mdmbasedata.domain;

import com.ibs.portal.framework.server.domain.BaseEntity;


/**
 * 系统支持语言
 * 
 * @author zdp
 * 
 */
public class Language   extends BaseEntity   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7704657169449984429L;	 
	private String code;// 数据编码
	private String name;// 名称
	private String description;// 描述
	private  int displayOrder;//排序号
	 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	public String getId() {
		return code;
	}
	
}
