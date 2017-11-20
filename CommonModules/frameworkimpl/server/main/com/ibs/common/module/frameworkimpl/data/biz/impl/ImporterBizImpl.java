package com.ibs.common.module.frameworkimpl.data.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.ibs.common.module.frameworkimpl.data.biz.IImporterBiz;
import com.ibs.common.module.frameworkimpl.data.dao.IImportDataDao;
import com.ibs.common.module.frameworkimpl.data.dao.IImportInfoDao;
import com.ibs.common.module.frameworkimpl.data.domain.ImportData;
import com.ibs.common.module.frameworkimpl.data.domain.ImportInfo;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSetting;
import com.ibs.common.module.frameworkimpl.file.dao.IFilePersistenceDao;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.interceptor.support.JsonDateValueProcessor;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class ImporterBizImpl extends BaseBiz implements IImporterBiz {

	private IImportDataDao importDataDao;
	private IImportInfoDao importInfoDao;
	private IFilePersistenceDao filePersistenceDao;

	// *************************  get/set methods ************************* //
	
	public void setImportDataDao(IImportDataDao importDataDao) {
		this.importDataDao = importDataDao;
	}

	public void setImportInfoDao(IImportInfoDao importInfoDao) {
		this.importInfoDao = importInfoDao;
	}

	public void setFilePersistenceDao(IFilePersistenceDao filePersistenceDao) {
		this.filePersistenceDao = filePersistenceDao;
	}

	// *************************  Biz methods ************************* //
	
	public String saveImportData(FilePersistence filePersistence,
			ImportInfo importInfo, List<ImportData> datas) {
		
		logger.trace("entering biz...");
		
		String fileId = (String) filePersistenceDao.save(filePersistence);
		String importId = StringUtils.getUUID();
		importInfo.setFileId(fileId);
		importInfo.setId(importId);
		importInfoDao.saveImportInfo(importInfo);
		
		if (datas != null && datas.size() > 0) {
			for (ImportData data : datas) {
				data.setImportId(importId);
				data.setImportTime(importInfo.getImportTime());
			}
			importDataDao.batchSaveImportData(datas);
		}
		
		return importId;
	}

	public IPage<ImportData> findImportDataByPage(QueryPage queryPage,
			Map<String, String> searchFields) {
		
		logger.trace("entering biz...");
		
		return importDataDao.findImportDataByPage(queryPage, searchFields);
	}

	public ImportInfo getImportInfo(String importId) {
		
		logger.trace("entering biz...");
		
		return importInfoDao.getImportInfo(importId);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findImportDataList(String importId,
			ExcelSetting excelSetting) {
		
		logger.trace("entering biz...");
		
		try {
			Class<?> clz = null;

			clz = Class.forName(excelSetting.getClassName());

			List<ImportData> datas = importDataDao.findImportDataList(importId);
			List list = new ArrayList(datas.size());

//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.setIgnoreDefaultExcludes(false);
//			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
//			jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new JsonDateValueProcessor());
//			jsonConfig.setRootClass(clz);
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {JsonDateValueProcessor.DEFAULT_DATE_PATTERN})); 
			
			for (ImportData data : datas) {
				
				JSONObject json = JSONObject.fromObject(data.getExcelData());
				Object bean = JSONObject.toBean(json, clz);
				
				list.add(bean);
			}

			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return new ArrayList();
	}

	public boolean haveImportErrData(String importId){
		logger.trace("entering biz...");
		
		return importDataDao.haveImportErrData(importId);
	}
}
