/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.account.biz;

import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlCustAcntDtlDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_ACNT_DTL
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustAcntDtlBiz {

	public IPage<CnlCustAcntDtl> findCnlCustAcntDtlByPage(QueryPage queryPage);

	public CnlCustAcntDtl getCnlCustAcntDtlById(String id);
	
	public CnlCustAcntDtl saveCnlCustAcntDtl(CnlCustAcntDtl object);
	
	public CnlCustAcntDtl updateCnlCustAcntDtl(CnlCustAcntDtl object);
	
	public void exportCnlCustAcntDtl(ExportSetting exportSetting);
	/**
	 * 与交易流水的多表查询
	 */
	public IPage<CnlCustAcntDtlDto> findCnlCustAcntDtlByPageAndMoreTable(QueryPage queryPage,String condition);

}
