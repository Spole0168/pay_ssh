package com.ibs.portal.framework.server.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.action.BaseDataAction;
import com.ibs.portal.framework.server.action.BaseDataBundleAction;
import com.ibs.portal.framework.server.exception.PermissionNotAssignedException;
import com.ibs.portal.framework.share.metadata.DataBundle;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * GUI异常拦截器
 *
 * @author 
 *
 */
public class GUIExceptionInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 2797068749600011060L;
	private static final Log logger = LogFactory
			.getLog(GUIExceptionInterceptor.class);

	private enum DIRECT {
		INPUT, FORWARD, MESSAGE, SUCCESS {
			@Override
			public String toString() {
				return super.toString().toLowerCase();
			}
		};
	}

	private class HandleInfo {
		DIRECT direct;
		Exception ex;

	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		String retPage = null;
		BaseAction action = (BaseAction) ai.getAction();
		try {
			retPage = ai.invoke();
			return retPage;
		} catch(PermissionNotAssignedException ex){
			if(logger.isErrorEnabled()){
				logger.error(ex.getMessage());
			}
			//logger.error(ex.getMessage());
			HandleInfo hi = handleException(ex);
			refreshData(action, hi);
			return hi.direct.toString();
		}  catch (Exception ex) {
			if(logger.isErrorEnabled()){
				logger.error(ex.getMessage(), ex);
			}
			//logger.error(ex.getMessage(), ex);
			String exName = ex.getClass().getName();
			if (! exName.startsWith("java.")
					&& ! exName.startsWith("javax.")
					&& ! exName.startsWith("com.ibs.portal.")) {
				ex = new RuntimeException(getMessage(ex)); // 非标准异常统一转换
			}
			HandleInfo hi = handleException(ex);
			refreshData(action, hi);
			return hi.direct.toString();
		}
	}

	private void refreshData(BaseAction action, HandleInfo hi) {
		if (!guiCall(action))
			return;

		if (BaseDataAction.class.isAssignableFrom(action.getClass())) {
			((BaseDataAction) action).setResponseData(hi.ex);
		} else if (BaseDataBundleAction.class.isAssignableFrom(action
				.getClass())) {
			DataBundle dbd = new DataBundle();
			dbd.setException(hi.ex);
			((BaseDataBundleAction) action).setResponseDataBundle(dbd);
		}

		hi.direct = DIRECT.SUCCESS;
	}

	private boolean guiCall(BaseAction action) {
		return (BaseDataAction.class.isAssignableFrom(action.getClass()))
				|| BaseDataBundleAction.class.isAssignableFrom(action
						.getClass());
	}

	private HandleInfo handleException(Exception ex) {
		HandleInfo hi = new HandleInfo();
		hi.direct = DIRECT.INPUT;
		hi.ex = ex;
		return hi;
	}

	private static String getMessage(Exception e) {
		StringWriter out = new StringWriter();
		e.printStackTrace(new PrintWriter(out));
		String msg = out.getBuffer().toString();
		return e.getClass().getName() + ": " + e.getMessage() + "\n" + msg;
	}

}
