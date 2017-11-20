package com.ibs.common.module.frameworkimpl.intergration.dto;

/**********************************************
* Description:  操作状态常量定义
***********************************************/
public interface OpTypeEnum {

	// 新增
	public static final String OP_TYPE_NEW = "N";
	// 修改
	public static final String OP_TYPE_MODIFY = "M";
	// 删除
	public static final String OP_TYPE_DELETE = "D";
	// 审核
	public static final String OP_TYPE_AUDIT = "A";
	// 启用
	public static final String OP_TYPE_ENABLE = "E";
	// 失效
	public static final String OP_TYPE_DISABLE = "L";
	// 红冲
	public static final String OP_TYPE_SUPPLEMENT = "S";
	// 转网络
	public static final String OP_TYPE_CHANGE_DEPT = "C";

}
