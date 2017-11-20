package com.ibs.common.module.frameworkimpl.log.common;

public enum RecordOperType {
	INSERT("INSERT"), UPDATE("UPDATE"), DELETE("DELETE");

	private RecordOperType(String value) {
		this.value = value;
	}

	private String value;
	
	public String getValue() {
		return value;
	}

}
