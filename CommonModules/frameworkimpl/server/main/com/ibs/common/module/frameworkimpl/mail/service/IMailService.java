package com.ibs.common.module.frameworkimpl.mail.service;

import java.util.List;

import com.ibs.common.module.frameworkimpl.mail.dto.MailMessageDto;

public interface IMailService {
	/**
	 * 邮件发送器
	 * @param mailMessageList
	 */
	public void MailListProcessor(List<MailMessageDto> mailMessageList);
	
	/**
	 * 邮件发送器
	 * @param mailMessage
	 */
	public void MailProcessor(MailMessageDto mailMessage);
}
