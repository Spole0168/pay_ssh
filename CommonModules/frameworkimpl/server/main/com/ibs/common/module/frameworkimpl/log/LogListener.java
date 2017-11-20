package com.ibs.common.module.frameworkimpl.log;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;

import com.ibs.common.module.frameworkimpl.log.common.Constants;
import com.ibs.common.module.frameworkimpl.log.common.RecordOperType;
import com.ibs.common.module.frameworkimpl.log.domain.EntityInfo;
import com.ibs.portal.framework.server.context.BeanHolder;
import com.ibs.portal.framework.server.domain.IHisEntity;

/**
 * Entity Log - Hibernate Event Listener
 * @author 
 * $Id: LogListener.java 35794 2011-04-21 05:08:50Z  $
 */
public class LogListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener{

	private static final long serialVersionUID = -3567697188105377971L;
	private static final Log log = LogFactory.getLog(LogListener.class);
	private LogMetaData logMetaData;
	
	public void onPostInsert(PostInsertEvent event) {
		log.trace("entering doListener ... ");

		String bizId = null;
		try {
			if (logMetaData == null)
				logMetaData = (LogMetaData) BeanHolder.getBean("logMetaData");
			HttpServletRequest request = ServletActionContext.getRequest();
			bizId = (String) request.getAttribute(Constants.LOG_BIZ_ATTRIBUTE_NAME);
		} catch (Exception e) {
 			return;
		}
		
		try {
			String className = event.getEntity().getClass().getName();
			
			HashMap<String, EntityInfo> entityInfoMap = logMetaData.getEntityInfoMap();
			if (!entityInfoMap.containsKey(className))
				return;
			
			// 需要保存历史实体
			IHisEntity hisEntity = (IHisEntity) Class.forName(entityInfoMap.get(className).getHisEntityClass()).newInstance();
			
//			event.getPersister().setPropertyValues(hisEntity, event.getState(), EntityMode.POJO);
//			BeanUtils.copyProperty(hisEntity, "id", event.getId());

			Object entity = event.getEntity();
			BeanCopier copy = BeanCopier.create(entity.getClass(), hisEntity.getClass(), false);
			copy.copy(entity, hisEntity, null);
			
			hisEntity.setBizId(bizId);
			hisEntity.setRecordOperType(RecordOperType.INSERT.getValue());
			
			log.debug(ToStringBuilder.reflectionToString(hisEntity, ToStringStyle.MULTI_LINE_STYLE));
			
			recordHis(event.getSession(), hisEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

	public void onPostUpdate(PostUpdateEvent event) {
		log.trace("entering doListener ... ");

		String bizId = null;
		try {
			if (logMetaData == null)
				logMetaData = (LogMetaData) BeanHolder.getBean("logMetaData");
			HttpServletRequest request = ServletActionContext.getRequest();
			bizId = (String) request.getAttribute(Constants.LOG_BIZ_ATTRIBUTE_NAME);
		} catch (Exception e) {
			return;
		}
		
		try {
			String className = event.getEntity().getClass().getName();
			
			HashMap<String, EntityInfo> entityInfoMap = logMetaData.getEntityInfoMap();
			if (!entityInfoMap.containsKey(className))
				return;
			
			// 需要保存历史实体
			IHisEntity hisEntity = (IHisEntity) Class.forName(entityInfoMap.get(className).getHisEntityClass()).newInstance();
			
//			event.getPersister().setPropertyValues(hisEntity, event.getState(), EntityMode.POJO);
//			BeanUtils.copyProperty(hisEntity, "id", event.getId());
			
			Object entity = event.getEntity();
			BeanCopier copy = BeanCopier.create(entity.getClass(), hisEntity.getClass(), false);
			copy.copy(entity, hisEntity, null);
			
			hisEntity.setBizId(bizId);
			hisEntity.setRecordOperType(RecordOperType.UPDATE.getValue());
			
			log.debug(ToStringBuilder.reflectionToString(hisEntity, ToStringStyle.MULTI_LINE_STYLE));
			
			recordHis(event.getSession(), hisEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

	public void onPostDelete(PostDeleteEvent event) {
		log.trace("entering doListener ... ");
		
		String bizId = null;
		try {
			if (logMetaData == null)
				logMetaData = (LogMetaData) BeanHolder.getBean("logMetaData");
			HttpServletRequest request = ServletActionContext.getRequest();
			bizId = (String) request.getAttribute(Constants.LOG_BIZ_ATTRIBUTE_NAME);
		} catch (Exception e) {
			return;
		}
		
		try {
			String className = event.getEntity().getClass().getName();
			
			HashMap<String, EntityInfo> entityInfoMap = logMetaData.getEntityInfoMap();
			if (!entityInfoMap.containsKey(className))
				return;
			
			// 需要保存历史实体
			IHisEntity hisEntity = (IHisEntity) Class.forName(entityInfoMap.get(className).getHisEntityClass()).newInstance();
			
//			event.getPersister().setPropertyValues(hisEntity, event.getDeletedState(), EntityMode.POJO);
//			BeanUtils.copyProperty(hisEntity, "id", event.getId());
			
			Object entity = event.getEntity();
			BeanCopier copy = BeanCopier.create(entity.getClass(), hisEntity.getClass(), false);
			copy.copy(entity, hisEntity, null);
			
			hisEntity.setBizId(bizId);
			hisEntity.setRecordOperType(RecordOperType.DELETE.getValue());
			
			log.debug(ToStringBuilder.reflectionToString(hisEntity, ToStringStyle.MULTI_LINE_STYLE));
			
			recordHis(event.getSession(), hisEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	private void recordHis(Session session, IHisEntity hisEntity) {
		
		Session hisSession = session.getSessionFactory().openSession();
		Transaction tx = hisSession.beginTransaction();
		try {
			tx.begin();
			hisSession.save(hisEntity);
			hisSession.flush();
			tx.commit();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			tx.rollback();
		} finally {
			try {
				hisSession.close();
			} catch (Exception ex) {}
		}
	}

	public void setLogMetaData(LogMetaData logMetaData) {
		this.logMetaData = logMetaData;
	}

}
