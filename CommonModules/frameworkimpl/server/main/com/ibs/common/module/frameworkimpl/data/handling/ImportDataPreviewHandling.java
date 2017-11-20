package com.ibs.common.module.frameworkimpl.data.handling;

import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.data.domain.ImportData;

/**
 * BaseImportDataHandling 导入预览回调类
 * 
 * @author  $Id: ExporterFactory.java 39293 2011-05-30 08:45:11Z  $
 */
public interface ImportDataPreviewHandling<E> extends ImportDataHandling<E> {

	/**
	 * 回调函数，做批量数据验证和处理
	 * 对于list中某行数据校验不通过，则设置datas该行数据的isSuccess为false，errorMessage为错误信息
	 * 
	 * @param list
	 * @param datas
	 */
	public abstract void checkData(final List<E> list,
			final List<ImportData> datas, final Map<String, String> params);

}
