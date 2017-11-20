package com.ibs.common.module.frameworkimpl.log.biz;

import com.ibs.common.module.frameworkimpl.log.domain.ActionLog;
import com.ibs.common.module.frameworkimpl.log.domain.BizLog;

public interface ILogBiz {
	public void saveActionLog(ActionLog actionLog);
	
	public void saveBizLog(BizLog bizLog);
}
