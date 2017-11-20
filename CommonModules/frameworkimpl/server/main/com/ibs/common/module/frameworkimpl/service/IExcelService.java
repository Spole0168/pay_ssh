package com.ibs.common.module.frameworkimpl.service;

import java.io.OutputStream;
import java.util.Collection;

import com.ibs.portal.framework.server.metadata.ExportSetting;


public interface IExcelService {

	/**
	  * 从数据库读数据,生成带日期戳的Excel文件
	  * @param result 数据结果列表
	  * @param setting 数据结果集对应Excel表列名映射导出对象
	  * @throws Exception 方法内的父类异常有SQLException和IOException
	  */
	 public void exportToFile(Collection result, ExportSetting setting);
	
	 /**
	  * 从数据库读数据，写入Excel
	  * @param os 数据流，如果是写本地文件的话，可以是FileOutputStream；
	  *    如果是写Web下载的话，可以是ServletOupputStream
	  * @param result 数据结果列表
	  * @param setting 数据结果集对应Excel表列名映射导出对象
	  * @throws Exception 方法内的父类异常有SQLException和IOException
	  */
	 public void export(OutputStream os, Collection result, ExportSetting setting);
}
