package com.ibs.portal.framework.server.metadata;

import org.hibernate.type.Type;

public class PageFooterColumn {

	/**
	 * 查询列别名，与jqgrid中colModel中name保持一致
	 */
	private String name;
	/**
	 * 聚合函数表达式，如SUM(FEE),MAX(NO),count(xx),case xx then xx else xx等
	 */
	private String aggExpression;
	/**
	 * Hibernate 类型，用于Spring SQL Scalar
	 */
	private Type type;

	/**
	 * 用于HQL
	 * @param name
	 * @param aggExpression
	 */
	public PageFooterColumn(String name, String aggExpression) {
		this.name = name;
		this.aggExpression = aggExpression;
	}

	/**
	 * 用于SQL
	 * @param name
	 * @param aggExpression
	 * @param type
	 */
	public PageFooterColumn(String name, String aggExpression, Type type) {
		this.name = name;
		this.aggExpression = aggExpression;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAggExpression() {
		return aggExpression;
	}

	public void setAggExpression(String aggExpression) {
		this.aggExpression = aggExpression;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
