/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.bank.dao;
import java.util.Collection;
import java.util.List;

import com.ibs.core.module.bank.domain.CorBankMsg;
import com.ibs.core.module.bank.domain.CorBankMsgHis;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankMsgDao {

	public IPage<CorBankMsg> findCorBankMsgByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorBankMsg corBankMsg);

	public CorBankMsg load(String id);
	
	public List<CorBankMsg> findCorBankMsg();
	
	public void saveCorBankMsgHis(Collection<CorBankMsgHis> corBankMsgHisList);
	
	public boolean deleteCorBankMsg();

	/** 根据报文编码 和 支付渠道编码 查询 一条记录
	 * @param msgCode
	 * @param bankPmtCnlCode
	 * @return
	 */
//	public CorBankMsg findByMsgCodeCnlCnlCode(String msgCode, String bankPmtCnlCode);
	public CorBankMsg findByMsgCodeCnlCnlCode(String msgCode);
}
