package com.ibs.portal.framework.server.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.ibs.portal.framework.server.domain.IEntity;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.IPage;

public interface IEntityDao <E extends IEntity> extends IDao{
	/**
	 * 执行更新语句
	 *
	 * @param hql
	 *            hql语句
	 * @throws DaoException 当执行失败时抛出
	 */
	int execute(String hql) throws DaoException;

	/**
	 * 执行更新语句
	 *
	 * @param hql
	 *            hql语句
	 * @param args
	 *            参数, 替换hql中的"?"
	 * @throws DaoException 当执行失败时抛出
	 */
	int execute(String hql, List<Object> args) throws DaoException;

	/**
	 * 执行更新语句
	 *
	 * @param hql
	 *            hql语句
	 * @param args
	 *            参数, 替换hql中的":arg"
	 * @throws DaoException 当执行失败时抛出
	 */
	int execute(String hql, Map<String, Object> args) throws DaoException;

	/**
	 * 执行SQL更新语句
	 *
	 * @param sql
	 *            sql语句
	 * @throws DaoException 当执行失败时抛出
	 */
	int executeSql(String sql) throws DaoException;

	/**
	 * 执行SQL更新语句
	 *
	 * @param sql
	 *            sql语句
	 * @param args
	 *            参数, 替换sql中的"?"
	 * @throws DaoException 当执行失败时抛出
	 */
	int executeSql(String sql, List<Object> args) throws DaoException;

	/**
	 * 执行更新语句
	 *
	 * @param sql
	 *            sql语句
	 * @param args
	 *            参数, 替换sql中的":arg"
	 * @throws DaoException 当执行失败时抛出
	 */
	int executeSql(String sql, Map<String, Object> args) throws DaoException;	
	// 单实体操作 ----

	/**
	 * 保存实体
	 *
	 * @param entity
	 *            实体对象
	 * @throws DaoException 当保存失败时抛出
	 */
	Serializable save(E entity) throws DaoException ;

	/**
	 * 批量保存实体
	 *
	 * @param entities 实体集
	 * @throws DaoException 当保存失败时抛出
	 */
	void saveBatch(Collection<E> entities) throws DaoException;

	/**
	 * 更新实体
	 *
	 * @param entity
	 *            实体对象
	 * @throws DaoException 当更新失败时抛出
	 */
	void update(E entity) throws DaoException;

	/**
	 * 批量更新实体
	 *
	 * @param entities 实体集
	 * @throws DaoException 当更新失败时抛出
	 */
	void updateBatch(Collection<E> entities) throws DaoException;

	/**
	 * 保存或更新实体
	 *
	 * @param entity 实体对象
	 * @throws DaoException 当保存或更新失败时抛出
	 */
	void saveOrUpdate(E entity) throws DaoException;

	/**
	 * 批量保存或更新实体
	 *
	 * @param entities 实体集
	 * @throws DaoException 当保存或更新失败时抛出
	 */
	void saveOrUpdateBatch(Collection<E> entities) throws DaoException;

	/**
	 * 删除实体
	 *
	 * @param entity
	 *            实体对象
	 * @throws DaoException 当删除出错时抛出
	 */
	void remove(E entity) throws DaoException;

	/**
	 * 根据ID删除实体
	 *
	 * @param id 实体的ID
	 * @throws DaoException 当删除出错时抛出
	 */
	void remove(Serializable id) throws DaoException;

	/**
	 * 批量删除实体
	 *
	 * @param entities 待删除实体集
	 * @throws DaoException 当删除出错时抛出
	 */
	void removeBatch(Collection<E> entities) throws DaoException;

	/**
	 * 查出相关实体进行批量删除
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @throws DaoException 当查询或删除出错时抛出
	 */
	void removeBatch(String queryString, List<Object> args) throws DaoException;

	/**
	 * 加载实体
	 *
	 * @param entity
	 *            实体查询对象
	 * @return 实体对象
	 * @throws DaoException 当查询出错时抛出
	 */
	E load(E entity) throws DaoException;

	/**
	 * 通过ID加载实体
	 *
	 * @param id
	 *            查询实体的ID
	 * @return 实体对象
	 * @throws DaoException 当查询出错时抛出
	 */
	E load(Serializable id) throws DaoException;

	/**
	 * 通过编号加载实体
	 *
	 * @param code
	 *            查询实体的编号
	 * @return 实体对象
	 * @throws DaoException 当查询出错时抛出
	 */
	public E loadBy(String propertyName, String propertyValue) throws DaoException;

	// 实体HQL查询 ----

	/**
	 * 查询实体
	 *
	 * @param queryString 查询命令串(HQL)
	 * @return 查询出的实体
	 * @throws DaoException 当查询出错时抛出
	 */
	public List<E> find(String queryString) throws DaoException;

	/**
	 * 查询实体
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @return 查询出的实体
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> find(String queryString, List<Object> args) throws DaoException;

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
	List<E> find(String queryString, List<Object> args, int pageSize, int pageIndex) throws DaoException;

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
	IPage<E> findPage(String queryString, List<Object> args, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 统计实体的个数
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @return 实体的个数
	 * @throws DaoException 当查询出错时抛出
	 */
	int count(String queryString, List<Object> args) throws DaoException;

	/**
	 * 查询实体
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @return 查询出的实体
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> find(String queryString, Map<String, Object> args) throws DaoException;

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
	List<E> find(String queryString, Map<String, Object> args, int pageSize, int pageIndex) throws DaoException;

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
	IPage<E> findPage(String queryString, Map<String, Object> args, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 统计实体的个数
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @return 实体的个数
	 * @throws DaoException 当查询出错时抛出
	 */
	int count(String queryString, Map<String, Object> args) throws DaoException;

	/**
	 * 查询实体
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @return 查询出的实体
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> findByValue(String queryString, Object args) throws DaoException;

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
	List<E> findByValue(String queryString, Object args, int pageSize, int pageIndex) throws DaoException;

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
	IPage<E> findPageByValue(String queryString, Object args, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 统计实体的个数
	 *
	 * @param queryString 查询命令串(HQL)
	 * @param args 查询参数
	 * @return 实体的个数
	 * @throws DaoException 当查询出错时抛出
	 */
	int countByValue(String queryString, Object args) throws DaoException;

	// 实体条件查询 ----

	/**
	 * 查询符合依据的实体
	 *
	 * @param criteria 依据
	 * @return 符合依据的数据
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> findBy(DetachedCriteria criteria) throws DaoException;

	/**
	 * 分页查询符合依据的实体
	 *
	 * @param criteria 依据
	 * @param pageSize 页面大小
	 * @param pageIndex 当前页码
	 * @return 符合依据的当前页数据
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> findBy(DetachedCriteria criteria, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 分页查询符合依据的实体
	 *
	 * @param criteria 依据
	 * @param pageSize 页面大小
	 * @param pageIndex 当前页码
	 * @return 符合依据的当前页数据
	 * @throws DaoException 当查询出错时抛出
	 */
	IPage<E> findPageBy(DetachedCriteria criteria, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 统计符合依据的实体的个数
	 *
	 * @param criteria 依据
	 * @return 实体的个数
	 * @throws DaoException 当查询出错时抛出
	 */
	int countBy(DetachedCriteria criteria) throws DaoException;

	/**
	 * 查询符合依据的实体
	 *
	 * @param entity 实体依据
	 * @return 符合依据的数据
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> findBy(E entity) throws DaoException;

	/**
	 * 分页查询符合依据的实体
	 *
	 * @param entity 实体依据
	 * @param pageSize 页面大小
	 * @param pageIndex 当前页码
	 * @return 符合依据的当前页数据
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> findBy(E entity, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 分页查询符合依据的实体
	 *
	 * @param entity 实体依据
	 * @param pageSize 页面大小
	 * @param pageIndex 当前页码
	 * @return 符合依据的当前页数据
	 * @throws DaoException 当查询出错时抛出
	 */
	IPage<E> findPageBy(E entity, int pageSize, int pageIndex) throws DaoException;

	/**
	 * 统计符合依据的实体的个数
	 *
	 * @param entity 实体依据
	 * @return 实体的个数
	 * @throws DaoException 当查询出错时抛出
	 */
	int countBy(E entity) throws DaoException;

	// 实体全集合查询 ----

	/**
	 * 查询所有实体
	 *
	 * @return 所有实体
	 */
	List<E> findAll() throws DaoException;

	/**
	 * 分页查询所有实体
	 *
	 * @param pageSize 页面大小
	 * @param pageIndex 当前页码
	 * @return 当前页数据
	 * @throws DaoException 当查询出错时抛出
	 */
	List<E> findAll(int pageSize, int pageIndex) throws DaoException;

	/**
	 * 分页查询所有实体
	 *
	 * @param pageSize 页面大小
	 * @param pageIndex 当前页码
	 * @return 当前页数据
	 * @throws DaoException 当查询出错时抛出
	 */
	IPage<E> findPageAll(int pageSize, int pageIndex) throws DaoException;

	/**
	 * 统计所有实体
	 *
	 * @return 所有实体的个数
	 * @throws DaoException 当查询出错时抛出
	 */
	int countAll() throws DaoException;
}
