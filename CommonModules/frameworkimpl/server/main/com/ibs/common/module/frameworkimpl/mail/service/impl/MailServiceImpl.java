package com.ibs.common.module.frameworkimpl.mail.service.impl;

import java.util.List;

import com.ibs.common.module.frameworkimpl.mail.dao.IMailMessageDao;
import com.ibs.common.module.frameworkimpl.mail.dto.MailMessageDto;
import com.ibs.common.module.frameworkimpl.mail.service.IMailService;
import com.ibs.portal.framework.server.service.BaseService;

public class MailServiceImpl extends BaseService implements IMailService {
	private IMailMessageDao mailMessageDao;
	
	/**
	 * 邮件发送器
	 * @param mailMessageList
	 */
	public void MailListProcessor(List<MailMessageDto> mailMessageList){
		logger.trace("entering service...");
		
		mailMessageDao.saveMails(mailMessageList);
	}
	
	/**
	 * 邮件发送器
	 * @param mailMessage
	 */
	public void MailProcessor(MailMessageDto mailMessage){
		logger.trace("entering service...");
		
		mailMessageDao.saveMail(mailMessage);
		
	}

	
	/*--------------- get/set ----------------*/
	public IMailMessageDao getMailMessageDao() {
		return mailMessageDao;
	}

	public void setMailMessageDao(IMailMessageDao mailMessageDao) {
		this.mailMessageDao = mailMessageDao;
	}
	/*--------------- get/set ----------------*/

}
