package com.ibs.portal.framework.share.domain;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class BaseDataResponse extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	  
	int pageNo;
	  
	int totalPages;
	  
	java.lang.Long totalSize;
	  
	@SuppressWarnings("unchecked")
	java.util.List modifiedData;
	  
	@SuppressWarnings("unchecked")
	java.util.List deletedData;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public java.lang.Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(java.lang.Long totalSize) {
		this.totalSize = totalSize;
	}

	public java.util.List getModifiedData() {
		return modifiedData;
	}

	public void setModifiedData(java.util.List modifiedData) {
		this.modifiedData = modifiedData;
	}

	public java.util.List getDeletedData() {
		return deletedData;
	}

	public void setDeletedData(java.util.List deletedData) {
		this.deletedData = deletedData;
	}
	
	
	  
}
