package com.ibs.portal.framework.server.interceptor;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.interceptor.support.IgnoreFieldProcessorImpl;
import com.ibs.portal.framework.server.interceptor.support.JsonDateValueProcessor;
import com.ibs.portal.framework.server.interceptor.support.JsonNumberValueProcessor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class JsonObjectResult extends BaseResult {

	private static Logger logger = LoggerFactory.getLogger(JsonObjectResult.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String include = "include"; // 需要json转换的对象
	private String excludeProperties = "excludeProperties"; // 不需要json转换的属性

	static JsonDateValueProcessor jsonDateValueProcessor = new JsonDateValueProcessor();
	static JsonNumberValueProcessor jsonNumberValueProcessor = new JsonNumberValueProcessor();

	@Override
	protected void doExecute(String location, ActionInvocation invocation)
			throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
//		HttpServletRequest request = (HttpServletRequest) actionContext
//				.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);

		try {
			Object result = invocation.getStack().findValue(include);
			
//			invocation.getResult();
			
			JsonConfig config = new JsonConfig();
			
			// 循环引用
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 

			String[] fields = null;
			if(-1 != excludeProperties.indexOf(",")){
				// 数组
				fields = excludeProperties.split(",");
			} else{
				fields = new String[]{excludeProperties};
			}
			
			if(null != fields)
				config.setJsonPropertyFilter(new IgnoreFieldProcessorImpl(fields)); // 忽略掉集合对象 
			
			response.setContentType("text/javascript;charset=UTF-8");
			
			config.registerJsonValueProcessor(Date.class, jsonDateValueProcessor);
			config.registerJsonValueProcessor(Timestamp.class, jsonDateValueProcessor);
			config.registerDefaultValueProcessor(Integer.class, jsonNumberValueProcessor);
			config.registerDefaultValueProcessor(Number.class, jsonNumberValueProcessor);
			config.registerDefaultValueProcessor(Short.class, jsonNumberValueProcessor);
			config.registerDefaultValueProcessor(Long.class, jsonNumberValueProcessor);
			config.registerDefaultValueProcessor(BigDecimal.class, jsonNumberValueProcessor);
			config.registerDefaultValueProcessor(Double.class, jsonNumberValueProcessor);
			config.registerDefaultValueProcessor(Float.class, jsonNumberValueProcessor);
			
			StringBuffer strb = new  StringBuffer();
			if(result == null){
				result = new ArrayList();
			}
			if (result instanceof Collection<?>)
				strb.append(JSONArray.fromObject(result, config).toString());
			else{
				JSONObject d =  JSONObject.fromObject(result, config);
				strb.append(d.toString());
			}
			PrintWriter out = response.getWriter();
			out.write(strb.toString());
//			logger.debug("#######################JSON:"+strb.toString());
		} catch (Exception e) {
			logger.error("JqGridMessageResult error!", e);
		}

	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getExcludeProperties() {
		return excludeProperties;
	}

	public void setExcludeProperties(String excludeProperties) {
		this.excludeProperties = excludeProperties;
	}

}
