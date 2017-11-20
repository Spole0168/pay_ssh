package com.ibs.common.module.frameworkimpl.file.dto;

import java.io.InputStream;

public class DownloadDto implements java.io.Serializable {

	private static final long serialVersionUID = 1398378987157549732L;

	private String fileId;
	private String fileName;
	private InputStream inputStream;
	private String contentType;
	private Long contentLength;
	private Integer bufferSize;
	
	private String realName;
	private String webFileName;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(Integer bufferSize) {
		this.bufferSize = bufferSize;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public String getWebFileName() {
		return webFileName;
	}

	public void setWebFileName(String webFileName) {
		this.webFileName = webFileName;
	}

}
