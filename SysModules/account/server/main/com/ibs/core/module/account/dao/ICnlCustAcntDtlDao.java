/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlCustAcntDtlDto;
import com.ibs.core.module.cnltrans.domain.CnlCustAcntDtlHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustAcntDtlDao {

	public IPage<CnlCustAcntDtl> findCnlCustAcntDtlByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlCustAcntDtl cnlCustAcntDtl);

	public CnlCustAcntDtl load(String id);
	
	/**
	 * 实现与交易流水的多表连查
	 */
	public IPage<CnlCustAcntDtlDto> findCnlCustAcntDtlByPageAndMoreTable(QueryPage queryPage,String conditon);

	public boolean saveCnlCustAcntDtl(CnlCustAcntDtl cnlCustAcntDtl);

	public void save(List<CnlCustAcntDtl> list);
	
	public List<CnlCustAcntDtl> findCnlCustAcntDtl();
	
	public void saveCnlCustAcntDtlHis(Collection<CnlCustAcntDtlHis> cnlCustAcntDtlHisList);
	
	public boolean deleteCnlCustAcntDtl();
}
