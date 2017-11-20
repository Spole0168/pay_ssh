package com.ibs.common.module.frameworkimpl.data.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class ImportData extends BaseEntity {

	private static final long serialVersionUID = -7828342320400004641L;

	private String importId;
	private Date importTime;
	private Long rowNo;
	private Boolean isSuccess;
	private String errorMessage;
	private String excelRowId;
	private String excelRowCode;
//	private Clob excelData;
	private String excelData;
	private Double sum1;
	private Double sum2;
	private Double sum3;
	private Double sum4;
	private Double sum5;

	public Long getRowNo() {
		return rowNo;
	}

	public void setRowNo(Long rowNo) {
		this.rowNo = rowNo;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getExcelData() {
		return excelData;
	}

	public void setExcelData(String excelData) {
		this.excelData = excelData;
	}

	public String getImportId() {
		return importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public String getExcelRowId() {
		return excelRowId;
	}

	public void setExcelRowId(String excelRowId) {
		this.excelRowId = excelRowId;
	}

	public String getExcelRowCode() {
		return excelRowCode;
	}

	public void setExcelRowCode(String excelRowCode) {
		this.excelRowCode = excelRowCode;
	}

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public Double getSum1() {
		return sum1;
	}

	public void setSum1(Double sum1) {
		this.sum1 = sum1;
	}

	public Double getSum2() {
		return sum2;
	}

	public void setSum2(Double sum2) {
		this.sum2 = sum2;
	}

	public Double getSum3() {
		return sum3;
	}

	public void setSum3(Double sum3) {
		this.sum3 = sum3;
	}

	public Double getSum4() {
		return sum4;
	}

	public void setSum4(Double sum4) {
		this.sum4 = sum4;
	}

	public Double getSum5() {
		return sum5;
	}

	public void setSum5(Double sum5) {
		this.sum5 = sum5;
	}
	
//	public String getExcelDataString() {
//		return StringUtils.getStringFromClob(excelData);
//	}
//
//	private Clob getExcelData() {
//		return excelData;
//	}
//
//	public void setExcelData(Clob excelData) {
//		this.excelData = excelData;
//	}
}
