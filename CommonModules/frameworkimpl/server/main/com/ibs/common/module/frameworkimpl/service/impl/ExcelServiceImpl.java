package com.ibs.common.module.frameworkimpl.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Blank;
import jxl.write.Boolean;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BeanUtils;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.portal.framework.server.exception.ServiceException;
import com.ibs.portal.framework.server.metadata.ExportColType;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.GridModel;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.RandomUtils;
import com.ibs.portal.framework.util.StringUtils;

public class ExcelServiceImpl extends BaseService implements IExcelService {
	
	private String tmpFileRoot;

	public String getTmpFileRoot() {
		return tmpFileRoot;
	}

	public void setTmpFileRoot(String tmpFileRoot) {
		this.tmpFileRoot = tmpFileRoot;
	}

	/**
	 * 从数据库读数据,生成带日期戳的Excel文件
	 * 
	 * @param result
	 *            数据结果列表
	 * @param setting
	 *            数据结果集对应Excel表列名映射导出对象
	 * @throws Exception
	 *             方法内的异常有ServiceException
	 */
	public void exportToFile(Collection result, ExportSetting setting) {
		Integer rdm = Math.abs(RandomUtils.getRandom().nextInt());
		Long timeMillis = System.currentTimeMillis();
		
		String fileName = tmpFileRoot + timeMillis + "_" + rdm + ".xls";
		
		try {
			File f = new File(fileName);
			f.createNewFile();
			export(new FileOutputStream(f), result, setting);
		}
		catch (IOException e) {
			if(logger.isDebugEnabled())logger.debug(fileName+"不存在, 请检查application.properties中的tmp.file.folder配置项, 保证在系统中存在相应的目录.");
			throw new ServiceException(fileName+ "不存在, 请检查application.properties中的tmp.file.folder配置项, 保证在系统中存在相应的目录.",e);
		}
		
		// 设置真正的文件名
		setting.setRealName(fileName);
	}

	/**
	 * 从数据库读数据，写入Excel
	 * 
	 * @param os
	 *            数据流，如果是写本地文件的话，可以是FileOutputStream；
	 *            如果是写Web下载的话，可以是ServletOupputStream
	 * @param result
	 *            数据结果列表
	 * @param setting
	 *            数据结果集对应Excel表列名映射导出对象
	 * @throws Exception
	 *             方法内的异常有ServiceException
	 */
	public void export(OutputStream os, Collection result, ExportSetting setting) {

		WritableWorkbook wbook = null;
		try {
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			// sheet名称
			WritableSheet wsheet = wbook.createSheet("Sheet1", 0);
			// 字体
			WritableFont wfont = null;
			// 字体格式
			WritableCellFormat wcfFC = null;
			// Excel表格的Cell
			Label wlabel = null;

			// 设置excel标题字体
			wfont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD,
					false, jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			wcfFC = new WritableCellFormat(wfont);

			// 添加excel标题
			Label wlabel1 = new Label(5, 0, setting.getTitle(), wcfFC);
			wsheet.addCell(wlabel1);

			// 设置列名字体
			// 如果有标题的话，要设置一下偏移
			int offset = 2;
			if (setting.getTitle() == null
					|| setting.getTitle().trim().equals(""))
				offset = 0;
			else {
				wfont = new WritableFont(WritableFont.ARIAL, 14,
						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
						Colour.BLACK);
				wcfFC = new WritableCellFormat(wfont);
			}

			// 根据ExportColNames来创建Excel的列名
			Map<Integer, String> colNames = setting.getExportColNames();
			int count = colNames.size();
			for (int i = 0; i < count; i++) {
//				String name = URLDecoder.decode(colNames.get(i),"utf-8");
				String name = colNames.get(i);
				if (name == null) {
					break;
				}
				wlabel = new Label(i, offset, name);
				wsheet.addCell(wlabel);
			}

			// 设置正文字体
			wfont = new WritableFont(WritableFont.TIMES, 14, WritableFont.BOLD,
					false, jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			wcfFC = new WritableCellFormat(wfont);
			//設置字段類型用於設置
			WritableCell writableCell = null;
			// 往Excel输出数据
			int rowIndex = 1 + offset;
			//获得columnType
			Map colType = setting.getExportColTypes();
			String propertye = null;
			int row = 0;
			for (Object obj : result) {
				Map<Integer, String> colModels = setting.getExportColModels();
				Map<Integer, GridModel> colModelList = setting.getExportColModelList();
				int size = colModels.size();
				++row;
				wsheet.setColumnView(row,30);
				wsheet.setRowView(row,400);
				for (int i = 0; i < count; i++) {
					String col = colModels.get(i);
					GridModel model = colModelList.get(i);
					
					try {
						propertye = BeanUtils.getProperty(obj, col);
					} catch (Exception e) { 
						propertye = null;
					}
					
					if(propertye == null || "".equals(propertye)){
						writableCell = new Blank(i, rowIndex);
					} else if (model != null) {
						if (model.getEditoptions() != null) {	// map 转换
							String value = model.getMaps().get(propertye);
							writableCell = new Label(i, rowIndex, value);
						} else if (model.getFormatoptions() != null && 
								StringUtils.isNotEmpty(model.getFormatoptions().getNewformat())) {	// format date
							String format = model.getFormatoptions().getNewformat()
								.replaceAll("Y", "yyyy")
								.replaceAll("m", "MM")
								.replaceAll("d", "dd")
								.replaceAll("G", "HH")
								.replaceAll("i", "mm")
								.replaceAll("s", "ss")
								;
							Date date = new SimpleDateFormat(format).parse(propertye);
							DateFormat dateFormat = new DateFormat(format);
							WritableCellFormat wcf = new WritableCellFormat(dateFormat);
							writableCell = new DateTime(i, rowIndex, date, wcf);
						} else if (propertye != null) {
							writableCell = new Label(i, rowIndex, propertye);
						} else {
							writableCell = new Blank(i, rowIndex);
						}
					} else {
						ExportColType exportColType = (ExportColType)colType.get(col);
						if (exportColType == null) {
							writableCell = new Label(i, rowIndex, BeanUtils.getProperty(obj,
									col));
						}else {
							switch (exportColType) {
							case Boolean://boolean
								boolean flag = java.lang.Boolean.valueOf(propertye);
								writableCell = new Boolean(i, rowIndex,flag);
								break;
							case DateTime://日期
								Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(propertye);
								DateFormat dateFormat = new DateFormat("yyyy-MM-dd hh:mm:ss");
								WritableCellFormat wcf = new WritableCellFormat(dateFormat);
								writableCell = new DateTime(i,rowIndex,date,wcf);
								break;
							case Label:	//文本
								writableCell = new Label(i, rowIndex, BeanUtils.getProperty(obj,
										col));
								break;
							case Number://數字
								Double double1  = Double.valueOf(propertye);
								writableCell = new Number(i, rowIndex, double1);
								break;
							case Formula://公式
								writableCell = new Formula(i,rowIndex,propertye);
								break;
							case Blank:	//空格
								writableCell = new Blank(i, rowIndex);
								break;
							default:
								writableCell = new Label(i, rowIndex, BeanUtils.getProperty(obj,
										col));
								break;
							}
						}
					}
					
					wsheet.addCell(writableCell);
					writableCell = null;
				}
				rowIndex++;
			}
			wbook.write(); // 写入文件
			os.flush();
		} catch (RowsExceededException e) {
			if(logger.isDebugEnabled())logger.debug("Excel行数超过65536行限制！");
			throw new ServiceException("Excel行数超过65536行限制！",e);
		} catch (Exception e) {
			if(logger.isDebugEnabled())logger.debug("Excel文件操作失败！");
			throw new ServiceException("Excel文件操作失败！",e);
		} finally {
			try {
				if (wbook != null)
					wbook.close();
				os.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new ServiceException("Excel文件操作失败！",e);
			}
		}
	}
}
