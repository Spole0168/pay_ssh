package com.ibs.common.module.frameworkimpl.intergration.dto;


/**********************************************
* Description: 	网关传输对象基础类
***********************************************/
public abstract class SGTransferObject {

	// 操作类型
	protected String opType;
	// 对象名称
	protected String objName;

	public SGTransferObject() {
		String packName = this.getClass().getName();
		this.objName = packName.replaceAll("[a-zA-Z0-9]{1,}[.]{1}", "");
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

}
