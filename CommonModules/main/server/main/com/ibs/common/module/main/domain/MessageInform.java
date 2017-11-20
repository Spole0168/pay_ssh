package com.ibs.common.module.main.domain;

public class MessageInform extends
		com.ibs.portal.framework.server.domain.BaseEntity {

	private static final long serialVersionUID = 7244496630227929516L;

	private String informUrl;
	private String informLevel;
	private String informName;
	private Boolean status;
	private Integer sort;

	public String getInformUrl() {
		return informUrl;
	}

	public void setInformUrl(String informUrl) {
		this.informUrl = informUrl;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getInformName() {
		return informName;
	}

	public void setInformName(String informName) {
		this.informName = informName;
	}

	public String getInformLevel() {
		return informLevel;
	}

	public void setInformLevel(String informLevel) {
		this.informLevel = informLevel;
	}
}
