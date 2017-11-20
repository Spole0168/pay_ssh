/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.account.domain.Reconciliation;
import com.ibs.core.module.account.domain.SumAmountDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTransDao {

	public IPage<CnlTrans> findCnlTransByPage(QueryPage queryPage);
	
	public IPage<CnlTrans> findCnlTransBySql(QueryPage queryPage,CnlTrans cnlTrans);
	
	public void saveOrUpdate(CnlTrans cnlTrans);

	public CnlTrans load(String id);
	
	public List<CnlTrans> findTrans(Date startTime,Date endTime);
	
	public void updateStatus(List<CnlTrans> list);

	public void updateStatus2(List<CnlTrans> list);
	
	public void saveBatch(List<CnlTrans> list);
	
	public List<Reconciliation> getList(String cnlCustomerCode,String typeCode,String pageSize,String pageIndex);

	public CnlTrans findTrans(String stlNum);
	
	public int listCount(String cnlCustomerCode,String typeCode);

	public List<CnlTrans> findTransDtl(String stlNum);
	
	public SumAmountDto sumAmount(String custCode,Date startTime,Date endTime,Date startWeek,Date endWeek,Date startMonth,Date startYear);
	
	public List<CnlTrans> findCnlTrans();
	
	public void saveCnlTrans3m(Collection<CnlTrans3m> cnlTrans3mList);
	
	public boolean deleteCnlTrans();
}
