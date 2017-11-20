/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.fundtransaction.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.bank.domain.CorBankAcntTransHis;
import com.ibs.core.module.corcust.domain.CorDto;
import com.ibs.core.module.fundtransaction.domain.CorBankAcntTrans;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankAcntTransDao {

	public IPage<CorDto> findCorBankAcntTransByPage(QueryPage queryPage,String st,String direction);
	
	public void saveOrUpdate(CorBankAcntTrans corBankAcntTrans);

	public CorBankAcntTrans load(String id);
	public void savaOrUpdateBatch(List<CorBankAcntTrans> list);
	
	public List<CorBankAcntTrans> findCorBankAcntTrans();
	
	public void saveCorBankAcntTransHis(Collection<CorBankAcntTransHis> corBankAcntTransHisList);
	
	public boolean deleteCorBankAcntTrans();
}
