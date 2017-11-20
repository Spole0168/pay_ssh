/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.interfaces.dao;

import java.util.Collection;
import java.util.List;

import com.ibs.core.module.cnltrans.domain.CnlMsgHis;
import com.ibs.core.module.interfaces.domain.CnlMsg;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICnlMsgDao {

	public IPage<CnlMsg> findCnlMsgByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CnlMsg cnlMsg);

	public CnlMsg load(String id);
	public List<CnlMsg> findByMsgCode(String msgCode);
	
	public CnlMsg saveCnlMsg( CnlMsg cnlMsg);
	public CnlMsg findByMsgCodeCnlCnlCode(String msgCode,String cnlCnlCode);
	
	public List<CnlMsg> findCnlMsg();
	
	public void saveCnlMsgHis(Collection<CnlMsgHis> cnlMsgHisList);
	
	public boolean deleteCnlMsg();
}
