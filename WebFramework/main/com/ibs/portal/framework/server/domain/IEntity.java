package com.ibs.portal.framework.server.domain;

import java.io.Serializable;

public interface IEntity extends Serializable, Comparable<Object>{
	/**
	 * 获得ID
	 *
	 * @return 获得ID
	 */
	String getId();
	
}
