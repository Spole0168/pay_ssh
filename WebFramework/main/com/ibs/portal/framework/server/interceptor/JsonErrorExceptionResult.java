package com.ibs.portal.framework.server.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.JavaScriptUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class JsonErrorExceptionResult extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4359763251644999441L;

	protected static Logger logger = LoggerFactory
			.getLogger(JsonErrorExceptionResult.class);

	@SuppressWarnings("unchecked")
	protected void doExecute(String location, ActionInvocation invocation)
			throws Exception {
		List err = null;
		Collection val = null;
		Object action = invocation.getAction();

		if (action instanceof ValidationAware) {
			ValidationAware validationAwareAction = (ValidationAware) action;
			if (validationAwareAction.hasErrors()) {
				err = (List) validationAwareAction.getActionErrors();
			}
			if ((err == null || err.size() <= 0)
					&& validationAwareAction.hasFieldErrors()) {
				Map m = validationAwareAction.getFieldErrors();
				if (null != m && m.size() > 0) {
					val = m.values();
					if (null != val && val.size() > 0) {
						err = new ArrayList();
						err.addAll(val);
					}
				}
			}
		}

		String errMsg = "";
		if (null != err && err.size() > 0) {
			for (Object o : err) {
				if (null != o) {
					if (o.getClass().isAssignableFrom(ArrayList.class)) {
						List msgList = (List) err.get(0);
						for (Object msg : msgList) {
							if (null != msg) {
								errMsg = msg.toString();
								break;
							}
						}

					} else {
						errMsg = o.toString();
						if (null != errMsg
								&& errMsg
										.startsWith("the request was rejected because its size (")) {
							ActionContext ac = invocation
									.getInvocationContext();
							errMsg = getTextMessage(
									"struts.messages.error.file.too.large", ac
											.getLocale());
						}
					}
					break;
				}
			}
		}

		if (null == errMsg || "".equals(errMsg)) {
			errMsg = "It seens some unknow error prisent when you access :["
					+ action.toString() + "]";
		}

		errMsg = JavaScriptUtils.javaScriptEscape(errMsg);

		HttpServletResponse response = (HttpServletResponse) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);

		response.setContentType("text/javascript;charset=UTF-8");

		PrintWriter out = response.getWriter();

		JSONObject json = new JSONObject();
		json.put("success", "false");
		// json.put("status", "error");
		// json.put("responseText", errMsg);
		json.put("text", errMsg);

		if (logger.isDebugEnabled())
			logger.debug("return error message is:" + json.toString());

		out.print(json.toString());
		// out.write("{success:false,status:'" + errMsg + "'"));
		// out.flush();
	}

	private String getTextMessage(String messageKey, Locale locale) {
		return LocalizedTextUtil.findText(this.getClass(), messageKey, locale);
	}
}
