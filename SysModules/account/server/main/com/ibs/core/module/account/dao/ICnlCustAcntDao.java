/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustAcntDto;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceServiceBalance;
import com.ibs.core.module.account.domain.OperatingAcntDto;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustAcntDao {

	public IPage<CnlCustAcntDto> findCnlCustAcntByPage(QueryPage queryPage,String custType,String cnlCustCode,String localName,String currency,
			String statisticalType,String max,String min);
	
	public void saveOrUpdate(CnlCustAcnt cnlCustAcnt);

	public CnlCustAcnt load(String id);

	public IPage<OperatingAcntDto> findOperatingAcntByPage(QueryPage queryPage, String custType, String cnlCustCode,
			String localName, String currency, String groupOfAccounts, String statisticalType, String max, String min);

	public CnlCustAcnt loadBy(String cnlCustCode);

	public void saveCnlCustAcnt(CnlCustAcnt cnlCustAcnt);
	
	public void updateCnlCustAcnt(CnlCustAcnt cnlCustAcnt);

	public CnlCustAcnt findByCustCode(String custCode);

	public BigDecimal findByBal(String cnlCustCode);
	
	public List<CnlTransTraceServiceBalance> findBlance(String cnlCnlCode, String cnlCustCode,String pageSize,String pageIndex);
	
	public boolean updateBalanceDebit(List<CnlTransTrace> list);
	
	public boolean updateBalanceCridit(List<CnlTransTrace> list);

	public int blanceCount(String cnlCnlCode, String cnlCustCode);
	
	public boolean updateBalance(CnlCustAcnt cnlCustAcnt);
	
	public CnlCustAcnt findCompanyByCnlCnlCode(String cnlCnlCode);
	
	public CnlCustAcnt findPersonalByCnlCnlCodeAndCnlCustCode(String cnlCnlCode,String cnlCustCode);
	
	public boolean updateChannelDibetSuccess(String cnlCnlCode,String cnlCustCode, BigDecimal amount);
	
	public boolean updateChannelCustDibetSuccess(String cnlCnlCode,String cnlCustCode, BigDecimal amount);
	
	public boolean updateChannelCustDibetFailed(String cnlCnlCode,String cnlCustCode, BigDecimal amount);
	
	public boolean updateCompanyCreditSuccess(CnlCustAcnt cnlCustAcnt, BigDecimal amount);
	
	public boolean updatePersonalCreditFailed(CnlCustAcnt cnlCustAcnt, BigDecimal amount);

	public boolean updatePersonalCreditSuccess(CnlCustAcnt cnlCustAcnt, BigDecimal amount);
	
	public BigDecimal findTransAmount(String cnlCustCode);
	
	public CnlCustAcnt findByCnlCustAcnt(CnlCustAcnt cnlCustAcnt);
	
	public void updateAmount(String stlNum);
	
	public void updateBlance(String cnlCustCode,BigDecimal amount);
	
	public boolean cheackAccount(String cnlCnlCode, String cnlCustCode);
	
	public BigDecimal cheackBlance(String cnlCnlCode,String cnlCustCode) throws BizException;
}
