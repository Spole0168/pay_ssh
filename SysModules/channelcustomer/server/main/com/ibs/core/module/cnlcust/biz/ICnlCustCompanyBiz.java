/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.biz;

import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyAndOtherDto;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustCompanyBiz {

	public IPage<CnlCustCompany> findCnlCustCompanyByPage(QueryPage queryPage);

	public CnlCustCompany getCnlCustCompanyById(String id);
	
	public CnlCustCompany saveCnlCustCompany(CnlCustCompany object);
	
	public CnlCustCompany updateCnlCustCompany(CnlCustCompany object);
	
	public void exportCnlCustCompany(ExportSetting exportSetting);
	
	public CnlCustCompany getInfoByCnlCustCode(String cnlCustCode);

	public IPage<CnlCustCompanyAndOtherDto> findCnlCustCompanyByMoreTable(QueryPage queryPage,String hqlCondtion);

	public CnlCustCompanyAndOtherDto findDetail(String cnlCustCode);
}
