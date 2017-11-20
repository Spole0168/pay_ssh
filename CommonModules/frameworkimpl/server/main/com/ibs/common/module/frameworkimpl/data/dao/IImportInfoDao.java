package com.ibs.common.module.frameworkimpl.data.dao;

import com.ibs.common.module.frameworkimpl.data.domain.ImportInfo;


public interface IImportInfoDao {

	public String saveImportInfo(ImportInfo entity);
	
	public ImportInfo getImportInfo(String importId);
}
