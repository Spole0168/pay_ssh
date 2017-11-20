/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.biz;
import com.ibs.core.module.bank.domain.CorBankMsg;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankMsgBiz {

	public IPage<CorBankMsg> findCorBankMsgByPage(QueryPage queryPage);

	public CorBankMsg getCorBankMsgById(String id);
	
	public CorBankMsg saveCorBankMsg(CorBankMsg object);
	
	public CorBankMsg updateCorBankMsg(CorBankMsg object);
	
	public void exportCorBankMsg(ExportSetting exportSetting);

	/** 根据报文编码和 渠道编码  查询报文信息
	 * @param msgCode
	 * @param cnlCnlCode
	 * @return
	 */
//	public String findByMsgCode(String msgCode, String bankPmtCnlCode);

	public String findByMsgCode(String msgCode);

}
