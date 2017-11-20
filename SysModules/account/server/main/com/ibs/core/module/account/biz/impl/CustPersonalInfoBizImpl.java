package com.ibs.core.module.account.biz.impl;

import com.ibs.core.module.account.biz.ICustPersonalInfoBiz;
import com.ibs.core.module.account.dao.ICustPersonalInfoDao;
import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.cnlcust.dao.ICnlCustDao;
import com.ibs.portal.framework.server.biz.BaseBiz;

public class CustPersonalInfoBizImpl extends BaseBiz implements ICustPersonalInfoBiz{

	private ICustPersonalInfoDao custPersonalInfoDao;
	public CustPersonalInfo findByCustid(String id){
		return custPersonalInfoDao.findByCustid(id);
	}
	
	
	
	
	
	public ICustPersonalInfoDao getCustPersonalInfoDao() {
		return custPersonalInfoDao;
	}
	public void setCustPersonalInfoDao(ICustPersonalInfoDao custPersonalInfoDao) {
		this.custPersonalInfoDao = custPersonalInfoDao;
	}
	
	
}
