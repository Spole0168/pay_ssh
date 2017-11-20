package com.ibs.portal.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * 扩展Apache Commons BeanUtils
 * 
 * @author luoyue
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	protected static final Log logger = LogFactory.getLog(BeanUtils.class);

	private BeanUtils() {
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Field getDeclaredField(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Field getDeclaredField(Class<?> clazz, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName()
				+ '.' + propertyName);
	}

	/**
	 * 暴力获取对象变量值,忽略private,protected修饰符的限制.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Object forceGetProperty(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = getDeclaredField(object, propertyName);
		Object result = null;

		// if (null != field) {
		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			if (logger.isInfoEnabled()) {
				logger.info("error wont' happen");
			}
		}
		field.setAccessible(accessible);
		/*
		 * }else{ logger.warn("Class " + object.getClass().getSimpleName() +
		 * " No Such Field:" + propertyName); }
		 */
		return result;

	}

	/**
	 * 暴力设置对象变量值,忽略private,protected修饰符的限制.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static void forceSetProperty(Object object, String propertyName,
			Object newValue) throws NoSuchFieldException {
		if (propertyName == null || "".equals(propertyName)) {
			return;
		}
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = null;
		try {
			field = getDeclaredField(object, propertyName);
		} catch (NoSuchFieldException e) {

		}
		if (null != field) {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			try {
				field.set(object, newValue);
			} catch (IllegalAccessException e) {
				if (logger.isInfoEnabled()) {
					logger.info("Error won't happen");
				}
			}
			field.setAccessible(accessible);
		}
	}

	/**
	 * 暴力调用对象函数,忽略private,protected修饰符的限制.
	 * 
	 * @throws NoSuchMethodException
	 *             如果没有该Method时抛出.
	 */
	public static Object invokePrivateMethod(Object object, String methodName,
			Object... params) throws NoSuchMethodException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class<?>[] types = new Class<?>[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}

		Class<?> clazz = object.getClass();
		Method method = null;
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {
				// 方法不在当前类定义,继续向上转型
			}
		}

		if (method == null)
			throw new NoSuchMethodException("No Such Method:"
					+ clazz.getSimpleName() + methodName);

		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		method.setAccessible(accessible);
		return result;
	}

	/**
	 * 按Filed的类型取得Field列表.
	 */
	public static List<Field> getFieldsByType(Object object, Class<?> type) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(type)) {
				list.add(field);
			}
		}
		return list;
	}

	/**
	 * 按FiledName获得Field的类型.
	 */
	public static Class<?> getPropertyType(Class<?> type, String name)
			throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	/**
	 * 获得field的getter函数名称.
	 */
	public static String getGetterName(Class<?> type, String fieldName) {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");

		if (type.getName().equals("boolean")) {
			return "is" + StringUtils.capitalize(fieldName);
		} else {
			return "get" + StringUtils.capitalize(fieldName);
		}
	}

	/**
	 * 获得field的getter函数,如果找不到该方法,返回null.
	 */
	public static Method getGetterMethod(Class<?> type, String fieldName) {
		try {
			return type.getMethod(getGetterName(type, fieldName));
		} catch (NoSuchMethodException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			// logger.error(e.getMessage(), e);
		}
		return null;
	}

	// add by 
	/**
	 * 是否基本类型
	 * 
	 * @param propertyType
	 * @return
	 */
	public static boolean isPrimitiveOrLangType(Class propertyType) {
		return propertyType.isPrimitive() || propertyType.equals(String.class)
				|| propertyType.equals(Integer.class)
				|| propertyType.equals(Character.class)
				|| propertyType.equals(Float.class)
				|| propertyType.equals(Boolean.class)
				|| propertyType.equals(Byte.class)
				|| propertyType.equals(Double.class)
				|| propertyType.equals(Short.class)
				|| propertyType.equals(Number.class)
				|| propertyType.equals(BigDecimal.class)
				|| propertyType.equals(Long.class)
				|| propertyType.equals(Date.class)
				|| propertyType.equals(Timestamp.class);
	}

	// add by 
	/**
	 * 拷贝基本类型属性
	 * 
	 * @param vo
	 * @param po
	 * @throws InvocationTargetException
	 */
	public static void copyBasicTypeProperties(Object toObject,
			Object fromobject) throws InvocationTargetException {
		if (toObject == null || fromobject == null) {
			return;
		}

		PropertyDescriptor[] poDescriptors = PropertyUtils
				.getPropertyDescriptors(fromobject.getClass());
		for (int i = 0; i < poDescriptors.length; i++) {
			String propertyName = poDescriptors[i].getName();
			Class propertyType = poDescriptors[i].getPropertyType();

			// if (logger.isDebugEnabled()) {
			// logger.debug("fromobject:"
			// + fromobject.getClass().getSimpleName() + ", property:"
			// + propertyName + "; toObject:"
			// + toObject.getClass().getSimpleName() + ", property:"
			// + propertyName);
			// }

			try {
				if (isPrimitiveOrLangType(propertyType)
						|| propertyType.equals(Date.class)) {

					Object propertyValue = PropertyUtils.getSimpleProperty(
							fromobject, propertyName);

					PropertyUtils.setSimpleProperty(toObject, propertyName,
							propertyValue);
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
	public static void copyBasicTypeProperties(Object toObject,
			Object fromobject, Map<String, String> excludeParm)
			throws InvocationTargetException {
		if (toObject == null || fromobject == null) {
			return;
		}

		PropertyDescriptor[] poDescriptors = PropertyUtils
				.getPropertyDescriptors(fromobject.getClass());
		for (int i = 0; i < poDescriptors.length; i++) {
			try {
				String propertyName = poDescriptors[i].getName();
				Class propertyType = poDescriptors[i].getPropertyType();
				if (excludeParm != null
						&& excludeParm.get(propertyName) != null) {

					continue;

				}
				if (isPrimitiveOrLangType(propertyType)
						|| propertyType.equals(Date.class)) {

					Object propertyValue = PropertyUtils.getSimpleProperty(
							fromobject, propertyName);

					PropertyUtils.setSimpleProperty(toObject, propertyName,
							propertyValue);
				} else {
					// did nothing
				}
			} catch (Exception e) {
				continue;
			}
		}

	}
}
