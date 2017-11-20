package com.ibs.common.module.frameworkimpl.data.biz;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.data.domain.ImportData;
import com.ibs.common.module.frameworkimpl.data.domain.ImportInfo;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSetting;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IImporterBiz {
	
	/**
	 * 保存导入数据
	 * @param filePersistence
	 * @param importInfo
	 * @param importData
	 */
	public String saveImportData(FilePersistence filePersistence,
			ImportInfo importInfo, List<ImportData> datas);
	
	/**
	 * 查询导入数据
	 * @param queryPage
	 * @param searchFields
	 * @return
	 */
	public IPage<ImportData> findImportDataByPage(QueryPage queryPage, Map<String, String> searchFields);
	
	public ImportInfo getImportInfo(String importId);
	
	@SuppressWarnings("rawtypes")
	public List findImportDataList(String importId, ExcelSetting excelSetting);
	
	public boolean haveImportErrData(String importId);
}
