/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.biz;

import java.util.List;

import com.ibs.core.module.bank.domain.BankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankInfoDTO;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_INTF
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankIntfBiz {
	////
	public IPage<CorBankIntf> findCorBankIntfByPage(QueryPage queryPage);

	public CorBankIntf getCorBankIntfById(String id);
	
	public CorBankInfoDTO getBankInnerCode(String  id,String  bid) ;
	
	public CorBankIntf saveCorBankIntf(CorBankIntf object);
	
	public CorBankIntf updateCorBankIntf(CorBankIntf object);
	
	public void exportCorBankIntf(ExportSetting exportSetting);
	
	public IPage<CorBankInfoDTO> findCorBankInfoByPageAndMoreTable(QueryPage queryPage);

//	public CorBankInfoDTO getInnerCode(String bankInnerCode);
	public BankInfoDTO getInnerCode(String id);
//页面查询 bankInnerCode 是否存在
	public List<CorBankIntf>  isSame(String bankInnerCode);
	
	public boolean isSameEdit(String bankInnerCode, String id);
	
	
	public List<CorBankIntf> findAll();
	
	/*public CorReservedFundAcnt findAcntInfo(String bankNum);*/
}
