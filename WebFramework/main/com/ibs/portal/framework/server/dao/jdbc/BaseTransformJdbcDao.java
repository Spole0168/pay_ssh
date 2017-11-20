package com.ibs.portal.framework.server.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

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
public abstract class BaseTransformJdbcDao extends BaseJdbcDao {

	@SuppressWarnings("unchecked")
	public IPage<?> queryByPage(String sql, List<Object> args,
			int pageSize, int pageIndex, RowMapper rowMapper
			, final Map<String, String> sortMap) throws DaoException {
		
		QueryPage queryPage = new QueryPage(pageSize, pageIndex);
		queryPage.setSqlString(sql);
		queryPage.setRowMapper(rowMapper);
		if (args != null) {
			for (Object arg : args) {
				if(arg==null){
					arg="";
				}
				queryPage.addQueryCondition(null, arg);
			}
		}
		queryPage.setSortMap(sortMap);
		
		return queryByPage(queryPage);
		
	}
	
	
	public IPage<?> queryByPage(QueryPage queryPage) throws DaoException {
		
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
		Map<String,String> colMap = this.getColMap();
		if (sortMap != null && !sortMap.isEmpty()) {
			sbSql.append(ORDER_BY);
			int i = 0;
			for (Iterator it = sortMap.keySet().iterator(); it
					.hasNext();) {
				Object o = it.next();
				String fieldName = o.toString();
				if (colMap.containsKey(o.toString())){
					fieldName = colMap.get(o.toString());
				}
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
		
//		if(null == params)
//			args = new ArrayList<Object>();

		int firstResult = pageSize * (newPageIndex) + 1;
		params.add(firstResult); // FirstResult
		params.add(pageSize + firstResult); // MaxResults

		sql = "select * from ( select TMP_RESULT.*, ROWNUM RN from (" + sbSql.toString()
				+ ") TMP_RESULT ) where RN >= ? and RN<= ? ";

		Page page = new Page(
				getJdbcTemplate().query(sql, params.toArray(), rowMapper), total,
				pageSize, newPageIndex);
		page.setUserdata(aggregations);
		
		return page;

	}
	
	/**
	 * 取得实体Bean和SQL字段之间的Mapping关系
	 * 
	 * @return Map<实体Bean字段名，SQL字段名>
	 */
	protected abstract Map<String,String> getColMap();
	
}
