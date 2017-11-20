package com.ibs.core.module.account.mq.producer;

import javax.jms.Destination;


public interface IProducer {

	public void sendMessage(final String destinationName, final  String message);
	public void sendMessage(final  String message);
	
	public void sendMessageQueue(Destination destination,final  String message);
	
	public void sendMessageTopic(String topic,final  String message);
	
}