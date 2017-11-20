package com.ibs.portal.framework.server.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ibs.portal.framework.server.dao.IJdbcDao;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.PageFooterColumn;
import com.ibs.portal.framework.server.metadata.QueryPage;

/**
 * JDBC数据操作实现,注入dataSource
 * 
 * @author
 * 
 */
public class BaseJdbcDao extends JdbcDaoSupport implements IJdbcDao {
	/**
	 * 日志工具类, 使用如:
	 * 
	 * <pre>
	 * if (logger.isDebugEnabled())
	 * 	logger.debug(&quot;error id : {0}&quot;, id);
	 * </pre>
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public IPage<?> queryByPage(String sql, List<Object> args, int pageSize,
			int pageIndex, RowMapper rowMapper) throws DaoException {
		
		QueryPage queryPage = new QueryPage(pageSize, pageIndex);
		queryPage.setSqlString(sql);
		if (args != null) {
			for (Object arg : args) {
				queryPage.addQueryCondition(null, arg);
			}
		}
		queryPage.setRowMapper(rowMapper);
		
		return queryByPage(queryPage);
	}

	public IPage<?> queryByPage(QueryPage queryPage)
			throws DaoException {
		
		String sql = queryPage.getSqlString();
		List args = queryPage.getAllNotNullArg();
		int pageSize = queryPage.getPageSize();
		int pageIndex = queryPage.getPageIndex();
		RowMapper rowMapper = queryPage.getRowMapper();
		boolean calCount = queryPage.isCalCount();
		Map<String, String> sortMap = queryPage.getSortMap();
		
		List<PageFooterColumn> footers = new ArrayList<PageFooterColumn>();
		List<Object> params = new ArrayList<Object>();
		
		if (args != null && args.size() > 0) {
			for (Object arg : args) {
				if ( (arg instanceof PageFooterColumn) )
					footers.add((PageFooterColumn) arg);
				else
					params.add(arg);
			}
		}
		
		// 汇总
		Map<String, Object> aggregations = null;
		Integer total = pageSize;
		if (calCount) {
			aggregations = aggregate(sql, params, footers);
			total = Integer.valueOf(aggregations.get(COUNT).toString());
		}

		// 如果有排序条件，在sqlString后添加order by
		StringBuffer sbSql = new StringBuffer(sql);
		if (sortMap != null && !sortMap.isEmpty()) {
			sbSql.append(ORDER_BY);
			int i = 0;
			for (Iterator it = sortMap.keySet().iterator(); it
					.hasNext();) {
				Object o = it.next();
				String fieldName = o.toString();
				String orderType = sortMap.get(o.toString())
						.toString();

				if (i > 0) {
					sbSql.append(COMMA);
				}

				if (ASC.equalsIgnoreCase(orderType)) {
					sbSql.append(fieldName)
							.append(SPACE).append(ASC);
				} else {
					sbSql.append(fieldName)
							.append(SPACE).append(DESC);
				}
				i++;
			}
			
			sbSql.append(COMMA).append("sys_guid()");
		}

		// 翻页
		int newPageIndex = pageIndex;
		if (calCount) {
			// edit by huolang 2011-01-14
			// 修正翻页后无法回到最后一页问题
			if (pageIndex < 0) {
				newPageIndex = 0;
			}
			else if (total <= pageSize * (pageIndex)) {
				if (total > 0)
					newPageIndex = (total + pageSize - 1) / pageSize - 1;
			}
		}
		
		int firstResult = pageSize * (newPageIndex) + 1;
		params.add(firstResult); // FirstResult
		params.add(pageSize + firstResult); // MaxResults

		sql = "select * from ( select TMP_RESULT.*, ROWNUM RN from (" + sql
				+ ") TMP_RESULT ) where RN >= ? and RN<= ? ";

		Page page = new Page(
				getJdbcTemplate().query(sql, params.toArray(), rowMapper), total,
				pageSize, newPageIndex);
		page.setUserdata(aggregations);
		
		return page;
	}
	
	public int count(String sql, List<Object> args) throws DaoException {
		String countSql = "select count(*) from (" + sql + ")";
		
		if(logger.isDebugEnabled())
			logger.debug("countSql:" + countSql);
		
		if (null != args)
			return getJdbcTemplate().queryForInt(countSql, args.toArray());
		else
			return getJdbcTemplate().queryForInt(countSql);
	}
	
	public Map<String, Object> aggregate(String sql, List<Object> args, List<PageFooterColumn> footers) {
		StringBuilder aggSql = new StringBuilder();
		aggSql.append("select count(*) as ");
		aggSql.append(COUNT).append(SPACE);
		if (footers != null) {
			for (PageFooterColumn column : footers) {
				aggSql.append(",").append(column.getAggExpression()).append(" as ").append(column.getName());
			}
		}
		aggSql.append(" from (").append(sql).append(")");
		
		if(logger.isDebugEnabled())
			logger.debug("aggSql:" + aggSql.toString());
		
		Map<String, Object> map = this.getJdbcTemplate().queryForMap(aggSql.toString(), args.toArray());
		if (footers != null) {
			for (PageFooterColumn column : footers) {
				String key = column.getName().toUpperCase();
				Object value = map.get(key);
				map.remove(key);
				map.put(column.getName(), value);
			}
		}
		
		return map;
	}

	// 实现SQL查询
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> query(String sql, List<Object> args)
			throws DaoException {
		return (List<Map<String, Object>>) getJdbcTemplate().query(sql,
				args == null ? new Object[0] : args.toArray(),
				new ColumnMapRowMapper());
	}

	public List<?> query(String sql, List<Object> args,RowMapper rowMapper)
			throws DaoException {
		return getJdbcTemplate().query(sql,
				args == null ? new Object[0] : args.toArray(),rowMapper);
	}

	public void call(final String procedureName, final List<Object> args)
			throws DaoException {
		getJdbcTemplate().execute(new CallableStatementCreator() {
			public CallableStatement createCallableStatement(
					Connection connection) throws SQLException {
				CallableStatement stmt = connection.prepareCall(procedureName);
				if (args != null && args.size() > 0) {
					for (int i = 0, n = args.size(); i < n; i++) {
						stmt.setObject(i, args.get(i));
					}
				}
				return stmt;
			}
		}, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement stmt)
					throws SQLException, DataAccessException {
				stmt.execute();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> callQuery(final String procedureName,
			final List<Object> args) throws DaoException {
		return (List<Map<String, Object>>) getJdbcTemplate().execute(
				new CallableStatementCreator() {
					public CallableStatement createCallableStatement(
							Connection connection) throws SQLException {
						CallableStatement stmt = connection
								.prepareCall(procedureName);
						if (args != null && args.size() > 0) {
							for (int i = 0, n = args.size(); i < n; i++) {
								stmt.setObject(i, args.get(i));
							}
						}
						return stmt;
					}
				}, new CallableStatementCallback() {
					public Object doInCallableStatement(CallableStatement stmt)
							throws SQLException, DataAccessException {
						List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
						ResultSet rs = stmt.executeQuery();
						ResultSetMetaData meta = rs.getMetaData();
						int col = meta.getColumnCount();
						while (rs.next()) {
							Map<String, Object> map = new HashMap<String, Object>();
							for (int i = 0; i < col; i++) {
								map
										.put(meta.getCatalogName(i), rs
												.getObject(i));
							}
							results.add(map);
						}
						return results;
					}
				});
	}

	// 实现存储过程调用
	@SuppressWarnings("unchecked")
	public Map<String, Object> callOut(final String procedureName,
			final List<Object> args) throws DaoException {
		return (Map<String, Object>) getJdbcTemplate().call(
				new CallableStatementCreator() {
					public CallableStatement createCallableStatement(
							Connection connection) throws SQLException {
						return connection.prepareCall(procedureName);
					}
				}, args);
	}

}
