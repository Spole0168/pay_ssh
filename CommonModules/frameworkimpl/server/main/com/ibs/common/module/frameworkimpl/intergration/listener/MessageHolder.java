package com.ibs.common.module.frameworkimpl.intergration.listener;

/**
 * <pre>
 * *********************************************
 * Description: MessageHolder
 * *********************************************
 * </pre>
 */
class MessageHolder {

	public static void setMessage(String message) {
		MessageHolder.message.set(message);
	}

	public static String getMessage() {
		return MessageHolder.message.get();
	}

	private static ThreadLocal<String> message = new ThreadLocal<String>();
}
