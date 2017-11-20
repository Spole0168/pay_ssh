package com.ibs.common.module.frameworkimpl.common;

public class Constants {

	public final static String	USER_CACHE						= "_user_cache_region";
	public final static String	ROLE_CACHE						= "_role_cache_region";
	public final static String	ORG_CACHE				        = "_org_cache_region";
	public final static String	APPLICATION_CACHE				= "_application_cache_region";
	public final static String	URL_RESOURCE_CACHE				= "_url_resource_cache_region";
	public final static String	BUTTON_RESOURCE_CACHE				= "_button_resource_cache_region";
	public final static String	MAC_CACHE				        = "_mac_cache_region";

	public final static String	PROPERTY_CACHE						= "_property_cache_region";
	public final static String	PROPERTY_CACHE_KEY					= PROPERTY_CACHE;

	public final static Integer AUTO_COMPLETE_ROW_SIZE = 10;
	public final static Integer JOB_PROCESS_ROW_SIZE = 1000;
	public final static Integer SQL_IN_ROW_SIZE = 100;	// IN (?,?...)，经过测试100条一查询效率最高
	public final static String  DEFAULT_NULL_VALUE = "N/A";
}
