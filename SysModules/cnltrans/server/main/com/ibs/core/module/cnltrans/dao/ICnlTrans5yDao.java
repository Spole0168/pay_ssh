/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.cnltrans.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.cnltrans.domain.CnlTrans5y;
import com.ibs.core.module.cnltrans.domain.CnlTrans5yHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_5Y
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlTrans5yDao {

	public IPage<CnlTrans5y> findCnlTrans5yByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlTrans5y cnlTrans5y);

	public CnlTrans5y load(String id);
	
	public List<CnlTrans5y> findCnlTrans5y();
	
	public void saveCnlTrans5yHis(Collection<CnlTrans5yHis> cnlTrans5yHisList);
	
	public boolean deleteCnlTrans5y();
	
}
