package com.ibs.common.module.frameworkimpl.intergration.adapter;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import com.ibs.common.module.frameworkimpl.intergration.ISGAdapter;
import com.ibs.common.module.frameworkimpl.intergration.ISGConverter;
import com.ibs.common.module.frameworkimpl.intergration.SGException;

/*******************************************************************************
 * Copyright sf-express. All rights reserved. Description: 消息同步发送（接收）Adapter
 ******************************************************************************/
public class SyncJMSAdapter implements ISGAdapter {

	// JMSTemplate，由Spring注入
	private JmsTemplate jmsTemplate;
	// 发送队列，由Spring注入
	private Queue sendQueue;
	// 接收队列，由Spring注入
	private Queue receiveQueue;
	// 发送数据转换器，由Spring注入
	private ISGConverter sendConverter;
	// 接收数据转换器，由Spring注入
	private ISGConverter receiveConverter;

	// 响应对象的Class,必须设置
	private Class<?> receiveTOClazz;

	/**
	 * 服务处理 发送消息到sendQueue队列，从接收队列接收返回消息
	 */
	public Object send(Object to) throws SGException {
		String correlationID = UUID.randomUUID().toString();
		return this.send(to, correlationID, null);
	}

	/**
	 * 服务处理 设置关联ID，发送消息到sendQueue队列，从接收队列接收返回消息
	 */
	public Object send(Object to, final String correlationID) throws SGException {
		return this.send(to, correlationID, null);
	}

	/**
	 * 服务处理 设置消息头属性，发送消息到sendQueue队列，从接收队列接收返回消息
	 */
	public Object send(Object to, final Map<String, ?> header) throws SGException {
		String correlationID = UUID.randomUUID().toString();
		return send(to, correlationID, header);
	}

	/**
	 * 服务处理 设置消息的关联ID，消息头属性，发送消息到sendQueue，从接收队列接收返回消息
	 */
	public Object send(Object to, final String correlationID, final Map<String, ?> header) throws SGException {
		if (receiveTOClazz == null)
			throw new IllegalArgumentException("Must set the receiveTOClazz property");

		String xml = sendConverter.toXML(to);
		// 发送消息
		jmsTemplate.convertAndSend(sendQueue, xml, new MessagePostProcessor() {
			public Message postProcessMessage(Message message) throws JMSException {
				if ((header != null) && !header.isEmpty()) {
					Set<String> keys = header.keySet();
					for (String key : keys) {
						message.setObjectProperty(key, header.get(key));
					}
				}
				message.setJMSCorrelationID(correlationID);
				return message;
			}
		});

		// 接收消息
		String rcvXml = (String) receiveMessage(correlationID);
		return receiveConverter.fromXML(rcvXml, receiveTOClazz);
	}

	/**
	 * 根据关联ID接收消息，同步等待、接收消息
	 * 
	 * @param correlationID
	 * @return
	 */
	private Object receiveMessage(String correlationID) {
		// 阻塞接收消息
		String selector = String.format("JMSCorrelationID='%s'", correlationID);
		return jmsTemplate.receiveSelectedAndConvert(receiveQueue, selector);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setSendQueue(Queue sendQueue) {
		this.sendQueue = sendQueue;
	}

	public void setReceiveQueue(Queue receiveQueue) {
		this.receiveQueue = receiveQueue;
	}

	public void setSendConverter(ISGConverter sendConverter) {
		this.sendConverter = sendConverter;
	}

	public void setReceiveConverter(ISGConverter receiveConverter) {
		this.receiveConverter = receiveConverter;
	}

	/**
	 * 响应对象的Class,必须设置
	 * 
	 * @param receiveToClazz
	 */
	public void setReceiveTOClazz(Class<?> receiveTOClazz) {
		this.receiveTOClazz = receiveTOClazz;
	}

}
