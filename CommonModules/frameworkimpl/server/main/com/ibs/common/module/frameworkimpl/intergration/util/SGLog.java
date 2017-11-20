package com.ibs.common.module.frameworkimpl.intergration.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.UUID;

import com.ibs.common.module.frameworkimpl.intergration.*;
import com.ibs.common.module.frameworkimpl.intergration.config.SGConfig;

/**
 * <pre>
 * *********************************************
 * Description: 消息错误日志处理
 * *********************************************
 * </pre>
 */
public class SGLog {

	/**
	 * 错误消息处理：<br>
	 * 1、保存报文到文件：消息ID + ".xml"<br>
	 * 2、保存错误堆栈信息到文件: 消息ID + ".err"<br>
	 * 
	 * @param e
	 * @param message
	 * @param packageName
	 */
	public static void error(Throwable e, String message, String packageName) {
		if (message == null) {
			message = "";
		}
		String fileName = getFileName(message, packageName);
		// 保存消息报文
		saveDatagram(fileName, message);
		// 保存错误堆栈
		saveErrorStack(fileName, e);
	}

	/**
	 * 通过类名、消息对象，获取保存消息报文的路径
	 * 
	 * @param clazz
	 * @param message
	 * @return
	 */
	private static String getFileName(String message, String packageName) {
		StringBuilder path = new StringBuilder();
		// 根目录
		path.append(SGConfig.getInstance().getRootPath()).append("/");
		// 目录：日期
		path.append(SGConfig.getInstance().getDateFormater().format(new Date())).append("/");
		// 目录：包名称
		path.append(getClassPackage(packageName)).append("/");
		// 文件名：
		UUID uuid = UUID.nameUUIDFromBytes(message.getBytes());
		path.append(uuid.toString());
		return path.toString();
	}

	/**
	 * 获取类的包名
	 * 
	 * @param clazz
	 * @return
	 */
	private static String getClassPackage(String packageName) {
		String pkg = "";
		String rootPackage = SGConfig.getInstance().getRootPackage();
		if (packageName.startsWith(rootPackage)) {
			int startIndex = rootPackage.length() + 1;
			int endIndex = packageName.indexOf('.', startIndex);
			if (endIndex > 0) {
				pkg = packageName.substring(startIndex, endIndex);
			}
		}
		return pkg;
	}

	/**
	 * 保存消息到文件
	 * 
	 * @param fileName
	 * @param msg
	 */
	private static void saveDatagram(String fileName, String msg) {
		fileName = fileName + ".xml";
		// 检查目录
		checkPath(fileName.substring(0, fileName.lastIndexOf('/')));

		// 保存消息报文
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			file.write(msg.getBytes("UTF-8"));
			file.close();
		} catch (Exception e) {
			throw new SGException("save datagram error", e);
		}
	}

	/**
	 * 保存异常堆栈到文件
	 * 
	 * @param fileName
	 * @param t
	 */
	private static void saveErrorStack(String fileName, Throwable t) {
		fileName = fileName + ".err";
		// 保存消息报文
		try {
			PrintStream ps = new PrintStream(new File(fileName));
			// 第一行输出异常原因
			ps.println(getCause(t));
			// 输出完整的异常堆栈
			t.printStackTrace(ps);
			ps.close();
		} catch (Exception e) {
			throw new SGException("save error stack error", e);
		}
	}

	/**
	 * 检查目录是否存在，如果不存在，创建目录
	 * 
	 * @param path
	 */
	private static void checkPath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			synchronized (SGLog.class) {
				file.mkdirs();
			}
		}
	}

	/**
	 * 获取最底层的异常原因
	 * 
	 * @param t
	 * @return
	 */
	private static String getCause(Throwable t) {
		Throwable cause = t;
		while (cause.getCause() != null) {
			cause = cause.getCause();
		}
		return "Caused by: " + cause.getMessage();
	}
}
