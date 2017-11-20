/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.bank.dao;


import java.util.List;

import com.ibs.core.module.bank.domain.CorBankAcnt;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankAcntDao {

	public IPage<CorBankAcnt> findCorBankAcntByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorBankAcnt corBankAcnt);

	public CorBankAcnt load(String id);
	
	
	/**
	 * 增加一条用户资金账号的记录
	 * @author jicheng
	 * @param corBankAcnt
	 */

	public void saveCorBankAcnt(CorBankAcnt corBankAcnt);
	
	/**
	 * 修改用户资金账号的记录
	 * @author jicheng
	 * @param corBankAcnt
	 */
	
	public void updateCorBankAcnt(CorBankAcnt corBankAcnt);
	
	
	/**
	 * 根据客户名称查找
	 * @param custName
	 */

	public List<CorBankAcnt> findByCorBankAcnt(CorBankAcnt corBankAcnt);
	
	public CorBankAcnt findByCustCode(String custCode);
	
	/** 银行转账所用：根据银行卡号和渠道客户编码 查询一条记录
	 * @param cardNum
	 * @param cnlCustCode
	 * @return
	 */
	public CorBankAcnt findByCardNumAndCnlCustCode(String cardNum, String cnlCustCode);
	
	
	public CorBankAcnt findByBankAcntCode(String bankAcntCode);
	
}
