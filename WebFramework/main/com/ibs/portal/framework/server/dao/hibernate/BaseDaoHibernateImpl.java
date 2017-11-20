package com.ibs.portal.framework.server.dao.hibernate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibs.portal.framework.server.dao.IDao;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.PageFooterColumn;

/**
 * Hibernate 通用方法
 * @author 
 */
public abstract class BaseDaoHibernateImpl extends HibernateDaoSupport implements IDao{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public int execute(final String queryString) throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						return query.executeUpdate();
					}
				});
	}

	public int execute(final String hql, final List<Object> args)
			throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						if (args != null && args.size() > 0) {
							Object[] values = args.toArray();
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						return query.executeUpdate();
					}
				});
	}

	public int execute(final String hql, final Map<String, Object> args)
			throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						// important! args value could not be null
						query.setProperties(args);
						return query.executeUpdate();
					}
				});
	}

	public int execute(final String hql, final Map<String, Object> parameters, final Map<String, Object> properties)
			throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						if (parameters != null) {
							for (Map.Entry<String, Object> entry : parameters.entrySet()) {
								query.setParameter(entry.getKey(), entry.getValue());
							}
						}
						if (properties != null) {
							query.setProperties(properties);
						}
						return query.executeUpdate();
					}
				});
	}

	public int executeSql(final String sqlString) throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sqlString);
						return query.executeUpdate();
					}
				});
	}

	public int executeSql(final String sql, final List<Object> args)
			throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						if (args != null && args.size() > 0) {
							Object[] values = args.toArray();
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						return query.executeUpdate();
					}
				});
	}

	public int executeSql(final String sql, final Map<String, Object> args)
			throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						// important! args value could not be null
						query.setProperties(args);
						return query.executeUpdate();
					}
				});
	}

	public int executeSql(final String sql, final Map<String, Object> parameters, final Map<String, Object> properties)
			throws DaoException {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						
						SQLQuery query = session.createSQLQuery(sql);

						if (parameters != null) {
							for (Map.Entry<String, Object> entry : parameters.entrySet()) {
								query.setParameter(entry.getKey(), entry.getValue());
							}
						}
						if (properties != null) {
							query.setProperties(properties);
						}
						
						return query.executeUpdate();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> aggregate(final String queryString, final List<Object> params, final List<PageFooterColumn> footers) {
		
		return (Map<String, Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery(getCountString(queryString, footers));
						
						if (params != null && params.size() > 0) {
							int i = 0;
							for (Object param : params) {
								query.setParameter(i++, param);
							}
						}
						
						List list = query.list();
						String[] names = query.getReturnAliases();
						
						Map<String, Object> result = new HashMap<String, Object>();
						for (int i = 0; i < names.length; ++i) {

							if (names.length > 1)
								result.put(names[i], ((Object[])list.get(0))[i]);
							else
								result.put(names[i], list.get(0));
						}
						
						return result;
					}
				});
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> aggregate(final String queryString, final Map<String, Object> params, final Map<String, PageFooterColumn> footers) {
		
		return (Map<String, Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery(getCountString(queryString, footers.values()));
						
						query.setProperties(params);
						
						List list = query.list();
						String[] names = query.getReturnAliases();
						
						Map<String, Object> result = new HashMap<String, Object>();
						for (int i = 0; i < names.length; ++i) {

							if (names.length > 1)
								result.put(names[i], ((Object[])list.get(0))[i]);
							else
								result.put(names[i], list.get(0));
						}
						
						return result;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> aggregateBySql(final String sqlString, final List<Object> params, final List<PageFooterColumn> footers)
			throws DaoException {
		return (Map<String, Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session
								.createSQLQuery(getCountStringSql(sqlString, footers));
						
						if (params != null && params.size() > 0) {
							int i = 0;
							for (Object arg : params) {
								query.setParameter(i++, arg);
							}
						}
						
						int size = 1;
						if (footers != null)
							size += footers.size();
						String[] names = new String[size];
						names[0] = COUNT;
						query.addScalar(COUNT, Hibernate.INTEGER);
						if (footers != null) {
							for ( int i = 0; i < footers.size(); ++i ) {
								PageFooterColumn footer = footers.get(i);
								names[i + 1] = footer.getName();
								query.addScalar(footer.getName(), footer.getType());
							}
						}
						
						List list = query.list();
						
						Map<String, Object> result = new HashMap<String, Object>();
						for (int i = 0; i < names.length; ++i) {

							if (names.length > 1)
								result.put(names[i], ((Object[])list.get(0))[i]);
							else
								result.put(names[i], list.get(0));
						}
						
						return result;
					}
				});
		
	}

	public int count(final String queryString, final List<Object> args)
			throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery(getCountString(queryString, null));
						if (args != null && args.size() > 0) {
							int i = 0;
							for (Object arg : args) {
								if ( !(arg instanceof PageFooterColumn) )
									query.setParameter(i++, arg);
							}
						}
						return query.uniqueResult();
					}
				})).intValue();
	}

	public int count(final String queryString, final Map<String, Object> args)
			throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery(getCountString(queryString, null));
						query.setProperties(args);
						return query.uniqueResult();
					}
				})).intValue();
	}

	public int countBySql(final String sqlString, final List<Object> args)
			throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session
								.createSQLQuery(getCountStringSql(sqlString, null));
						if (args != null && args.size() > 0) {
							int i = 0;
							for (Object arg : args) {
								query.setParameter(i++, arg);
							}
						}
						return query.uniqueResult();
					}
				})).intValue();
	}

	/**
	 * 周祥增加,运行Count SQL，并且返回结果
	 * @param 
	 * String sqlString
	 * List<Object> args
	 */	
	public int countByCountSql(final String sqlString, final List<Object> args)
	throws DaoException {
		return ((Number) getHibernateTemplate().execute(
		new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sqlString);
				if (args != null && args.size() > 0) {
					int i=0;
					for(Object arg:args){
						query.setParameter(i++, arg);
					}
				}
				return query.uniqueResult();
			}
		})).intValue();
	}
	
	protected String getCountString(final String queryString, final Collection<PageFooterColumn> footers) {
		StringBuffer agg = new StringBuffer();
		if (footers != null) {
			for (PageFooterColumn column : footers) {
				agg.append(",").append(column.getAggExpression()).append(" as ").append(column.getName());
			}
		}
		
		String tmp = queryString.trim();

		if (queryString.indexOf("order by") != -1)
			tmp = tmp.substring(0, tmp.indexOf("order by") - 1);

		if (tmp.toLowerCase().startsWith("from ")) {
			return " select count(*) as " + COUNT + SPACE + agg.toString() + SPACE + tmp;
		}
		if (!tmp.toLowerCase().startsWith("select"))
			throw new RuntimeException(" the query not valid [" + queryString
					+ "]");
		int pos = queryString.toLowerCase().indexOf(" from ");
		if (pos == -1)
			throw new RuntimeException("the query not valid [" + queryString
					+ "]");

		final String where = tmp.substring(pos);
		// 思路 1 截取select 与 from 之间 第一个逗号(,)的字符串
		// 直接使用count(*)
		// final String suffix = tmp.substring(7, pos);
		// String token[] = suffix.split(",");
		// String cntField = token[0];

		// return " select count(" + cntField + ") " + where;
		return " select count(*) as " + COUNT + SPACE + agg.toString() + where;
	}

	protected String getCountStringSql(String sqlString, final Collection<PageFooterColumn> footers) {
		StringBuffer agg = new StringBuffer();
		if (footers != null) {
			for (PageFooterColumn column : footers) {
				agg.append(",").append(column.getAggExpression()).append(" as ").append(column.getName());
			}
		}
		
		return "select count(*) as " + COUNT + SPACE + agg.toString() + " from (" + sqlString + ")";
	}
	
	protected String retrieveAlias(String sql) {
		char[] chs = sql.toCharArray();
		int bracket = 0;
		int fromIdx = -1;
		
		// 查找关键字from
		for(int i = 0; i < chs.length; ++i) {
			if (chs[i] == '(') {
				bracket -= 1;
			} else if (chs[i] == ')') {
				bracket += 1;
			}
			
			if (bracket != 0)
				continue;
			
			if (chs[i] == ' ' 
				|| chs[i] == '\t'
				|| chs[i] == '\r'
				|| chs[i] == '\n') {
				continue;
			}
			
			if ( bracket == 0 && i + 4 < chs.length &&
				(chs[i] == 'f' || chs[i] == 'F') &&
				(chs[i+1] == 'r' || chs[i+1] == 'R') &&
				(chs[i+2] == 'o' || chs[i+2] == 'O') &&
				(chs[i+3] == 'm' || chs[i+3] == 'M') &&
				(chs[i+4] == ' ' || chs[i+4] == '\t')) {
				fromIdx = i;
				break;
			}
		}
		
		if (fromIdx < 0)
			return null;
		
		// 查找别名
		bracket = 0;
		int tblBegIdx = 0;
		int tblEndIdx = 0;
		for (int i = fromIdx + 4; i < chs.length; ++i) {
			// 子查询
			if (chs[i] == '(') {
				if (bracket == 0)
					tblBegIdx = i;
				bracket -= 1;
			} else if (chs[i] == ')') {
				bracket += 1;
				if (bracket == 0) {
					tblEndIdx = i;
					break;
				}
			}
			
			if (bracket != 0)
				continue;
			
			// 表名
			if (tblBegIdx == 0) {
				if (chs[i] == ' ' 
					|| chs[i] == '\t'
					|| chs[i] == '\r'
					|| chs[i] == '\n'
					|| chs[i] == ',') {
					continue;
				}
				
				tblBegIdx = i;
				continue;
			} else {
				if (chs[i] == ' ' 
					|| chs[i] == '\t'
					|| chs[i] == '\r'
					|| chs[i] == '\n'
					|| chs[i] == ',') {
					tblEndIdx = i - 1;
					break;
				}
			}
		}
		
		if (tblEndIdx == 0)
			return null;
		
		int aliasBegIdx = 0;
		int aliasEndIdx = 0;
		for (int i = tblEndIdx + 1; i < chs.length; ++i) {
			if (aliasBegIdx == 0) {
				if (chs[i] == ' ' 
					|| chs[i] == '\t'
					|| chs[i] == '\r'
					|| chs[i] == '\n'
					|| chs[i] == ',') {
					continue;
				}
				
				aliasBegIdx = i;
			} else {
				if (chs[i] == ' ' 
					|| chs[i] == '\t'
					|| chs[i] == '\r'
					|| chs[i] == '\n'
					|| chs[i] == ',') {
					aliasEndIdx = i;
					break;
				}
			}
		}
		
		String alias = null;
		if (aliasBegIdx > 0) {
			if (aliasEndIdx > aliasBegIdx)
				alias = sql.substring(aliasBegIdx, aliasEndIdx);
			else if (aliasEndIdx == 0)
				alias = sql.substring(aliasBegIdx);
		}
		
		if ("where".equalsIgnoreCase(alias)
				|| "order".equals(alias)
				|| "union".equals(alias)
			)
			return null;
		
		return alias;
	}
	
}
