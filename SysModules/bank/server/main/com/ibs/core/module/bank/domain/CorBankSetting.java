/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankSetting extends BaseEntity {
	////
	private String id; // ID
	private String bankInnerCode; // BANK_INNER_CODE
	private String country; // COUNTRY
	private String bankName; // BANK_NAME
	private String bankCode; // BANK_CODE
	private String bankBranchName; // BANK_BRANCH_NAME
	private String bankBranchCode; // BANK_BRANCH_CODE
	private String bankNum; // BANK_NUM
	private String bankLevel; // BANK_LEVEL
	private String desc; // DESC
	private String bankAddr; // BANK_ADDR
	private Date contractEffectDate; // CONTRACT_EFFECT_DATE
	private Date contractExpireDate; // CONTRACT_EXPIRE_DATE
	private String swiftCode; // SWIFT_CODE
	private String branchCode; // BRANCH_CODE
	private String ngsnCode; // NGSN_CODE
	private Long singlePayLimit; // SINGLE_PAY_LIMIT
	private Long monthPayLimit; // MONTH_PAY_LIMIT
	private Long yearPayLimit; // YEAR_PAY_LIMIT
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

	public String getBankInnerCode(){
		return bankInnerCode;
	}
	public void setBankInnerCode(String bankInnerCode){
		this.bankInnerCode = bankInnerCode;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
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

	public String getBankNum(){
		return bankNum;
	}
	public void setBankNum(String bankNum){
		this.bankNum = bankNum;
	}

	public String getBankLevel(){
		return bankLevel;
	}
	public void setBankLevel(String bankLevel){
		this.bankLevel = bankLevel;
	}

	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getBankAddr(){
		return bankAddr;
	}
	public void setBankAddr(String bankAddr){
		this.bankAddr = bankAddr;
	}

	public Date getContractEffectDate(){
		return contractEffectDate;
	}
	public void setContractEffectDate(Date contractEffectDate){
		this.contractEffectDate = contractEffectDate;
	}

	public Date getContractExpireDate(){
		return contractExpireDate;
	}
	public void setContractExpireDate(Date contractExpireDate){
		this.contractExpireDate = contractExpireDate;
	}

	public String getSwiftCode(){
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode){
		this.swiftCode = swiftCode;
	}

	public String getBranchCode(){
		return branchCode;
	}
	public void setBranchCode(String branchCode){
		this.branchCode = branchCode;
	}

	public String getNgsnCode(){
		return ngsnCode;
	}
	public void setNgsnCode(String ngsnCode){
		this.ngsnCode = ngsnCode;
	}

	public Long getSinglePayLimit(){
		return singlePayLimit;
	}
	public void setSinglePayLimit(Long singlePayLimit){
		this.singlePayLimit = singlePayLimit;
	}

	public Long getMonthPayLimit(){
		return monthPayLimit;
	}
	public void setMonthPayLimit(Long monthPayLimit){
		this.monthPayLimit = monthPayLimit;
	}

	public Long getYearPayLimit(){
		return yearPayLimit;
	}
	public void setYearPayLimit(Long yearPayLimit){
		this.yearPayLimit = yearPayLimit;
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
