package com.ibs.common.module.frameworkimpl.data.excel.setting;

/**
 * ExcelSettingColumn excel列信息
 * @author 
 * $Id: ExporterFactory.java 39293 2011-05-30 08:45:11Z  $
 */
public class ExcelSettingColumn {
	private String name;
	private String field;
	private Integer minLength;
	private Integer maxLength;
	private Class<?> type;
	private boolean required;
	private String columnName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
