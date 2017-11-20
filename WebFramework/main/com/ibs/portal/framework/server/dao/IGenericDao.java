package com.ibs.portal.framework.server.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.IPage;

public interface IGenericDao extends IDao{
	
	public abstract void setCacheQueries(boolean cacheQueries);

	public abstract void setQueryCacheRegion(String queryCacheRegion);

	/////////////////////////////////////////////////////////////
	/**
	 * 添加或更新一个 Object，即往数据库中加入一条记录
	 *
	 * @param entity 需要添加或更新的 Object
	 */
	public void saveOrUpdate(final Object entity);

	/**
	 * 立即将当前的数据写入到数据库中去
	 */
	public abstract void flush();

	/**
	 * 根据 entity 的 class 和 id 删除该 entity
	 *
	 * @param clazz 需要删除 entity 的类型
	 * @param id    需要删除 entity 的 id
	 */
	public abstract void delete(Serializable id, Class<?> clazz);

	/**
	 * 根据传入的 Object 中的 id 删除其对应的 entity
	 *
	 * @param idEntity 需要删除的 entity
	 * @param clazz    需要删除 entity 的类型
	 */
	public abstract void delete(Object idEntity, Class<?> clazz);

	/**
	 * 删除一个 List 中所有的 entity
	 *
	 * @param clazz    List 中 entity 的类型
	 * @param itemList 需要删除的 entity List
	 */
	public abstract void delete(List<?> itemList, Class<?> clazz);

	/**
	 * 删除数组中所有 ID 对应的 entity
	 *
	 * @param ids   id 数组
	 * @param clazz 对应的实体类
	 */
	public abstract void delete(Serializable[] ids, Class<?> clazz);

	/////////////////////////////////////////////////////////////
	// Find by
	////////////////////////////////////////////////////////////
	/**
	 * 执行一个命名查询
	 *
	 * @param queryName Hibernate映射文件中的查询名称
	 * @return 返回一个 List 的结果集
	 */
	public abstract <T> List<T> findByNamedQuery(final String queryName);

	/**
	 * 执行一个命名查询,并绑定单一参数到查询中的 ? 上
	 *
	 * @param queryName Hibernate映射文件中的查询名称
	 * @param parameter 所需要绑定的参数
	 * @return 返回一个 List 的结果集
	 */
	public abstract <T> List<T> findByNamedQuery(final String queryName,
			final Object parameter);

	/**
	 * 执行一个命名查询,并绑定一个参数集合到查询中的 ? 上
	 *
	 * @param queryName  Hibernate映射文件中的查询名称
	 * @param parameters 所需要绑定的参数集
	 * @return 返回一个 List 的结果集
	 */
	public abstract <T> List<T> findByNamedQuery(final String queryName,
			final Object[] parameters);

	/**
	 * 执行一个 HQL 的查询
	 *
	 * @param queryString HQL 查询语句
	 * @return 返回一个 List 的结果集
	 */
	public abstract <T> List<T> find(final String queryString);

	/**
	 * 执行一个 HQL 的查询,并绑定单一参数到查询中的 ? 上
	 *
	 * @param queryString HQL 查询语句
	 * @param parameter   所需要绑定的参数
	 * @return 返回一个 List 的结果集
	 */
	public abstract <T> List<T> find(final String queryString,
			final Object parameter);

	/**
	 * 执行一个 HQL 的查询,并绑定参数集参数到查询中的 ? 上
	 *
	 * @param queryString HQL 查询语句
	 * @param parameters  所需要绑定的参数集
	 * @return 返回一个 List 的结果集
	 */
	public abstract <T> List<T> find(final String queryString,
			final Object[] parameters);

	/**
	 * 获取一个 Entity 中的所有记录，即获取一张表中的所有记录
	 *
	 * @param entity 需要查询的 Entity
	 * @return 返回一个 List 的结果集
	 */
	public abstract <T> List<T> findAll(final Class<?> entity);

	/**
	 * 获取当前 clazz 拥有的实体数，即一张表中的所有记录数
	 *
	 * @param clazz 需要统计的实体类
	 * @return 该实体类对应的数量
	 */
	public abstract int countAll(final Class<?> clazz);
	
	/**
	 * 分页查询实体
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @param pageSize 页面大小
	 * @param pageIndex 当前页码
	 * @return 查询出的实体
	 * @throws DaoException 当查询出错时抛出
	 */
	public abstract IPage<?> findPage(String queryString, List<Object> args, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 统计实体的个数
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @return 实体的个数
	 * @throws DaoException 当查询出错时抛出
	 */
	public abstract int count(String queryString, List<Object> args) throws DaoException;

	/**
	 * 根据传入的 DetachedCriteria 进行查询并返回一个 List 的结果集
	 *
	 * @param detachedCriteria 包含查询条件的 DetachedCriteria
	 * @return 符合查询条件的结果集，存放于 List 中
	 */
	public abstract <T> List<T> findAllByCriteria(
			final DetachedCriteria detachedCriteria);

	/**
	 * 根据传入的 DetachedCriteria 进行统计，得出该查询能够得到的记录数
	 *
	 * @param detachedCriteria 包含查询条件的 DetachedCriteria
	 * @return 符合查询条件的记录数
	 */
	public abstract int countByCriteria(final DetachedCriteria detachedCriteria);

	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	/**
	 * 根据ID获取对象. 实际调用Hibernate的session.get()方法返回实体对象. 如果对象不存在，抛出异常.
	 */
	public abstract <T> T get(Class<T> entityClass, Serializable id);

	/**
	 * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
	 *
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public abstract <T> T load(Class<T> entityClass, Serializable id);

	/**
	 * 获取全部对象.
	 */
	public abstract <T> List<T> getAll(Class<T> entityClass);

	/**
	 * 获取全部对象,带排序字段与升降序参数.
	 */
	public abstract <T> List<T> getAll(Class<T> entityClass, String orderBy,
			boolean isAsc);

	/**
	 * 保存对象.
	 */
	public abstract Serializable save(Object o);

	/**
	 * 删除对象.
	 */
	public abstract void remove(Object o);

	/**
	 * 根据ID删除对象.
	 */
	public abstract <T> void removeById(Class<T> entityClass, Serializable id);

	/**
	 *  根据ID列表删除对象
	 *
	 */
	public abstract <T> void removeAllById(Class<T> entityClass,
			final Collection<Serializable> entities);

	/**
	 * 根据属性名和属性值查询对象.
	 *
	 * @return 符合条件的对象列表
	 */
	public abstract <T> List<T> findBy(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 根据属性名和属性值查询对象,带排序字段与升降序字段.
	 *
	 * @return 符合条件的对象列表
	 */
	public abstract <T> List<T> findBy(Class<T> entityClass,
			String propertyName, Object value, String orderBy, boolean isAsc);

	/**
	 * 根据属性名和属性值查询唯一对象.
	 *
	 * @return 符合条件的唯一对象 or null if not found.
	 */
	public abstract <T> T findUniqueBy(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 判断对象某些属性的值在数据库中是否唯一.
	 *
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	public abstract <T> boolean isUnique(Class<T> entityClass, Object entity,
			String uniquePropertyNames);

	/**
	 * 创建Criteria对象.
	 *
	 * @param criterions
	 *            可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	 */
	public abstract <T> Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions);

	/**
	 * merge，从Detached对象到persistent对象使用
	 * @param object
	 */
	public abstract void merge(Object object);

	/**
	 * 执行in语句的查询
	 * @param hql
	 * @param placeholder
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List<?> findAllByList(final String hql,
			final String placeholder, final Collection values);

}
