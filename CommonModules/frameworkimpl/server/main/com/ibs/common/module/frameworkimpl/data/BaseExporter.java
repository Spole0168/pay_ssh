package com.ibs.common.module.frameworkimpl.data;

import java.io.File;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceException;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceMessage;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.ExportSetting;

public abstract class BaseExporter {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public abstract FilePersistence exportToFile(Collection<?> result, ExportSetting setting, IUser user);
	
	public abstract int getExportSize();

	/**
	 * 拼接上传路径字符串
	 * @param dir			BASE目录
	 * @param ownerModule	第一层目录
	 * @param timestamp		第二层目录
	 * @return
	 * @throws FileServiceException 
	 */
	protected String newFilePath(String dir, String ownerModule, String date) {
		
		StringBuffer filePath = new StringBuffer();
		filePath.append(dir);
		filePath.append(ownerModule);
		filePath.append(File.separator);
		filePath.append(date);
		filePath.append(File.separator);
		
		try {
			File d = new File(filePath.toString());
			if (!d.exists())
				d.mkdirs();
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);
			throw new BizException(FileServiceMessage.FILE_CANNOT_WRITE.getName());
		}
		
		return filePath.toString();
	}
}
