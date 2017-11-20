package com.ibs.common.module.frameworkimpl.security.constant;

import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

public class PermissionConstant {
	/**
	 * 初始密码
	 */
	public static final String DEFAULT_PASSWORD = "111111";
	public static final String DEFAULT_PASSWORD_ENCRYPTED = "lueSGJZetyySpUndWjMBEg==";
	
	/**
	 * 总部
	 */
	public static final String ORG_TYPE_HEADQUARTER = "HEAD";
	public static final String ORG_TYPE_HEADQUARTER_VALUE = "总部";
	/**
	 * 部门
	 */
	public static final String ORG_TYPE_DEPARTMENT = "DEPARTMENT";
	public static final String ORG_TYPE_DEPARTMENT_VALUE = "部门";
	/**
	 * 管理区
	 */
	public static final String ORG_TYPE_MANAGE_AREA = "MANAGE_AREA";
	public static final String ORG_TYPE_MANAGE_AREA_VALUE = "管理区";
	
	/**
	 * 区域管理机构  
	 */
	public static final String TYPE_REGION_MANAGE ="REGION_MANAGE";
	public static final String TYPE_REGION_MANAGE_VALUE ="区域管理机构";
	/**
	 * 转运中心
	 */
	public static final String ORG_TYPE_TRANSFER_CENTER = "TRANSFER_CENTER";
	public static final String ORG_TYPE_TRANSFER_CENTER_VALUE = "转运中心";
	/**
	 * 分公司
	 */
	public static final String ORG_TYPE_SUB_BRANCH = "BRANCH";
	public static final String ORG_TYPE_SUB_BRANCH_VALUE = "分公司";
	/**
	 * 分部
	 */
	public static final String ORG_TYPE_SUB_DEPARTMENT = "SUB_DEPARTMENT";
	public static final String ORG_TYPE_SUB_DEPARTMENT_VALUE = "分部";
	
	public static final OptionObjectPair[] ORG_TYPE_PRIORITY = {
		new OptionObjectPair(ORG_TYPE_HEADQUARTER,ORG_TYPE_HEADQUARTER_VALUE),
		new OptionObjectPair(ORG_TYPE_MANAGE_AREA,ORG_TYPE_MANAGE_AREA_VALUE),
		new OptionObjectPair(TYPE_REGION_MANAGE,TYPE_REGION_MANAGE_VALUE),
		new OptionObjectPair(ORG_TYPE_TRANSFER_CENTER,ORG_TYPE_TRANSFER_CENTER_VALUE),
		new OptionObjectPair(ORG_TYPE_SUB_BRANCH,ORG_TYPE_SUB_BRANCH_VALUE),
		new OptionObjectPair(ORG_TYPE_SUB_DEPARTMENT,ORG_TYPE_SUB_DEPARTMENT_VALUE),
		
		};
	
	public static final OptionObjectPair[] USER_TYPE_PRIORITY = {
		new OptionObjectPair(User.USER_TYPE_EMPLOYEE,User.USER_TYPE_EMPLOYEE_VALUE),
		new OptionObjectPair(User.USER_TYPE_CUSTOMER,User.USER_TYPE_CUSTOMER_VALUE),
		new OptionObjectPair(User.USER_TYPE_AGENT,User.USER_TYPE_AGENT_VALUE),
		new OptionObjectPair(User.USER_TYPE_INTAGENT,User.USER_TYPE_INTAGENT_VALUE)
		};
	
	/**
	 * 最小密码长度
	 */
	public static final int MIN_PWD_LENGTH = 4;
	
	/**
	 * 密码过期提前提醒天数
	 */
	public static final int PWD_ACTIVE_WARN_DAY = 10;
	
	/**
	 * 密码有效时间
	 */
	public static final int PWD_ACTIVE_DAY = 60;
		
	public static final String USER_INUSED = "N";
	public static final String USER_INUSED_VALUE = "禁用";
	public static final String USER_USED = "Y";
	public static final String USER_USED_VALUE = "启用";
	
	public static final String AGENT_VALID="4";
	
}
