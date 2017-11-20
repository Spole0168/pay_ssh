/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz;

import com.ibs.core.module.corcust.domain.CorBankAcntTransDtl;
import com.ibs.core.module.corcust.domain.CorDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_ACNT_TRANS_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankAcntTransDtlBiz {

	public IPage<CorDto> findCorBankAcntTransDtlByPage(QueryPage queryPage,String st,String direction);

	public CorBankAcntTransDtl getCorBankAcntTransDtlById(String id);
	
	public CorBankAcntTransDtl saveCorBankAcntTransDtl(CorBankAcntTransDtl object);
	
	public CorBankAcntTransDtl updateCorBankAcntTransDtl(CorBankAcntTransDtl object);
	
	public void exportCorBankAcntTransDtl(ExportSetting exportSetting,String st,String direction);

}
