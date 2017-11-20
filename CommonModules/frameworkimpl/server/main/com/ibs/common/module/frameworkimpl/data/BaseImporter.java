package com.ibs.common.module.frameworkimpl.data;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 导入基类
 * @author 
 * $Id: ExporterFactory.java 39293 2011-05-30 08:45:11Z  $
 */
public abstract class BaseImporter {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected String importerDirectory;
	
	public static final String IMPORT_MODULE = "IMPORT";
	
	public abstract<E> String importFile(Class<E> clz, File file, Map<String, String> params);
	
	public abstract<E> String importFilePreview(Class<E> clz, File file, Map<String, String> params);
	
	
	public String getImporterDirectory() {
		return importerDirectory;
	}

	public void setImporterDirectory(String importerDirectory) {
		this.importerDirectory = importerDirectory;
	}

}
