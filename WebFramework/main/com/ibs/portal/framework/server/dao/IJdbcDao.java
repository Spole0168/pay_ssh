package com.ibs.portal.framework.server.dao;

import java.util.List;
import java.util.Map;

import com.ibs.portal.framework.server.exception.DaoException;

/**
 * JDBC数据操作接口, 用于高性能要求的查询与存储过程调用. 如:报表等
 * 
 *
 * @author 
 *
 */
public interface IJdbcDao extends IDao{
	/**
	 * SQL查询
	 *
	 * @param command SQL语句
	 * @param args 参数
	 * @return 数据集
	 * @throws JdbcDaoException JDBC出错时抛出
	 */
	List<Map<String, Object>> query(String command, List<Object> args) throws DaoException;

	/**
	 * 调用存储过程
	 *
	 * @param procedureName 存储过程名称
	 * @param args 参数
	 * @return 执行完成状态
	 * @throws JdbcDaoException JDBC出错时抛出
	 */
	void call(String procedureName, List<Object> args) throws DaoException;

	/**
	 * 调用查询存储过程
	 *
	 * @param procedureName 存储过程名称
	 * @param args 参数
	 * @return 数据集
	 * @throws JdbcDaoException JDBC出错时抛出
	 */
	List<Map<String, Object>> callQuery(String procedureName, List<Object> args) throws DaoException;

	/**
	 * 输出性调用存储过程
	 *
	 * @param procedureName 存储过程名称
	 * @param args 参数
	 * @param outs 输出参数 <输出参数索引号, 输出参数类型>, 参数类型如:Types.FLOAT
	 * @return 输出参数对应的数据 <输出参数索引号, 输出数据>
	 * @throws JdbcDaoException JDBC出错时抛出
	 */
	Map<String, Object> callOut(String procedureName, List<Object> args) throws DaoException;
}
