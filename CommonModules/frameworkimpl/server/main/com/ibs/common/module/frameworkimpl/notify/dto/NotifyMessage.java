package com.ibs.common.module.frameworkimpl.notify.dto;

public class NotifyMessage implements Comparable<NotifyMessage> {
	
	private String notifyType;
	private Integer notifyQty;
	private String notifyContent;
	private String notifyHref;

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyContent() {
		return notifyContent;
	}

	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}

	public String getNotifyHref() {
		return notifyHref;
	}

	public void setNotifyHref(String notifyHref) {
		this.notifyHref = notifyHref;
	}

	public Integer getNotifyQty() {
		return notifyQty;
	}

	public void setNotifyQty(Integer notifyQty) {
		this.notifyQty = notifyQty;
	}

	public int compareTo(NotifyMessage target) {
		if (target == null)
			return 1;

		return this.getNotifyQty().compareTo(target.getNotifyQty());
	}

}
