package com.ibs.common.module.frameworkimpl.log.dao;

import java.util.Date;
import java.util.List;

import com.ibs.common.module.frameworkimpl.log.domain.ActionLog;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;


public interface IActionLogDao {
	
	public ActionLog getActionLog(String id);

	public void saveActionLog(ActionLog actionLog);

	public IPage<ActionLog> findByPage(QueryPage queryPage);
	
	public List<ActionLog> queryClientMacs(int batchSize, Date startTime, Date endTime);
}
