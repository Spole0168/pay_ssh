package com.ibs.portal.framework.server.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.Type;
import org.springframework.jdbc.core.RowMapper;

public class QueryPage {

	// 排序参数容器
	Map<String, String> sortMap = null;

	// 别名容器
	Map<String, String> aliasMap = null;

	// 每页记录数
	int pageSize;

	// 目标页
	int pageIndex;

	// 总记录数
	int totalRows;
	
	// 是否查询总数
	boolean calCount = true;

	List<Criterion> criterionList = new ArrayList<Criterion>();

	List<CriteriaAlias> criteriaAliasList = new ArrayList<CriteriaAlias>();

	// HQL
	String hqlString;

	// SQL
	String sqlString;

	// 查询条件列表
	List<ColumnValue> queryConditionList = new ArrayList<ColumnValue>();

	// 标量列表
	List<ColumnType> scalarList = new ArrayList<ColumnType>();
	
	List<PageFooterColumn> pageFooter = new ArrayList<PageFooterColumn>();
	
	Class<?> dtoClass;
	
	RowMapper rowMapper;

	public QueryPage(int pageSize, int pageIndex) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}
	
	// 初始化操作
	public QueryPage(int pageSize, int pageIndex, String sortName,
			String sortOrder) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;

		if (sortName != null && !sortName.equals("")) {
			this.addSort(sortName, sortOrder);
		}
	}

	/**
	 * 前后模糊查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addLikeSearch(String field, String value) {
		criterionList.add(Expression.like(field, value, MatchMode.ANYWHERE));
	}

	/**
	 * 前模糊查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addBeforeLikeSearch(String field, String value) {
		criterionList.add(Expression.like(field, value, MatchMode.START));
	}

	/**
	 * 后模糊查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addAfterLikeSearch(String field, String value) {
		criterionList.add(Expression.like(field, value, MatchMode.END));
	}

	/**
	 * 等于查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addEqualSearch(String field, Object value) {
		criterionList.add(Expression.eq(field, value));

	}

	/**
	 * 不等于查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addNotEqualSearch(String field, Object value) {
		criterionList.add(Expression.not(Expression.eq(field, value)));

	}

	/**
	 * 包含查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addInSearch(String field, Object[] value) {
		criterionList.add(Expression.in(field, value));
	}

	/**
	 * 不包含查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addNotInSearch(String field, Object[] value) {
		criterionList.add(Expression.not(Expression.in(field, value)));
	}

	/**
	 * 或查询 -field1是数组，field2是数组
	 * 
	 * @param field1
	 * @param value1
	 * @param field2
	 * @param value2
	 * @author meng ying jie
	 */
	public void addOrInSearch(String orField1, Object[] orValue1,
			String orField2, Object[] orValue2) {

		criterionList.add(Expression.or(Expression.in(orField1, orValue1),
				Expression.in(orField2, orValue2)));
	}

	/**
	 * 或查询 -field1是单个对象，field2是数组
	 * 
	 * @param field1
	 * @param value1
	 * @param field2
	 * @param value2
	 */
	public void addOrSearch(String orField1, Object orValue1, String orField2,
			Object[] orValue2) {
		Criterion exp1 = null;

		if (orValue1 == null) {
			exp1 = Expression.isNull(orField1);
		} else {
			exp1 = Expression.eq(orField1, orValue1);
		}

		Criterion exp2 = null;
		if (orValue2 == null) {
			exp2 = Expression.isNull(orField2);
		} else {
			exp2 = Expression.in(orField2, orValue2);
		}

		criterionList.add(Expression.or(exp1, exp2));
	}

	/**
	 * 或模糊查询
	 * 
	 * @param field1
	 * @param value1
	 * @param field2
	 * @param value2
	 */
	public void addOrLikeSearch(String orField1, String orValue1,
			String orField2, String orValue2) {
		Criterion exp1 = null;
		Criterion exp2 = null;

		if (orValue1 == null) {
			exp1 = Expression.isNull(orField1);
		} else {
			exp1 = Expression.like(orField1, orValue1, MatchMode.ANYWHERE);
		}
		if (orValue2 == null) {
			exp2 = Expression.isNull(orField2);
		} else {
			exp2 = Expression.like(orField2, orValue2, MatchMode.ANYWHERE);
		}

		criterionList.add(Expression.or(exp1, exp2));
	}

	/**
	 * 或查询 -field1是单个对象，field2是单个对象
	 * 
	 * @param field1
	 * @param value1
	 * @param field2
	 * @param value2
	 */
	public void addOrSearch(String orField1, Object orValue1, String orField2,
			Object orValue2) {
		Criterion exp1 = null;
		Criterion exp2 = null;

		if (orValue1 == null) {
			exp1 = Expression.isNull(orField1);
		} else {
			exp1 = Expression.eq(orField1, orValue1);
		}
		if (orValue2 == null) {
			exp2 = Expression.isNull(orField2);
		} else {
			exp2 = Expression.eq(orField2, orValue2);
		}

		criterionList.add(Expression.or(exp1, exp2));
	}

	/*
	 * 首先两个field and 操作，然后和第三个or操作
	 */
	public void addFirstAndLastOrSearch(String andFiled1, Object andValue1,
			String andFiled2, Object andValue2, String orField, Object orValue) {
		Criterion exp1 = null;
		Criterion exp2 = null;
		Criterion exp3 = null;
		Criterion exp4 = null;
		Criterion exp5 = null;

		if (andValue1 == null) {
			exp1 = Expression.isNull(andFiled1);
		} else {
			exp1 = Expression.eq(andFiled1, andValue1);
		}
		if (andValue2 == null) {
			exp2 = Expression.isNull(andFiled2);
		} else {
			exp2 = Expression.eq(andFiled2, andValue2);
		}

		exp3 = Expression.and(exp1, exp2);

		// 再进行or操作
		exp4 = Expression.eq(orField, orValue);

		exp5 = Expression.or(exp3, exp4);

		criterionList.add(exp5);
	}

	/**
	 * 区间查询
	 * 
	 * @param field
	 * @param lo
	 * @param hi
	 */
	public void addBetweenSearch(String field, Object low, Object high) {
		criterionList.add(Expression.between(field, low, high));
	}

	/**
	 * 大于等于查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addGreatEqualSearch(String field, Object value) {
		criterionList.add(Expression.ge(field, value));
	}

	/**
	 * 大于查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addGreatSearch(String field, Object value) {
		criterionList.add(Expression.gt(field, value));
	}

	/**
	 * 小于等于查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addLessEqualSearch(String field, Object value) {
		criterionList.add(Expression.le(field, value));
	}

	/**
	 * 小于查询
	 * 
	 * @param field
	 * @param value
	 */
	public void addLessSearch(String field, Object value) {
		criterionList.add(Expression.lt(field, value));
	}

	/**
	 * 一属性大于等于另一属性查询
	 * 
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void addGreatEqualPropertySearch(String propertyName,
			String otherPropertyName) {
		criterionList.add(Expression
				.geProperty(propertyName, otherPropertyName));
	}
	/**
	 * 一属性等于另一属性查询
	 * 
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void addEqualPropertySearch(String propertyName,
			String otherPropertyName) {
		criterionList.add(Expression
				.eqProperty(propertyName, otherPropertyName));
	}

	/**
	 * 一属性大于另一属性查询
	 * 
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void addGreatPropertySearch(String propertyName,
			String otherPropertyName) {
		criterionList.add(Expression
				.gtProperty(propertyName, otherPropertyName));
	}

	/**
	 * SQL语句查询
	 * 
	 * @param sql
	 */
	public void addSqlSearch(String sql) {
		criterionList.add(Expression.sql(sql));
	}

	/**
	 * 属性为空查询
	 * 
	 * @param field
	 */
	public void addIsNull(String field) {
		criterionList.add(Expression.isNull(field));
	}

	/**
	 * 属性不为空查询
	 * 
	 * @param field
	 */
	public void addIsNotNull(String field) {
		criterionList.add(Expression.isNotNull(field));
	}

	private void addAliasToMap(String objectName, String aliasName) {
		if (aliasMap == null) {
			aliasMap = new HashMap<String, String>();
		}

		if (aliasMap.containsKey(objectName)) {
			return;
		}

		aliasMap.put(objectName, aliasName);
	}

	/**
	 * 跨表，等于查询
	 */
	public void addAliasEqualSearch(String objectName, String aliasName,
			String field, Object value) {
		CriteriaAlias obj = new CriteriaAlias();
		obj.setObjectName(objectName);
		obj.setAliasName(aliasName);
		obj.setCriterion(Expression.eq(aliasName + "." + field, value));
		criteriaAliasList.add(obj);

		addAliasToMap(objectName, aliasName);
	}

	/**
	 * 跨表，不等于查询
	 */
	public void addAliasNotEqualSearch(String objectName, String aliasName,
			String field, Object value) {
		CriteriaAlias obj = new CriteriaAlias();
		obj.setObjectName(objectName);
		obj.setAliasName(aliasName);
		obj.setCriterion(Expression.not(Expression.eq(aliasName + "." + field,
				value)));
		criteriaAliasList.add(obj);

		addAliasToMap(objectName, aliasName);
	}

	/**
	 * 跨表，模糊查询
	 */
	public void addAliasLikeSearch(String objectName, String aliasName,
			String field, Object value) {
		CriteriaAlias obj = new CriteriaAlias();
		obj.setObjectName(objectName);
		obj.setAliasName(aliasName);
		obj.setCriterion(Expression.like(aliasName + "." + field, "%" + value
				+ "%"));
		criteriaAliasList.add(obj);

		addAliasToMap(objectName, aliasName);
	}

	/**
	 * 排序
	 * 
	 * @param sortName
	 * @param sortOrder
	 */
	public void addSort(String sortName, String sortOrder) {

		if (sortMap == null) {
			sortMap = new LinkedHashMap<String, String>();
		}

		sortMap.put(sortName, sortOrder);
	}
	
	/**
	 * 设置排序
	 * 
	 * @param sortName
	 * @param sortOrder
	 */
	public void setSort(String sortName, String sortOrder) {

		if (sortMap == null) {
			sortMap = new LinkedHashMap<String, String>();
		}

		sortMap.put(sortName, sortOrder);
	}

	public Map<String, String> getSortMap() {
		return sortMap;
	}

	public void setSortMap(Map<String, String> sortMap) {
		this.sortMap = sortMap;
	}

	public void clearSortMap() {
		if (sortMap == null) {
			sortMap = new HashMap<String, String>();
		}

		sortMap.clear();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<Criterion> getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(List<Criterion> criterionList) {
		this.criterionList = criterionList;
	}

	public List<CriteriaAlias> getCriteriaAliasList() {
		return criteriaAliasList;
	}

	public void setCriteriaAliasList(List<CriteriaAlias> criteriaAliasList) {
		this.criteriaAliasList = criteriaAliasList;
	}

	public Map<String, String> getAliasMap() {
		return aliasMap;
	}

	public void setAliasMap(Map<String, String> aliasMap) {
		this.aliasMap = aliasMap;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public String getHqlString() {
		return hqlString;
	}

	public void setHqlString(String hqlString) {
		this.hqlString = hqlString;
	}

	public String getSqlString() {
		return sqlString;
	}

	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}

	public List<ColumnValue> getQueryConditionList() {
		return queryConditionList;
	}

	public void setQueryConditionList(List<ColumnValue> queryConditionList) {
		this.queryConditionList = queryConditionList;
	}

	public void addQueryCondition(String columeName, Object queryCondition) {
		this.queryConditionList
				.add(new ColumnValue(columeName, queryCondition));
	}

	public void clearQueryCondition() {
		this.queryConditionList.clear();
	}

	public List<ColumnType> getScalarList() {
		return scalarList;
	}

	public void setScalarList(List<ColumnType> scalarList) {
		this.scalarList = scalarList;
	}

	public List<PageFooterColumn> getPageFooter() {
		return pageFooter;
	}

	public void setPageFooter(List<PageFooterColumn> pageFooter) {
		this.pageFooter = pageFooter;
	}

	public QueryPage addScalar(String scalar) {
		this.scalarList.add(new ColumnType(scalar));
		return this;
	}

	public QueryPage addScalar(String scalar, Type type) {
		this.scalarList.add(new ColumnType(scalar, type));
		return this;
	}

	public void clearScalar() {
		this.scalarList.clear();
	}
	
	public void addPageFooterColumn(PageFooterColumn column) {
		pageFooter.add(column);
	}

	@SuppressWarnings("unchecked")
	public List getAllNotNullArg() {
		List args = new ArrayList();
		for (ColumnValue cv : this.queryConditionList) {
			if (cv.isNotNullValue())
				args.add(cv.getValue());
		}
		
		for (PageFooterColumn column : pageFooter) {
			args.add(column);
		}
		
		return args;
	}

	public boolean isCalCount() {
		return calCount;
	}

	public void setCalCount(boolean calCount) {
		this.calCount = calCount;
	}

	public Class<?> getDtoClass() {
		return dtoClass;
	}

	public void setDtoClass(Class<?> dtoClass) {
		this.dtoClass = dtoClass;
	}

	public RowMapper getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
	}

}
