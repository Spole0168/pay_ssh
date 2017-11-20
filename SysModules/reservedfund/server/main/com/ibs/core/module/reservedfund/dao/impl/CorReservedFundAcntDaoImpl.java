/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.reservedfund.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.account.domain.CustPersonal;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.reservedfund.dao.ICorReservedFundAcntDao;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_RESERVED_FUND_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorReservedFundAcntDaoImpl extends BaseEntityDao<CorReservedFundAcnt> implements
		ICorReservedFundAcntDao {

	public IPage<CorReservedFundAcnt> findCorReservedFundAcntByPage(QueryPage queryPage) {
		logger.info("entering action::CorReservedFundAcntDaoImpl.findCorReservedFundAcntByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorReservedFundAcnt load(String id) {
		logger.info("entering action::CorReservedFundAcntDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorReservedFundAcnt corReservedFundAcnt) {
		logger.info("entering action::CorReservedFundAcntDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corReservedFundAcnt);
	}

	public List<CorReservedFundAcnt> findAll(){
		return super.findAll();
	}
	
	/*
	 * 徐伟龙备付金管理
	 * 2016、08、03
	 */
	public IPage<CorReservedFundAcnt> findByBankNum(QueryPage queryPage,String bankInnerCode){
		String hql="select crfa.id as id,crfa.bankAcntCode as bankAcntCode,crfa.cnlCustCode as cnlCustCode,crfa.country as country,crfa.custName as custName,"
				+ "crfa.certType as certType,crfa.certNum as certNum,crfa.bankName as bankName,crfa.bankCode as bankCode,"
				+ "crfa.bankBranchCode as bankBranchCode,crfa.bankBranchName as bankBranchName,crfa.bankCardNum as bankCardNum,"
				+ "crfa.bankCardType as bankCardType,crfa.status as status,crfa.currency as currency,crfa.acntCategory as acntCategory,"
				+ "crfa.acntType as acntType,crfa.lastTransTime as lastTransTime,crfa.frozenAmoumt as frozenAmoumt,crfa.avaliableAmount as avaliableAmount,"
				+ "crfa.balance as balance,crfa.bankNum as bankNum,crfa.createTime as createTime,crfa.updateTime as updateTime,"
				+ "crfa.isValid as isValid,crfa.creator as creator,crfa.updator as updator "
				+ "from CorReservedFundAcnt as crfa where crfa.bankAcntCode='"+bankInnerCode+"' and crfa.isValid='"+Constants.IS_VALID_VALID+"'";
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		queryPage.setHqlString(hql);
		IPage<CorReservedFundAcnt> page = super.findPageByHql(queryPage,CorReservedFundAcnt.class);
		return page;
	}
	
	/*
	 * 修改  检验银行卡号是否存在
	 * */
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum,String id){
		String hql="select crfa.id as id,crfa.bankAcntCode as bankAcntCode,crfa.cnlCustCode as cnlCustCode,crfa.country as country,crfa.custName as custName,"
				+ "crfa.certType as certType,crfa.certNum as certNum,crfa.bankName as bankName,crfa.bankCode as bankCode,"
				+ "crfa.bankBranchCode as bankBranchCode,crfa.bankBranchName as bankBranchName,crfa.bankCardNum as bankCardNum,"
				+ "crfa.bankCardType as bankCardType,crfa.status as status,crfa.currency as currency,crfa.acntCategory as acntCategory,"
				+ "crfa.acntType as acntType,crfa.lastTransTime as lastTransTime,crfa.frozenAmoumt as frozenAmoumt,crfa.avaliableAmount as avaliableAmount,"
				+ "crfa.balance as balance,crfa.bankNum as bankNum,crfa.createTime as createTime,crfa.updateTime as updateTime,"
				+ "crfa.isValid as isValid,crfa.creator as creator,crfa.updator as updator "
				+ "from CorReservedFundAcnt as crfa where crfa.bankAcntCode='"+bankInnerCode+"' and crfa.bankCardNum='"+bankCardNum+"' and crfa.isValid='"+Constants.IS_VALID_VALID+"'";
		List<CorReservedFundAcnt> crfa = super.findByHql(hql, null, null, CorReservedFundAcnt.class);
		if(crfa.size() > 0){
			CorReservedFundAcnt crfanct = crfa.get(0);
			if(crfanct.getId().equals(id)){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	/*
	 *  新增  检验银行卡号是否存在
	 * */
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum){
		String hql="select crfa.id as id,crfa.bankAcntCode as bankAcntCode,crfa.cnlCustCode as cnlCustCode,crfa.country as country,crfa.custName as custName,"
				+ "crfa.certType as certType,crfa.certNum as certNum,crfa.bankName as bankName,crfa.bankCode as bankCode,"
				+ "crfa.bankBranchCode as bankBranchCode,crfa.bankBranchName as bankBranchName,crfa.bankCardNum as bankCardNum,"
				+ "crfa.bankCardType as bankCardType,crfa.status as status,crfa.currency as currency,crfa.acntCategory as acntCategory,"
				+ "crfa.acntType as acntType,crfa.lastTransTime as lastTransTime,crfa.frozenAmoumt as frozenAmoumt,crfa.avaliableAmount as avaliableAmount,"
				+ "crfa.balance as balance,crfa.bankNum as bankNum,crfa.createTime as createTime,crfa.updateTime as updateTime,"
				+ "crfa.isValid as isValid,crfa.creator as creator,crfa.updator as updator "
				+ "from CorReservedFundAcnt as crfa where crfa.bankAcntCode='"+bankInnerCode+"' and crfa.bankCardNum='"+bankCardNum+"' and crfa.isValid='"+Constants.IS_VALID_VALID+"'";
		List<CorReservedFundAcnt> crfa = super.findByHql(hql, null, null, CorReservedFundAcnt.class);
		if(crfa.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	/*
	 * 根据ID删除备付金信息
	 * */
	public void deleteById(String id){
		CorReservedFundAcnt crfa = super.load(id);
		super.remove(crfa);
	}
}
