package com.ibs.common.module.frameworkimpl.data.pdf;

import java.util.Collection;

import com.ibs.common.module.frameworkimpl.data.BaseExporter;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.ExportSetting;

public class PdfExporter extends BaseExporter {

	@Override
	public FilePersistence exportToFile(Collection<?> result, ExportSetting setting, IUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExportSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
