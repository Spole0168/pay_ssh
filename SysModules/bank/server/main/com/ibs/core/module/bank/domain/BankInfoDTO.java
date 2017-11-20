package com.ibs.core.module.bank.domain;

import java.util.Date;

public class BankInfoDTO 
{
	////
	private String id; 										 // 	ID
	private String bankInnerCode; 					//		银行编码----
	private String bankName;  						//		银行名称
	private String bankBranchName;					 //		分支行名称	
	private String bankLevel; 							//		银行层级
	private String country;								//		国家	
	private String bankAddr;							//		银行地址
	private Date contractEffectDate; 				//		合同开始日期	
	private Date contractExpireDate;				 //		合同结束日期
	private String techSupportName; 				//	 	技术负责人	
	private String techSupportPhonenum;			//		技术负责人电话	
	private String techSupportEmail;				//		技术负责人邮箱
	private String businessSupportName;			//		业务负责人	
	private String businessSupportPhonenum; 	//		业务负责人电话
	private String businessSupportEmail;			//		业务负责人邮箱
	private String bankNum;							//		银行联行号	接口文档中用到
	private String swiftCode;							//		SWIFT 代码
	private String branchCode;						//		BRANCH代码
	private String ngsnCode;							//		NGSN代码
	private Date createTime;							//		创建时间
	private String creator;								//		创建人	
	private String desc;								//		备注	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBankInnerCode() {
		return bankInnerCode;
	}
	public void setBankInnerCode(String bankInnerCode) {
		this.bankInnerCode = bankInnerCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankLevel() {
		return bankLevel;
	}
	public void setBankLevel(String bankLevel) {
		this.bankLevel = bankLevel;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBankAddr() {
		return bankAddr;
	}
	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
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
	public String getTechSupportName() {
		return techSupportName;
	}
	public void setTechSupportName(String techSupportName) {
		this.techSupportName = techSupportName;
	}
	public String getTechSupportPhonenum() {
		return techSupportPhonenum;
	}
	public void setTechSupportPhonenum(String techSupportPhonenum) {
		this.techSupportPhonenum = techSupportPhonenum;
	}
	public String getTechSupportEmail() {
		return techSupportEmail;
	}
	public void setTechSupportEmail(String techSupportEmail) {
		this.techSupportEmail = techSupportEmail;
	}
	public String getBusinessSupportName() {
		return businessSupportName;
	}
	public void setBusinessSupportName(String businessSupportName) {
		this.businessSupportName = businessSupportName;
	}
	public String getBusinessSupportPhonenum() {
		return businessSupportPhonenum;
	}
	public void setBusinessSupportPhonenum(String businessSupportPhonenum) {
		this.businessSupportPhonenum = businessSupportPhonenum;
	}
	public String getBusinessSupportEmail() {
		return businessSupportEmail;
	}
	public void setBusinessSupportEmail(String businessSupportEmail) {
		this.businessSupportEmail = businessSupportEmail;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getNgsnCode() {
		return ngsnCode;
	}
	public void setNgsnCode(String ngsnCode) {
		this.ngsnCode = ngsnCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "BankInfoDTO [id=" + id + ", bankInnerCode=" + bankInnerCode + ", bankName=" + bankName
				+ ", bankBranchName=" + bankBranchName + ", bankLevel=" + bankLevel + ", country=" + country
				+ ", bankAddr=" + bankAddr + ", contractEffectDate=" + contractEffectDate + ", contractExpireDate="
				+ contractExpireDate + ", techSupportName=" + techSupportName + ", techSupportPhonenum="
				+ techSupportPhonenum + ", techSupportEmail=" + techSupportEmail + ", businessSupportName="
				+ businessSupportName + ", businessSupportPhonenum=" + businessSupportPhonenum
				+ ", businessSupportEmail=" + businessSupportEmail + ", bankNum=" + bankNum + ", swiftCode=" + swiftCode
				+ ", branchCode=" + branchCode + ", ngsnCode=" + ngsnCode + ", createTime=" + createTime + ", creator="
				+ creator + ", desc=" + desc + "]";
	}
	
	
}
