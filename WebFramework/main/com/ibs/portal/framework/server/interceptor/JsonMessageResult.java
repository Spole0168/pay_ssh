package com.ibs.portal.framework.server.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * JqGrid操作在某些场合要返回状态
 * 
 * @author luoyue
 *
 */
public class JsonMessageResult extends BaseResult{
	
	private static Logger logger = LoggerFactory.getLogger(JsonMessageResult.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8228503248809386020L;
	
	private String message = "message";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	protected void doExecute(String location, ActionInvocation invocation)
			throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();

		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);

		try {
			Object result = invocation.getStack().findValue(message);
//			invocation.getResult();
			
			response.setContentType("text/javascript;charset=UTF-8");

			PrintWriter out = response.getWriter();
			
			JSONObject json = new JSONObject();
			json.put("message", result);
			
			out.write(json.toString());
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("JqGridMessageResult error!", e);
			}
			//logger.error("JqGridMessageResult error!", e);
		}

	}

}
