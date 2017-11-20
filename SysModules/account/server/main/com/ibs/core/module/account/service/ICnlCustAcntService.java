/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.service;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.bank.domain.CorBankAcnt;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustAcntService {

	/**
	 * 根据渠道客户号来查当前可用余额
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return
	 * @throws Exception
	 */

	public BigDecimal queryBalance(String cnlCustCode, String cnlCnlCode) throws Exception;

	/**
	 * 根据渠道客户号，时间段，出入金类型来查询出金明细或者入金明细
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param StartTransTime
	 * @param endTransTime
	 * @param transType
	 * @return
	 * @throws Exception
	 */

	public CnlTrans3m queryCnlTrans3m(String cnlCustCode, String StartTransTime, String endTransTime, String transType)
			throws Exception;

	/**
	 * 根据渠道客户号，卡号，卡银行信息，币种新增用户资金账号
	 * 
	 * @author jicheng
	 * @param corBankAcnt
	 * @throws Exception
	 */

	public void addBanktAcnt(CorBankAcnt corBankAcnt) throws Exception;

	/**
	 * 根据渠道客户号，卡号，卡银行信息，币种新增总帐户
	 * 
	 * @author jicheng
	 * @param cnlCustAcnt
	 * @throws Exception
	 */

	public void addCustAcnt(CnlCustAcnt cnlCustAcnt) throws Exception;

	/**
	 * 根据渠道客户号来销户
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @throws Exception
	 */

	public void logout(String cnlCustCode, String cnlCnlCode) throws Exception;

	/**
	 * 企业卡片帐账户开户
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param bankCardNum
	 * @param certNum
	 * @param custName
	 * @param bankBranchCode
	 * @throws Exception
	 */

	public void createCompanyAccount(String cnlCustCode, String cnlCnlCode, String bankCardNum, String certNum,
			String custName, String bankBranchCode) throws Exception;

	/**
	 * 个人卡片帐账户开户
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param bankCardNum
	 * @param bankCode
	 * @param certNum
	 * @param custName
	 * @throws Exception
	 */
	public void createPersonlAccount(String cnlCustCode, String cnlCnlCode, String bankCardNum, String bankCode,
			String certNum, String custName) throws Exception;

	/**
	 * 卡片帐账户修改
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param bankCardNum
	 * @param bankCode
	 * @param certNum
	 * @param custName
	 * @throws Exception
	 */

	public void updateAccount(String cnlCustCode, String cnlCnlCode, String bankCardNum, String bankCode,
			String certNum, String custName) throws Exception;

	/**
	 * 校验银行卡
	 * 
	 * @author jicheng
	 * @param bankCardNum
	 * @param cnlCustCode
	 * @return
	 * @throws Exception
	 */
	public boolean checkCodeAndNum(String bankCardNum, String cnlCustCode) throws Exception;

	/**
	 * 校验银行卡完整版
	 * 
	 * @author jicheng
	 * @param bankCardNum
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return
	 * @throws Exception
	 */
	public boolean checkCnlCustCodeAndbankCardNum(String bankCardNum, String cnlCustCode, String cnlCnlCode)
			throws Exception;

	/**
	 * 根据渠道客户编号查询资金账号信息
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @return
	 */
	public CorBankAcnt findBankAcnt(String cnlCustCode) throws Exception;

	/**
	 * 根据渠道客户编号查询资金账号信息完整版
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @return
	 */
	public CorBankAcnt queryBankAcnt(String cnlCustCode, String cnlCnlCode) throws Exception;

	/**
	 * 验证银行编号是否存在于数字字典中
	 * 
	 * @author jicheng
	 * @param bankCode
	 * @return
	 * @throws Exception
	 */
	public boolean checkBankCode(String bankCode) throws Exception;

	/**
	 * 验证分行编号是否存在是否正确
	 * 
	 * @author jicheng
	 * @param bankBranchCode
	 * @return
	 * @throws Exception
	 */

	public boolean checkBankBranchCode(String bankBranchCode) throws Exception;

	/**
	 * 验证当前卡片帐帐户是否存在是否有效
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return
	 * @throws Exception
	 */

	public boolean checkCnlCustAcnt(String cnlCustCode, String cnlCnlCode) throws Exception;

}
