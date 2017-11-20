package com.ibs.portal.framework.server.metadata;

import org.hibernate.type.Type;

public class ColumnType {
	String column;
	Type type;
	
	
	public ColumnType() {
	}

	public ColumnType(String column) {
		super();
		this.column = column;
	}

	public ColumnType(String column, Type type) {
		super();
		this.column = column;
		this.type = type;
	}
	
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isNullType(){
		return this.type==null;
	}
	
	public boolean isNotNullType(){
		return this.type!=null;
	}
	
}
