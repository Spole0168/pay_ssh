package com.ibs.common.module.frameworkimpl.data;

import com.ibs.common.module.frameworkimpl.data.excel.ExcelExporter;
import com.ibs.portal.framework.util.StringUtils;

/**
 * Exporter Factory
 * @author 
 * $Id: ExporterFactory.java 39293 2011-05-30 08:45:11Z  $
 */
public class ExporterFactory {
	
	public final static String FORMAT_EXCEL = "xls";
	public final static String FORMAT_PDF = "pdf";
	
	public final static String FORMAT_EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";
	
	private ExcelExporter excelExporter;

	public void setExcelExporter(ExcelExporter excelExporter) {
		this.excelExporter = excelExporter;
	}
	
	public BaseExporter createExporter(String format) {
		
		BaseExporter exporter = null;
		
		if (StringUtils.isEmpty(format))
			format = FORMAT_EXCEL;
		
		if (FORMAT_EXCEL.equals(format)) {
			exporter = excelExporter;
		} else {
			return null;
		}
		
		return exporter;
	}
}
