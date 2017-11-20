package com.ibs.portal.framework.server.domain;

public interface ISynchronize {	
	/**
	 * 获得ID
	 *
	 * @return 获得ID
	 */
	String getId();
	/**
	 * 版本号
	 * @return 版本号
	 */
	public Long getVersionNo();
	/**
	 * 数据有效无效状态
	 * @return VALID：有效；IN_VALID:无效
	 */
	public String getStatus();
}
