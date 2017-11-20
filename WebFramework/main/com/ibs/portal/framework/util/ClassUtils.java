package com.ibs.portal.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ClassUtils {
	private ClassUtils(){}

	/**
	 * 通过类名反射得到类元.
	 * 使用的类名应该和<code>java.lang.Class#getName()</code>一致
	 *
	 * @see java.lang.Class#getName()
	 * @param className 类名
	 * @return 类元
	 * @throws ClassNotFoundException 类不存在时抛出
	 */
	public static Class<?> forName(String className) throws ClassNotFoundException {
		if (className == null)
			throw new java.lang.NullPointerException("类名不能为空！");
		return Class.forName(className);
	}

	/**
	 * 通过标准类名反射得到类元.
	 * <p/>
	 * 与forName主要区别在于数组名使用："java.lang.Object[]"，在forName时使用："[Ljava.lang.Object;"
	 *
	 * @param className 标准类名
	 * @return 类元
	 * @throws ClassNotFoundException 类不存在时抛出
	 */
	public static Class<?> forCanonicalName(String className) throws ClassNotFoundException {
		if (className == null)
			throw new java.lang.NullPointerException("类名不能为空！");
		if (className.endsWith("[]"))
			className = "[L" + className.substring(0, className.length() - 2) + ";";
		return Class.forName(className);
	}

	/**
	 * 获取对象的属性值
	 *
	 * @param object 对象实例
	 * @param property 属性名
	 * @return 属性的值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getObjectProperty(Object object, String property) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null)
			return null;
		Method getter = getClassGetter(object.getClass(), property);
		return getter.invoke(object, new Object[0]);
	}

	/**
	 * 获取对象的多个属性值
	 *
	 * @param object 对象实例
	 * @param properties 属性列表
	 * @return 属性集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Map<String, ?> getObjectProperties(Object object, String[] properties) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null || properties == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>(properties.length);
		for (int i = 0, n = properties.length; i < n; i ++) {
			String prop = properties[i];
			if (prop != null && prop.length() > 0)
				map.put(prop, getObjectProperty(object, prop));
		}
		return map;
	}

	/**
	 * 获取对象的所有属性值
	 *
	 * @param object 对象实例
	 * @return 属性集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> getObjectAllProperties(Object object) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (object != null) {
			Method[] methods = object.getClass().getMethods();
			for (int i = 0, n = methods.length; i < n; i ++) {
				Method method = methods[i];
				if ((method.getModifiers() & Modifier.PUBLIC) == 1
						&& method.getDeclaringClass() != Object.class
						&& (method.getParameterTypes() == null || method
								.getParameterTypes().length == 0)) {
					String property = method.getName();
					if (property.startsWith("get")) {
						property = property.substring(3);
						String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
						map.put(lower, method.invoke(object, new Object[0]));
					} else if (property.startsWith("is")) {
						property = property.substring(2);
						String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
						map.put(lower, method.invoke(object, new Object[0]));
					}
				}
			}
		}
		return map;
	}

	/**
	 * 获取类的所有属性名
	 *
	 * @param clazz 类元
	 * @return 属性名称集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<String> getClassAllProperties(Class<?> clazz) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (clazz == null)
			return null;
		List<String> list = new ArrayList<String>();
		Method[] methods = clazz.getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			Method method = methods[i];
			if ((method.getModifiers() & Modifier.PUBLIC) == 1
					&& method.getDeclaringClass() != Object.class
					&& (method.getParameterTypes() == null || method
							.getParameterTypes().length == 0)) {
				String property = method.getName();
				if (property.startsWith("get") && ! "getClass".equals(property)) {
					property = property.substring(3);
					String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
					list.add(lower);
				} else if (property.startsWith("is")) {
					property = property.substring(2);
					String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
					list.add(lower);
				}
			}
		}
		return list;
	}

	/**
	 * 设置对象的属性值
	 *
	 * @param object 对象实例
	 * @param property 属性名
	 * @param value 待设置属性的值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setObjectProperty(Object object, String property, Object value) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null)
			return;
		Method setter = getClassSetter(object.getClass(), property);
		setter.invoke(object, new Object[]{value});
	}

	/**
	 * 设置对象的多个属性值
	 *
	 * @param object 对象实例
	 * @param properties 属性集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setObjectProperties(Object object, Map<String, Object> properties) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null)
			return;
		for (Iterator<Map.Entry<String, Object>> iterator = properties.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, Object> entry = iterator.next();
			String property = entry.getKey();
			Object value = entry.getValue();
			Method setter = getClassSetter(object.getClass(), property);
			setter.invoke(object, new Object[]{value});
		}
	}

	/**
	 * 查找getXXX与isXXX的属性Getter方法
	 *
	 * @param clazz 类元
	 * @param property 属性名
	 * @return 属性Getter方法
	 * @throws NoSuchMethodException Getter不存时抛出
	 */
	public static Method getClassGetter(Class<?> clazz, String property) throws NoSuchMethodException, SecurityException {
		if (clazz == null)
			throw new java.lang.NullPointerException("类元不能为空！");
		if (property == null)
			throw new java.lang.NullPointerException("属性名不能为空！");
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		try {
			return clazz.getMethod("get" + upper, new Class[0]);
		} catch (Exception e) {
			return clazz.getMethod("is" + upper, new Class[0]);
		}
	}

	/**
	 * 查找setXXX的属性Setter方法
	 *
	 * @param clazz 类元
	 * @param property 属性名
	 * @return 属性Setter方法
	 * @throws NoSuchMethodException Setter不存时抛出
	 */
	public static Method getClassSetter(Class<?> clazz, String property) throws NoSuchMethodException, SecurityException {
		if (clazz == null)
			throw new java.lang.NullPointerException("类元不能为空！");
		if (property == null)
			throw new java.lang.NullPointerException("属性名不能为空！");
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		return clazz.getMethod("set" + upper, new Class[0]);
	}
}
