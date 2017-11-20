package com.ibs.common.module.frameworkimpl.redis.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBinder {

	private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);

	private ObjectMapper mapper;

	public JsonBinder() {
		mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_DEFAULT);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		
		
//		mapper.configure(SerializationConfig..WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置输出包含的属性
//		mapper.getSerializationConfig().setSerializationInclusion(inclusion);
//		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
//		mapper.getDeserializationConfig()
//				.set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
//						false);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
	 * 
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 */
	public <T> T fromJson(String json, Class<T> clazz) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}

		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + json, e);
			return null;
		}
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
	 * 
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String json, TypeReference<T> type) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}

		try {
			return (T)mapper.readValue(json, type);
		} catch (IOException e) {
			logger.warn("parse json string error:" + json, e);
			return null;
		}
	}

	/**
	 * 如果对象为Null,返回"null". 如果集合为空集合,返回"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}