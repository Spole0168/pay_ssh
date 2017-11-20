package com.ibs.portal.framework.server.metadata;

import java.io.Serializable;


/**
 * 向浏览器返回的json数据容器
 * 
 * @author luoyue
 * 
 */
public class JsonContainer extends BaseJsonObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3041667317728004713L;

	private String message;
	private Object data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
