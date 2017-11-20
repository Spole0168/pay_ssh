package com.ibs.portal.framework.server.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.ReflectionUtils;

import com.ibs.portal.framework.server.dao.IGenericDao;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.PageFooterColumn;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/**
 * 任意类型实体持久化操作基类.
 * 
 * 没有提供的方法，可用getHibernateTemplate()取得HibernateTemplate实例，直接调用其API.
 * 如果DAO中主要操作同一种实体对象，建议使用<code>BaseEntityDao</code>，以省略每个方法中的Class参数。
 * 
 * @see com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao
 * @author
 * 
 */
public abstract class BaseGenericDao extends BaseDaoHibernateImpl implements IGenericDao{
	
	/**
	 * 日志工具类, 使用如:
	 * <pre>
	 * if (logger.isDebugEnabled())
	 *     logger.debug("error id : {0}", id);
	 * </pre>
	 */
//	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected boolean cacheQueries = true; // query cache
	protected String queryCacheRegion;;

	protected void initDao() throws Exception {
		super.initDao();
		getHibernateTemplate().setCacheQueries(cacheQueries);
		if (null != queryCacheRegion) {
			getHibernateTemplate().setQueryCacheRegion(queryCacheRegion);
		}
	}

	/**
	 * 周祥增加,按照参数来做HQL分页查询并且返回对象数组列表
	 * @param <T>
	 */		
	@SuppressWarnings("unchecked")
	public IPage<?> findPage(final String queryString, final List<Object> args,
			final int pageSize, final int pageIndex) throws DaoException {
		return (IPage) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(queryString);
				
				//处理删除最后一页的最后一条数据之后，返回页数据为空的问题
				Integer total = (Integer)count(queryString, args);
				// edit by huolang 2011-01-14
				// 修正翻页后无法回到最后一页问题
				int newPageIndex = pageIndex;
				if (pageIndex < 0) {
					newPageIndex = 0;
				}
				else if (total <= pageSize * (pageIndex)) {
					if (total > 0)
						newPageIndex = (total+pageSize-1) / pageSize -1;
				}	
				
				query.setFirstResult(pageSize * (newPageIndex));
				query.setMaxResults(pageSize);
				if (args != null && args.size() > 0) {
					Object[] values = args.toArray();
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return new Page(query.list(),total,pageSize, newPageIndex);
			}
		});
	}
	/**
	 * 周祥增加,根据参数做HQL查询并且返回DTO对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHql(final String hqlString,final List<Object> args,final Map<String,String> sortMap,final Class<T> dtoClass) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				//如果有排序条件，在hqlString后添加order by
				StringBuffer newHqlString = new StringBuffer(hqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newHqlString.append(ORDER_BY);
					int i=0;
					for (Iterator it = sortMap.keySet().iterator(); it.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();
						
						if(i>0){
							newHqlString.append(COMMA);
						}
						
						if (ASC.equalsIgnoreCase(orderType)) {
							newHqlString.append(fieldName).append(SPACE).append(ASC);
						} else {
							newHqlString.append(fieldName).append(SPACE).append(DESC);
						}
						i++;
					}
				}
				
				Query query = session.createQuery(newHqlString.toString());

				if (args != null && args.size() > 0) {
					int i=0;
					for(Object arg:args){
						query.setParameter(i++, arg);
					}
				}
				if(dtoClass!=null)query.setResultTransformer( Transformers.aliasToBean(dtoClass));
				
				return query.list();
			}
		});
	}	
	
	/**
	 * 周祥增加,根据参数做HQL查询并且返回HQL对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHql(final String hqlString,final List<Object> args,final Map<String,String> sortMap) {
		return this.findByHql(hqlString, args, sortMap, null);
	}	
	
	/**
	 * 周祥增加,按照参数来做HQL分页查询并且返回DTO对象列表
	 * @param <T>
	 */	
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(final String hqlString, final List<Object> args,
			final int pageSize, final int pageIndex, final Map<String,String> sortMap, final Class<T> dtoClass) throws DaoException {
		
		QueryPage queryPage = new QueryPage(pageSize, pageIndex);
		queryPage.setHqlString(hqlString);
		queryPage.setSortMap(sortMap);
		if (args != null) {
			for (Object arg : args) {
				queryPage.addQueryCondition(null, arg);
			}
		}
		queryPage.setDtoClass(dtoClass);
		
		return this.findPageByHql(queryPage);
		
	}
	
	/**
	 * 周祥增加,按照QueryPage的参数来做HQL分页查询并且返回DTO对象列表
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(QueryPage queryPage,final Class<T> dtoClass) throws DaoException {
	
		queryPage.setDtoClass(dtoClass);
		return findPageByHql(queryPage);
		
	}
	
	/**
	 * 周祥增加,按照参数来做HQL分页查询并且返回HQL对象列表
	 * @param <T>
	 */	
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(final String hqlString, final List<Object> args,
			final int pageSize, final int pageIndex, final Map<String,String> sortMap) throws DaoException {
		
		QueryPage queryPage = new QueryPage(pageSize, pageIndex);
		queryPage.setHqlString(hqlString);
		queryPage.setSortMap(sortMap);
		if (args != null) {
			for (Object arg : args) {
				queryPage.addQueryCondition(null, arg);
			}
		}
		
		return this.findPageByHql(queryPage);
		
	}
	
	/**
	 * 周祥增加,按照QueryPage的参数来做HQL分页查询并且返回HQL对象列表
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(QueryPage queryPage) throws DaoException {
		
		final int pageSize = queryPage.getPageSize();
		final int pageIndex = queryPage.getPageIndex();
		final boolean calCount = queryPage.isCalCount();
		final Map<String, String> sortMap = queryPage.getSortMap();
		final String hqlString = queryPage.getHqlString();
		final List<Object> args = queryPage.getAllNotNullArg();
		final Class<?> dtoClass = queryPage.getDtoClass();
		
		return (IPage) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//如果有排序条件，在hqlString后添加order by
				StringBuffer newHqlString = new StringBuffer(hqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newHqlString.append(ORDER_BY);
					int i=0;
					for (Iterator it = sortMap.keySet().iterator(); it.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();
						
						if(i>0){
							newHqlString.append(COMMA);
						}
						
						if (ASC.equalsIgnoreCase(orderType)) {
							newHqlString.append(fieldName).append(SPACE).append(ASC);
						} else {
							newHqlString.append(fieldName).append(SPACE).append(DESC);
						}
						i++;
					}
					
					// ORACLE 对于 order by 结合 rownum <= xx 返回结果不稳定
					// 增加 id 的排序，
					String aliasName = retrieveAlias(newHqlString.toString());
					
					if (StringUtils.isNotEmpty(aliasName)) 
						newHqlString.append(COMMA).append(aliasName).append(".").append("id");
					else
						newHqlString.append(COMMA).append("id");
				}

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
				
				Query query = session.createQuery(newHqlString.toString());
			
				int newPageIndex = pageIndex;
				int total = pageSize;
				Map<String, Object> aggregations = null;
				
				if (calCount) {
					//处理删除最后一页的最后一条数据之后，返回页数据为空的问题
					aggregations = aggregate(hqlString, params, footers);
					total = Integer.valueOf(aggregations.get(COUNT).toString());
					
					// edit by huolang 2011-01-14
					// 修正翻页后无法回到最后一页问题
					if (pageIndex < 0) {
						newPageIndex = 0;
					}
					else if (total <= pageSize * (pageIndex)) {
						if (total > 0)
							newPageIndex = (total+pageSize-1) / pageSize -1;
					}
				}

				query.setFirstResult(pageSize * (newPageIndex));
				query.setMaxResults(pageSize);
				if (params != null && params.size() > 0) {
					int i = 0;
					for (Object param : params) {
						query.setParameter(i++, param);
					}
				}
				if (dtoClass != null)
					query.setResultTransformer(Transformers
							.aliasToBean(dtoClass));
				
				Page page = null;
				List list = query.list();
				if (calCount)
					page = new Page(list, total, pageSize, newPageIndex);
				else {
					if (list == null || list.size() < pageSize)
						total = pageSize * pageIndex + list.size();
					else
						total = pageSize * (pageIndex + 1) + 1;
					
					page = new Page(list, total, pageSize, newPageIndex);
				}
				page.setUserdata(aggregations);
				
				return page;
			}
		});
		
	}
	
	/**
	 * 周祥增加,根据参数做SQL查询并且返回DTO对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(final String sqlString,final List<Object> args,final Map<String,String> sortMap,final List<ColumnType> scallarList,final Class<T> dtoClass) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				//如果有排序条件，在sqlString后添加order by
				StringBuffer newSqlString = new StringBuffer(sqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newSqlString.append(ORDER_BY);
					int i=0;
					for (Iterator it = sortMap.keySet().iterator(); it.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();
						
						if(i>0){
							newSqlString.append(COMMA);
						}
						
						if (ASC.equalsIgnoreCase(orderType)) {
							newSqlString.append(fieldName).append(SPACE).append(ASC);
						} else {
							newSqlString.append(fieldName).append(SPACE).append(DESC);
						}
						i++;
					}
				}
				
				SQLQuery query = session.createSQLQuery(newSqlString.toString());

				if (args != null && args.size() > 0) {
					int i=0;
					for(Object arg:args){
						query.setParameter(i++, arg);
					}
				}
				for(ColumnType scallar:scallarList){
					if(scallar.isNotNullType()){
						query.addScalar(scallar.getColumn(),scallar.getType());
					}else{
						query.addScalar(scallar.getColumn());
					}
				}
				query.setResultTransformer( Transformers.aliasToBean(dtoClass));
				
				return query.list();
			}
		});
	}
	

	/**
	 * 周祥增加,根据参数做SQL查询并且返回DTO对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(final String sqlString, final List<Object> args, final Map<String, Object> params
			, final Map<String,String> sortMap,final List<ColumnType> scallarList,final Class<T> dtoClass) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				//如果有排序条件，在sqlString后添加order by
				StringBuffer newSqlString = new StringBuffer(sqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newSqlString.append(ORDER_BY);
					int i=0;
					for (Iterator it = sortMap.keySet().iterator(); it.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();
						
						if(i>0){
							newSqlString.append(COMMA);
						}
						
						if (ASC.equalsIgnoreCase(orderType)) {
							newSqlString.append(fieldName).append(SPACE).append(ASC);
						} else {
							newSqlString.append(fieldName).append(SPACE).append(DESC);
						}
						i++;
					}
				}
				
				SQLQuery query = session.createSQLQuery(newSqlString.toString());

				if (args != null && args.size() > 0) {
					int i=0;
					for(Object arg:args){
						query.setParameter(i++, arg);
					}
				}
				if (params != null && params.size() > 0)
					query.setProperties(params);
				for(ColumnType scallar:scallarList){
					if(scallar.isNotNullType()){
						query.addScalar(scallar.getColumn(),scallar.getType());
					}else{
						query.addScalar(scallar.getColumn());
					}
				}
				query.setResultTransformer( Transformers.aliasToBean(dtoClass));
				
				return query.list();
			}
		});
	}
	
	/**
	 * 周祥增加,根据参数来做SQL分页查询并且返回DTO对象列表
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageBySql(final String sqlString,
			final List<Object> args, final int pageSize, final int pageIndex,
			final Map<String, String> sortMap,
			final List<ColumnType> scallarList, final Class<T> dtoClass)
			throws DaoException {
		
		return (IPage<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//如果有排序条件，在sqlString后添加order by
				StringBuffer newSqlString = new StringBuffer(sqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newSqlString.append(ORDER_BY);
					int i=0;
					for (Iterator it = sortMap.keySet().iterator(); it.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();
						
						if(i>0){
							newSqlString.append(COMMA);
						}
						
						if (ASC.equalsIgnoreCase(orderType)) {
							newSqlString.append(fieldName).append(SPACE).append(ASC);
						} else {
							newSqlString.append(fieldName).append(SPACE).append(DESC);
						}
						i++;
					}

					newSqlString.append(COMMA).append("sys_guid()");
					
					/*
					// 对于聚合函数，无法增加ROWID
					boolean appendSortCol = true;
					Pattern pattern1 = Pattern.compile("\\bcount\\b|\\bsum\\b|\\bavg\\b|\\bunion\\b|\\bmax\\b|\\bmin\\b|\\bgroup\\b|\\bdistinct\\b", Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
					Matcher matcher1 = pattern1.matcher(sqlString);
					if (matcher1.find()) {
						appendSortCol = false;
					}
					
					if (appendSortCol) {
						// ORACLE 对于 order by 结合 rownum <= xx 返回结果不稳定
						// 增加 ROWID 的排序
						String aliasName = retrieveAlias(sqlString.toString());
						
						if (StringUtils.isNotEmpty(aliasName)) 
							newSqlString.append(COMMA).append(aliasName).append(".").append("ROWID");
						else
							newSqlString.append(COMMA).append("ROWID");
					}*/
				}

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
				
				SQLQuery query = session.createSQLQuery(newSqlString.toString());
				
				//处理删除最后一页的最后一条数据之后，返回页数据为空的问题
				Map<String, Object> aggregations = aggregateBySql(sqlString, params, footers);
				Integer total = Integer.valueOf(aggregations.get(COUNT).toString());
				
				// edit by huolang 2011-01-14
				// 修正翻页后无法回到最后一页问题
				int newPageIndex = pageIndex;
				if (pageIndex < 0) {
					newPageIndex = 0;
				}
				else if (total <= pageSize * (pageIndex)) {
					if (total > 0)
						newPageIndex = (total+pageSize-1) / pageSize -1;
				}
				
				query.setFirstResult(pageSize * (newPageIndex));
				query.setMaxResults(pageSize);
				if (params != null && params.size() > 0) {
					int i = 0;
					for (Object param : params) {
						query.setParameter(i++, param);
					}
				}
				
				for(ColumnType scallar:scallarList){
					if(scallar.isNotNullType()){
						query.addScalar(scallar.getColumn(),scallar.getType());
					}else{
						query.addScalar(scallar.getColumn());
					}
				}
				query.setResultTransformer( Transformers.aliasToBean(dtoClass));
				
				Page page = new Page(query.list(), total, pageSize, newPageIndex);
				page.setUserdata(aggregations);
				
				return page;
			}
		});
	}	
	
	
	/**
	 * 周祥增加,按照QueryPage的参数来做SQL分页查询
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageBySql(QueryPage queryPage,final Class<T> dtoClass) throws DaoException {
		
		//获取上下文
		final String sqlString = queryPage.getSqlString();
		final int pageSize = queryPage.getPageSize();
		final int pageIndex = queryPage.getPageIndex();
		final boolean calCount = queryPage.isCalCount();
		final List args = queryPage.getAllNotNullArg();
		final List<ColumnType> scallarList = queryPage.getScalarList();
		final Map<String, String>sortMap = queryPage.getSortMap();

		return (IPage<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//如果有排序条件，在sqlString后添加order by
				StringBuffer newSqlString = new StringBuffer(sqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newSqlString.append(ORDER_BY);
					int i=0;
					for (Iterator it = sortMap.keySet().iterator(); it.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();
						
						if(i>0){
							newSqlString.append(COMMA);
						}
						
						if (ASC.equalsIgnoreCase(orderType)) {
							newSqlString.append(fieldName).append(SPACE).append(ASC);
						} else {
							newSqlString.append(fieldName).append(SPACE).append(DESC);
						}
						i++;
					}

					newSqlString.append(COMMA).append("sys_guid()");
					
					/*
					// 对于聚合函数，无法增加ROWID
					boolean appendSortCol = true;
					Pattern pattern1 = Pattern.compile("\\bcount\\b|\\bsum\\b|\\bavg\\b|\\bunion\\b|\\bmax\\b|\\bmin\\b|\\bgroup\\b|\\bdistinct\\b", Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
					Matcher matcher1 = pattern1.matcher(sqlString);
					if (matcher1.find()) {
						appendSortCol = false;
					}
					
					if (appendSortCol) {
						// ORACLE 对于 order by 结合 rownum <= xx 返回结果不稳定
						// 增加 ROWID 的排序
						String aliasName = retrieveAlias(sqlString.toString());
						
						if (StringUtils.isNotEmpty(aliasName)) 
							newSqlString.append(COMMA).append(aliasName).append(".").append("ROWID");
						else
							newSqlString.append(COMMA).append("ROWID");
					}*/
				}

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
				
				SQLQuery query = session.createSQLQuery(newSqlString.toString());
				
				int newPageIndex = pageIndex;
				int total = pageSize;
				Map<String, Object> aggregations = null;
				
				if (calCount) {
				
					//处理删除最后一页的最后一条数据之后，返回页数据为空的问题
					aggregations = aggregateBySql(sqlString, params, footers);
					total = Integer.valueOf(aggregations.get(COUNT).toString());
					
					// edit by huolang 2011-01-14
					// 修正翻页后无法回到最后一页问题
					if (pageIndex < 0) {
						newPageIndex = 0;
					}
					else if (total <= pageSize * (pageIndex)) {
						if (total > 0)
							newPageIndex = (total+pageSize-1) / pageSize -1;
					}
				}
				
				query.setFirstResult(pageSize * (newPageIndex));
				query.setMaxResults(pageSize);
				if (params != null && params.size() > 0) {
					int i = 0;
					for (Object param : params) {
						query.setParameter(i++, param);
					}
				}
				
				for(ColumnType scallar:scallarList){
					if(scallar.isNotNullType()){
						query.addScalar(scallar.getColumn(),scallar.getType());
					}else{
						query.addScalar(scallar.getColumn());
					}
				}
				query.setResultTransformer( Transformers.aliasToBean(dtoClass));
				
				Page page = null;
				List list = query.list();
				if (calCount)
					page = new Page(list, total, pageSize, newPageIndex);
				else {
					if (list == null || list.size() < pageSize)
						total = pageSize * pageIndex + list.size();
					else
						total = pageSize * (pageIndex + 1) + 1;
					
					page = new Page(list, total, pageSize, newPageIndex);
				}
				page.setUserdata(aggregations);
				
				return page;
			}
		});
		
	}
	
	public void setCacheQueries(boolean cacheQueries) {
		this.cacheQueries = cacheQueries;
	}

	public void setQueryCacheRegion(String queryCacheRegion) {
		this.queryCacheRegion = queryCacheRegion;
	}

	public void saveOrUpdate(final Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void delete(Serializable id, Class<?> clazz) {
		getHibernateTemplate().delete(get(clazz, id));
	}

	public void delete(Object idEntity, Class<?> clazz) {
		getHibernateTemplate().delete(idEntity);
	}

	public void delete(List<?> itemList, Class<?> clazz) {
		Object idEntity;
		for (Object item : itemList) {
			idEntity = (Object) item;
			delete(idEntity, clazz);
		}
	}

	public void delete(Serializable[] ids, Class<?> clazz) {
		for (Serializable id : ids) {
			delete(id, clazz);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNamedQuery(final String queryName) {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNamedQuery(final String queryName,
			final Object parameter) {
		return getHibernateTemplate().findByNamedQuery(queryName, parameter);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNamedQuery(final String queryName,
			final Object[] parameters) {
		return getHibernateTemplate().findByNamedQuery(queryName, parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(final String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(final String queryString, final Object parameter) {
		return getHibernateTemplate().find(queryString, parameter);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(final String queryString, final Object[] parameters) {
		return getHibernateTemplate().find(queryString, parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(final Class<?> entity) {
		return getHibernateTemplate().find("from " + entity.getName());
	}

	public int countAll(final Class<?> clazz) {
		return countByCriteria(DetachedCriteria.forClass(clazz));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	public int countByCriteria(final DetachedCriteria detachedCriteria) {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				})).intValue();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> entityClass, String orderBy,
			boolean isAsc) {
		if (isAsc)
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.asc(orderBy)));
		else
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.desc(orderBy)));
	}

	public Serializable save(Object o) {
		return getHibernateTemplate().save(o);
	}

	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	public <T> void removeById(Class<T> entityClass, Serializable id) {
		getHibernateTemplate().delete(get(entityClass, id));
	}

	public <T> void removeAllById(Class<T> entityClass,
			Collection<Serializable> entities) {
		for (Iterator<Serializable> it = entities.iterator(); it.hasNext();) {
			removeById(entityClass, (Serializable) it.next());
		}
	}

	/**
	 * 必须使用事务管理器包装,否则会有未关闭的连接
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value) {
		return createCriteria(entityClass, Restrictions.eq(propertyName, value))
				.list();
	}

	/**
	 * 必须使用事务管理器包装,否则会有未关闭的连接
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, String orderBy, boolean isAsc) {
		return createCriteria(entityClass, orderBy, isAsc,
				Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 必须使用事务管理器包装,否则会有未关闭的连接
	 */
	@SuppressWarnings("unchecked")
	public <T> T findUniqueBy(Class<T> entityClass, String propertyName,
			Object value) {
		return (T) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 必须使用事务管理器包装,否则会有未关闭的连接
	 */
	public <T> boolean isUnique(Class<T> entityClass, Object entity,
			String uniquePropertyNames) {
		Criteria criteria = createCriteria(entityClass).setProjection(
				Projections.rowCount());
		String[] nameList = uniquePropertyNames.split(",");
		try {
			// 循环加入唯一列
			for (String name : nameList) {
				criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(
						entity, name)));
			}

			// 以下代码为了如果是update的情况,排除entity自身.
			String idName = getIdName(entityClass);

			// 取得entity的主键值
			Serializable id = (Serializable) PropertyUtils.getProperty(entity,
					idName);

			// 如果id!=null,说明对象已存在,该操作为update,加入排除自身的判断
			if (id != null)
				criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return (Integer) criteria.uniqueResult() == 0;
	}

	/**
	 * 
	 * 必须使用事务管理器包装,否则会有未关闭的连接
	 * 
	 * 创建Query对象.
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values
	 *            可变参数.
	 */
	protected Query createQuery(String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/**
	 * 必须使用事务管理器包装,否则会有未关闭的连接
	 */
	public <T> Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	protected <T> Criteria createCriteria(Class<T> entityClass, String orderBy,
			boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		
		criteria.addOrder(Order.asc("id"));

		return criteria;
	}

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	protected String getIdName(Class<?> clazz) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		String idName = meta.getIdentifierPropertyName();
		return idName;
	}

	/**
	 * 
	 * 必须使用事务管理器包装,否则会有未关闭的连接
	 * 
	 * 使用HQL的简单查询（TODO：最好需要增加检查语句，如防止DELETE等）
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List simpleQuery(String hql, Object... values) {
		Query query = createQuery(hql, values);
		return query.list();
	}

	public void merge(Object object) {
		getHibernateTemplate().merge(object);
	}

	@SuppressWarnings("unchecked")
	public List<?> findAllByList(final String hql, final String placeholder,
			final Collection values) {
		return (List<?>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setParameterList(placeholder, values);
						return query.list();
					}
				});
	}
	

}
