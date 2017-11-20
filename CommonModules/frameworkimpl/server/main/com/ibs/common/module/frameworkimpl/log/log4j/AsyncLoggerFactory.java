package com.ibs.common.module.frameworkimpl.log.log4j;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.Logger;

import com.ibs.common.module.frameworkimpl.log.domain.ActionLog;
import com.ibs.common.module.frameworkimpl.log.domain.BizLog;

/**
 * Asynchronous Logger Factory
 * @author 
 * $Id: AsyncLoggerFactory.java 38454 2011-05-17 06:58:11Z  $
 */
public class AsyncLoggerFactory{

	private static AsyncLoggerFactory factory = null;
	
	public static synchronized AsyncLoggerFactory instance() {
		if (factory == null) {
			factory = new AsyncLoggerFactory();
		}
		return factory;
	}
	
	private Logger actionLogger = null;
	
	private AsyncLoggerFactory() {
		AsyncAppender appender = new AsyncAppender();
		//PatternLayout layout = new PatternLayout();
		DBLogAppender log = new DBLogAppender();
		//log.setSql("%m");
		//log.setLayout(layout);
		//log.setBufferSize(10);
		appender.setBufferSize(4096);
		//appender.setThreshold(arg0)
		appender.addAppender(log);
		actionLogger = Logger.getLogger(this.getClass());
//		actionLogger = Logger.getLogger("ActionLog");
		actionLogger.removeAllAppenders();
		actionLogger.addAppender(appender);
	}
	
	public void saveActionLog(ActionLog actionLog) {
		actionLogger.info(actionLog);
	}

	public void saveBizLog(BizLog bizLog) {
		actionLogger.info(bizLog);
	}
}
