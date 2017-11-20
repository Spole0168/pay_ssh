package com.ibs.common.module.frameworkimpl.data.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class ImportInfo extends BaseEntity {

	private static final long serialVersionUID = -7828342320400004641L;

	private String fileId;
	private String excelId;
	private Date importTime;
	private Boolean defaultConfig;
	private String excelConfig;
	private String importParams;
	
	//增加3个变量对应3个统计列
	private String columnName1;
	private String columnName2;
	private String columnName3;
	private String columnName4;
	private String columnName5;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getExcelId() {
		return excelId;
	}

	public void setExcelId(String excelId) {
		this.excelId = excelId;
	}

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public Boolean getDefaultConfig() {
		return defaultConfig;
	}

	public void setDefaultConfig(Boolean defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

	public String getExcelConfig() {
		return excelConfig;
	}

	public void setExcelConfig(String excelConfig) {
		this.excelConfig = excelConfig;
	}

	public String getImportParams() {
		return importParams;
	}

	public void setImportParams(String importParams) {
		this.importParams = importParams;
	}

	public String getColumnName1() {
		return columnName1;
	}

	public void setColumnName1(String columnName1) {
		this.columnName1 = columnName1;
	}

	public String getColumnName2() {
		return columnName2;
	}

	public void setColumnName2(String columnName2) {
		this.columnName2 = columnName2;
	}

	public String getColumnName3() {
		return columnName3;
	}

	public void setColumnName3(String columnName3) {
		this.columnName3 = columnName3;
	}

	public String getColumnName4() {
		return columnName4;
	}

	public void setColumnName4(String columnName4) {
		this.columnName4 = columnName4;
	}

	public String getColumnName5() {
		return columnName5;
	}

	public void setColumnName5(String columnName5) {
		this.columnName5 = columnName5;
	}

}
