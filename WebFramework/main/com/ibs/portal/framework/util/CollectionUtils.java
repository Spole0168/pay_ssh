package com.ibs.portal.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.exception.ServiceException;

public class CollectionUtils {
	
	protected final static Logger logger = LoggerFactory.getLogger(CollectionUtils.class);
	
	/**
	 * 根据指定的key字段，（以list的形式传入的数据字典）与数据字段中指定的key字段比较如果相同，那么就将
	 * 数据字段指定的value字段放入传入的collection指定的字段中去,注意只能是stirng类型作为属性
	 * @param data
	 * @param dataKeyName
	 * @param dataValueName
	 * @param cols
	 * @param colKeyName
	 * @param colValName
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	public static void transformCollection(Collection data, String dataKeyName, String dataValueName, 
			Collection cols, String colKeyName, String colValName) {
		
		try {
			Hashtable map = new Hashtable();//保存指定的参照字段（key）和返回字段数据（value）
			//循环cols获取数据字段对象，获取指定的参照字段 和 返回字段数据 放入map
			for (Iterator iterator = cols.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();//获取对象，这里不清楚是什么类型
				Class c1 = object.getClass();//获取这个对象的class实例
				String kString =  upperCaseFirstWord(colKeyName);//指定数据字典key首字母大写
				String vString =  upperCaseFirstWord(colValName);//指定数据字典value首字母大写
				String methodNameForKey = "get"+ kString;//获取key字段值
				String methodNameForValue = "get"+ vString;//获取value字段值
				Method method = null; 
				method = c1.getMethod(methodNameForKey,null);//获取指定key钩子方法对象
				Object key = method.invoke(object, null);
				method = c1.getMethod(methodNameForValue,null);//获取指定value钩子方法对象
				Object value = method.invoke(object, null);
				map.put(key.toString(), value.toString());//放入map中
			}
			transformCollection(data, dataKeyName, dataValueName, map);//将对应的value放入data中
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
	}
	
	/**
	 * 多数据字典 一个List中 多个字段获取 value
	 * @param data
	 * @param dataKeyName
	 * @param dataValueName
	 * @param cols
	 * @param colKeyName
	 * @param colValName
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 */
	public static void transformCollection(Collection data, String[] dataKeyName, String[] dataValueName, 
			Collection[] cols, String colKeyName, String colValName) {
		
		try {
			if(dataKeyName.length != dataValueName.length){
				throw new ServiceException("dataKeyName length != dataValueName length");
			}
			for (int i = 0; i < cols.length; i++) {
				Collection col = cols[i];
				for (int j = 0; j < dataKeyName.length; j++) {
					String string1 = dataKeyName[j];
					String string2 = dataValueName[j];
					transformCollection(data, string1, string2, col, colKeyName, colValName);
				}
			}
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
	}
	


	/**
	 * 根据指定的key字段，（以map形式传入的数据字典）与数据字段中指定的key字段比较如果相同，那么就将
	 * 数据字段指定的value字段放入传入的collection指定的字段中去
	 * @param data
	 * @param dataKeyName
	 * @param dataValueName
	 * @param maps
	 * @param mapKeyName
	 * @param mapValName
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	public static void transformCollection(Collection data, String dataKeyName, String dataValueName, 
			Map maps) {
		
		try {
			//循环data，用于将value放入对应的字段中去
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();//获取data中的对象
				Class c = object.getClass();//获得对象的class对象
				String dkString = upperCaseFirstWord(dataKeyName);//首字母大写
				String getPropertyMethodName = "get"+dkString;//拼接指定的key字段的钩子方法名称
				Method m1 = c.getMethod(getPropertyMethodName, null);//获取钩子方法对象
				Object key = m1.invoke(object, null);//执行钩子方法用于获取key的值
				if(key != null && !"".equals(key)){
					boolean flag =  maps.containsKey(key.toString());//将这个值与map中的key做判断是否存在
					if(flag){//如果存在
						String dvString = upperCaseFirstWord(dataValueName);//首字母大写
						String setPropertyMethodName = "set"+dvString;//凭借key所对应的value字段钩子方法名称
						//获取这个value字段的钩子方法，参数是刚才获得的类型class对象
						Method m2 = c.getMethod(setPropertyMethodName, new Class[]{String.class});
						//value的钩子方法，将maps中key所对应的value值放入data对象中对应的dataValueName字段中
						m2.invoke(object, new Object[]{maps.get(key.toString())});
					}
				}
			}
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
	}
	/**
	 * 首字母大写
	 * @param string
	 * @return
	 */
	private static String upperCaseFirstWord(String string){
		String rString =string.substring(0,1).toUpperCase()+string.substring(1);
		return rString;
	}
	
	public static <K, V> List<K> asList(final java.util.Collection<V> coll, 
			final Class<K> keyType,
			final Class<V> valueType,
			final String keyMethodName) {
		
	    if (coll == null || coll.size() == 0)
	    	return new ArrayList<K>();

		final List<K> list = new ArrayList<K>(coll.size());

		try {
			// return the Method to invoke to get the key for the map
			Method method = valueType.getMethod(keyMethodName);

			for (final V value : coll) {

				Object object;
				object = method.invoke(value);
				@SuppressWarnings("unchecked")
				final K key = (K) object;
				list.add(key);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	    
	    return list;
	}
	
	public static <K, V> Map<K, V> asMap(final java.util.Collection<V> coll,
	        final Class<K> keyType,
	        final Class<V> valueType,
	        final String keyMethodName) {

		if (coll == null || coll.size() == 0)
			return new LinkedHashMap<K, V>();

		final Map<K, V> map = new LinkedHashMap<K, V>(coll.size());

		try {
			// return the Method to invoke to get the key for the map
			Method method = valueType.getMethod(keyMethodName);

			for (final V value : coll) {

				Object object;
				object = method.invoke(value);
				@SuppressWarnings("unchecked")
				final K key = (K) object;
				map.put(key, value);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	    
	    return map;
	}

	public static <T> List<List<T>> splitList(List<T> values, Integer size) {
		if (values == null || values.size() == 0)
			return null;

		if (size == null || size <= 0)
			size = 1000;
		
		List<List<T>> results = new ArrayList<List<T>>();
		
		for (int i = 0; i < values.size(); ++i) {
			if (i % size == 0) {
				List<T> result = new ArrayList<T>();
				results.add(result);
			}
			
			results.get(i / size).add(values.get(i));
		}

		return results;
	}

	public static Double max(Double[] array) {
		Double max = null;
		
		if (array == null || array.length == 0)
			return max;
		
		for (int i = 0; i < array.length; ++i) {
			Double d = array[i];
			if (d == null) {
				continue;
			}
			if (max == null || d > max) {
				max = d;
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		List<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");
		test.add("5");
		test.add("6");
		test.add("7");
		test.add("8");
		test.add("9");
		test.add("10");
		
		List<List<String>> results = CollectionUtils.splitList(test, 3);
	
		System.out.println(results.size());
		
		System.out.println(max(new Double[]{null, 0.3d, new Double(1)}));
		System.out.println(max(new Double[]{null, null, new Double(1)}));
		System.out.println(max(new Double[]{null, null, null}));
	}
}
















