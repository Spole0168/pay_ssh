/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.mdmbasedata.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_BANK_DICT
 * @modify		: your comments goes here (author,date,reason).
 */
public class PblBankDict extends BaseEntity {
	
	private String id; // ID
	private String bankName; // BANK_NAME
	private String bankCode; // BANK_CODE
	private String bankBranchName; // BANK_BRANCH_NAME
	private String bankBranchCode; // BANK_BRANCH_CODE
	private String countryCode; // COUNTRY_CODE
	private String description; // DESCRIPTION
	private Long displayorder; // DISPLAYORDER
	private String status; // STATUS
	private String version; // VERSION
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String provienceCode; // PROVIENCE_CODE
	private String provienceName; // PROVIENCE_NAME
	private String cityCode; // CITY_CODE
	private String cityName; // CITY_NAME
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getBankName(){
		return bankName;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankCode(){
		return bankCode;
	}
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}

	public String getBankBranchName(){
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName){
		this.bankBranchName = bankBranchName;
	}

	public String getBankBranchCode(){
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode){
		this.bankBranchCode = bankBranchCode;
	}

	public String getCountryCode(){
		return countryCode;
	}
	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public Long getDisplayorder(){
		return displayorder;
	}
	public void setDisplayorder(Long displayorder){
		this.displayorder = displayorder;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getVersion(){
		return version;
	}
	public void setVersion(String version){
		this.version = version;
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

	public String getProvienceCode(){
		return provienceCode;
	}
	public void setProvienceCode(String provienceCode){
		this.provienceCode = provienceCode;
	}

	public String getProvienceName(){
		return provienceName;
	}
	public void setProvienceName(String provienceName){
		this.provienceName = provienceName;
	}

	public String getCityCode(){
		return cityCode;
	}
	public void setCityCode(String cityCode){
		this.cityCode = cityCode;
	}

	public String getCityName(){
		return cityName;
	}
	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	
}
