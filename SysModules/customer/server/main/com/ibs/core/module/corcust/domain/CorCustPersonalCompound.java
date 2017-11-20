/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.domain;

import java.util.List;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 
 * 合体类
 * 将一个用户相关的所有信息都放入这个类中，包括一个CorCust，一个CorCustPersonal，一个CorCustPhones，一个CorCustAddr
 * 
 * 
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustPersonalCompound extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CorCust curCust;
	private CorCustPersonal corCustPersonal;
	private List<CorCustPhones> phonesList;
	private List<CorCustAddr> addrList;
	public CorCust getCurCust() {
		return curCust;
	}
	public void setCurCust(CorCust curCust) {
		this.curCust = curCust;
	}
	public CorCustPersonal getCorCustPersonal() {
		return corCustPersonal;
	}
	public void setCorCustPersonal(CorCustPersonal corCustPersonal) {
		this.corCustPersonal = corCustPersonal;
	}
	public List<CorCustPhones> getPhonesList() {
		return phonesList;
	}
	public void setPhonesList(List<CorCustPhones> phonesList) {
		this.phonesList = phonesList;
	}
	public List<CorCustAddr> getAddrList() {
		return addrList;
	}
	public void setAddrList(List<CorCustAddr> addrList) {
		this.addrList = addrList;
	}
	@Override
	public String toString() {
		return "CorCustPersonalCompound [curCust=" + curCust + ", corCustPersonal=" + corCustPersonal + ", phonesList="
				+ phonesList + ", addrList=" + addrList + "]";
	}
	
}
