package com.ibs.common.module.frameworkimpl.data.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.util.NumberUtils;

import com.ibs.common.module.frameworkimpl.data.BaseImporter;
import com.ibs.common.module.frameworkimpl.data.biz.IImporterBiz;
import com.ibs.common.module.frameworkimpl.data.domain.ImportData;
import com.ibs.common.module.frameworkimpl.data.domain.ImportInfo;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSetting;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSettingColumn;
import com.ibs.common.module.frameworkimpl.data.excel.setting.ExcelSettingHelper;
import com.ibs.common.module.frameworkimpl.data.handling.ImportDataHandling;
import com.ibs.common.module.frameworkimpl.data.handling.ImportDataPreviewHandling;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.interceptor.support.JsonDateValueProcessor;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.RandomUtils;
import com.ibs.portal.framework.util.StringUtils;

/**
 * Excel 导入类
 * @author 
 * $Id: ExporterFactory.java 39293 2011-05-30 08:45:11Z  $
 */
public class ExcelImporter extends BaseImporter {
	
	public static String EXCEL_NEXT_ACTION = "excel.nextAction";

	private Map<Class<?>, ExcelSetting> mappings = new HashMap<Class<?>, ExcelSetting>();
	
	private IImporterBiz importerBiz;
	
	public void setImporterBiz(IImporterBiz importerBiz) {
		this.importerBiz = importerBiz;
	}

	public ExcelImporter() {
		
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(this.getClass().getResourceAsStream("/excel.xml"));
			
			List<ExcelSetting> settings = ExcelSettingHelper.parseExcelSettings(document);
			for(ExcelSetting setting : settings) {
				mappings.put(Class.forName(setting.getClassName()), setting);
			}
		} catch (Exception e) {
			logger.error("no excel.xml");
		}
	}

	// *************************  public methods ************************* //
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> String importFile(Class<E> clz, File file, Map<String, String> params) {
		logger.trace("entering importer...");
		
		if (mappings == null)
			throw new BizException("excel配置信息加载失败！");
		
		String importId = null;
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		
		// 读取配置信息
		ExcelSetting setting = mappings.get(clz);
		List<E> list = null;
		ImportDataHandling<E> handling = null;
		
		// 读取excel
		Workbook book = null;
		try {
			File newFile = fillNewFile(file);
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("ISO-8859-1"); // 关键代码，解决中文乱码
			book = Workbook.getWorkbook(newFile, workbookSettings);
			Sheet sheet = book.getSheet(0);
			
			// 生成两个对象
			FilePersistence filePersistence = fillFilePersistence(newFile, now);
			ImportInfo importInfo = fillImportInfo(setting, now, filePersistence.getId(), params);
			
			// 获取handling对象
			Object obj = ExcelSettingHelper.getImportDataHanler(setting.getHandleClass());
//			if ( !ImportDataPreviewHandling.class.isAssignableFrom())
			if (!(obj instanceof ImportDataHandling)) {
				throw new BizException("handleClass 必须是ImportDataHandling的子类");
			}
			handling = (ImportDataHandling<E>) obj;
			
			// 不做动态列选择
			long time1 = System.nanoTime()/1000000;
			
			list = readExcel(clz, sheet, setting);
			
			long time2 = System.nanoTime()/1000000;
			
			// 不用预览，不保存数据
			importId = importerBiz.saveImportData(filePersistence, importInfo, null);
			long time3 = System.nanoTime()/1000000;
			
			logger.info("解析Excel时间：" + (time2 - time1));
			logger.info("导入数据库时间：" + (time3 - time2));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (book != null)
				book.close();
		}
		
		// 同线程中处理导入数据
		handling.handleDatas(list, params);
		
		return importId;
	}

	@Override
	public <E> String importFilePreview(Class<E> clz, File file, Map<String, String> params) {
		logger.trace("entering importer...");
		
		if (mappings == null)
			throw new BizException("excel配置信息加载失败！");
		
		String importId = null;
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		
		// 读取配置信息
		ExcelSetting setting = mappings.get(clz);
		
		
		// 读取excel
		Workbook book = null;
		try {
			File newFile = fillNewFile(file);
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("ISO-8859-1"); // 关键代码，解决中文乱码
			book = Workbook.getWorkbook(newFile, workbookSettings);
			Sheet sheet = book.getSheet(0);
			

			// 生成两个对象
			FilePersistence filePersistence = fillFilePersistence(newFile, now);
			ImportInfo importInfo = fillImportInfo(setting, now, filePersistence.getId(), params);
			List<ImportData> datas = null;
			
			if (setting.isDefaultConfig()) {
				long time1 = System.nanoTime()/1000000;
				
				datas = readExcelPreview(clz, sheet, setting, params);
				
				long time2 = System.nanoTime()/1000000;
				
				importId = importerBiz.saveImportData(filePersistence, importInfo, datas);
				long time3 = System.nanoTime()/1000000;
				
				logger.info("解析Excel时间：" + (time2 - time1));
				logger.info("导入数据库时间：" + (time3 - time2));
			} else {	// 预读取数据,并在界面上由用户选择列
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (book != null)
				book.close();
		}
		
		return importId;
	}

	// *************************  private methods ************************* //

	private <E> List<E> readExcel(Class<E> clz, Sheet sheet,
			ExcelSetting setting) throws Exception {

		Map<Integer, ExcelSettingColumn> columns = setting.getColumns();
		
		int row = sheet.getRows();
		int column = sheet.getColumns();
		List<ImportData> datas = new ArrayList<ImportData>(column);
		List<ImportData> successDatas = new ArrayList<ImportData>(column);
		List<E> es = new ArrayList<E>(column);
		List<E> successEs = new ArrayList<E>(column);
		
		// 一行一行读Excel数据
		for (int i = setting.getStartRow(); i < row; i++) {

			// 处理一行数据
			E e = clz.newInstance();
			
			Cell[] cells = sheet.getRow(i);
			ImportData data = readRow(e, columns, cells);
			
			data.setRowNo(Long.valueOf(i));
			
			if (data.getIsSuccess()) {	// 通过配置校验
				successDatas.add(data);
				successEs.add(e);
			}

//			logger.debug(ToStringBuilder.reflectionToString(data));
			
			datas.add(data);
			es.add(e);
		}
		
		// 校验数据 callback
//		handling.checkData(successEs, successDatas);
		
		return es;
	}

	@SuppressWarnings("unchecked")
	private <E> List<ImportData> readExcelPreview(Class<E> clz, Sheet sheet,
			ExcelSetting setting, Map<String, String> params) throws Exception {

		Map<Integer, ExcelSettingColumn> columns = setting.getColumns();
		ImportDataPreviewHandling<E> handling = null;
		
		// 获取handling对象
		Object obj = ExcelSettingHelper.getImportDataHanler(setting.getHandleClass());
//		if ( !ImportDataPreviewHandling.class.isAssignableFrom())
		if (!(obj instanceof ImportDataPreviewHandling)) {
			throw new BizException("handleClass 必须是ImportDataPreviewHandling的子类");
		}
		handling = (ImportDataPreviewHandling<E>) obj;
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.NOPROP);
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		
		int row = sheet.getRows();
		int column = sheet.getColumns();
		List<ImportData> datas = new ArrayList<ImportData>(column);
		List<ImportData> successDatas = new ArrayList<ImportData>(column);
		List<E> es = new ArrayList<E>(column);
		List<E> successEs = new ArrayList<E>(column);
		
		// 一行一行读Excel数据
		for (int i = setting.getStartRow(); i < row; i++) {

			// 处理一行数据
			E e = clz.newInstance();
			
			Cell[] cells = sheet.getRow(i);
			ImportData data = readRow(e, columns, cells);
			
			data.setRowNo(Long.valueOf(i));
			if(StringUtils.isNotEmpty(setting.getIdField())) {
				Object excelRowId = PropertyUtils.getProperty(e, setting.getIdField());
				if (excelRowId != null)
					data.setExcelRowId(excelRowId.toString());
			}
			if(StringUtils.isNotEmpty(setting.getCodeField())) {
				Object excelRowCode = PropertyUtils.getProperty(e, setting.getIdField());
				if (excelRowCode != null)
					data.setExcelRowCode(excelRowCode.toString());
			}
			
//			data.setExcelData(Hibernate.createClob(JSONObject.fromObject(e).toString()));
//			data.setExcelData(JSONObject.fromObject(e).toString());
			data.setExcelData(StringUtils.getCompactStringFromJson(JSONObject.fromObject(e, jsonConfig)));
			
			if (data.getIsSuccess()) {	// 通过配置校验
				successDatas.add(data);
				successEs.add(e);
			}

//			logger.debug(ToStringBuilder.reflectionToString(data));
			
			datas.add(data);
			es.add(e);
		}
		
		// 校验数据 callback
		handling.checkData(successEs, successDatas, params);
		
		return datas;
	}

	/**
	 * 读取行数据
	 * @param <E>
	 * @param e
	 * @param columns
	 * @param cells
	 * @return
	 * @throws Exception
	 */
	private <E>ImportData readRow(E e, Map<Integer, ExcelSettingColumn> columns, Cell[] cells) throws Exception {
		ImportData data = new ImportData();
		
		boolean isSuccess = true;
		StringBuffer errorMessage = new StringBuffer();
		
		
		for (Entry<Integer, ExcelSettingColumn> entry : columns.entrySet()) {
			int j = entry.getKey();
			ExcelSettingColumn excelSettingColumn = entry.getValue();
			
			Class<?> type = excelSettingColumn.getType();
			Object obj = null;
			
			if(j>cells.length-1)//如果最后1列或者多列为空，则跳过判断。
				break;
			
			Cell cell = cells[j];
//			Cell cell = sheet.getCell(j, i);
			
			if(CellType.EMPTY == cell.getType()) {
				if (excelSettingColumn.isRequired()) {
					isSuccess = false;
					errorMessage.append(excelSettingColumn.getName() + "不能为空！");
				}
			} else if (CellType.DATE == cell.getType()) {
				if (!(Date.class.isAssignableFrom(type))){
					isSuccess = false;
					errorMessage.append(excelSettingColumn.getName() + "格式有误！");
				}else{
					DateCell dateCell = (DateCell) cell;
					obj = convertDate4JXL(dateCell.getDate());
				}
			} else if (CellType.NUMBER == cell.getType()) {
				if (String.class.isAssignableFrom(type)) {
					obj = cell.getContents();
				} else {
					if (!(Number.class.isAssignableFrom(type))){
						isSuccess = false;
						errorMessage.append(excelSettingColumn.getName() + "格式有误！");
					}else{
						obj = NumberUtils.parseNumber(cell.getContents(), type);
					}
				}
				//2012-2-15 caoslin 判断导入列是否需要作统计，如果需要就把值赋给统计列 (sum1 sum2 sum3)
				if(excelSettingColumn.getColumnName()!=null){
					if(excelSettingColumn.getColumnName().equals("sum1"))
						data.setSum1((Double)obj);
					if(excelSettingColumn.getColumnName().equals("sum2"))
							data.setSum2((Double)obj);
					if(excelSettingColumn.getColumnName().equals("sum3"))
							data.setSum3((Double)obj);
					if(excelSettingColumn.getColumnName().equals("sum4"))
						data.setSum4((Double)obj);
					if(excelSettingColumn.getColumnName().equals("sum5"))
						data.setSum5((Double)obj);
				}				
//				NumberCell numberCell = (NumberCell) cell;
//				obj = numberCell.getValue();
			}else if (CellType.LABEL == cell.getType()) {
				if (Date.class.isAssignableFrom(type)) {	// label 格式中存放日期数据
					obj = DateUtils.convert(cell.getContents());
				} else if (Number.class.isAssignableFrom(type)) {	// label 格式中存放数字格式
					try{
						obj = NumberUtils.parseNumber(cell.getContents(), type);
					} catch (Exception ex){
						isSuccess = false;
						errorMessage.append(excelSettingColumn.getName()+"数字格式错误！");
					}
				} else if (String.class.isAssignableFrom(type)) {
					obj = cell.getContents();
					
					if (excelSettingColumn.getMinLength() != null
							&& ((String)obj).length() < excelSettingColumn.getMinLength()) {
						isSuccess = false;
						errorMessage.append(excelSettingColumn.getName() + "：长度不能小于" + excelSettingColumn.getMinLength());
					}
					if (excelSettingColumn.getMaxLength() != null
							&& ((String)obj).length() > excelSettingColumn.getMaxLength()) {
						isSuccess = false;
						errorMessage.append(excelSettingColumn.getName() + "：长度不能大于" + excelSettingColumn.getMaxLength());
					}
				}
			}
			
			PropertyUtils.setProperty(e, excelSettingColumn.getField(), obj);
		}
		
		data.setIsSuccess(isSuccess);
		data.setErrorMessage(errorMessage.toString());
		
		return data;
	}

	private FilePersistence fillFilePersistence(File file, Date now) {
		
		//创建文件对象
		FilePersistence fp = new FilePersistence();
		fp.setId(StringUtils.getUUID());
		fp.setFileName(file.getName());
		fp.setOwnerModule(IMPORT_MODULE);
//		fp.setContentType(null);
		// TODO
//		fp.setOperatorID(getCurrentUser().getUserId());
		fp.setLogDate(now);
		fp.setPhsicalName(file.getAbsolutePath());
		
		return fp;
	}
	
	private ImportInfo fillImportInfo(ExcelSetting setting, Date now, String fileId, Map<String, String> params) {
		
		if(params!=null){
			//设置动态的下一步action
			String nextAction = params.get(EXCEL_NEXT_ACTION);
			if (StringUtils.isNotEmpty(nextAction)) {
				setting.setNextAction(nextAction);
			}
		}
		
		ImportInfo importInfo = new ImportInfo();
		
		importInfo.setDefaultConfig(setting.isDefaultConfig());
		importInfo.setExcelConfig(ExcelSettingHelper.createExcelSetting(setting));
		importInfo.setExcelId(setting.getId());
		importInfo.setImportTime(now);
		if (params != null && params.size() > 0) {
			JSONObject json = new JSONObject();
			json.putAll(params);
			importInfo.setImportParams(json.toString());
		}
		
		return importInfo;
	}
	
	private File fillNewFile(File file) {
		String direcotry = importerDirectory + IMPORT_MODULE + File.separator 
			+ System.currentTimeMillis() + "_" + Math.abs(RandomUtils.getRandom().nextInt()) + ".xls";
//		String direcotry = file.getParent() + File.separator + System.currentTimeMillis() + "_" + Math.abs(RandomUtils.getRandom().nextInt()) + ".xls";
		
		File newFile = new File(direcotry);
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdirs();
		}
//		file.renameTo(newFile);
		
		logger.info(file.getAbsolutePath() + " to " + newFile.getAbsolutePath());
		
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(file);
			os = new FileOutputStream(newFile);
			
			IOUtils.copy(is, os);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		
		return newFile;
	}

	public Date convertDate4JXL(Date jxlDate)
			throws ParseException {

		if (jxlDate == null)
			return null;

		TimeZone gmt = TimeZone.getTimeZone("GMT");

		DateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT, Locale.getDefault());
		dateFormat.setTimeZone(gmt);
		String str = dateFormat.format(jxlDate);
		TimeZone local = TimeZone.getDefault();
		dateFormat.setTimeZone(local);

		return dateFormat.parse(str);
	}
}

