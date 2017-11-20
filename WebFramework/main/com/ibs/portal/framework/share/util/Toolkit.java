package com.ibs.portal.framework.share.util;

import com.ibs.portal.framework.share.util.impl.JavaSerializer;


/**
 * 公共组件工具包，隐藏实现
 * 
 * @author 
 * 
 */
public class Toolkit {

	private static final Toolkit INSTANCE = new Toolkit();

	// 序列化器
	private ISerializer serializer = new JavaSerializer();

	/**
	 * 获取序列化器
	 * 
	 * @return
	 */
	public static ISerializer getSerializer() {
		return INSTANCE.serializer;
	}

}
