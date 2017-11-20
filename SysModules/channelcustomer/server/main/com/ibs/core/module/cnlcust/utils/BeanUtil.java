package com.ibs.core.module.cnlcust.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
/**
 * JavaBean 属性值简单复制
 * 可用于新增，修改业务
 * 
 * @author kaijiezhao
 *
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class BeanUtil extends BeanUtils {

	protected static final Log logger = LogFactory.getLog(BeanUtil.class);

	private BeanUtil() {
	}

	/**
	 * 是否基本类型
	 * 
	 * @param propertyType
	 * @return
	 */
	public static boolean isPrimitiveOrLangType(Class<?> propertyType) {
		return propertyType.isPrimitive() || propertyType.equals(String.class) || propertyType.equals(Integer.class)
			|| propertyType.equals(Character.class) || propertyType.equals(Float.class)
			|| propertyType.equals(Boolean.class) || propertyType.equals(Byte.class)
			|| propertyType.equals(Double.class) || propertyType.equals(Short.class)
			|| propertyType.equals(Number.class) || propertyType.equals(BigDecimal.class)
			|| propertyType.equals(Long.class) || propertyType.equals(Date.class)
			|| propertyType.equals(Timestamp.class);
	}

	/**
	 * 拷贝基本类型属性
	 * @param vo
	 * @param po
	 * @throws InvocationTargetException
	 */
	public static void copyBasicTypeProperties(Object toObject, Object fromobject) throws InvocationTargetException {
		if (toObject == null || fromobject == null) {
			return;
		}

		PropertyDescriptor[] poDescriptors = PropertyUtils.getPropertyDescriptors(fromobject.getClass());
		for (int i = 0; i < poDescriptors.length; i++) {
			String propertyName = poDescriptors[i].getName();
			Class<?> propertyType = poDescriptors[i].getPropertyType();
			try {
				if (isPrimitiveOrLangType(propertyType) || propertyType.equals(Date.class)) {
					Object propertyValue = PropertyUtils.getSimpleProperty(fromobject, propertyName);
					if (propertyValue == null)
						continue;
					PropertyUtils.setSimpleProperty(toObject, propertyName, propertyValue);
				} else {
					// did nothing
				}
			} catch (Exception e) {
				continue;
			}
		}

	}

	// add by
	/**
	 * 拷贝基本类型属性
	 * 
	 * @param toObject
	 * @param fromobject
	 * @param excludeParm
	 *            那些属性不拷贝
	 * @throws InvocationTargetException
	 */
	public static void copyBasicTypeProperties(Object toObject, Object fromobject, Map<String, String> excludeParm)
			throws InvocationTargetException {
		if (toObject == null || fromobject == null) {
			return;
		}

		PropertyDescriptor[] poDescriptors = PropertyUtils.getPropertyDescriptors(fromobject.getClass());
		for (int i = 0; i < poDescriptors.length; i++) {
			try {
				String propertyName = poDescriptors[i].getName();
				Class<?> propertyType = poDescriptors[i].getPropertyType();
				if (excludeParm != null && excludeParm.get(propertyName) != null) {
					continue;
				}
				if (isPrimitiveOrLangType(propertyType) || propertyType.equals(Date.class)) {
					Object propertyValue = PropertyUtils.getSimpleProperty(fromobject, propertyName);
					if (propertyValue == null)
						continue;
					PropertyUtils.setSimpleProperty(toObject, propertyName, propertyValue);
				} else {
					// did nothing
				}
			} catch (Exception e) {
				continue;
			}
		}

	}
}
