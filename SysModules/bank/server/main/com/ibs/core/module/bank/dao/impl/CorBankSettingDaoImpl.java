/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.bank.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.core.module.bank.dao.ICorBankSettingDao;
import com.ibs.core.module.bank.domain.CorBankIntf;
import com.ibs.core.module.bank.domain.CorBankSetting;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankSettingDaoImpl extends BaseEntityDao<CorBankSetting> implements
		ICorBankSettingDao {

	public IPage<CorBankSetting> findCorBankSettingByPage(QueryPage queryPage) {
		logger.info("entering action::CorBankSettingDaoImpl.findCorBankSettingByPage()...");
		return super.findPageBy(queryPage);
	}
	////
	public CorBankSetting load(String id) {
		logger.info("entering action::CorBankSettingDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorBankSetting corBankSetting) {
		logger.info("entering action::CorBankSettingDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corBankSetting);
	}

	public CorBankSetting findByValue(String bankAcntCode){
		String  hql="select intf  from CorBankSetting intf  where intf.bankInnerCode=? and intf.isValid=?";
		
		List<Object> obj=new ArrayList<Object>();
		obj.add(bankAcntCode);
		obj.add(Constants.IS_VALID_VALID);
		
		List<CorBankSetting> list=super.find(hql,obj);
		
		return list.isEmpty()?null:list.get(0);
	} 

}
