package com.ibs.portal.framework.server.cache;

public class CacheEntry {
	//数据对象
	protected Object data;

	//键值
	protected Object key;

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @return the key
	 */
	public Object getKey() {
		return key;
	}

	/**
	 * @param data the data to set
	 */
	public void setData( Object data ) {
		this.data = data;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey( Object key ) {
		this.key = key;
	}



}
