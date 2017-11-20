package com.ibs.core.module.account.biz;

import com.ibs.core.module.account.domain.CustPersonalInfo;

public interface ICustPersonalInfoBiz {
	public CustPersonalInfo findByCustid(String id);
}
