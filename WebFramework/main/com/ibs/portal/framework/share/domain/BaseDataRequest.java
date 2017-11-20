package com.ibs.portal.framework.share.domain;

import java.util.List;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class BaseDataRequest extends BaseEntity {
	private static final long serialVersionUID = 1L;
	  
	java.lang.String baseDataType;
	  
	java.util.Date beginModifiedTime;
	  
	java.util.Date endModifiedTime;
	  
	java.lang.String updatedType;
	  
	@SuppressWarnings("unchecked")
	List basedataFilter;
	  
	public java.lang.String getBaseDataType() {
		return baseDataType;
	}

	public void setBaseDataType(java.lang.String baseDataType) {
		this.baseDataType = baseDataType;
	}

	public java.util.Date getBeginModifiedTime() {
		return beginModifiedTime;
	}

	public void setBeginModifiedTime(java.util.Date beginModifiedTime) {
		this.beginModifiedTime = beginModifiedTime;
	}

	public java.util.Date getEndModifiedTime() {
		return endModifiedTime;
	}

	public void setEndModifiedTime(java.util.Date endModifiedTime) {
		this.endModifiedTime = endModifiedTime;
	}

	public java.lang.String getUpdatedType() {
		return updatedType;
	}

	public void setUpdatedType(java.lang.String updatedType) {
		this.updatedType = updatedType;
	}

	public List getBasedataFilter() {
		return basedataFilter;
	}

	public void setBasedataFilter(List basedataFilter) {
		this.basedataFilter = basedataFilter;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public java.lang.Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(java.lang.Long totalSize) {
		this.totalSize = totalSize;
	}

	int pageNo;
	  
	  int pageSize;
	  
	  java.lang.Long totalSize;
	  
	  
}
