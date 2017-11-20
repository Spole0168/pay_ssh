package com.ibs.common.module.frameworkimpl.intergration.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.jms.support.converter.MessageConverter;

import com.ibs.common.module.frameworkimpl.intergration.SGException;

/**
 * <pre>
 * *********************************************
 * Description: 消息监听基础类
 * *********************************************
 * </pre>
 */
public class SGMessageListener implements MessageListener {

	// jms消息转换器
	private MessageConverter jmsConverter;

	public void setJmsConverter(MessageConverter jmsConverter) {
		this.jmsConverter = jmsConverter;
	}

	public void onMessage(Message message) {
		try {
			// 转换消息成文本内容
			String xml = (String) jmsConverter.fromMessage(message);
			// 将消息文本保存到ThreadLocal，在异常时，可以取得该文本
			MessageHolder.setMessage(xml);
			// 处理消息
			processMessage(xml);
		} catch (Throwable e) {
			throw new SGException("convert message error", e);
		}
	}

	/**
	 * 具体的消息监听器实现该方法，进行具体的消息处理
	 * 
	 * @param message
	 */
	public void processMessage(String message) {

	}
}
