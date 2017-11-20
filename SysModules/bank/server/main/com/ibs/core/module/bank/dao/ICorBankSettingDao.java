/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.bank.dao;

import com.ibs.core.module.bank.domain.CorBankSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorBankSettingDao {
	////
	public IPage<CorBankSetting> findCorBankSettingByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorBankSetting corBankSetting);

	public CorBankSetting load(String id);
	
	public CorBankSetting findByValue(String bankAcntCode);
	
}
