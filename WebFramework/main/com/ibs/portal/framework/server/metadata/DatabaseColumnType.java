package com.ibs.portal.framework.server.metadata;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.ibs.portal.framework.util.DateUtils;

public enum DatabaseColumnType {
	
	STRING("String", Hibernate.STRING, String.class),
	SHORT("Short", Hibernate.SHORT, Short.class),
	INTEGER("Integer", Hibernate.INTEGER, Integer.class),
	LONG("Long", Hibernate.LONG, Long.class),
	DOUBLE("Double", Hibernate.DOUBLE, Double.class),
	BIG_DECIMAL("BigDecimal", Hibernate.BIG_DECIMAL, BigDecimal.class),
	BOOLEAN("Boolean", Hibernate.BOOLEAN, Boolean.class),
	DATE("Date", Hibernate.DATE, Date.class),
	TIMESTAMP("Timestamp", Hibernate.TIMESTAMP, Timestamp.class)
	;

	private String value;
	private Type type;
	private Class<?> clz;
	
	private DatabaseColumnType(String value, Type type, Class<?> clz) {
		this.value = value;
		this.type = type;
		this.clz = clz;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}

	// *************************  util methods ************************* //
	
	public static Type getHibernateType(String value) {
		if (StringUtils.isEmpty(value))
			return null;
		for (DatabaseColumnType type: DatabaseColumnType.values())
			if (type.value.equals(value))
				return type.type;
		return null;
	}
	
	public static Object getObjectFromString(String value, String obj) {
		
		if (STRING.value.equals(value)) {
			return obj;
		} else if (SHORT.value.equals(value)) {
			return Short.valueOf(obj);
		} else if (INTEGER.value.equals(value)) {
			return Integer.valueOf(obj);
		} else if (LONG.value.equals(value)) {
			return Long.valueOf(obj);
		} else if (BIG_DECIMAL.value.equals(value)) {
			return BigDecimal.valueOf(Double.valueOf(obj));
		} else if (DOUBLE.value.equals(value)) {
			return Double.valueOf(obj);
		} else if (BOOLEAN.value.equals(value)) {
			return Boolean.valueOf(obj);
		} else if (DATE.value.equals(value)) {
			return DateUtils.convert(value);
		} else if (TIMESTAMP.value.equals(value)) {
			return DateUtils.convert(value);
		}
		
		return null;
	}
	
}
