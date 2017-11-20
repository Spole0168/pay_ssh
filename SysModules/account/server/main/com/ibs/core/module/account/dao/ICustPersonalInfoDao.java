package com.ibs.core.module.account.dao;

import com.ibs.core.module.account.domain.CustPersonalInfo;

public interface ICustPersonalInfoDao {
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 根据cnl_cust的ID查看详情
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public CustPersonalInfo findByCustid(String id);
}
