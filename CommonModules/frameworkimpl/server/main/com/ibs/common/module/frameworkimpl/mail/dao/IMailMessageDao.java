package com.ibs.common.module.frameworkimpl.mail.dao;

import java.util.List;

import com.ibs.common.module.frameworkimpl.mail.dto.MailMessageDto;

public interface IMailMessageDao {
	
	public void saveMail(MailMessageDto mailMessage);

	public void saveMails(List<MailMessageDto> mailMessageList);
	
}
