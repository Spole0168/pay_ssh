package com.ibs.portal.framework.util;

import java.util.Random;

/**
 * 获取一个随机数
 * @author zjblue
 *
 */
public class RandomUtils {
	
	private RandomUtils(){};
	
	public static Random random = new Random(100000);
	
	/**
	 * 获取随机数产生器
	 * 
	 * @return
	 */
	public static Random getRandom() {
		return RandomUtils.random;
	}
}
