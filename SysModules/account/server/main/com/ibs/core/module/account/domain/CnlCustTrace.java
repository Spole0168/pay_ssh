/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustTrace extends BaseEntity {
	
	private String id; // ID
	private String reqInnerNum; // REQ_INNER_NUM
	private String cnlCustCode; // CNL_CUST_CODE
	private String reqNum; // REQ_NUM
	private Long reqBatch; // REQ_BATCH
	private String reqType; // REQ_TYPE
	private String reqStatus; // REQ_STATUS
	private String name; // NAME
	private String certType; // CERT_TYPE
	private String certNum; // CERT_NUM
	private Date certExpire; // CERT_EXPIRE
	private String bankCustName; // BANK_CUST_NAME
	private String bankCardNum; // BANK_CARD_NUM
	private String phonenum; // PHONENUM
	private String addr; // ADDR
	private String country; // COUNTRY
	private Date msgTime; // MSG_TIME
	private String errType; // ERR_TYPE
	private String errMsg; // ERR_MSG
	private String custType; // CUST_TYPE
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private String bankCode; // BANK_CODE
	private String provience; // PROVIENCE
	private String city; // CITY
	private String bankName; // BANK_NAME
	private String bankBranchName; // BANK_BRANCH_NAME
	private String bankBranchCode; // BANK_BRANCH_CODE
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getReqInnerNum(){
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum){
		this.reqInnerNum = reqInnerNum;
	}

	public String getCnlCustCode(){
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode){
		this.cnlCustCode = cnlCustCode;
	}

	public String getReqNum(){
		return reqNum;
	}
	public void setReqNum(String reqNum){
		this.reqNum = reqNum;
	}

	public Long getReqBatch(){
		return reqBatch;
	}
	public void setReqBatch(Long reqBatch){
		this.reqBatch = reqBatch;
	}

	public String getReqType(){
		return reqType;
	}
	public void setReqType(String reqType){
		this.reqType = reqType;
	}

	public String getReqStatus(){
		return reqStatus;
	}
	public void setReqStatus(String reqStatus){
		this.reqStatus = reqStatus;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getCertType(){
		return certType;
	}
	public void setCertType(String certType){
		this.certType = certType;
	}

	public String getCertNum(){
		return certNum;
	}
	public void setCertNum(String certNum){
		this.certNum = certNum;
	}

	public Date getCertExpire(){
		return certExpire;
	}
	public void setCertExpire(Date certExpire){
		this.certExpire = certExpire;
	}

	public String getBankCustName(){
		return bankCustName;
	}
	public void setBankCustName(String bankCustName){
		this.bankCustName = bankCustName;
	}

	public String getBankCardNum(){
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum){
		this.bankCardNum = bankCardNum;
	}

	public String getPhonenum(){
		return phonenum;
	}
	public void setPhonenum(String phonenum){
		this.phonenum = phonenum;
	}

	public String getAddr(){
		return addr;
	}
	public void setAddr(String addr){
		this.addr = addr;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	public Date getMsgTime(){
		return msgTime;
	}
	public void setMsgTime(Date msgTime){
		this.msgTime = msgTime;
	}

	public String getErrType(){
		return errType;
	}
	public void setErrType(String errType){
		this.errType = errType;
	}

	public String getErrMsg(){
		return errMsg;
	}
	public void setErrMsg(String errMsg){
		this.errMsg = errMsg;
	}

	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
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

	public String getBankCode(){
		return bankCode;
	}
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}

	public String getProvience(){
		return provience;
	}
	public void setProvience(String provience){
		this.provience = provience;
	}

	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getBankName(){
		return bankName;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
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

	
}
