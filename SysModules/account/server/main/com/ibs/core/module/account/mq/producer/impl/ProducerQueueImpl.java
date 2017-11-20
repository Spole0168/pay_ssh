package com.ibs.core.module.account.mq.producer.impl;





import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.ibs.core.module.account.mq.producer.IProducer;

public class ProducerQueueImpl implements IProducer{
	
	private JmsTemplate jmsTemplate; 

public void sendMessage(final  String message) {
		
		final String msg = message;
		Destination destination = jmsTemplate.getDefaultDestination();
		jmsTemplate.send(destination, new MessageCreator() {  
			public Message createMessage(Session session) throws JMSException {  
				return session.createTextMessage(msg);  
			}  
		});
		
	}
	
	public void sendMessage(final String destinationName, final  String message) {
		
		final String msg = message;
//		Destination destination = jmsTemplate.getDefaultDestination();
		jmsTemplate.send(destinationName, new MessageCreator() {  
			public Message createMessage(Session session) throws JMSException {  
				return session.createTextMessage(msg);  
			}  
		});
		
	}

	public void sendMessageQueue(Destination destination, String message) {
	
		
	}

	public void sendMessageTopic(String topic, String message) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the jmsTemplate
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * @param jmsTemplate the jmsTemplate to set
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	

}

