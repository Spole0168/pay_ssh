/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.mdmbasedata.dao;



import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_BANK_DICT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface IPblBankDictDao {

	public IPage<PblBankDict> findPblBankDictByPage(QueryPage queryPage);
	
	public void saveOrUpdate(PblBankDict pblBankDict);

	public PblBankDict load(String id);
	
	public PblBankDict findByBankBranchCode(String bankBranchCode);
	
}
