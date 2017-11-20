/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.pbl.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @create by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_SYS_SYS_CFG
 */
public class SysSysCfg extends BaseEntity {
	
	private String id; // ID
	private String category; // CATEGORY
	private String code; // CODE
	private String name; // NAME
	private String value; // VALUE
	private String version; // VERSION
	private String isValid; // IS_VALID
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getCategory(){
		return category;
	}
	public void setCategory(String category){
		this.category = category;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}

	public String getVersion(){
		return version;
	}
	public void setVersion(String version){
		this.version = version;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
	}

	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	
}
