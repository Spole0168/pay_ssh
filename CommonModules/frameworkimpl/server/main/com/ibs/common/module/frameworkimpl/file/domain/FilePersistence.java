package com.ibs.common.module.frameworkimpl.file.domain;

import java.io.File;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class FilePersistence extends BaseEntity {

	private static final long serialVersionUID = -7828342320400004641L;
	
	private String fileName;// 文件名
	private String phsicalName;// 物理文件名
	private String ownerModule;// 模块
	private String operatorID;// 操作者ID
	private Date logDate;// 日志时间
	private File tempFile;// 临时文件对象，主要用户提供下载时使用
	private String contentType;//文件类型
	private Long contentLength;//文件大小，单位字节
	private String root;// 物理路径
	private String downLoadPath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPhsicalName() {
		return phsicalName;
	}

	public void setPhsicalName(String phsicalName) {
		this.phsicalName = phsicalName;
	}

	public String getOwnerModule() {
		return ownerModule;
	}

	public void setOwnerModule(String ownerModule) {
		this.ownerModule = ownerModule;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) { 
		this.operatorID = operatorID;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public File getTempFile() {
		return tempFile;
	}

	public void setTempFile(File tempFile) {
		this.tempFile = tempFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDownLoadPath() {
		return downLoadPath;
	}

	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	
}
