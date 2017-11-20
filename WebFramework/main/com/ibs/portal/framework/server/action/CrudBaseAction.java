package com.ibs.portal.framework.server.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.GridModel;
import com.ibs.portal.framework.server.metadata.PageCache;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;

public abstract class CrudBaseAction extends JqGridBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8474887933678699654L;
	
	private boolean isModify = false;
	
	//导出参数对象
	protected ExportSetting exportSetting;
	
	/**
	 * Action方法，需要继承，跳转到新增页面
	 * @return
	 */
	public abstract String create();

	/**
	 * Action方法，需要继承，获取修改的数据，并跳转到修改页面
	 * @return
	 */
	public abstract String modify();
	
	/**
	 * Action方法，需要继承，保存当前数据，并跳转到列表页面
	 * @return
	 */
	public abstract String saveOrUpdate();
	
	/**
	 * Action方法，列表
	 * @return
	 */
	public  abstract String list();
	
	/**
	 * Action方法，查询
	 * @return
	 */
	public abstract String search();
	
	/**
	 * Action方法，删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract String delete();
	
	/**
	 * Action方法，导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract String export();
	
	/**
	 * 导出相关数据
	 */
	@SuppressWarnings("unchecked")
	public void populateExportData() {
		Map<String, String[]> parameters = ServletActionContext.getRequest()
				.getParameterMap();
		Set<String> keys = parameters.keySet();
		for (String key : keys) {
			String[] values = parameters.get(key);
			
			if(key.startsWith(ExportSetting.EXPORT_COL_NAMES)) {
				String seqStr = key.substring(ExportSetting.EXPORT_COL_NAMES.length());
				int seq = Integer.parseInt(seqStr);
				if (null != values && values.length > 0){
					exportSetting.addExportColName(seq, values[0]);
				}

			} else if (key.startsWith(ExportSetting.EXPORT_COL_MODELS)) {
				String seqStr = key.substring(ExportSetting.EXPORT_COL_MODELS.length());
				int seq = Integer.parseInt(seqStr);
				if (null != values && values.length > 0){
					exportSetting.addExportColModel(seq, values[0]);
				}
			} else if (key.startsWith(ExportSetting.EXPORT_COL_MODEL_LIST)){
				String seqStr = key.substring(ExportSetting.EXPORT_COL_MODEL_LIST.length());
				int seq = Integer.parseInt(seqStr);
				if (null != values && values.length > 0){
					try {
						JsonConfig config = new JsonConfig();
						config.setExcludes(new String[]{"editrules"});
						JSONObject json = JSONObject.fromObject(values[0], config);
						GridModel model = (GridModel) JSONObject.toBean(json, GridModel.class);
//						GridModel model = (GridModel) JSONObject.toBean(json, GridModel.class, config);
						if (StringUtils.isNotEmpty(model.getWidth()) && model.getWidth().indexOf("%") > 0) {
							model.setRealWidth(Float.floatToIntBits(Float.valueOf(model.getWidth()) * 1000));
						} else {
							model.setRealWidth(Integer.valueOf(model.getWidth()));
						}
						if (model.getEditoptions() != null) {
							String option = model.getEditoptions().getValue();
							String[] ms = option.split(";");
							HashMap<String, String> maps = new HashMap<String, String>(ms.length);
							for (String m : ms) {
								String[] d = m.split(":");
								if (d.length >= 2)
									maps.put(d[0].trim(), d[1].trim());
							}
							model.setMaps(maps);
						}
						exportSetting.addExportColModelList(seq, model);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}

			} else if ("fileName".equals(key)) {
				if (null != values && values.length > 0){
					String fileName = values[0];
					try {
						fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
						exportSetting.setFileName(fileName);
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage(), e);
					} 
				}

			}
		}
	}	

	
	/**
	 * 将String数组转为long数组
	 */
	protected Integer[] toIntgerArray(String[] stringArray) {
		Integer[] integerArray = new Integer[stringArray.length];
		for(int i = 0 ; i < stringArray.length; i ++) {
			integerArray[i] = new Integer(Integer.parseInt(stringArray[i]));
		}
		
		return integerArray;
	}
	
	/**
	 * 将String数组转为Long数组
	 */
	protected Long[] toLongArray(String[] stringArray) {
		Long[] integerArray = new Long[stringArray.length];
		for(int i = 0 ; i < stringArray.length; i ++) {
			integerArray[i] = new Long(Long.parseLong(stringArray[i]));
		}
		
		return integerArray;
	}	
	
	/**
	 * 前端AOP
	 */
	public void beforeInvoke(String method) {
		super.beforeInvoke(method);
		
		//方法名是search
		if(StringUtils.startsWith(method, "search") || StringUtils.startsWith(method, "query")) {
			this.populateJqGridData();
			
			//创建QueryPage
			queryPage = new QueryPage(this.getRows(),this.getPage(),this.getSidx(),this.getSord());
		}
		//如果方法名是delete
		else if("delete".equals(method)) {
			this.populateJqGridData();
		}
		//如果方法名是create
		else if("create".equals(method)) {
			this.setIsModify(false);
		}
		//如果方法名是modify
		else if("modify".equals(method)) {
			this.setIsModify(true);
		}
		//如果方法名是list
		else if("list".equals(method)) {
			// load from cache
			IUser su = UserContext.getUserContext().getCurrentUser();
			String loadPageCache = ServletActionContext.getRequest().getParameter("loadPageCache");
			if (null != su && "true".equals(loadPageCache)) {
				String actionName = this.getClass().getName();
				PageCache oldCache = su.getPageCache(actionName);
				try {
					if (oldCache != null) {
						Map<String, Object> maps = oldCache.getPageProperties();
						Map<String, Object> maps2 = new HashMap<String, Object>();
						for (Map.Entry<String, Object> entry : maps.entrySet()) {
							maps2.put(entry.getKey(), URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
							searchFields.put(entry.getKey(), URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
						}
//						Cookie cookie = new Cookie("pageCache", URLEncoder.encode(new Gson().toJson(maps, Map.class), "UTF-8"));
//						Cookie cookie = new Cookie("pageCache", new Gson().toJson(maps2, Map.class));
						JSONObject json = new JSONObject();
						json.putAll(maps2);
						Cookie cookie = new Cookie("pageCache", json.toString());
						ServletActionContext.getResponse().addCookie(cookie);
						
						for (Map.Entry<String, Object> entry : maps.entrySet()) {
							try {
								BeanUtils.copyProperty(this, entry.getKey(), entry.getValue());
							} catch (Exception e) {}
							// support ModelDriven
							try {
								Method mth = this.getClass().getMethod("getModel", null);
								Object model = mth.invoke(this);
								try {
									BeanUtils.copyProperty(model, entry.getKey(), entry.getValue());
								} catch(Exception e) {}
							} catch(Exception e) {}
						}
					}
				} catch (Exception e) {}
			}
			
//			IUser su = UserContext.getUserContext().getCurrentUser();
//			if (null != su) {
//				//String actionName = ActionContext.getContext().getName();
//				String actionName = this.getClass().getName();
//				pageCache = su.getPageCache(actionName);
//				try {
//					if(null!=pageCache) BeanUtils.populate(this, pageCache.getPageProperties());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
//			}
		}
		//如果方法名是export
		else if(StringUtils.startsWith(method, "export")) {
			this.populateJqGridData();
			
			//创建ExportSetting
			exportSetting = new ExportSetting(this.getSidx(),this.getSord());
			
			this.populateExportData();
		}		
	}
	

	public boolean getIsModify() {
		return isModify;
	}

	public void setIsModify(boolean isModify) {
		this.isModify = isModify;
	}
	
	public ExportSetting getExportSetting() {
		return exportSetting;
	}

	
}
