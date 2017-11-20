package com.ibs.portal.framework.server.metadata;

public class ColumnValue {
	String columnName;
	Object value;
	
	
	public ColumnValue() {
	}

	public ColumnValue(String columnName, Object value) {
		super();
		this.columnName = columnName;
		this.value = value;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public boolean isNullValue(){
		return this.value==null;
	}
	
	public boolean isNotNullValue(){
		return this.value!=null;
	}
}
