package com.ibs.portal.framework.server.interceptor;

import java.io.Serializable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.action.IDataAction;
import com.ibs.portal.framework.server.action.IDataBundleAction;
import com.ibs.portal.framework.share.util.Toolkit;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 富客户端数据结果
 *
 * <p/>
 * 注册配置如下：(struts.xml)
 * <pre>
 * &lt;result-types&gt;
 *     &lt;result-type name="data" class="com.ibs.portal.framework.server.action.DataResult" /&gt;
 * &lt;/result-types&gt;
 * </pre>
 * 使用配置如下：(struts.xml)
 * <pre>
 * &lt;action name="login" class="com.ibs.test.action.UserAction" method="login"&gt;
 *     &lt;result name="success" type="data" /&gt;
 * &lt;/action&gt;
 * </pre>
 *
 * @author 
 *
 */
public class DataResult extends BaseResult {

	private static final long serialVersionUID = 1L;

	protected void doExecute(String location, ActionInvocation invocation) throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		Object action = invocation.getAction();
		response.setContentType("application/x-serialization");
		if (action instanceof IDataAction) {
			IDataAction richAction = (IDataAction)action;
			ServletOutputStream out = response.getOutputStream();
			Toolkit.getSerializer().serialize((Serializable)richAction.getResponseData(), out);
			//out.flush();
		} else if (action instanceof IDataBundleAction) {
			IDataBundleAction richAction = (IDataBundleAction)action;
			ServletOutputStream out = response.getOutputStream();
			Toolkit.getSerializer().serialize((Serializable)richAction.getResponseDataBundle(), out);
			//out.flush();
		}
	}

}
