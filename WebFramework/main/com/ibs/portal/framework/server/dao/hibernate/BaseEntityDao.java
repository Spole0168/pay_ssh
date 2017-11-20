package com.ibs.portal.framework.server.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.portal.framework.server.dao.IEntityDao;
import com.ibs.portal.framework.server.domain.IEntity;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.CriteriaAlias;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.PageFooterColumn;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.StringUtils;

/**
 * 单实体持久化操作基类, 必须声明所操作实体的泛型. 添加对于DTO的支持，提供部分查询方法中返回类型的定义
 * 
 * 使用如:
 * 
 * <pre>
 * public interface IUserDao extends IEntityDao&lt;User&gt; {
 *     ...
 * }
 * public class UserDao extends BaseEntityDao&lt;User&gt; implements IUserDao {
 *     ...
 * }
 * </pre>
 * 
 * @author
 * 
 * @param <E>
 *            实体类型
 */
public class BaseEntityDao<E extends IEntity> extends BaseDaoHibernateImpl
		implements IEntityDao<E> {
	/**
	 * 日志工具类, 使用如:
	 * 
	 * <pre>
	 * if (log.isDebugEnabled())
	 * 	log.debug(&quot;error id : {0}&quot;, id);
	 * </pre>
	 */
//	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public BaseEntityDao() {
		try {
			entityClass = (Class<E>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			String clsName = getClass().getSimpleName();
			throw new RuntimeException(getClass().getCanonicalName()
					+ "未定义泛型! 继承于:" + BaseEntityDao.class.getCanonicalName()
					+ "的类都必需声明所操作实体的泛型, 如:\npublic class " + clsName
					+ " extends " + BaseEntityDao.class.getSimpleName() + "<"
					+ clsName.substring(0, clsName.length() - 3)
					+ "> implements I" + clsName + "{\n\t...\n}");
		}
	}

	// ---- 实现IEntityDao ----

	public Serializable save(E entity) throws DaoException {
		return (Serializable) getHibernateTemplate().save(entity);
	}

	public void saveBatch(Collection<E> entities) throws DaoException {
		for (E entity : entities) {
			getHibernateTemplate().save(entity);
		}
	}

	public void update(E entity) throws DaoException {
		getHibernateTemplate().update(entity);
	}

	public void updateBatch(Collection<E> entities) throws DaoException {
		for (E entity : entities) {
			getHibernateTemplate().update(entity);
		}
	}

	public void saveOrUpdate(E entity) throws DaoException {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateBatch(Collection<E> entities) throws DaoException {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public void remove(E entity) throws DaoException {
		getHibernateTemplate().delete(entity);
	}

	public void remove(Serializable id) throws DaoException {
		getHibernateTemplate().delete(
				getHibernateTemplate().load(entityClass, id));
	}

	public void removeBatch(Collection<E> entities) throws DaoException {
		super.getHibernateTemplate().deleteAll(entities);
	}

	@SuppressWarnings("unchecked")
	public void removeBatch(String strCommand, List<Object> args)
			throws DaoException {
		if (args == null) {
			args = new ArrayList<Object>();
		}
		removeBatch(getHibernateTemplate().find(strCommand, args.toArray()));
	}

	@SuppressWarnings("unchecked")
	public E load(Serializable id) throws DaoException {
		return (E) getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public E loadBy(String propertyName, String propertyValue)
			throws DaoException {
		if (logger.isDebugEnabled())
			logger.debug("propertyName:" + propertyName + ",propertyValue:"
					+ propertyValue);

		List<E> list = getHibernateTemplate().find(
				"from " + entityClass.getName() + " where " + propertyName
						+ " = ?", propertyValue);

		// + " = ?", propertyValue);
		if (list.size() > 0)
			return (E) list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String queryString) throws DaoException {
		return super.getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String queryString, List<Object> args)
			throws DaoException {
		return super.getHibernateTemplate().find(queryString,
				(args == null ? new Object[0] : args.toArray()));
	}

	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString, final List<Object> args,
			final int pageSize, final int pageIndex) throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						query.setFirstResult(pageSize * (pageIndex));
						query.setMaxResults(pageSize);
						if (args != null && args.size() > 0) {
							int i = 0;
							for (Object arg : args) {
								query.setParameter(i++, arg);
							}
						}
						return query.list();
					}
				});

	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPage(final String queryString, final List<Object> args,
			final int pageSize, final int pageIndex) throws DaoException {
		return (IPage<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						
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
						
						Map<String, Object> aggregations = aggregate(queryString, params, footers);
						Integer total = Integer.valueOf(aggregations.get(COUNT).toString());

						// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
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

						Query query = session.createQuery(queryString);
						
						query.setFirstResult(pageSize * (newPageIndex));
						query.setMaxResults(pageSize);
						if (params != null && params.size() > 0) {
							int i = 0;
							for (Object param : params) {
								query.setParameter(i++, param);
							}
						}

						Page page = new Page<E>(query.list(), total, pageSize, newPageIndex);
						page.setUserdata(aggregations);
						
						return page;
					}
				});

	}


	public int countWithColumn(final String queryString, final List<Object> args,final String column)
			throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery(getCountStringWithColumn(queryString,column));
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

	/**
	 * 
	 * @param queryString
	 * @return
	 */
	private String getCountStringWithColumn(final String queryString,
			final String column) {
		String tmp = queryString.trim();
		if (null == column && "".equalsIgnoreCase(column)) {
			throw new RuntimeException(" column is null  [" + queryString + "]");
		}

		if (queryString.indexOf("order by") != -1)
			tmp = tmp.substring(0, tmp.indexOf("order by") - 1);

		if (tmp.toLowerCase().startsWith("from ")) {
			return " select count(" + column + ") " + tmp;
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
		return " select count(" + column + ") " + where;
	}

	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString, final Map<String, Object> args)
			throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						query.setProperties(args);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString,
			final Map<String, Object> args, final int pageSize,
			final int pageIndex) throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						query.setFirstResult(pageSize * (pageIndex));
						query.setMaxResults(pageSize);
						query.setProperties(args);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPage(final String queryString,
			final Map<String, Object> args, final int pageSize,
			final int pageIndex) throws DaoException {
		return (IPage<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						
						Map<String, PageFooterColumn> footers = new HashMap<String, PageFooterColumn>();
						Map<String, Object> params = new HashMap<String, Object>();
						
						if (args != null && args.size() > 0) {
							for (Map.Entry<String, Object> entry : args.entrySet()) {
								if ( (entry.getValue() instanceof PageFooterColumn) )
									footers.put(entry.getKey(), (PageFooterColumn) entry.getValue());
								else
									params.put(entry.getKey(), entry.getValue());
							}
						}
						
						Map<String, Object> aggregations = aggregate(queryString, params, footers);
						Integer total = Integer.valueOf(aggregations.get(COUNT).toString());

						// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
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

						Query query = session.createQuery(queryString);
						query.setFirstResult(pageSize * (newPageIndex));
						query.setMaxResults(pageSize);
						query.setProperties(params);
						
						Page page = new Page<E>(query.list(), total, pageSize, newPageIndex);
						page.setUserdata(aggregations);
						
						return page;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E> findByValue(final String queryString, final Object args)
			throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						query.setParameter(0, args);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E> findByValue(final String queryString, final Object[] args)
			throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						if (args != null) {
							int i = 0;
							for (Object arg : args) {
								query.setParameter(i++, arg);
							}
						}
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E> findByValue(final String queryString, final Object args,
			final int pageSize, final int pageIndex) throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						query.setFirstResult(pageSize * (pageIndex));
						query.setMaxResults(pageSize);
						query.setParameter(0, args);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageByValue(final String queryString,
			final Object args, final int pageSize, final int pageIndex)
			throws DaoException {
		return (IPage<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						int total = countByValue(queryString, args);

						// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
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

						Query query = session.createQuery(queryString);
						query.setFirstResult(pageSize * (newPageIndex));
						query.setMaxResults(pageSize);
						query.setParameter(0, args);
						return new Page<E>(query.list(), total, pageSize,
								newPageIndex);
					}
				});
	}

	public int countByValue(final String queryString, final Object args)
			throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery(getCountString(queryString, null));
						query.setParameter(0, args);
						return query.uniqueResult();
					}
				})).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<E> findBy(final DetachedCriteria detachedCriteria)
			throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						return detachedCriteria.getExecutableCriteria(session)
								.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E> findBy(final DetachedCriteria detachedCriteria,
			final int pageSize, final int pageIndex) throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						criteria.setFirstResult(pageSize * (pageIndex));
						criteria.setMaxResults(pageSize);
						return criteria.list();
					}
				});

	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageBy(final DetachedCriteria detachedCriteria,
			final int pageSize, final int pageIndex) throws DaoException {
		
		QueryPage queryPage = new QueryPage(pageSize, pageIndex);
		return findPageBy(queryPage);
	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageBy(final DetachedCriteria detachedCriteria, final List<PageFooterColumn> footers,
			final int pageSize, final int pageIndex) throws DaoException {
		
		QueryPage queryPage = new QueryPage(pageSize, pageIndex);
		queryPage.setPageFooter(footers);
		return findPageBy(queryPage);
	}

	public int countBy(final DetachedCriteria detachedCriteria)
			throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);

						CriteriaImpl impl = (CriteriaImpl) criteria;

						// 获得原来的Projection
						Projection projection = impl.getProjection();

						// 去掉排序
						List orderEntries;
						try {
							orderEntries = (List) BeanUtils.forceGetProperty(
									impl, "orderEntries");
							BeanUtils.forceSetProperty(impl, "orderEntries",
									new ArrayList());
						} catch (Exception e) {
							throw new InternalError(
									" Runtime Exception impossibility throw ");
						}

						Object result = criteria.setProjection(
								Projections.rowCount()).uniqueResult();

						// 设置Projection
						impl.setProjection(projection);

						// 增加排序
						try {
							BeanUtils.forceSetProperty(impl, "orderEntries",
									orderEntries);
						} catch (Exception e) {
							throw new InternalError(
									" Runtime Exception impossibility throw ");
						}

						return result;
					}
				})).intValue();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> aggregateBy(final DetachedCriteria detachedCriteria, final List<PageFooterColumn> footers)
			throws DaoException {
		return (Map<String, Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);

						CriteriaImpl impl = (CriteriaImpl) criteria;
		
						// 获得原来的Projection
						Projection projection = impl.getProjection();
		
						// 去掉排序
						List orderEntries;
						try {
							orderEntries = (List) BeanUtils.forceGetProperty(
									impl, "orderEntries");
							BeanUtils.forceSetProperty(impl, "orderEntries",
									new ArrayList());
						} catch (Exception e) {
							throw new InternalError(
									" Runtime Exception impossibility throw ");
						}
						
						Map<String, Object> result = new LinkedHashMap<String, Object>();
						result.put(COUNT, null);
						
						ProjectionList projectList = Projections.projectionList();
						projectList.add(Projections.rowCount());
						if (footers != null && !footers.isEmpty()) {
							Pattern pattern = Pattern.compile("(\\w*)\\((\\w*)\\)");
							
							for( int i = 0; i < footers.size(); ++i ) {
								PageFooterColumn footer = footers.get(i);
								Matcher matcher = pattern.matcher(footer.getAggExpression());
								String aggName = null;
								String propName = null;
								if (matcher.find()) {
									aggName = matcher.group(1);
									propName = matcher.group(2);
									
									if (aggName.equalsIgnoreCase("sum")) {
										projectList.add(Projections.sum(propName));
									} else if (aggName.equalsIgnoreCase("avg")) {
										projectList.add(Projections.avg(propName));
									} else if (aggName.equalsIgnoreCase("min")) {
										projectList.add(Projections.min(propName));
									} else if (aggName.equalsIgnoreCase("max")) {
										projectList.add(Projections.max(propName));
									}
									result.put(footer.getName(), null);
								} else
									result.put(footer.getName(), footer.getAggExpression().replaceAll("'", ""));
							}
						}

						List list = criteria.setProjection(projectList).list();
						
						int i = 0;
						for (Map.Entry<String, Object> entry : result.entrySet()) {
							if (entry.getValue() != null)
								continue;
							
							if (list.get(0) instanceof Object[]) {
								Object[] objs = ((Object[]) list.get(0));
								entry.setValue(objs[i++]);
							} else if (list.get(0) instanceof Object) {
								entry.setValue(list.get(0));
							}
							
						}
						
						// 设置Projection
						impl.setProjection(projection);
		
						// 增加排序
						try {
							BeanUtils.forceSetProperty(impl, "orderEntries",
									orderEntries);
						} catch (Exception e) {
							throw new InternalError(
									" Runtime Exception impossibility throw ");
						}

						return result;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E> findBy(final E entity) throws DaoException {
		return getHibernateTemplate().findByExample(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> findBy(final E entity, final int pageSize,
			final int pageIndex) throws DaoException {
		return getHibernateTemplate().findByExample(entity,
				pageSize * (pageIndex), pageSize);
	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageBy(final E entity, final int pageSize,
			final int pageIndex) throws DaoException {
		return (IPage<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entity
								.getClass());
						criteria.add(Example.create(entity));
						Integer total = (Integer) criteria.setProjection(
								Projections.rowCount()).uniqueResult();

						// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
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
						criteria.setProjection(null);
						criteria.setFirstResult(pageSize * (newPageIndex));
						criteria.setMaxResults(pageSize);
						return new Page<E>(criteria.list(), total, pageSize,
								newPageIndex);
					}
				});
	}

	public int countBy(final E entity) throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entity
								.getClass());
						criteria.add(Example.create(entity));
						return criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				})).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entityClass);
						return criteria.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll(final int pageSize, final int pageIndex)
			throws DaoException {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entityClass);
						criteria.setFirstResult(pageSize * (pageIndex));
						criteria.setMaxResults(pageSize);
						return criteria.list();
					}
				});

	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageAll(final int pageSize, final int pageIndex)
			throws DaoException {
		return (IPage<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entityClass);
						Integer total = (Integer) criteria.setProjection(
								Projections.rowCount()).uniqueResult();
						// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
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
						criteria.setProjection(null);
						criteria.setFirstResult(pageSize * (newPageIndex));
						criteria.setMaxResults(pageSize);
						return new Page(findAll(), total, pageSize,
								newPageIndex);
					}
				});
	}

	public int countAll() throws DaoException {
		return ((Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entityClass);
						return criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				})).intValue();
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	public E load(E entity) throws DaoException {
		return (E) getHibernateTemplate().get(entityClass, entity.getId());
	}

	public DetachedCriteria createCriteriaByQueryPage(QueryPage queryPage,
			Class entityClass) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		/**
		 * 增加过滤条件
		 */
		for (Iterator<Criterion> i = queryPage.getCriterionList().iterator(); i
				.hasNext();) {
			criteria.add(i.next());
		}

		// 对alais的过滤
		Map<String, DetachedCriteria> aliasMap = new HashMap<String, DetachedCriteria>();
		for (Iterator<CriteriaAlias> i = queryPage.getCriteriaAliasList()
				.iterator(); i.hasNext();) {
			CriteriaAlias alias = i.next();

			DetachedCriteria criteriaAlias = null;
			if (aliasMap.containsKey(alias.getAliasName()) == true) {
				criteriaAlias = aliasMap.get(alias.getAliasName());
			} else {
				criteriaAlias = criteria.createAlias(alias.getObjectName(),
						alias.getAliasName());
				aliasMap.put(alias.getAliasName(), criteriaAlias);
			}
			criteriaAlias.add(alias.getCriterion());
		}

		Map<String, String> sortMap = queryPage.getSortMap();
		if (sortMap != null && !sortMap.isEmpty()) {
			for (Iterator it = sortMap.keySet().iterator(); it.hasNext();) {
				Object o = it.next();
				String fieldName = o.toString();
				String orderType = sortMap.get(fieldName).toString();

				if ("asc".equalsIgnoreCase(orderType)) {
					criteria.addOrder(Order.asc(fieldName));
				} else {
					criteria.addOrder(Order.desc(fieldName));
				}
			}
			
			criteria.addOrder(Order.asc("id"));
		}
		
		return criteria;
	}

	/**
	 * 覃争鸣增加,按照QueryPage的参数来设置
	 */
	@SuppressWarnings("unchecked")
	public IPage<E> findPageBy(final QueryPage queryPage) throws DaoException {

		final DetachedCriteria detachedCriteria = this.createCriteriaByQueryPage(queryPage,
				entityClass);

		// 获取上下文
		final int pageSize = queryPage.getPageSize();
		final int pageIndex = queryPage.getPageIndex();
		final List<PageFooterColumn> footers = queryPage.getPageFooter();
		final boolean calCount = queryPage.isCalCount();
		
		return (IPage<E>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						
						int newPageIndex = pageIndex;
						int total = pageSize;
						Map<String, Object> aggregations = null;
						
						if (calCount) {
							// 获得总个数
							aggregations = aggregateBy(detachedCriteria, footers);
							total = Integer.valueOf(aggregations.get(COUNT).toString());
							
							// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
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

						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						criteria.setProjection(null);
						criteria.setFirstResult(pageSize * (newPageIndex));
						criteria.setMaxResults(pageSize);

						Page page = null;
						List list = criteria.list();
						if (calCount)
							page = new Page<E>(list, total, pageSize, newPageIndex);
						else {
							if (list == null || list.size() < pageSize)
								total = pageSize * pageIndex + list.size();
							else
								total = pageSize * (pageIndex + 1) + 1;
							
							page = new Page<E>(list, total, pageSize, newPageIndex);
						}
						page.setUserdata(aggregations);
						
						return page;
					}
				});
		
	}

	/**
	 * 周祥增加,按照QueryPage的参数来设置
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAllBy(QueryPage queryPage) throws DaoException {

		DetachedCriteria criteria = this.createCriteriaByQueryPage(queryPage,
				entityClass);

		return findBy(criteria);
	}

	/**
	 * 周祥增加,根据参数做HQL查询并且返回DTO对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHql(final String hqlString,
			final List<Object> args, final Map<String, String> sortMap,
			final Class<T> dtoClass) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {

						// 如果有排序条件，在hqlString后添加order by
						StringBuffer newHqlString = new StringBuffer(hqlString);
						if (sortMap != null && !sortMap.isEmpty()) {
							newHqlString.append(ORDER_BY);
							int i = 0;
							for (Iterator it = sortMap.keySet().iterator(); it
									.hasNext();) {
								Object o = it.next();
								String fieldName = o.toString();
								String orderType = sortMap.get(fieldName)
										.toString();

								if (i > 0) {
									newHqlString.append(COMMA);
								}

								if (ASC.equalsIgnoreCase(orderType)) {
									newHqlString.append(fieldName)
											.append(SPACE).append(ASC);
								} else {
									newHqlString.append(fieldName)
											.append(SPACE).append(DESC);
								}
								i++;
							}
						}

						Query query = session.createQuery(newHqlString
								.toString());

						if (args != null && args.size() > 0) {
							int i = 0;
							for (Object arg : args) {
								query.setParameter(i++, arg);
							}
						}
						if (dtoClass != null)
							query.setResultTransformer(Transformers
									.aliasToBean(dtoClass));

						return query.list();
					}
				});
	}

	/**
	 * 周祥增加,根据参数做HQL查询并且返回HQL对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHql(final String hqlString,
			final List<Object> args, final Map<String, String> sortMap) {
		return this.findByHql(hqlString, args, sortMap, null);
	}

	/**
	 * 周祥增加,按照参数来做HQL分页查询并且返回DTO对象列表
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(final String hqlString,
			final List<Object> args, final int pageSize, final int pageIndex,
			final Map<String, String> sortMap, final Class<T> dtoClass)
			throws DaoException {
		
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
	 * 周祥增加,按照参数来做HQL分页查询并且返回DTO对象列表
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHqlWithColumn(final String hqlString,
			final List<Object> args, final int pageSize, final int pageIndex,
			final Map<String, String> sortMap, final Class<T> dtoClass,
			final String column) throws DaoException {
		return (IPage) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// 如果有排序条件，在hqlString后添加order by
				StringBuffer newHqlString = new StringBuffer(hqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newHqlString.append(ORDER_BY);
					int i = 0;
					for (Iterator it = sortMap.keySet().iterator(); it
							.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();

						if (i > 0) {
							newHqlString.append(COMMA);
						}

						if (ASC.equalsIgnoreCase(orderType)) {
							newHqlString.append(fieldName).append(SPACE)
									.append(ASC);
						} else {
							newHqlString.append(fieldName).append(SPACE)
									.append(DESC);
						}
						i++;
					}
				}

				Query query = session.createQuery(newHqlString.toString());

				// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
				Integer total = (Integer) countWithColumn(hqlString, args,column);
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
					int i = 0;
					for (Object arg : args) {
						query.setParameter(i++, arg);
					}
				}
				if (dtoClass != null)
					query.setResultTransformer(Transformers
							.aliasToBean(dtoClass));
				return new Page(query.list(), total, pageSize, newPageIndex);
			}
		});
	}

	/**
	 * 周祥增加,按照QueryPage的参数来做HQL分页查询并且返回DTO对象列表
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(QueryPage queryPage,
			final Class<T> dtoClass) throws DaoException {

		queryPage.setDtoClass(dtoClass);
		return findPageByHql(queryPage);
		
	}

	/**
	 * 周祥增加,按照参数来做HQL分页查询并且返回HQL对象列表
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(final String hqlString,
			final List<Object> args, final int pageSize, final int pageIndex,
			final Map<String, String> sortMap) throws DaoException {
		
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
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageByHql(QueryPage queryPage) throws DaoException {

		// 获取上下文
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
				// 如果有排序条件，在hqlString后添加order by
				StringBuffer newHqlString = new StringBuffer(hqlString);
				if (sortMap != null && !sortMap.isEmpty()) {
					newHqlString.append(ORDER_BY);
					int i = 0;
					for (Iterator it = sortMap.keySet().iterator(); it
							.hasNext();) {
						Object o = it.next();
						String fieldName = o.toString();
						String orderType = sortMap.get(fieldName).toString();

						if (i > 0) {
							newHqlString.append(COMMA);
						}

						if (ASC.equalsIgnoreCase(orderType)) {
							newHqlString.append(fieldName).append(SPACE)
									.append(ASC);
						} else {
							newHqlString.append(fieldName).append(SPACE)
									.append(DESC);
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
					// 获得总个数
					aggregations = aggregate(hqlString, params, footers);
					total = Integer.valueOf(aggregations.get(COUNT).toString());
					
					// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
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
					page = new Page<E>(list, total, pageSize, newPageIndex);
				else {
					if (list == null || list.size() < pageSize)
						total = pageSize * pageIndex + list.size();
					else
						total = pageSize * (pageIndex + 1) + 1;
					
					page = new Page<E>(list, total, pageSize, newPageIndex);
				}
				page.setUserdata(aggregations);
				
				return page;
			}
		});
		
	}

	/**
	 * 黄一龙增加,根据参数做SQL查询并且返回DTO对象列表，当sql中有in时使用这个方法
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySqlWithIn(final String sqlString,
			final Map<String, Object> args, final Map<String, String> sortMap,
			final List<ColumnType> scallarList, final Class<T> dtoClass) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {

						// 如果有排序条件，在sqlString后添加order by
						StringBuffer newSqlString = new StringBuffer(sqlString);
						if (sortMap != null && !sortMap.isEmpty()) {
							newSqlString.append(ORDER_BY);
							int i = 0;
							for (Iterator it = sortMap.keySet().iterator(); it
									.hasNext();) {
								Object o = it.next();
								String fieldName = o.toString();
								String orderType = sortMap.get(fieldName)
										.toString();

								if (i > 0) {
									newSqlString.append(COMMA);
								}

								if (ASC.equalsIgnoreCase(orderType)) {
									newSqlString.append(fieldName)
											.append(SPACE).append(ASC);
								} else {
									newSqlString.append(fieldName)
											.append(SPACE).append(DESC);
								}
								i++;
							}
						}

						SQLQuery query = session.createSQLQuery(newSqlString
								.toString());

						if (args != null && args.size() > 0) {
							query.setProperties(args);
						}
						for (ColumnType scallar : scallarList) {
							if (scallar.isNotNullType()) {
								query.addScalar(scallar.getColumn(), scallar
										.getType());
							} else {
								query.addScalar(scallar.getColumn());
							}
						}
						query.setResultTransformer(Transformers
								.aliasToBean(dtoClass));

						return query.list();
					}
				});
	}
	
	/**
	 * 周祥增加,根据参数做SQL查询并且返回DTO对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(final String sqlString,
			final List<Object> args, final Map<String, String> sortMap,
			final List<ColumnType> scallarList, final Class<T> dtoClass) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {

						// 如果有排序条件，在sqlString后添加order by
						StringBuffer newSqlString = new StringBuffer(sqlString);
						if (sortMap != null && !sortMap.isEmpty()) {
							newSqlString.append(ORDER_BY);
							int i = 0;
							for (Iterator it = sortMap.keySet().iterator(); it
									.hasNext();) {
								Object o = it.next();
								String fieldName = o.toString();
								String orderType = sortMap.get(fieldName)
										.toString();

								if (i > 0) {
									newSqlString.append(COMMA);
								}

								if (ASC.equalsIgnoreCase(orderType)) {
									newSqlString.append(fieldName)
											.append(SPACE).append(ASC);
								} else {
									newSqlString.append(fieldName)
											.append(SPACE).append(DESC);
								}
								i++;
							}
						}

						SQLQuery query = session.createSQLQuery(newSqlString
								.toString());

						if (args != null && args.size() > 0) {
							int i = 0;
							for (Object arg : args) {
								query.setParameter(i++, arg);
							}
						}
						for (ColumnType scallar : scallarList) {
							if (scallar.isNotNullType()) {
								query.addScalar(scallar.getColumn(), scallar
										.getType());
							} else {
								query.addScalar(scallar.getColumn());
							}
						}
						query.setResultTransformer(Transformers
								.aliasToBean(dtoClass));

						return query.list();
					}
				});
	}

	/**
	 * 周祥增加,根据参数来做SQL分页查询并且返回DTO对象列表
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageBySql(final String sqlString,
			final List<Object> args, final int pageSize, final int pageIndex,
			final Map<String, String> sortMap,
			final List<ColumnType> scallarList, final Class<T> dtoClass)
			throws DaoException {
		
		QueryPage queryPage = new QueryPage(pageSize, pageIndex);
		queryPage.setSqlString(sqlString);
		queryPage.setSortMap(sortMap);
		queryPage.setScalarList(scallarList);
		if (args != null) {
			for (Object arg : args) {
				queryPage.addQueryCondition(null, arg);
			}
		}
//		queryPage.setDtoClass(dtoClass);
		
		return findPageBySql(queryPage, dtoClass);

	}

	/**
	 * 周祥增加,按照QueryPage的参数来做SQL分页查询
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> IPage<T> findPageBySql(QueryPage queryPage,
			final Class<T> dtoClass) throws DaoException {

		// 获取上下文
		final String sqlString = queryPage.getSqlString();
		final int pageSize = queryPage.getPageSize();
		final int pageIndex = queryPage.getPageIndex();
		final boolean calCount = queryPage.isCalCount();
		final List args = queryPage.getAllNotNullArg();
		final List<ColumnType> scallarList = queryPage.getScalarList();
		final Map<String, String> sortMap = queryPage.getSortMap();

		return (IPage<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						// 如果有排序条件，在sqlString后添加order by
						StringBuffer newSqlString = new StringBuffer(sqlString);
						if (sortMap != null && !sortMap.isEmpty()) {
							newSqlString.append(ORDER_BY);
							int i = 0;
							for (Iterator it = sortMap.keySet().iterator(); it
									.hasNext();) {
								Object o = it.next();
								String fieldName = o.toString();
								String orderType = sortMap.get(fieldName)
										.toString();

								if (i > 0) {
									newSqlString.append(COMMA);
								}

								if (ASC.equalsIgnoreCase(orderType)) {
									newSqlString.append(fieldName)
											.append(SPACE).append(ASC);
								} else {
									newSqlString.append(fieldName)
											.append(SPACE).append(DESC);
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
							}
							*/
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
						
						SQLQuery query = session.createSQLQuery(newSqlString
								.toString());

						int newPageIndex = pageIndex;
						int total = pageSize;
						Map<String, Object> aggregations = null;
						
						if (calCount) {
							// 处理删除最后一页的最后一条数据之后，返回页数据为空的问题
							//Integer total = (Integer) countBySql(sqlString, args);
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

						for (ColumnType scallar : scallarList) {
							if (scallar.isNotNullType()) {
								query.addScalar(scallar.getColumn(), scallar
										.getType());
							} else {
								query.addScalar(scallar.getColumn());
							}
						}
						query.setResultTransformer(Transformers
								.aliasToBean(dtoClass));
						
						Page page = null;
						List list = query.list();
						if (calCount)
							page = new Page<E>(list, total, pageSize, newPageIndex);
						else {
							if (list == null || list.size() < pageSize)
								total = pageSize * pageIndex + list.size();
							else
								total = pageSize * (pageIndex + 1) + 1;
							
							page = new Page<E>(list, total, pageSize, newPageIndex);
						}
						page.setUserdata(aggregations);
						
						return page;
					}
				});
		
	}

}
