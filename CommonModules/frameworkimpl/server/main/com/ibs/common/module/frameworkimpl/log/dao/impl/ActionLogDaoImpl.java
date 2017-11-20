package com.ibs.common.module.frameworkimpl.log.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;

import com.ibs.common.module.frameworkimpl.log.dao.IActionLogDao;
import com.ibs.common.module.frameworkimpl.log.domain.ActionLog;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class ActionLogDaoImpl extends BaseEntityDao<ActionLog> implements IActionLogDao {

	public ActionLog getActionLog(String id) {
		return super.load(id);
	}

	public void saveActionLog(ActionLog actionLog) {
		super.save(actionLog);
	}

	public IPage<ActionLog> findByPage(QueryPage queryPage) {
		return super.findPageBy(queryPage);
	}

	public List<ActionLog> queryClientMacs(int batchSize, Date startTime, Date endTime) {
		logger.trace("entering dao...");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct ");
		sql.append("o.USER_CODE as userCode, ");
		sql.append("o.USER_NAME as userName, ");
		sql.append("o.ORG_CODE as orgCode, ");
		sql.append("o.ORG_NAME as orgName, ");
		sql.append("o.CLIENT_MAC as clientMac ");
		sql.append(" from pbl.T_PBL_ACTION_LOG o ");
		sql.append(" where o.CLIENT_MAC is not null and o.REQUEST_TIME >= ? and o.REQUEST_TIME < ?");
		
		List<Object> args = new ArrayList<Object>();
		args.add(startTime);
		args.add(endTime);
		
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		
		scallarList.add(new ColumnType("userCode", Hibernate.STRING));
		scallarList.add(new ColumnType("userName", Hibernate.STRING));
		scallarList.add(new ColumnType("orgCode", Hibernate.STRING));
		scallarList.add(new ColumnType("orgName", Hibernate.STRING));
		scallarList.add(new ColumnType("clientMac", Hibernate.STRING));

		return super.findBySql(sql.toString(), args, null, scallarList, ActionLog.class);

	}

}
