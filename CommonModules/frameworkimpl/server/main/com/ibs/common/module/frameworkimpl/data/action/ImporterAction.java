package com.ibs.common.module.frameworkimpl.data.action;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Hibernate;

import com.ibs.common.module.frameworkimpl.data.biz.IImporterBiz;
import com.ibs.common.module.frameworkimpl.data.domain.ImportData;
import com.ibs.common.module.frameworkimpl.data.domain.ImportInfo;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSetting;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSettingColumn;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSettingHelper;
import com.ibs.common.module.frameworkimpl.data.handling.ImportDataPreviewHandling;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.PageFooterColumn;

public class ImporterAction extends CrudBaseAction {

	private static final long serialVersionUID = 5038173836642627615L;
	
	private IImporterBiz importerBiz;
	
	private String importId;
	private ImportInfo importInfo;
	private ExcelSetting excelSetting;
	private String successMessage;
	private String nextAction;
	private String stopOnError="";

	private String columnName1;
	private String columnName2;
	private String columnName3;
	private String columnName4;
	private String columnName5;
	
	
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

	public ExcelSetting getExcelSetting() {
		return excelSetting;
	}

	public String getImportId() {
		return importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public void setImporterBiz(IImporterBiz importerBiz) {
		this.importerBiz = importerBiz;
	}

	public ImportInfo getImportInfo() {
		return importInfo;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getNextAction() {
		return nextAction;
	}

	public String getStopOnError() {
		return stopOnError;
	}

	public void setStopOnError(String stopOnError) {
		this.stopOnError = stopOnError;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	@Override
	public String create() {
		logger.trace("entering action...");
		return SUCCESS;
	}

	@Override
	public String modify() {
		logger.trace("entering action...");
		return SUCCESS;
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public String saveOrUpdate() {
		logger.trace("entering action...");
		
		try {
			importInfo = importerBiz.getImportInfo(importId);
			excelSetting = ExcelSettingHelper.parseExcelSetting(importInfo.getExcelConfig());
			successMessage = excelSetting.getDescription() + "成功�?";
			nextAction = excelSetting.getNextAction();
			
			// 获取handling对象
			ImportDataPreviewHandling<?> handling = (ImportDataPreviewHandling<?>) ExcelSettingHelper.getImportDataHanler(excelSetting.getHandleClass());
			
			List list = importerBiz.findImportDataList(importId, excelSetting);
			
			Map<String, String> params = null;
			String importParams = importInfo.getImportParams();
			if (StringUtils.isNotEmpty(importParams)) {
				JSONObject json = JSONObject.fromObject(importParams);
				params = (Map<String, String>) JSONObject.toBean(json, Map.class);
			}
			
			handling.handleDatas(list, params);
			
			if (excelSetting.getRedirect())
				return "redirect";
			else
				return SUCCESS;
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("导入失败");
			return ERROR;
		}
		
	}

	@Override
	public String list() {
		
		logger.trace("entering action...");
		
		try {
			importInfo = importerBiz.getImportInfo(importId);
			excelSetting = ExcelSettingHelper.parseExcelSetting(importInfo.getExcelConfig());
			
			Map<Integer, ExcelSettingColumn> columns = excelSetting.getColumns();
			for (Entry<Integer, ExcelSettingColumn> entry : columns.entrySet()) {
				ExcelSettingColumn excelSettingColumn = entry.getValue();
				//2012-2-15 caoslin 判断导入列是否需要作统计，如果需要就把�?�赋给统计列 (sum1 sum2 sum3)
				if(excelSettingColumn.getColumnName()!=null){
					if(excelSettingColumn.getColumnName().equals("sum1"))
						importInfo.setColumnName1(excelSettingColumn.getField());
					if(excelSettingColumn.getColumnName().equals("sum2"))
						importInfo.setColumnName2(excelSettingColumn.getField());
					if(excelSettingColumn.getColumnName().equals("sum3"))
						importInfo.setColumnName3(excelSettingColumn.getField());
					if(excelSettingColumn.getColumnName().equals("sum4"))
						importInfo.setColumnName4(excelSettingColumn.getField());
					if(excelSettingColumn.getColumnName().equals("sum5"))
						importInfo.setColumnName5(excelSettingColumn.getField());
				}
			}
			
			
			if(excelSetting.isStopOnError() ==true){
				boolean boo = importerBiz.haveImportErrData(importId);
				if(boo)
					this.stopOnError = "stop";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("无导入信�?");
			return ERROR;
		}
		
		return SUCCESS;
	}

	@Override
	public String search() {
		
		logger.trace("entering action...");
		
		long startTime = System.currentTimeMillis();
		try {
			if(columnName1 !=null && !columnName1.equals(""))
				queryPage.addPageFooterColumn(new PageFooterColumn(columnName1, "sum(sum1)", Hibernate.DOUBLE));
			if(columnName2 !=null && !columnName2.equals(""))
				queryPage.addPageFooterColumn(new PageFooterColumn(columnName2, "sum(sum2)", Hibernate.DOUBLE));
			if(columnName3 !=null && !columnName3.equals(""))
				queryPage.addPageFooterColumn(new PageFooterColumn(columnName3, "sum(sum3)", Hibernate.DOUBLE));
			if(columnName4 !=null && !columnName4.equals(""))
				queryPage.addPageFooterColumn(new PageFooterColumn(columnName4, "sum(sum4)", Hibernate.DOUBLE));
			if(columnName5 !=null && !columnName5.equals(""))
				queryPage.addPageFooterColumn(new PageFooterColumn(columnName5, "sum(sum5)", Hibernate.DOUBLE));
			
			Page<ImportData> result = (Page<ImportData>) importerBiz.findImportDataByPage(queryPage, getSearchFields());
			setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("查询失败");
			return ERROR;
		}
		
		long endTime = System.currentTimeMillis();
		
		logger.info("执行时间�?" + (endTime - startTime));
		
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String delete() {
		logger.trace("entering action...");
		return SUCCESS;
	}

	@Override
	public String export() {
		logger.trace("entering action...");
		return SUCCESS;
	}

}
