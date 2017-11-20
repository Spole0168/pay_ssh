/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.corcust.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.bank.domain.CorBankAcntTransDtlCh;
import com.ibs.core.module.corcust.domain.CorBankAcntTransDtl;
import com.ibs.core.module.corcust.domain.CorDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankAcntTransDtlDao {

	public IPage<CorBankAcntTransDtl> findCorBankAcntTransDtlByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorBankAcntTransDtl corBankAcntTransDtl);

	public CorBankAcntTransDtl load(String id);

	public List<CorBankAcntTransDtl> findDtl(Date startTime,Date endTime);
	
	public void saveBycollection(Collection<CorBankAcntTransDtl> corBankAcntTransDtlList);
	
	public IPage<CorDto> findCorBankAcntTransDtlByPage(QueryPage queryPage, String st, String direction);

	public void updateStatus(List<CorBankAcntTransDtl> listDtl);

	public void updateStatus2(List<CorBankAcntTransDtl> listDtl);
	
	public List<CorBankAcntTransDtl> findCorBankAcntTransDtl();
	
	public void saveCorBankAcntTransDtlCh(Collection<CorBankAcntTransDtlCh> corBankAcntTransDtlChList);
	
	public boolean deleteCorBankAcntTransDtl();
}
