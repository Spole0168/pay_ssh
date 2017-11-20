/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.cnlcust.dao;

import java.util.List;

import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyAndOtherDto;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustCompanyDao {

	public IPage<CnlCustCompany> findCnlCustCompanyByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlCustCompany cnlCustCompany);

	public CnlCustCompany load(String id);

	public CnlCustCompany loadBy(String string, String cnlCustCode);

	public CnlCustCompany getInfoByCnlCustCode(String cnlCustCode);

	public IPage<CnlCustCompanyAndOtherDto> findCnlCustCompanyByMoreTable(QueryPage queryPage, String hqlCondition);

	public CnlCustCompanyAndOtherDto findDetail(String cnlCustCode);
	
	public List<CnlCustCompany> getAll();
}
