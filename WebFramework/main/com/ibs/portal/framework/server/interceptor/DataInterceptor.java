package com.ibs.portal.framework.server.interceptor;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.action.IDataAction;
import com.ibs.portal.framework.server.action.IDataBundleAction;
import com.ibs.portal.framework.share.metadata.DataBundle;
import com.ibs.portal.framework.share.util.Toolkit;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 富客户端数据解包拦截器
 * 
 * <p/> 注册配置如下：(struts.xml)
 * 
 * <pre>
 * &lt;interceptors&gt;
 *     &lt;interceptor name=&quot;data&quot; class=&quot;com.ibs.portal.framework.server.interceptor.DataInterceptor&quot; /&gt;
 * &lt;/interceptors&gt;
 * </pre>
 * 
 * @author 
 * 
 */
public class DataInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		Object action = invocation.getAction();
		if (action instanceof IDataAction) {
			IDataAction richAction = (IDataAction) action;
			Serializable model = Toolkit.getSerializer().deserialize(
					request.getInputStream());
			richAction.setRequestData(model);
		} else if (action instanceof IDataBundleAction) {
			IDataBundleAction richAction = (IDataBundleAction) action;
			Serializable model = Toolkit.getSerializer().deserialize(
					request.getInputStream());
			richAction.setRequestDataBundle((DataBundle) model);
		}
		return invocation.invoke();
	}

}
