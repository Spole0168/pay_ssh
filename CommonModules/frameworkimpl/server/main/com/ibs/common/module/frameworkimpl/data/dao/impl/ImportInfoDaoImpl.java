package com.ibs.common.module.frameworkimpl.data.dao.impl;

import com.ibs.common.module.frameworkimpl.data.dao.IImportInfoDao;
import com.ibs.common.module.frameworkimpl.data.domain.ImportInfo;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

public class ImportInfoDaoImpl extends BaseEntityDao<ImportInfo> implements IImportInfoDao {

	public String saveImportInfo(ImportInfo entity) {
		
		logger.trace("entering dao...");
		
		return (String) super.save(entity);
	}

	public ImportInfo getImportInfo(String importId) {
		
		logger.trace("entering dao...");
		
		return super.load(importId);
	}

}
