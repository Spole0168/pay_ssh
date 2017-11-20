package com.ibs.common.module.frameworkimpl.log.log4j;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.common.module.frameworkimpl.log.biz.ILogBiz;
import com.ibs.common.module.frameworkimpl.log.domain.ActionLog;
import com.ibs.common.module.frameworkimpl.log.domain.BizLog;
import com.ibs.portal.framework.server.context.BeanHolder;

/**
 * Database Log Appender
 * @author 
 * $Id: DBLogAppender.java 25832 2011-01-18 01:58:55Z  $
 */
public class DBLogAppender extends AppenderSkeleton implements Appender {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ILogBiz logBiz;

	public DBLogAppender() {
		super();
		try {
			logBiz = (ILogBiz) BeanHolder.getBean("logBiz");
		} catch (Exception e) {
		}
	}

	protected void append(LoggingEvent event) {
		Object msg = event.getMessage();
        try {
        	if (msg instanceof ActionLog)
        		logBiz.saveActionLog((ActionLog) msg);
        	else if (msg instanceof BizLog)
        		logBiz.saveBizLog((BizLog) msg);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.debug(ToStringBuilder.reflectionToString(msg));
		}
	}

	public void close() {
		this.closed = true;
	}

	public boolean requiresLayout() {
		return false;
	}

}
