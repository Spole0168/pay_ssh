package com.ibs.common.module.frameworkimpl.intergration.listener;

import com.ibs.common.module.frameworkimpl.intergration.listener.SGExceptionHandler;
import com.ibs.common.module.frameworkimpl.intergration.util.SGLog;

/**
 * <pre>
 * *********************************************
 * Description: 缺省的JMS异常处理
 * *********************************************
 * </pre>
 */
public class DefaultSGExceptionHandler implements SGExceptionHandler {

	public void onException(Throwable e, String message, String packageName) {
		SGLog.error(e, message, packageName);
	}

}
