/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.cnlcust.dao;

import java.util.List;

import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.account.domain.CustPersonal;
import com.ibs.core.module.cnlcust.domain.CnlCust;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlCustDao {

	public IPage<CustPersonal> findCnlCustByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlCust cnlCust);

	public CnlCust load(String id);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 根据检索条件得到ipage数据
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public IPage<CustPersonal> findCnlCustBylike(QueryPage queryPage,String cnlCustCode,String cnlSysName,String cnlCustType,String custStatus,
			String certType,String certNum,String realNameInfoCnl,String custLevel,String createStartTime,String createEndTime);

	public void deleteByCnlCustCode(String cnlCustCode);

	public CnlCust loadBy(String string, String cnlCustCode);

	public List<CnlCust> validateCustInCnl(String cnlCnlCode, String localName, String certNum);

	public CnlCust findByCnlAndCode(String cnlCustCode, String cnlCnlCode);

	public CnlCust findByCnlAndCert(String certNum, String cnlCnlCode);
	
	public CnlCust loadByCnlCustCode(String cnlCustCode);

	public CnlReqTransDto findPersonAboutCustPersonal(String cnlCustCode);

	public CnlReqTransDto findCompanyAboutCustCompany(String cnlCustCode);
	
}
