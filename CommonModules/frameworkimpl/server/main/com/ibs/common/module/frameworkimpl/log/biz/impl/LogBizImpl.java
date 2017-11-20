package com.ibs.common.module.frameworkimpl.log.biz.impl;

import com.ibs.common.module.frameworkimpl.log.biz.ILogBiz;
import com.ibs.common.module.frameworkimpl.log.dao.IActionLogDao;
import com.ibs.common.module.frameworkimpl.log.dao.IBizLogDao;
import com.ibs.common.module.frameworkimpl.log.domain.ActionLog;
import com.ibs.common.module.frameworkimpl.log.domain.BizLog;
import com.ibs.portal.framework.server.biz.BaseBiz;

/**
 * @author 
 * $Id: LogBizImpl.java 7767 2010-10-06 12:54:11Z  $
 */
public class LogBizImpl extends BaseBiz implements ILogBiz {

	private IActionLogDao actionLogDao;
	private IBizLogDao bizLogDao;

	// *************************  get/set methods ************************* //

	public IActionLogDao getActionLogDao() {
		return actionLogDao;
	}

	public void setActionLogDao(IActionLogDao actionLogDao) {
		this.actionLogDao = actionLogDao;
	}

	public IBizLogDao getBizLogDao() {
		return bizLogDao;
	}

	public void setBizLogDao(IBizLogDao bizLogDao) {
		this.bizLogDao = bizLogDao;
	}

	// *************************  Biz methods ************************* //
	
	public void saveActionLog(ActionLog actionLog) {
		actionLogDao.saveActionLog(actionLog);
	}

	public void saveBizLog(BizLog bizLog) {
		bizLogDao.saveBizLog(bizLog);
	}

}
