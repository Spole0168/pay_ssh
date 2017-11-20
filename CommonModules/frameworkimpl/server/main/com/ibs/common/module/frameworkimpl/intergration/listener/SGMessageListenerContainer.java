package com.ibs.common.module.frameworkimpl.intergration.listener;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.ibs.common.module.frameworkimpl.intergration.SGException;

/**
 * <pre>
 * *********************************************
 * Description: JMS消息监听容器，增加了异常处理方法
 * *********************************************
 * </pre>
 */
public class SGMessageListenerContainer extends DefaultMessageListenerContainer {

	// 异常处理接口
	private SGExceptionHandler sgErrorHandler;

	/**
	 * 接收、处理消息，做了异常处理包装
	 */
	protected boolean receiveAndExecute(Session session, MessageConsumer consumer) throws JMSException {
		boolean messageReceived = false;
		try {
			messageReceived = super.receiveAndExecute(null, session, consumer);
		} catch (Throwable e) {
			handleException(e);
			throw new SGException(e);
		} finally {
			MessageHolder.setMessage(null);
		}

		return messageReceived;
	}

	/**
	 * 重载handleListenerException，调用自己的异常处理函数
	 */
	protected void handleListenerException(Throwable ex) {
		super.handleListenerException(ex);
		handleException(ex); 
	}

	/**
	 * 异常处理
	 * 
	 * @param e
	 */
	private void handleException(Throwable e) {
		// 取得消息文本
		String message = MessageHolder.getMessage();
		if ((sgErrorHandler != null) && (message != null)) {
			// 取得包名
			Object listener = getMessageListener();
			String packageName = listener.getClass().getPackage().getName();
			// 调用异常处理接口，执行异常处理
			sgErrorHandler.onException(e, message, packageName);
		}
	}

	public void setSgErrorHandler(SGExceptionHandler sgErrorHandler) {
		this.sgErrorHandler = sgErrorHandler;
	}
}
