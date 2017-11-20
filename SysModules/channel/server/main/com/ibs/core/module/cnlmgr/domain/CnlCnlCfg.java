/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlmgr.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CNL_CFG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCnlCfg extends BaseEntity {
	
	private String id; // ID
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlDesc; // CNL_DESC
	private String cnlSysName; // CNL_SYS_NAME
	private String cnlStatus; // CNL_STATUS
	private String cnlCustCode; // CNL_CUST_CODE
	private String custCode; // CUST_CODE
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private Date contractEffectDate;//合同起始时间
	private Date contractExpireDate;//合同结束时间
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getCnlDesc(){
		return cnlDesc;
	}
	public void setCnlDesc(String cnlDesc){
		this.cnlDesc = cnlDesc;
	}

	public String getCnlSysName(){
		return cnlSysName;
	}
	public void setCnlSysName(String cnlSysName){
		this.cnlSysName = cnlSysName;
	}

	public String getCnlStatus(){
		return cnlStatus;
	}
	public void setCnlStatus(String cnlStatus){
		this.cnlStatus = cnlStatus;
	}

	public String getCnlCustCode(){
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode){
		this.cnlCustCode = cnlCustCode;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
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
	public Date getContractEffectDate() {
		return contractEffectDate;
	}
	public void setContractEffectDate(Date contractEffectDate) {
		this.contractEffectDate = contractEffectDate;
	}
	public Date getContractExpireDate() {
		return contractExpireDate;
	}
	public void setContractExpireDate(Date contractExpireDate) {
		this.contractExpireDate = contractExpireDate;
	}

	
}
