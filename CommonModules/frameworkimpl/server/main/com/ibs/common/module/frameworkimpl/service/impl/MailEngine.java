package com.ibs.common.module.frameworkimpl.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Class for sending e-mail messages based on Velocity templates or with
 * attachments.
 * 
 * <p>
 * <a href="MailEngine.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author Matt Raible
 */
public class MailEngine {
	protected static final Log log = LogFactory.getLog(MailEngine.class);

	private MailSender mailSender;
	private String from;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Send a simple message with pre-populated values.
	 * 
	 * @param msg
	 */
	public void send(SimpleMailMessage msg) {
		try {
			msg.setFrom(from);
			mailSender.send(msg);
		} catch (MailException ex) {
			// log it and go on
			log.error(ex.getMessage());
		}
	}

	/**
	 * @param emailAddresses
	 * @param subject
	 * @param bodyText
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMessage(String[] emailAddresses, String subject,
			String bodyText) throws MessagingException,
			UnsupportedEncodingException {
		MimeMessage message = ((JavaMailSenderImpl) mailSender)
				.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(from);
		helper.setTo(emailAddresses);
		helper.setSubject(subject);
		helper.setText(bodyText, true);

		((JavaMailSenderImpl) mailSender).send(message);
	}

	/**
	 * Convenience method for sending messages with attachments.
	 * 
	 * @param emailAddresses
	 * @param resource
	 * @param bodyText
	 * @param subject
	 * @param attachmentName
	 * @throws MessagingException
	 * @author Ben Gill
	 */
	public void sendMessageAttachment(String[] emailAddresses,
			ClassPathResource resource, String bodyText, String subject,
			String attachmentName) throws MessagingException {
		MimeMessage message = ((JavaMailSenderImpl) mailSender)
				.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(from);
		helper.setTo(emailAddresses);
		helper.setText(bodyText,true);
		helper.setSubject(subject);
		
		//附件
		//helper.addAttachment(attachmentName, resource);

		((JavaMailSenderImpl) mailSender).send(message);
	}

}
