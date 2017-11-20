package com.ibs.common.module.frameworkimpl.data.dao;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.data.domain.ImportData;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;


public interface IImportDataDao {

	public void batchSaveImportData(final List<ImportData> datas);
	
	public IPage<ImportData> findImportDataByPage(QueryPage queryPage,
			Map<String, String> searchFields);
	
	public List<ImportData> findImportDataList(String importId);
	
	public boolean haveImportErrData(String importId);
}
