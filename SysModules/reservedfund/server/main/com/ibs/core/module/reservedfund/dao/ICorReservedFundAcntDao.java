/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.reservedfund.dao;

import java.util.List;

import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_RESERVED_FUND_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorReservedFundAcntDao {

	public IPage<CorReservedFundAcnt> findCorReservedFundAcntByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorReservedFundAcnt corReservedFundAcnt);

	public CorReservedFundAcnt load(String id);
	
	public List<CorReservedFundAcnt> findAll();

	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 根据银行编号得到备付金信息
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public IPage<CorReservedFundAcnt> findByBankNum(QueryPage queryPage,String bankInnerCode);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 修改 检验银行卡号是否存在
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum,String id);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 新增 检验银行卡号是否存在
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public boolean CheckBankNum(String bankInnerCode,String bankCardNum);
	/*
	 * @created by	: xbh
	 * @version 	: 1.0
	 * @comments	: 删除备付金信息
	 * @modify		: your comments goes here (author,date,reason).
	 */
	public void deleteById(String id);
	
}
