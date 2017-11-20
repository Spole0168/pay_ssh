package com.ibs.portal.framework.server.action;

/**
 * 自定义http请求-响应参数键值
 * 
 * @author luoyue
 * 
 */
public interface CustomHttpConstants {

	public static final String X_REQUEST_TYPE = "X_REQUEST_TYPE"; // 请求类型
	public static final String X_REQUEST_METHOD = "X_REQUEST_METHOD"; // 请求方法
	public static final String X_REQUEST_PARAM = "X_REQUEST_PARAM"; // 请求参数
	public static final String X_REQUEST_USER = "X_REQUEST_USER"; // 请求用户
	public static final String X_REQUEST_AUTH = "X_REQUEST_AUTH"; // 请求授权信息

	public static final String X_RESPONSE_TYPE = "X_RESPONSE_TYPE"; // 响应类型
	public static final String X_RESPONSE_METHOD = "X_RESPONSE_METHOD"; // 响应方法
	public static final String X_RESPONSE_PARAM = "X_RESPONSE_PARAM"; // 响应参数
	public static final String X_RESPONSE_STATUS = "X_RESPONSE_STATUS"; // 响应执行状态
	public static final String X_RESPONSE_ERROR = "X_RESPONSE_ERROR"; // 响应错误信息
	public static final String X_RESPONSE_HOST = "X_RESPONSE_HOST"; // 响应主机
	public static final String X_RESPONSE_URL = "X_RESPONSE_URL"; // 响应URL
		
	public static final String X_RESPONSE_STATUS_SUCCESS = "SUCCESS"; // 响应执行状态-成功
	public static final String X_RESPONSE_STATUS_ERROR = "ERROR"; // 响应执行状态-出错

	public static final String DATA_RETURN_TYPE = "data"; // 有返回参数和返回数据
	public static final String PLAN_RETURN_TYPE = "plan"; // 只有返回参数
}
