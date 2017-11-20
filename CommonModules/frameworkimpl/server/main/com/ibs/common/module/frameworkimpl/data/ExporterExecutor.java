package com.ibs.common.module.frameworkimpl.data;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/**
 * Exporter Executor
 * @author 
 * $Id: ExporterExecutor.java 39293 2011-05-30 08:45:11Z  $
 */
public class ExporterExecutor {
	
	private static final Logger logger = Logger.getLogger(ExporterExecutor.class);
	
	private ThreadPoolTaskExecutor exporterExecutorManager;
	private ExporterFactory exporterFactory;
	private String exporterDirectory;
	private String exporterModule;
	private IFilePersistenceService filePersistenceService;

	public void setExporterModule(String exporterModule) {
		this.exporterModule = exporterModule;
	}

	public void setFilePersistenceService(
			IFilePersistenceService filePersistenceService) {
		this.filePersistenceService = filePersistenceService;
	}

	public void setExporterFactory(ExporterFactory exporterFactory) {
		this.exporterFactory = exporterFactory;
	}

	public void setExporterExecutorManager(
			ThreadPoolTaskExecutor exporterExecutorManager) {
		this.exporterExecutorManager = exporterExecutorManager;
	}

	public void setExporterDirectory(String exporterDirectory) {
		this.exporterDirectory = exporterDirectory;
	}

	private class ExportorExecutorTask implements Runnable {

		private Object bizBean;
		private Object[] args;
		
		private Class<?> bizClass;
		private String methodName;
		private Class<?>[] argsClass;
		private IUser user;

		public ExportorExecutorTask(Object bizBean, Object[] args, 
				Class<?> bizClass, String methodName, Class<?>[] argsClass, IUser user) {
			this.bizBean = bizBean;
			this.args = args;
			this.bizClass = bizClass;
			this.methodName = methodName;
			this.argsClass = argsClass;
			this.user = user;
		}

		public void run() {
			try {
				
				ExportSetting exportSetting = (ExportSetting) args[0];
				
				String format = exportSetting.getFormat();
				BaseExporter exporter = exporterFactory.createExporter(format);
				exportSetting.setDirectory(exporterDirectory);
				exportSetting.setPageSize(exporter.getExportSize());
				String fileName = exportSetting.getFileName();
				
				// use cglib reflection, better performance
				FastClass fc = FastClass.create(bizClass);
				FastMethod method = fc.getMethod(methodName, argsClass);
				
				int i = 0;
				while(true) {
					exportSetting.setFileName(fileName + i);
					exportSetting.setPageIndex(i++);
					Page<?> page = (Page<?>) method.invoke(bizBean, args);
					logger.debug(page);
					
					if(StringUtils.isEmpty(exportSetting.getModule()))
						exportSetting.setModule(exporterModule);
					FilePersistence fp = exporter.exportToFile(page.getRows(), exportSetting, user);
					filePersistenceService.saveFile(fp);
					
					// last page
					if (page.getRows() == null || page.getRows().size() < exportSetting.getPageSize())
						break;
				}

			} catch(Exception e) {
				logger.error(e.getMessage(), e);
				throw new BizException(e.getMessage());
			}
		}

	}

	public void exportAsync(Object bizBean, Object[] args, 
			Class<?> bizClass, String methodName, Class<?>[] argsClass) {
		try {
			if (!(args[0] instanceof ExportSetting))
				throw new BizException("ExportSetting must be the first parameter");
			
			IUser user = UserContext.getUserContext().getCurrentUser();
			exporterExecutorManager.execute(new ExportorExecutorTask(bizBean, args, bizClass, methodName, argsClass, user));
//			exportorExecutorManager.getActiveCount();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BizException(e.getMessage());
		}
		
	}
	
	public void exportSync(Object bizBean, Object[] args, 
			Class<?> bizClass, String methodName, Class<?>[] argsClass) {
		try {
			if (!(args[0] instanceof ExportSetting))
				throw new BizException("ExportSetting must be the first parameter");
			
			ExportSetting exportSetting = (ExportSetting) args[0];
			
			String format = exportSetting.getFormat();
			BaseExporter exporter = exporterFactory.createExporter(format);
			exportSetting.setDirectory(exporterDirectory);
			exportSetting.setPageSize(exporter.getExportSize());
			exportSetting.setPageIndex(0);
			
			// use cglib reflection, better performance
			FastClass fc = FastClass.create(bizClass);
			FastMethod method = fc.getMethod(methodName, argsClass);
			Page<?> page = (Page<?>) method.invoke(bizBean, args);
			logger.debug(page);

			if(StringUtils.isEmpty(exportSetting.getModule()))
				exportSetting.setModule(exporterModule);
			exporter.exportToFile(page.getRows(), exportSetting, null);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BizException(e.getMessage());
		}
	}
	
}
