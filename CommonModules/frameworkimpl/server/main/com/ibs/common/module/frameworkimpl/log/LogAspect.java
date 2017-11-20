package com.ibs.common.module.frameworkimpl.log;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;

import com.ibs.common.module.frameworkimpl.log.common.Constants;
import com.ibs.common.module.frameworkimpl.log.dao.IDomainDao;
import com.ibs.common.module.frameworkimpl.log.domain.BizInfo;
import com.ibs.common.module.frameworkimpl.log.domain.BizLog;
import com.ibs.common.module.frameworkimpl.log.domain.EntityInfo;
import com.ibs.common.module.frameworkimpl.log.log4j.AsyncLoggerFactory;
import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.util.StringUtils;

/**
 * Biz Log - Spring AOP Aspect
 * @author 
 * $Id: LogAspect.java 31169 2011-03-11 01:35:33Z  $
 */
public class LogAspect {
	
	private final Log log = LogFactory.getLog(LogAspect.class);
	private IDomainDao domainDao;
	private LogMetaData logMetaData;
	
	public LogAspect() {
	}

	public Object doBiz(ProceedingJoinPoint joinPoint) throws Throwable {
		log.trace("entering doBiz ... ");
		
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
//		log.debug(className);
//		log.debug(methodName);

		HashMap<String, BizInfo> bizInfoMap = logMetaData.getBizInfoMap();
		
		BizInfo bizInfo = bizInfoMap.get(className.concat(".").concat(methodName));
		BizLog bizLog = null;
		Object object = null;

		HttpServletRequest request = null;
		try {
			request = ServletActionContext.getRequest();
		} catch(Exception e) { }
		
		if (request == null || bizInfo == null) {
			object = joinPoint.proceed();
			return object;
		}
		
		IUser user = UserContext.getUserContext().getCurrentUser();
		bizLog = new BizLog();

		String bizId = StringUtils.getUUID();
		String actionId = (String) request.getAttribute(Constants.LOG_ACTION_ATTRIBUTE_NAME);
		
		request.setAttribute(Constants.LOG_BIZ_ATTRIBUTE_NAME, bizId);
		
		bizLog.setId(bizId);
		bizLog.setActionId(actionId);
		bizLog.setBizType(bizInfo.getId());
		bizLog.setBizTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		if (user != null) {
			bizLog.setUserCode(user.getUserCode());
			bizLog.setUserName(user.getDisplayName());
//			bizLog.setOrgCode(user.getOrgCode());
//			bizLog.setOrgName(user.getOrgName());
		}
		
		String entityId = null;
		EntityInfo entityInfo = bizInfo.getEntityInfo();
		if (entityInfo != null) {
			Object[] args = joinPoint.getArgs();
			// 从第一个参数的getId获取值
			if (args != null && args.length > 0) {
				if(args[0] instanceof String) {
					entityId = (String) args[0];
				} else
				try {
					entityId = BeanUtils.getProperty(args[0], "id");
				} catch (Exception e) {}
			}
		}


		try {
			object = joinPoint.proceed();
			bizLog.setIsException(Boolean.FALSE);
			
			// 新增实体，从返回值中获取id值；返回值可以是id或者是具有id属性的po对象
			if (entityInfo != null && StringUtils.isEmpty(entityId)) {
				if (object == null) {
					// do nothing
				}
				else if (object instanceof String)
					entityId = (String) object;
				else {
					try {
						entityId = BeanUtils.getProperty(object, "id");
					} catch (Exception e) {
						entityId = object.toString();
					}
				}
			}
			// 新增和修改实体需要获取code和name
			try {
				if (entityInfo != null && StringUtils.isNotEmpty(entityId) && 
						!methodName.toLowerCase().contains("delete") && 
						!methodName.toLowerCase().contains("remove") && 
						!methodName.toLowerCase().contains("cancel")) {
					Object domain = domainDao.load(Class.forName(entityInfo.getEntityClass()), entityId);
					if (StringUtils.isNotEmpty(entityInfo.getCodeProperty()))
						bizLog.setEntityCode(BeanUtils.getProperty(domain, entityInfo.getCodeProperty()));
					if (StringUtils.isNotEmpty(entityInfo.getNameProperty()))
						bizLog.setEntityName(BeanUtils.getProperty(domain, entityInfo.getNameProperty()));
				}
			} catch(Exception e) {
				log.error(e.getMessage(), e);
			}
			
			bizLog.setEntityId(entityId);
			
		} catch (Exception e) {
			bizLog.setIsException(Boolean.TRUE);
			log.error(e.getMessage(), e);
			
			throw e;
		} finally {
			AsyncLoggerFactory.instance().saveBizLog(bizLog);
		}

		log.trace("exiting doBiz ... ");
		return object;
	}

	public IDomainDao getDomainDao() {
		return domainDao;
	}

	public void setDomainDao(IDomainDao domainDao) {
		this.domainDao = domainDao;
	}

	public LogMetaData getLogMetaData() {
		return logMetaData;
	}

	public void setLogMetaData(LogMetaData logMetaData) {
		this.logMetaData = logMetaData;
	}

}
