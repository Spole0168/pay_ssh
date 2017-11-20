package com.ibs.common.module.frameworkimpl.mail.dto;

import java.util.Date;

public class MailMessageDto {
	private String id;
	private Date createTime;
	private String toAddrs;
	private String subject;
	private String text;
	private Long useHtml;
	private String attachmentDir;
	private Long failureCount;
	private Date failureTime;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getToAddrs() {
		return this.toAddrs;
	}

	public void setToAddrs(String toAddrs) {
		this.toAddrs = toAddrs;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getUseHtml() {
		return this.useHtml;
	}

	public void setUseHtml(Long useHtml) {
		this.useHtml = useHtml;
	}

	public String getAttachmentDir() {
		return this.attachmentDir;
	}

	public void setAttachmentDir(String attachmentDir) {
		this.attachmentDir = attachmentDir;
	}

	public Long getFailureCount() {
		return this.failureCount;
	}

	public void setFailureCount(Long failureCount) {
		this.failureCount = failureCount;
	}

	public Date getFailureTime() {
		return this.failureTime;
	}

	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}

}
