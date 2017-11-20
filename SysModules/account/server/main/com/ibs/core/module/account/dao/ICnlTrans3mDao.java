/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.account.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.cnltrans.domain.CnlTrans5y;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_3M
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTrans3mDao {

	public IPage<CnlTrans3m> findCnlTrans3mByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlTrans3m cnlTrans3m);

	public CnlTrans3m load(String id);

	public CnlTrans3m findCnlTran3m(CnlTrans3m cnlTrans3m);
	
	
	/**
	 * 根据条件查询交易流水
	 * @author jicheng
	 * @param cnlCustCode
	 * @param startTransTime
	 * @param endTransTime
	 * @param transType
	 * @return
	 */

	public CnlTrans3m queryCnlTrans3m(String cnlCustCode, String startTransTime, String endTransTime, String transType);
	
	public List<CnlTrans3m> findCnlTrans3m();
	
	public void saveCnlTrans5y(Collection<CnlTrans5y> cnlTrans5yList);
	
	public boolean deleteCnlTrans3m();
	
}
